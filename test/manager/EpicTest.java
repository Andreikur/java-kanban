package manager;

import domain.Epic;
import domain.Subtask;
import manager.enums.Status;
import manager.taskManager.InMemoryTaskManager;
import manager.taskManager.TaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EpicTest {
    private final TaskManager taskManager = new InMemoryTaskManager();
     Epic epic = new Epic("testEpic", "testEpicDescription");
     Subtask subtask1 = new Subtask("testSubtask1", "testSubtaskDescription1", 1);
     Subtask subtask2 = new Subtask("testSubtask2", "testSubtaskDescription2", 1);


    /*@AfterAll
    void afterAll() {
        //final Epic epic = new Epic("testEpic", "testEpicDescription", 1);
        inMemoryTaskManager.addEpic(epic);
    }*/

    @Test
    public void updateStatusEpicEmptyTaskList (){
        taskManager.addEpic(epic);

        Assertions.assertEquals(Status.NEW, epic.getStatus(), "Статусы не совпадают");
    }

    @Test
    public void updateStatusEpicSubtaskNewStatus (){
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);

        Assertions.assertEquals(Status.NEW, epic.getStatus(), "Статусы не совпадают");
    }

    @Test
    public void updateStatusEpicSubtaskDoneStatus (){
        taskManager.addEpic(epic);
        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);

        Assertions.assertEquals(Status.DONE, epic.getStatus(), "Статусы не совпадают");
    }

    @Test
    public void updateStatusEpicSubtaskNewAndDoneStatus (){
        taskManager.addEpic(epic);
        subtask1.setStatus(Status.DONE);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);

        Assertions.assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Статусы не совпадают");
    }

    @Test
    public void updateStatusEpicSubtaskInProgressStatus (){
        taskManager.addEpic(epic);
        subtask1.setStatus(Status.IN_PROGRESS);
        subtask1.setStatus(Status.IN_PROGRESS);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);

        Assertions.assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Статусы не совпадают");
    }
}