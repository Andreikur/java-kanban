package manager;

import domain.Epic;
import domain.Subtask;
import domain.Task;

import java.util.ArrayList;
import java.util.List;

public class Managers {

    public static TaskManager getDefault(){
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
