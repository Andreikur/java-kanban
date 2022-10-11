package manager;

import domain.CheckingLogic;
import domain.Epic;
import domain.Subtask;
import domain.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    protected int id;
    protected final Map<Integer, Task> allTasks = new HashMap<>();
    protected final Map<Integer, Epic> allEpics = new HashMap<>();
    protected final Map<Integer, Subtask> allSubtasks = new HashMap<>();
    protected final   HistoryManager historyManager = Managers.getDefaultHistory();

    protected final TreeSet treeSet = new TreeSet();
    private TaskDataComparator comparator = new TaskDataComparator();

    //Создать Task
    @Override
    public void addTask(Task task){
        id++;
        task.setIdTask(id);
        if (treeSet.checkingIntersections(task)) {  // если есть пересечение по времени задача не добавляется
            allTasks.put(id, task);
            treeSet.sortingByDate(task);        //добавление в список отсортированный по времени
        }
    }

    //получить список всех задач Task
    @Override
    public List<Task> getAllTask(){
        return List.copyOf(allTasks.values());
    }

    //получить Task по идентификатору
    @Override
    public Task getTask(Integer id) {
        Task task = allTasks.get(id);
        if(task != null) {
            historyManager.add(task);
        }
        return task;
    }

    //Удаление всех Task
    @Override
    public void deleteAllTasks() {
        for (Integer id : allTasks.keySet()){
            historyManager.remove(id);                  //удаление таска из истории
            treeSet.remove(id);                         //удаление таска из сортированного списка
        }
        allTasks.clear();
    }

    //удалить Task по модификатору
    @Override
    public void deleteTask(Integer id){
        if (allTasks.containsKey(id)){
            allTasks.remove(id);
            historyManager.remove(id);                  //удаление таска из истории
            treeSet.remove(id);                         //удаление таска из сортированного списка

        }
        else {
            System.out.println("Task с таким ID не существует");
        }
    }

    //Обновить статус Task
    @Override
    public void updateTask(Task task) {
        allTasks.put(task.getIdTask(), task);
    }

    //Создать Epic
    @Override
    public void addEpic(Epic epic){
        id++;
        epic.setIdTask(id);
        allEpics.put(id, epic);


    }

    //получить список всех задач Epic
    @Override
    public List<Epic> getAllEpics(){
        return List.copyOf(allEpics.values());
    }

    //получить Epic по идентификатору
    @Override
    public Epic getEpic(Integer id) {
        Epic epic = allEpics.get(id);
        if(epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    //получит лист Subtask входящих в эпик
    @Override
    public  List <Subtask> getListSubtaskInEpic(Integer idEpic){
        List <Subtask> listSubtaskInEpic = new ArrayList<>();
        for (Integer idSubtask : allEpics.get(idEpic).getIdSubtask()){
            listSubtaskInEpic.add(allSubtasks.get(idSubtask));
        }
        return listSubtaskInEpic;
    }

    //Удаление всех Epic
    @Override
    public void deleteAllEpic() {
        for (Integer id : allEpics.keySet()){
            historyManager.remove(id);                  //удаление epic из истории
        }
        deleteAllSubtask();
        allEpics.clear();
    }

    //удалить Epic по модификатору
    @Override
    public void dellEpic(Integer id){
        if (allEpics.containsKey(id)){
            Epic thisEpic;
            thisEpic = allEpics.get(id);
            List<Integer> listIdEpic;
            listIdEpic = thisEpic.getIdSubtask();
            for (Integer subtaskId : listIdEpic) {                 //удаление всех Subtask входящих в эпик
                allSubtasks.remove(subtaskId);
                historyManager.remove(subtaskId);                     //удаление из истории всех Subtask
            }
            allEpics.remove(id);
            historyManager.remove(id);                          //удаление эпик из истории
            System.out.println("Epic и все связанные с ним Subtask удалены");
        } else {
            System.out.println("Epic с таким ID не существует");
            }
    }

    //Обновить статус Epic

    private void updateStatusEpic(Epic epic) {
            boolean isNEW;
            boolean isDONE;
            List<Integer> idSubtask = epic.getIdSubtask();
            CheckingLogic checkingLogic = new CheckingLogic();
            isNEW = checkingLogic.checking(allSubtasks, idSubtask, Status.NEW);
            isDONE = checkingLogic.checking(allSubtasks, idSubtask, Status.DONE);
            if (epic.getIdSubtask().isEmpty() || isNEW) {
                epic.setStatus(Status.NEW);                  //если нет Subtask или все Subtask NEW, статус NEW
            } else if(isDONE){
                epic.setStatus(Status.DONE);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
        }
    }

    //Обновить Epic
    @Override
    public void updateEpic(Epic epic) {
        updateStatusEpic(epic);
        allEpics.put(epic.getIdTask(), epic);
    }

    //Создать Subtask
    @Override
    public void addSubtask(Subtask subtask){
        if (allEpics.containsKey(subtask.getIdEpic())) {
            id++;
            subtask.setIdTask(id);
            if (treeSet.checkingIntersections(subtask)) {           // если есть пересечение по времени задача не добавляется
                allSubtasks.put(id, subtask);
                treeSet.sortingByDate(subtask);                            //добавление в список отсортированный по времени
            }
            Epic thisEpic = allEpics.get(subtask.getIdEpic());
            List <Integer> listIdEpic = thisEpic.getIdSubtask();
            listIdEpic.add(id);
            updateStatusEpic(thisEpic);                            // обновление статуса Epic, для данной Subtask
            addEpicDataTime(thisEpic);                              //обновление времени в Epic
        } else {
            System.out.println("Задачи Epic с таким ID нет");
        }
    }

    //получить список всех задач Subtask
    @Override
    public List<Subtask> getAllSubtasks(){
        return List.copyOf(allSubtasks.values());
    }

    //получить Subtask по идентификатору
    @Override
    public Subtask getSubtask(Integer id) {
        Subtask subtask = allSubtasks.get(id);
        if(subtask != null) {
            historyManager.add(subtask);
        }
        return subtask;
    }

    //Удаление всех Subtask
    @Override
    public void deleteAllSubtask() {
        for (Integer id : allSubtasks.keySet()){
            historyManager.remove(id);                  //удаление Subtask из истории
            treeSet.remove(id);                         //удаление таска из сортированного списка
        }
        allSubtasks.clear();
    }

    //удалить Subtask по модификатору
    @Override
    public void dellSubtask(Integer id){
        if (allSubtasks.containsKey(id)){
            Integer idEpic = getSubtask(id).getIdEpic();  //  получаем ключ эпика к которому привязан Subtask
            allSubtasks.remove(id);                        // удаляем Subtask
            Epic epic = allEpics.get(idEpic);                // получаем эпик
            List<Integer> listIdSubtask = epic.getIdSubtask();  //получаем лист с ID Subtask  эпика
            listIdSubtask.remove(id);                                //удаляем из листа ID Subtask эпика, ID удаляемого Subtask
            epic.setIdSubtask(listIdSubtask);                       //обновляем лист ID Subtask эпика
            updateStatusEpic(epic);                       // обновление статуса и листа входящих в Epic Subtask, для данной Subtask
            historyManager.remove(id);              // удаление Subtask из истории
            treeSet.remove(id);                         //удаление таска из сортированного списка
        } else {
            System.out.println("Subtask с таким ID не существует");
        }
    }

    //Обновить статус Subtask
    @Override
    public void updateSubtask(Subtask subtask) {
            allSubtasks.put(subtask.getIdTask(), subtask);
            Epic epic = this.getEpic(subtask.getIdEpic());
            updateStatusEpic(epic);                                    // обновление статуса Epic, для данной Subtask
    }

    @Override
    public List<Task> getHistory(){
        return historyManager.getHistory();
    }

    @Override
    public TreeSet getTreeSet(){
        return treeSet;
    }

    public void setId(int id) {
        if (this.id < id) {
            this.id = id;
        }
    }

    //запись времени в Epic
    private void addEpicDataTime(Epic epic){
        List<Integer> listIdSubtasks = epic.getIdSubtask();
        Task previousTask = null;
        for (int id : listIdSubtasks){
            if (previousTask != null) {
                if (comparator.compare(allSubtasks.get(id), previousTask) <= 0) {
                    if(allSubtasks.get(id).getStartTime() != null) {
                        epic.setStartTime(allSubtasks.get(id).getStartTime());
                    }
                }
                if (comparator.compare(allSubtasks.get(id), previousTask) >= 0) {
                    if(allSubtasks.get(id).getEndTime()  != null) {
                        epic.setEndTime(allSubtasks.get(id).getEndTime());
                    }
                }
            }
            previousTask = allSubtasks.get(id);
        }
    }

}
