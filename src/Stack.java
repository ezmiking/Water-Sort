public class Stack {

    public Node top;
    public Stack nextStack;
    public static boolean bottleSelected = false;
    public static int isPour = 0;

    public Stack() {
        top = null;
        setNextStack(null);
    }

    public Stack(Node x) {
        if (top == null) {
            top = x;
        }else {
            x.next(top);
            top = x;
        }
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
        if (top == null) {
            System.out.println("Bottle is Empty");
            return null;
        } else {
            String popStr;
            popStr = top.getColor();
            top = top.next();
            return popStr;
        }
    }

    public String display(int n) {
        Node temp = top;

        try {
            for (int i = 1; i < n; i++) {
                temp = temp.next();
            }
            return temp.getColor();
        }catch (NullPointerException exception){
            return "Empty";
        }
    }

    public void setNextStack(Stack nextStack) {
        this.nextStack = nextStack;
    }

    public Stack getNextStack() {
        return nextStack;
    }
}
