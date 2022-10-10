package domain;

import java.time.LocalDateTime;

public class Subtask extends Task {
    private int idEpic;

    public Subtask(String taskName, String taskDescription, int idTask, int idEpic) {
        super(taskName, taskDescription, idTask);
        this.idEpic = idEpic;
    }

    public Subtask(String taskName, String taskDescription, int idEpic) {
        super(taskName, taskDescription);
        this.idEpic = idEpic;
    }

    public Subtask(String taskName, String taskDescription, long duration, LocalDateTime startTime, int idEpic) {
        super(taskName, taskDescription, duration, startTime);
        this.idEpic = idEpic;

        setEndTime(startTime.plusMinutes(duration));
    }

    public Subtask() {

    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    @Override
    public String toString(){
        String  strStartTime = "";
        String  strEndTime = "";
        if (getStartTime() != null){
            strStartTime = getStartTime().format(formatter);
            strEndTime = getEndTime().format(formatter);
        }
        return "Subtask{" +
                "Название='" + getTaskName() + '\'' +
                ", описание=" + getTaskDescription() +
                ", Id =" + getIdTask() +
                ", статус=" + getStatus() +
                ", Epic=" + getIdEpic() +
                ", T старта=" + strStartTime +
                ", Т окончания=" + strEndTime +
                '}';
    }
}
