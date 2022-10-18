package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import domain.Epic;
import domain.Subtask;
import domain.Task;
import manager.taskManager.FileBackedTasksManager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class HTTPTaskManager extends FileBackedTasksManager {

    protected String path;
    protected KVTaskClient kvTaskClient;
    private static final Gson gson = new GsonBuilder().
            registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter()).
            create();

    public HTTPTaskManager (String path){
        this.path = path;
    }

    public void getToken() {
        kvTaskClient = new KVTaskClient(path);
        kvTaskClient.register();
    }

    public void saveTasks() throws IOException {
        if (kvTaskClient == null) {
            System.out.println("Получите API_TOKEN");
            return;
        }
        kvTaskClient.put("/tasks", gson.toJson(getAllTasks().values()));
        kvTaskClient.put("/epics", gson.toJson(getAllEpicsMap().values()));
        kvTaskClient.put("/subtasks", gson.toJson(getAllSubtasksMap().values()));
        kvTaskClient.put("/history", gson.toJson(getHistoryList()));
    }

    public void loadTasks() {
        String json = kvTaskClient.load("/tasks");
        Type type = new TypeToken<ArrayList<Task>>(){}.getType();
        ArrayList<Task> tasksList = gson.fromJson(json, type);
        for (Task task : tasksList) {
            super.addTask(task);
        }
        allTasks.putAll(getAllTasks());

        json = kvTaskClient.load("/epics");
        type = new TypeToken<ArrayList<Epic>>(){}.getType();
        ArrayList<Epic> epicsList = gson.fromJson(json, type);
        for (Epic epic : epicsList) {
            super.addEpic(epic);
        }
        allTasks.putAll(getAllEpicsMap());

        json = kvTaskClient.load("/subtasks");
        type = new TypeToken<ArrayList<Subtask>>(){}.getType();
        ArrayList<Subtask> subtasksList = gson.fromJson(json, type);
        for (Subtask subtask : subtasksList) {
            super.addSubtask(subtask);
        }
        allTasks.putAll(getAllSubtasksMap());

        json = kvTaskClient.load("/history");
        String historyLine = json.substring(1, json.length() - 1);
        if (!historyLine.equals("\"\"")) {
            String[] historyLineContents = historyLine.split(",");
            for (String s : historyLineContents) {
                historyManager.getHistory();
            }
        }
        save();
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
    }

    @Override
    public void deleteTask(Integer id) {
        super.deleteTask(id);
    }

    @Override
    public void deleteAllEpic() {
        super.deleteAllEpic();
    }

    @Override
    public void dellEpic(Integer id) {
        super.dellEpic(id);
    }

    @Override
    public void deleteAllSubtask() {
        super.deleteAllSubtask();
    }

    @Override
    public void dellSubtask(Integer id) {
        super.dellSubtask(id);
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
    }

    @Override
    public Task getTask(Integer id) {
        super.getTask(id);
        return super.getTask(id);
    }

    @Override
    public Epic getEpic(Integer id) {
        super.getEpic(id);
        return super.getEpic(id);
    }

    @Override
    public Subtask getSubtask(Integer id) {
        return super.getSubtask(id);
    }

    public void setId(int id) {
        super.setId(id);
    }

    public void addCombinedTaskList(){
        super.addCombinedTaskList();
    }

    public Map<Integer, Task> getCombinedTaskList(){
        return combinedTaskList;
    }

    public Map<Integer, Task> getAllTasks() {
        return allTasks;
    }

    public Map<Integer, Epic> getAllEpicsMap() {
        return allEpics;
    }

    public Map<Integer, Subtask> getAllSubtasksMap() {
        return allSubtasks;
    }

}
