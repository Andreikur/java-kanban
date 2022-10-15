package manager.taskManager;

import domain.Epic;
import domain.Subtask;
import domain.Task;
import manager.TreeSet.TreeSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface TaskManager {

    //Создать Task
    void addTask(Task task);

    //получить список всех задач Task
    List<Task> getAllTask();

    //получить Task по идентификатору
    Task getTask(Integer id);

    //Удаление всех Task
    void deleteAllTasks();

    //удалить Task по модификатору
    void deleteTask(Integer id);

    //Обновить статус Task
    void updateTask(Task task);

    //Создать Epic
    void addEpic(Epic epic);

    //получить список всех задач Epic
    List<Epic> getAllEpics();

    //получить Epic по идентификатору
    Epic getEpic(Integer id);

    //получит лист Subtask входящих в эпик
    List <Subtask> getListSubtaskInEpic(Integer idEpic);

    //Удаление всех Epic
    void deleteAllEpic();

    //удалить Epic по модификатору
    void dellEpic(Integer id);

    //Обновить epic
    void  updateEpic(Epic epic);

    //Создать Subtask
    void addSubtask(Subtask subtask);

    //получить список всех задач Subtask
    List<Subtask> getAllSubtasks();

    //получить Subtask по идентификатору
    Subtask getSubtask(Integer id);

    //Удаление всех Subtask
    void deleteAllSubtask();

    //удалить Subtask по модификатору
    void dellSubtask(Integer id);

    //Обновить статус Subtask
    void updateSubtask(Subtask subtask);

    List<Task> getHistory();

    TreeSet getTreeSet();

    void setId(int id);

    void addCombinedTaskList();

    Map<Integer, Task> getCombinedTaskList();

    Map<Integer, Task> getAllTasks();

    Map<Integer, Epic> getAllEpicsMap();

    Map<Integer, Subtask> getAllSubtasksMap();

    LinkedList<Task> getHistoryList();
}
