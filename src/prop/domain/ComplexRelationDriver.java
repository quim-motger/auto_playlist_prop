package prop.domain;

import java.util.Scanner;

/**
 * Complex Relation Driver.
 * @author oscar.manas
 */
public class ComplexRelationDriver {

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Complex Relation");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();
        System.out.println("WARNING: it is highly recommendable to start with option #2\n");

        ComplexRelation complexRelation = null;
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
                    complexRelation = new ComplexRelationStub();
                    break;
            }
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  ComplexRelation()\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

    private static class ComplexRelationStub extends ComplexRelation {

        public boolean evaluateSongs(Song s1, Song s2) {
            return false;
        }

        public boolean evaluateUser(User u) {
            return false;
        }

    }
}
