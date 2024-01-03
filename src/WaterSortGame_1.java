import java.util.Scanner;

public class WaterSortGame_1 {

    private ClinkedList_1 clinkedList;
    private Stack_1 undoStack;
    private int bottleNumber_selected;
    private int bottleNumber_2;
    private int countColor;//= maxBottle
//    private Stack stack;//باید اینو حذف کنم
    private String[] colors;
    private String[] randomColors;
    private String[][] arrDisplay;
    private static boolean addEmpty = false;
    private static boolean undo = false;
//    private int countColors; // = maxBottleSize
    private int bottleSize = 0; //مقداری که در هر بطری قرار دارد و نمیدانیم چقد است
    private boolean isPoured = false;

    Scanner scanner = new Scanner(System.in);

    public WaterSortGame_1(String[] colors, int maxBottleSize) {

    }

    public void display() {
        Stack_1 temp = clinkedList.last.getNextStack();
//        arrDisplay[clinkedList.last.maxBottleSize][];
        while (temp.getNextStack() != clinkedList.last.getNextStack()) {
            while (temp.top.next() != null) {
                System.out.println(temp.top.getColor());
                temp.top = temp.top.next();
            }

        }
    }

    public void undo() {
        //1->2->3->4
        int k = 0; // تعداد رنگ هایی که در بطری اندو وجود دارد برای حذف از بطری دیگر
        Stack_1 stack_selected, stack_2;
        stack_selected = bottle_selected(bottleNumber_selected);
        stack_2 = bottle_selected(bottleNumber_2);
        if (undoStack.isEmpty() == false) {
            undo = true;
            while (undoStack.top != null) {
                stack_selected.pushForUndo(undoStack.top.getColor());
                undoStack.pop();
                k++;
            }
            for (int i = 0; i < k; i++) {
                stack_2.pop();
            }
        }
        else {
            undo = false;
            System.out.println("you can't undo");
        }
    }

    public void redo() {
        if (undo == true) {
            pourForRedo(bottleNumber_selected, bottleNumber_2);
        }else {
            System.out.println("you can't use redo");
        }
    }

    public void pourForRedo(int BtNum_selected, int BtNum_2) {
        Stack_1 stack_selected, stack_2;
        stack_2 = bottle_selected(BtNum_2);
        stack_selected = bottle_selected(BtNum_selected);
        while (stack_selected.top.getColor() == stack_2.top.getColor()) {
            stack_2.push(stack_selected.top.getColor());
            if (stack_2.isPour == 1) {
                stack_selected.pop();
            }
        }

    }

    public boolean hasWon() {
        int k = 0;//تعداد بطری هایی که کامل شده اند
        Stack_1 temp = clinkedList.last;
        while (temp.getNextStack() != clinkedList.last) {
            if (temp.sameColor() == true) {
                k++;
            }
            temp = temp.getNextStack();
        }
        if (k == clinkedList.last.maxBottleSize) {
            System.out.println("you are winner");
            return true;
        }
        else return false;
    }

    public void addEmptyBottle() {
        if (addEmpty == false) {
            Stack_1 minStack = new Stack_1();
            minStack.maxBottleSize = clinkedList.last.maxBottleSize / 2;
            minStack.setNextStack(clinkedList.last.getNextStack());
            clinkedList.last.setNextStack(minStack);
            clinkedList.last = minStack;
            addEmpty = true;
        }
        else return;
    }

