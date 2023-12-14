public class Node {

    private String color;
    private Node next;


    public Node() {
        setColor(null);
        next(null);
    }
    public Node(String color) {
        setColor(color);
        next(null);
    }
    public void setColor(String color) { //set color
        this.color = color;
    }
    public String getColor() { //get color
        return color;
    }
    public void next(Node next) {
        this.next = next;
    }
    public Node next() {
        return next;
    }

}
