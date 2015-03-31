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
     * @return  the size of the set
     */
    public int size() {
        return songSet.size();
    }

    /**
     * Add a song to the set
     * @param song  the song to add
     * @return      true if the song was added
     *              false if the song was present, and thus not added
     */
    public boolean addSong(Song song) {
        if (searchSong(song.getId()) == null) {
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
        int i = searchSongIndex(id);
        if (i != -1)
            return songSet.remove(i);
        else
            return null;
    }

    /**
     * Search for a group of songs
     * @param ids    list of identifications of the songs to search
     * @return      list of present songs
     */
    public ArrayList<Song> searchSongs(int[] ids) {
        ArrayList<Song> songList = new ArrayList<Song>();
        for (int i = 0; i < ids.length; ++i) {
            Song c = searchSong(ids[i]);
            if (c != null)
                songList.add(c);
        }
        return songList;
    }

    /**
     * Search for a song
     * @param id    the identification of the song to search
     * @return      the song if present
     *              null if not present
     */
    public Song searchSong(int id) {
        for (int i = 0; i < songSet.size(); ++i) {
            if (songSet.get(i).getId() == id)
                return songSet.get(i);
        }
        return null;
    }

    /**
     * Search for a song index
     * @param id    the identification of the song to search
     * @return      the index of the song in the set if present
     *              -1 if not present
     */
    private int searchSongIndex(int id) {
        for (int i = 0; i < songSet.size(); ++i) {
            if (songSet.get(i).getId() == id)
                return i;
        }
        return -1;
    }
}
