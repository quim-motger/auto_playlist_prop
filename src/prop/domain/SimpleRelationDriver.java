package prop.domain;

import java.util.Scanner;

/**
 * Simple Relation driver
 * @author joaquim.motger
 */
public class SimpleRelationDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Simple Relation Driver");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        Relation r = null;
        Song s1 = null;
        Song s2 = null;

        int i = -1;
        printInfoComplete();
        while (i != 0) {
            i = in.nextInt();
            switch(i) {
                case 0:
                    break;
                case 1:
                    printInfoComplete();
                    break;
                case 2:
                    if (r.evaluate(s1,s2)) System.out.print("true\n");
                    else System.out.print("false\n");
                    break;
                case 3:
                    String attribute = in.next();
                    String value = in.next();
                    r = new SimpleRelation(attribute,value);
                    break;
                case 4:
                    String title = in.next();
                    String artist = in.next();
                    String album = in.next();
                    int y = in.nextInt();
                    Genre genre = Genre.getGenreById(in.nextInt());
                    Genre subgenre = Genre.getGenreById(in.nextInt());
                    int duration = in.nextInt();
                    s1 = new Song(title, artist, album, y, genre, subgenre, duration);
                    break;
                case 5:
                    String title2 = in.next();
                    String artist2 = in.next();
                    String album2 = in.next();
                    int y2 = in.nextInt();
                    Genre genre2 = Genre.getGenreById(in.nextInt());
                    Genre subgenre2 = Genre.getGenreById(in.nextInt());
                    int duration2 = in.nextInt();
                    s1 = new Song(title2, artist2, album2, y2, genre2, subgenre2, duration2);
                    break;
                default:
                    printInfoComplete();
            }
            if (i > 0 && i < 6) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    printInfoComplete()\n");
        System.out.print("2:    evaluate(Song s1, Song s2)\n");
        System.out.print("3:    r = new SimpleRelation(String attribute, String value)\n");
        System.out.print("4:    s1 = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        System.out.print("5:    s2 = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    printInfoComplete()\n");
    }
}
