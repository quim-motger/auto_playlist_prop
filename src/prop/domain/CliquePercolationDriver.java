package prop.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clique Percolation class driver
 * @author joaquim.motger
 */
public class CliquePercolationDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Girvan-Newman algorithm");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();

        CliquePercolation cp = null;

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
                    cp = new CliquePercolation();
                    break;
                case 3:
                    cp.readGraph();
                    break;
                case 4:
                    cp.writeGraph();
                    break;
                case 5:
                    cp.execute();
                    break;
                case 6:
                    cp.getCliques();
                    break;
                case 7:
                    int n = in.nextInt();
                    ArrayList<Integer> neighbours = cp.neighbours(n);
                    for (int m : neighbours) System.out.print(m + " ");
                    System.out.print("\n");
            }
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  BronKerboschTomita()\n");
        sb.append("3:  void readGraph()\n");
        sb.append("4:  void writeGraph()\n");
        sb.append("5:  void execute()\n");
        sb.append("6:  void getCliques()\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }
}
