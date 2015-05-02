package prop.domain;

import java.util.Scanner;

/**
 * RelationDriver in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 02/05/15
 */
public class RelationDriver {

    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************************");
        System.out.println("** Playback");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        Song s1 = null;
        Song s2 = null;
        User u = null;
        Relation r = null;
        int i = -1;
        while (i != 0) {
            while (i != 0) {
                i = in.nextInt();
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        printInfoComplete();
                        break;
                    case 2:
                        r = new SimpleRelation(in.next(),in.next(),in.next());
                        break;
                    case 3:
                        System.out.println(r.evaluateSongs(s1,s2));
                        break;
                    case 4:
                        System.out.println(r.evaluateUser(u));
                        break;
                    case 5:
                        s1 = Song.valueOf(in.next());
                        break;
                    case 6:
                        s2 = Song.valueOf(in.next());
                        break;
                    case 7:
                        //TODO: Make u from something
                        break;
                    default:
                        printInfoComplete();
                }
            }


        }
    }


    private static void printInfoComplete() {

    }

}