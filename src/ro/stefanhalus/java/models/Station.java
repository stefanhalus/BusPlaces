package ro.stefanhalus.java.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Station {
    private IntegerProperty id;
    private IntegerProperty route_id;
    private IntegerProperty station_id;
    private StringProperty arrival;
    private StringProperty departure;
    private IntegerProperty order;
    private IntegerProperty distance;
    private StringProperty station_name;
    private StringProperty city_name;
    private StringProperty district_name;
    private StringProperty district_short;
    private IntegerProperty city_id;
    private IntegerProperty district_id;
    private StringProperty station_description;


    public Station(){
        this.id = new SimpleIntegerProperty(0);
        this.route_id = new SimpleIntegerProperty(0);
        this.station_id = new SimpleIntegerProperty(0);
        this.arrival = new SimpleStringProperty();
        this.departure = new SimpleStringProperty();
        this.order = new SimpleIntegerProperty(0);
        this.distance = new SimpleIntegerProperty();
        this.station_name = new SimpleStringProperty();
        this.city_name = new SimpleStringProperty();
        this.district_name = new SimpleStringProperty();
        this.district_short = new SimpleStringProperty();
        this.city_id = new SimpleIntegerProperty(0);
        this.district_id = new SimpleIntegerProperty(0);
        this.station_description = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getRoute_id() {
        return route_id.get();
    }

    public IntegerProperty route_idProperty() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id.set(route_id);
    }

    public int getStation_id() {
        return station_id.get();
    }

    public IntegerProperty station_idProperty() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id.set(station_id);
    }

    public String getArrival() {
        return arrival.get();
    }

    public StringProperty arrivalProperty() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival.set(arrival);
    }

    public String getDeparture() {
        return departure.get();
    }

    public StringProperty departureProperty() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure.set(departure);
    }

    public int getOrder() {
        return order.get();
    }

    public IntegerProperty orderProperty() {
        return order;
    }

    public void setOrder(int order) {
        this.order.set(order);
    }

    public int getDistance() {
        return distance.get();
    }

    public IntegerProperty distanceProperty() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance.set(distance);
    }

    public String getStation_name() {
        return station_name.get();
    }

    public StringProperty station_nameProperty() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name.set(station_name);
    }

    public String getCity_name() {
        return city_name.get();
    }

    public StringProperty city_nameProperty() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name.set(city_name);
    }

    public String getDistrict_name() {
        return district_name.get();
    }

    public StringProperty district_nameProperty() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name.set(district_name);
    }

    public String getDistrict_short() {
        return district_short.get();
    }

    public StringProperty district_shortProperty() {
        return district_short;
    }

    public void setDistrict_short(String district_short) {
        this.district_short.set(district_short);
    }

    public int getCity_id() {
        return city_id.get();
    }

    public IntegerProperty city_idProperty() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id.set(city_id);
    }

    public String getStation_description() {
        return station_description.get();
    }

    public StringProperty station_descriptionProperty() {
        return station_description;
    }

    public void setStation_description(String station_description) {
        this.station_description.set(station_description);
    }

    public int getDistrict_id() {
        return district_id.get();
    }

    public IntegerProperty district_idProperty() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id.set(district_id);
    }
}
