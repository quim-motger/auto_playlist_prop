package prop.presentation;

import prop.PropException;
import prop.domain.SongController;

public class SongPController {

    private SongController songController;

    /**
     * Creates a new SongPController
     */
    public SongPController() {
        songController = new SongController();
    }

    /**
     * Returns the SongController of the SongPController
     * @return
     */
    public SongController getSongController() {
        return songController;
    }

    /**
     * Adds a new song to songController
     * @param title     song title
     * @param artist    song artist
     * @param album     song album
     * @param year      song year
     * @param genre     song genre
     * @param subgenre  song subgenre
     * @param duration  song duraiton
     * @throws PropException
     */
    public void addSong(String title, String artist, String album, String year, String genre, String subgenre, String duration) throws PropException {
        // Genres should be created in the domain tier, so songController.addSong should get an integer as a Genre id
        songController.addSong(title,artist,album,year,genre,subgenre,duration);
    }

    /**
     * Removes a song from songController
     * @param title     song title
     * @param artist    song artist
     * @throws PropException
     */
    public void removeSong(String title, String artist) throws PropException {
        songController.removeSong(title, artist);
    }

    /**
     * Finds all songs that matches with prefix
     * @param prefix    the substring
     * @return  a String with all songs found
     */
    public String findSongsByName(String prefix) {
       return songController.findSongsByName(prefix);
    }

    /**
     * Get all songs in songController
     * @return  a String with all songs
     */
    public String findSongs() {
        return songController.getList();
    }

    /**
     * Gets all genres
     * @return  a String[] with all genres
     */
    public String[] listGenres() {
        return songController.listGenres();
    }

    /**
     * Gets a song in string format
     * @param title     song title
     * @param artist    song artist
     * @return          a String with the song
     * @throws PropException
     */
    public String getSong(String title, String artist) throws PropException {
        return songController.getSongString(title, artist);
    }

    /**
     * saves the SongController status
     * @param path  path of the file
     * @throws Exception
     */
    public void save(String path) throws Exception {
        songController.save(path);
    }

    /**
     * loads a SongController status
     * @param path  path of the file
     * @throws Exception
     */
    public void load(String path) throws Exception {
        songController.load(path);
    }

    /**
     * edits a song attribute
     * @param title     song title
     * @param artist    song artist
     * @param attr      attribute to modify
     * @param val       new value
     * @throws PropException
     */
    public void edit(String title, String artist, String attr, String val) throws PropException {
        songController.editSong(title, artist, attr, val);
    }

    /**
     * Search songs by matching a series of attributes
     * @param p     a String with attributes and values
     * @return      songs found
     * @throws PropException
     */
    public String searchSongs(String p) throws PropException {
        return songController.searchSongs(p);
    }

    /**
     * remove all songs from songController
     */
    public void removeAllSongs() {
        songController.removeAllSongs();
    }

    public int load(String path, int line) throws Exception {
        return songController.load(path,line);
    }
}
