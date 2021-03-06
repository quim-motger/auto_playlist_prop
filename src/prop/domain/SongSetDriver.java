package prop.domain;

import prop.PropException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Song Set Driver.
 * @author oscar.manas
 */
public class SongSetDriver {

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** SongSet");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();
        System.out.println("WARNING: it is highly recommendable to start with option #2\n");

        SongSet songSet = null;
        String serialized = "";
        String title,artist,album;
        int year,duration;
        Genre genre,subgenre;
        int n;
        ArrayList<Song> songs;
        Scanner in = new Scanner(System.in);

        int i = -1;
        while (i != 0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfo();
                    break;
                case 2:
                    songSet = new SongSet();
                    break;
                case 3:
                    System.out.println(songSet.size() + "\n");
                    break;
                case 4:
                    songs = songSet.getSongSet();
                    for (Song s : songs)
                        printSong(s);
                    System.out.print("\n");
                    break;
                case 5:
                        title = in.next();
                        artist = in.next();
                    try {
                        Song s = songSet.getSong(title, artist);
                        printSong(s);
                        System.out.print("\n");
                    }
                    catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    ArrayList<Pair<String, String>> ids = new ArrayList<>();
                    n = in.nextInt();
                    for (int j = 0; j < n; ++j)
                        ids.add(new Pair<>(in.next(), in.next()));
                    try {
                        songs = songSet.getSongs(ids);
                        for (Song s : songs)
                            printSong(s);
                        System.out.print("\n");
                    }
                    catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    title = in.next();
                    artist = in.next();
                    album = in.next();
                    year = in.nextInt();
                    try {
                        genre = Genre.getGenreById(in.nextInt());
                        subgenre = Genre.getGenreById(in.nextInt());
                        duration = in.nextInt();
                        Song song = new Song(title, artist, album, year, genre, subgenre, duration);
                        songSet.addSong(song);
                    }
                    catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    title = in.next();
                    artist = in.next();
                    try {
                        songSet.removeSong(title, artist);
                    }
                    catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    title = in.next();
                    artist = in.next();
                    if (songSet.contains(title,artist))
                        System.out.println("Yes");
                    else
                        System.out.println("No");
                    System.out.print("\n");
                    break;
                case 10:
                    System.out.println(songSet.getTotalDuration() + "\n");
                    break;
                case 11:
                    ArrayList<Pair<String, String>> conditions = new ArrayList<>();
                    n = in.nextInt();
                    for (int j = 0; j < n; ++j)
                        conditions.add(new Pair<>(in.next(), in.next()));
                    try {
                        songs = songSet.searchSongs(conditions);
                        for (Song s : songs)
                            printSong(s);
                        System.out.print("\n");
                    }
                    catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 12:
                    serialized = songSet.toString();
                    System.out.println(serialized);
                    System.out.print("\n");
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
        sb.append("2:  SongSet()\n");
        sb.append("3:  int size()\n");
        sb.append("4:  ArrayList<Song> getSongSet()\n");
        sb.append("5:  Song getSong(String title, String artist)\n");
        sb.append("6:  ArrayList<Song> getSongs(ArrayList<Pair<String,String>> ids)\n");
        sb.append("7:  void addSong(String title, String artist, String album, int year, int genre, int subgenre, int duration)\n");
        sb.append("8:  void removeSong(String title, String artist)\n");
        sb.append("9: boolean contains(String title, String artist)\n");
        sb.append("10: int getTotalDuration()\n");
        sb.append("11: ArrayList<Song> searchSongs(ArrayList<Pair<String,String>> conditions)\n");
        sb.append("12: String toString()\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

    private static void printSong(Song s) {
        System.out.println(s.getTitle() + " " + s.getArtist() + " " + s.getAlbum() + " " + s.getYear() +
                " " + s.getGenre().getName() + " " + s.getSubgenre().getName() + " " + s.getDuration());
    }

}
