package domain;
public class Subtask extends Task {

    int idEpic;

    public Subtask(String taskName, String taskDescription, int idTask, int idEpic) {
        super(taskName, taskDescription, idTask);
        this.idEpic = idEpic;
    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }
}
