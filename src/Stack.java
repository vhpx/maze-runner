public class Stack {
    private StackNode top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public void push(Position pos) {
        StackNode newNode = new StackNode(pos);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    public Position pop() {
        if (isEmpty())
            return null;

        Position pos = top.getPos();
        top = top.getNext();
        size--;
        return pos;
    }

    public Position peek() {
        if (isEmpty())
            return null;

        return top.getPos();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}
