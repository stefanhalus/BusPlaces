package ro.stefanhalus.java.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import ro.stefanhalus.java.Main;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class OptionsController {

//    ScreensController myController;
//    myController.setScreen(Main.screenEmployee);

    private Preferences pref;

    @FXML
    public void initialize() {
        pref = Preferences.userRoot().node("BusPlaces");

        listEmployees.getItems().add("user: " + pref.get("empUser", ""));
        listEmployees.getItems().add("pass: " + pref.get("empPass", ""));
        listEmployees.getItems().add("user id: " + pref.getInt("empId", 0));
        listEmployees.getItems().add("Validated: " + pref.getBoolean("empRemember", false));
        listEmployees.getItems().add("---:---- ");
    }

//    public void setScreenParent(ScreensController screenParent){
//        myController = screenParent;
//    }

    @FXML
    private Button iconBack;

    @FXML
    private ListView<String> listEmployees;

    @FXML
    void goToWelcome(ActionEvent event) {
//        myController.setScreen(Main.screenWelcome);
    }

}
