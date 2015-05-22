package prop.domain;


import java.util.*;

/**
 * Class ListSet, represents a set of playlists.
 * @author Carles Garcia Cabot
 * @see List
 */
public class ListSet {
    //It assigns unique ids to lists as they are added.
    private HashMap<Integer,List> lists;
    private int nextId; // id to be assigned to a new list

    private static final char delimiter = '\n';

    /* CONSTRUCTORS */
    /**
     * ListSet default constructor. Creates a ListSet with 0 lists
     */
    public ListSet() {
        lists = new HashMap<Integer,List>();
        nextId = 0;
    }

    /* GETTERS */
    public ArrayList<List> getLists() {
        return new ArrayList<List>(lists.values());
    }

    public int getNextId() {
        return nextId;
    }

    /* SETTERS */

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
        lists.put(nextId, list);
        ++nextId;
    }

    /**
     * Searches the list with a certain id
     * @param id id of the list to search. The list must be contained in the ListSet
     * @return Returns the list
     */
    public List getList(int id) {
        if (lists.containsKey(id)) return lists.get(id);
        throw new IllegalArgumentException("The list with this id isn't contained in the ListSet");
    }

    /**
     * Removes the list with this id from the set
     * @param id int id of the list to be removed
     * @return true if the list was found and removed, false otherwise
     */
    public boolean remove(int id) {
        for (Map.Entry<Integer,List> entry : lists.entrySet()) {
            if (id == entry.getValue().obtainId()) {
                lists.remove(entry.getKey());
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
        return lists.containsValue(list);
    }

    /**
     * Indicates if a list with this title is contained in the set
     * @param title string title of the list to look up
     * @return true if found, false otherwise
     */
    public boolean contains(String title) {
        for (List l : lists.values())
            if (l.obtainTitle().equals(title)) return true;
        return false;
    }

    /**
     * Indicates if the list with this id is contained in the set
     * @param id int id of the list to look up
     * @return true if found, false otherwise
     */
    public boolean contains(int id) {
        for (List l : lists.values()) if (l.obtainId() == id) return true;
        return false;
    }

    /**
     * Total duration of all the playlists in the set
     * @return int total time
     */
    public int totalTime() {
        int time = 0;
        for (List l : lists.values()) time += l.obtainTotalTime();
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
        for (List l : lists.values()) {
            s += l.toString() + delimiter;
        }
        return s;
    }

}