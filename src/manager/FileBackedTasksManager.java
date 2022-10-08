package manager;

import domain.Epic;
import domain.Subtask;
import domain.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager{

    private static String filePath;
    private List<Integer> listHistory = new ArrayList<>();

    public FileBackedTasksManager(){
        super();
    }

    public static void setFilePath(String filePath) {
        FileBackedTasksManager.filePath = filePath;
    }

    public void save() {
        try(FileWriter file = new FileWriter(filePath)){
            file.write("id,type,name,status,description,epic\n");
            for (Task task : allTasks.values()){
                file.write(toString(task) + "\n");
            }
            for (Epic epic : allEpics.values()){
                file.write(toString(epic) + "\n");
            }
            for (Subtask subtask : allSubtasks.values()){
                file.write(toString(subtask) + "\n");
            }
            file.write("\n");
            file.write(historyToString(historyManager));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileBackedTasksManager  loadFromFile(File file) {
        FileBackedTasksManager fileBackedTasksManager =new FileBackedTasksManager();
        try (FileReader reader = new FileReader(file); BufferedReader br = new BufferedReader(reader)) {
            while (br.ready()) {
                String str = br.readLine();
                if (str.equals("id,type,name,status,description,epic")){  //Пропускаем первую строку

                } else if (str.isBlank()) {
                    str = br.readLine();                                    //читаем строку следующую за пустой
                    if (str == null){
                        break;
                    } else if (!str.isBlank()) {
                        fileBackedTasksManager.listHistory = historyFromString(str);            // получаем лист с ip запросов
                        for (Integer id : fileBackedTasksManager.listHistory) {
                            if (fileBackedTasksManager.allTasks.containsKey(id)) {
                                fileBackedTasksManager.getTask(id);
                            } else if (fileBackedTasksManager.allEpics.containsKey(id)) {
                                fileBackedTasksManager.getEpic(id);
                            } else {
                                fileBackedTasksManager.getSubtask(id);
                            }
                        }
                    }
                } else {
                    Task task = fileBackedTasksManager.fromString(str);
                    if (str.contains("SUBTASK")) {
                        Subtask subtask = new Subtask(task.getTaskName(), task.getTaskDescription(),task.getIdTask(), 0);
                        String[] dataTask = str.split(",");
                        int idEpic = Integer.parseInt(dataTask[5]);
                        subtask.setIdEpic(idEpic);
                        fileBackedTasksManager.allSubtasks.put(subtask.getIdTask(), subtask);
                        fileBackedTasksManager.setId(subtask.getIdTask());                  //изменение последнего ID в классе InMemoryTaskManager
                        Epic thisEpic = fileBackedTasksManager.allEpics.get(idEpic);        //заполнение списка Subtasks у Epic
                        List<Integer> list = thisEpic.getIdSubtask();
                        list.add(subtask.getIdTask());
                        thisEpic.setIdSubtask(list);
                        fileBackedTasksManager.allEpics.put(idEpic, thisEpic);
                        fileBackedTasksManager.treeSet.sortingByDate(subtask);
                    }else if (str.contains("EPIC")){
                        Epic epic = new Epic(task.getTaskName(), task.getTaskDescription(),task.getIdTask());
                        //Epic epic = (Epic) task;       //??? вываливается исключение ClassCastException
                        fileBackedTasksManager.setId(epic.getIdTask());                  //изменение последнего ID в классе InMemoryTaskManager
                        fileBackedTasksManager.allEpics.put(epic.getIdTask(), epic);
                    } else if (str.contains("TASK")) {
                        fileBackedTasksManager.allTasks.put(task.getIdTask(), task);
                        fileBackedTasksManager.setId(task.getIdTask());                  //изменение последнего ID в классе InMemoryTaskManager
                        fileBackedTasksManager.treeSet.sortingByDate(task);
                    }
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return fileBackedTasksManager;
    }

    static List<Integer> historyFromString(String value){       //создание истории из строки
        String[] data= value.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            list.add(Integer.valueOf(data[i]));
        }
        return list;
    }

    private Task fromString(String value){           //создание задачи из строки
        Task task = new Task();
        String[] dataTask = value.split(",");
        task.setIdTask(Integer.parseInt(dataTask[0]));
        task.setTaskName(dataTask[2]);
        task.setStatus(Status.valueOf(dataTask[3]));
        task.setTaskDescription(dataTask[4]);
        return task;
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
        super.getTask(id);
        save();
        return super.getTask(id);
    }

    @Override
    public Epic getEpic(Integer id) {
        super.getEpic(id);
        save();
        return super.getEpic(id);
    }

    @Override
    public Subtask getSubtask(Integer id) {
        save();
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
        String str = "";
        for (Task task : manager.getHistory()) {
            str +=  task.getIdTask() + ",";
        }
        if(str.length() > 0) {
            str = str.substring(0, str.length() - 1);  //убираем последнюю запятую
        }
        return str;
    }

    public void setId(int id) {
        super.setId(id);
    }
}
