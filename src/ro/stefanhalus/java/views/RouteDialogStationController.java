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
import javafx.stage.Stage;
import ro.stefanhalus.java.models.City;
import ro.stefanhalus.java.db.StationAdapter;
import ro.stefanhalus.java.models.District;
import ro.stefanhalus.java.models.Station;
import ro.stefanhalus.java.utilClases.Transport;

import java.util.List;

public class RouteDialogStationController {

    private StationAdapter stationAdapter = new StationAdapter();

    private Station editStation;
    private int cityId = 0;

    public void initialize() {
        this.cityId = 0;
        this.editStation = (Station) Transport.obj;
        tableColumnsBindDistrict();
        tableFillDataDistrict();
        if(editStation != null) {
            tblDistrict.setVisible(false);
            tblCity.setVisible(false);
            loadData();
        }
    }

    @FXML
    private TextField tfStation;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnClose;

    @FXML
    private TableView<District> tblDistrict;

    @FXML
    private TableColumn<?, ?> tblDSimbol;

    @FXML
    private TableColumn<?, ?> tblDName;

    @FXML
    private TableView<City> tblCity;

    @FXML
    private TableColumn<?, ?> tblCName;

    @FXML
    private Button btnDel;

    @FXML
    private TextField tfDescription;

    @FXML
    void btnActClose(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void btnActDel(ActionEvent event) {
        if (editStation.getStation_id() > 0) {
            stationAdapter.stationDelete(editStation);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vrem să ștergem");
            alert.setHeaderText("Station : " + editStation.getCity_name() + " " + editStation.getStation_name() + ", ID: " + editStation.getStation_id());
            alert.showAndWait();
            closeWindow();
        }
    }

    @FXML
    void btnActSave(ActionEvent event) {
        Station station = new Station();
        if(editStation != null){
            station.setStation_id(editStation.getStation_id());
            station.setCity_id(editStation.getCity_id());
        } else {
            City citySelected = tblCity.getSelectionModel().getSelectedItem();
            station.setCity_id(citySelected.getId());
            station.setStation_id(0);
        }
        if (station.getCity_id()> 0) {
            station.setStation_name(tfStation.getText());
            station.setStation_description(tfDescription.getText());
            stationAdapter.stationSave(station);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Salvare finalizată");
            alert.setHeaderText("Stația a fost salvată cu succes! \nCity ID: " + station.getCity_id() + " \nStation id: " + station.getStation_id());
            alert.showAndWait();
            closeWindow();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("SNu a'i selectat ora;ul");
            alert.setHeaderText("Stația va fi situată pe Lună?");
            alert.showAndWait();
        }
    }


    @FXML
    void tblDActClick(MouseEvent event) {
        tableColumnBindCity();
        tableFillDataCity();
    }

    @FXML
    void tblDActEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            tableFillDataCity();
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private void tableColumnsBindDistrict() {
        tblDSimbol.setCellValueFactory(new PropertyValueFactory<>("simbol"));
        tblDName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void tableColumnBindCity() {
        tblCName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void tableFillDataDistrict() {
        List<District> district = stationAdapter.districtList();
        ObservableList<District> oDistrict = FXCollections.observableArrayList(district);
        tblDistrict.getItems().clear();
        tblDistrict.setItems(oDistrict);
    }

    private void tableFillDataCity() {
        District district = tblDistrict.getSelectionModel().getSelectedItem();
        List<City> city = stationAdapter.cityListByDistrict(district.getId());
        ObservableList<City> oCity = FXCollections.observableArrayList(city);
        tblCity.getItems().clear();
        tblCity.setItems(oCity);
    }

    private void loadData() {
        District district = new District();
        City city = new City();
        district.setId(editStation.getDistrict_id());
        district.setName(editStation.getDistrict_name());
        district.setSimbol(editStation.getDistrict_short());
        city.setId(editStation.getStation_id());
        city.setName(editStation.getStation_name());
        tblDistrict.getSelectionModel().select(district);
        tblCity.getSelectionModel().select(city);
        tfStation.setText(editStation.getStation_name());
        tfDescription.setText(editStation.getStation_description());
    }
}
