package prop.domain;

import java.util.Scanner;

/**
 * Simple Relation driver
 * @author joaquim.motger
 */
public class SimpleRelationDriver {
    public static void main(String[] args){
        System.out.println("**********************************************************");
        System.out.println("** Complex Relation AND");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        SimpleRelation r = null;
        Song s1 = null;
        Song s2 = null;
        SongController sc = null;

        int i = -1;
        while (i != 0) {
            printInfo();
            i = in.nextInt();
            switch(i) {
                case 0:
                    break;
                case 1:
                    if (r.evaluate(s1,s2)) System.out.print("The songs are related by the specified relation\n");
                    else System.out.print("The songs are not related by the specified relation\n");
                    break;
                case 2:
                    System.out.print("Select the attribute of the relation (title,artist,"
                            +"album,year,genre,subgenre,duration):");
                    String attribute = in.next();
                    System.out.print("Specify the value of the attribute:");
                    String value = in.next();
                    r = new SimpleRelation(attribute,value);
                    break;
                case 3:
                    sc = new SongController();
                    break;
                case 4:
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
                    boolean b = sc.addSong(title,artist,album,y,genre,subgenre,duration);
                    if (b) System.out.print("Song added\n");
                    else System.out.print("Song already exists\n");
                    break;
                case 5:
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
        System.out.print("1:    evaluate(Song s1, Song s2)\n");
        System.out.print("2:    SimpleRelation(String attribute, String value)\n");
        System.out.print("3:    SongController()\n");
        System.out.print("4:    boolean addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        System.out.print("5:    Song getSong(String title, String artist)\n");
    }
}
