public class Node_1 {

    private String color;
    private Node_1 next;


    public Node_1() {
        setColor(null);
        next(null);
    }
    public Node_1(String color) {
        setColor(color);
        next(null);
    }
    public void setColor(String color) { //set color
        this.color = color;
    }
    public String getColor() { //get color
        return color;
    }
    public void next(Node_1 next) {
        this.next = next;
    }
    public Node_1 next() {
        return next;
    }

}
