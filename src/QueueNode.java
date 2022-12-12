public class QueueNode {
    // Each node contains a position
    // and a reference to the next node
    Position position;
    QueueNode next;

    public QueueNode(Position position) {
        this.position = position;
        this.next = null;
    }
}