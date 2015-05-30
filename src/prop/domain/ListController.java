package prop.domain;

import prop.ErrorString;
import prop.PropException;
import prop.data.DataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * The controller for {@code List} and {@code ListSet}.
 * @author oscar.manas
 * @see List
 * @see ListSet
 */
public class ListController {

    private static final char elemDelimiter = '|';
    private static final char setDelimiter = '\n';
    private ListSet listSet;

    /**
     * Default constructor.
     */
    public ListController() {
        listSet = new ListSet();
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
    public void addList(String title) throws PropException {
        List list = new List(title.trim());
        listSet.add(list);
    }

    /**
     * Add a new list to the set.
     * @param list the list
     */
    public void addList(List list) throws PropException {
        listSet.add(list);
    }

    /**
     * Remove a list from the set.
     * @param name    the name of the list to remove
     */
    public void removeList(String name) throws PropException {
        listSet.remove(name);
    }

    /**
     * Generate a random song list.
     * @param title             the title of the list
     * @param n                 the number of songs the list will contain
     * @param songController    an instance of the Song Controller
     */
    public void createRandomList(String title, int n, SongController songController) throws PropException {
        int m = songController.size();
        if (n > m) n = m;
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
        ArrayList<Song> songs = songController.getSongs();
        for (int pos : positions) {
            list.addSong(songs.get(pos));
        }
        listSet.add(list);
    }

    /**
     * Set a new title for the specified list.
     * @param title_old the old title
     * @param title_new the new title
     */
    public void setListTitle(String title_old, String title_new) throws PropException {
        if (listSet.contains(title_old)) {
            if (!listSet.contains(title_new)) {
                List l = listSet.getList(title_old);
                listSet.remove(title_old);
                l.editTitle(title_new);
                listSet.add(l);
            }
            else
                throw new PropException(ErrorString.EXISTING_LIST);
        }
    }

    /**
     * Add a song to a specified list.
     * @param title             the title of the song to add
     * @param artist            the artist of the song to add
     * @param songController    an instance of the Song Controller
     */
    public void addSong(String name, String title, String artist, SongController songController) throws PropException {
        Song song = songController.getSong(title, artist);
        listSet.getList(name).addSong(song);
    }

    /**
     * Remove a song from a specified list.
     * @param name      list name
     * @param title     the title of the song to remove
     * @param artist    the artist of the song to remove
     */
    public void removeSong(String name, String title, String artist) {
        listSet.getList(name).removeSong(title, artist);
    }

    /**
     * Remove a song from a specified list.
     * @param name  list name
     * @param pos   the position of the song to remove in the list
     */
    public void removeSong(String name, int pos) {
        listSet.getList(name).removeSong(pos);
    }

    public boolean contains(String name) {
        return listSet.contains(name);
    }

    /**
     * Swaps two songs inside the list.
     * @param name  list name
     * @param pos1  the position of the first song to swap
     * @param pos2  the position of the second song to swap
     */
    public void swapSongs(String name, int pos1, int pos2) {
        listSet.getList(name).swapSongs(pos1, pos2);
    }

    /**
     * Check if a song is included in the list.
     * @param name      list name
     * @param title     the title of the wanted song
     * @param artist    the artist of the wanted song
     * @return          true if present,
     *                  false if not present
     */
    public boolean containsSong(String name, String title, String artist) {
        return listSet.getList(name).contains(title, artist);
    }

    /**
     * Get the number of songs in the list.
     * @param name  list name
     * @return      the number of songs in the list
     */
    public int listSize(String name) {
        return listSet.getList(name).size();
    }

    /**
     * Get the total duration of all songs in the list.
     * @param name  list name
     * @return      the total duration of all songs in the list
     */
    public int getListDuration(String name) {
        return listSet.getList(name).obtainTotalTime();
    }

    /**
     * Get a list.
     * @param name  list name
     * @return      the list
     */
    public List getList(String name) {
        return listSet.getList(name);
    }

    public String[] findLists(String prefix) {
        ArrayList<List> l = listSet.findLists(prefix);
        String result[] = new String[l.size()];
        for (int i = 0; i < l.size(); ++i)
            result[i] = l.get(i).obtainTitle();
        return result;
    }

    /**
     * Get a list in string format.
     * @param name  list name
     * @return      a string representing the list
     */
    public String getListString(String name) {
        List list = listSet.getList(name);
        ArrayList<Song> songs = list.obtainSongs();
        StringBuilder sb = new StringBuilder();
        sb.append(list.obtainTitle() + "\n");
        for (int i = 0; i < songs.size(); ++i) {
            Song s = songs.get(i);
            sb.append("    " + i + "  " + s.getTitle() + " " + s.getArtist() + " " + s.getAlbum() + " " + s.getYear() +
                    " " + s.getGenre().getName() + " " + s.getSubgenre().getName() + " " + s.getDuration() + "\n");
        }
        return sb.toString();
    }

    /**
     * Get a list in array of strings format.
     * @param title    the identification of the list
     * @return      a string representing the list
     */
    public String[] getListStringArray(String title) {
        List list = listSet.getList(title);
        ArrayList<Song> songs = list.obtainSongs();
        String result[] = new String[songs.size()+1];
        result[0] = list.obtainTitle();
        for (int i = 0; i < songs.size(); ++i)
            result[i+1] = songs.get(i).getTitle() + " | " + songs.get(i).getArtist();
        return result;
    }

    /**
     * Get all the lists in the set.
     * @return an array of lists
     */
    public ArrayList<List> getLists() {
        return listSet.getLists();
    }

    /**
     * Get all the lists in string format.
     * @return  a string with the lists titles and their identifiers
     */
    public String getListSetString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<List> lists = listSet.getLists();
        for (List list : lists)
            sb.append(list.obtainTitle() + "\n");
        return sb.toString();
    }

    /**
     * Get all the list in array of strings format.
     * @return  an array of strings with the lists titles and their identifiers
     */
    public String[] getListSetStringArray() {
        ArrayList<List> lists = listSet.getLists();
        String result[] = new String[lists.size()];
        for (int i = 0; i < lists.size(); ++i)
            result[i] = lists.get(i).obtainTitle();
        return result;
    }

    public String[] getSongId(int listIndex, int songIndex) {
        return listSet.getSongId(listIndex,songIndex);
    }

    /**
     * Save the list set in the specified path.
     * @param path  the path where save the list set to
     */
    public void save(String path) throws IOException {
        DataController.save(listSet.toString(),path);
    }

    /**
     * Load the list set from the specified path.
     * @param path  the path where load the list set from
     */
    public void load(String path, SongController songController) throws PropException, IOException {
        String serialized = DataController.load(path);
        listSet = valueOf(serialized,songController);
    }

    /**
     * Parse a string to a {@code ListSet} object.
     * @param str               the string representing the list set
     * @param songController    a songController instance
     * @return                  the {@code ListSet} object created from the string
     * @throws PropException
     */
    private ListSet valueOf(String str, SongController songController) throws PropException {
        String[] lists = str.split(Pattern.quote(String.valueOf(setDelimiter)));
        ListSet listSet = new ListSet();
        for (int i = 1; i < Integer.parseInt(lists[0])+1; ++i) {
            String[] songs = lists[i].split(Pattern.quote(String.valueOf(elemDelimiter)));
            List list = new List(songs[0]);
            for (int j = 2; j < Integer.parseInt(songs[1])*2+2; j += 2) {
                Song s = songController.getSong(songs[j], songs[j+1]);
                list.addSong(s);
            }
            listSet.add(list);
        }
        return listSet;
    }

}
