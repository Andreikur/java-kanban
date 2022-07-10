package manager;

import domain.Task;

import java.util.List;

public interface HistoryManager {

     List<Task> getHistory();

    //добавить элемент в историю просмотров
    void add(Task task);
}
