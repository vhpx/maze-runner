public class StackNode {
    // Each node contains a position
    // and a reference to the next node
    Position position;
    StackNode next;

    public StackNode(Position position) {
        this.position = position;
        this.next = null;
    }

    public Position getPos() {
        return position;
    }

    public void setPos(Position pos) {
        this.position = pos;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}
