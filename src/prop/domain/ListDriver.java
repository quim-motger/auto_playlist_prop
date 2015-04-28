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

    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************************");
        System.out.println("** List");
        System.out.println("**********************************************************");
        System.out.println("One line for instruction. Please avoid using spaces unless it's a separator.");
        System.out.print("\n");
        printInfo();
        
        List l=null;
        Song song = null;
        Scanner in = new Scanner(System.in);
        SongController sc = new SongController();
        
        String ret;
        int i = -1;
        while (i!=0) {
            ret = in.next();
            String[] rets = ret.split(" ");
            i=Integer.valueOf(rets[0]);
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfo();
                    break;
                case 2:
                    testNewList(rets,l);
                    break;
                case 3:
                    if(!invalidArguments(0,rets))
                        System.out.println(l.size());
                    break;
                case 4:
                    if(!invalidArguments(0,rets))
                        System.out.println(l.obtainTitle());
                    break;
                case 5:
                    if(!invalidArguments(0,rets))
                        System.out.println(l.obtainId());
                    break;
                case 6:
                    testObtainSong(rets,l);
                    break;
                case 7:
                    testObtainSongs(rets, l);
                    break;
                case 8:
                    testObtainSongPosition(rets, l);
                    break;
                case 9:
                    if(!invalidArguments(0,rets))
                        System.out.println(l.obtainTotalTime());
                    break;
                case 10:
                    testContains(rets,l);
                    break;
                case 11:
                    testIsEmpty(rets,l);
                    break;
                case 12:
                    testEditTitle(rets,l);;
                    break;
                case 13:
                    testEditId(rets,l);
                    int id = in.nextInt();
                    l.editId(id);
                    break;
                case 14:
                    if(!invalidArguments(0,rets))
                        l.addSong(song);
                    break;
                case 15:
                    testRemoveSong(rets,l);
                    break;
                case 16:
                    if(!invalidArguments(0,rets)) 
                        l.empty();
                    break;
                case 17:
                    testSwapSongs(rets,l);
                    break;
                case 18:
                    if(!invalidArguments(0,rets))
                        System.out.println(l.toString());   
                    break;
                case 19:
                    testValueOf(rets,l,sc);
                    break;
                case 20:
                    testNewSong(rets,song);
                    break;
                case 21:
                    testGetSong(sc,rets,song);
                    break;
                case 22:
                    testAddSong(sc,rets);
                    break;
                default:
                    printInfo();
            }
            
        }
    }

    private static void testAddSong(SongController sc, String[] rets) {
        if(!invalidArguments(7,rets))
            try {
                sc.addSong(
                        rets[1],
                        rets[2],
                        rets[3],
                        Integer.valueOf(rets[4]),
                        Genre.getGenreById(Integer.valueOf(rets[5])),
                        Genre.getGenreById(Integer.valueOf(rets[6])),
                        Integer.valueOf(rets[7])
                );
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            
    }

    private static void testGetSong(SongController sc, String[] rets, Song song) {
        if(!invalidArguments(2,rets))
            try {
                song = sc.getSong(rets[1],rets[2]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }


    private static void testNewSong(String[] rets, Song song) {
        if(!invalidArguments(7,rets)) {
            song = new Song(
                    rets[1],
                    rets[2],
                    rets[3],
                    Integer.valueOf(rets[4]),
                    Genre.getGenreById(Integer.valueOf(rets[5])),
                    Genre.getGenreById(Integer.valueOf(rets[6])),
                    Integer.valueOf(rets[7])
            );
        }
    }

    private static void testValueOf(String[] rets, List l, SongController sc) {
        if(!invalidArguments(1,rets)) {
            try {
                l = List.valueOf(rets[1],sc);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void testSwapSongs(String[] rets, List l) {
        if(!invalidArguments(2,rets)) {
            l.swapSongs(Integer.valueOf(rets[1]),Integer.valueOf(rets[2]));            
        }
    }

    private static void testRemoveSong(String[] rets, List l) {
        if(!invalidArguments(2,rets)) {
            l.removeSong(rets[0],rets[1]);
        }
    }

    private static void testEditId(String[] rets, List l) {
        if(!invalidArguments(1,rets)) {
            l.editId(Integer.valueOf(rets[1]));
        }
    }

    private static void testEditTitle(String[] rets, List l) {
        if(!invalidArguments(1,rets)) {
            l.editTitle(rets[1]);
        }
    }

    private static void testIsEmpty(String[] rets, List l) {
        if(!invalidArguments(0,rets)) {
            if(l.isEmpty()) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }

    private static void testContains(String[] rets, List l) {
        if(!invalidArguments(2,rets)) {
            if(l.contains(rets[1], rets[2])) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }

    private static void testObtainSongPosition(String[] rets, List l) {
        if(!invalidArguments(2,rets)) {
            System.out.println(l.obtainSongPosition(rets[1],rets[2]));
        }
    }

    private static void testObtainSongs(String[] rets, List l) {
        if(!invalidArguments(0,rets)) {
            ArrayList<Song> a = l.obtainSongs();
            for (Song s : a) {
                System.out.println(s.toString());
            }
        }
    }

    private static void testObtainSong(String[] rets, List l) {
        if (!invalidArguments(1,rets)) {
            int pos = Integer.valueOf(rets[1]);
            System.out.println(l.obtainSong(pos).toString());
        }
    }

    private static void testNewList(String[] rets,List l) {
        if(!invalidArguments(1,rets)) {
            String title = rets[1];
            l = new List(title);
        }
    }

    private static boolean invalidArguments(int nArg, String[] rets) {
        if((nArg+1)!=rets.length){
            System.out.println("Invalid number of arguments");
            return true;
        }
        return false;
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
        sb.append("9:  int obtainTotalTime()\n");
        sb.append("10: boolean contains(String title, String artist)\n");
        sb.append("11: boolean isEmpty()\n");
        sb.append("12: void editTitle(String title)\n");
        sb.append("13: void editId(int listId)\n");
        sb.append("14: void addSong(Song song)\n");
        sb.append("15: void removeSong(String title, String artist)\n");
        sb.append("16: void empty()\n");
        sb.append("17: void swapSongs(int index1, int index2)\n");
        sb.append("18: String toString()\n");
        sb.append("19: valueOf(String origin, SongController sc)\n");
        sb.append("20: song = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        sb.append("21: song = sc.getSong(String title, String artist)\n");
        sb.append("22: sc.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
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
