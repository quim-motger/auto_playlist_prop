package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ListSetDriver
 * @author Carles Garcia Cabot
 */
public class ListSetDriver {
    public static void main(String[] args) {
        try {
            System.out.println("**********************************************************");
            System.out.println("** ListSet");
            System.out.println("**********************************************************");
            System.out.print("\n");
            printInfo();
            System.out.println("This class needs 2 other Classes: Song" +
                    " and List \n"
                    + "Therefore, you can create a Song and a List\n"

                    + "Example of Genre: Rock, Blues, Disco\n"
                    + "START WITH OPTION 2\n");

            Song song = null;
            SongController songController = new SongController();
            String serialize = null;
            List list = null;
            ArrayList<List> arrayList = new ArrayList<>();
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
                        l = new ListSet(arrayList);
                        break;
                    case 4:
                        l.add(list);
                        break;
                    case 5:
                        l.clear();
                        break;
                    case 6:
                        System.out.println(l.contains(in.nextInt()));
                        break;
                    case 7:
                        System.out.println(l.contains(list));
                        break;
                    case 8:
                        System.out.println(l.contains(in.next()));
                        break;
                    case 9:
                        System.out.println(l.getList(in.nextInt()));
                        break;
                    case 10:
                        ArrayList<List> al = l.getLists();
                        for (List li : al) System.out.println(li);
                        break;
                    case 11:
                        System.out.println(l.remove(in.nextInt()));
                        break;
                    case 12:
                        System.out.println(l.remove(list));
                        break;
                    case 13:
                        l.setLists(arrayList);
                        break;
                    case 14:
                        l.setNextId(in.nextInt());
                        break;
                    case 15:
                        System.out.println(l.size());
                        break;
                    case 16:
                        serialize = l.toString();
                        System.out.println(serialize);
                        break;
                    case 17:
                        System.out.println(l.totalTime());
                        break;
                    case 18:
                        if (serialize.equals("")) throw new Exception("Error: must do toString before valueOf");
                        l = ListSet.valueOf(serialize, songController);
                        serialize = "";
                        break;
                    case 19:
                        song = songController.getSong(in.next(),in.next());
                        break;
                    case 20:
                        songController.addSong(in.next(), in.next(), in.next(), in.nextInt(), Genre.valueOf(in.next()),
                                Genre.valueOf(in.next()), in.nextInt());
                        break;
                    case 21:
                        list = new List(in.next());
                        break;
                    case 22:
                        list.addSong(song);
                        break;
                    case 23:
                        //nothing
                        break;
                    case 24:
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
        sb.add("boolean 	contains(String title)");
        sb.add("List 	getList(int id)");
        sb.add("ArrayList<List> 	getLists()");
        sb.add("boolean 	remove(int id)");
        sb.add("boolean 	remove(list)");
        sb.add("void 	setLists(arrayList)");
        sb.add("void 	setNextId(int nid)");
        sb.add("int 	size()");
        sb.add("String 	toString()");
        sb.add("int 	totalTime()");
        sb.add("static ListSet 	valueOf()");
        // OTHER METHODS
        sb.add("song = songController.getSong(String title, String artist)");
        sb.add("songController.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)");
        sb.add("list = new List(String listTitle)");
        sb.add("list.addSong(song)");
        sb.add("nothing");
        sb.add("arrayList.add(list)");
        for (int i = 0; i < sb.size(); ++i) {
            if (!sb.get(i).equals("nothing"))
                System.out.println(i + ": " + sb.get(i));
        }
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n"
                + "1:    info\n");
    }

}
