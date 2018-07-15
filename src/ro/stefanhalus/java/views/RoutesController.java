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
import ro.stefanhalus.java.db.RoutesAdapter;
import ro.stefanhalus.java.db.StationAdapter;
import ro.stefanhalus.java.models.Route;
import ro.stefanhalus.java.models.Station;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;
import ro.stefanhalus.java.utilClases.Transport;

import javax.swing.tree.TreeNode;
import java.util.List;

public class RoutesController {

    private RoutesAdapter routeAdapter = new RoutesAdapter();
    private StationAdapter stationAdapter = new StationAdapter();

    @FXML
    public void initialize() {
        // routes tab
        routeColumnsBind();
        routeRefresh();

        // stops tab
        comboFillRoute();

        // stations tab
        stationColumnsBind();
        stationRefresh();
    }

    @FXML
    private TabPane tabPaneRoutes;

    /*
    Tabul RUTE
      */
    private int selectedRouteId = 0;

    @FXML
    private Tab routeTabRoute;

    @FXML
    private TableView<Route> tblRoutes;

    @FXML
    private TableColumn<?, ?> routeName;

    @FXML
    private TableColumn<?, ?> routeBus;

    @FXML
    private Button btnAddRoute;

    @FXML
    private TableView<Station> tblRouteStops;

    @FXML
    private TableColumn<?, ?> tblRouteStopsCrt;

    @FXML
    private TableColumn<?, ?> tblRouteStopsStation;

    @FXML
    private TableColumn<?, ?> tblRouteStopsDeparture;

    @FXML
    void routeAdd(ActionEvent event) {
        Main main = new Main();
        main.popup("RoutesDialog", "Rută nouă");
        routeRefresh();
    }

    @FXML
    void routeSelectRouteClick(MouseEvent event) {
        Route sr = tblRoutes.getSelectionModel().getSelectedItem();
        switch (event.getClickCount()) {
            case 1:
                routeFillStopsList();
                break;
            case 2:
                Transport.obj = (Object)sr;
                Main main = new Main();
                main.popup("RoutesDialog", "Rută nouă");
                Transport.obj = null;
                routeRefresh();
                break;
        }
    }

