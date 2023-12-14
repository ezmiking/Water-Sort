public class ClinkedList {
    Stack last;
    private int countBottle;// تعداد بطری ها
//    private int bottleNumber;
    private int count = 0;

    public ClinkedList() {
        last = null;
        countBottle++;
    }
    public ClinkedList(Stack last) {
        last.setNextStack(last);
        countBottle++;
    }
    public int traverseForCount() {
        Stack p;
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
