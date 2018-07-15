package ro.stefanhalus.java.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ro.stefanhalus.java.db.ClientAdapter;
import ro.stefanhalus.java.db.ReservationAdapter;
import ro.stefanhalus.java.models.Client;
import ro.stefanhalus.java.models.Reservation;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;
import ro.stefanhalus.java.utilClases.Transport;

import java.util.List;

public class ReservationDialogController {

    private Client client;
    private ClientAdapter clientAdapter = new ClientAdapter();
    private Reservation reservation = (Reservation) Transport.obj;
    private ReservationAdapter reservationAdapter = new ReservationAdapter();

    @FXML
    public void initialize() {
        client = clientAdapter.clientById(reservation.getClient_id());
        fillReservation();
        fillClient();
        fillComboBox();
    }

    @FXML
    private Label lblRouteName;

    @FXML
    private Label lblRouteDeparture;

    @FXML
    private Label lblRouteArrival;

    @FXML
    private Label lblRouteDate;

    @FXML
    private Label lblClientName;

    @FXML
    private Label lblClientPhone;

    @FXML
    private Label lblClientEmail;

    @FXML
    private ComboBox<ComboListKeyValue> cbStatus;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnClose;

    @FXML
    void btnActClose(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void btnActSave(ActionEvent event) {
        ComboListKeyValue newStatus = cbStatus.getSelectionModel().getSelectedItem();
        reservationAdapter.reswrvationStatusUpdate(reservation.getId(), newStatus.getID());
        System.out.println("Id selectat: "+ newStatus.getID());
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private void fillReservation() {
        lblRouteName.setText(reservation.getRoute_name());
        lblRouteDeparture.setText(reservation.getDeparture_city());
        lblRouteArrival.setText(reservation.getArrival_city());
        lblRouteDate.setText(reservation.getDate());
    }

    private void fillClient(){
        lblClientName.setText(client.getFirstName() + " " + client.getLastName());
        lblClientPhone.setText(client.getPhone());
        lblClientEmail.setText(client.getEmail());
    }

    private void fillComboBox(){
        ComboListKeyValue cbSelected = new ComboListKeyValue();
        cbSelected.setID(reservation.getStatus_id());
        cbSelected.setName(reservation.getStatus_name());

        List<ComboListKeyValue> selectList = reservationAdapter.statusList();

        ObservableList<ComboListKeyValue> oSelectList = FXCollections.observableArrayList(selectList);
        cbStatus.setItems(oSelectList);
        cbStatus.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbStatus.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
        cbStatus.getSelectionModel().select(cbSelected);
    }

}
