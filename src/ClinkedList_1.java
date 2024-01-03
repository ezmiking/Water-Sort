public class ClinkedList_1 {
    public Stack_1 last;
    public int countBottle;// تعداد بطری ها
//    private int bottleNumber;
    private int count = 0;

    public ClinkedList_1() {
        last = null;
        countBottle++;
    }
    public ClinkedList_1(Stack_1 last) {
        last.setNextStack(last);
        countBottle++;
    }
    public int traverseForCount() {
        Stack_1 p;
        if (last == null) {
            System.out.println("list is empty!!");
            return 0;
        }
        else {
            p = last.getNextStack();
            do {
                count++;
                p.setNextStack(p);
            }while (p != last.getNextStack());
            return count;
        }
    }
    public int length() {
        return countBottle;
    }
    public boolean isEmpty() {
        return countBottle == 0;
    }

}
