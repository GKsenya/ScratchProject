package ScratchPad.WorkClasses;

import ScratchPad.Interfaces.ResourceReader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ScratchPad.WorkClasses.DBWriter.MonthOrYear;

public class DBReader implements ResourceReader {

    private List<Task> fixedTask = new ArrayList<>();
    private List<Task> unfixedTask = new ArrayList<>();
    private List<Task> taskForMonth = new ArrayList<>();

    @Override
    public User getUserById(int id) {
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        User user;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM `Users` WHERE `id` = " + id);
            rs1.next();
            user = new User(rs1.getString("user_login"), rs1.getString("user_name"), rs1.getInt("id"));
            rs1.close();
            stmt.close();
        } catch (Exception ex) {
            user = null;
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public void getTasks(int user, String date) {
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `task_for_day` WHERE `user_id` = " + user
                    + " AND `task_date` = '" + date + "' ORDER BY `task_time`");
            while (rs.next()) {
                Task task = new Task(rs.getInt("id"), rs.getString("task"), rs.getString("task_date"), rs.getString("task_time"));
                if (task.getTaskTime().equalsIgnoreCase("null")) {
                    this.unfixedTask.add(task);
                } else {
                    this.fixedTask.add(task);
                }
            }
            rs.close();
        } catch (Exception ex) {
            System.out.println("ffffff");
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Task> getMonthTasks(int user, String date) {
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        String tableName = null;
        if (MonthOrYear(date).equalsIgnoreCase("month")) {
            tableName = "`task_for_month`";
        }
        if (MonthOrYear(date).equalsIgnoreCase("year")) {
            tableName = "`task_for_year`";
        }
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE `user_id` = " + user
                    + " AND `date` = '" + date + "'");
            while (rs.next()) {
                Task task = new Task(rs.getInt("id"), rs.getString("task"));
                this.taskForMonth.add(task);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.taskForMonth;
    }

    @Override
    public List<Task> unfixedTasks() {
        return this.unfixedTask;
    }

    @Override
    public List<Task> fixedTasks() {
        return this.fixedTask;
    }

}
