package manager;

import domain.Epic;
import domain.Subtask;
import domain.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager{

    private String filePath = "history/history.csv";            // путь местонахождения файлов
    File file = new File(filePath);

    public FileBackedTasksManager(){
        super();
    }

    public FileBackedTasksManager(String filesPath){
        this.filePath = filesPath;
    }

    public void save(){
        try(FileWriter file = new FileWriter(filePath)){          //FileWriter file = new FileWriter(filePath, true)
            file.write("id,type,name,status,description,epic \n");

            for (Task task : allTasks.values()){
                file.write(toString(task) + "\n");
            }
            for (Epic epic : allEpics.values()){
                file.write(toString(epic) + "\n");
            }
            for (Subtask subtask : allSubtasks.values()){
                file.write(toString(subtask) + "\n");
            }
            file.write(historyToString(historyManager));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        save();
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
        save();
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
        save();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteTask(Integer id) {
        super.deleteTask(id);
        save();
    }

    @Override
    public void deleteAllEpic() {
        super.deleteAllEpic();
        save();
    }

    @Override
    public void dellEpic(Integer id) {
        super.dellEpic(id);
        save();
    }

    @Override
    public void deleteAllSubtask() {
        super.deleteAllSubtask();
        save();
    }

    @Override
    public void dellSubtask(Integer id) {
        super.dellSubtask(id);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public Task getTask(Integer id) {
        return super.getTask(id);
    }

    @Override
    public Epic getEpic(Integer id) {
        return super.getEpic(id);
    }

    @Override
    public Subtask getSubtask(Integer id) {
        return super.getSubtask(id);
    }

    public String toString(Task task) {
        return task.getIdTask() + ","
                + TypesOfTasks.TASK + ","
                + task.getTaskName() + ","
                + task.getStatus() + ","
                + task.getTaskDescription();
    }
    public String toString(Epic epic) {
        return epic.getIdTask() + ","
                + TypesOfTasks.EPIC + ","
                + epic.getTaskName() + ","
                + epic.getStatus() + ","
                + epic.getTaskDescription();
    }

    public String toString(Subtask subtask) {
        return subtask.getIdTask() + ","
                + TypesOfTasks.SUBTASK + ","
                + subtask.getTaskName() + ","
                + subtask.getStatus() + ","
                + subtask.getTaskDescription() + ","
                + subtask.getIdEpic();
    }

    static String historyToString(HistoryManager manager){
        String str = " ";
        for (Task task : manager.getHistory()) {
            str = str + Integer.toString(task.getIdTask()) + ",";
        }
        //str = str.substring(0, str.length() - 1);  //убираем последнюю запятую
        return str;
    }
}
