package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
     private List<Integer> idSubtask = new ArrayList<>();

    public Epic(String taskName, String taskDescription, int idTask, List<Integer> idSubtask) {
        super(taskName, taskDescription, idTask);
        this.idSubtask = idSubtask;
    }

    public Epic(String taskName, String taskDescription, int idTask) {
        super(taskName, taskDescription, idTask);
    }

    public Epic(String taskName, String taskDescription) {
        super(taskName, taskDescription);
    }

    public Epic(String taskName, String taskDescription, int idTask, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName, taskDescription, idTask, startTime, endTime);
    }

    public Epic () {

    }

    public List<Integer> getIdSubtask() {
        return idSubtask;
    }

    public void setIdSubtask(List<Integer> idSubtask) {
        this.idSubtask = idSubtask;
    }

    @Override
    public String toString(){
        String str = "";
        for (Integer idSubtask : idSubtask) {
            str = str + ", " + idSubtask;
        }
        return "Epic{" +
                "Название='" + getTaskName() + '\'' +
                ", описание=" + getTaskDescription() +
                ", Id =" + getIdTask() +
                ", статус=" + getStatus() +
                ", Subtasks=" + str +
                ", T старта=" + ((getStartTime() != null) ? getStartTime().format(formatter) : "") +
                ", Т окончания=" + ((getEndTime() != null) ? getEndTime().format(formatter) : "") +
                '}';
    }
}
