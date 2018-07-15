package ro.stefanhalus.java.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ro.stefanhalus.java.db.BusAdapter;
import ro.stefanhalus.java.models.Feature;

public class FeatureDialogController {

    BusAdapter adaptBus = new BusAdapter();

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfIcon;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnClose;

    @FXML
    void closeDialog(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveFeature(ActionEvent event) {
        Feature feature = new Feature();
        feature.setName(tfName.getText());
        feature.setDescription(tfDescription.getText());
        feature.setIcon(tfIcon.getText());
        adaptBus.saveFeature(feature);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Salvare facilitate");
        alert.setHeaderText("Facilitatea a fost salvată");
        alert.setContentText("Facilitate adăugată cu succes în baza de date");
        Stage myAlert = (Stage) alert.getDialogPane().getScene().getWindow();
        myAlert.getIcons().add(new Image(this.getClass().getResource("../images/autocar_welcome.png").toString()));
        alert.showAndWait();

        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
