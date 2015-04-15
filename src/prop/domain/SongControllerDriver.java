package prop.domain;

import java.util.Scanner;

/**
 * SongSet Controller Driver
 * @author quim_motger
 */
public class SongControllerDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Song Controller");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        int i = -1;
        while (i != 0) {
            printInfo();
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:   terminate program\n");
        System.out.print(sb.toString());
    }
}
