public class LinkedList<T> {
    private Node<T> head;
    private int size = 0;

    public LinkedList() {
        head = null;
    }

    public void addLast(T data) {
        if (head == null) {
            head = new Node<>(data);
        } else {
            Node<T> node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<>(data);
        }

        size++;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        return sb.toString();
    }
}
