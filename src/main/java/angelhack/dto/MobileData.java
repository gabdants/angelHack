package angelhack.dto;

public class MobileData {

    private String _rev;
    private String _id;
    private String lat;
    private String longi;
    private String temperature;

    public MobileData() {
        this._rev = _rev;
        this._id = _id;
        this.lat = lat;
        this.longi = longi;
        this.temperature = temperature;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "lat='" + lat + '\'' +
                ", longi='" + longi + '\'' +
                ", temperature='" + temperature;
    }
}
