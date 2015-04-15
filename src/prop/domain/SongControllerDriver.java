package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * SongSet Controller Driver
 * @author joaquim.motger
 */
public class SongControllerDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************************");
        System.out.println("** Song Controller");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        int i = -1;
        SongController songController = null;
        Song song;
        while (i != 0) {
            printInfo();
            i = in.nextInt();
            switch(i) {
                case 0:
                    break;
                case 1:
                    songController = new SongController();
                    break;
                case 2:
                    System.out.print("Add song\n");
                    System.out.print("Title:");
                    String title = in.next();
                    System.out.print("Artist:");
                    String artist = in.next();
                    System.out.print("Album:");
                    String album = in.next();
                    System.out.print("Year:");
                    int year = in.nextInt();
                    System.out.print("Genre:");
                    Genre genre = Genre.getGenreById(in.nextInt());
                    System.out.print("Subgenre:");
                    Genre subgenre = Genre.getGenreById(in.nextInt());
                    System.out.print("Duration:");
                    int duration = in.nextInt();
                    boolean b = songController.addSong(title,artist,album,year,genre,subgenre,duration);
                    if (b) System.out.print("Song added\n");
                    else System.out.print("Song already exists\n");
                    break;
                case 3:
                    System.out.print("Remove song\n");
                    System.out.print("Title:");
                    String title2 = in.next();
                    System.out.print("Artist:");
                    String artist2 = in.next();
                    boolean b2 = songController.removeSong(title2,artist2);
                    if (b2) System.out.print("Song removed\n");
                    else System.out.print("Song doesn't exist\n");
                    break;
                case 4:
                    System.out.print("Edit song\n");
                    System.out.print("Title:");
                    String title3 = in.next();
                    System.out.print("Artist:");
                    String artist3 = in.next();
                    System.out.print("Atribute to modify:");
                    String s1 = in.next();
                    System.out.print("New value:");
                    String s2 = in.next();
                    Pair<String,String> pair = new Pair<>(s1, s2);
                    songController.editSong(title3, artist3, pair);
                    break;
                case 5:
                    System.out.print(songController.getSongSetString());
                    break;
                case 6:
                    System.out.print("Get song\n");
                    System.out.print("Title:");
                    String title4 = in.next();
                    System.out.print("Artist");
                    String artist4 = in.next();
                    song = songController.getSong(title4,artist4);
                    System.out.print(song.toString());
                    break;
                case 7:
                    System.out.print("Search songs\n");
                    System.out.print("Introduce pairs of attributes and values to search songs; attribute 'finish' when finished\n");
                    System.out.print("Attribute:");
                    String c1 = in.next();
                    System.out.print("Value:");
                    String c2 = in.next();
                    ArrayList< Pair<String,String>> l = new ArrayList<>();
                    while (!c1.equals("finish")) {
                        Pair<String,String> p = new Pair<>(c1,c2);
                        l.add(p);
                        System.out.print("Attribute:");
                        c1 = in.next();
                        System.out.print("Value:");
                        c2 = in.next();
                    }
                    System.out.print(songController.searchSongs(l));
                    break;
                case 8:
                    System.out.print("Save\n");
                    System.out.print("Path:");
                    String path = in.next();
                    songController.save(path);
                    break;
                case 9:
                    System.out.print("Load\n");
                    System.out.print("Path:");
                    String path2 = in.next();
                    songController.load(path2);
                    break;
                default:
                    printInfo();
            }
        }
    }

    private static void printInfo() {
        System.out.print("0:   terminate program\n"
                + "1:   SongController()\n"
                + "2:   boolean addSong(String title, String artist, String album, int year, Genre genre, "
                + "Genre subgenre, int duration)\n"
                + "3:   boolean removeSong(String title, String artist\n"
                + "4:   void editSong(String title, String artist, Pair<String, String> pair)\n"
                + "5:   String getSongSetString()\n"
                + "6:   Song getSong(String title, String artist)\n"
                + "7:   String searchSongs(ArrayList< Pair<String, String> > l)\n"
                + "8:   void save(String path)\n"
                + "9:   void load(String path)\n");
    }
}