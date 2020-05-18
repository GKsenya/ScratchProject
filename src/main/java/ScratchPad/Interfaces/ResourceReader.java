package ScratchPad.Interfaces;

import ScratchPad.WorkClasses.Task;
import ScratchPad.WorkClasses.User;
import java.util.List;

public interface ResourceReader {
    User getUserById(int id);
    List<Task> fixedTasks();
    List<Task> unfixedTasks();
    void getTasks(int user, String date);
    List<Task> getMonthTasks(int user, String date);
}
