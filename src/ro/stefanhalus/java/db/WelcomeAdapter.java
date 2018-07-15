package ro.stefanhalus.java.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WelcomeAdapter {
    private Connection cnx = DBConnect.getConnection();

    public int busesCount() {
        int count = 0;
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("SELECT COUNT(`id`) AS `count`  FROM `%s`;", "bus");
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int driversCount() {
        int count = 0;
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("SELECT COUNT(`id`) AS `count`  FROM `%s` WHERE `title_id` = ?;", "employee");
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, 4);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int employeesCount() {
        int count = 0;
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("SELECT COUNT(`id`) AS `count`  FROM `%s` ;", "employee");
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setInt(1, 4);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Integer> welcomeStatistics() {
        List<Integer> stats = new ArrayList<>();
        try {
//            cnx.setAutoCommit(false);
            String sql = "{CALL welcome(?,?,?,?,?,?,?)}";
            CallableStatement psCall = cnx.prepareCall(sql);
            psCall.registerOutParameter(1, Types.INTEGER);
            psCall.registerOutParameter(2, Types.INTEGER);
            psCall.registerOutParameter(3, Types.INTEGER);
            psCall.registerOutParameter(4, Types.INTEGER);
            psCall.registerOutParameter(5, Types.INTEGER);
            psCall.registerOutParameter(6, Types.INTEGER);
            psCall.registerOutParameter(7, Types.INTEGER);
            psCall.execute();
            stats.add(psCall.getInt(1));
            stats.add(psCall.getInt(2));
            stats.add(psCall.getInt(3));
            stats.add(psCall.getInt(4));
            stats.add(psCall.getInt(5));
            stats.add(psCall.getInt(6));
            stats.add(psCall.getInt(7));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return stats;
    }
}
