package prop.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AlgorithmOutputDriver
 * @author Carles Garcia Cabot
 */
public class AlgorithmOutputDriver {
    public static void main(String[] args) {
     /*   try {
            System.out.println("**********************************************************");
            System.out.println("** Algorithm Output");
            System.out.println("**********************************************************");
            System.out.print("\n");
            printInfo();

            Scanner in = new Scanner(System.in);
            int i = -1;
            AlgorithmOutput ao = null;
            while (i != 0) {
                i = in.nextInt();
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        printInfo();
                        break;
                    case 2:
                        ao = new AlgorithmOutput();
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        //densest
                        break;
                    case 6:
                        // getCom
                        break;
                    case 7:
                        for (String s : ao.getLog()) System.out.println(s);
                        break;
                    default:
                        printInfo();
                }
            }
        }
        catch(IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }*/
    }
    private static void printInfo() {
        ArrayList<String> sb = new ArrayList<>();
        sb.add("terminate program");
        sb.add("info");
        sb.add("AlgorithmOutput()");
        sb.add("AlgorithmOutput(java.util.ArrayList<Graph> communities, java.util.ArrayList<java.lang.String> log)");
        sb.add("void 	add(Graph community)");
        sb.add("void 	add(java.lang.String l)");
        sb.add("Graph 	densestGraph()");
        sb.add("java.util.ArrayList<Graph> 	getCommunities()");
        sb.add("java.util.ArrayList<java.lang.String> 	getLog()");
        for (int i = 0; i < sb.size(); ++i) {
            System.out.println(i + ": " + sb.get(i));
        }
    }
}
