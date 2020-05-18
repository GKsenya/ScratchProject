package ScratchPad.WorkClasses;

public class Task {

    private String task = null;
    private String taskTime = null;
    private String date = null;
    private int id = 0;


    public Task(String task, String date) {
        this.task = task;
        this.date = date;
    }

    public Task(String task, String date, String taskTime) {
        this.task = task;
        this.taskTime = taskTime;
        this.date = date;
    }
    public Task(int id, String task, String date, String taskTime) {
        this.id = id;
        this.task = task;
        this.taskTime = taskTime;
        this.date = date;
    }
    public Task(int id, String task) {
        this.id = id;
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
