package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Algorithm Controller Driver
 * @author oscar.manas
 */
public class AlgorithmControllerDriver {

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** AlgorithmController");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();
        System.out.println("WARNING: it is highly recommendable to start with option #2\n");

        AlgorithmController ac = null;
        ListController listController = new ListController();
        RelationController relationController = new RelationController();
        // todo: initialize relationController

        Scanner in = new Scanner(System.in);
        int i = -1;
        while (i != 0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfo();
                    break;
                case 2:
                    ac = new AlgorithmController();
                    break;
                case 3:
                    String title = in.next();
                    int algorithm = in.nextInt();
                    int k = in.nextInt();
                    ArrayList<String> log = ac.execute(title, algorithm, k, listController, relationController);
                    for (String s : log)
                        System.out.print(s);
                    break;
                case 4:
                    int id = in.nextInt();
                    System.out.print(listController.getListString(id));
                    break;
            }

        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  AlgorithmController()\n");
        sb.append("3:  ArrayList<String> execute(String title, int algorithm, int k, ListController listController, " +
                "RelationController relationController)\n");
        sb.append("4:  String getListString(int id)\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

}
