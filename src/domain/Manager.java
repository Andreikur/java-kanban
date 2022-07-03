package domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private int id;
    private HashMap<Integer, Task> allTasks = new HashMap<>();
    private HashMap<Integer, Epic> allEpic = new HashMap<>();
    private HashMap<Integer, Subtask> allSubtask = new HashMap<>();

    //Создать Task
    public void addTask(Task task){
        id++;
        task.idTask = id;
        allTasks.put(id, task);
    }

    //получить список всех задач Task
    public HashMap getAllTask(){
        return allTasks;
    }

    //получить Task по идентификатору
    public Task getTask (Integer id) {
        return allTasks.get(id);
    }

    //Удаление всех Task
    public void deleteAllTasks() {
        allTasks.clear();
    }

    //удалить Task по модификатору
    public void deleteTask(Integer id){
        if (allTasks.containsKey(id)){
            System.out.println("Task с таким ID не существует");
        }
        else {
            allTasks.remove(id);
        }
    }

    //Обновить статус Task
    public void updateTask (Task task) {
            allTasks.put(task.getIdTask(), task);
    }

    //Создать Epic
    public void addEpic(Epic epic){
        id++;
        epic.idTask = id;
        allEpic.put(id, epic);
    }

    //получить список всех задач Epic
    public HashMap getAllEpic(){
        return allEpic;
    }

    //получить Epic по идентификатору
    public Epic getEpic (Integer id) {
       return allEpic.get(id);
    }

    //Удаление всех Epic
    public void dellAllEpic() {
        allSubtask.clear();
        allEpic.clear();
    }

    //удалить Epic по модификатору
    public void dellEpic(Integer id){
        if (allEpic.containsKey(id)){
            System.out.println("Epic с таким ID не существует");
        }
        else {
            Epic thisEpic;
            thisEpic = allEpic.get(id);
            ArrayList<Integer> listIdEpic;
            listIdEpic = thisEpic.getIdSubtask();
            for (Integer epic : listIdEpic) {                 //удаление всех субтасков входящих в эпик
                allSubtask.remove(epic);
            }
            allEpic.remove(id);
            System.out.println("Epic и все связанные с ним Subtask удалены");
        }
    }

    //Обновить статус Epic
    public void updateEpic (Integer id) {
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
    public void addSubtask(Subtask subtask){
        if (allEpic.containsKey(subtask.getIdEpic())) {
            id++;
            subtask.idTask = id;
            allSubtask.put(id, subtask);
            Epic thisEpic = allEpic.get(subtask.getIdEpic());
            ArrayList <Integer> listIdEpic = thisEpic.getIdSubtask();
            listIdEpic.add(id);
            thisEpic.setIdSubtask(listIdEpic);
            allEpic.remove(subtask.getIdEpic());                        //удалить епик с ключем idEpic
            allEpic.put(subtask.getIdEpic(), thisEpic);                 //добавить епик с ключем idEpic
            updateEpic(subtask.getIdEpic());                            // обновление статуса Epic, для данной Subtask
        }
        else {
            System.out.println("Задачи Epic с таким ID нет");
        }
    }

    //получить список всех задач Subtask
    public HashMap getAllSubtask(){
        return allSubtask;
    }

    //получить Subtask по идентификатору
    public Subtask getSubtask(Integer id) {
        return allSubtask.get(id);
    }

    //Удаление всех Subtask
    public void dellAllSubtask() {
        allSubtask.clear();
    }

    //удалить Subtask по модификатору
    public void dellSubtask(Integer id){
        if (allTasks.containsKey(id)){
            System.out.println("Subtask с таким ID не существует");
        }
        else {
            Integer idEpic = allSubtask.get(id).getIdEpic();    // получить idEpic, для обновления статуса
            allSubtask.remove(id);
            updateEpic(idEpic);                                  // обновление статуса Epic, для данной Subtask
        }
    }

    //Обновить статус Subtask
    public void updateSubtask (Subtask subtask) {
            allSubtask.put(subtask.getIdTask(), subtask);
            updateEpic(subtask.getIdEpic());                // обновление статуса Epic, для данной Subtask


    }
}
