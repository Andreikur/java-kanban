package manager;

import domain.Task;

import java.util.ArrayList;
import java.util.List;

public class TreeSet {

    private final List<Task> listSortedByDate = new ArrayList<>();
    private TaskDataComparator comparator = new TaskDataComparator();

    public List<Task> getListSortedByDate() {
        return listSortedByDate;
    }

    public void sortingByDate (Task task){
        if(task.getStartTime() == null || listSortedByDate.size() == 0) {
            listSortedByDate.add(task);                 //если нет времени начала, добавляем в конец списка
        } else {
            int size = listSortedByDate.size();
            for (int i = 0; i < size; i++) {
                if (listSortedByDate.get(i).getStartTime() == null) {
                    listSortedByDate.add(i, task);
                    break;
                } else if (comparator.compare(task, listSortedByDate.get(i)) <= 0) {        // если первый меньше или равен второму
                    listSortedByDate.add(i, task);
                    break;
                } else if (i == size - 1 ) {
                    listSortedByDate.add(task);
                }
            }
        }
    }

    //проверка пересечений по времени
    public boolean checkingIntersections (Task task) throws IllegalArgumentException {
        boolean isIntersection = true;
        for (Task oldTask : listSortedByDate){

            try {
                if ((comparator.compareDataTime(task.getStartTime(), oldTask.getStartTime()) >= 0
                        & comparator.compareDataTime(task.getStartTime(), oldTask.getEndTime()) <= 0)
                        || (comparator.compareDataTime(task.getEndTime(), oldTask.getStartTime()) >= 0
                        & comparator.compareDataTime(task.getEndTime(), oldTask.getEndTime()) <= 0)) {
                    isIntersection = false;
                    System.out.println("Диапазон времени " + task.getTaskName() + "пересекается с " + oldTask.getTaskName() + ", задача не добавлена");
                }
            }
            catch (Throwable exception){

            }
        }
        return isIntersection;
    }

    public void printTreeSet(){
        for (Task task : listSortedByDate){
            System.out.println(task.toString());
        }
    }

    //удаление из списка при удалении задачи
    public void remove(int id){
        for (int i = 0; i < listSortedByDate.size(); i++){
            if (listSortedByDate.get(i).getIdTask() == id){
                listSortedByDate.remove(i);
                break;
            }
        }
    }

}
