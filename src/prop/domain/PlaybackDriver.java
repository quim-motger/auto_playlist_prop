package prop.domain;

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
                    playback = new Playback(song, date);
                    break;
                case 3:
                    song = playback.getSong();
                    System.out.println("Title: "+song.getTitle());
                    System.out.println("Artist: "+song.getArtist());
                    System.out.println("Album: "+song.getAlbum());
                    System.out.println("Year: "+song.getYear());
                    System.out.println("Genre: "+song.getGenre().getName());
                    System.out.println("Subgenre: "+song.getSubgenre().getName());
                    System.out.println("Duration: "+song.getDuration());
                    break;
                case 4:
                    date = playback.getDate();
                    System.out.println("Year: "+date.get(Calendar.YEAR));
                    System.out.println("Month: "+date.get(Calendar.MONTH));
                    System.out.println("Day: "+date.get(Calendar.DAY_OF_MONTH));
                    System.out.println("Hour: "+date.get(Calendar.HOUR));
                    System.out.println("Minute: "+date.get(Calendar.MINUTE));
                    System.out.println("Second: "+date.get(Calendar.SECOND));
                    break;
                case 5:
                    playback.setSong(song);
                case 6:
                    playback.setDate(date);
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
                    boolean b = sc.addSong(title,artist,album,year,genre,subgenre,duration);
                    if (!b) System.out.print("Song already exists");
                    break;
                case 10:
                    String title2 = in.next();
                    String artist2 = in.next();
                    boolean b2 = sc.removeSong(title2, artist2);
                    if (!b2) System.out.print("Song doesn't exist\n");
                    break;
                case 11:
                    String title3 = in.next();
                    String artist3 = in.next();
                    song = sc.getSong(title3, artist3);
                    break;
                case 12:
                    int y = in.nextInt();
                    int month = in.nextInt();
                    int day = in.nextInt();
                    int hour = in.nextInt();
                    int minute = in.nextInt();
                    int second = in.nextInt();
                    date.set(y,month,day,hour,minute,second);
                    break;
                default:
                    printInfoComplete();
            }
            if (i != 0) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:   terminate program\n"
                + "1:   printInfoComplete()\n"
                + "2:   Playback(Song song, Calendar date)\n"
                + "3:   Song getSong()\n"
                + "4:   Calendar getDate()\n"
                + "5:   void setSong(Song song)\n"
                + "6:   void setDate(Calendar date)\n"
                + "7:   String toString()\n"
                + "8:   void valueOf(String s)\n"
                + "9:   boolean addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n"
                + "10:  boolean removeSong(String title, String artist)\n"
                + "11:  song = sc.getSong(String title, String artist)\n"
                + "12:  date.set(int year, int month, int day, int hour, int minute, int second)\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n"
                + "1:   printInfoComplete()\n");
    }
}