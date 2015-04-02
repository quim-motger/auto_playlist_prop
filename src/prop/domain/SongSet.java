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
     * @return      true if the song was added,
     *              false if the song was present, and thus not added
     */
    public boolean addSong(Song song) {
        if (!contains(song.getTitle(),song.getArtist())) {
            songSet.add(song);
            return true;
        }
        else
            return false;
    }

    /**
     * Remove a song from the set
     * @param title     the title of the song
     * @param artist    the artist of the song
     * @return          the song if present,
     *                  null if not present
     */
    public Song removeSong(String title, String artist) {
        int i = getSongIndex(title,artist);
        if (i != -1)
            return songSet.remove(i);
        else
            return null;
    }

    /**
     * Returns true if the song set contains the specified song
     * @param title     the title of the specified song
     * @param artist    the artist of the specified song
     * @return          true if present,
     *                  false if not present
     */
    public boolean contains(String title, String artist) {
        for (Song song : songSet) {
            if (song.getTitle().equals(title) && song.getArtist().equals(artist))
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
        for (Song song : songSet) {
            sum += song.getDuration();
        }
        return sum;
    }

    /**
     * Get a group of songs
     * @param titles            list of titles of the songs to get
     * @param artists           list of artist of the songs to get
     * @throws RuntimeException if titles.length != artists.length
     * @return                  list of present songs
     */
    public ArrayList<Song> getSongs(String[] titles, String[] artists) {
        if (titles.length != artists.length)
            throw new RuntimeException("titles and artists arrays should have the same length");
        ArrayList<Song> songList = new ArrayList<Song>();
        for (int i = 0; i < titles.length; ++i) {
            Song c = getSong(titles[i],artists[i]);
            if (c != null)
                songList.add(c);
        }
        return songList;
    }

    /**
     * Get a song
     * @param title     the title of the song to get
     * @param artist    the artist of the song to get
     * @return          the song if present,
     *                  null if not present
     */
    public Song getSong(String title, String artist) {
        for (Song song : songSet) {
            if (song.getTitle().equals(title) && song.getArtist().equals(artist))
                return song;
        }
        return null;
    }

    /**
     * Get the song index within the song set
     * @param title     the title of the song to search
     * @param artist    the artist of the song to search
     * @return          the index of the song in the set if present,
     *                  -1 if not present
     */
    private int getSongIndex(String title, String artist) {
        for (int i = 0; i < songSet.size(); ++i) {
            Song song = songSet.get(i);
            if (song.getTitle().equals(title) && song.getArtist().equals(artist))
                return i;
        }
        return -1;
    }
}
