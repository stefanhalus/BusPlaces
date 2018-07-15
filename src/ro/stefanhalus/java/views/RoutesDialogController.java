package ro.stefanhalus.java.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ro.stefanhalus.java.db.BusAdapter;
import ro.stefanhalus.java.db.RoutesAdapter;
import ro.stefanhalus.java.models.Bus;
import ro.stefanhalus.java.models.Route;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;
import ro.stefanhalus.java.utilClases.Transport;

import java.util.List;

public class RoutesDialogController {

    private BusAdapter busAdapter = new BusAdapter();
    private RoutesAdapter routesAdapter = new RoutesAdapter();
    private Route myRoute;

    @FXML
    public void initialize() {
        pupulateBusList();
        if (Transport.obj != null) {
            myRoute = (Route)Transport.obj;
            tfRouteName.setText(myRoute.getName());

            ComboListKeyValue selected = new ComboListKeyValue();
            selected.setID(myRoute.getBus_id());
            selected.setName(myRoute.getBus_name());
            cbBusSelected.getSelectionModel().select(selected);
        } else {
            myRoute = new Route();
//            myRoute.setId(0);
            btnDelete.setVisible(false);
        }

    }

    @FXML
    private TextField tfRouteName;

    @FXML
    private ComboBox<ComboListKeyValue> cbBusSelected;


    @FXML
    private Button btnSave;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnDelete;

    @FXML
    void deleteRoute(ActionEvent event) {
        if (myRoute.getId() > 0) {
            Route route = new Route();
            route.setId(myRoute.getId());
            route.setName(tfRouteName.getText());
            routesAdapter.routeDelete(route);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ștergere rută finalizată");
            alert.setHeaderText("Ruta a fost ștearsă cu succes!");
            Transport.id = 0;
            closeWindow();
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void saveRoute(ActionEvent event) {
        ComboListKeyValue busSelected =  cbBusSelected.getSelectionModel().getSelectedItem();
        Route route = new Route();
        route.setId(myRoute.getId());
        route.setName(tfRouteName.getText());
        route.setBus_id(busSelected.getID());
        routesAdapter.routeSave(route);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Salvare finalizată");
        alert.setHeaderText("Ruta a fost salvată cu succes!");
        Transport.id = 0;
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private void pupulateBusList(){

        List<ComboListKeyValue> busList = busAdapter.busListSimple();
        ObservableList<ComboListKeyValue> oBusList = FXCollections.observableArrayList(busList);
        cbBusSelected.setItems(oBusList);
        cbBusSelected.setConverter(new StringConverter<>() {

            @Override
            public String toString(ComboListKeyValue object) {
                return object.getName();
            }

            @Override
            public ComboListKeyValue fromString(String string) {
                return cbBusSelected.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

}
