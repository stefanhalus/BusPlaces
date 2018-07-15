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
import ro.stefanhalus.java.Main;
import ro.stefanhalus.java.db.ClientAdapter;
import ro.stefanhalus.java.models.Client;
import ro.stefanhalus.java.utilClases.Transport;

import java.util.List;

public class ClientsController {

    private ClientAdapter clientAdapter = new ClientAdapter();
    private int selectedClientId = 0;

    @FXML
    void initialize(){
        clientColumnsBind();
        clientsRefresh();
    }

    @FXML
    private TableView<Client> clientTbl;

    @FXML
    private TableColumn<?, ?> clientTblColLastName;

    @FXML
    private TableColumn<?, ?> clientTblColFirstName;

    @FXML
    private TableColumn<?, ?> clientTblColPhone;

    @FXML
    private Button clientBtnAdd;

    @FXML
    private Button clientBtnEdit;

    @FXML
    private Button clientBtnDel;

    @FXML
    private Label clientLblLastName;

    @FXML
    private TextField clientTfLastName;

    @FXML
    private Label clientLblFirstName;

    @FXML
    private TextField clientTfFirstName;

    @FXML
    private Label clientLblPhone;

    @FXML
    private TextField clientTfPhone;

    @FXML
    private Label clientLblEmail;

    @FXML
    private TextField clientTfEmail;

    @FXML
    private Label clientLblUser;

    @FXML
    private TextField clientTfUser;

    @FXML
    private Button clientBtnSave;

    @FXML
    private Button clientBtnReset;

    @FXML
    void clientBtnActAdd(ActionEvent event) {
        selectedClientId = 0;
        clientBtnAdd.setVisible(false);
        clientEditEnable();
        clientFormShow();
        clientBtnSave.setVisible(true);
    }

    @FXML
    void clientBtnActDel(ActionEvent event) {
        Client client = new Client();
        client.setId(selectedClientId);
        if(selectedClientId > 0){
            clientAdapter.clientDelete(client);
        }
        clientsRefresh();
    }

    @FXML
    void clientBtnActEdit(ActionEvent event) {
        clientEditEnable();
    }

    @FXML
    void clientBtnActReset(ActionEvent event) {
        Transport.id = selectedClientId;
        Main main = new Main();
        main.popup("DialogPasswordReset", "Resetare parolă uitată");
//        clientsRefresh();
    }

    @FXML
    void clientBtnActSave(ActionEvent event) {
        Client client = new Client();
        client.setId(selectedClientId);
        client.setFirstName(clientTfFirstName.getText());
        client.setLastName(clientTfLastName.getText());
        client.setUser(clientTfUser.getText());
        client.setPass("");
        client.setPhone(clientTfPhone.getText());
        client.setEmail(clientTfEmail.getText());
        if(selectedClientId > 0){
            clientAdapter.clientUpdate(client);
        } else {
            clientAdapter.clientSave(client);
        }
        clientsRefresh();
    }

    @FXML
    void clientTblActClick(MouseEvent event) {
        if(event.getClickCount() == 2) {
            clientEditingButtons();
            clientFormShow();
            clientFormFill();
        }
    }

    @FXML
    void clientTblActEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            clientEditingButtons();
            clientFormShow();
            clientFormFill();
        }
    }

    private void clientColumnsBind(){
        clientTblColLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        clientTblColFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clientTblColPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void clientEditingButtons(){
        clientBtnEdit.setVisible(true);
        clientBtnDel.setVisible(true);
        clientBtnReset.setVisible(true);
        clientBtnAdd.setVisible(true);
    }

    private void clientEditEnable(){
        clientTfLastName.setEditable(true);
        clientTfFirstName.setEditable(true);
        clientTfPhone.setEditable(true);
        clientTfEmail.setEditable(true);
        clientTfUser.setEditable(true);
        clientBtnSave.setVisible(true);
    }

    private void clientFormHide(){
        // LastName
        clientLblLastName.setVisible(false);
        clientTfLastName.setVisible(false);
        // FirstName
        clientLblFirstName.setVisible(false);
        clientTfFirstName.setVisible(false);
        // Phone
        clientLblPhone.setVisible(false);
        clientTfPhone.setVisible(false);
        // Email
        clientLblEmail.setVisible(false);
        clientTfEmail.setVisible(false);
        // Username
        clientLblUser.setVisible(false);
        clientTfUser.setVisible(false);
    }

    private void clientFormShow(){
        // LastName
        clientLblLastName.setVisible(true);
        clientTfLastName.setVisible(true);
        // FirstName
        clientLblFirstName.setVisible(true);
        clientTfFirstName.setVisible(true);
        // Phone
        clientLblPhone.setVisible(true);
        clientTfPhone.setVisible(true);
        // Email
        clientLblEmail.setVisible(true);
        clientTfEmail.setVisible(true);
        // Username
//        clientLblUser.setVisible(true);
//        clientTfUser.setVisible(true);
    }

    private void clientFormFill(){
        Client cl = clientTbl.getSelectionModel().getSelectedItem();
        selectedClientId = cl.getId();
        clientTfLastName.setText(cl.getLastName());
        clientTfFirstName.setText(cl.getFirstName());
        clientTfPhone.setText(cl.getPhone());
        clientTfEmail.setText(cl.getEmail());
        clientTfUser.setText(cl.getUser());
    }

    private void clientsRefresh(){
        selectedClientId = 0;
        List<Client> clientsRefresh = clientAdapter.clientList();
        ObservableList<Client> oclientsRefresh = FXCollections.observableArrayList(clientsRefresh);
        clientTbl.getItems().clear();
        clientTbl.setItems(oclientsRefresh);
        clientFormHide();
        clientBtnAdd.setVisible(true);
        clientBtnEdit.setVisible(false);
        clientBtnDel.setVisible(false);
        clientBtnReset.setVisible(false);
        clientBtnSave.setVisible(false);
    }

}
