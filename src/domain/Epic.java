package domain;

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

    public Epic () {
    }

    public List<Integer> getIdSubtask() {
        return idSubtask;
    }

    public void setIdSubtask(List<Integer> idSubtask) {
        this.idSubtask = idSubtask;
    }

}
