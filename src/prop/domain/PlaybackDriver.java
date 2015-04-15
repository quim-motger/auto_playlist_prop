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
        Song song = null;
        Calendar date = null;
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
                    song.setTitle(in.next());
                    System.out.print("Artist:");
                    song.setArtist(in.next());
                    System.out.print("Album:");
                    song.setAlbum(in.next());
                    System.out.print("Year:");
                    song.setYear(in.nextInt());
                    System.out.print("Genre:");
                    song.setGenre(Genre.getGenreById(in.nextInt()));
                    System.out.print("Subgenre:");
                    song.setSubgenre(Genre.getGenreById(in.nextInt()));
                    System.out.print("Duration:");
                    song.setDuration(in.nextInt());
                    System.out.print("Date\n");
                    System.out.print("Year:");
                    int year = in.nextInt();
                    System.out.print("Month:");
                    int month = in.nextInt();
                    System.out.print("Day");
                    int day = in.nextInt();
                    System.out.print("Hour");
                    int hour = in.nextInt();
                    System.out.print("Minute");
                    int minute = in.nextInt();
                    System.out.print("Second");
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
                    song.setTitle(in.next());
                    System.out.print("Artist:");
                    song.setArtist(in.next());
                    System.out.print("Album:");
                    song.setAlbum(in.next());
                    System.out.print("Year:");
                    song.setYear(in.nextInt());
                    System.out.print("Genre:");
                    song.setGenre(Genre.getGenreById(in.nextInt()));
                    System.out.print("Subgenre:");
                    song.setSubgenre(Genre.getGenreById(in.nextInt()));
                    System.out.print("Duration:");
                    song.setDuration(in.nextInt());
                    playback.setSong(song);
                case 5:
                    System.out.print("Date\n");
                    System.out.print("Year:");
                    int year2 = in.nextInt();
                    System.out.print("Month:");
                    int month2 = in.nextInt();
                    System.out.print("Day");
                    int day2 = in.nextInt();
                    System.out.print("Hour");
                    int hour2 = in.nextInt();
                    System.out.print("Minute");
                    int minute2 = in.nextInt();
                    System.out.print("Second");
                    int second2 = in.nextInt();
                    date.set(year2,month2,day2,hour2,minute2,second2);
                    playback.setDate(date);
                case 6:
                    String s3 = playback.toString();
                    System.out.println(s3);
                    break;
                case 7:
                    //playback = Playback.valueOf();
                    break;
                case 8:
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
                + "8:   Song(String title,String artist,String album,int year,Genre genre,Genre subgenre,int duration)\n");
    }
}