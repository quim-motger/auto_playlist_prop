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
        SongController songController = new SongController();
        Song song;
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
                    String title = in.next();
                    String artist = in.next();
                    String album = in.next();
                    int year = in.nextInt();
                    Genre genre = Genre.getGenreById(in.nextInt());
                    Genre subgenre = Genre.getGenreById(in.nextInt());
                    int duration = in.nextInt();
                    songController.addSong(title,artist,album,year,genre,subgenre,duration);
                    break;
                case 3:
                    String title2 = in.next();
                    String artist2 = in.next();
                    songController.removeSong(title2,artist2);
                    break;
                case 4:
                    String title3 = in.next();
                    String artist3 = in.next();
                    String attribute = in.next();
                    String value = in.next();
                    Pair<String,String> pair = new Pair<>(attribute, value);
                    songController.editSong(title3, artist3, pair);
                    break;
                case 5:
                    System.out.print(songController.getSongSetString());
                    break;
                case 6:
                    String title4 = in.next();
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
                    songController.save(in.next());
                    break;
                case 9:
                    songController.load(in.next());
                    break;
                default:
                    printInfoComplete();
            }
            if (i > 0 && i < 11) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n"
                + "2:   void addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n"
                + "3:   void removeSong(String title, String artist\n"
                + "4:   void editSong(String title, String artist, Pair<String, String> pair)\n"
                + "6:   String getSongSetString()\n"
                + "7:   Song getSong(String title, String artist)\n"
                + "8:   String searchSongs(ArrayList< Pair<String, String> > l)\n"
                + "9:   void save(String path)\n"
                + "10:   void load(String path)\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n");
    }
}
