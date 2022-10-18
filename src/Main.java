import java.io.*;

import manager.Managers;
import manager.taskManager.FileBackedTasksManager;
import manager.taskManager.TaskManager;
import server.HTTPTaskManager;
import server.HttpTaskServer;
import server.KVServer;

public class Main {

    public static void main(String[] args) throws IOException {

        new KVServer().start();
        new HttpTaskServer().start();

    }
}
