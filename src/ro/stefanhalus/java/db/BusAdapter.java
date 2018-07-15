package ro.stefanhalus.java.db;

import ro.stefanhalus.java.models.Bus;
import ro.stefanhalus.java.models.Feature;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusAdapter {

//    BUS

    private Connection cnx = DBConnect.getConnection();

    public List<Bus> busList() {
        List<Bus> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sqlEmp = String.format("SELECT `bus`.* FROM `%s`;", "bus");
            PreparedStatement preparedStatement = cnx.prepareStatement(sqlEmp);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setId(rs.getInt("id"));
                bus.setMark(rs.getString("mark"));
                bus.setModel(rs.getString("model"));
                bus.setMatriculation(rs.getString("matriculation"));
                bus.setPlaces(rs.getInt("places"));
                bus.setComfort(rs.getString("comfort"));
                myList.add(bus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public List<ComboListKeyValue> busListSimple() {
        List<ComboListKeyValue> bus = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT `bus`.* FROM `bus`;";
            PreparedStatement ps = cnx.prepareStatement(sql);
//            ps.setString(1, "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ComboListKeyValue busCmb = new ComboListKeyValue();
                busCmb.setID(rs.getInt("id"));
                busCmb.setName(rs.getString("mark") + " (" + rs.getString("matriculation") + ")");
//                busCombo.setValue(rs.getString("mark") + " " + rs.getString("model") + " (" + rs.getString("matriculation") + ")");
                bus.add(busCmb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bus;
    }

    public Bus busData(int idBus) {
        Bus date = new Bus();
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("SELECT `bus`.* FROM `%s` WHERE `id` = ?;", "bus");
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, idBus);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                date.setId(rs.getInt("id"));
                date.setMark(rs.getString("mark"));
                date.setModel(rs.getString("model"));
                date.setMatriculation(rs.getString("matriculation"));
                date.setPlaces(rs.getInt("places"));
                date.setComfort(rs.getString("comfort"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }

    public void saveBus(Bus bus) {
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("INSERT INTO `%s` (`mark`, `model`, `matriculation`, `places`, `comfort`) VALUES (?, ?, ?, ?, ?);", "bus");
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, bus.getMark());
            ps.setString(2, bus.getModel());
            ps.setString(3, bus.getMatriculation());
            ps.setInt(4, bus.getPlaces());
            ps.setString(5, bus.getComfort());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBus(Bus bus) {
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("UPDATE `%s` SET `mark` = ?, `model` = ?, `matriculation` = ?, `places` = ?, `comfort` =? WHERE `id` = ?;", "bus");
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, bus.getMark());
            ps.setString(2, bus.getModel());
            ps.setString(3, bus.getMatriculation());
            ps.setInt(4, bus.getPlaces());
            ps.setString(5, bus.getComfort());
            ps.setInt(6, bus.getId());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBus(Bus bus) {
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("DELETE FROM `%s` WHERE `id` = ?;", "bus");
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, bus.getId());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FEATURES

    public List<Feature> featureList() {
        List<Feature> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("SELECT `features`.* FROM `%s`;", "features");
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Feature feature = new Feature();
                feature.setId(rs.getInt("id"));
                feature.setName(rs.getString("name"));
                feature.setDescription(rs.getString("description"));
                feature.setIcon(rs.getString("icon"));
                myList.add(feature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public List<Feature> featureSelectedList(int id) {
        List<Feature> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT `bus_features`.`feature_id`, `features`.`name`, `features`.`description` FROM `bus_features` INNER JOIN `features` ON `bus_features`.`feature_id` = `features`.`id` WHERE `bus_features`.`bus_id` = ?;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Feature feature = new Feature();
                feature.setId(rs.getInt("id"));
                feature.setName(rs.getString("name"));
                feature.setDescription(rs.getString("description"));
                feature.setIcon(rs.getString("icon"));
                myList.add(feature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public void saveFeature(Feature feature) {
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("INSERT INTO `%s` (`name`, `description`, `icon`) VALUES (?, ?, ?);", "features");
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, feature.getName());
            ps.setString(2, feature.getDescription());
            ps.setString(3, feature.getIcon());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