    @FXML
    void routeSelectRouteEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
            routeFillStopsList();
        }
    }
    private void routeFillStopsList(){
        Route sr = tblRoutes.getSelectionModel().getSelectedItem();
        int idSelected = sr.getId();
        tblRouteStopsFill(idSelected);
    }

    @FXML
    void routeStopDetails(MouseEvent event){
        if(event.getClickCount() ==2) {
            Station sr = tblRouteStops.getSelectionModel().getSelectedItem();
            Transport.obj = (Object) sr;
            Main main = new Main();
            main.popup("RoutesDialogStopDetails", "Detalii oprire");
            tblRouteStopsFill(sr.getId());
        }
    }

    private void tblRouteStopsFill(int idSelected) {

        tblRouteStopsCrt.setCellValueFactory(new PropertyValueFactory<>("order"));
        tblRouteStopsStation.setCellValueFactory(new PropertyValueFactory<>("station_name"));
        tblRouteStopsDeparture.setCellValueFactory(new PropertyValueFactory<>("departure"));

        List<Station> dbListStops = stationAdapter.stationListByRouteId(idSelected);
        ObservableList<Station> odbListStops = FXCollections.observableArrayList(dbListStops);
        tblRouteStops.getItems().clear();

        tblRouteStops.setItems(odbListStops);
    }

    private void routeColumnsBind() {
        routeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        routeBus.setCellValueFactory(new PropertyValueFactory<>("bus_name"));
    }

    private void routeRefresh() {
        selectedRouteId = 0;
        List<Route> routesRefresh = routeAdapter.routeListDistinct();
        ObservableList<Route> oRoutesRefresh = FXCollections.observableArrayList(routesRefresh);
        tblRoutes.getItems().clear();
        tblRoutes.setItems(oRoutesRefresh);
        comboFillRoute();
    }


    /*
    ++++++++++++++++++++++++++++++++++++++
    Tabul STOPS
        */
    private int selectedStationId = 0;

    @FXML
    private Tab routeTabStops;

    @FXML
    private ComboBox<ComboListKeyValue> cbSelectCombo;

    @FXML
    private Button btnSelect;

    @FXML
    private TableView<Station> tblStopsAvailable;

    @FXML
    private TableColumn<?, ?> stopAvailableStation;

    @FXML
    private TableColumn<?, ?> stopAvailableCity;

    @FXML
    private TableColumn<?, ?> stopAvailableDistrict;


    @FXML
    private Button btnAddStop;

    @FXML
    private Button btnDeleteStop;

    @FXML
    private TableView<Station> tblStopsSelected;

    @FXML
    private TableColumn<?, ?> stopSelectedStation;

    @FXML
    private TableColumn<?, ?> stopSelectedCity;

    @FXML
    private TableColumn<?, ?> stopSelectedDistrict;


    @FXML
    void stopAdd(ActionEvent event) {
        ComboListKeyValue cbRoute = cbSelectCombo.getSelectionModel().getSelectedItem();
        Station availableSelected = tblStopsAvailable.getSelectionModel().getSelectedItem();
        availableSelected.setRoute_id(cbRoute.getID());
        if (availableSelected.getStation_id() > 0) {
            stationAdapter.routeStopsAdd(availableSelected);
        }
        stopsPopulateAvailable();
        stopsPopulateSelected();
    }

    @FXML
    void stopDelete(ActionEvent event) {
        ComboListKeyValue cbRoute = cbSelectCombo.getSelectionModel().getSelectedItem();
        Station selectedSelected = tblStopsSelected.getSelectionModel().getSelectedItem();
        selectedSelected.setRoute_id(cbRoute.getID());
        if (selectedSelected.getStation_id() > 0) {
            stationAdapter.routeStopsDel(selectedSelected);
        }
        stopsPopulateAvailable();
        stopsPopulateSelected();
    }

    @FXML
    void stopSelect(ActionEvent event) {
        ComboListKeyValue clkv = cbSelectCombo.getSelectionModel().getSelectedItem();
        selectedRouteId = clkv.getID();
        stopsColumnsBindAvailable();
        stopsColumnsBindSelected();
        stopsPopulateAvailable();
        stopsPopulateSelected();
    }

    private void comboFillRoute() {
        List<ComboListKeyValue> routeDistinct = routeAdapter.routeListDistinctCombo();
        ObservableList<ComboListKeyValue> oRouteDistinct = FXCollections.observableArrayList(routeDistinct);
        cbSelectCombo.setItems(oRouteDistinct);
        cbSelectCombo.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbSelectCombo.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void stopsColumnsBindAvailable() {
        stopAvailableStation.setCellValueFactory(new PropertyValueFactory<>("station_name"));
        stopAvailableCity.setCellValueFactory(new PropertyValueFactory<>("city_name"));
        stopAvailableDistrict.setCellValueFactory(new PropertyValueFactory<>("district_short"));

    }

    private void stopsColumnsBindSelected() {
        stopSelectedStation.setCellValueFactory(new PropertyValueFactory<>("station_name"));
        stopSelectedCity.setCellValueFactory(new PropertyValueFactory<>("city_name"));
        stopSelectedDistrict.setCellValueFactory(new PropertyValueFactory<>("district_short"));
    }

    private void stopsPopulateAvailable() {

        List<Station> stationsAvailable = stationAdapter.stationListAll();
        ObservableList<Station> oStationsAvailable = FXCollections.observableArrayList(stationsAvailable);
        tblStopsAvailable.getItems().clear();
        tblStopsAvailable.setItems(oStationsAvailable);
    }

    private void stopsPopulateSelected() {
        List<Station> stationsSelected = stationAdapter.stationListByRouteId(selectedRouteId);
        ObservableList<Station> oStstationsSelected = FXCollections.observableArrayList(stationsSelected);
        tblStopsSelected.getItems().clear();
        tblStopsSelected.setItems(oStstationsSelected);

    }




    /*
    ++++++++++++++++++++++++++++++++++
    Tabul STATIONAS
*/

    @FXML
    private Tab routeTabStations;

    @FXML
    private TableView<Station> tblStations;

    @FXML
    private TableColumn<?, ?> stationsNume;

    @FXML
    private TableColumn<?, ?> stationsSosire;

    @FXML
    private TableColumn<?, ?> stationsPlecare;

    @FXML
    private TableColumn<?, ?> stationsLocalitate;

    @FXML
    private TableColumn<?, ?> stationsJudet;

    @FXML
    private Button btnAddStation;

    @FXML
    void stationsAdd(ActionEvent event) {
        Main main = new Main();
        main.popup("RouteDialogStation", "Adatugă stație nouă");
        Transport.obj = null;
        stationRefresh();
    }

    @FXML
    void stationsDetailsClick(MouseEvent event) {
        if(event.getClickCount() == 2){
            stationEditData();
            Main main = new Main();
            main.popup("RouteDialogStation", "Adatugă stație nouă");
            Transport.obj = null;
            stationRefresh();
        }
    }

    @FXML
    void stationsDetailsEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
//            Station editStation = tblStations.getSelectionModel().getSelectedItem();
//            Transport.obj = editStation;
            stationEditData();
            Main main = new Main();
            main.popup("RouteDialogStation", "Adatugă stație nouă");
            Transport.obj = null;
            stationRefresh();
        }
    }

    private void stationColumnsBind() {
        stationsNume.setCellValueFactory(new PropertyValueFactory<>("station_name"));
        stationsLocalitate.setCellValueFactory(new PropertyValueFactory<>("city_name"));
        stationsJudet.setCellValueFactory(new PropertyValueFactory<>("district_name"));
    }

    private void stationRefresh() {
        selectedStationId = 0;
        List<Station> stationsRefresh = stationAdapter.stationListAll();
        ObservableList<Station> oStationsRefresh = FXCollections.observableArrayList(stationsRefresh);
        tblStations.getItems().clear();
        tblStations.setItems(oStationsRefresh);
    }

    private void stationEditData(){
        Station myStation = tblStations.getSelectionModel().getSelectedItem();
        Station fill = new Station();
        fill.setStation_id(myStation.getStation_id());
        fill.setStation_name(myStation.getStation_name());
        fill.setStation_description(myStation.getStation_description());
        fill.setDistrict_id(myStation.getDistrict_id());
        fill.setDistrict_name(myStation.getDistrict_name());
        fill.setDistrict_short(myStation.getDistrict_short());
        fill.setCity_id(myStation.getCity_id());
        fill.setCity_name(myStation.getCity_name());
        Transport.obj = (Object) fill;
    }
}
