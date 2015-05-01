package prop.domain;

import java.util.ArrayList;

/**
 * ListSetDriver
 * @author Carles Garcia Cabot
 */
public class ListSetDriver {
    private static void printInfo() {
        ArrayList<String> sb = new ArrayList<>();
        sb.add("ListSet()");
        sb.add("ListSet(java.util.ArrayList<List> lists)");
        sb.add("void 	add(List list)");
        sb.add("void 	clear()");
        sb.add("boolean 	contains(int id)");
        sb.add("boolean 	contains(List list)");
        sb.add("boolean 	contains(java.lang.String title)");
        sb.add("List 	getList(int id)");
        sb.add("java.util.ArrayList<List> 	getLists()");
        sb.add("boolean 	remove(int id)");
        sb.add("boolean 	remove(List list)");
        sb.add("void 	setLists(java.util.ArrayList<List> lists)");
        sb.add("void 	setNextId(int nid)");
        sb.add("int 	size()");
        sb.add("java.lang.String 	toString()");
        sb.add("int 	totalTime()");
        sb.add("static ListSet 	valueOf(java.lang.String origin, SongController songController)");

        for (int i = 0; i < sb.size(); ++i) {
            System.out.println(i + ": " + sb.get(i));
        }
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n"
                + "1:    info\n");
    }

}
