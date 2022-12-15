public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(T pos) {
        Node<T> newNode = new Node<T>(pos);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    public void addLast(T pos) {
        Node<T> newNode = new Node<T>(pos);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public void add(int index, T pos) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(pos);
        } else if (index == size) {
            addLast(pos);
        } else {
            Node<T> newNode = new Node<T>(pos);
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.getNext();
            }
            newNode.setNext(prev.getNext());
            prev.setNext(newNode);
            size++;
        }
    }

    public T getFirst() {
        if (head == null) {
            throw new NullPointerException();
        }
        return head.getData();
    }

    public T getLast() {
        if (tail == null) {
            throw new NullPointerException();
        }
        return tail.getData();
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public T removeFirst() {
        if (head == null) {
            throw new NullPointerException();
        }

        T pos = head.getData();
        head = head.getNext();
        size--;
        return pos;
    }

    public T removeLast() {
        if (tail == null) {
            throw new NullPointerException();
        }

        T value = tail.getData();
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node<T> current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            current.setNext(null);
            tail = current;
        }
        size--;
        return value;
    }
}
