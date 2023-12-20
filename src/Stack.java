public class Stack {

    public Node top;
    public static int selectedBottle = 0;
    public int maxBottleSize;
    public static int isPour = 0;
    private Stack nextStack;
//    private int bottleSize = 0; //مقداری که در هر بطری قرار دارد و نمیدانیم چقد است
//    private int maxBottleSize;
//    private int bottleNum;

    public Stack() {
        top = null;
        setNextStack(null);
//        bottleSize = 0;
    }

    public boolean sameColor() {
        Node temp = top;
        int k = 1;
        while (temp == temp.next()) {
            temp = temp.next();
            k++;
            //1-2-3-4
        }
        if (k == maxBottleSize) {
            return true;
        }
        else return false;
    }
    public Stack(Node x) {
        if (top == null) {
            top = x;
        } else {
            x.next(top);
            top = x;
        }
//        bottleSize++;
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
        Node temp = top;
        int j = 1;
        while (temp.next() != null) {
            j++;
            temp = temp.next();
        }
        if (top == null) {
            top = newColor;
            isPour = 1;
//            bottleSize++;
        }
        else if (j == maxBottleSize) {
            System.out.println("The bottle is full");
            isPour = 0;
            return;
        }
        else {
            if (newColor.getColor() == top.getColor()) {
                newColor.next(top);
                top = newColor;
                isPour = 1;
            } else {
                System.out.println("You cannot add this color to the bottle");
                isPour = 0;
                return;
            }
//            bottleSize++;
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
//            bottleSize--;
            return popStr;
        }
    }

    //    public boolean isSelected(Stack p) {
//        if (p.top == null) {
//            System.out.println("You can't select this bottle");
//            return false;
//        } else {
//            Node temp = p.top;
//            int k = 0;
//            while (temp.next() != null) {
//                bottleSize++;
//                if (temp.getColor() == temp.next().getColor()) {
//                    k++;
//                    temp = temp.next();
//                }
//                else {
//                    return true;
//                }
//            }
//            if (bottleSize == maxBottleSize && k == bottleSize) return false;
//            return true;
//        }
//    }
}
