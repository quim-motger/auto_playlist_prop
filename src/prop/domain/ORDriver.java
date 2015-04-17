package prop.domain;

import java.util.Scanner;

/**
 * ORDriver in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 17/04/15
 */
public class ORDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************************");
        System.out.println("** Complex Relation OR");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        ComplexRelation r = null;
        SimpleRelation r1 = null;
        SimpleRelation r2 = null;
        Song s1 = new Song();
        Song s2 = new Song();
        SongController sc = null;

        int i = -1;
        while (i!=0) {
            printInfo();
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    r = new OR(r1,r2);
                    break;
                case 2:
                    if (r.evaluate(s1,s2)) System.out.print("The songs are related by the specified relation\n");
                    else System.out.print("The songs are not related by the specified relation\n");
                    break;
                case 3:
                    System.out.print("Select the relation to create (1/2):");
                    int rel = in.nextInt();
                    System.out.print("Select the attribute of the relation (title,artist,"
                            +"album,year,genre,subgenre,duration):");
                    String attribute = in.next();
                    System.out.print("Specify the value of the attribute:");
                    String value = in.next();
                    if (rel == 1) r1 = new SimpleRelation(attribute,value);
                    else if (rel == 2) r2 = new SimpleRelation(attribute,value);
                    break;
                case 4:
                    sc = new SongController();
                    break;
                case 5:
                    System.out.print("Add song\n");
                    System.out.print("Title:");
                    String title = in.next();
                    System.out.print("Artist:");
                    String artist = in.next();
                    System.out.print("Album:");
                    String album = in.next();
                    System.out.print("Year:");
                    int y = in.nextInt();
                    System.out.print("Genre:");
                    Genre genre = Genre.getGenreById(in.nextInt());
                    System.out.print("Subgenre:");
                    Genre subgenre = Genre.getGenreById(in.nextInt());
                    System.out.print("Duration:");
                    int duration = in.nextInt();
                    sc.addSong(title,artist,album,y,genre,subgenre,duration);
                    break;
                case 6:
                    System.out.print("Select the song to define (1/2):");
                    int c = in.nextInt();
                    System.out.print("Specify the title of the song:");
                    String title2 = in.next();
                    System.out.print("Specify the artist of the song:");
                    String artist2 = in.next();
                    if (c == 1) s1 = sc.getSong(title2,artist2);
                    else if (c == 2) s2 = sc.getSong(title2,artist2);
                    break;
                default:
                    printInfo();
            }
        }
    }

    private static void printInfo() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    OR(Relation r1, Relation r2)\n");
        System.out.print("2:    evaluate(Song s1, Song s2)\n");
        System.out.print("3:    SimpleRelation(String attribute, String value)\n");
        System.out.print("4:    SongController()\n");
        System.out.print("5:    boolean addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        System.out.print("6:    Song getSong(String title, String artist)\n");
    }
}
