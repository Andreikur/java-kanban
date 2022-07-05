package manager;

import domain.CheckingLogic;
import domain.Epic;
import domain.Subtask;
import domain.Task;

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
        task.setIdTask(id);
        allTasks.put(id, task);
    }

    //получить список всех задач Task
    public ArrayList<Task> getAllTask(){
        ArrayList listTask = new ArrayList();
        for (Integer key: allTasks.keySet()){
            listTask.add(allTasks.get(key));
        }
        return listTask;
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
            allTasks.remove(id);
        }
        else {
            System.out.println("Task с таким ID не существует");
        }
    }

    //Обновить статус Task
    public void updateTask (Task task) {
            allTasks.put(task.getIdTask(), task);
    }

    //Создать Epic
    public void addEpic(Epic epic){
        id++;
        epic.setIdTask(id);
        allEpic.put(id, epic);
    }

    //получить список всех задач Epic
    public ArrayList<Epic> getAllEpic(){
        ArrayList<Epic> listEpic = new ArrayList<>();
        for (Integer key : allEpic.keySet()){
            listEpic.add(allEpic.get(key));
        }
        return listEpic;
    }

    //получить Epic по идентификатору
    public Epic getEpic (Integer id) {
        if (allEpic.containsKey(id)){
            return allEpic.get(id);
        }
        else {
            return null;
        }
    }

    //получит лист Subtask входящих в эпик
    public  ArrayList <Integer> getListIdSubtask(Integer idEpic){
        ArrayList <Integer> listIdSubtask = allEpic.get(idEpic).getIdSubtask();
        return listIdSubtask;
    }

    //Удаление всех Epic
    public void deleteAllEpic() {
        allSubtask.clear();
        allEpic.clear();
    }

    //удалить Epic по модификатору
    public void dellEpic(Integer id){
        if (allEpic.containsKey(id)){
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
        else {
            System.out.println("Epic с таким ID не существует");
            }
    }

    //Обновить статус Epic
    public void updateEpic (Epic epic) {
            boolean isNEW;
            boolean isDONE;
            ArrayList<Integer> idSubtask = epic.getIdSubtask();
            CheckingLogic checkingLogic = new CheckingLogic();

            isNEW = checkingLogic.checking(allSubtask, idSubtask, "NEW");
            isDONE = checkingLogic.checking(allSubtask, idSubtask, "DONE");
            if (epic.getIdSubtask().isEmpty() || isNEW) {
                epic.setStatus("NEW");                  //если нет субтасков  или все субтаск NEW , статус NEW
            }
            else if(isDONE){
                epic.setStatus("DONE");
            }
            else {
                epic.setStatus("IN_PROGRESS");
        }
            allEpic.put(epic.getIdTask(), epic);         //записываем эпик с измененным статусом в хешмап
    }


    //Создать Subtask
    public void addSubtask(Subtask subtask){
        if (allEpic.containsKey(subtask.getIdEpic())) {
            id++;
            subtask.setIdTask(id);
            allSubtask.put(id, subtask);
            Epic thisEpic = allEpic.get(subtask.getIdEpic());
            ArrayList <Integer> listIdEpic = thisEpic.getIdSubtask();
            listIdEpic.add(id);
            thisEpic.setIdSubtask(listIdEpic);
            updateEpic(thisEpic);                            // обновление статуса Epic, для данной Subtask
        }
        else {
            System.out.println("Задачи Epic с таким ID нет");
        }
    }

    //получить список всех задач Subtask
    public ArrayList<Subtask> getAllSubtask(){
        ArrayList<Subtask> listSubtask = new ArrayList<>();
        for (Integer key :allSubtask.keySet()){
            listSubtask.add(allSubtask.get(key));
        }
        return listSubtask;
    }

    //получить Subtask по идентификатору
    public Subtask getSubtask(Integer id) {
        return allSubtask.get(id);
    }

    //Удаление всех Subtask
    public void deleteAllSubtask() {
        allSubtask.clear();
    }

    //удалить Subtask по модификатору
    public void dellSubtask(Integer id){
        if (allSubtask.containsKey(id)){
            Integer idEpic = getSubtask(id).getIdEpic();  //  получаем ключ эпика к которому привязан субтаск
            allSubtask.remove(id);
            Epic epic = allEpic.get(idEpic);
            ArrayList<Integer> listIdSubtask = getListIdSubtask(idEpic);  //получаем лист с субтасками входящими в эпик
            listIdSubtask.remove(id);                            // удаляем из списка объект со значением id субтаска
            epic.setIdSubtask(listIdSubtask);                    // обновляем в эпике лист id субтасков
            updateEpic(epic);                       // обновление статуса Epic, для данной Subtask
        }
        else {
            System.out.println("Subtask с таким ID не существует");
        }
    }

    //Обновить статус Subtask
    public void updateSubtask (Subtask subtask) {
            allSubtask.put(subtask.getIdTask(), subtask);
            Epic epic = this.getEpic(subtask.getIdEpic());
            updateEpic(epic);                                    // обновление статуса Epic, для данной Subtask


    }
}
