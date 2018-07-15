package ro.stefanhalus.java.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.stefanhalus.java.db.RoutesAdapter;
import ro.stefanhalus.java.models.Route;
import ro.stefanhalus.java.models.Station;
import ro.stefanhalus.java.utilClases.Transport;

public class RoutesDialogStopDetailsController {

    private Station stop;

    private RoutesAdapter routeAdapter = new RoutesAdapter();

    public void initialize(){
        this.stop = (Station) Transport.obj;
        tfCityDistrict.setText(stop.getCity_name() + ", " + stop.getDistrict_short());
        tfStation.setText(stop.getStation_name());
        tfArrival.setText(stop.getArrival());
        tfDeparture.setText(stop.getDeparture());
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 55, stop.getOrder());
        tfOrder.setValueFactory(valueFactory);
        tfDistance.setText(Integer.toString(stop.getDistance()));
    }

    @FXML
    private TextField tfCityDistrict;

    @FXML
    private TextField tfStation;

    @FXML
    private TextField tfArrival;

    @FXML
    private TextField tfDeparture;

    @FXML
    private Spinner<Integer> tfOrder;

    @FXML
    private TextField tfDistance;

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
        Station upd = new Station();
        upd.setId(stop.getId());
        upd.setOrder(tfOrder.getValue());
        upd.setArrival(tfArrival.getText());
        upd.setDeparture(tfDeparture.getText());
        upd.setDistance(Integer.parseInt(tfDistance.getText()));
        routeAdapter.routeStopSave(upd);
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
