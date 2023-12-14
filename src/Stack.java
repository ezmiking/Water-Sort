public class Stack {

    private Node top;
    private Stack nextStack;
    private int bottleSize;
//    private int bottleNum;

    public Stack() {
        top = null;
        setNextStack(null);
        bottleSize = 0;
    }

    public Stack(Node x) {
        if (top == null) {
            top = x;
        } else {
            x.next(top);
            top = x;
        }
        bottleSize++;
        setNextStack(null);
    }

    public void setNextStack(Stack nextStack) {
        this.nextStack = nextStack;
    }
    public Stack getNextStack() {
        return nextStack;
    }

    public boolean isEmpty() {
        if (top == null) return true;
        return false;
    }

    public void push(String color) {
        Node newColor = new Node(color);
        if (top == null) {
            top = newColor;
        } else {
            newColor.next(top);
            top = newColor;
        }
    }

    public String pop() {
        if (isEmpty() == true) {
            System.out.println("Bottle is Empty");
            return null;
        } else {
            String popStr;
            popStr = top.getColor();
            top = top.next();
            return popStr;
        }
    }
}
