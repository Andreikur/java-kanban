package manager;

import domain.Node;
import domain.Task;

import java.util.*;

public class  InMemoryHistoryManager implements HistoryManager {

    private Node<Task> head;
    private Node<Task> tail;

    private final   LinkedList<Task> history = new LinkedList<>();
    private final HashMap<Integer, Node<Task>> nodes = new HashMap<>();

    public  List<Task> getHistory(){
        getTasks();
        return  history;
    }




    public void add(Task task){
        Node<Task> currentNode = this.linkLast(task);
        if (nodes.containsKey(task.getIdTask())){
            removeNode(nodes.get(task.getIdTask()));           //удаление повторяющейся задачи из истории
            nodes.remove(task.getIdTask());
        }
        nodes.put(task.getIdTask(), currentNode);
    }

    @Override
    public void remove(int id) {
        if (nodes.containsKey(id)) {
            removeNode(nodes.get(id));           //удаление задачи из истории при удалении из списка
            nodes.remove(id);
        }
    }

    //добавление задачи в конец списка истории
        public Node<Task> linkLast (Task  value){
        Node<Task> oldTAil = tail;
        Node<Task> node = new Node<>(oldTAil ,value, null);
        tail = node;
        if (oldTAil == null) {                 //Если добавляется первый узел. Начало и конец это один и тот же элемент
            head = node;
        } else {
            oldTAil.setNext(node);
        }
            return node;
    }

    public void getTasks(){
        history.clear();
        Node<Task> cur = head;
            while (cur != null) {
                history.add(cur.getValue());
                cur = cur.getNext();
            }
        }

    //удаление повторяющегося элемента и при удалении элемента из списка
    public void removeNode(Node<Task> node){
        if(node.getPrev() == null && node.getNext() == null){
            head = null;
            tail = null;
        } else if (node.getPrev() == null){
            head = node.getNext();
            head.setPrev(null);
       } else if(node.getNext() == null){
            tail = node.getPrev();
            tail.setNext(null);
       } else {
           node.getPrev().setNext(node.getNext());
           node.getNext().setPrev(node.getPrev());
       }
    }

}
