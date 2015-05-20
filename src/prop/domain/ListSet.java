package prop.domain;


import java.util.TreeSet;

/**
 * Class ListSet, represents a set of playlists.
 * @author Carles Garcia Cabot
 * @see List
 */
public class ListSet {
    //It assigns unique ids to lists as they are added.
    private TreeSet<List> lists;
    private int nextId; // id to be assigned to a new list

    private static final char delimiter = '\n';

    /* CONSTRUCTORS */
    /**
     * ListSet default constructor. Creates a ListSet with 0 lists
     */
    public ListSet() {
        lists = new TreeSet<List>();
        nextId = 0;
    }

    /**
     * ListSet constructor with lists
     * @param lists array of lists to form the set
     */
    public ListSet(TreeSet<List> lists1) {
        lists = lists1;
        nextId = 0;
    }

    /* GETTERS */
    public TreeSet<List> getLists() {
        return lists;
    }

    public int getNextId() {
        return nextId;
    }

    /* SETTERS */
    public void setLists(TreeSet<List> lists1) {
        lists = lists1;
    }

    public void setNextId(int nid) {
        if (nid < nextId) throw new IllegalArgumentException("nextId can't be set to an inferior number to the current");
        nextId = nid;
    }

    /* OTHER METHODS */

    /**
     * Adds a list to the set
     * @param list list to be added (not null)
     */
    public void add(List list) {
        list.editId(nextId);
        lists.add(list);
        ++nextId;
    }

    /**
     * Searches the list with a certain id
     * @param id id of the list to search. The list must be contained in the ListSet
     * @return Returns the list
     */
    public List getList(int id) {
        for (List list: lists) {
            if (list.obtainId() == id)
                return list;
        }
        throw new NullPointerException("The list with this id isn't contained in the ListSet");
    }

    /**
     * Removes the list from the set
     * @param list list to be removed (not null)
     * @return true if the list was found and removed, false otherwise
     */
    public boolean remove(List list) {
        return lists.remove(list);
    }

    /**
     * Removes the list with this id from the set
     * @param id int id of the list to be removed
     * @return true if the list was found and removed, false otherwise
     */
    public boolean remove(int id) {
        for (List l : lists) {
            if (l.obtainId() == id) {
                lists.remove(l);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the set contains the list
     * @param list list to look up
     * @return true if found, false otherwise
     */
    public boolean contains(List list) {
        return lists.contains(list);
    }

    /**
     * Indicates if a list with this title is contained in the set
     * @param title string title of the list to look up
     * @return true if found, false otherwise
     */
    public boolean contains(String title) {
        for (List l : lists) if (l.obtainTitle().equals(title)) return true;
        return false;
    }

    /**
     * Indicates if the list with this id is contained in the set
     * @param id int id of the list to look up
     * @return true if found, false otherwise
     */
    public boolean contains(int id) {
        for (List l : lists) if (l.obtainId() == id) return true;
        return false;
    }

    /**
     * Total duration of all the playlists in the set
     * @return int total time
     */
    public int totalTime() {
        int time = 0;
        for (List l : lists) time += l.obtainTotalTime();
        return time;
    }

    /**
     * Returns the number of lists contained in the set
     * @return int size
     */
    public int size() { return lists.size(); }

    /**
     * Removes ALL lists from the set
     */
    public void clear() {
        lists.clear();
        nextId = 0;
    }

    public String toString() {
        String s = "";
        s += String.valueOf(lists.size()) + delimiter;
        for (List l : lists) {
            s += l.toString() + delimiter;
        }
        return s;
    }

}