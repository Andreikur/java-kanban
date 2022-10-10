package manager;

import domain.Task;

import java.time.LocalDateTime;
import java.util.Comparator;

class TaskDataComparator implements Comparator<Task> {


    //+ первый элемент > второго
    //0 первый = второму
    //- первый < второго
    /*@Override
    public int compare(Task o1, Task o2) {
        return o1.getStartTime().compareTo(o2.getStartTime());
    }*/

    @Override
    public int compare(Task o1, Task o2) {
        if (o1 == null || o2 == null){
            return 0;
        } else {
            if(o1.getStartTime() == null || o2.getStartTime() == null){
                return 0;
            } else {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        }
    }

    public int compareDataTime(LocalDateTime o1, LocalDateTime o2){
            return o1.compareTo(o2);
    }
}