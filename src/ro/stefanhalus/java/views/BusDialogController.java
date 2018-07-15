package ro.stefanhalus.java.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ro.stefanhalus.java.Main;
import ro.stefanhalus.java.utilClases.Transport;
import ro.stefanhalus.java.db.BusAdapter;
import ro.stefanhalus.java.models.Bus;

import java.util.Optional;

public class BusDialogController {

    private Bus bus = new Bus();
    private Object busData = null;
    private BusAdapter busAdapt = new BusAdapter();
    private Main main = new Main();
    private Integer busId = 0;

    public BusDialogController() {
        busId = Transport.id;
        busData = Transport.obj;
    }

    @FXML
    void initialize() {
        try {
            if (busData != null) {
//                Bus busData = busAdapt.busData(busId);
                fillData((Bus)busData);
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @FXML
    private TextField tfMark;

    @FXML
    private TextField tfModel;

    @FXML
    private TextField tfMatriculation;

    @FXML
    private TextField tfPlaces;

    @FXML
    private TextField tfComfort;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClose;

    @FXML
    void closeDialog(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteData(ActionEvent event) {
        Bus myBus = (Bus)Transport.obj;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ștergere autocar");
        alert.setHeaderText("Doriți să ștergeți elementul \n" + myBus.getMark() + " " + myBus.getModel() + " (" + myBus.getMatriculation() + ")?");
        alert.setContentText("Sigur ștergeți datele?");

        Stage myAlert = (Stage) alert.getDialogPane().getScene().getWindow();
        myAlert.getIcons().add(new Image(this.getClass().getResource("../images/autocar_welcome.png").toString()));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            busAdapt.deleteBus(myBus);
        } else {
            // ... user chose CANCEL or closed the dialog
        }

        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveData(ActionEvent event) {
        addEdit();
    }

    private void addEdit() {
        // collecting data
        bus.setMark(tfMark.getText());
        bus.setModel(tfModel.getText());
        bus.setMatriculation(tfMatriculation.getText());
        bus.setPlaces(Integer.parseInt(tfPlaces.getText()));
        bus.setComfort(tfComfort.getText());
        String action;
        // take action SAVE or UPDATE
        if(busId > 0){
            bus.setId(busId);
            busAdapt.updateBus(bus);
            action = "ADĂUGATE";
        } else {
            busAdapt.saveBus(bus);
            action = "ACTUALIZATE";
        }

        // Show Confirmation dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Salvare date despre autocar");
        alert.setHeaderText(String.format("Datele au fost %s", action));
        alert.setContentText("Autocar adăugat cu succes în baza de date");
        Stage myAlert = (Stage) alert.getDialogPane().getScene().getWindow();
        myAlert.getIcons().add(new Image(this.getClass().getResource("../images/autocar_welcome.png").toString()));
        alert.showAndWait();

        // Closw the window
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private void fillData(Bus date) {
        if (date != null) {
            tfMark.setText(date.getMark());
            tfModel.setText(date.getModel());
            tfMatriculation.setText(date.getMatriculation());
            tfPlaces.setText(Integer.toString(date.getPlaces()));
            tfComfort.setText(date.getComfort());
        }
    }

}
