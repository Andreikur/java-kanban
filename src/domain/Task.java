package domain;

import manager.Status;

public class Task {

    private String taskName;                //название задачи
    private String taskDescription;         //Описание задачи
    private int idTask;                     //номер задачи
    private Status status = Status.NEW;                    // статус задачи

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

    public Status getStatus() {
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

    public void setStatus(Status status) {
        this.status = status;
    }
}
