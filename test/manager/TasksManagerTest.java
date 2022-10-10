package manager;

import domain.Epic;
import domain.Subtask;
import domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public abstract class TasksManagerTest<T extends TaskManager> {
    protected T taskManager ;

    protected Task task = new Task("testTask", "testEpicDescription");
    protected Task task2 = new Task("testTask2", "testEpicDescription2");
    protected Epic epic = new Epic("testEpic", "testEpicDescription");

    protected LocalDateTime localDateTime1 = LocalDateTime.of(2022,10, 1, 1, 10);
    protected LocalDateTime localDateTime2 = LocalDateTime.of(2022,10, 1, 3, 30);
    protected Subtask subtask1 = new Subtask("subtask1", "testSubtaskDescription1", 10 ,  localDateTime1, 1);
    protected Subtask subtask2 = new Subtask("subtask2", "testSubtaskDescription2", 15, localDateTime2, 1);

    @Test
    void addTaskTest(){
        taskManager.addTask(task);
        final Task actualTask = taskManager.getTask(1);

        Assertions.assertNotNull(actualTask, "Задача не найдена.");
        Assertions.assertEquals(task, actualTask, "Задачи не совпадают.");
    }

    @Test
    void getAllTaskTest(){
        taskManager.addTask(task);
        final List<Task> listTask = taskManager.getAllTask();

        Assertions.assertTrue(listTask.contains(task), "Задача отсутствует в списке");
        Assertions.assertEquals(1, listTask.size(), "Неверное количество задач");
        Assertions.assertEquals(task, listTask.get(0), "Задачи не совпадают");
    }

    @Test
    void getTaskTest(){
        taskManager.addTask(task);
        final Task actualTask1 = taskManager.getTask(1);
        final Task actualTask2 = taskManager.getTask(2);

        Assertions.assertEquals(task, actualTask1, "Задачи не совпадают");
        Assertions.assertNull(actualTask2, "Задача неверно возращена");
    }

    @Test
    void deleteAllTasksTest(){
        taskManager.addTask(task);
        taskManager.deleteAllTasks();
        final List<Task> actualListTask = taskManager.getAllTask();

        Assertions.assertFalse(actualListTask.contains(task), "Задача присутствует в списке");
        Assertions.assertEquals(0, actualListTask.size(), "Неверное количество задач");
    }

    @Test
    void deleteTaskTest(){
        taskManager.addTask(task);
        taskManager.deleteTask(1);
        final List<Task> actualListTask1 = taskManager.getAllTask();

        Assertions.assertFalse(actualListTask1.contains(task), "Задача присутствует в списке");
        Assertions.assertEquals(0, actualListTask1.size(), "Список не пустой");

        taskManager.addTask(task);
        taskManager.deleteTask(3);                      //Task  с таким Id не существует
        final List<Task> actualListTask2 = taskManager.getAllTask();

        Assertions.assertEquals(1, actualListTask2.size(), "Список пустой");
    }

    @Test
    void updateTaskTest(){
        taskManager.addTask(task);
        task.setStatus(Status.DONE);
        taskManager.updateTask(task);
        final Task actualTask1 = taskManager.getTask(1);

        Assertions.assertEquals(Status.DONE, actualTask1.getStatus(), "Статус не обновлен");
    }

    @Test
    void addEpicTest(){
        taskManager.addEpic(epic);
        final Epic actualEpic = taskManager.getEpic(1);

        Assertions.assertNotNull(actualEpic, "Эпик не найдена.");
        Assertions.assertEquals(epic, actualEpic, "Эпики не совпадают.");
    }

    @Test
    void getAllEpicTest(){
        taskManager.addTask(epic);
        final List<Epic> listEpic = taskManager.getAllEpics();

        Assertions.assertTrue(listEpic.contains(task), "Эпик отсутствует в списке");
        Assertions.assertEquals(1, listEpic.size(), "Неверное количество эпик");
        Assertions.assertEquals(epic, listEpic.get(0), "Эпики не совпадают");
    }

    @Test
    void getEpicTest(){
        taskManager.addEpic(epic);
        final Epic actualEpic1 = taskManager.getEpic(1);
        final Epic actualEpic2 = taskManager.getEpic(2);

        Assertions.assertEquals(epic, actualEpic1, "Эпики не совпадают");
        Assertions.assertNull(actualEpic2, "Эпик неверно возращен");
    }

    @Test
    void deleteAllEpicsTest(){
        taskManager.addTask(epic);
        taskManager.deleteAllEpic();
        final List<Epic> actualListEpic = taskManager.getAllEpics();

        Assertions.assertFalse(actualListEpic.contains(task), "Эпик присутствует в списке");
        Assertions.assertEquals(0, actualListEpic.size(), "Неверное количество Эпик");
    }

    @Test
    void  dellEpicTest(){
        taskManager.addTask(epic);
        taskManager.dellEpic(1);
        final List<Epic> actualListEpic1 = taskManager.getAllEpics();

        Assertions.assertFalse(actualListEpic1.contains(epic), "Эпик присутствует в списке");
        Assertions.assertEquals(0, actualListEpic1.size(), "Список не пустой");

        taskManager.addEpic(epic);
        taskManager.dellEpic(3);                      //Task  с таким Id не существует
        final List<Epic> actualListEpic2 = taskManager.getAllEpics();

        Assertions.assertEquals(1, actualListEpic2.size(), "Список пустой");
    }

    @Test
    void updateStatusEpicTest(){
        taskManager.addEpic(epic);

        Assertions.assertEquals(Status.NEW, epic.getStatus(), "Статусы не совпадают");

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);

        Assertions.assertEquals(Status.NEW, epic.getStatus(), "Статусы не совпадают");

        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);

        Assertions.assertEquals(Status.DONE, epic.getStatus(), "Статусы не совпадают");

        subtask2.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask2);

        Assertions.assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Статусы не совпадают");
        subtask1.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask1);

        Assertions.assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Статусы не совпадают");
    }

    @Test
    void updateEpicTest(){
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);

        Assertions.assertEquals(Status.DONE, epic.getStatus(), "Статусы не совпадают");
    }

    @Test
    void addTSubtaskTest(){
        taskManager.addTask(task);
        final Task actualTask = taskManager.getTask(1);

        Assertions.assertNotNull(actualTask, "Subtask не найдена.");
        Assertions.assertEquals(task, actualTask, "Subtasks не совпадают.");
    }

    @Test
    void getAllSubtaskTest(){
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask1);
        final List<Subtask> listSubtask = taskManager.getAllSubtasks();

        Assertions.assertTrue(listSubtask.contains(subtask1), "Subtask отсутствует в списке");
        Assertions.assertEquals(1, listSubtask.size(), "Неверное количество Subtask");
        Assertions.assertEquals(subtask1, listSubtask.get(0), "Subtasks не совпадают");
    }

    @Test
    void getTSubtaskTest(){
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask1);
        final Task actualSubtask1 = taskManager.getSubtask(2);
        final Task actualSubtask2 = taskManager.getSubtask(3);

        Assertions.assertEquals(subtask1, actualSubtask1, "Subtasks не совпадают");
        Assertions.assertNull(actualSubtask2, "Subtask неверно возращена");
    }

    @Test
    void deleteAllSubtaskTest(){
        taskManager.addSubtask(subtask1);
        taskManager.deleteAllSubtask();
        final List<Subtask> actualListSubtask = taskManager.getAllSubtasks();

        Assertions.assertFalse(actualListSubtask.contains(subtask1), "Subtask присутствует в списке");
        Assertions.assertEquals(0, actualListSubtask.size(), "Неверное количество Subtask");
    }

    @Test
    void deleteSubtaskTest(){
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask1);
        taskManager.dellSubtask(2);
        final List<Subtask> actualListSubtask1 = taskManager.getAllSubtasks();

        Assertions.assertFalse(actualListSubtask1.contains(subtask1), "Subtask присутствует в списке");
        Assertions.assertEquals(0, actualListSubtask1.size(), "Список не пустой");

        taskManager.addSubtask(subtask1);
        taskManager.dellSubtask(4);                      //Subtask с таким Id не существует
        final List<Subtask> actualListSubtask2 = taskManager.getAllSubtasks();

        Assertions.assertEquals(1, actualListSubtask2.size(), "Список пустой");
    }

    @Test
    void updateSubtaskTest(){
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask1);
        subtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask1);
        final Subtask actualSubtask1 = taskManager.getSubtask(2);

        Assertions.assertEquals(Status.DONE, actualSubtask1.getStatus(), "Статус не обновлен");
    }

}
