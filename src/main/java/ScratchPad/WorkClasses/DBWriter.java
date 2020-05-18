package ScratchPad.WorkClasses;

import ScratchPad.Interfaces.ResourceWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWriter implements ResourceWriter {

    private boolean userInDB = false;
    private boolean timeTaken = false;

    @Override
    public void saveNewUser(User user) {
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `Users` WHERE `user_login` = '" + user.getLogin()
                    + "' AND `user_name` = '" + user.getName() + "'");
            if (!rs.next()) {
                String request = "INSERT INTO `Users`(`user_login`, `user_password`, `user_name`) VALUES ('"
                        + user.getLogin()
                        + "', '" + user.getPassword()
                        + "', '" + user.getName() + "')";
                stmt.execute(request);
            } else {
                this.userInDB = true;
            }
        } catch (Exception ex) {
            System.out.println("");
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isUserInDB() {
        return userInDB;
    }

    @Override
    public void saveNewTask(int user, Task task) {
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `task_for_day` WHERE `user_id` = '" + user
                    + "' AND `task` = '" + task.getTask() + "' AND `task_time` = '" + task.getTaskTime() + "' AND `task_date` = '" + task.getDate() + "'");
            if (!rs.next()) {
                String request = "INSERT INTO `task_for_day`(`user_id`, `task`, `task_date`, `task_time`) VALUES ("
                        + user + ", '" + task.getTask()
                        + "', '" + task.getDate()
                        + "', '" + task.getTaskTime() + "')";
                stmt.execute(request);
            } else {
                this.timeTaken = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("");
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveNewMonthYearTask(int user, Task task) {
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        String tableName = null;
        if (this.MonthOrYear(task.getDate()).equalsIgnoreCase("month")) {
            tableName = "`task_for_month`";
        }
        if(this.MonthOrYear(task.getDate()).equalsIgnoreCase("year")){
            tableName = "`task_for_year`";
        }
        System.out.println(tableName);
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE `user_id` = " + user
                    + " AND `task` = '" + task.getTask() + "' AND `date` = '" + task.getDate() + "'");
            if (!rs.next()) {
                String request = "INSERT INTO " + tableName + " (`user_id`, `task`, `date`) VALUES ("
                        + user + ", '" + task.getTask()
                        + "', '" + task.getDate() + "')";
                stmt.execute(request);
            } else {
                this.timeTaken = true;
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
    }

    @Override
    public void deleteDayTask(int taskId, String tableName) {
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            String request = "DELETE from `" + tableName + "` WHERE `id` = " + taskId;
            stmt.execute(request);
            stmt.close();
        } catch (Exception ex) {
            System.out.println("");
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void editDayTask(int taskId, String task, String time, String tableName, String date) {
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            String request = new String();
            if(time == null) {
                String table;
                if(date.contains(".")){
                    table = "`task_for_month`";
                } else{
                    table = "`task_for_year`";
                }
                request = "UPDATE "+ table +" SET `task`=\""+task+"\" WHERE `id`="+taskId;
            } else{
                request = "UPDATE `task_for_day` SET `task`=\""+task+"\", `task_time`=\""+time+"\" WHERE `id`="+taskId;
            }
            stmt.execute(request);
            stmt.close();
        } catch (Exception ex) {
            System.out.println("агааааааааааааа");
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    protected static String MonthOrYear(String date) {
        if (date.contains(".")) {
            return "month";
        }
        return "year";
    }

}
