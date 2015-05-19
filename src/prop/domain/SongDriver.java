package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.Scanner;

/**
 * Song Driver.
 * @author oscar.manas
 */
public class SongDriver {

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Song");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();
        System.out.println("WARNING: it is highly recommendable to start with option #2 or #3\n");

        Song song = null;
        String serialized = "";
        String title,artist,album;
        int year,duration,genreId,subgenreId;
        Genre genre,subgenre;
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
                    song = new Song();
                    break;

                case 3:
                    title = in.next();
                    artist = in.next();
                    album = in.next();
                    year = in.nextInt();
                    try {
                        genreId = in.nextInt();
                        if (genreId < 0 || genreId > 131) throw new PropException(ErrorString.UNEXISTING_GENRE);
                        genre = Genre.getGenreById(genreId);
                        subgenreId = in.nextInt();
                        if (subgenreId < 0 || subgenreId > 131) throw new PropException(ErrorString.UNEXISTING_GENRE);
                        subgenre = Genre.getGenreById(subgenreId);
                        duration = in.nextInt();
                        song = new Song(title, artist, album, year, genre, subgenre, duration);
                    }
                    catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println(song.getTitle() + "\n");
                    break;

                case 5:
                    System.out.println(song.getArtist() + "\n");
                    break;
                case 6:
                    System.out.println(song.getAlbum() + "\n");
                    break;
                case 7:
                    System.out.println(song.getYear() + "\n");
                    break;
                case 8:
                    System.out.println(song.getGenre().getName() + "\n");
                    break;
                case 9:
                    System.out.println(song.getSubgenre().getName() + "\n");
                    break;
                case 10:
                    System.out.println(song.getDuration() + "\n");
                    break;
                case 11:
                    title = in.next();
                    song.setTitle(title);
                    break;
                case 12:
                    artist = in.next();
                    song.setArtist(artist);
                    break;
                case 13:
                    album = in.next();
                    song.setAlbum(album);
                    break;
                case 14:
                    year = in.nextInt();
                    song.setYear(year);
                    break;
                case 15:
                    try {
                        genreId = in.nextInt();
                        if (genreId < 0 || genreId > 131) throw new PropException(ErrorString.UNEXISTING_GENRE);
                        song.setGenre(Genre.getGenreById(genreId));
                    }
                    catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 16:
                    try {
                        subgenreId = in.nextInt();
                        if (subgenreId < 0 || subgenreId > 131) throw new PropException(ErrorString.UNEXISTING_GENRE);
                        song.setSubgenre(Genre.getGenreById(subgenreId));
                    }
                    catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 17:
                    duration = in.nextInt();
                    song.setDuration(duration);
                    break;
                case 18:
                    serialized = song.toString();
                    break;
                default:
                    printInfo();
            }
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  Song()\n");
        sb.append("3:  Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        sb.append("4:  String getListTitle()\n");
        sb.append("5:  String getArtist()\n");
        sb.append("6:  String getAlbum()\n");
        sb.append("7:  int getYear()\n");
        sb.append("8:  Genre getGenre()\n");
        sb.append("9:  Genre getSubgenre()\n");
        sb.append("10: int getDuration()\n");
        sb.append("11: String setListTitle(String title)\n");
        sb.append("12: String setArtist(String artist)\n");
        sb.append("13: String setAlbum(String album)\n");
        sb.append("14: int setYear(int year)\n");
        sb.append("15: Genre setGenre(Genre genre)\n");
        sb.append("16: Genre setSubgenre(Genre subgenre)\n");
        sb.append("17: int setDuration(int duration)\n");
        sb.append("18: public String toString()\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

}
