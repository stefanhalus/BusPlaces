package ro.stefanhalus.java.db;

import ro.stefanhalus.java.models.Employee;
import ro.stefanhalus.java.models.Right;
import ro.stefanhalus.java.utilClases.ComboListKeyValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter {

    //    DBConnect db = new DBConnect();
    private Connection cnx = DBConnect.getConnection();

//    EMPLOYEE

    public List<Employee> employeeList() {
        List<Employee> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("SELECT `employee`.*, `title`.`name` AS `title` FROM `%s` INNER JOIN `title` ON `employee`.`title_id` = `title`.`id`;", "employee");
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setUser(rs.getString("user"));
                emp.setPass(rs.getString("pass"));
                emp.setPhone(rs.getString("phone"));
                emp.setEmail(rs.getString("email"));
                emp.setTitle(rs.getString("title"));
                emp.setTitle_id(rs.getInt("title_id"));
                myList.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public void employeeSave(Employee emp) {
        try {
            cnx.setAutoCommit(false);
            String sql;
            if (emp.getId() > 0) {
                sql = "UPDATE `employee` SET `firstName` = ?, `lastName` = ?, `user` = ?, `pass` = '', `email` = ?, `phone` = ?, `title_id` = ? WHERE `id` = ?;";
            } else {
                sql = "INSERT INTO `employee` (`firstName`, `lastName`, `user`, `pass`, `email`, `phone`, `title_id`) VALUES (?, ?, ?, '', ?, ?, ?);";
            }
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getUser());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setInt(6, emp.getTitle_id());
            if (emp.getId() > 0) {
                ps.setInt(7, emp.getId());
            }
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void employeeDelete(Employee emp) {
        try {
            cnx.setAutoCommit(false);
            String sql;
            sql = "DELETE FROM `employee` WHERE `id` = ?;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, emp.getId());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void employeeResetPassword(Employee emp) {
        try {
            cnx.setAutoCommit(false);
            String sql = "UPDATE `employee` SET `pass` = ? WHERE `id` = ?;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, emp.getPass());
            ps.setInt(2, emp.getId());
            ps.executeUpdate();
            cnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // RIGHTS

    public List<Right> rightsList() {
        List<Right> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("SELECT `right`.* FROM `%s` ;", "right");
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Right right = new Right();
                right.setId(rs.getInt("id"));
                right.setName(rs.getString("name"));
                right.setDescription(rs.getString("description"));
                myList.add(right);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }


    public List<ComboListKeyValue> titlesList() {
        List<ComboListKeyValue> myList = new ArrayList<>();
        try {
            cnx.setAutoCommit(false);
            String sql = String.format("SELECT `title`.* FROM `%s` ;", "title");
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
//            preparedStatement.setString(1, "");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ComboListKeyValue title = new ComboListKeyValue();
                title.setID(rs.getInt("id"));
                title.setName(rs.getString("name"));
                myList.add(title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public Employee employeeLogin(String user, String pass)
    {
        Employee emp = new Employee();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT `employee`.*, `title`.`name` AS `title` FROM `employee` INNER JOIN `title` ON `employee`.`title_id` = `title`.`id` WHERE `user` = ? AND `pass` = ? ;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setUser(rs.getString("user"));
                emp.setPass(rs.getString("pass"));
                emp.setPhone(rs.getString("phone"));
                emp.setEmail(rs.getString("email"));
                emp.setTitle(rs.getString("title"));
                emp.setTitle_id(rs.getInt("title_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    public Employee employeeRememberPassword(String user)
    {
        Employee emp = new Employee();
        try {
            cnx.setAutoCommit(false);
            String sql = "SELECT * FROM `employee` WHERE `email` = ?;";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, user);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setUser(rs.getString("user"));
                emp.setPass(rs.getString("pass"));
                emp.setPhone(rs.getString("phone"));
                emp.setEmail(rs.getString("email"));
                emp.setTitle(rs.getString("title"));
                emp.setTitle_id(rs.getInt("title_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }
}
