package prop.domain;

import prop.PropException;

import java.util.Scanner;
import java.util.Calendar;

/**
 * Playback driver
 * @author joaquim.motger
 */

public class PlaybackDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************************");
        System.out.println("** Playback");
        System.out.println("**********************************************************");
        System.out.println("WARNING: you need to add songs to the Song Controller first in order to create Playbacks");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        Playback playback = null;
        Song song = null;
        String s = null;
        Calendar date = Calendar.getInstance();
        SongController sc = new SongController();
        int i = -1;
        printInfoComplete();
        while (i != 0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfoComplete();
                    break;
                case 2:
                    String title3 = in.next();
                    String artist3 = in.next();
                    song = sc.getSong(title3, artist3);
                    int y = in.nextInt();
                    int month = in.nextInt();
                    int day = in.nextInt();
                    int hour = in.nextInt();
                    int minute = in.nextInt();
                    int second = in.nextInt();
                    date.set(y,month,day,hour,minute,second);
                    playback = new Playback(song, date);
                    break;
                case 3:
                    song = playback.getSong();
                    System.out.println(song.getTitle());
                    System.out.println(song.getArtist());
                    System.out.println(song.getAlbum());
                    System.out.println(song.getYear());
                    System.out.println(song.getGenre().getName());
                    System.out.println(song.getSubgenre().getName());
                    System.out.println(song.getDuration());
                    break;
                case 4:
                    date = playback.getDate();
                    System.out.println(date.get(Calendar.YEAR));
                    System.out.println(date.get(Calendar.MONTH));
                    System.out.println(date.get(Calendar.DAY_OF_MONTH));
                    System.out.println(date.get(Calendar.HOUR));
                    System.out.println(date.get(Calendar.MINUTE));
                    System.out.println(date.get(Calendar.SECOND));
                    break;
                case 5:
                    try {
                        playback.setSong(song);
                    } catch (Exception pe) {
                        System.err.println(pe.getMessage());
                    }
                    break;
                case 6:
                    try {
                        playback.setDate(date);
                    } catch (Exception pe) {
                        System.err.println(pe.getMessage());
                    }
                    break;
                case 7:
                    s = playback.toString();
                    System.out.print(s);
                    break;
                case 8:
                    playback = Playback.valueOf(s, sc);
                    break;
                case 9:
                    String title = in.next();
                    String artist = in.next();
                    String album = in.next();
                    int year = in.nextInt();
                    Genre genre = Genre.getGenreById(in.nextInt());
                    Genre subgenre = Genre.getGenreById(in.nextInt());
                    int duration = in.nextInt();
                    sc.addSong(title,artist,album,year,genre,subgenre,duration);
                    break;
                case 10:
                    String title2 = in.next();
                    String artist2 = in.next();
                    sc.removeSong(title2, artist2);
                    break;
                default:
                    printInfoComplete();
            }
            if (i > 1 && i < 11) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n"
                + "2:   Playback(Song song, Calendar date): title artist YYYY MM DD hh mm ss\n"
                + "3:   Song getSong()\n"
                + "4:   Calendar getDate()\n"
                + "5:   void setSong(Song song): title artist\n"
                + "6:   void setDate(Calendar date): YYYY MM DD hh mm ss\n"
                + "7:   String toString()\n"
                + "8:   void valueOf(String s)\n"
                + "9:   sc.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration): title " +
                "artist album YYYY id_genre id_subgenre duration(seconds)\n"
                + "10:  sc.removeSong(String title, String artist): title artist\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n"
                + "1:    printInfoComplete()\n");
    }
}