package prop.domain;

import java.util.ArrayList;

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
     * @param id        the identification of the specified list
     * @param title     the title of the song to add
     * @param artist    the artist of the song to add
     */
    public void addSong(int id, String title, String artist, SongController songController) {
        List list = listSet.getList(id);
        Song song = songController.getSong(title,artist);  // the getSong() method should be in the SongController class
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

    public void save(String path){}

    public void load(String path){}

}
