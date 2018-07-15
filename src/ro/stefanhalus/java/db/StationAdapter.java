package ro.stefanhalus.java.db;

import ro.stefanhalus.java.models.City;
import ro.stefanhalus.java.models.District;
import ro.stefanhalus.java.models.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationAdapter {
    private Connection cnx = DBConnect.getConnection();

    public List<Station> stationListAll() {
        List<Station> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT * FROM `v_stations_available` ;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setInt(1, route_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Station station = new Station();
                station.setStation_id(rs.getInt("station_id"));
                station.setStation_name(rs.getString("station_name"));
                station.setStation_description(rs.getString("station_details"));
                station.setCity_id(rs.getInt("city_id"));
                station.setCity_name(rs.getString("city_name"));
                station.setDistrict_id(rs.getInt("district_id"));
                station.setDistrict_name(rs.getString("district_name"));
                station.setDistrict_short(rs.getString("district_short"));
                myList.add(station);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public List<Station> stationListByRouteId(int route_id) {
        List<Station> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT * FROM `v_routestopstation` WHERE `route_id` = ? ORDER BY `order`, `city_name`, `station_name`;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, route_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Station station = new Station();
                station.setId(rs.getInt("id"));
                station.setRoute_id(rs.getInt("route_id"));
                station.setStation_id(rs.getInt("station_id"));
                station.setArrival(rs.getString("arrival"));
                station.setDeparture(rs.getString("departure"));
                station.setOrder(rs.getInt("order"));
                station.setDistance(rs.getInt("distance"));
                station.setStation_name(rs.getString("station_name"));
                station.setCity_name(rs.getString("city_name"));
                station.setDistrict_name(rs.getString("district_name"));
                station.setDistrict_short(rs.getString("district_short"));
                myList.add(station);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public void stationSave(Station station) {
        String sql = "";
        try {
            cnx.setAutoCommit(false);
            if (station.getStation_id() > 0) {
                sql = "UPDATE `station` SET `name` = ?, `details` = ?, `city_id` = ? WHERE `id` = ?;";
            } else {
                sql = "INSERT INTO `station` (`name`, `details`, `city_id`) VALUES (?, ?, ?);";
            }
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, station.getStation_name());
            ps.setString(2, station.getStation_description());
            ps.setInt(3, station.getCity_id());
            if (station.getStation_id() > 0) {
                ps.setInt(4, station.getStation_id());
            }
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stationDelete(Station station) {
        String sql = "";
        try {
            cnx.setAutoCommit(false);
            sql = "DELETE FROM `station` WHERE `id`= ? ;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, station.getStation_id());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void routeStopsAdd(Station station) {
        try {
            cnx.setAutoCommit(false);
            String sql = "INSERT INTO `stops` (`route_id`, `station_id`) VALUES (?, ?);";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, station.getRoute_id());
            ps.setInt(2, station.getStation_id());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void routeStopsDel(Station station) {
        try {
            cnx.setAutoCommit(false);
            String sql = "DELETE FROM `stops` WHERE `route_id` = ? AND `station_id`= ?;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, station.getRoute_id());
            ps.setInt(2, station.getStation_id());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<District> districtList() {
        List<District> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT * FROM `district` ORDER BY `name`;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setInt(1, district);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                District district = new District();
                district.setId(rs.getInt("id"));
                district.setName(rs.getString("name"));
                district.setSimbol(rs.getString("simbol"));
//                district.setPhoneCode(rs.getInt("phoneCode"));
//                district.setPoliceCode(rs.getInt("policeCode"));
                myList.add(district);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public List<City> cityListByDistrict(int district) {
        List<City> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT * FROM `city` WHERE `district_id`= ? ORDER BY `name`;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, district);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setDistric_id(rs.getInt("district_id"));
                myList.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

}
