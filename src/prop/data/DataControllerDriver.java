package prop.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class DataControllerDriver
 * @author Carles Garcia Cabot
 */
public class DataControllerDriver {
    public static void main(String[] args) {
        try {
            System.err.println("**********************************************************");
            System.err.println("** DataController");
            System.err.println("**********************************************************");
            System.err.print("\n");
            printInfo();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Scanner in = new Scanner(System.in);
            String data, path, res;
            int i = -1;
            while (i != 0) {
                res = br.readLine();
                String[] parts = res.split(Pattern.quote("|"));
                i = Integer.parseInt(parts[0]);
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        printInfo();
                        break;
                    case 3:
                        path = parts[1];
                        System.out.println(DataController.load(path));
                        break;
                    case 2:
                        data = parts[1];
                        path = parts[2];
                        DataController.save(data, path);
                        break;
                    default:
                        printInfo();
                }
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
        System.err.print(sb.toString());
        System.err.println("Separate parameters with '|' \n" +
                "Example: 2|Hello World!|hello.txt");
    }
}
