package classes.common;

public class Node {
    private Position data;
    private Node next;

    public Node(Position data) {
        this.data = data;
        this.next = null;
    }

    public Node(Position data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Position getData() {
        return data;
    }

    public void setData(Position data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}