    public void replaceColor(String firstColor, String secondColor) {
        int sw = 0, k = 0;
        firstColor = scanner.next();
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == firstColor) {
                sw = 1;
                k = i;
            }
        }
        if (sw == 1) {
            secondColor = scanner.next();
            colors[k] = secondColor;

        } else {
            System.out.println("your color in doesn't exist");
            return;
        }
    }

    public void swap(int bottleNumber) {
        Stack_1 stack_select, stack_2;
        Stack_1 temp_select, temp_2;
        Stack_1 prev_select = null, prev_2 = null;
        stack_2 = bottle_selected(bottleNumber);
        temp_2 = stack_2;
        while (temp_2.getNextStack() != stack_2) {
            prev_2 = temp_2;
            temp_2 = temp_2.getNextStack();
        }
        temp_2 = stack_2;
        if (select(getNumberBottle()) == true) {
            stack_select = bottle_selected(getNumberBottle());
            temp_select = stack_select;
            while (temp_select.getNextStack() != stack_select) {
                prev_select = temp_select;
                temp_select = temp_select.getNextStack();
            }
            temp_select = stack_select;

            prev_select.setNextStack(temp_2);
            temp_2.setNextStack(stack_select.getNextStack());
            stack_select.setNextStack(null);

            prev_2.setNextStack(temp_select);
            temp_select.setNextStack(stack_2.getNextStack());
            stack_2.setNextStack(null);

        }
    }

    public boolean select(int bottleNumber) {
//        bottleNumber = getNumberBottle();
        Stack_1 first = clinkedList.last.getNextStack();
        if (bottleNumber > clinkedList.countBottle) {
            System.out.println("Bottle is not found");
            return false;
        } else {
            for (int i = 1; i < bottleNumber; i++) {
                first = first.getNextStack();
            }
            first.selectedBottle = 1;
        }
        if (first.selectedBottle == 1 && isSelected(first) == true) {
            return true;
        } else {
            first.selectedBottle = 0;
            return false;
        }
    }

    public boolean isSelected(Stack_1 p) {
        if (p.top == null) {
            System.out.println("You can't select this bottle");
            return false;
        } else {
            Node_1 temp = p.top;
            int k = 1;
            while (temp.next() != null) {
                bottleSize++;
                if (temp.getColor() == temp.next().getColor()) {
                    k++;
                    temp = temp.next();
                }
                else {
                    return true;
                }
            }
            if (bottleSize == clinkedList.countBottle - 1 && k == bottleSize) return false;// درست کنم
            // maxBottleSize = clinkedList.countBottle - 1
            return true;
        }
    }

    public void deSelect() {
        Stack_1 ft = clinkedList.last.getNextStack();
        if (select(getNumberBottle()) == true) {
            for (int i = 1; i < getNumberBottle(); i++) {
                ft = ft.getNextStack();
            }
            ft.selectedBottle = 0;
        }
        else {
            System.out.println("No bottles selected");
        }
    }

    public void selectNext() {
        Stack_1 ft = clinkedList.last.getNextStack();
        if (select(getNumberBottle()) == true) {
            for (int i = 1; i < getNumberBottle(); i++) {
                ft = ft.getNextStack();
            }
            ft.selectedBottle = 0;
            ft = ft.getNextStack();
            ft.selectedBottle = 1;
            int selNex = 0;
            if (getNumberBottle() + 1 > clinkedList.countBottle) {
                selNex = (getNumberBottle() + 1) % clinkedList.countBottle;
                select(selNex);
            }
            else {
                selNex = getNumberBottle() + 1;
                select(selNex);
            }
        }
        else {
            System.out.println("No bottles selected");
        }
    }

    public void selectPrevious() {
        Stack_1 first = clinkedList.last.getNextStack();
        if (select(getNumberBottle()) == true) {
            for (int i = 1; i < getNumberBottle(); i++) {
                first = first.getNextStack();
            }
            first.selectedBottle = 0;
            Stack_1 temp = first;
            while (first.getNextStack() != temp) {
                first = first.getNextStack();
            }
            first.selectedBottle = 1;
            int selprev = 0;
            if (getNumberBottle() == 1) {
                selprev = clinkedList.countBottle;
                select(selprev);
            }
            else {
                selprev = getNumberBottle() - 1;
                select(selprev);
            }
        }
        else {
            System.out.println("No bottles selected");
        }
    }

    public Stack_1 bottle_selected(int bottleNumber) { //استکی که انتخاب شده است را برمیگرداند
        if (select(bottleNumber) == true) {
            Stack_1 temp = clinkedList.last.getNextStack();
            for (int i = 1; i < bottleNumber; i++) {
                temp = temp.getNextStack();
            }
            return temp;
        }else {
            System.out.println("No bottles selected");
            return null;
        }
    }

    public boolean pour(int bottleNumber) {
        int k = 0; // تعداد رنگایی که اضافه میشه به بطری دیگر
        int selectNum = getNumberBottle();
        Stack_1 stack_selected, stack_2;
        stack_2 = bottle_selected(bottleNumber);
        bottleNumber_2 = bottleNumber;
        bottleNumber_selected = selectNum;
        if (select(selectNum) == true) {
            stack_selected = bottle_selected(selectNum);
            while (stack_selected.top.getColor() == stack_2.top.getColor()) {
                stack_2.push(stack_selected.top.getColor());
                if (stack_2.isPour == 1) {
                    k++;
                    undoStack.clearStack();
                    undoStack.push(stack_selected.top.getColor());
                    stack_selected.pop();
                }
            }
            if (k > 0) {
                isPoured = true;
                return isPoured;
            }
            else {
                isPoured = false;
                return isPoured;
            }
        }else {
            isPoured = false;
            return isPoured;
        }
    }

    public int getNumberBottle() {
        int bottleNumber = scanner.nextInt();
        while (bottleNumber <= 0){
            bottleNumber = scanner.nextInt();
        }
        return bottleNumber;
    }

    public void getColors() {
        System.out.println("pleas enter your colors");
        String newColor = null;
        for (int i = 0; i < clinkedList.last.maxBottleSize; i++) {
            newColor = scanner.next();
            colors[i] = newColor;
        }
    }

    public void getMaxBottleSize() {
        clinkedList.last.maxBottleSize = scanner.nextInt();
        countColor = clinkedList.last.maxBottleSize;
    }

}
