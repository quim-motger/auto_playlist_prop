package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * ListSetDriver
 * @author Carles Garcia Cabot
 */
public class ListSetDriver {
    public static void main(String[] args) {
        try {
            System.err.println("**********************************************************");
            System.err.println("** ListSet");
            System.err.println("**********************************************************");
            System.err.print("\n");
            printInfo();
            System.err.println("This class needs 2 other Classes: Song" +
                    " and List \n"
                    + "Therefore, you can create a Song and a List\n"

                    + "Example of Genre: 17, 18, 19\n"
                    + "START WITH OPTION 2\n");

            Song song = null;
            SongController songController = new SongController();
            String serialize = null;
            List list = null;
            TreeSet<List> arrayList = new TreeSet<>();
            ListSet l = null;
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
                        l = new ListSet();
                        break;
                    case 3:
                       // l = new ListSet(arrayList);
                        break;
                    case 4:
                        l.add(list);
                        break;
                    case 5:
                        l.clear();
                        break;
                    case 6:
                        System.out.println(l.contains(in.next()));
                        break;
                    case 7:
                        System.out.println(l.contains(list));
                        break;
                    case 8:
                        List gl = l.getList(in.next());
                        if (gl == null) System.out.println("The list with this id doesn't exist");
                        else System.out.println(gl);
                        break;
                    case 9:
                        ArrayList<List> al = l.getLists();
                        for (List li : al) System.out.println(li);
                        break;
                    case 10:
                        l.remove(in.next());
                        break;
                    case 11:
                       // System.out.println(l.remove(list));
                        break;
                    case 12:
                     //   l.setLists(arrayList);
                        break;
                    case 13:
                        System.out.println(l.size());
                        break;
                    case 14:
                        serialize = l.toString();
                        System.out.println(serialize);
                        break;
                    case 15:
                        System.out.println(l.totalTime());
                        break;
                    case 16:
                        song = songController.getSong(in.next(),in.next());
                        break;
                    case 17:
                        songController.addSong(in.next(), in.next(), in.next(), in.nextInt(), Genre.getGenreById(in.nextInt()),
                                Genre.getGenreById(in.nextInt()), in.nextInt());
                        break;
                    case 18:
                        list = new List(in.next());
                        break;
                    case 19:
                        list.addSong(song);
                        break;
                    case 20:
                        //nothing
                        break;
                    case 21:
                        arrayList.add(list);
                        break;
                    default:
                        printInfo();
                }
                printInfoBrief();
            }
        } catch (Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }

    private static void printInfo() {
        ArrayList<String> sb = new ArrayList<>();
        sb.add("terminate program");
        sb.add("info");
        sb.add("ListSet()");
        sb.add("ListSet(arrayList)");
        sb.add("void 	add(list)");
        sb.add("void 	clear()");
        sb.add("boolean 	contains(int id)");
        sb.add("boolean 	contains(list)");
        sb.add("List 	getList(int id)");
        sb.add("ArrayList<List> 	getLists()");
        sb.add("boolean 	remove(int id)");
        sb.add("boolean 	remove(list)");
        sb.add("void 	setLists(arrayList)");
        sb.add("int 	size()");
        sb.add("String 	toString()");
        sb.add("int 	totalTime()");
        // OTHER METHODS
        sb.add("song = songController.getSong(String title, String artist)");
        sb.add("songController.addSong(String title, String artist, String album, int year, int genre, int subgenre, int duration)");
        sb.add("list = new List(String listTitle)");
        sb.add("list.addSong(song)");
        sb.add("nothing");
        sb.add("arrayList.add(list)");
        for (int i = 0; i < sb.size(); ++i) {
            if (!sb.get(i).equals("nothing"))
                System.err.println(i + ": " + sb.get(i));
        }
    }

    private static void printInfoBrief() {
        System.err.print("0:    terminate program\n"
                + "1:    info\n");
    }

}
