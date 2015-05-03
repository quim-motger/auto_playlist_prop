package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * SongSet Controller Driver
 * @author joaquim.motger
 */
public class SongControllerDriver {
    public static void main(String[] args) {
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
                    songController = new SongController();
                case 3:
                    System.out.print(songController.size() + "\n");
                    break;
                case 4:
                    SongSet ss = songController.getSongSet();
                    for (Song s: ss.getSongSet()) {
                        System.out.print(s.getTitle() + " " + s.getArtist() + " " + s.getAlbum() + " " + s.getYear() +
                        " " + s.getGenre().getName() + " " + s.getSubgenre().getName() + " " + s.getDuration() + "\n");
                    }
                    break;
                case 5:
                    String title = in.next();
                    String artist = in.next();
                    String album = in.next();
                    int year = in.nextInt();
                    Genre genre = Genre.getGenreById(in.nextInt());
                    Genre subgenre = Genre.getGenreById(in.nextInt());
                    int duration = in.nextInt();
                    try {
                        songController.addSong(title,artist,album,year,genre,subgenre,duration);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    String title2 = in.next();
                    String artist2 = in.next();
                    try {
                        songController.removeSong(title2,artist2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    String title3 = in.next();
                    String artist3 = in.next();
                    String attribute = in.next();
                    String value = in.next();
                    Pair<String,String> pair = new Pair<>(attribute, value);
                    try {
                        songController.editSong(title3, artist3, pair);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    System.out.print(songController.getSongSetString());
                    break;
                case 9:
                    String title4 = in.next();
                    String artist4 = in.next();
                    try {
                        song = songController.getSong(title4,artist4);
                        System.out.print(song.getTitle() + " " + song.getArtist() + " " + song.getAlbum() + " " + song.getYear() +
                                " " + song.getGenre().getName() + " " + song.getSubgenre().getName() + " " + song.getDuration() + "\n");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    try {
                        song = songController.getSong(in.nextInt());
                        System.out.print(song.getTitle() + " " + song.getArtist() + " " + song.getAlbum() + " " + song.getYear() +
                                " " + song.getGenre().getName() + " " + song.getSubgenre().getName() + " " + song.getDuration() + "\n");

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 11:
                    int n = in.nextInt();
                    ArrayList< Pair<String,String>> l = new ArrayList<>();
                    while (n > 0) {
                        Pair<String,String> p = new Pair<>(in.next(),in.next());
                        l.add(p);
                        --n;
                    }
                    try {
                        String p = songController.searchSongs(l);
                        System.out.print(p);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 12:
                    System.out.print(songController.listGenres());
                case 13:
                    songController.save(in.next());
                    break;
                case 14:
                    songController.load(in.next());
                    break;
                default:
                    printInfoComplete();
            }
            if (i > 1 && i < 15) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n"
                + "2:   new songController()\n"
                + "3:   int size()\n"
                + "4:   SongSet getSongSet()\n"
                + "5:   void addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration) " +
                ": title artist album YYYY id_genre id_subgenre duration(seconds)\n"
                + "6:   void removeSong(String title, String artist): title artist\n"
                + "7:   void editSong(String title, String artist, Pair<String, String> pair): title artist attribute new_value\n"
                + "8:   String getSongSetString()\n"
                + "9:   Song getSong(String title, String artist): title artist\n"
                + "10:   Song getSong(int i): index\n"
                + "11:   String searchSongs(ArrayList< Pair<String, String> > l): n_criteria [attribute, value]\n"
                + "12:   listGenres()\n"
                + "13:   void save(String path): path\n"
                + "14:   void load(String path): path\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n");
    }
}
