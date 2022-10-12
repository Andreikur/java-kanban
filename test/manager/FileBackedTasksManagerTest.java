package manager;

import domain.Epic;
import domain.Subtask;
import domain.Task;
import manager.taskManager.FileBackedTasksManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class FileBackedTasksManagerTest extends TasksManagerTest{

    FileBackedTasksManager taskManager1 = new FileBackedTasksManager();
    protected Epic epic = new Epic("testEpic", "testEpicDescription");

    @BeforeEach
    void setUp() {
        taskManager = new FileBackedTasksManager();
        String filePath = "test/history/historyTest.csv";            // путь местонахождения файла
        FileBackedTasksManager.setFilePath(filePath);
    }

    @Test
    void addTaskTest(){
        super.addTaskTest();
    }
    @Test
    public void getAllTaskTest(){
        super.getAllTaskTest();
    }

    @Test
    void getTaskTest(){
        super.getTaskTest();
    }

    @Test
    void deleteAllTasksTest(){
        super.deleteAllTasksTest();
    }

    @Test
    void deleteTaskTest(){
        super.deleteTaskTest();
    }

    @Test
    void updateTaskTest(){
        super.updateTaskTest();
    }

    @Test
    void addEpicTest(){
        super.addEpicTest();
    }

    void getAllEpicTest(){
        super.getAllEpicTest();
    }

    @Test
    void getEpicTest(){
        super.getEpicTest();
    }

    @Test
    void deleteAllEpicsTest(){
        super.deleteAllEpicsTest();
    }

    @Test
    void  dellEpicTest(){
        super.dellEpicTest();
    }

    @Test
    void updateStatusEpicTest(){
        super.updateStatusEpicTest();
    }

    @Test
    void updateEpicTest(){
        super.updateEpicTest();
    }

    @Test
    void addTSubtaskTest(){
        super.addTSubtaskTest();
    }

    @Test
    void getAllSubtaskTest(){
        super.getAllSubtaskTest();
    }

    @Test
    void getTSubtaskTest(){
        super.getTSubtaskTest();
    }

    @Test
    void deleteAllSubtaskTest(){
        super.deleteAllSubtaskTest();
    }

    @Test
    void deleteSubtaskTest(){
        super.deleteSubtaskTest();
    }

    @Test
    void updateSubtaskTest(){
        super.updateSubtaskTest();
    }

    @Test
    void saveEmptyTaskListTest(){
        taskManager1.deleteAllTasks();
        taskManager1.deleteAllSubtask();
        taskManager1.deleteAllEpic();
        taskManager1.save();
        List<Task> listTaskTest = taskManager1.getAllTask();
        List<Epic> listTEpicTest = taskManager1.getAllEpics();
        List<Subtask> listSubtaskTest = taskManager1.getAllSubtasks();

        Assertions.assertEquals(0, listTaskTest.size(),  "Список Task не пустой");
        Assertions.assertEquals(0, listTEpicTest.size(), "Список Epic не пустой");
        Assertions.assertEquals(0, listSubtaskTest.size(), "Список Subtask не пустой");
    }

    @Test
    void saveEpicWithoutSubtaskListTest(){
        taskManager1.deleteAllTasks();
        taskManager1.deleteAllSubtask();
        taskManager1.deleteAllEpic();
        taskManager1.addEpic(epic);
        taskManager1.save();
        Epic actualEpic = taskManager1.getEpic(1);

        Assertions.assertEquals(epic, actualEpic,  "Epic не создан");
    }

    @Test
    void saveEmptyHistoryListTest(){
        taskManager1.deleteAllTasks();
        taskManager1.deleteAllSubtask();
        taskManager1.deleteAllEpic();
        taskManager1.addEpic(epic);
        taskManager1.save();
        final List<Task> actualHistory = taskManager1.getHistory();

        Assertions.assertEquals(0, actualHistory.size(),  "История не пустая");
    }

    @Test
    void loadFromEmptyTaskListTest(){
        taskManager1.deleteAllTasks();
        taskManager1.deleteAllSubtask();
        taskManager1.deleteAllEpic();
        taskManager1.save();
        String filePath = "test/history/historyTest.csv";
        File file = new File(filePath);
        taskManager1 = FileBackedTasksManager.loadFromFile(file);
        List<Task> listTaskTest = taskManager1.getAllTask();
        List<Epic> listTEpicTest = taskManager1.getAllEpics();
        List<Subtask> listSubtaskTest = taskManager1.getAllSubtasks();

        Assertions.assertEquals(0, listTaskTest.size(),  "Список Task не пустой");
        Assertions.assertEquals(0, listTEpicTest.size(), "Список Epic не пустой");
        Assertions.assertEquals(0, listSubtaskTest.size(), "Список Subtask не пустой");
    }

    @Test
    void loadFromEpicWithoutSubtaskListTest(){
        taskManager1.deleteAllTasks();
        taskManager1.deleteAllSubtask();
        taskManager1.deleteAllEpic();
        taskManager1.addEpic(epic);
        taskManager1.save();
        String filePath = "test/history/historyTest.csv";
        File file = new File(filePath);
        taskManager1 = FileBackedTasksManager.loadFromFile(file);
        List<Epic> listTEpicTest = taskManager1.getAllEpics();

        Assertions.assertEquals(1, listTEpicTest.size(),  "Epic не создан");
    }

    @Test
    void loadFromEmptyHistoryListTest(){
        taskManager1.deleteAllTasks();
        taskManager1.deleteAllSubtask();
        taskManager1.deleteAllEpic();
        taskManager1.save();
        String filePath = "test/history/historyTest.csv";
        File file = new File(filePath);
        taskManager1 = FileBackedTasksManager.loadFromFile(file);
        List<Epic> listTEpicTest = taskManager1.getAllEpics();

        Assertions.assertEquals(0, listTEpicTest.size(),  "Epic не создан");
    }

}
