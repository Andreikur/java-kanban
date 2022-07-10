package manager;

import domain.Epic;
import domain.Subtask;
import domain.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {


    //Создать Task
    void addTask(Task task);

    //получить список всех задач Task
    ArrayList<Task> getAllTask();

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
    ArrayList<Epic> getAllEpic();

    //получить Epic по идентификатору
    Epic getEpic(Integer id);

    //получит лист Subtask входящих в эпик
    ArrayList <Subtask> getListSubtaskInEpic(Integer idEpic);

    //Удаление всех Epic
    void deleteAllEpic();

    //удалить Epic по модификатору
    void dellEpic(Integer id);

    //Обновить статус Epic
    void updateEpic(Epic epic);

    //Создать Subtask
    void addSubtask(Subtask subtask);

    //получить список всех задач Subtask
    ArrayList<Subtask> getAllSubtask();

    //получить Subtask по идентификатору
    Subtask getSubtask(Integer id);

    //Удаление всех Subtask
    void deleteAllSubtask();

    //удалить Subtask по модификатору
    void dellSubtask(Integer id);

    //Обновить статус Subtask
    void updateSubtask(Subtask subtask);

}
