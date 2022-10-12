package manager.historyManager;

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
            oldTAil.next = node;
        }
            return node;
    }

    public void getTasks(){
        history.clear();
        Node<Task> cur = head;
            while (cur != null) {
                history.add(cur.value);
                cur = cur.next;
            }
        }

    //удаление повторяющегося элемента и при удалении элемента из списка
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
    }
}
