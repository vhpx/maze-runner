package classes.common;

import classes.common.Position;
import classes.common.Node;

public class Queue {
    private Node head;
    private Node tail;

    public Queue() {
        head = null;
        tail = null;
    }

    public void enqueue(Position pos) {
        Node node = new Node(pos);

        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
        }

        tail = node;
    }

    public Position dequeue() {
        if (head == null) {
            return null;
        }

        Position data = head.getData();
        head = head.getNext();

        if (head == null) {
            tail = null;
        }

        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void print() {
        Node current = head;

        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public Position peek() {
        if (isEmpty()) return null;
        return head.getData();
    }
}
