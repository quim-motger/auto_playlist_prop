package prop.domain;

import java.util.ArrayList;

/**
 * A set of songs
 * @author oscar.manas
 */
public class SongSet {

    private ArrayList<Song> songSet;

    public SongSet() {
        songSet = new ArrayList<Song>();
    }

    /**
     * Get the size of the set
     * @return  the size of the set
     */
    public int size() {
        return songSet.size();
    }

    /**
     * Get the whole set of songs
     * @return  the set of songs
     */
    public ArrayList<Song> getSongSet() {
        return songSet;
    }

    /**
     * Add a song to the set
     * @param song  the song to add
     * @return      true if the song was added
     *              false if the song was present, and thus not added
     */
    public boolean addSong(Song song) {
        if (!contains(song.getId())) {
            songSet.add(song);
            return true;
        }
        else
            return false;
    }

    /**
     * Remove a song from the set
     * @param id    the identification of a song
     * @return      the song if present
     *              null if not present
     */
    public Song removeSong(int id) {
        int i = getSongIndex(id);
        if (i != -1)
            return songSet.remove(i);
        else
            return null;
    }

    /**
     * Returns true if the song set contains the specified song
     * @param id    the identification of the specified song
     * @return      true if present
     *              false if not present
     */
    public boolean contains(int id) {
        for (int i = 0; i < songSet.size(); ++i) {
            if (songSet.get(i).getId() == id)
                return true;
        }
        return false;
    }

    /**
     * Get the total duration of all songs in the set
     * @return  the total duration
     */
    public int getTotalDuration() {
        int sum = 0;
        for (int i = 0; i < songSet.size(); ++i) {
            sum += songSet.get(i).getDuration();
        }
        return sum;
    }

    /**
     * Get a group of songs
     * @param ids   list of identifications of the songs to get
     * @return      list of present songs
     */
    public ArrayList<Song> getSongsById(int[] ids) {
        ArrayList<Song> songList = new ArrayList<Song>();
        for (int i = 0; i < ids.length; ++i) {
            Song c = getSongById(ids[i]);
            if (c != null)
                songList.add(c);
        }
        return songList;
    }

    /**
     * Get a song
     * @param id    the identification of the song to get
     * @return      the song if present
     *              null if not present
     */
    public Song getSongById(int id) {
        for (int i = 0; i < songSet.size(); ++i) {
            if (songSet.get(i).getId() == id)
                return songSet.get(i);
        }
        return null;
    }

    /**
     * Get the song index within the song set
     * @param id    the identification of the song to search
     * @return      the index of the song in the set if present
     *              -1 if not present
     */
    private int getSongIndex(int id) {
        for (int i = 0; i < songSet.size(); ++i) {
            if (songSet.get(i).getId() == id)
                return i;
        }
        return -1;
    }
}
