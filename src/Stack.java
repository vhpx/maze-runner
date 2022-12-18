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

        return head.data;
    }

    public void push(T pos) {
        if (head == null) {
            head = new Node<>(pos);
        } else {
            Node<T> node = new Node<>(pos);
            node.next = head;
            head = node;
        }

        size++;
    }

    public T pop() {
        if (head == null)
            return null;

        Node<T> node = head;
        head = head.next;
        size--;

        return node.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
