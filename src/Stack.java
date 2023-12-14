public class Stack {

    private Node top;
    private int size;

    public Stack() {
        top = null;
    }

    public Stack(int size) {

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
            popStr = top.getData();
            top = top.next();
            return popStr;
        }
    }
}
