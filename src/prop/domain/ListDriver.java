package prop.domain;

import prop.PropException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ListDriver in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 14/04/15
 */
public class ListDriver {

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** List");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();

        List l=null;
        Song song = null;
        Scanner in = new Scanner(System.in);
        SongController sc = new SongController();

        int i = -1;
        while (i!=0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfo();
                    break;
                case 2:
                    String title = in.next();
                    l=new List(title);
                    break;
                case 3:
                    System.out.println(l.size());
                    break;
                case 4:
                    System.out.println(l.obtainTitle());
                    break;
                case 5:
                    int pos = in.nextInt();
                    System.out.println(l.obtainSong(pos).toString());
                    break;
                case 6:
                    ArrayList<Song> a = l.obtainSongs();
                    for (Song s : a) {
                        System.out.println(s.toString());
                    }
                    break;
                case 7:
                    String titl, artist;
                    titl = in.next();
                    artist = in.next();
                    System.out.println(l.obtainSongPosition(titl,artist));
                    break;
                case 8:
                    System.out.println(l.obtainTotalTime());
                    break;
                case 9:
                    String titl2, artist2;
                    titl2 = in.next();
                    artist2 = in.next();
                    System.out.println(l.contains(titl2,artist2));
                    break;
                case 10:
                    System.out.println(l.isEmpty());
                    break;
                case 11:
                    String title2 = in.next();
                    l.editTitle(title2);
                    break;
                case 12:
                    l.addSong(song);
                    break;
                case 13:
                    String sTitle = in.next();
                    String sArtist = in.next();
                    l.removeSong(sTitle,sArtist);
                    break;
                case 14:
                    l.empty();
                    break;
                case 15:
                    int i1,i2;
                    i1 = in.nextInt();
                    i2 = in.nextInt();
                    l.swapSongs(i1,i2);
                    break;
                case 16:
                    System.out.println(l.toString());
                    break;
                case 17:
                    try {
                        song = new Song(
                                in.next(),
                                in.next(),
                                in.next(),
                                in.nextInt(),
                                Genre.getGenreById(in.nextInt()),
                                Genre.getGenreById(in.nextInt()),
                                in.nextInt());
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 18:
                    try {
                        song = sc.getSong(in.next(),in.next());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 19:
                    try {
                        sc.addSong(in.next(),
                                in.next(),
                                in.next(),
                                in.next(),
                                in.next(),
                                in.next(),
                                in.next());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        i=0;
                    }
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
        sb.append("2:  List(String listTitle)\n");
        sb.append("3:  int size()\n");
        sb.append("4:  String obtainTitle()\n");
        sb.append("5:  Song obtainSong(int position)\n");
        sb.append("6:  ArrayList<Song> obtainSongs()\n");
        sb.append("7:  int obtainSongPosition(String title, String artist)\n");
        sb.append("8:  int obtainTotalTime()\n");
        sb.append("9: boolean contains(String title, String artist)\n");
        sb.append("10: boolean isEmpty()\n");
        sb.append("11: void editTitle(String title)\n");
        sb.append("12: void addSong(Song song)\n");
        sb.append("13: void removeSong(String title, String artist)\n");
        sb.append("14: void empty()\n");
        sb.append("15: void swapSongs(int index1, int index2)\n");
        sb.append("16: String toString()\n");
        sb.append("17: song = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        sb.append("18: song = sc.getSong(String title, String artist)\n");
        sb.append("19: sc.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

    private static void printInfoBrief() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        System.out.println(sb.toString());

    }
}
