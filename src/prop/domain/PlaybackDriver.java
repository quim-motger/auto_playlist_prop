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
        Playback playback = new Playback();
        int i = -1;
        while (i != 0) {
            printInfo();
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    Song s = playback.getSong();
                    System.out.println(s.getTitle());
                    System.out.println(s.getArtist());
                    break;
                case 2:
                    Calendar d = playback.getDate();
                    System.out.println(d.get(Calendar.YEAR));
                    System.out.println(d.get(Calendar.MONTH));
                    System.out.println(d.get(Calendar.DAY_OF_MONTH));
                    System.out.println(d.get(Calendar.HOUR));
                    System.out.println(d.get(Calendar.MINUTE));
                    System.out.println(d.get(Calendar.SECOND));
                    break;
                case 3:
                    String title = in.next();
                    String artist = in.next();
                    String album = in.next();
                    int y = in.nextInt();
                    int id = in.nextInt();
                    String gen = in.next();
                    Genre genre = new Genre(id,gen);
                    int duration = in.nextInt();
                    Song s = new Song(title, artist, album, y, genre, duration);
                    playback.setSong(s);
                case 4:
                    int year = in.nextInt();
                    int month = in.nextInt();
                    int day = in.nextInt();
                    int hour = in.nextInt();
                    int minute = in.nextInt();
                    int second = in.nextInt();
                    Calendar d2;
                    d2.set(year,month,day,hour,minute,second);
                    playback.setDate(d2);
                case 5:
                    String s = playback.toString();
                    System.out.println(s);
            }
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:   terminate program\n");
        sb.append("1:   Song getSong()\n");
        sb.append("2:   Calendar getDate()\n");
        sb.append("3:   void setSong(Song song)\n");
        sb.append("4:   void setDate(Calendar date)\n");
        sb.append("5:   String toString()\n");
        sb.append("6:   void valueOf(String s)");
        sb.append("\n");
        System.out.print(sb.toString());
    }
}