package ro.stefanhalus.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.prefs.Preferences;

public class Main extends Application {
    /*
     * Use window Stage variable as popup root
     * */
    private Stage window;
    /*/
     * define a root variable of type Border Pane
     * BorderPane allows complex interfaces
     */
    private static BorderPane root = new BorderPane();

    private Preferences pref = Preferences.userRoot().node("BusPlaces");

    /*
     * root getter to be used by controller
     */
    private static BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        window.setTitle("Bus Places - cerish your place");
        window.getIcons().add(new Image(Main.class.getResourceAsStream("images/autocar_welcome.png")));
        Scene scene = new Scene(root, 999, 550);
        scene.getStylesheets().add(getClass().getResource("style/stil.css").toExternalForm());
        window.setScene(scene);
        authentificated();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void authentificated() {
        boolean authentificated = pref.getBoolean("empAuthentificated", false);
        boolean remembered = pref.getBoolean("empRemember", false);
        if (remembered) {
            loadWelcome();
        } else {
            loadLogin();
        }
    }

    public void loadWelcome() {
        URL menuBarUrl = getClass().getResource("views/AppMenu.fxml");
        URL centerUrl = getClass().getResource("views/Welcome.fxml");
        MenuBar bar = null;
        AnchorPane center = null;
        try {
            bar = FXMLLoader.load(menuBarUrl);
            center = FXMLLoader.load(centerUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setTop(bar);
        root.setCenter(center);
    }

    private void loadLogin() {
        AnchorPane center = null;
        try {
            URL centerUrl = getClass().getResource("views/Login.fxml");
            center = FXMLLoader.load(centerUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setTop(null);
        root.setCenter(center);
    }

    /*
     * Method that implements center pane switching
     * */
    public static void goTo(String pane) {
        try {
            /*
             * Loading the FXML for center pane requested by controller switch function
             * After loading it, set it as center pane
             * */
            URL dest = Main.class.getResource("views/" + pane + ".fxml");
            AnchorPane aPane = FXMLLoader.load(dest);
            BorderPane border = Main.getRoot();
            border.setCenter(aPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method used to show popup focused dialogs
     * */
    public void popup(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(String.format("views/%s.fxml", fxml)));
            Stage popup = new Stage();
            Scene popupScene = new Scene(loader.load());
            popupScene.getStylesheets().add(getClass().getResource("style/stil.css").toExternalForm());
            popup.setScene(popupScene);
            popup.setTitle(title);
            popup.getIcons().add(new Image(Main.class.getResourceAsStream("images/autocar_welcome.png")));
            popup.initOwner(window);
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
