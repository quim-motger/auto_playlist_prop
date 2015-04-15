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
                    Calendar date = Calendar.getInstance();
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
                    Song s = playback.getSong();
                    System.out.println("Title: "+s.getTitle());
                    System.out.println("Artist: "+s.getArtist());
                    System.out.println("Album: "+s.getAlbum());
                    System.out.println("Year: "+s.getYear());
                    System.out.println("Genre: "+s.getGenre().getName());
                    System.out.println("Subgenre: "+s.getSubgenre().getName());
                    System.out.println("Duration: "+s.getDuration());
                    break;
                case 3:
                    Calendar d = playback.getDate();
                    System.out.println("Year: "+d.get(Calendar.YEAR));
                    System.out.println("Month: "+d.get(Calendar.MONTH));
                    System.out.println("Day: "+d.get(Calendar.DAY_OF_MONTH));
                    System.out.println("Hour: "+d.get(Calendar.HOUR));
                    System.out.println("Minute: "+d.get(Calendar.MINUTE));
                    System.out.println("Second: "+d.get(Calendar.SECOND));
                    break;
                case 4:
                    Song song2 = new Song();
                    System.out.print("Song\n");
                    System.out.print("Title:");
                    song2.setTitle(in.next());
                    System.out.print("Artist:");
                    song2.setArtist(in.next());
                    System.out.print("Album:");
                    song2.setAlbum(in.next());
                    System.out.print("Year:");
                    song2.setYear(in.nextInt());
                    System.out.print("Genre:");
                    song2.setGenre(Genre.getGenreById(in.nextInt()));
                    System.out.print("Subgenre:");
                    song2.setSubgenre(Genre.getGenreById(in.nextInt()));
                    System.out.print("Duration:");
                    song2.setDuration(in.nextInt());
                    playback.setSong(song2);
                case 5:
                    Calendar date2 = Calendar.getInstance();
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
                    date2.set(year2,month2,day2,hour2,minute2,second2);
                    playback.setDate(date2);
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
        StringBuilder sb = new StringBuilder();
        sb.append("0:   terminate program\n");
        sb.append("1:   Playback(Song song, Calendar date)\n");
        sb.append("2:   Song getSong()\n");
        sb.append("3:   Calendar getDate()\n");
        sb.append("4:   void setSong(Song song)\n");
        sb.append("5:   void setDate(Calendar date)\n");
        sb.append("6:   String toString()\n");
        sb.append("7:   void valueOf(String s)\n");
        sb.append("8:   Song(String title,String artist,String album,int year,Genre genre,Genre subgenre,int duration)\n");
        System.out.print(sb.toString());
    }
}