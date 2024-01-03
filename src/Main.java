import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[] color = {"kir","dol","chool"};
        int maxB = 3;
        WaterSortGame waterSortGame = new WaterSortGame(color, 3);
        waterSortGame.display();
//        waterSortGame.replaceColor("kir", "khkhkhkh");
//
        waterSortGame.addEmptyBottle();
        waterSortGame.display();
    }
}
