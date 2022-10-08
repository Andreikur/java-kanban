package domain;

import manager.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private String taskName;                //название задачи
    private String taskDescription;         //Описание задачи
    private int idTask;                     //номер задачи
    private Status status = Status.NEW;     // статус задачи
    private long duration;                  //время выполнения задачи в минутах
    private LocalDateTime startTime;                // дата, когда предполагается приступить к выполнению задачи
    private LocalDateTime endTime;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");

    public Task(String taskName, String taskDescription, long duration, LocalDateTime startTime) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.duration = duration;
        this.startTime = startTime;

        endTime = startTime.plusMinutes(duration);
    }

    public Task(String taskName, String taskDescription, long duration, LocalDateTime startTime, LocalDateTime endTime) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
    }

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

    public long getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }



    public LocalDateTime getEndTime() {
        return endTime;
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

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }



    public void setEndTimeForEpic(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString(){
        String  strStartTime = "";
        String  strEndTime = "";
        if (getStartTime() != null){
            strStartTime = getStartTime().format(formatter);
            strEndTime = getEndTime().format(formatter);
        }
        return "Task{" +
                "Название='" + getTaskName() + '\'' +
                ", описание=" + getTaskDescription() +
                ", Id =" + getIdTask() +
                ", статус=" + getStatus() +
                ", T старта=" + strStartTime +
                ", Т окончания=" + strEndTime +
                '}';
    }
}
