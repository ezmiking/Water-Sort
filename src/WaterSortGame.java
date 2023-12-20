import java.util.Scanner;

public class WaterSortGame {

    private ClinkedList clinkedList;
    private String[] colors;
    private String[] randomColors;
    private Stack stack;
    private static boolean addEmpty = false;
//    private int countColors; // = maxBottleSize
    private int bottleSize = 0; //مقداری که در هر بطری قرار دارد و نمیدانیم چقد است
    private boolean isPoured = false;

    Scanner scanner = new Scanner(System.in);

    public WaterSortGame(String[] colors, int maxBottleSize) {

    }

    public boolean hasWon() {
        int k = 0;//تعداد بطری هایی که کامل شده اند
        Stack temp = stack;
        while (temp.getNextStack() != null) {
            if (temp.sameColor() == true) {
                k++;
            }
            temp = temp.getNextStack();
        }
        if (k == stack.maxBottleSize) {
            System.out.println("you are winner");
            return true;
        }
        else return false;
    }

    public void addEmptyBottle() {
        Stack temp = stack;
        if (addEmpty == false) {
            Stack minStack = new Stack();
            minStack.maxBottleSize = stack.maxBottleSize / 2;
            while (temp.getNextStack() != null) {
                temp = temp.getNextStack();
                //1->2->4->6
            }
            temp.setNextStack(minStack);
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
        Stack stack_select, stack_2;
        Stack temp_select, temp_2;
        Stack prev_select = null, prev_2 = null;
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
        Stack first = clinkedList.last.getNextStack();
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
            return false;
        }
    }

    public boolean isSelected(Stack p) {
        if (p.top == null) {
            System.out.println("You can't select this bottle");
            return false;
        } else {
            Node temp = p.top;
            int k = 0;
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
            if (bottleSize == clinkedList.countBottle - 1 && k == bottleSize) return false;
            // maxBottleSize = clinkedList.countBottle - 1
            return true;
        }
    }

    public void deSelect() {
        Stack ft = clinkedList.last.getNextStack();
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
        Stack ft = clinkedList.last.getNextStack();
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
        Stack first = clinkedList.last.getNextStack();
        if (select(getNumberBottle()) == true) {
            for (int i = 1; i < getNumberBottle(); i++) {
                first = first.getNextStack();
            }
            first.selectedBottle = 0;
            Stack temp = first;
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

    public Stack bottle_selected(int bottleNumber) { //استکی که انتخاب شده است را برمیگرداند
        if (select(bottleNumber) == true) {
            Stack temp = clinkedList.last.getNextStack();
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
        int k = 0;
        Stack stack_selected, stack_2;
        stack_2 = bottle_selected(bottleNumber);
        if (select(getNumberBottle()) == true) {
            stack_selected = bottle_selected(getNumberBottle());
            while (stack_selected.top.getColor() == stack_2.top.getColor()) {
                stack_2.push(stack_selected.top.getColor());
                if (stack_2.isPour == 1) {
                    k++;
                    stack_selected.pop();
                }
            }
            if (k > 0) {
                isPoured = true;
                return isPoured;
            } else {
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
        for (int i = 0; i < stack.maxBottleSize; i++) {
            newColor = scanner.next();
            colors[i] = newColor;
        }
    }

    public void getMaxBottleSize() {
        stack.maxBottleSize = scanner.nextInt();
    }

    public void display() {

    }
}
