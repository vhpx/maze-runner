package classes.common;

public class Stack {
    private Node head;
    private int size;

    public Stack() {
        head = null;
        size = 0;
    }

    public void push(Node node) {
        if (head == null) {
            head = node;
        } else {
            node.setNext(head);
            head = node;
        }

        size++;
    }

    public Node pop() {
        if (head == null)
            return null;

        Node node = head;
        head = head.getNext();
        size--;

        return node;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void print() {
        Node node = head;

        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }

        System.out.println();
    }
}
