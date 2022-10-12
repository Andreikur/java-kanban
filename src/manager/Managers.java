package manager;

import manager.historyManager.HistoryManager;
import manager.historyManager.InMemoryHistoryManager;
import manager.taskManager.FileBackedTasksManager;
import manager.taskManager.TaskManager;

public class Managers {

    private Managers(){

    }

    public static TaskManager getDefault(){
        return new FileBackedTasksManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
