package prop.domain;

import java.util.Scanner;

/**
 * Girvan-Newman algorithm Driver
 * @author oscar.manas
 */
public class GirvanNewmanDriver {

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Girvan-Newman algorithm");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();

        GirvanNewman gn = null;

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
                    gn = new GirvanNewman();
                    break;
                case 3:
                    gn.readGraph();
                    break;
                case 4:
                    gn.writeGraph();
                    break;
                case 5:
                    gn.execute();
                    break;
            }
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  GirvanNewman()\n");
        sb.append("3:  void readGraph()\n");
        sb.append("4:  void writeGraph()\n");
        sb.append("5:  void execute()\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

}
