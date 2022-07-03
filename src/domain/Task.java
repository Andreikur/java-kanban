package domain;
public class Task {

    protected String taskName;                //название задачи
    protected String taskDescription;         //Описание задачи
    protected int idTask;                     //номер задачи
    protected String status = "NEW";                    // статус задачи

    public Task(String taskName, String taskDescription, int idTask) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.idTask = idTask;
    }

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public Task() {
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getIdTask() {
        return idTask;
    }

    public String getStatus() {
        return status;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
