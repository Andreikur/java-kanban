package manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryTasksManagerTest extends TasksManagerTest<InMemoryTaskManager> {

    @BeforeEach
        void setUp() {
            taskManager = new InMemoryTaskManager();
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

}
