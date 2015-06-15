package prop.domain;


import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;

/**
 * Class ListSet, represents a set of playlists.
 * @author Carles Garcia Cabot
 * @see List
 */
public class ListSet {
    private static final char delimiter = '\n';
    private TernarySearchTree<List> lists;

    /* CONSTRUCTORS */
    /**
     * ListSet default constructor. Creates a ListSet with 0 lists
     */
    public ListSet() {
        lists = new TernarySearchTree<>();
    }

    /* GETTERS */
    public ArrayList<List> getLists() {
        return new ArrayList<>(lists.getList());
    }

    /* OTHER METHODS */

    /**
     * Adds a list to the set
     * @param list list to be added (Precondition: the list isn't already contained and is not null)
     */
    public void add(List list) throws PropException {
        if (!contains(list.obtainTitle())) lists.put(list.obtainTitle(), list);
        else throw new PropException(ErrorString.EXISTING_LIST);
    }

    /**
     * Searches the list with a certain id
     * @param name name of the list to search (not null). The list must be contained in the ListSet
     * @return Returns the list
     */
    public List getList(String name) {
        if (lists.contains(name)) return lists.get(name);
        throw new IllegalArgumentException("The list with this id isn't contained in the ListSet");
    }


    /**
     * Obtains Lists whose title starts by a specific prefix
     * @param prefix (not null)
     * @return ArrayList with the lists
     */
    public ArrayList<List> findLists(String prefix) {
        return lists.matchPrefix(prefix);
    }

    /**
     * Gets the songIndex id of the songIndex in the position {@code songIndex} of the listIndex in the position {@code listIndex}
     * @param listIndex     the position of the listIndex
     * @param songIndex     the position of the songIndex
     * @return              the songIndex id
     */
    public String[] getSongId(int listIndex, int songIndex) {
        Song s = lists.getList().get(listIndex).obtainSong(songIndex);
        String id[] = new String[2];
        id[0] = s.getTitle();
        id[1] = s.getArtist();
        return id;
    }

    /**
     * Removes the list with a specific title from the set
     * @param name String title of the list to be removed (Precondition: the list is contained in the set and is not null)
     */
    public void remove(String name) throws PropException {
        if (lists.contains(name)) {
            lists.remove(name);
        } else throw new PropException(ErrorString.UNEXISTING_LIST);
    }

    /**
     * Indicates if a list with this title is contained in the set
     * @param title string title of the list to look up (not null)
     * @return true if found, false otherwise
     */
    public boolean contains(String title) {
        if (lists.contains(title)) return true;
        else return false;
    }

    /**
     * Indicates if a list is contained in the set
     * @param l list to look up (not null)
     * @return true if found, false otherwise
     */
    public boolean contains(List l) {
        if (lists.contains(l.obtainTitle())) return true;
        else return false;
    }

    /**
     * Total duration of all the playlists in the set
     * @return int total time
     */
    public int totalTime() {
        int time = 0;
        for (List l : lists.getList()) time += l.obtainTotalTime();
        return time;
    }

    /**
     * Returns the number of lists contained in the set
     * @return int size
     */
    public int size() {
        return lists.getSize();
    }

    /**
     * Removes ALL lists from the set
     */
    public void clear() {
        lists = new TernarySearchTree<>();
    }

    @Override
    public String toString() {
        String s = "";
        s += String.valueOf(lists.getSize()) + delimiter;
        for (List l : lists.getList()) {
            s += l.toString() + delimiter;
        }
        return s;
    }

}