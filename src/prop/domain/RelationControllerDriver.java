package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Relation Controller driver
 *
 * @author gerard.casas.saez
 * @author joaquim.motger
 */
public class RelationControllerDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Relation Controller");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        int i = -1;
        RelationController rc = new RelationController();
        SongController sc = new SongController();
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
                    rc.initGraph(sc);
                    break;
                case 3:
                    String title = in.next();
                    String artist = in.next();
                    String album = in.next();
                    int year = in.nextInt();
                    Genre genre = Genre.getGenreById(in.nextInt());
                    Genre subgenre = Genre.getGenreById(in.nextInt());
                    int duration = in.nextInt();
                    try {
                        sc.addSong(title, artist, album, year, genre, subgenre, duration);
                    } catch (Exception e) {
                        System.out.println("e.getMessage()");
                    }
                    break;
                // todo: adapt to Graph
                /*case 4:
                    HashGraph g = rc.getGraph();
                    ArrayList<Song> songs = g.getVertices();
                    for (Song s : songs) {
                        System.out.print("(" + s.getTitle() + "," + s.getArtist() + ") :");
                        ArrayList<Song> adj = g.adjacentVertices(s);
                        for (Song ss : adj) {
                            System.out.print(" (" + ss.getTitle() + "," + ss.getArtist() + ")");
                        }
                        System.out.print("\n");
                    }
                    break;*/
                default:
                    printInfoComplete();
            }
            if (i > 0 && i < 10) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n"
                + "2:   initGraph(SongController sc)\n"
                + "3:   sc.addSong(new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n"
                + "4:   getGraph()\n"
                + "5:   ");
    }

    private static void printInfoBrief() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n");
    }
}
