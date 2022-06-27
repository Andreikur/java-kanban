import domain.Epic;
import domain.Subtask;
import domain.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    int id;
    HashMap<Integer, Task> allTasks = new HashMap<>();
    HashMap<Integer, Epic> allEpic = new HashMap<>();
    HashMap<Integer, Subtask> allSubtask = new HashMap<>();

    //Создать Task
    void addTask(String taskName, String taskDescription){
        id ++;
        Task task = new Task(taskName, taskDescription, id);
        allTasks.put(id, task);
    }

    //получить список всех задач Task
    void getAllTask(){
        for (Integer id : allTasks.keySet()) {
            Task thisTasks = allTasks.get(id);
            System.out.println(id + " - " + thisTasks.getTaskName());
        }
    }

    //получить Task по идентификатору
    void getTask (Integer id) {
        if (allTasks.containsKey(id)) {
            System.out.println(allTasks.get(id));
        }
        else {
            System.out.println("Задачи с таким ID нет");
        }
    }

    //Удаление всех Task
    void dellAllTask() {
        allTasks.clear();
    }

    //удалить Task по модификатору
    void dellTask(Integer id){
        allTasks.remove(id);
    }

    //Обновить статус Task
    void updateTask (Integer id, byte status) {
        if (allTasks.containsKey(id)) {

        }

    }

    //Создать Epic
    void addEpic(String taskName, String taskDescription){
        id ++;
        Epic epic = new Epic(taskName, taskDescription, id);
        allEpic.put(id, epic);
    }

    //получить список всех задач Epic
    void getAllEpic(){
        for (Integer id : allEpic.keySet()) {
            Task thisEpic = allEpic.get(id);
            System.out.println(id + " - " + thisEpic.getTaskName());
        }
    }

    //получить Epic идентификатору
    void getEpic (Integer id) {
        if (allEpic.containsKey(id)) {
            System.out.println(allEpic.get(id));
        }
        else {
            System.out.println("Задачи с таким ID нет");
        }
    }

    //Удаление всех Epic
    void dellAllEpic() {
        allEpic.clear();
    }

    //удалить Epic по модификатору
    void dellEpic(Integer id){
        allTasks.remove(id);
    }

    //Создать Subtask
    void addSubtask(String taskName, String taskDescription, int idEpic){
        if (allEpic.containsKey(idEpic)) {
            id++;
            Subtask subtask = new Subtask(taskName, taskDescription, id, idEpic);
            allSubtask.put(id, subtask);
            Epic thisEpic;
            thisEpic = allEpic.get(idEpic);
            ArrayList listIdEpic;
            listIdEpic = thisEpic.getIdSubtask();                         // получить список  id Subtask в Epic
            listIdEpic.add(id);
            thisEpic.setIdSubtask(listIdEpic);                            //добавить в список Epic номер Subtask
        }
        else {
            System.out.println("Задачи с таким ID нет");
        }
    }

    //получить список всех задач Subtask
    void getAllSubtask(){
        for (Integer id : allSubtask.keySet()) {
            Task thisSubtask = allSubtask.get(id);
            System.out.println(id + " - " + thisSubtask.getTaskName());
        }
    }

    //получить Subtask по идентификатору
    void getSubtask(Integer id) {
        if (allSubtask.containsKey(id)) {
            System.out.println(allSubtask.get(id));
        }
        else {
            System.out.println("Задачи с таким ID нет");
        }
    }

    //Удаление всех Subtask
    void dellAllSubtask() {
        allSubtask.clear();
    }

    //удалить Subtask по модификатору
    void dellSubtask(Integer id){
        allSubtask.remove(id);
    }


}
