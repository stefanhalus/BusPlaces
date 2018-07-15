package ro.stefanhalus.java.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ro.stefanhalus.java.db.ClientAdapter;
import ro.stefanhalus.java.db.ReservationAdapter;
import ro.stefanhalus.java.db.RoutesAdapter;
import ro.stefanhalus.java.models.Client;
import ro.stefanhalus.java.models.Reservation;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;

import java.time.LocalDate;
import java.util.List;

public class ReservationDialogAddController {

    private Client client = new Client();
    private ClientAdapter clientAdapter = new ClientAdapter();
    private ReservationAdapter reservationAdapter = new ReservationAdapter();
    private RoutesAdapter routesAdapter = new RoutesAdapter();

    @FXML
    private void initialize(){
        comboFillRoute();
        comboFillDepartureArival();
        comboFillStatus();
        tfTravelPlaces.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,9,1));
    }

    @FXML
    private RadioButton rbClientNew;

    @FXML
    private TextField tfClientFirstName;

    @FXML
    private TextField tfClientLastName;

    @FXML
    private TextField tfClientPhone;

    @FXML
    private TextField tfClientEmail;

    @FXML
    private RadioButton rbClientExisting;

    @FXML
    private ComboBox<ComboListKeyValue> cbClientExistingId;

    @FXML
    private ComboBox<ComboListKeyValue> cbRouteId;

    @FXML
    private ComboBox<ComboListKeyValue> cbDepartureId;

    @FXML
    private ComboBox<ComboListKeyValue> cbArrivalId;

    @FXML
    private DatePicker tfTravelDate;

    @FXML
    private Spinner<Integer> tfTravelPlaces;

    @FXML
    private TextField tfDistance;

    @FXML
    private TextField tfPrice;

    @FXML
    private ComboBox<ComboListKeyValue> cbStatusId;

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
        readClientData();
        ComboListKeyValue cbRoute = cbRouteId.getSelectionModel().getSelectedItem();
        ComboListKeyValue cbDep = cbDepartureId.getSelectionModel().getSelectedItem();
        ComboListKeyValue cbArr = cbArrivalId.getSelectionModel().getSelectedItem();
        ComboListKeyValue cbStatus = cbStatusId.getSelectionModel().getSelectedItem();
        LocalDate dateSelected = tfTravelDate.getValue();

        Reservation rez = new Reservation();
        rez.setClient_id(client.getId());
        rez.setRoute_id(cbRoute.getID());
        rez.setDeparture_id(cbDep.getID());
        rez.setArrival_id(cbArr.getID());
        rez.setDate(dateSelected.toString());
        rez.setPlaces(tfTravelPlaces.getValue());
        rez.setDistance(Integer.parseInt(tfDistance.getText()));
        rez.setPrice(Double.parseDouble(tfPrice.getText()));
        rez.setStatus_id(cbStatus.getID());
        reservationAdapter.reservationAdd(client, rez);
        closeWindow();
    }

    @FXML
    void rbActNewEdit(ActionEvent event) {
        clientNewEnabeEditing(true);
        rbClientExisting.setSelected(false);
    }

    @FXML
    void rbClientExistingSelect(ActionEvent event) {
        clientNewEnabeEditing(false);
        rbClientNew.setSelected(false);
        cbClientExistingId.setDisable(false);
        comboFillClient();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
        cbClientExistingId.setDisable(true);
        cbClientExistingId.setValue(null);
    }

    private void clientNewEnabeEditing(Boolean edit){
        tfClientFirstName.setEditable(edit);
        tfClientLastName.setEditable(edit);
        tfClientPhone.setEditable(edit);
        tfClientEmail.setEditable(edit);
    }

    private void comboFillClient(){
        List<ComboListKeyValue> routeDistinct = clientAdapter.clientListCombo();
        ObservableList<ComboListKeyValue> oRouteDistinct = FXCollections.observableArrayList(routeDistinct);
        cbClientExistingId.setItems(oRouteDistinct);
        cbClientExistingId.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbClientExistingId.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void comboFillRoute(){
        List<ComboListKeyValue> routeDistinct = routesAdapter.routeListDistinctCombo();
        ObservableList<ComboListKeyValue> oRouteDistinct = FXCollections.observableArrayList(routeDistinct);
        cbRouteId.setItems(oRouteDistinct);
        cbRouteId.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbRouteId.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void comboFillDepartureArival(){
        List<ComboListKeyValue> routeDistinct = reservationAdapter.stationList();
        ObservableList<ComboListKeyValue> oRouteDistinct = FXCollections.observableArrayList(routeDistinct);
        cbDepartureId.setItems(oRouteDistinct);
        cbDepartureId.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbDepartureId.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
        cbArrivalId.setItems(oRouteDistinct);
        cbArrivalId.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbArrivalId.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void comboFillStatus(){
        List<ComboListKeyValue> routeDistinct = reservationAdapter.statusList();
        ObservableList<ComboListKeyValue> oRouteDistinct = FXCollections.observableArrayList(routeDistinct);
        cbStatusId.setItems(oRouteDistinct);
        cbStatusId.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbStatusId.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void readClientData(){
        if(rbClientExisting.isSelected()){
            ComboListKeyValue clCmb = cbClientExistingId.getSelectionModel().getSelectedItem();
            client.setId(clCmb.getID());
        } else if (rbClientNew.isSelected()){
            client.setId(0);
            client.setFirstName(tfClientFirstName.getText());
            client.setLastName(tfClientLastName.getText());
            client.setPass(tfClientPhone.getText());
            client.setEmail(tfClientEmail.getText());
        }
    }

}
