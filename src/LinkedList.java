public class LinkedList<T> {
    private Node<T> head;
    private int size = 0;

    public LinkedList() {
        head = null;
    }

    public void addFirst(T node) {
        if (head == null) {
            head = new Node<T>(node);
        } else {
            Node<T> newNode = new Node<T>(node);
            newNode.setNext(head);
            head = newNode;
        }

        size++;
    }

    public int getSize() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            return null;

        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node.getData();
    }
}
