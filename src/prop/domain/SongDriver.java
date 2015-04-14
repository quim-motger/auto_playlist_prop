package prop.domain;

import java.util.Scanner;

/**
 * Song Driver
 * @author oscar.manas
 */
public class SongDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Song");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();

        Song song = new Song();
        String serialized = "";
        Scanner in = new Scanner(System.in);
        int i = -1;
        while(i != 0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfo();
                    break;
                case 2:
                    System.out.println(song.getTitle());
                    break;
                case 3:
                    System.out.println(song.getArtist());
                    break;
                case 4:
                    System.out.println(song.getYear());
                    break;
                case 5:
                    System.out.println(song.getAlbum());
                    break;
                case 6:
                    System.out.println(song.getGenre().getName());
                    break;
                case 7:
                    System.out.println(song.getSubgenre().getName());
                    break;
                case 8:
                    System.out.println(song.getDuration());
                    break;
                case 9:
                    String title = in.next();
                    song.setTitle(title);
                    break;
                case 10:
                    String artist = in.next();
                    song.setArtist(artist);
                    break;
                case 11:
                    int year = in.nextInt();
                    song.setYear(year);
                    break;
                case 12:
                    String album = in.next();
                    song.setAlbum(album);
                    break;
                case 13:
                    int genre = in.nextInt();
                    song.setGenre(Genre.getGenreById(genre));
                    break;
                case 14:
                    int subgenre = in.nextInt();
                    song.setGenre(Genre.getGenreById(subgenre));
                    break;
                case 15:
                    int duration = in.nextInt();
                    song.setDuration(duration);
                    break;
                case 16:
                    serialized = song.toString();
                    break;
                case 17:
                    Song s = Song.parse(serialized);
                    System.out.println(s.getTitle() + " " + s.getArtist() + " " + s.getAlbum() + " " + s.getYear() +
                    " " + s.getGenre().getName() + " " + s.getSubgenre().getName() + " " + s.getDuration());
                    break;
            }
        }

    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  String getTitle()\n");
        sb.append("3:  String getArtist()\n");
        sb.append("4:  int getYear()\n");
        sb.append("5:  String getAlbum()\n");
        sb.append("6:  Genre getGenre()\n");
        sb.append("7:  Genre getSubgenre()\n");
        sb.append("8:  int getDuration()\n");
        sb.append("9:  String setTitle(String title)\n");
        sb.append("10: String setArtist(String artist)\n");
        sb.append("11: int setYear(int year)\n");
        sb.append("12: String setAlbum(String album)\n");
        sb.append("13: Genre setGenre(Genre genre)\n");
        sb.append("14: Genre setSubgenre(Genre subgenre)\n");
        sb.append("15: int setDuration(int duration)\n");
        sb.append("16: public String toString()\n");
        sb.append("17: public static Song parse(String s)\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }
}
