package ro.stefanhalus.java.db;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;

class DBConnect {

    protected static Connection con = null;

    static Connection getConnection() {
        String db = "busplaces";
        String user = "busPlaces";
        String pass = "busPlaces";

        if (con != null) return con;
        // get db, user, pass from settings file
        return getConnection(db, user, pass);
    }

    static Connection getConnection(String db_name, String user_name, String password) {
        String host = "localhost";
        String characterEncoding = "utf8";
        String serverTimezone = "Europe/Bucharest";
        Boolean useSSL = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + db_name + "?characterEncoding=" + characterEncoding + "&useSSL=" + useSSL.toString() + "&serverTimezone=" + serverTimezone, user_name, password);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Probleme la conectarea la baza de date.");
            alert.setHeaderText("Nu se poate conecta la baza de date");
            alert.setContentText("Eroare: " + e.getMessage() );
            e.printStackTrace();
        }
        return con;
    }
}
