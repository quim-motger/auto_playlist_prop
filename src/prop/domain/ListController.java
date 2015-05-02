package prop.domain;

import prop.data.DataController;
import java.util.*;

/**
 * The controller for {@code List} and {@code ListSet}.
 * @author oscar.manas
 * @see List
 * @see ListSet
 */
public class ListController {

    ListSet listSet;

    /**
     * Default constructor.
     */
    public ListController() {
        listSet = new ListSet();
    }

    /**
     * Indicates if the list with this id is contained in the set.
     * @param id    the id of the list to look up
     * @return      true if present,
     *              false if not present
     */
    public boolean contains(int id) {
        return listSet.contains(id);
    }

    /**
     * Get the size of the list set.
     * @return  the size of the list set
     */
    public int size() {
        return listSet.size();
    }

    /**
     * Get the total duration of all the lists in the set.
     * @return  the total duration of all the lists in the set
     */
    public int getTotalDuration() {
        return listSet.totalTime();
    }

    /**
     * Add a new list to the set.
     * @param title the title of the list
     */
    public void addList(String title) {
        List list = new List(title);
        listSet.add(list);
    }

    /**
     * Add a new list to the set.
     * @param list the list
     */
    public void addList(List list) {
        listSet.add(list);
    }

    /**
     * Remove a list from the set.
     * @param id    the identification of the list to remove
     * @return      true if the list was present, and thus removed,
     *              false if the list was not present
     */
    public boolean removeList(int id) {
        return listSet.remove(id);
    }

    /**
     * Generate a random song list.
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
     * Set a new title for the specified list.
     * @param id    the identification of the specified list
     * @param title the new title
     */
    public void setListTitle(int id, String title){
        listSet.getList(id).editTitle(title);
    }

    /**
     * Get the title of the specified list.
     * @param id    the identification of the specified list
     * @return      the title of the specified list
     */
    public String getListTitle(int id) {
        return listSet.getList(id).obtainTitle();
    }

    /**
     * Add a song to a specified list.
     * @param id                the identification of the specified list
     * @param title             the title of the song to add
     * @param artist            the artist of the song to add
     * @param songController    an instance of the Song Controller
     */
    public void addSong(int id, String title, String artist, SongController songController) throws Exception {
        Song song = songController.getSong(title, artist);
        listSet.getList(id).addSong(song);
    }

    /**
     * Remove a song from a specified list.
     * @param id        the identification of the specified list
     * @param title     the title of the song to remove
     * @param artist    the artist of the song to remove
     */
    public void removeSong(int id, String title, String artist) {
        listSet.getList(id).removeSong(title, artist);
    }

    /**
     * Remove a song from a specified list.
     * @param id    the identification of the specified list
     * @param pos   the position of the song to remove in the list
     */
    public void removeSong(int id, int pos) {
        listSet.getList(id).removeSong(pos);
    }

    /**
     * Swaps two songs inside the list.
     * @param id    the identification of the specified list
     * @param pos1  the position of the first song to swap
     * @param pos2  the position of the second song to swap
     */
    public void swapSongs(int id, int pos1, int pos2) {
        listSet.getList(id).swapSongs(pos1,pos2);
    }

    /**
     * Check if a song is included in the list.
     * @param id        the identification of the specified list
     * @param title     the title of the wanted song
     * @param artist    the artist of the wanted song
     * @return          true if present,
     *                  false if not present
     */
    public boolean containsSong(int id, String title, String artist) {
        return listSet.getList(id).contains(title, artist);
    }

    /**
     * Get the number of songs in the list.
     * @param id    the identification of the specified list
     * @return      the number of songs in the list
     */
    public int listSize(int id) {
        return listSet.getList(id).size();
    }

    /**
     * Get the total duration of all songs in the list.
     * @param id    the identification of the specified list
     * @return      the total duration of all songs in the list
     */
    public int getListDuration(int id) {
        return listSet.getList(id).obtainTotalTime();
    }

    /**
     * Get a list
     * @param id    the identification of the list.
     * @return      the list
     */
    public List getList(int id) {
        return listSet.getList(id);
    }

    /**
     * Get a list in string format.
     * @param id    the identification of the list
     * @return      a string representing the list
     */
    public String getListString(int id) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Song> songs = listSet.getList(id).obtainSongs();
        for (int i = 0; i < songs.size(); ++i) {
            Song s = songs.get(i);
            sb.append(i + "\t" + s.getTitle() + " " + s.getArtist() + " " + s.getAlbum() + " " + s.getYear() +
                    " " + s.getGenre().getName() + " " + s.getSubgenre().getName() + " " + s.getDuration() + "\n");
        }
        return sb.toString();
    }

    /**
     * Get all the lists in string format.
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
     * Save the list set in the specified path.
     * @param path  the path where save the list set to
     */
    public void save(String path) throws Exception {
        DataController.save(listSet.toString(),path);
    }

    /**
     * Load the list set from the specified path.
     * @param path  the path where load the list set from
     */
    public void load(String path, SongController songController) throws Exception {
        String serialized = DataController.load(path);
        listSet = ListSet.valueOf(serialized,songController);
    }

}
