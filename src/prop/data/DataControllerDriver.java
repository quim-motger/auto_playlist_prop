package prop.data;

import java.io.IOException;
import java.util.Scanner;
/**
 * Class DataControllerDriver
 * @author Carles Garcia Cabot
 */
public class DataControllerDriver {
    public static void main(String[] args) {
        try {
            System.out.println("**********************************************************");
            System.out.println("** DataController");
            System.out.println("**********************************************************");
            System.out.print("\n");
            printInfo();

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
                        DataController.load(in.next());
                        break;
                    case 3:
                        DataController.save(in.next(), in.next());
                        break;
                    case 4:
                        DataController.loadAllLines(in.next());
                        break;
                    default:
                        printInfo();
                }
                printInfoBrief();
            }
        }
        catch(IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  static void save(String data, String path) throws IOException\n");
        sb.append("3:  static String load(String path) throws IOException\n");
        sb.append("4:  static ArrayList<String> loadAllLines(String path) throws IOException\n");
        System.out.print(sb.toString());
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n"
                + "1:    printInfoComplete()\n");
    }
}
