package prop.domain;

import java.util.ArrayList;

/**
 * Class ListSet, represents a set of playlists
 * A list can't be duplicated in the set
 * @author Carles Garcia Cabot
 */
public class ListSet {
    private ArrayList<List> lists; // a list can't be duplicated

    /* CONSTRUCTORS */
    /**
     * ListSet default constructor. Creates and empty ListSet
     */
    public ListSet() {}

    /**
     * ListSet constructor with its only member
     * @param lists array of lists to form the set
     */
    public ListSet(ArrayList<List> lists) {
        this.lists = lists;
    }

    /* GETTERS */
    public ArrayList<List> getLists() {
        return lists;
    }

    /* SETTERS */
    public void setLists(ArrayList<List> lists) {
        this.lists = lists;
    }

    /* OTHER METHODS */

    /**
     * Adds a list to the set
     * @param list list to be added
     *             (Precondition: list isn't already in the set)
     */
    public void add(List list) {
        lists.add(list);
    }

    /**
     * Removes the list from the set
     * @param list list to be removed
     * @return true if the list was found and removed, false otherwise
     */
    public boolean remove(List list) {
        return lists.remove(list);
    }

    /**
     * Removes the list with this title from the set
     * @param title string title of the list to be removed
     * @return true if the list was found and removed, false otherwise
     */
    public boolean remove(String title) {
        for (int i = 0; i < lists.size(); ++i) {
            if (lists.get(i).obtainTitle().equals(title)) {
                lists.remove(i);
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
     * Indicates if the list with this title is contained in the set
     * @param title string title of the list to look up
     * @return true if found, false otherwise
     */
    public boolean contains(String title) {
        for (List l : lists) if (l.obtainTitle().equals(title)) return true;
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
     * Returns size of the set
     * @return int size
     */
    public int size() { return lists.size(); }

    /**
     * Removes ALL lists from the set
     */
    public void clear() {
        lists.clear();
    }

}