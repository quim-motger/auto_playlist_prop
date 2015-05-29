package prop.domain;

import prop.PropException;

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
                    rc.initGraph(sc,uc);
                    break;
                case 3:
                    writeGraph(rc.getGraph());
                    break;
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
                    int m = 0;
                    while (m < n) {
                        sb.append(in.next() + " " + in.next() + "\n");
                        ++m;
                    }

                    String p = in.next();
                    try {
                        rc.addRelation(sb.toString(),p,n);
                    } catch (PropException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    rc.playbackRelations(uc);
                    break;
                case 6:
                    String title2 = in.next();
                    String artist2 = in.next();
                    String album2 = in.next();
                    String year2 = in.next();
                    String genre2 = in.next();
                    String subgenre2 = in.next();
                    String duration2 = in.next();
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
                                in.nextInt(),
                                in.nextInt(),
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
                    try {
                        uc.associateListToUser(lc, in.next(), in.next());
                    } catch (PropException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 11:
                    try {
                        uc.playSong(in.next(), in.next(), in.next(), sc);
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 12:
                    try {
                        lc.createRandomList(in.next(), in.nextInt(), sc);
                    } catch (PropException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 13:
                    System.out.println(lc.getListSetString());
                    break;
                case 14:
                    System.out.println(lc.getListString(in.next()));
                    break;
                default:
                    printInfoComplete();
            }
            if (i > 0 && i < 15) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n"
                + "2:   initGraph(SongController sc)\n"
                + "3:   getGraph()\n"
                + "4:   addSongRelation(String simpRel, String exp)\n"
                + "5:   playbackRelations(UserController uc)\n"
                + "SONG CONTROLLER METHODS\n"
                + "6:   sc.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration):" +
                " title artist album YYYY id_genre id_subgenre duration(seconds)\n"
                + "7:   sc.removeSong(String title, String artist): title artist\n"
                + "USER CONTROLLER METHODS\n"
                + "8:   uc.addUser(String name, String gender, int year, int month, int date, String countryCode): name MALE/FEMALE/OTHER YYYY " +
                "MM DD countryCode\n"
                + "9:   uc.removeUser(String name): userName\n"
                + "10:  uc.associateListToUser(ListController lc, int listId, String userName): listId userName\n"
                + "11:  uc.playSong(String title, String artist, String name, SongController sc) : songTitle songArtist userName\n"
                + "LIST CONTROLLER METHODS\n"
                + "12:  lc.createRandomlist(String title, int n, SongController sc): title n\n"
                + "13:  String lc.getListSetString()\n"
                + "14:  String lc.getListString(int id): idList\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n");
    }

    private static void writeGraph(Graph G) {
        Song s;
        for (int i = 0; i < G.numberOfVertices(); ++i) {
            s = (Song) G.getVertexT(i);
            System.out.print(s.getTitle() + " " + s.getArtist() + ":");
            for (Object j : G.adjacentVertices(i)) {
                s = (Song) G.getVertexT((int) j);
                System.out.print(" " + s.getTitle() + " " + s.getArtist());
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
