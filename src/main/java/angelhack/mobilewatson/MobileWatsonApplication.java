package angelhack.mobilewatson;

import angelhack.dto.MobileData;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.IndexField;
import com.cloudant.client.api.model.Response;
import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryOptions;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class MobileWatsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileWatsonApplication.class, args);
	}

	@Autowired
	protected Discovery discovery;

	@Autowired
    private CloudantClient client;

	String djaman;




	@GetMapping("/discover")
	String discovery(@RequestParam String query){
		QueryOptions options = new QueryOptions.Builder("40fcc52c-7f64-4d3c-a27d-c1f1e2171925", "ef033e6a-317d-4d54-aef5-92502024b248").naturalLanguageQuery(query).build();

		QueryResponse queryResponse = discovery.query(options).execute();

		String titles = queryResponse.getResults().stream()
				.map(r -> (String) r.get("title"))
				.collect(Collectors.joining("\n<p>"));

		return titles;
	}

	@GetMapping("cloudant")
    String cloudant(){
	    List<String> list = new ArrayList<>();

	    try {
            list = client.getAllDbs();

        }catch (Exception e){
            return "error" + e.getMessage();
        }

	    return "dbs: " + list.toString();
    }

	@Autowired
	private Database db;


	@PostMapping(path = "/saveData", consumes = "application/json")
	public @ResponseBody
    String saveReview(@RequestBody MobileData mobileData) {
		System.out.println("Save MobileData " + mobileData);
		Response r = null;
		if (mobileData != null) {
			r = db.post(mobileData);
		}

		return mobileData.toString();
	}

	@GetMapping("/get")
	public @ResponseBody
	List getAll(@RequestParam(required=false) String itemId) {
		// Get all documents from socialreviewdb
		List allDocs = null;
		try {
			if (itemId == null) {
				allDocs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
						.getDocsAs(MobileData.class);
			} else {
				db.createIndex("querybyitemIdView", "mobiledoc", "json",
						new IndexField[]{new IndexField("itemId", IndexField.SortOrder.asc)});
				System.out.println("Successfully created index");
				allDocs = db.findByIndex("{\"itemId\" : " + itemId + "}", MobileData.class);
				System.out.println(allDocs.get(0).toString());
			}
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}

		return allDocs;
	}


}
