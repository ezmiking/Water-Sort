public class Stack_1 {

    public Node_1 top;
    public static int selectedBottle = 0;
    public int maxBottleSize;
    public static int isPour = 0; //مال اینکه بدونیم بطری میتونه داخلش چیزی ریخته بشه یا نه
    private Stack_1 nextStack;
//    private int bottleSize = 0; //مقداری که در هر بطری قرار دارد و نمیدانیم چقد است
//    private int maxBottleSize;
//    private int bottleNum;

    public Stack_1() {
        top = null;
        setNextStack(null);
//        bottleSize = 0;
    }

    public boolean sameColor() {
        Node_1 temp = top;
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

    public Stack_1(Node_1 x) {
        if (top == null) {
            top = x;
        } else {
            x.next(top);
            top = x;
        }
//        bottleSize++;
        setNextStack(null);
    }

    public void setNextStack(Stack_1 nextStack) {
        this.nextStack = nextStack;
    }

    public Stack_1 getNextStack() {
        return nextStack;
    }

    public boolean isEmpty() {
        if (top == null) return true;
        return false;
    }

    public void pushForUndo(String color) {
        Node_1 undoColor = new Node_1(color);
        if (top == null) {
            top = undoColor;
        } else {
            undoColor.next(top);
            top = undoColor;
        }
    }

    public void push(String color) {
        Node_1 newColor = new Node_1(color);
        Node_1 temp = top;
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

    public void clearStack() {
        Node_1 temp = top;
        while (temp.next() != null) {
            pop();
//            temp = null;
//            temp = top.next();
//            top = temp;
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
