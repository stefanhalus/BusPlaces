package ro.stefanhalus.java.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import ro.stefanhalus.java.db.ClientAdapter;
import ro.stefanhalus.java.models.Client;
import ro.stefanhalus.java.utilClases.Transport;

public class DialogPasswordResetController {

    private ClientAdapter clientAdapter = new ClientAdapter();
    private int selectedClientId = 0;

    @FXML
    private Button btnClose;

    @FXML
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    @FXML
    private Button btnSave;

    @FXML
    void closeDialog(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void resetPassword(ActionEvent event) {
        if(pass1.getText().equals(pass2.getText())){
            Client client = new Client();
            client.setId(Transport.id);
            client.setPass(pass1.getText());
            clientAdapter.clientResetPassword(client);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Parolă schimbată cu succes");
            alert.setHeaderText("Resetare parolă pentru " + client.getId() + " va fi" + client.getPass());
            alert.setContentText("Noua parolă a fost modificată în sistem și poate fi folosită din acest moment.");
            alert.showAndWait();
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Parolele nu se potrivesc");
            alert.setHeaderText("Resetare parolă");
            alert.setContentText("Parola nouă și repetiția ei nu sunt identice. \nVă rugăm să scrieți din nou cu mai multă atenție!");
            alert.showAndWait();
        }
    }

}
