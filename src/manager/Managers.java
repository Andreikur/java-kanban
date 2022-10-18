package manager;

import server.HTTPTaskManager;
import manager.historyManager.HistoryManager;
import manager.historyManager.InMemoryHistoryManager;
import manager.taskManager.FileBackedTasksManager;
import manager.taskManager.TaskManager;

import java.io.File;

public class Managers {

    private Managers(){

    }

    public static TaskManager getDefault(){
        return new FileBackedTasksManager();
    }

    public static HTTPTaskManager getDefaultHTTPTaskManager(){
        HTTPTaskManager httpTaskManager = new HTTPTaskManager("http://localhost:8078");

        //String filePath = "history/history.csv";            // путь местонахождения файла
        //FileBackedTasksManager.setFilePath(filePath);
        //TaskManager taskManager = Managers.getDefault();
        //File file = new File(filePath);
        //FileBackedTasksManager.loadFromFile(file);

        return httpTaskManager;
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
