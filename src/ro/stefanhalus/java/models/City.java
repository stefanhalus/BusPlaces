package ro.stefanhalus.java.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class City {
    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty distric_id;

    public City(){
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.distric_id = new SimpleIntegerProperty();
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

    public int getDistric_id() {
        return distric_id.get();
    }

    public IntegerProperty distric_idProperty() {
        return distric_id;
    }

    public void setDistric_id(int distric_id) {
        this.distric_id.set(distric_id);
    }
}
