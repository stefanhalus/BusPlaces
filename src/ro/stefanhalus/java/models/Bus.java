package ro.stefanhalus.java.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bus {
    private IntegerProperty id;
    private StringProperty mark;
    private StringProperty model;
    private StringProperty matriculation;
    private IntegerProperty places;
    private StringProperty comfort;

    public Bus(){
        this.id = new SimpleIntegerProperty();
        this.mark = new SimpleStringProperty();
        this.model = new SimpleStringProperty();
        this.matriculation = new SimpleStringProperty();
        this.places = new SimpleIntegerProperty();
        this.comfort = new SimpleStringProperty();
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

    public String getMark() {
        return mark.get();
    }

    public StringProperty markProperty() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark.set(mark);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getMatriculation() {
        return matriculation.get();
    }

    public StringProperty matriculationProperty() {
        return matriculation;
    }

    public void setMatriculation(String matriculation) {
        this.matriculation.set(matriculation);
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

    public String getComfort() {
        return comfort.get();
    }

    public StringProperty comfortProperty() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort.set(comfort);
    }
}
