package manager;

import domain.Epic;
import domain.Subtask;
import domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TreeSetTest extends TreeSet{

    TreeSet treeSet;
    protected LocalDateTime localDateTime1 = LocalDateTime.of(2022,10, 1, 1, 10);
    protected LocalDateTime localDateTime2 = LocalDateTime.of(2022,10, 1, 3, 30);
    protected LocalDateTime localDateTime3 = LocalDateTime.of(2022,10, 1, 2, 00);


    protected Task task1 = new Task("testTask1", "testEpicDescription1", 70, localDateTime1);
    protected Epic epic = new Epic("testEpic", "testEpicDescription");

    protected Subtask subtask1 = new Subtask("testSubtask1", "testSubtaskDescription1", 30, localDateTime2, 2);
    protected Subtask subtask2 = new Subtask("testSubtask1", "testSubtaskDescription1", 30, localDateTime3, 2);
    protected Task task2 = new Task("testTask2", "testEpicDescription1");


    @BeforeEach
    void setUp() {
        treeSet = new TreeSet();
        treeSet.getListSortedByDate().clear();
    }

    @Test
    void sortingByDateTest(){
        treeSet.sortingByDate(task1);

        Assertions.assertTrue(treeSet.getListSortedByDate().contains(task1), "Задача отсутствует в списке");
        Assertions.assertEquals(1, treeSet.getListSortedByDate().size(), "Неверное количество задач");
        Assertions.assertEquals(task1, treeSet.getListSortedByDate().get(0), "Задачи не совпадают");

        treeSet.sortingByDate(subtask1);
        subtask1.setIdTask(3);
        subtask2.setIdTask(4);

        boolean isIntersection = this.checkingIntersections(subtask2);

        Assertions.assertTrue(isIntersection, "Не верный ответ");
    }

}
