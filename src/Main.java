import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int maxBottleSize;
    public static String[] color;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //String pp = scan.next();
        color = scan.nextLine().split(" ");
        maxBottleSize = scan.nextInt();
        WaterSortGame waterSortGame = new WaterSortGame(color, maxBottleSize);
        System.out.println(color.length);
        for (int i = 0; i < color.length; i++) {
            waterSortGame.color[i] = color[i];
        }
//        waterSortGame.display();
//        System.out.println();
//        waterSortGame.replaceColor("111", "777");
//        waterSortGame.display();
        String order = "";
        while (waterSortGame.hasWon() == false) {
            waterSortGame.display();
            System.out.println();
            order = scan.next();
            switch (order) {
                case "select":
                    int bottleNumber = scan.nextInt();
                    waterSortGame.select(bottleNumber);
//                    waterSortGame.display();
                    System.out.println("selected --> " + waterSortGame.bottleNumSelected);
                    break;
                case "next":
                    waterSortGame.nextSelect_2();
//                    waterSortGame.display();
                    System.out.println("selected --> " + waterSortGame.bottleNumSelected);
                    break;
                case "previous":
                    waterSortGame.previousSelect();
//                    waterSortGame.display();
                    System.out.println("selected --> " + waterSortGame.bottleNumSelected);
                    break;
                case "deSelect":
                    waterSortGame.deSelect();
//                    waterSortGame.display();
                    System.out.println("selected --> " + waterSortGame.bottleNumSelected);
                    break;
                case "pour":
                    System.out.println("kh");
                    System.out.println("selected --> " + waterSortGame.bottleNumSelected);
                    int pourKh = scan.nextInt();
                    waterSortGame.pour_2(pourKh);
//                    waterSortGame.display();
                    break;
                case "swap":
                    int swapKh = scan.nextInt();
                    waterSortGame.swap(swapKh);
//                    waterSortGame.display();
                    System.out.println("selected --> " + waterSortGame.bottleNumSelected);
                    break;
                case "replace" :
                    String firstcolor = scan.next();
                    String secondColor = scan.next();
                    waterSortGame.replaceColor(firstcolor, secondColor);
                    System.out.println("selected --> " + waterSortGame.bottleNumSelected);

            }
        }
        System.out.println("YOU WON KH KH KH KH !!!");
        waterSortGame.display();
    }
}
