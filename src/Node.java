public class Node {

    private String data;
    private Node nextData;

    public Node() {
        setData(null);
        next(null);
    }
    public Node(String data) {
        setData(data);
        next(null);
    }
    public void setData(String data) { //set data
        this.data = data;
    }
    public String getData() { //get data
        return data;
    }
    public void next(Node nextData) {
        this.nextData = nextData;
    }
    public Node next() {
        return nextData;
    }

}
