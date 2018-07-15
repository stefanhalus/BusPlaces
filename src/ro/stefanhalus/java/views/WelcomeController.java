package ro.stefanhalus.java.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ro.stefanhalus.java.Main;
import ro.stefanhalus.java.db.WelcomeAdapter;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class WelcomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private ListView<String> listUserLogedIn;

    @FXML
    private Label lblReservations;

    @FXML
    private Label lblClients;

    @FXML
    private Label lblRoutes;

    @FXML
    private Label lblStations;

    @FXML
    private Label lblDrivers;

    @FXML
    private Label lblBuses;

    @FXML
    private Label lblEmployees;

    @FXML
    private Button btnLogout;

    private WelcomeAdapter welcomeAdapt = new WelcomeAdapter();

    private Preferences pref;

    @FXML
    void initialize(){
        pref = Preferences.userRoot().node("BusPlaces");
        if(pref.get("empUser", "").equals("")){
            listUserLogedIn.setVisible(false);
        }
        listUserLogedIn.getItems().add("user: " + pref.get("empUser", ""));
        listUserLogedIn.getItems().add("user id: " + pref.getInt("empId", 0));
        listUserLogedIn.getItems().add("authentificated: " + pref.getBoolean("empAuthentificated", false));
        listUserLogedIn.getItems().add("Validated: " + pref.getBoolean("empRemember", false));

        List<Integer> stats = welcomeAdapt.welcomeStatistics();
        lblReservations.setText(Integer.toString(stats.get(0)));
        lblClients.setText(Integer.toString(stats.get(1)));
        lblRoutes.setText(Integer.toString(stats.get(2)));
        lblStations.setText(Integer.toString(stats.get(3)));
        lblBuses.setText(Integer.toString(stats.get(4)));
        lblEmployees.setText(Integer.toString(stats.get(5)));
        lblDrivers.setText(Integer.toString(stats.get(6)));
    }

    @FXML
    void btnActLogout(ActionEvent event) {
        pref.put("empUser", "");
        pref.put("empPass", "");
        pref.putInt("empId", 0);
        pref.putBoolean("empRemember", false);
        Main main = new Main();
        main.popup("Login", "Schimba'i utilizatorul");
    }

}
