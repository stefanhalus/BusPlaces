package ro.stefanhalus.java.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ro.stefanhalus.java.db.EmployeeAdapter;
import ro.stefanhalus.java.models.Employee;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;
import ro.stefanhalus.java.utilClases.Transport;

import java.util.List;

public class EmployeeDialogController {

    private EmployeeAdapter employeeAdapter = new EmployeeAdapter();
    private Employee employee;

    @FXML
    public void initialize() {
        btnDel.setVisible(false);
        comboFillTitle();
        if (Transport.obj != null) {
            employee = (Employee) Transport.obj;
            if (employee.getId() > 0) {
                btnDel.setVisible(true);
                textFieldFill();
            }
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private void comboFillTitle() {
        List<ComboListKeyValue> titles = employeeAdapter.titlesList();
        ObservableList<ComboListKeyValue> oTitles = FXCollections.observableArrayList(titles);
        cbTitle.setItems(oTitles);
        cbTitle.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbTitle.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void textFieldFill() {
        tfFirstName.setText(employee.getFirstName());
        tfLastName.setText(employee.getLastName());
        tfUser.setText(employee.getUser());
        tfEmail.setText(employee.getEmail());
        tfPhone.setText(employee.getPhone());
        ComboListKeyValue cbs = new ComboListKeyValue();
        cbs.setID(employee.getTitle_id());
        cbs.setName(employee.getTitle());
        cbTitle.getSelectionModel().select(cbs);
    }

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfUser;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPhone;

    @FXML
    private ComboBox<ComboListKeyValue> cbTitle;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnClose;

    @FXML
    void btnActClose(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void btnActDel(ActionEvent event) {
        if (employee.getId() > 0) {
            Employee save = new Employee();
            save.setId(employee.getId());
            employeeAdapter.employeeDelete(save);
            closeWindow();
        }
    }

    @FXML
    void btnActSave(ActionEvent event) {
        ComboListKeyValue titleid = cbTitle.getSelectionModel().getSelectedItem();
        Employee save = new Employee();
        if(employee != null) {
            save.setId(employee.getId());
        } else {
            save.setId(0);
        }
        save.setFirstName(tfFirstName.getText());
        save.setLastName(tfLastName.getText());
        save.setUser(tfUser.getText());
        save.setEmail(tfEmail.getText());
        save.setPhone(tfPhone.getText());
        save.setTitle_id(titleid.getID());
        employeeAdapter.employeeSave(save);
        closeWindow();
    }


}
