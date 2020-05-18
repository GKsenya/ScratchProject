package ScratchPad.Interfaces;

import ScratchPad.WorkClasses.Task;
import ScratchPad.WorkClasses.User;

public interface ResourceWriter {
    void saveNewUser(User user);
    boolean isUserInDB();
    void saveNewTask(int user, Task task);
    void saveNewMonthYearTask(int user, Task task);
    void deleteDayTask(int taskId, String tableName);
    void editDayTask(int taskId, String task, String time, String tableName, String date);
}
