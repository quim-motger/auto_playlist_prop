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
        int i = -1;
        while (i != 0) {
            printInfo();
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    Song song = new Song();
                    System.out.print("Song");
                    System.out.print("Title:");
                    song.setTitle(in.next());
                    System.out.print("Artist:");
                    song.setAlbum(in.next());
                    System.out.print("Year:");
                    song.setYear(in.nextInt());
                    System.out.print("Genre:");
                    song.setGenre(Genre.getGenreById(in.nextInt()));
                    System.out.print("Subgenre:");
                    song.setSubgenre(Genre.getGenreById(in.nextInt()));
                    System.out.print("Duration:");
                    song.setDuration(in.nextInt());
                    Calendar calendar = Calendar.getInstance();
                    System.out.print("Duration:");
                    int year = in.nextInt();
                    int month = in.nextInt();
                    int day = in.nextInt();
                    int hour = in.nextInt();
                    int minute = in.nextInt();
                    int second = in.nextInt();
                    calendar.set(year,month,day,hour,minute,second);
                    break;
                case 2:
                    Song s = playback.getSong();
                    System.out.println(s.getTitle());
                    System.out.println(s.getArtist());
                    break;
                case 3:
                    Calendar d = playback.getDate();
                    System.out.println(d.get(Calendar.YEAR));
                    System.out.println(d.get(Calendar.MONTH));
                    System.out.println(d.get(Calendar.DAY_OF_MONTH));
                    System.out.println(d.get(Calendar.HOUR));
                    System.out.println(d.get(Calendar.MINUTE));
                    System.out.println(d.get(Calendar.SECOND));
                    break;
                case 4:
                    String title = in.next();
                    String artist = in.next();
                    String album = in.next();
                    int y = in.nextInt();
                    int id = in.nextInt();
                    Genre genre = Genre.getGenreById(id);
                    int duration = in.nextInt();
                    Song s2 = new Song(title, artist, album, y, genre, genre, duration);
                    playback.setSong(s2);
                case 5:
                    int year2 = in.nextInt();
                    int month2 = in.nextInt();
                    int day2 = in.nextInt();
                    int hour2 = in.nextInt();
                    int minute2 = in.nextInt();
                    int second2 = in.nextInt();
                    Calendar d2 = Calendar.getInstance();
                    d2.set(year2,month2,day2,hour2,minute2,second2);
                    playback.setDate(d2);
                case 6:
                    String s3 = playback.toString();
                    System.out.println(s3);
            }
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:   terminate program\n");
        sb.append("1:   Playback(Song song, Calendar date)");
        sb.append("2:   Song getSong()\n");
        sb.append("3:   Calendar getDate()\n");
        sb.append("4:   void setSong(Song song)\n");
        sb.append("5:   void setDate(Calendar date)\n");
        sb.append("6:   String toString()\n");
        sb.append("7:   void valueOf(String s)\n");
        System.out.print(sb.toString());
    }
}