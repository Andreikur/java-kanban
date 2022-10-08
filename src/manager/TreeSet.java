package manager;

import domain.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TreeSet {

    private final List<Task> sortedByDate = new ArrayList<>();
    private TaskDataComparator comparator = new TaskDataComparator();

    public List<Task> getSortedByDate() {
        return sortedByDate;
    }

    public void sortingByDate (Task task){
        if(task.getStartTime() == null || sortedByDate.size() == 0) {
            sortedByDate.add(task);                 //если нет времени начала, добавляем в конец списка
        } else {
            for(int i = 0; i < sortedByDate.size(); i++){
                if (sortedByDate.get(i).getStartTime() == null){
                    sortedByDate.add(i, task);
                    break;
                }
                if (comparator.compare(task, sortedByDate.get(i)) <= 0){        // если первый меньше или равен второму
                    sortedByDate.add(i, task);
                    break;
                }
            }
        }
    }

    //проверка пересечений по времени
    public void checkingIntersections(Task task){

    }

    public void printTreeSet(){
        for (Task task : sortedByDate){
            System.out.println(task.toString());
        }
    }



    /*
    public void removeNode(Node<Task> node){
        if(node.prev == null && node.next == null){
            head = null;
            tail = null;
        } else if (node.prev == null){
            head = node.next;
            head.prev = null;
        } else if(node.next == null){
            tail = node.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private class Node<Task> {
        private Task value;
        private Node<Task> next;
        private Node<Task> prev;
        public Node(Task value) {
            this.value = value;
        }

        public Node(Node<Task> prev, Task value, Node<Task> next){
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }*/
}
