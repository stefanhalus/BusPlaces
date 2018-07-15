package ro.stefanhalus.java.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class District {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty simbol;
    private IntegerProperty phoneCode;
    private IntegerProperty policeCode;

    public District(){
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.simbol = new SimpleStringProperty();
        this.phoneCode = new SimpleIntegerProperty();
        this.policeCode = new SimpleIntegerProperty();
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

    public String getSimbol() {
        return simbol.get();
    }

    public StringProperty simbolProperty() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol.set(simbol);
    }

    public int getPhoneCode() {
        return phoneCode.get();
    }

    public IntegerProperty phoneCodeProperty() {
        return phoneCode;
    }

    public void setPhoneCode(int phoneCode) {
        this.phoneCode.set(phoneCode);
    }

    public int getPoliceCode() {
        return policeCode.get();
    }

    public IntegerProperty policeCodeProperty() {
        return policeCode;
    }

    public void setPoliceCode(int policeCode) {
        this.policeCode.set(policeCode);
    }

}
