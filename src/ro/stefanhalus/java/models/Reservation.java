package ro.stefanhalus.java.models;

import javafx.beans.property.*;

public class Reservation {
    private StringProperty arrival_city;
    private IntegerProperty arrival_id;
    private StringProperty arrival_name;
    private IntegerProperty client_id;
    private StringProperty date;
    private StringProperty departure_city;
    private IntegerProperty departure_id;
    private StringProperty departure_name;
    private IntegerProperty distance;
    private IntegerProperty id;
    private IntegerProperty places;
    private DoubleProperty price;
    private IntegerProperty route_id;
    private StringProperty route_name;
    private IntegerProperty status_id;
    private StringProperty status_name;

    public Reservation(){
        this.arrival_city = new SimpleStringProperty();
        this.arrival_id = new SimpleIntegerProperty();
        this.arrival_name = new SimpleStringProperty();
        this.client_id = new SimpleIntegerProperty();
        this.date = new SimpleStringProperty();
        this.departure_city = new SimpleStringProperty();
        this.departure_id = new SimpleIntegerProperty();
        this.departure_name = new SimpleStringProperty();
        this.distance = new SimpleIntegerProperty();
        this.id = new SimpleIntegerProperty();
        this.places = new SimpleIntegerProperty();
        this.price = new SimpleDoubleProperty();
        this.route_id = new SimpleIntegerProperty();
        this.route_name = new SimpleStringProperty();
        this.status_id = new SimpleIntegerProperty();
        this.status_name = new SimpleStringProperty();
    }

    public String getArrival_city() {
        return arrival_city.get();
    }

    public StringProperty arrival_cityProperty() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city.set(arrival_city);
    }

    public int getArrival_id() {
        return arrival_id.get();
    }

    public IntegerProperty arrival_idProperty() {
        return arrival_id;
    }

    public void setArrival_id(int arrival_id) {
        this.arrival_id.set(arrival_id);
    }

    public String getArrival_name() {
        return arrival_name.get();
    }

    public StringProperty arrival_nameProperty() {
        return arrival_name;
    }

    public void setArrival_name(String arrival_name) {
        this.arrival_name.set(arrival_name);
    }

    public int getClient_id() {
        return client_id.get();
    }

    public IntegerProperty client_idProperty() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id.set(client_id);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getDeparture_city() {
        return departure_city.get();
    }

    public StringProperty departure_cityProperty() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city.set(departure_city);
    }

    public int getDeparture_id() {
        return departure_id.get();
    }

    public IntegerProperty departure_idProperty() {
        return departure_id;
    }

    public void setDeparture_id(int departure_id) {
        this.departure_id.set(departure_id);
    }

    public String getDeparture_name() {
        return departure_name.get();
    }

    public StringProperty departure_nameProperty() {
        return departure_name;
    }

    public void setDeparture_name(String departure_name) {
        this.departure_name.set(departure_name);
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

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getPlaces() {
        return places.get();
    }

    public IntegerProperty placesProperty() {
        return places;
    }

    public void setPlaces(int places) {
        this.places.set(places);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
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

    public String getRoute_name() {
        return route_name.get();
    }

    public StringProperty route_nameProperty() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name.set(route_name);
    }

    public int getStatus_id() {
        return status_id.get();
    }

    public IntegerProperty status_idProperty() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id.set(status_id);
    }

    public String getStatus_name() {
        return status_name.get();
    }

    public StringProperty status_nameProperty() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name.set(status_name);
    }
}
