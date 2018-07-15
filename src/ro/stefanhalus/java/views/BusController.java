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
import ro.stefanhalus.java.utilClases.ComboListKeyValue;
import ro.stefanhalus.java.utilClases.Transport;
import ro.stefanhalus.java.db.BusAdapter;
import ro.stefanhalus.java.models.Bus;
import ro.stefanhalus.java.models.Feature;

import java.net.URL;
import java.util.*;

public class BusController {

    private Main main = new Main();
    private BusAdapter adaptBus = new BusAdapter();
    private Bus busData = new Bus();

    @FXML
    void initialize() {
        // Bus tab
        busColumnsBinding();
        busRowsListFill();
        // BusFeatures tab
        busFeaturesComboListFill();
        // Features tab
        featureColumnsBinding();
        featureRowsListFill();
    }

    @FXML
    private TableView<Bus> tblBus;

    @FXML
    private TableColumn<?, ?> colMark;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colMatriculation;

    @FXML
    private TableColumn<?, ?> colPlaces;

    @FXML
    private TableColumn<?, ?> colComfort;

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<Feature> tblFeatures;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colIcon;

    @FXML
    private Button btnAddFeature;

    @FXML
    private ComboBox<ComboListKeyValue> cbBusFeatureCombo;

    @FXML
    private Button btnBusFeatureSelect;

    @FXML
    private TableView<Feature> tblBusFeatureAvailable;

    @FXML
    private TableColumn<?, ?> colBusFeatureAvailable;

    @FXML
    private Button busFeatureAdd;

    @FXML
    private Button busFeatureDell;

    @FXML
    private TableView<Feature> tblBusFeatureSelected;

    @FXML
    private TableColumn<?, ?> busFeatureSelected;

    /*
    Bus
    */
    @FXML
    void busAdd(ActionEvent event) {
        Transport.id = 0;
        Transport.obj = null;
        main.popup("BusDialog", "Adaugă autocar");
    }

    void busColumnsBinding() {
        colMark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colMatriculation.setCellValueFactory(new PropertyValueFactory<>("matriculation"));
        colPlaces.setCellValueFactory(new PropertyValueFactory<>("places"));
        colComfort.setCellValueFactory(new PropertyValueFactory<>("comfort"));
    }

    void busRowsListFill() {
        tblBus.getItems().clear();
        List<Bus> listBus = adaptBus.busList();
        ObservableList<Bus> oList = FXCollections.observableList(listBus);
        tblBus.setItems(oList);
    }

    @FXML
    void busShowItemDetailsClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Bus bus = tblBus.getSelectionModel().getSelectedItem();
            Transport.id = bus.getId();
            Transport.obj = (Object) bus;
            main.popup("BusDialog", "Modifică autocar");
        }
    }

    @FXML
    void busShowItemDetailsEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Bus bus = tblBus.getSelectionModel().getSelectedItem();
            Transport.id = bus.getId();
            Transport.obj = (Object) bus;
            main.popup("BusDialog", "Modifică autocar");
        }
    }

    /*
    BusFeature
    */
    @FXML
    void busFeatureAdd(ActionEvent event) {

    }

    @FXML
    void busFeatureDel(ActionEvent event) {

    }

    @FXML
    void busFeatureSelect(ActionEvent event) {
        if (!cbBusFeatureCombo.getSelectionModel().isEmpty()) {
            Collection<Feature> listOne = adaptBus.featureList();
            Collection<Feature> listTwo = adaptBus.featureSelectedList(8);

            List<Feature> sourceList = new ArrayList<Feature>(listOne);
            List<Feature> destinationList = new ArrayList<Feature>(listTwo);

            sourceList.removeAll(listTwo);

            tblBusFeatureAvailable.getItems().clear();
            tblBusFeatureSelected.getItems().clear();

            ObservableList<Feature> available = FXCollections.observableList(sourceList);
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tblBusFeatureAvailable.setItems(available);

            ObservableList<Feature> selected = FXCollections.observableList(destinationList);
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tblBusFeatureSelected.setItems(selected);
        }
    }

    void busFeaturesComboListFill() {
        List<ComboListKeyValue> listBusSimple = adaptBus.busListSimple();
        ObservableList<ComboListKeyValue> oListBusSimple = FXCollections.observableArrayList(listBusSimple);
        cbBusFeatureCombo.getItems().addAll(oListBusSimple);
        cbBusFeatureCombo.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbBusFeatureCombo.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    /*
    Feature
    */
    @FXML
    void featureAdd(ActionEvent event) {
        Transport.id = 0;
        Transport.obj = null;
        main.popup("FeatureDialog", "Adaugă facilitate");
    }

    @FXML
    void featureDetailsClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Feature feature = tblFeatures.getSelectionModel().getSelectedItem();
            Transport.id = feature.getId();
            Transport.obj = (Object) feature;
            main.popup("FeatureDialog", "Modifică facilitate");
        }
    }

    @FXML
    void featureDetailsEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Feature feature = tblFeatures.getSelectionModel().getSelectedItem();
            Transport.id = feature.getId();
            Transport.obj = (Object) feature;
            main.popup("FeatureDialog", "Modifică facilitate");
        }
    }

    void featureColumnsBinding() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colIcon.setCellValueFactory(new PropertyValueFactory<>("icon"));
    }

    void featureRowsListFill() {
        List<Feature> listFeature = adaptBus.featureList();
        ObservableList<Feature> oListFeature = FXCollections.observableList(listFeature);
        tblFeatures.setItems(oListFeature);
    }

}
