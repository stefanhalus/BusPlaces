package ro.stefanhalus.java.views;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import ro.stefanhalus.java.Main;

public class AppMenuNotLogedinController {

    Main main = new Main();

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuFileWelcome;

    @FXML
    private MenuItem menuFileExit;

    @FXML
    private MenuItem menuCompanyEmployee;

    /*
     * File menu switch
     * */
    @FXML
    void goToWelcome(ActionEvent event) {
        Main.goTo("Welcome");
    }

    @FXML
    void ExitApplication(ActionEvent event) {
        Platform.exit();
    }

    /*
     * Company switch
     * */
    @FXML
    void goToCompany(ActionEvent event) {
        Main.goTo("Options");
    }

    @FXML
    void goToAuthentificationData(ActionEvent event) {
        Main.goTo("Welcome");
    }

    @FXML
    void goToEmployeeList(ActionEvent event) {
        Main.goTo("Employees");
    }

    @FXML
    void goToBus(ActionEvent event) {
        Main.goTo("Bus");
    }

    @FXML
    void goToRoutes(ActionEvent event) {
        Main.goTo("Routes");
    }

//    @FXML
//    void goToFeatures(ActionEvent event) {
//        Main.goTo("Features");
//    }

    /*
    * Clients menu
    * */
    @FXML
    void goToClientList(ActionEvent event) {
        Main.goTo("Clients");
    }

    /*
    * Reservations menu
    * */
    @FXML
    void goToReservationList(ActionEvent event) {
        Main.goTo("Reservations");
    }


    /*
    * Help menu
    * */
    @FXML
    void goToHelbAbout(ActionEvent event) {
        main.popup("DialogAbout", "Despre aplicație");
    }

}
