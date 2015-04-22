package prop.domain;

import java.util.Scanner;

/**
 * LouvainDriver in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 22/04/15
 */
public class LouvainDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Louvain algorithm");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();

        Louvain louvain = null;

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
                    louvain = new Louvain();
                    break;
                case 3:
                    louvain.readGraph();
                    break;
                case 4:
                    louvain.writeGraph();
                    break;
                case 5:
                    louvain.modularityOptimization();
                    break;
            }
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  Louvain()\n");
        sb.append("3:  void readGraph()\n");
        sb.append("4:  void writeGraph()\n");
        sb.append("5:  void modularityOptimization()\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }
}
