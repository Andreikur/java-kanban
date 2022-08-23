package domain;

public class Node<Task> {
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

    public Task getValue() {
        return value;
    }

    public void setValue(Task value) {
        this.value = value;
    }

    public Node<Task> getNext() {
        return next;
    }

    public void setNext(Node<Task> next) {
        this.next = next;
    }

    public Node<Task> getPrev() {
        return prev;
    }

    public void setPrev(Node<Task> prev) {
        this.prev = prev;
    }
}

