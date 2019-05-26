package angelhack.controller;//package com.angelhack.controller;
//
//import com.angelhack.dto.MobileData;
//import com.cloudant.client.api.Database;
//import com.cloudant.client.api.model.IndexField;
//import com.cloudant.client.api.model.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/review")
//public class ReviewController {
//
//    @Autowired
//    private Database db;
//
//    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
//    public @ResponseBody
//    String saveReview(@RequestBody MobileData review) {
//        System.out.println("Save MobileData " + review);
//        Response r = null;
//        if (review != null) {
//            r = db.post(review);
//        }
//        return r.getId();
//    }
//
//    @RequestMapping(method=RequestMethod.GET)
//    public @ResponseBody
//    List getAll(@RequestParam(required=false) Integer itemId) {
//        // Get all documents from socialreviewdb
//        List allDocs = null;
//        try {
//            if (itemId == null) {
//                allDocs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
//                        .getDocsAs(MobileData.class);
//            } else {
//                // create Index
//                // Create a design doc named designdoc
//                // A view named querybyitemIdView
//                // and an index named itemId
//                db.createIndex("querybyitemIdView", "designdoc", "json",
//                        new IndexField[]{new IndexField("itemId", IndexField.SortOrder.asc)});
//                System.out.println("Successfully created index");
//                allDocs = db.findByIndex("{\"itemId\" : " + itemId + "}", MobileData.class);
//            }
//        } catch (Exception e) {
//            System.out.println("Exception thrown : " + e.getMessage());
//        }
//        return allDocs;
//    }
//
//}
