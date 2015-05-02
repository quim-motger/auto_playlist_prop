package prop.domain;

import java.util.Scanner;

/**
 * Created by quim_motger on 16/04/15.
 */
public class PairDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Pair");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfoComplete();

        ComplexRelation complexRelation = null;
        Scanner in = new Scanner(System.in);

        int i = -1;
        Pair p;
        while (i != 0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfoComplete();
                    break;
                case 2:
                    int first = in.nextInt();
                    int second = in.nextInt();
                    p = Pair.create(first, second);
                    System.out.print("First: " + p.first + "\nSecond: " + p.second + "\n");
                    break;
                case 3:
                    String firsts = in.next();
                    String seconds = in.next();
                    p = Pair.create(firsts, seconds);
                    System.out.print("First: " + p.first + "\nSecond: " + p.second + "\n");
                default:
                    printInfoComplete();
            }
            if (i > 1 && i < 3) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  printInfoComplete\n");
        sb.append("2:  create(int first, int second)\n");
        sb.append("3:  create(String first, String second)\n");
        System.out.print(sb.toString());
    }

    private static void printInfoBrief() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  printInfoComplete()\n");
        System.out.print(sb.toString());
    }
}
