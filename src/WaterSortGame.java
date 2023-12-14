import java.util.Scanner;

public class WaterSortGame {

    private ClinkedList clinkedList;
    private Stack stack;
    private int bottleSize = 0; //مقداری که در هر بطری قرار دارد و نمیدانیم چقد است
    private int maxBottleSize;

    public WaterSortGame(String[] colors, int maxBottleSize) {

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
            if (bottleSize == maxBottleSize && k == bottleSize) return false;
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

    public int getNumberBottle() {
        Scanner scanner = new Scanner(System.in);
        int bottleNumber = scanner.nextInt();
        while (bottleNumber <= 0){
            bottleNumber = scanner.nextInt();
        }
        return bottleNumber;
    }

    public void display() {

    }
}
