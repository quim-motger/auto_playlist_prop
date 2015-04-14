package prop.domain;

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
        Scanner in = new Scanner(System.in);
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
                    System.out.println(l.obtainId());
                    break;
                case 6:
                    int pos = in.nextInt();
                    System.out.println(l.obtainSong(pos).toString());
                    break;
                case 7:
                    ArrayList<Song> a = l.obtainSongs();
                    for (Song song : a) {
                        System.out.println(song.toString());
                    }
                    break;
                case 8:
                    String titl, artist;
                    titl = in.next();
                    artist = in.next();
                    System.out.println(l.obtainSongPosition(titl,artist));
                    break;
                case 9:
                    System.out.println(l.obtainTotalTime());
                    break;
                case 10:
                    String titl2, artist2;
                    titl2 = in.next();
                    artist2 = in.next();
                    System.out.println(l.contains(titl2,artist2));
                    break;
                case 11:
                    System.out.println(l.isEmpty());
                    break;
                case 12:
                    String title2 = in.next();
                    l.editTitle(title2);
                    break;
                case 13:
                    int id = in.nextInt();
                    l.editId(id);
                    break;
                case 14:
                    Song song = new Song();
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
                    l.addSong(song);
                    break;
                case 15:
                    String sTitle = in.next();
                    String sArtist = in.next();
                    l.removeSong(sTitle,sArtist);
                    break;
                case 16:
                    l.empty();
                    break;
                case 17:
                    int i1,i2;
                    i1 = in.nextInt();
                    i2 = in.nextInt();
                    l.swapSongs(i1,i2);
                    break;
                case 18:
                    System.out.println(l.toString());                    
            }
            System.out.println("END OF CONTENT");
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  List(String listTitle)\n");
        sb.append("3:  int size()\n");
        sb.append("4:  String obtainTitle()\n");
        sb.append("5:  int obtainId()\n");
        sb.append("6:  Song obtainSong(int position)\n");
        sb.append("7:  ArrayList<Song> obtainSongs()\n");
        sb.append("8:  int obtainSongPosition(String title, String artist)\n");
        sb.append("9: int obtainTotalTime()\n");
        sb.append("10: boolean contains(String title, String artist)\n");
        sb.append("11: boolean isEmpty()\n");
        sb.append("12: void editTitle(String title)\n");
        sb.append("13: void editId(int listId)\n");
        sb.append("14: void addSong(Song song)");
        sb.append("15: void removeSong(String title, String artist)");
        sb.append("16: void empty()");
        sb.append("17: void swapSongs(int index1, int index2)");
        sb.append("18: String toString()");
        sb.append("\n");
        System.out.print(sb.toString());
    }
}