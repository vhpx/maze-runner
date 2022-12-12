public class Queue {
    private Node head;
    private Node tail;

    public Queue() {
        head = null;
        tail = null;
    }

    public void enqueue(Position position) {
        // Create a new node
        Node node = new Node(position);

        // If the queue is empty
        if (head == null) {
            // The new node is the head and the tail
            head = node;
            tail = node;
        } else {
            // The new node is the new tail
            tail.next = node;
            tail = node;
        }
    }

    public Position dequeue() {
        // If the queue is empty
        if (head == null) {
            // Return null
            return null;
        }

        // Get the position of the head
        Position position = head.position;

        // Remove the head
        head = head.next;

        // If the queue is empty
        if (head == null) {
            // The tail is also null
            tail = null;
        }

        // Return the position of the head
        return position;
    }

    public boolean isEmpty() {
        // If the queue is empty
        if (head == null) {
            // Return true
            return true;
        }

        // Return false
        return false;
    }

    public void print() {
        // Print the queue
        Node node = head;
        while (node != null) {
            System.out.print(node.position + " ");
            node = node.next;
        }
        System.out.println();
    }

    public Position peek() {
        // If the queue is empty
        if (head == null) {
            // Return null
            return null;
        }

        // Return the position of the head
        return head.position;
    }
}

class Node {
    // Each node contains a position
    // and a reference to the next node
    Position position;
    Node next;

    public Node(Position position) {
        this.position = position;
        this.next = null;
    }

}
