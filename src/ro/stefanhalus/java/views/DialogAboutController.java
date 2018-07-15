package ro.stefanhalus.java.views;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.stefanhalus.java.models.AboutApp;

public class DialogAboutController {

    AboutApp about = new AboutApp();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;

    @FXML
    private TextField tfNume;

    @FXML
    private TextField tfVersiune;

    @FXML
    private TextField tfAutor;

    @FXML
    private Hyperlink hlEmail;

    @FXML
    private Hyperlink hlWebSite;

    @FXML
    private TextArea taDescription;


    @FXML
    void closeDialog(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void openEmail(ActionEvent event) {
        try {
            Desktop.getDesktop().mail (new URL("mailto:" + about.getEmail()).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openWww(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL(about.getWebsite()).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        tfNume.setText(about.getName());
        tfVersiune.setText(about.getVersion());
        tfAutor.setText(about.getAuthor());
        hlEmail.setText(about.getEmail());
        hlWebSite.setText(about.getWebsite());
        taDescription.setText(about.getDescription());
    }
}
