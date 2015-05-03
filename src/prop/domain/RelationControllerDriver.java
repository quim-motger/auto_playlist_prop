package prop.domain;

import prop.PropException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Relation Controller driver
 *
 * @author gerard.casas.saez
 * @author joaquim.motger
 */
public class RelationControllerDriver {

    private static Song song0 = new Song("title0","artist0","album0",2000,Genre.getGenreById(0),Genre.getGenreById(0),000);
    private static Song song1 = new Song("title0","artist0","album1",2001,Genre.getGenreById(1),Genre.getGenreById(1),111);

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Relation Controller");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        int i = -1;
        RelationController rc = new RelationController();
        SongController sc = new SongController();
        UserController uc = new UserController();
        ListController lc = new ListController();
        Relation r;
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
                case 4:
                    System.out.println("How to introduce relations:");
                    System.out.println("1. Introduce the number of SimpleRelations you are going to work with: n_relations");
                    System.out.println("2. Introduce n_relations sequences describing the relations): attribute value");
                    System.out.println("2. Introduce the boolean expression using the indexs of simple relations considering their order" +
                            "(indicate end with a ';')");
                    System.out.println("    for AND relations:  0 and 1");
                    System.out.println("    for OR relations:   0 or 1");
                    System.out.println("    for NOT relation:   not0");
                    System.out.println("    Example: not0 and 1 or 0 and not2 and 3 or 4 ;");
                    StringBuilder sb = new StringBuilder();
                    int n = in.nextInt();
                    while (n > 0) {
                        sb.append("SONG " + in.next() + " " + in.next() + "\n");
                        --n;
                    }

                    StringBuilder sp = new StringBuilder();
                    String p = in.next();
                    sp.append(p);
                    while (!(p = in.next()).equals(";")) {
                        sp.append(" " + p);
                    }
                    try {
                        rc.addSongRelation(sb.toString(),sp.toString());
                    } catch (PropException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("How to introduce relations:");
                    System.out.println("1. Introduce the number of SimpleRelations you are going to work with: n_relations");
                    System.out.println("2. Introduce n_relations sequences describing the relations): attribute value");
                    System.out.println("2. Introduce the boolean expression using the indexs of simple relations considering their order" +
                            "(indicate end with a ';')");
                    System.out.println("    for AND relations:  0 and 1");
                    System.out.println("    for OR relations:   0 or 1");
                    System.out.println("    for NOT relation:   not0");
                    System.out.println("    Example: not0 and 1 or 0 and not2 and 3 or 4 ;");
                    StringBuilder sb2 = new StringBuilder();
                    int n2 = in.nextInt();
                    while (n2 > 0) {
                        sb2.append("USER " + in.next() + " " + in.next() + "\n");
                        --n2;
                    }

                    StringBuilder sp2 = new StringBuilder();
                    String p2 = in.next();
                    sp2.append(p2);
                    while (!(p2 = in.next()).equals(";")) {
                        sp2.append(" " + p2);
                    }
                    try {
                        rc.addUserRelation(sb2.toString(), sp2.toString(), uc);
                    } catch (PropException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 6:
                    String title2 = in.next();
                    String artist2 = in.next();
                    String album2 = in.next();
                    int year2 = in.nextInt();
                    Genre genre2 = Genre.getGenreById(in.nextInt());
                    Genre subgenre2 = Genre.getGenreById(in.nextInt());
                    int duration2 = in.nextInt();
                    try {
                        sc.addSong(title2, artist2, album2, year2, genre2, subgenre2, duration2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    String title = in.next();
                    String artist = in.next();
                    try {
                        sc.removeSong(title, artist);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        uc.addUser(
                                in.next(),
                                in.next(),
                                in.nextLong(),
                                in.nextInt()
                        );
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    try {
                        uc.removeUser(in.next());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    uc.associateListToUser(lc,in.nextInt(),in.next());
                    break;
                case 11:
                    try {
                        lc.createRandomList(in.next(), in.nextInt(), sc);
                    } catch (PropException e) {
                        System.err.println(e.getMessage());
                    }
                default:
                    printInfoComplete();
            }
            if (i > 0 && i < 13) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n"
                + "2:   initGraph(SongController sc)\n"
                + "3:   getGraph()\n"
                + "4:   addSongRelation(String simpRel, String exp)\n"
                + "5:   addUserRelation(String simpRel, String exp, UserController uc)\n"
                + "SONG CONTROLLER METHODS\n"
                + "6:   sc.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration):" +
                " title artist album YYYY id_genre id_subgenre duration(seconds)\n"
                + "7:   sc.removeSong(String title, String artist): title artist\n"
                + "USER CONTROLLER METHODS\n"
                + "8:   uc.addUser(String name, Gender gender, long birthday, int countryCode):\n"
                + "9:   uc.removeUser(String name): userName\n"
                + "10:  uc.associateListToUser(ListController lc, int listId, String userName): listId userName\n"
                + "LIST CONTROLLER METHODS\n"
                + "11:  lc.createRandomlist(String title, int n, SongController sc): title n\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n");
    }
}
