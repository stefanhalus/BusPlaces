package ro.stefanhalus.java.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {

    private IntegerProperty id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty user;
    private StringProperty pass;
    private StringProperty phone;
    private StringProperty email;
    private StringProperty title;
    private IntegerProperty title_id;

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getUser() {
        return user.get();
    }

    public StringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getPass() {
        return pass.get();
    }

    public StringProperty passProperty() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass.set(pass);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getTitle_id() {
        return title_id.get();
    }

    public IntegerProperty title_idProperty() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id.set(title_id);
    }

    public Employee(){
        this.id = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.user = new SimpleStringProperty();
        this.pass = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.title = new SimpleStringProperty();
        this.title_id = new SimpleIntegerProperty();
    }

}
