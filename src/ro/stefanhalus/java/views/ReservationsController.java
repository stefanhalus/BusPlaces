package ro.stefanhalus.java.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import ro.stefanhalus.java.Main;
import ro.stefanhalus.java.db.ReservationAdapter;
import ro.stefanhalus.java.db.RoutesAdapter;
import ro.stefanhalus.java.models.Client;
import ro.stefanhalus.java.models.Reservation;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;
import ro.stefanhalus.java.utilClases.Transport;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.List;

public class ReservationsController {

    private ReservationAdapter reservationAdapter = new ReservationAdapter();
    private RoutesAdapter routeAdapter = new RoutesAdapter();
    private int selectedReservationId = 0;
    @FXML
    void initialize(){
        reservationColumnsBind();
        reservationRefresh();
        comboFillRoute();
        istoricColumnsBind();
        istoricRezervariFill();
    }
    @FXML
    private TableView<Reservation> rezervariTable;

    @FXML
    private TableColumn<?, ?> rezervariColRuta;

    @FXML
    private TableColumn<?, ?> rezervariColClient;

    @FXML
    private TableColumn<?, ?> rezervariColPlecare;

    @FXML
    private TableColumn<?, ?> rezervariColSosire;

    @FXML
    private TableColumn<?, ?> rezervariColData;

    @FXML
    private TableColumn<?, ?> rezervariColPlaces;

    @FXML
    private TableView<Reservation> searchTable;

    @FXML
    private TableColumn<?, ?> searchColRoute;

    @FXML
    private TableColumn<?, ?> searchColClient;

    @FXML
    private TableColumn<?, ?> searchColDeparture;

    @FXML
    private TableColumn<?, ?> searchColArrival;

    @FXML
    private TableColumn<?, ?> searchColDate;

    @FXML
    private TableColumn<?, ?> searchColPlaces;

    @FXML
    private TableView<Reservation> istoricTable;

    @FXML
    private TableColumn<?, ?> istoricColRuta;

    @FXML
    private TableColumn<?, ?> istoricColClient;

    @FXML
    private TableColumn<?, ?> istoricColPlecare;

    @FXML
    private TableColumn<?, ?> istoricColSosire;

    @FXML
    private TableColumn<?, ?> istoricColData;

    @FXML
    private TableColumn<?, ?> istoricColLocuri;


    @FXML
    private ComboBox<ComboListKeyValue> cbRouteSelect;

    @FXML
    private DatePicker dpDateSelect;

    @FXML
    private Button btnSelect;

    @FXML
    private Button btnAdd;

    @FXML
    void btnActAdd(ActionEvent event) {
        Main main = new Main();
        main.popup("ReservationDialogAdd", "Adaugă rezervare nouă");
        istoricRezervariFill();
        reservationRefresh();
        searchReservationsFill();
    }

    @FXML
    void btnActSelect(ActionEvent event) {
        searchReservationsFill();
    }

    @FXML
    void istoricActClickDouble(MouseEvent event) {
        if (event.getClickCount() == 2){
            Reservation show = istoricTable.getSelectionModel().getSelectedItem();
            showDetails(show);
            istoricRezervariFill();
            reservationRefresh();
        }
    }

