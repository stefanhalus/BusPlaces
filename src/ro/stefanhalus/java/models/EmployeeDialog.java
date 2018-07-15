package ro.stefanhalus.java.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class EmployeeDialog {
    private IntegerProperty id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty user;
    private StringProperty pass;
    private StringProperty phone;
    private StringProperty email;
    private StringProperty title;
    private IntegerProperty title_id;

    public EmployeeDialog(){
        this(0);
    }

    public EmployeeDialog(int id){
        this.id = new SimpleIntegerProperty(id);
    }

}
