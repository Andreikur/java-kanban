import domain.CheckingLogic;
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
        if (!allTasks.isEmpty()) {
            for (Integer id : allTasks.keySet()) {
                Task thisTasks = allTasks.get(id);
                System.out.println("ID: " + id + " - " + thisTasks.getTaskName() + " статус: " + thisTasks.getStatus());
            }
        }
        else System.out.println("Список задач данного типа пуст");
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
    void updateTask (Integer id, String status) {
        if (allTasks.containsKey(id)) {
            Task task = allTasks.get(id);
            task.setStatus(status);
            allTasks.put(id, task);
        }
        else {
            System.out.println("Задачи с таким ID нет");
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
        if (!allEpic.isEmpty()) {
            for (Integer id : allEpic.keySet()) {
                Epic thisEpic = allEpic.get(id);
                System.out.println("ID: " + id + " - " + thisEpic.getTaskName() + " статус: " + thisEpic.getStatus());
            }
        }
        else System.out.println("Список задач данного типа пуст");
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
        allSubtask.clear();
        allEpic.clear();
    }

    //удалить Epic по модификатору
    void dellEpic(Integer id){
        Epic thisEpic;
        thisEpic = allEpic.get(id);
        ArrayList <Integer> listIdEpic;
        listIdEpic = thisEpic.getIdSubtask();
        for (Integer epic : listIdEpic) {                 //удаление всех субтасков входящих в эпик
            allSubtask.remove(epic);
        }
        allEpic.remove(id);
        System.out.println("Epic и все связанные с ним Subtask удалены");
    }

    //Обновить статус Epic
    void updateEpic (Integer id) {
        if (allEpic.containsKey(id)) {
            Epic epic = allEpic.get(id);
            boolean isNEW;
            boolean isDONE;
            ArrayList<Integer> idSubtask = epic.getIdSubtask();
            CheckingLogic checkingLogic = new CheckingLogic();

            isNEW = checkingLogic.checking(allSubtask, idSubtask, "NEW");
            isDONE = checkingLogic.checking(allSubtask, idSubtask, "DONE");
            if (epic.getIdSubtask().isEmpty() || isNEW) {
                epic.setStatus("NEW");      //если нет субтасков  или все субтаск NEW , статус NEW
            }
            else if(isDONE){
                epic.setStatus("DONE");
            }
            else epic.setStatus("IN_PROGRESS");
        }
        else {
            System.out.println("Задачи с таким ID нет");
        }
    }


    //Создать Subtask
    void addSubtask(String taskName, String taskDescription, int idEpic){
        if (allEpic.containsKey(idEpic)) {
            id++;
            Subtask subtask = new Subtask(taskName, taskDescription, id, idEpic);
            allSubtask.put(id, subtask);
            Epic thisEpic;
            thisEpic = allEpic.get(idEpic);

            ArrayList <Integer> listIdEpic;
            listIdEpic = thisEpic.getIdSubtask();
            //listIdEpic = thisEpic.getIdSubtask();                         // получить список  id Subtask в Epic
            listIdEpic.add(id);
            thisEpic.setIdSubtask(listIdEpic);
            //thisEpic.setIdSubtask(listIdEpic);                            //добавить в список Epic номер Subtask
            allEpic.remove(idEpic);                                         //удалить епик с ключем idEpic
            allEpic.put(idEpic, thisEpic);                                  //добавить епик с ключем idEpic

        }
        else {
            System.out.println("Задачи Epic с таким ID нет");
        }
    }

    //получить список всех задач Subtask
    void getAllSubtask(){
        if (!allSubtask.isEmpty()) {
            for (Integer id : allSubtask.keySet()) {
                Subtask thisSubtask = allSubtask.get(id);
                System.out.println("ID: " + id + " - " + thisSubtask.getTaskName() + " статус: " + thisSubtask.getStatus());
            }
        }
        else System.out.println("Список задач данного типа пуст");
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

    //Обновить статус Subtask
    void updateSubtask (Integer id, String status) {
        if (allSubtask.containsKey(id)) {
            Subtask subtask = allSubtask.get(id);
            subtask.setStatus(status);
            allSubtask.put(id, subtask);
        }
        else {
            System.out.println("Задачи с таким ID нет");
        }
    }

}
