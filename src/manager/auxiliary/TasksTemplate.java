//создаем 2 задачи эпик, и 3 подзадачи входящие в первый эпик.

package manager.auxiliary;

import domain.Epic;
import domain.Subtask;
import manager.taskManager.TaskManager;

public class TasksTemplate {

    public static void addTasksTemplate (TaskManager taskManager){
        for (int i =1; i<=2; i++) {
            Epic epic = new Epic("e" + i, "epic" + i);
            taskManager.addEpic(epic);
        }
        for (int i = 1; i<=3; i++){
            Subtask subtask =new Subtask("s" +i, "subtask" + i, 1);
            taskManager.addSubtask(subtask);
        }
    }
}
