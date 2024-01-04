import java.util.Random;
import java.util.Scanner;


public class WaterSortGame {
    private ClinkedList clinkedList = new ClinkedList();
//    private Stack stackFirst = new Stack();
    public static int maxBottleSize;
    public static int countBottle;
    public static int bottleNumSelected;
    private static int isPour = 0;
    public static int sizeArray;
    public static String[] color = new String[100];
    private static boolean addEmpty = false;
    public boolean isSelected = false;
    private boolean isPoured = false;


    Scanner input = new Scanner(System.in);
    Random random = new Random();

    public WaterSortGame(String[] color, int maxBottleSizeInput) {
        countBottle = 4;
        maxBottleSize = 3;
        int  p = 0; // آرایه ran اندیس
//        maxBottleSize = input.nextInt();
        maxBottleSize = maxBottleSizeInput;
        String[] rand = new String[color.length * maxBottleSizeInput];
        int randSize = rand.length;
        int t;
        sizeArray = color.length;;
        countBottle = color.length + 1;
//        for (int i = 0; i < color.length; i++) {
//            this.color[i] = color[i];
//        }


//        String newColors = input.next();
//        color = newColors.split(" ");
//        countBottle = color.length + 1;

        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < maxBottleSizeInput; j++) {
                rand[p] = color[i];
                p++;
            }
        }
        for (int i = 0; i < countBottle - 1; i++) {
            Stack stackFirst = new Stack();
            for (int j = 0; j < maxBottleSizeInput; j++) {
                t = random.nextInt(randSize);
                stackFirst.push(rand[t]);
                rand[t] = rand[randSize - 1];
                randSize--;
            }
            clinkedList.push(stackFirst);
        }
        Stack stackEmpty = new Stack();
//        for (int i = 0; i < maxBottleSize; i++) {
//            stackEmpty.push("Empty");
//        }
        clinkedList.push(stackEmpty);
    }

    public void display() {

        for (int i = 1; i <= maxBottleSize; i++) {
            Stack first = clinkedList.last.getNextStack();
            for (int j = 1; j <= countBottle; j++) {
                System.out.print(first.display(i) + "   ");
                first = first.getNextStack();
            }
            System.out.println();
        }
    }

    public boolean select(int bottleNumber) {
        Stack S = findBottle(bottleNumber);
        if (bottleNumber > countBottle) {
            System.out.println("Bottle is not found");
            isSelected = false;
            return isSelected;
        }else {
            S.bottleSelected = true;
        }
        if ( S.bottleSelected == true && isSelected(S) == true) {
            isSelected = true;
            bottleNumSelected = bottleNumber;
            return isSelected;
        } else {
            S.bottleSelected = false;
            isSelected = false;
            return isSelected;
        }
    }

    public boolean isSelected(Stack temp) {
        int bottleCount = 0;
        int samColor = 1;
        if (temp.top == null) {
            System.out.println("You can't select this bottle");
            return false;
        }else {
            Node t = temp.top;
            while (t.next() != null) {
                bottleCount++;
                if (t == t.next()) {
                    samColor++;
                    t = t.next();
                }else {
                    return true;
                }
            }
            if (bottleCount == maxBottleSize && samColor == bottleCount) {
                return false;
            }
            return true;
        }
    }

    public Stack findBottle(int bottleNumber) {
        Stack first = clinkedList.last.getNextStack();
        for (int i = 1; i < bottleNumber; i++) {
            first = first.getNextStack();
        }
        return first;
    }

    public Stack findBottlePrevious(int bottleNumber) {
        Stack previous = clinkedList.last.getNextStack();
        for (int i = 2; i < bottleNumber; i++) {
            previous = previous.getNextStack();
        }
        return previous;
    }

    public void deSelect() {
        Stack select = findBottle(bottleNumSelected);
        select.bottleSelected = false;
        bottleNumSelected = 0;
    }

    public void nextSelect_2() {
        Stack nextSelect = findBottle(bottleNumSelected);
        if (isSelected == true) {
            nextSelect.bottleSelected = false;
            select(bottleNumSelected + 1);
        }else {
            System.out.println("you can't select bottle");
        }
    }

    public void nextSelect() {
        if (isSelected == true) {
            Stack nextSelect = findBottle(bottleNumSelected);
            nextSelect.bottleSelected = false;
//            nextSelect = nextSelect.getNextStack();
            if (bottleNumSelected <= countBottle) {
                bottleNumSelected++;
            }else {
                bottleNumSelected = (bottleNumSelected + 1) % countBottle;
            }
            select(bottleNumSelected);
//            nextSelect.bottleSelected = true;
        } else {
            System.out.println("you can't select bottle");
        }
    }

    public void previousSelect() {
        Stack select = findBottle(bottleNumSelected);
        Stack selectPrevious = findBottle(bottleNumSelected);
        while (selectPrevious.nextStack != findBottle(bottleNumSelected)) {
            selectPrevious = selectPrevious.getNextStack();
        }
        select.bottleSelected = false;
        selectPrevious.bottleSelected = true;
        bottleNumSelected--;
    }

    public void  pushForPour(String color, Stack stackGetColor) {
        Node newColor = new Node(color);
        Node temp = stackGetColor.top;
        int j = 1;
        if (temp != null) {
            while (temp.next() != null) {
                j++;
                temp = temp.next();
            }
        } else {
            j = 0;
        }
        if (stackGetColor.top == null) {
            stackGetColor.top = newColor;
            isPour = 1;
//            bottleSize++;
        }
        else if (j == maxBottleSize) {
            System.out.println("The bottle is full");
            isPour = 0;
            return;
        }
        else {
            if (newColor.getColor() == stackGetColor.top.getColor()) {
                newColor.next(stackGetColor.top);
                stackGetColor.top = newColor;
                isPour = 1;
            } else {
                System.out.println("You cannot add this color to the bottle");
                isPour = 0;
                return;
            }
//            bottleSize++;
        }
    }

