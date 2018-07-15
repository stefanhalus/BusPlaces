package ro.stefanhalus.java.views;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import ro.stefanhalus.java.Main;
import ro.stefanhalus.java.db.EmployeeAdapter;
import ro.stefanhalus.java.models.Employee;

import java.util.prefs.Preferences;

public class LoginController {

    private EmployeeAdapter employeeAdapter = new EmployeeAdapter();
    private int loginCount = 3;
    private Preferences pref;

    @FXML
    public void initialize() {
        pref = Preferences.userRoot().node("BusPlaces");
        pref.putBoolean("empAuthentificated", false);
    }

    @FXML
    private TextField tfUser;

    @FXML
    private PasswordField tfPass;

    @FXML
    private Button btnAuth;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnRememberPassword;

    @FXML
    private CheckBox chkKeepLogedIn;

    @FXML
    void btnActAuth(ActionEvent event) {
        Validate();
    }

    @FXML
    void btnActClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnActRememberPassword(ActionEvent event) {
        Main main = new Main();
        main.popup("LoginRememberPassword", "Hai să resetăm parola!");
    }

    private void Validate() {
        Employee emp = employeeAdapter.employeeLogin(tfUser.getText(), tfPass.getText());
        if (emp.getId() != 0) {
            saveLoginData(emp);
            if (chkKeepLogedIn.isSelected()) {
                pref.putBoolean("empRemember", true);
            } else {
                clearLoginData();
            }
            Main main = new Main();
            main.loadWelcome();
        } else {
            if (loginCount < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Autentificare nereușită");
                alert.setHeaderText("Ai greșit de 4 ori!");
                alert.setContentText("Ne pare rău, dar ne despărțim aici. \n\nVă dorim o zi cât mai plăcută și \nmai multă atenție și inspirație data viitor.");
                alert.showAndWait();
                System.exit(0);
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Autentificare nereușită");
            alert.setHeaderText("Se pare că ai ceva probleme la autentificare");
            alert.setContentText("Mai ai : " + loginCount + " încercări rămase.");
            alert.showAndWait();
            loginCount = loginCount - 1;
        }
    }

    private void saveLoginData(Employee emp) {
        pref.put("empUser", emp.getUser());
        pref.put("empPass", emp.getPass());
        pref.putInt("empId", emp.getId());
        pref.putBoolean("empAuthentificated", true);
    }

    private void clearLoginData() {
        pref.put("empUser", "");
        pref.put("empPass", "");
        pref.putInt("empId", 0);
        pref.putBoolean("empRemember", false);
    }

    private void closeWindow() {
        Stage stage = (Stage) btnAuth.getScene().getWindow();
        stage.close();
    }

}
