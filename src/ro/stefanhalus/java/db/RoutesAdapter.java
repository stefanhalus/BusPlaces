package ro.stefanhalus.java.db;

import ro.stefanhalus.java.models.Client;
import ro.stefanhalus.java.models.Route;
import ro.stefanhalus.java.models.Station;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;
import ro.stefanhalus.java.utilClases.Transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoutesAdapter {
    private Connection cnx = DBConnect.getConnection();


    public Route routeById(int id) {
        Route route = new Route();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT * FROM `route` WHERE `id`=?;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                route.setId(rs.getInt("id"));
                route.setName(rs.getString("name"));
                route.setBus_id(rs.getInt("bus_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return route;
    }

    public List<Route> routeList() {
        List<Route> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT * FROM `route`;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Route route = new Route();
                route.setId(rs.getInt("id"));
                route.setName(rs.getString("name"));
                route.setBus_id(rs.getInt("bus_id"));
                myList.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public List<Route> routeListDistinct() {
        List<Route> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT `route`.*, `bus`.`mark` AS `bus_name`, `bus`.`matriculation` AS `bus_matriculation` FROM `route` INNER JOIN `bus` ON `route`.`bus_id` = `bus`.`id` GROUP BY `route`.`id`;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Route route = new Route();
                route.setId(rs.getInt("id"));
                route.setName(rs.getString("name"));
                route.setBus_name(rs.getString("bus_name") + " (" + rs.getString("bus_matriculation") + ")");
                myList.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public List<ComboListKeyValue> routeListDistinctCombo() {
        List<ComboListKeyValue> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT * FROM `route` GROUP BY `name`;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ComboListKeyValue route = new ComboListKeyValue();
                route.setID(rs.getInt("id"));
                route.setName(rs.getString("name"));
                myList.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }


    public void routeDelete(Route route) {
        try {
            cnx.setAutoCommit(false);
            String sql = "";
            if(route.getId() > 0){
                sql = "DELETE FROM `route` WHERE `id`= ?;";
            }
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, route.getId());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void routeSave(Route route) {
        try {
            cnx.setAutoCommit(false);
            String sql = "";
            if(route.getId() > 0){
                sql = "UPDATE `route` SET `name`= ?, `bus_id` = ? WHERE `id` = ?;";
            } else{
                sql = "INSERT INTO `route` (`name`, `bus_id`) VALUES (?, ?);";
            }
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, route.getName());
            ps.setInt(2, route.getBus_id());
            if(route.getId() > 0){
                ps.setInt(3, route.getId());
            }
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void routeStopSave(Station stop) {
        try {
            cnx.setAutoCommit(false);
            String sql = "UPDATE `stops` SET `order`= ?, `arrival` = ?, `departure` = ?, `distance` = ? WHERE `id` = ?;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, stop.getOrder());
            ps.setString(2, stop.getArrival());
            ps.setString(3, stop.getDeparture());
            ps.setInt(4, stop.getDistance());
            ps.setInt(5, stop.getId());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
