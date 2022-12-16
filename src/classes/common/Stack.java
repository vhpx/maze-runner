package classes.common;

public class Stack<T> {
    private Node<T> head;
    private int size;

    public Stack() {
        head = null;
        size = 0;
    }

    public T peek() {
        if (head == null)
            return null;

        return head.getData();
    }

    public void push(T pos) {
        if (head == null) {
            head = new Node<T>(pos);
        } else {
            Node<T> node = new Node<T>(pos);
            node.setNext(head);
            head = node;
        }

        size++;
    }

    public T pop() {
        if (head == null)
            return null;

        Node<T> node = head;
        head = head.getNext();
        size--;

        return node.getData();
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
