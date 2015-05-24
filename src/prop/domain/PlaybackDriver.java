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
        Song song;
        String s = "";
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
                    System.out.print(song.getTitle());
                    System.out.print(" " + song.getArtist());
                    System.out.print(" " + song.getAlbum());
                    System.out.print(" " + song.getYear());
                    System.out.print(" " + song.getGenre().getName());
                    System.out.print(" " + song.getSubgenre().getName());
                    System.out.print(" " + song.getDuration() + "\n");
                    break;
                case 4:
                    date = playback.getDate();
                    System.out.print(date.get(Calendar.YEAR));
                    System.out.print(" " + date.get(Calendar.MONTH));
                    System.out.print(" " + date.get(Calendar.DAY_OF_MONTH));
                    System.out.print(" " + date.get(Calendar.HOUR_OF_DAY));
                    System.out.print(" " + date.get(Calendar.MINUTE));
                    System.out.print(" " + date.get(Calendar.SECOND) + "\n");
                    break;
                case 5:
                    try {
                        playback.setSong(sc.getSong(in.next(), in.next()));
                    } catch (Exception pe) {
                        System.err.println(pe.getMessage());
                    }
                    break;
                case 6:
                    try {
                        int y2 = in.nextInt();
                        int month2 = in.nextInt();
                        int day2 = in.nextInt();
                        int hour2 = in.nextInt();
                        int minute2 = in.nextInt();
                        int second2 = in.nextInt();
                        date.set(y2,month2,day2,hour2,minute2,second2);
                        playback.setDate(date);
                    } catch (Exception pe) {
                        System.err.println(pe.getMessage());
                    }
                    break;
                case 7:
                    Song s1 = sc.getSong(in.next(), in.next());
                    Calendar d1 = Calendar.getInstance();
                    d1.set(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
                    Playback p1 = new Playback(s1, d1);
                    if (playback.compareTo(p1) > 0) System.out.println("this playback is posterior than new");
                    else if (playback.compareTo(p1) < 0) System.out.println("this playback is anterior than new");
                    else System.out.println("this playback and new have equal dates");
                    break;
                case 8:
                    s = playback.toString();
                    System.out.print(s + "\n");
                    break;
                case 9:
                    playback = Playback.valueOf(s, sc);
                    break;
                case 10:
                    String title = in.next();
                    String artist = in.next();
                    String album = in.next();
                    String year = in.next();
                    String genre = in.next();
                    String subgenre = in.next();
                    String duration = in.next();
                    sc.addSong(title,artist,album,year,genre,subgenre,duration);
                    break;
                case 11:
                    String title2 = in.next();
                    String artist2 = in.next();
                    sc.removeSong(title2, artist2);
                    break;
                default:
                    printInfoComplete();
            }
            System.out.print("\n");
            if (i > 1 && i < 12) printInfoBrief();
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
                + "7:   compareTo(Playback p): title artist YYYY MM DD hh mm ss\n"
                + "8:   String toString()\n"
                + "9:   void valueOf(String s)\n"
                + "10:   sc.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration): title " +
                "artist album YYYY id_genre id_subgenre duration(seconds)\n"
                + "11:  sc.removeSong(String title, String artist): title artist\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n"
                + "1:    printInfoComplete()\n");
    }
}