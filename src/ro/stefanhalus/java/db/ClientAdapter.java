package ro.stefanhalus.java.db;

import ro.stefanhalus.java.models.Client;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientAdapter {

    private Connection cnx = DBConnect.getConnection();

    public List<Client> clientList() {
        List<Client> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT `client`.* FROM `client`;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Client cl = new Client();
                cl.setId(rs.getInt("id"));
                cl.setLastName(rs.getString("lastName"));
                cl.setFirstName(rs.getString("firstName"));
                cl.setPhone(rs.getString("phone"));
                cl.setEmail(rs.getString("email"));
                cl.setUser(rs.getString("user"));
                myList.add(cl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public List<ComboListKeyValue> clientListCombo() {
        List<ComboListKeyValue> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT `client`.* FROM `client`;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ComboListKeyValue cl = new ComboListKeyValue();
                cl.setID(rs.getInt("id"));
                cl.setName(rs.getString("lastName") + " " + rs.getString("firstName"));
                myList.add(cl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public Client clientById(int id_client) {
        Client cl = new Client();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT `client`.* FROM `client` WHERE `id` = ?;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, id_client);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                cl.setId(rs.getInt("id"));
                cl.setLastName(rs.getString("lastName"));
                cl.setFirstName(rs.getString("firstName"));
                cl.setPhone(rs.getString("phone"));
                cl.setEmail(rs.getString("email"));
                cl.setUser(rs.getString("user"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cl;
    }

    public void clientSave(Client cl) {
        try {
            cnx.setAutoCommit(false);
            String sql = "INSERT INTO `client` (`firstName`, `lastName`, `phone`, `email`) VALUES (?, ?, ?, ?);";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, cl.getFirstName());
            ps.setString(2, cl.getLastName());
//            ps.setString(3, cl.getUser());
            ps.setString(3, cl.getPhone());
            ps.setString(4, cl.getEmail());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clientUpdate(Client cl) {
        try {
            cnx.setAutoCommit(false);
            String sql = "UPDATE `client` SET `firstName` = ?, `lastName` = ?, `phone` = ?, `email` = ? WHERE `id` = ?;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, cl.getFirstName());
            ps.setString(2, cl.getLastName());
            ps.setString(3, cl.getPhone());
            ps.setString(4, cl.getEmail());
            ps.setInt(5, cl.getId());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clientResetPassword(Client cl) {
        try {
            cnx.setAutoCommit(false);
            String sql = "UPDATE `client` SET `pass` = ? WHERE `id` = ?;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, cl.getPass());
            ps.setInt(2, cl.getId());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clientDelete(Client cl) {
        try {
            cnx.setAutoCommit(false);
            String sql = "DELETE FROM `client` WHERE `id` = ?;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, cl.getId());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
