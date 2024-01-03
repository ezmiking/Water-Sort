public class ClinkedList {
    Stack last;

    public ClinkedList() {

    }
    public ClinkedList(Stack last) {
        last.setNextStack(last);
    }

    public void push(Stack p) {
        if (last == null) {
            last = p;
            p.setNextStack(p);
        } else {
            p.setNextStack(last.getNextStack());
            last.setNextStack(p);
            last = p;
        }
    }
}
