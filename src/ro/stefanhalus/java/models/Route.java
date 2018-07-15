package ro.stefanhalus.java.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.SimpleStyleableIntegerProperty;

public class Route {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty bus;
    private IntegerProperty bus_id;
    private StringProperty bus_name;

    public Route(){
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.bus = new SimpleStringProperty();
        this.bus_id = new SimpleIntegerProperty();
        this.bus_name = new SimpleStringProperty();
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getBus() {
        return bus.get();
    }

    public StringProperty busProperty() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus.set(bus);
    }

    public int getBus_id() {
        return bus_id.get();
    }

    public IntegerProperty bus_idProperty() {
        return bus_id;
    }

    public void setBus_id(int bus_id) {
        this.bus_id.set(bus_id);
    }

    public String getBus_name() {
        return bus_name.get();
    }

    public StringProperty bus_nameProperty() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name.set(bus_name);
    }
}
