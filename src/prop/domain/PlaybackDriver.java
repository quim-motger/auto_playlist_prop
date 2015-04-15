package prop.domain;

import java.util.Scanner;
import java.util.Calendar;

/**
 * Playback driver
 * @author joaquim.motger
 */

public class PlaybackDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Playback");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        Playback playback = null;
        Song song;
        Calendar date;
        SongController songController = null;
        String s = "";
        int i = -1;
        while (i != 0) {
            printInfo();
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    System.out.print("Song\n");
                    System.out.print("Title:");
                    String title3 = in.next();
                    System.out.print("Artist:");
                    String artist3 = in.next();
                    song = songController.getSong(title3,artist3);
                    date = Calendar.getInstance();
                    System.out.print("Date\n");
                    System.out.print("Year:");
                    int year = in.nextInt();
                    System.out.print("Month:");
                    int month = in.nextInt();
                    System.out.print("Day:");
                    int day = in.nextInt();
                    System.out.print("Hour:");
                    int hour = in.nextInt();
                    System.out.print("Minute:");
                    int minute = in.nextInt();
                    System.out.print("Second:");
                    int second = in.nextInt();
                    date.set(year,month,day,hour,minute,second);
                    playback = new Playback(song, date);
                    break;
                case 2:
                    song = playback.getSong();
                    System.out.println("Title: "+song.getTitle());
                    System.out.println("Artist: "+song.getArtist());
                    System.out.println("Album: "+song.getAlbum());
                    System.out.println("Year: "+song.getYear());
                    System.out.println("Genre: "+song.getGenre().getName());
                    System.out.println("Subgenre: "+song.getSubgenre().getName());
                    System.out.println("Duration: "+song.getDuration());
                    break;
                case 3:
                    date = playback.getDate();
                    System.out.println("Year: "+date.get(Calendar.YEAR));
                    System.out.println("Month: "+date.get(Calendar.MONTH));
                    System.out.println("Day: "+date.get(Calendar.DAY_OF_MONTH));
                    System.out.println("Hour: "+date.get(Calendar.HOUR));
                    System.out.println("Minute: "+date.get(Calendar.MINUTE));
                    System.out.println("Second: "+date.get(Calendar.SECOND));
                    break;
                case 4:
                    System.out.print("Song\n");
                    System.out.print("Title:");
                    String title4 = in.next();
                    System.out.print("Artist:");
                    String artist4 = in.next();
                    song = songController.getSong(title4,artist4);
                    playback.setSong(song);
                case 5:
                    date = Calendar.getInstance();
                    System.out.print("Date\n");
                    System.out.print("Year:");
                    int year2 = in.nextInt();
                    System.out.print("Month:");
                    int month2 = in.nextInt();
                    System.out.print("Day:");
                    int day2 = in.nextInt();
                    System.out.print("Hour:");
                    int hour2 = in.nextInt();
                    System.out.print("Minute:");
                    int minute2 = in.nextInt();
                    System.out.print("Second:");
                    int second2 = in.nextInt();
                    date.set(year2,month2,day2,hour2,minute2,second2);
                    playback.setDate(date);
                case 6:
                    s = playback.toString();
                    System.out.println(s);
                    break;
                case 7:
                    playback = Playback.valueOf(s, songController);
                    break;
                case 8:
                    songController = new SongController();
                    break;
                case 9:
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
                    boolean b = songController.addSong(title,artist,album,y,genre,subgenre,duration);
                    if (b) System.out.print("Song added\n");
                    else System.out.print("Song already exists\n");
                    break;
                case 10:
                    System.out.print("Remove song\n");
                    System.out.print("Title:");
                    String title2 = in.next();
                    System.out.print("Artist:");
                    String artist2 = in.next();
                    boolean b2 = songController.removeSong(title2, artist2);
                    if (b2) System.out.print("Song removed\n");
                    else System.out.print("Song doesn't exist\n");
                    break;
                default:
                    printInfo();
            }
        }
    }

    private static void printInfo() {
        System.out.print("0:   terminate program\n"
                + "1:   Playback(Song song, Calendar date)\n"
                + "2:   Song getSong()\n"
                + "3:   Calendar getDate()\n"
                + "4:   void setSong(Song song)\n"
                + "5:   void setDate(Calendar date)\n"
                + "6:   String toString()\n"
                + "7:   void valueOf(String s)\n"
                + "8:   SongController()\n"
                + "9:   boolean addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n"
                + "10:  boolean removeSong(String title, String artist)\n");
    }
}