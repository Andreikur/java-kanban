package domain;

import java.util.ArrayList;

public class Epic extends Task {

    ArrayList<Integer> idSubtask = new ArrayList<>();

    public Epic(String taskName, String taskDescription, int idTask, ArrayList<Integer> idSubtask) {
        super(taskName, taskDescription, idTask);
        this.idSubtask = idSubtask;
    }

    public Epic(String taskName, String taskDescription, int idTask) {
        super(taskName, taskDescription, idTask);
    }

    public Epic () {
    }

    public ArrayList<Integer> getIdSubtask() {
        return idSubtask;
    }

    public void setIdSubtask(ArrayList<Integer> idSubtask) {
        this.idSubtask = idSubtask;
    }
}
