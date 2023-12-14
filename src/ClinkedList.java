public class ClinkedList {
    private Stack last;
    private int countBottle;

    public ClinkedList() {
        last = null;
        countBottle++;
    }
    public ClinkedList(Stack last) {
        last.setNextStack(last);
        countBottle++;
    }
    public int length() {
        return countBottle;
    }
    public boolean isEmpty() {
        return countBottle == 0;
    }

}
