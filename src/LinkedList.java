public class LinkedList<T> {
    private Node<T> head;
    private int size = 0;

    public LinkedList() {
        head = null;
    }

    public void addFirst(T node) {
        if (head == null) {
            head = new Node<>(node);
        } else {
            Node<T> newNode = new Node<>(node);
            newNode.setNext(head);
            head = newNode;
        }

        size++;
    }

    public int getSize() {
        return size;
    }
}