    @FXML
    void istoricActEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE){
            Reservation show = istoricTable.getSelectionModel().getSelectedItem();
            showDetails(show);
            istoricRezervariFill();
            reservationRefresh();
        }
    }

    @FXML
    void rezervariActClickDouble(MouseEvent event) {
        if (event.getClickCount() == 2){
            Reservation show = rezervariTable.getSelectionModel().getSelectedItem();
            showDetails(show);
            istoricRezervariFill();
            reservationRefresh();
        }
    }

    @FXML
    void rezervariActEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE){
            Reservation show = rezervariTable.getSelectionModel().getSelectedItem();
            showDetails(show);
            istoricRezervariFill();
            reservationRefresh();
        }
    }

    @FXML
    void searchActClickDouble(MouseEvent event) {
        if (event.getClickCount() == 2){
            Reservation show = searchTable.getSelectionModel().getSelectedItem();
            showDetails(show);
            istoricRezervariFill();
            reservationRefresh();
            searchReservationsFill();
        }
    }

    @FXML
    void searchActEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE){
            Reservation show = searchTable.getSelectionModel().getSelectedItem();
            showDetails(show);
            istoricRezervariFill();
            reservationRefresh();
            searchReservationsFill();
        }
    }

    private void showDetails(Reservation reservation){
        Transport.obj = (Object)reservation;
        Main main = new Main();
        main.popup("ReservationDialog", "Detaliile rezervării");
    }

    private void reservationColumnsBind(){
        rezervariColRuta.setCellValueFactory(new PropertyValueFactory<>("route_name"));
        rezervariColClient.setCellValueFactory(new PropertyValueFactory<>("status_name"));
        rezervariColPlecare.setCellValueFactory(new PropertyValueFactory<>("departure_name"));
        rezervariColSosire.setCellValueFactory(new PropertyValueFactory<>("arrival_name"));
        rezervariColData.setCellValueFactory(new PropertyValueFactory<>("date"));
        rezervariColPlaces.setCellValueFactory(new PropertyValueFactory<>("places"));
    }

    private void searchColumnsBind(){
        searchColRoute.setCellValueFactory(new PropertyValueFactory<>("route_name"));
        searchColClient.setCellValueFactory(new PropertyValueFactory<>("status_name"));
        searchColDeparture.setCellValueFactory(new PropertyValueFactory<>("departure_name"));
        searchColArrival.setCellValueFactory(new PropertyValueFactory<>("arrival_name"));
        searchColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        searchColPlaces.setCellValueFactory(new PropertyValueFactory<>("places"));
    }

    private void istoricColumnsBind(){
        istoricColRuta.setCellValueFactory(new PropertyValueFactory<>("route_name"));
        istoricColClient.setCellValueFactory(new PropertyValueFactory<>("status_name"));
        istoricColPlecare.setCellValueFactory(new PropertyValueFactory<>("departure_name"));
        istoricColSosire.setCellValueFactory(new PropertyValueFactory<>("arrival_name"));
        istoricColData.setCellValueFactory(new PropertyValueFactory<>("date"));
        istoricColLocuri.setCellValueFactory(new PropertyValueFactory<>("places"));
    }

    private void reservationRefresh(){
        selectedReservationId = 0;
        List<Reservation> reservationRefresh = reservationAdapter.reservationsList("reservation");
        ObservableList<Reservation> oReservationRefresh = FXCollections.observableArrayList(reservationRefresh);
        rezervariTable.getItems().clear();
        rezervariTable.setItems(oReservationRefresh);
    }

    private void searchReservationsFill(){
        ComboListKeyValue routeSelected = cbRouteSelect.getSelectionModel().getSelectedItem();
        LocalDate dateSelected = dpDateSelect.getValue();
        List<Reservation> searchResult = reservationAdapter.reservationsLisByRouteDate(routeSelected.getID(), dateSelected.toString());
        ObservableList<Reservation> oSearchResult = FXCollections.observableArrayList(searchResult);

        searchColumnsBind();
        searchTable.getItems().clear();
        searchTable.setItems(oSearchResult);
    }

    private void istoricRezervariFill(){
        selectedReservationId = 0;
        List<Reservation> istoric = reservationAdapter.reservationsList("history");
        ObservableList<Reservation> oIstoric = FXCollections.observableArrayList(istoric);
        istoricTable.getItems().clear();
        istoricTable.setItems(oIstoric);
    }


    private void comboFillRoute() {
        List<ComboListKeyValue> routeDistinct = routeAdapter.routeListDistinctCombo();

        ObservableList<ComboListKeyValue> oRouteDistinct = FXCollections.observableArrayList(routeDistinct);

        cbRouteSelect.setItems(oRouteDistinct);
        cbRouteSelect.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbRouteSelect.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

}
