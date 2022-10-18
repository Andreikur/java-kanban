package server;

import domain.Epic;
import domain.Subtask;
import domain.Task;
import manager.Managers;
import manager.taskManager.FileBackedTasksManager;
import manager.taskManager.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class KVServerTest {
    HTTPTaskManager httpTaskManager = Managers.getDefaultHTTPTaskManager();

    @BeforeAll
    static void constructFileForeTests() {

        var taskManager = Managers.getDefault();

        LocalDateTime localDateTime1 = LocalDateTime.of(2022,10, 2, 1, 10);
        Task task1 = new Task("testTask1", "testTaskDescription1", 1, localDateTime1);
        Epic epic = new Epic("testEpic", "testEpicDescription");

        LocalDateTime localDateTime2 = LocalDateTime.of(2022,10, 1, 1, 10);
        LocalDateTime localDateTime3 = LocalDateTime.of(2022,10, 1, 3, 30);
        Subtask subtask1 = new Subtask("subtask1", "testSubtaskDescription1", 10 ,  localDateTime2, 2);
        Subtask subtask2 = new Subtask("subtask2", "testSubtaskDescription2", 15, localDateTime3, 2);

        taskManager.addTask(task1);
        taskManager.addEpic(epic);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
    }

    @Test
    void saveAndLoadTasksKVServerTest() throws IOException {
        KVServer kvServer = new KVServer();
        kvServer.start();
        httpTaskManager = Managers.getDefaultHTTPTaskManager();
        httpTaskManager.getToken();
        httpTaskManager.saveTasks();
        HTTPTaskManager serverManager = new HTTPTaskManager("http://localhost:8078");
        serverManager.getToken();
        serverManager.loadTasks();
        kvServer.stop();
    }
}