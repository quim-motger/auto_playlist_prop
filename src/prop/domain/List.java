package prop.domain;

import java.util.ArrayList;

/**
 * Conjunt de songs ordenades
 * @author gerard.casas.saez
 * @version 1.0
 * @see Song
 */
public class List {
    
    String title;
    ArrayList<Song> songs;

    /**
     * <code>List</code> creator method
     * @param listTitle   Title of the list
     */
    public List(String listTitle) { title =listTitle;}

    /**
     * Obtain <code>List</code> title
     * @return title of the <code>List</code>
     */
    public String obtainTitle() {return title;}

    /**
     * Obtain the song at the position <code>pos</code>
     * @param pos   <code>pos</code> is smaller than <code>obtainListSize()</code> and bigger than 0
     * @return      <code>Song/code> of the list in the position <code>pos</code>
     */
    public Song obtainSong(int pos) {
        return songs.get(pos);
    }

    /**
     *  Obtain the number of songs in the list
     * @return Number of songs in the list
     */
    public int obtainListSize() { return songs.size();}

    /**
     * Obtain the addition of all the songs
     * @return  total time of the list
     */
    public int obtainTotalTime() {
        int total=0;
        for (int i=0; i < obtainListSize();++i) {
            total += songs.get(i).getDuration();
        }
        return total;
    }

    /**
     *  Modifier of the List title
     * @param title String that we want the list to be named
     */
    public void editTitle(String title) {
        this.title =title;
    }

    /**
     *  Modifier that adds song at the end of the <code>List</code>
     *  <code>song</code> has an index of <code>obtainListSize()</code> - 1
     *  @see Song
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     *  Modifier that deletes a song from the list
     *  The songs below the deleted one get promoted one position to fill the blank
     * @param pos   0 < <code>pos</code> < <code>obtainListSize()</code>
     */
    public void deleteSong(int pos) {
        songs.remove(pos);
    }

    /**
     * Modifier of order inside the list
     * Swaps two songs inside the list
     * @param index1    0 < <code>index1</code> < <code>obtainListSize()</code>
     * @param index2    0 < <code>index2</code> < <code>obtainListSize()</code>
     */
    public void swapSongs(int index1, int index2) {
        Song song= songs.get(index1);
        songs.set(index1, songs.get(index2));
        songs.set(index2, song);
    }
}