//    public boolean pour(int bottleNumber) { //*****undo redo
//        int k = 0; // تعداد رنگایی که اضافه میشه به بطری دیگر
//        Stack isStack = findBottle(bottleNumber);
//        Stack select = findBottle(bottleNumSelected);
//        pushForPour(select.top.getColor(), isStack);
//        select.pop();
//        while (select.top.getColor() == isStack.top.getColor()) {
//            pushForPour(select.top.getColor(), isStack);
//            if (isPour == 1) {
//                k++;
//                select.pop();
//            }
//        }
//        if (k > 0) {
//            isPoured = true;
//            return isPoured;
//        }else {
//            isPoured = false;
//            return isPoured;
//        }
//    }

    public boolean pour_2(int bottleNumber) {
        Stack isStack = findBottle(bottleNumber);
        Stack select = findBottle(bottleNumSelected);
        Node temp = isStack.top;
        int j = 1;
        while (temp != null) {
            j++;
        }
        if (isStack.top == null) {
            isStack.push(select.top.getColor());
            select.pop();
            isPoured = true;
        }
        while (select.top.getColor() == isStack.top.getColor()) {
            if (j == maxBottleSize) {
                System.out.println("bottle is full");
                isPoured = false;
            } else {
                isStack.push(select.top.getColor());
                select.pop();
                isPoured = true;
            }
        }

        return isPoured;
    }

    public void swap(int bottleNumber) {
        Stack select = findBottle(bottleNumSelected);
        Stack swapStack = findBottle(bottleNumber);
        Stack previousSelect, previousSwapStack;
        Stack temp = null;
        previousSelect = findBottlePrevious(bottleNumSelected);
        previousSwapStack = findBottlePrevious(bottleNumber);
        if (select == clinkedList.last) {
            temp = select;
            temp.setNextStack(swapStack.getNextStack());
            swapStack.setNextStack(select.getNextStack());
            previousSwapStack.setNextStack(temp);
            previousSelect.setNextStack(swapStack);
            clinkedList.last = swapStack;
        }
        else if (swapStack == clinkedList.last) {
            temp = swapStack;
            temp.setNextStack(select.getNextStack());
            select.setNextStack(swapStack.getNextStack());
            previousSelect.setNextStack(temp);
            previousSwapStack.setNextStack(select);
            clinkedList.last = select;
        }
        else if (select.getNextStack() == swapStack) {
            select.setNextStack(swapStack.getNextStack());
            swapStack.setNextStack(select);
            previousSelect.setNextStack(swapStack);
        }
        else if (swapStack.getNextStack() == select) {
            swapStack.setNextStack(select.getNextStack());
            select.setNextStack(swapStack);
            previousSwapStack.setNextStack(select);
        }
        else {
            temp = swapStack;
            temp.setNextStack(select.getNextStack());
            select.setNextStack(swapStack.getNextStack());
            previousSelect.setNextStack(temp);
            previousSwapStack.setNextStack(select);
        }
    }

    public void replaceColor(String firstColor, String secondColor) {
        int k = 0, sw = 0;
        for (int i = 0; i < 3; i++) { // 3 -> color.length
            System.out.println(color[i]);
            if (firstColor.equals(color[i])) {
                System.out.println("kh");
                sw = 1;
                k = i;
            }
        }
        color[k] = secondColor;
        if (sw == 1) {
            System.out.println("kh");
            Stack stackReplace = clinkedList.last;
            for (int i = 0; i < countBottle; i++) {
                Node temp = stackReplace.top;
                while (temp != null) {
                    if (temp.getColor().equals(firstColor)) {
                        temp.setColor(secondColor);
                    }
                    temp = temp.next();
                }
                stackReplace = stackReplace.getNextStack();
            }
        }
    }

    public boolean sameColor(Stack stackSame) {
        Node temp = stackSame.top;
        int k = 1;

            while (temp != null) {
                temp = temp.next();
                k++;
                //1-2-3-4
            }

        if (k == maxBottleSize) {
            return true;
        }
        else return false;
    }

    public boolean hasWon() {
        int k = 0;//تعداد بطری هایی که کامل شده اند
        Stack temp = clinkedList.last;
        while (temp.getNextStack() != clinkedList.last) {
            if (sameColor(temp) == true) {
                k++;
            }
            temp = temp.getNextStack();
        }
        if (k == countBottle - 1) {
            System.out.println("you are winner");
            return true;
        }
        else return false;
    }

    public void addEmptyBottle() {
        if (addEmpty == false) {
            Stack minStack = new Stack();
            clinkedList.push(minStack);
            countBottle++;
            addEmpty = true;
        }
        else return;
    }
}
