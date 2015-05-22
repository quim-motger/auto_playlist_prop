package prop.domain;

import prop.PropException;

import java.io.IOException;
import java.util.Scanner;

/**
 * List Controller Driver.
 * @author oscar.manas
 */
public class ListControllerDriver {

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** ListController");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();
        System.out.println("WARNING: it is highly recommendable to start with option #2\n");

        ListController listController = null;
        SongController songController = new SongController();
        initSongController(songController);

        String title,artist,path;
        int id,n,pos,pos1,pos2;
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
                    listController = new ListController();
                    break;
                case 3:
                    title = in.next();
                    if (listController.contains(title))
                        System.out.println("Yes");
                    else
                        System.out.println("No");
                    System.out.print("\n");
                    break;
                case 4:
                    System.out.println(listController.size() + "\n");
                    break;
                case 5:
                    System.out.println(listController.getTotalDuration() + "\n");
                    break;
                case 6:
                    title = in.next();
                    try {
                        listController.addList(title);
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    title = in.next();
                    try {
                        listController.removeList(title);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    title = in.next();
                    n = in.nextInt();
                    try {
                        listController.createRandomList(title, n, songController);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    title = in.next();
                    String new_title = in.next();
                    try {
                        listController.setListTitle(title, new_title);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    title = in.next();
                    String title_song = in.next();
                    artist = in.next();
                    try {
                        listController.addSong(title,title_song,artist,songController);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    title = in.next();
                    String title_song2 = in.next();
                    artist = in.next();
                    try {
                        listController.removeSong(title, title_song2, artist);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 12:
                    title = in.next();
                    pos = in.nextInt();
                    try {
                        listController.removeSong(title, pos);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 13:
                    title = in.next();
                    pos1 = in.nextInt();
                    pos2 = in.nextInt();
                    try {
                        listController.swapSongs(title, pos1, pos2);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 14:
                    title = in.next();
                    String title_song3 = in.next();
                    artist = in.next();
                    if (listController.containsSong(title,title_song3,artist))
                        System.out.println("Yes");
                    else
                        System.out.println("No");
                    System.out.print("\n");
                    break;
                case 15:
                    title = in.next();
                    System.out.println(listController.listSize(title) + "\n");
                    break;
                case 16:
                    title = in.next();
                    System.out.println(listController.getListDuration(title) + "\n");
                    break;
                case 17:
                   title = in.next();
                    try {
                        List list = listController.getList(title);
                        System.out.println(list.toString());
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 18:
                    title = in.next();
                    try {
                        System.out.println(listController.getListString(title));
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 19:
                    System.out.println(listController.getListSetString());
                    break;
                case 20:
                    path = in.next();
                    try {
                        listController.save(path);
                    }
                    catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 21:
                    path = in.next();
                    try {
                        listController.load(path, songController);
                    }
                    catch (PropException|IOException e) {
                        System.out.println(e.getMessage());
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
        sb.append("2:  ListController()\n");
        sb.append("3:  boolean contains(String name)\n");
        sb.append("4:  int size()\n");
        sb.append("5:  int getTotalDuration()\n");
        sb.append("6:  void addList(String title)\n");
        sb.append("7:  boolean removeList(String name)\n");
        sb.append("8:  void createRandomList(String title, int n)\n");
        sb.append("9:  void setListTitle(String old_title, String new_title)\n");
        sb.append("10: void addSong(String name, String title, String artist)\n");
        sb.append("11: void removeSong(String name, String title, String artist)\n");
        sb.append("12: void removeSong(int id, int pos)\n");
        sb.append("13: void swapSongs(String name, int pos1, int pos2)\n");
        sb.append("14: boolean containsSong(String name, String title, String artist)\n");
        sb.append("15: int listSize(String name)\n");
        sb.append("16: int getListDuration(String name)\n");
        sb.append("17: List getList(String name)\n");
        sb.append("18: String getListString(String name)\n");
        sb.append("19: String getListSetString()\n");
        sb.append("20: void save(String path)\n");
        sb.append("21: void load(String path)\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

    private static void initSongController(SongController songController) {
        Song song0 = new Song("title0","artist0","album0",2000,Genre.getGenreById(0),Genre.getGenreById(0),000);
        Song song1 = new Song("title1","artist1","album1",2001,Genre.getGenreById(1),Genre.getGenreById(1),111);
        Song song2 = new Song("title2","artist2","album2",2002,Genre.getGenreById(2),Genre.getGenreById(2),222);
        Song song3 = new Song("title3","artist3","album3",2003,Genre.getGenreById(3),Genre.getGenreById(3),333);
        Song song4 = new Song("title4","artist4","album4",2004,Genre.getGenreById(4),Genre.getGenreById(4),444);
        Song song5 = new Song("title5","artist5","album5",2005,Genre.getGenreById(5),Genre.getGenreById(5),555);
        Song song6 = new Song("title6","artist6","album6",2006,Genre.getGenreById(6),Genre.getGenreById(6),666);

        try {
            songController.addSong(song0);
            songController.addSong(song1);
            songController.addSong(song2);
            songController.addSong(song3);
            songController.addSong(song4);
            songController.addSong(song5);
            songController.addSong(song6);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
