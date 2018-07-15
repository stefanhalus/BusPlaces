package ro.stefanhalus.java.db;

import ro.stefanhalus.java.models.Client;
import ro.stefanhalus.java.models.Reservation;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter {
    private Connection cnx = DBConnect.getConnection();

    public String reservationsType = "reservation";

    public List<Reservation> reservationsList(String reservationsType) {
        List<Reservation> ret = new ArrayList<>();
        String sql = " ";
        try {
            cnx.setAutoCommit(false);
            switch (reservationsType) {
                case "reservation":
                    sql = "SELECT * FROM `v_reservations` WHERE `status_id` = '1' AND DATE(`date`) >= DATE_SUB(NOW(),INTERVAL 1 DAY);";
                    break;
                case "history":
                    sql = "SELECT * FROM `v_reservations` WHERE `status_id` != '1' OR DATE(`date`) < CURDATE();";
                    break;
                default:
                    sql = "SELECT * FROM `v_reservations`;";
            }


            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Reservation rez = new Reservation();
                rez.setArrival_city(rs.getString("arrival_city"));
                rez.setArrival_id(rs.getInt("arrival_id"));
                rez.setArrival_name(rs.getString("arrival_name"));
                rez.setClient_id(rs.getInt("client_id"));
                rez.setDate(rs.getString("date"));
                rez.setDeparture_city(rs.getString("departure_city"));
                rez.setDeparture_id(rs.getInt("departure_id"));
                rez.setDeparture_name(rs.getString("departure_name"));
                rez.setDistance(rs.getInt("distance"));
                rez.setId(rs.getInt("id"));
                rez.setPlaces(rs.getInt("places"));
                rez.setPrice(rs.getDouble("price"));
                rez.setRoute_id(rs.getInt("route_id"));
                rez.setRoute_name(rs.getString("route_name"));
                rez.setStatus_id(rs.getInt("status_id"));
                rez.setStatus_name(rs.getString("status_name"));
                ret.add(rez);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Reservation> reservationsLisByRouteDate(int route_id, String date) {
        List<Reservation> ret = new ArrayList<>();
        String sql = " ";
        try {
            cnx.setAutoCommit(false);
            sql = "SELECT * FROM `v_reservations` WHERE `route_id` = ? AND `date` = ?;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, route_id);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reservation rez = new Reservation();
                rez.setArrival_city(rs.getString("arrival_city"));
                rez.setArrival_id(rs.getInt("arrival_id"));
                rez.setArrival_name(rs.getString("arrival_name"));
                rez.setClient_id(rs.getInt("client_id"));
                rez.setDate(rs.getString("date"));
                rez.setDeparture_city(rs.getString("departure_city"));
                rez.setDeparture_id(rs.getInt("departure_id"));
                rez.setDeparture_name(rs.getString("departure_name"));
                rez.setDistance(rs.getInt("distance"));
                rez.setId(rs.getInt("id"));
                rez.setPlaces(rs.getInt("places"));
                rez.setPrice(rs.getDouble("price"));
                rez.setRoute_id(rs.getInt("route_id"));
                rez.setRoute_name(rs.getString("route_name"));
                rez.setStatus_id(rs.getInt("status_id"));
                rez.setStatus_name(rs.getString("status_name"));
                ret.add(rez);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<ComboListKeyValue> statusList() {
        List<ComboListKeyValue> ret = new ArrayList<>();
        String sql = "";
        try {
            cnx.setAutoCommit(false);
            sql = "SELECT * FROM `status`;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ComboListKeyValue rez = new ComboListKeyValue();
                rez.setID(rs.getInt("id"));
                rez.setName(rs.getString("status"));
                ret.add(rez);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<ComboListKeyValue> stationList() {
        List<ComboListKeyValue> ret = new ArrayList<>();
        String sql = "";
        try {
            cnx.setAutoCommit(false);
            sql = "SELECT `station`.*, `city`.`name` AS `city_name` FROM `station` INNER JOIN `city` ON `city`.`id` = `station`.`city_id`;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ComboListKeyValue rez = new ComboListKeyValue();
                rez.setID(rs.getInt("id"));
                rez.setName(rs.getString("city_name") + ", " + rs.getString("name"));
                ret.add(rez);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void reswrvationStatusUpdate(int reservationId, int statusId){
        try {
            cnx.setAutoCommit(false);
            String sql = "UPDATE `reservation` SET `status_id` = ? WHERE `id` = ? ;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, statusId);
            ps.setInt(2, reservationId);
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reservationAdd(Client cl, Reservation rez) {
        try {
            cnx.setAutoCommit(false);
            if(cl.getId() == 0) {
                String sql = "INSERT INTO `client` (`firstName`, `lastName`, `phone`, `email`) VALUES (?, ?, ?, ?);";
                PreparedStatement psc = cnx.prepareStatement(sql);
                psc.setString(1, cl.getFirstName());
                psc.setString(2, cl.getLastName());
                psc.setString(3, cl.getPhone());
                psc.setString(4, cl.getEmail());
                psc.executeUpdate();
                ResultSet rs = psc.getGeneratedKeys();
                if (rs.next()) {
                    rez.setClient_id(rs.getInt(1));
                }
            }
            String sql = "INSERT INTO `reservation` (`client_id`, `route_id`, `departure_id`, `arrival_id`, `date`, `places`, `distance`, `price`, `status_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, rez.getClient_id());
            ps.setInt(2, rez.getRoute_id());
            ps.setInt(3, rez.getDeparture_id());
            ps.setInt(4, rez.getArrival_id());
            ps.setString(5, rez.getDate());
            ps.setInt(6, rez.getPlaces());
            ps.setInt(7, rez.getDistance());
            ps.setDouble(8, rez.getPrice());
            ps.setInt(9, rez.getStatus_id());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
