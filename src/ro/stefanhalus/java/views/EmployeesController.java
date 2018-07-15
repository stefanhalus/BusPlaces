package ro.stefanhalus.java.views;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ro.stefanhalus.java.Main;
import ro.stefanhalus.java.db.EmployeeAdapter;
import ro.stefanhalus.java.models.Employee;
import ro.stefanhalus.java.models.Right;
import ro.stefanhalus.java.utilClases.Transport;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeesController {

    EmployeeAdapter adaptEmployee = new EmployeeAdapter();
    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colUser;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private ListView<String> listRights;

    @FXML
    void btnActEmployee(ActionEvent event) {
        Main main = new Main();
        main.popup("EmployeeDialog", "Adaugă angajat nou");
        Transport.obj = null;
        fillEmployeeData();
    }

    @FXML
    void tblEmployeeEditClick(MouseEvent event) {
        if(event.getClickCount() == 2){
            Employee emp = tblEmployee.getSelectionModel().getSelectedItem();
            Transport.obj = (Object) emp;
            Main main = new Main();
            main.popup("EmployeeDialog", "Adaugă angajat nou");
            Transport.obj = null;
            fillEmployeeData();
        }
    }

    @FXML
    void tblEmployeeEditEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            Employee emp = tblEmployee.getSelectionModel().getSelectedItem();
            Transport.obj = (Object) emp;
            Main main = new Main();
            main.popup("EmployeeDialog", "Adaugă angajat nou");
            Transport.obj = null;
            fillEmployeeData();
        }
    }


    @FXML
    void initialize() {
        fillEmployeeData();
        fillRightsData();


    }

    private void fillEmployeeData() {
        List<Employee> listEmployee = adaptEmployee.employeeList();
        ObservableList<Employee> observableList = FXCollections.observableList(listEmployee);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//        colUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblEmployee.setItems(observableList);

    }

    private void fillRightsData() {
        List<Right> listRight = adaptEmployee.rightsList();
        List<String> sList = new ArrayList<>();
        for (Right myRight : listRight) {
            sList.add(myRight.getName());
        }
        ObservableList<String> oListRight = FXCollections.observableArrayList(sList);

        listRights.setItems(oListRight);
    }
}
