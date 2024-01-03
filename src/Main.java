import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[] color = {"kir","dol","chool"};
        int maxB = 3;
        WaterSortGame waterSortGame = new WaterSortGame(color, 3);
        waterSortGame.display();
        waterSortGame.select(1);
        waterSortGame.swap(3);
        waterSortGame.display();
//        waterSortGame.pour(4);
//        waterSortGame.display();
    }
}
