package prop.domain;

import prop.PropException;

import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * AND driver
 * @author joaquim.motger
 */

public class ANDDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************************");
        System.out.println("** Complex Relation AND");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        ComplexRelation r = null;
        SimpleRelation r1 = null;
        SimpleRelation r2 = null;
        Song s1 = new Song();
        Song s2 = new Song();
        User u = new User();

        int i = -1;
        printInfoComplete();
        while (i!=0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfoComplete();
                    break;
                case 2:
                    r = new AND(r1,r2);
                    break;
                case 3:
                    try{
                        boolean b = r.evaluateSongs(s1, s2);
                        if (b) System.out.print("true\n");
                        else System.out.print("false\n");
                    } catch (PropException pe) {
                        System.err.println(pe.getMessage());
                    }
                    break;
                case 4:/*
                    try{
                        boolean b = r.evaluateUser(u);
                        if (b) System.out.print("true\n");
                        else System.out.print("false\n");
                    } catch (PropException pe) {
                        System.err.println(pe.getMessage());
                    }*/
                    break;
                case 5:
                    String type = in.next();
                    String attribute = in.next();
                    String value = in.next();
                    r1 = new SimpleRelation(attribute, value);
                    break;
                case 6:
                    String type2 = in.next();
                    String attribute2 = in.next();
                    String value2 = in.next();
                    r2 = new SimpleRelation(attribute2, value2);
                    break;
                case 7:
                    String title = in.next();
                    String artist = in.next();
                    String album = in.next();
                    int y = in.nextInt();
                    Genre genre = Genre.getGenreById(in.nextInt());
                    Genre subgenre = Genre.getGenreById(in.nextInt());
                    int duration = in.nextInt();
                    s1 = new Song(title,artist,album,y,genre,subgenre,duration);
                    break;
                case 8:
                    String title2 = in.next();
                    String artist2 = in.next();
                    String album2 = in.next();
                    int y2 = in.nextInt();
                    Genre genre2 = Genre.getGenreById(in.nextInt());
                    Genre subgenre2 = Genre.getGenreById(in.nextInt());
                    int duration2 = in.nextInt();
                    s2 = new Song(title2,artist2,album2,y2,genre2,subgenre2,duration2);
                    break;
                case 9:
                    u = new User(in.next(), Gender.valueOf(in.next()), new GregorianCalendar(in.nextInt(),in.nextInt(),in.nextInt()));
                default:
                    printInfoComplete();
            }
            if (i > 0 && i < 10) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    printInfoComplete()\n");
        System.out.print("2:    r = new AND(r1, r2)\n");
        System.out.print("3:    evaluateSongs(Song s1, Song s2)\n");
        System.out.print("4:    evaluateUser(User u)\n");
        System.out.print("5:    r1 = new Relation(String type, String attribute, String value): SONG/USER attribute value\n");
        System.out.print("6:    r2 = new Relation(String type, String attribute, String value): SONG/USER attribute value\n");
        System.out.print("7:    s1 = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)" +
                ": title artist album YYYY id_genre id_subgenre duration(seconds)\n");
        System.out.print("8:    s2 = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)" +
                ": title artist album YYYY id_genre id_subgenre duration(seconds)\n");
        System.out.print("9:    u = new User(String name, Gender gender, int year, int month, int day, CountryCode country): " +
                "name MALE/FEMALE/OTHER YYYY MM DD country_code\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    printInfoComplete()\n");
    }
}
