package manager;

import domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class HistoryManagerTest extends InMemoryHistoryManager {

    protected HistoryManager historyManager = new InMemoryHistoryManager();

    protected Task task1 = new Task("testTask1", "testEpicDescription1");
    protected Task task2 = new Task("testTask2", "testEpicDescription2");
    protected Task task3 = new Task("testTask3", "testEpicDescription3");

    @Test
    void getHistoryTest(){
        historyManager.add(task1);
        final List<Task> history = historyManager.getHistory();

        Assertions.assertNotNull(history, "История пустая.");
        Assertions.assertEquals(1, history.size(), "История пустая.");
    }

    @Test
    void addTest(){
        historyManager.add(task1);
        final List<Task> history = historyManager.getHistory();

        Assertions.assertNotNull(history, "История пустая.");
        Assertions.assertEquals(1, history.size(), "История пустая.");
    }

    @Test
    void removeFromBeginningTest(){             // удаление ноды из начала
        task1.setIdTask(1);
        task2.setIdTask(2);
        task3.setIdTask(3);
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);
        historyManager.remove(1);
        final List<Task> history = historyManager.getHistory();
        final boolean isTask1 = historyManager.getHistory().contains(task1);  //есть ли task1 в истории?


        Assertions.assertEquals(2, history.size(), "Длина истории не соответствует");
        Assertions.assertFalse(isTask1, "task1 не удален");
    }

    @Test
    void removeFromMiddleTest(){             // удаление ноды из середины
        task1.setIdTask(1);
        task2.setIdTask(2);
        task3.setIdTask(3);
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);
        historyManager.remove(2);
        final List<Task> history = historyManager.getHistory();
        final boolean isTask2 = historyManager.getHistory().contains(task2);  //есть ли task2 в истории?

        Assertions.assertEquals(2, history.size(), "Длина истории не соответствует");
        Assertions.assertFalse(isTask2, "task2 не удален");
    }

    @Test
    void removeFromEndTest(){             // удаление ноды их конца
        task1.setIdTask(1);
        task2.setIdTask(2);
        task3.setIdTask(3);
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);
        historyManager.remove(3);
        final List<Task> history = historyManager.getHistory();
        final boolean isTask3 = historyManager.getHistory().contains(task3);  //есть ли task3 в истории?

        Assertions.assertEquals(2, history.size(), "Длина истории не соответствует");
        Assertions.assertFalse(isTask3, "task3 не удален");
    }

    @Test
    void  linkLastTest(){
        task1.setIdTask(1);
        task2.setIdTask(2);
        historyManager.add(task1);
        historyManager.add(task2);
        final List<Task> history = historyManager.getHistory();
        Task actualTask = historyManager.getHistory().get(1);

        Assertions.assertEquals(2, history.size(), "История не соответствует длине");
        Assertions.assertEquals(task2, actualTask, "Последняя задача в истории не соответствует ожидаемой");
    }

    @Test
    void getTasksTest(){
        task1.setIdTask(1);
        task2.setIdTask(2);
        historyManager.add(task1);
        historyManager.add(task2);
        final List<Task> history = historyManager.getHistory();
        getTasks();
        final List<Task> actualHistory = historyManager.getHistory();

        Assertions.assertEquals(history, actualHistory, "Актуальная история не соответствует ожидаемой");
    }
}
