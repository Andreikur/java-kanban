package manager;

import domain.Node;
import domain.Task;

import java.util.*;

public class  InMemoryHistoryManager implements HistoryManager {

    private Node<Task> head;
    private int size;

    private  LinkedList<Task> history = new LinkedList<>();
    private HashMap<Integer, Node> historyHashMap = new HashMap<>();



    public  List<Task> getHistory(){
        getTasks(head);
        return  history;
    }


    public void add(Task task){
        if (historyHashMap.containsKey(task.getIdTask())){
            this.removeNode(getIndexNode(task.getIdTask()));           //удаление повторяющейся задачи из истории
        }

        historyHashMap.put(task.getIdTask(), this.linkLast(task));
    }

    @Override
    public void remove(int id) {
        if (historyHashMap.containsKey(id)) {
            removeNode(getIndexNode(id));           //удаление задачи из истории при удалении из списка
        }
    }

    //добавление задачи в конец списка истории
    public Node<Task> linkLast (Task  value){
        Node<Task> node = new Node<>(value);
        if (head == null) {
            head = node;
        } else {
            Node<Task> cur = head;
            while (cur.getNext() != null) {
                cur = cur.getNext();
            }
            cur.setNext(node);
        }
        size++;
        return node;
    }

    //сбор всех задач
    public void getTasks(Node<Task> head){
        history.clear();
        Node<Task> cur = head;

        if(cur == null){
            System.out.println("Список истории пустой");
        }
        else {
            history.add(cur.getValue());
            while (cur.getNext() != null) {
                cur = cur.getNext();
                history.add(cur.getValue());
            }
        }
    }

    //удаление повторяющегося элемента и при удалении элемента из списка
    public void removeNode(int index){
        if (index == 0) {
            head = head.getNext();
        } else {
            Node cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.getNext();
            }
            cur.setNext(cur.getNext().getNext());
        }
        size--;
    }

    public int getIndexNode(int idTask){          // получить индекс ноды
        int index = 0;
        Node<Task> cur = head;
        while(cur.getValue().getIdTask() != idTask){
            cur = cur.getNext();
            index++;
        }
        return index;
    }
}
