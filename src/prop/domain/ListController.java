package prop.domain;

import prop.data.DataController;
import java.io.IOException;
import java.util.*;

/**
 * The controller for {@code List} and {@code ListSet}
 * @author oscar.manas
 * @see List
 * @see ListSet
 */
public class ListController {

    ListSet listSet;

    /**
     * Constructor
     */
    public ListController() {
        listSet = new ListSet();
    }

    /**
     * Get the size of the list set
     * @return  the size of the list set
     */
    public int size() {
        return listSet.size();
    }

    /**
     * Add a new list to the set
     * @param title the title of the list
     */
    public void addList(String title) {
        List list = new List(title);
        listSet.add(list);
    }

    /**
     * Remove a list from the set
     * @param id    the identification of the list to remove
     * @return      true if the list was present, and thus removed,
     *              false if the list was not present
     */
    public boolean removeList(int id) {
        return listSet.remove(id);
    }

    /**
     * Generate a random song list
     * @param title             the title of the list
     * @param n                 the number of songs the list will contain
     * @param songController    an instance of the Song Controller
     */
    public void createRandomList(String title, int n, SongController songController) throws Exception {
        int m = songController.size();
        Random rand = new Random();
        // We use LinkedHashSet to maintain insertion order
        Set<Integer> positions = new LinkedHashSet<Integer>();
        // Generate m random integers with no duplicates
        while (positions.size() < n){
            int r = rand.nextInt(m);
            // As we're adding to a set, this will automatically do a containment check
            positions.add(r);
        }

        List list = new List(title);
        Iterator<Integer> it = positions.iterator();
        while (it.hasNext()) {
            list.addSong(songController.getSong(it.next()));
        }
        listSet.add(list);
    }

    /**
     * Set a new title for a list
     * @param id    the identification of the specified list
     * @param title the new title
     */
    public void setTitle(int id, String title){
        List list = listSet.getList(id);
        if (list != null)
            list.editTitle(title);
    }

    /**
     * Add a song to a specified list
     * @param id                the identification of the specified list
     * @param title             the title of the song to add
     * @param artist            the artist of the song to add
     * @param songController    an instance of the Song Controller
     */
    public void addSong(int id, String title, String artist, SongController songController) throws Exception {
        List list = listSet.getList(id);
        Song song = songController.getSong(title, artist);
        list.addSong(song);
    }

    /**
     * Remove a song from a specified list
     * @param id        the identification of the specified list
     * @param title     the title of the song to remove
     * @param artist    the artist of the song to remove
     */
    public void removeSong(int id, String title, String artist) {
        List list = listSet.getList(id);
        list.removeSong(title, artist);
    }

    /**
     * Get a list
     * @param id    the identification of the list
     * @return      the list
     */
    public List getList(int id) {
        return listSet.getList(id);
    }

    /**
     * Get a list in string format
     * @param id    the identification of the list
     * @return      a string representing the list
     */
    public String getListString(int id) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Song> songs = listSet.getList(id).obtainSongs();
        for (Song song : songs)
            sb.append(song.getTitle()+ "\t" + song.getArtist() + "\t" + song.getAlbum() + "\t" + song.getYear()
                    + "\t" + song.getDuration() + "\n");
        return sb.toString();
    }

    /**
     * Get all the lists in string format
     * @return  a string with the lists titles and their identifiers
     */
    public String getListSetString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<List> lists = listSet.getLists();
        for (List list : lists)
            sb.append(list.obtainId() + "\t" + list.obtainTitle() + "\n");
        return sb.toString();
    }

    /**
     * Save the list set in the specified path
     * @param path  the path where save the list set to
     */
    public void save(String path) {
        try {
            DataController.save(listSet.toString(),path);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Load the list set from the specified path
     * @param path  the path where load the list set from
     */
    public void load(String path) {
        try {
            String serialized = DataController.load(path);
            //listSet = ListSet.valueOf(serialized);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
