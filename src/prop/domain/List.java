package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Sorted set of songs
 * ID -> id: int
 * @author gerard.casas.saez
 * @version 1.0
 * @see Song
 */
public class List {


    private static final char delimiter = ' ';
    private int id;
    private String title;
    private ArrayList<Song> songs;


    public List (){
        title = null;
        id=-1;
        songs = new ArrayList<>();
    }

    /**
     * Creates an empty list with the designated title
     * @param listTitle   Title of the list
     */
    public List(String listTitle) {
        id=-1;
        title = listTitle;
        songs = new ArrayList<>();
    }

    /**
     * Creates a list with the designated title and the designated songs
     * @param listTitle   Title of the list
     * @param newSongs   Array of initial songs for the list
     */
    public List(String listTitle, ArrayList<Song> newSongs) {
        id = -1;
        title = listTitle;
        songs = newSongs;
    }

    /**
     *  Obtain the number of songs in the list
     * @return Number of songs in the list
     */
    public int size() {
        return songs.size();
    }

    /**
     * Obtain <code>List</code> title
     * @return title of the <code>List</code>
     */
    public String obtainTitle() {
        return title;
    }

    /**
     * Obtain <code>Id</code>
     * @return id from the List
     */
    public int obtainId() {
        return id;
    }

    /**
     * Obtain the song at the position <code>position</code>
     * @param position   <code>position</code> is smaller than <code>size()</code> and bigger than 0
     * @return      <code>Song/code> of the list in the position <code>position</code>
     */
    public Song obtainSong(int position) {
        return songs.get(position);
    }

    /**
     * Obtain an array list of all the songs in the list
     * @return Array of Songs of the list
     */
    public ArrayList<Song> obtainSongs() {
        return songs;
    }

    /**
     * Obtains the position of a song inside the list
     * @param title     the title of the searched song
     * @param artist    the artist of the searched song
     * @return the position of the first time the song appears in the list or -1 if the song is not in the list
     */
    public int obtainSongPosition(String title, String artist) {
        for (int i = 0; i < songs.size(); ++i) {
            Song song = songs.get(i);
            if (song != null && song.getTitle().equals(title) && song.getArtist().equals(artist))
                return i;
        }
        return -1;
    }

    /**
     * Obtain the addition of all the songs
     * @return total time of the list
     */
    public int obtainTotalTime() {
        int total = 0;
        for (Song song : songs) {
            total += song.getDuration();
        }
        return total;
    }

    /**
     *Check if a song is included in the list
     * @param title     the title of the wanted song
     * @param artist    the artist of the wanted song
     * @return <code>true</code> if the song is in the list
     */
    public boolean contains(String title, String artist) {
        return obtainSongPosition(title, artist) != -1;
    }

    /**
     * Checks if the List is empty
     * @return <code>true</code> if list is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     *  Modifier of the List title
     * @param title String that we want the list to be named
     */
    public void editTitle(String title) {
        this.title = title;
    }

    /**
     * Edit List ID
     * @param listId new ID
     */
    public void editId(int listId) {
        id = listId;
    }

    /**
     *  Modifier that adds song at the end of the <code>List</code>
     *  <code>song</code> has an index of <code>size()</code> - 1
     *  @see Song
     */
    public void addSong(Song song) {
        if (song != null) songs.add(song);
    }

    /**
     *  Modifier that adds songs at the end of the <code>List</code>
     *  @param newSongs the order inside the array is the one desired to have in the list
     *  @see Song
     */
    public void addSongs(ArrayList<Song> newSongs) {
        for (int i = 0; i < newSongs.size(); ++i) {
            songs.add(newSongs.get(i));
        }
    }

    /**
     * Modifier that deletes a song from the List
     * @param title title of the song
     * @param artist artist of the song
     */
    public void removeSong(String title, String artist) {
        int pos = obtainSongPosition(title, artist);
        if (pos > 0) {
            removeSong(pos);
        }
    }

    /**
     *  Modifier that deletes a song from the list
     *  The songs below the deleted one get promoted one position to fill the blank
     * @param pos   0 < <code>pos</code> < <code>size()</code>
     */
    public void removeSong(int pos) {
        songs.remove(pos);
    }

    /**
     * Deletes all the songs from the list
     * The List <code>isEmpty()</code> after this
     */
    public void empty() {
        songs = new ArrayList<>();
    }

    /**
     * Modifier of order inside the list
     * Swaps two songs inside the list
     * @param index1    0 < <code>index1</code> < <code>size()</code>
     * @param index2    0 < <code>index2</code> < <code>size()</code>
     */
    public void swapSongs(int index1, int index2) {
        Song song = songs.get(index1);
        songs.set(index1, songs.get(index2));
        songs.set(index2, song);
    }

    /**
     * Transforms a List into a String
     * @return Representation of a List into a String
     */
    public String toString() {
        String s = "";
        s += String.valueOf(id) + delimiter;
        s += title + delimiter;
        s += String.valueOf(songs.size()) + delimiter;
        for (Song song : songs) {
            s += song.getTitle() + delimiter;
            s += song.getArtist() + delimiter;
        }
        return s;
    }

}
