package prop.domain;

import java.lang.String;
import java.lang.StringBuilder;
import java.util.ArrayList;

/**
 * Song Controller
 * @author  joaquim.motger
 * @see     Song
 * @see     SongSet
 */

public class SongController {

    SongSet songSet;

    /**
     * Song Controller constructor
     */
    public SongController() {
        songSet = new SongSet();
    }

    /**
     * Add a new <b>song</b> to the set
     * @param title     song title
     * @param artist    song artist
     * @param album     song album
     * @param year      year of release
     * @param genre     song genre
     * @param subgenre  song subgenre
     * @param duration  song duration in seconds
     * @return          true if the song was added; false if the song was present and not added
     */
    public boolean addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration) {
        Song song = new Song(title, artist, album, year, genre, subgenre, duration);
        return songSet.addSong(song);
    }

    /**
     * Remove a <b>song</b> of the set
     * @param title     song title
     * @param artist    song artist
     * @return          true if the song was removed; false if the song was not present
     */
    public boolean removeSong(String title, String artist) {
        Song song = songSet.removeSong(title, artist);
        if (song != null) return true;
        else return false;
    }

    /**
     * Edit an attribute of a song
     * @param title     song title
     * @param artist    song artist
     * @param pair      a pair defining attribute and new value
     */
    public void editSong(String title, String artist, Pair<String, String> pair) {
        Song song = songSet.getSong(title, artist);
        if (song != null) {
            switch(pair.first) {
                case "title":
                    song.setTitle(pair.second);
                    break;
                case "artist":
                    song.setArtist(pair.second);
                    break;
                case "album":
                    song.setAlbum(pair.second);
                    break;
                case "year":
                    song.setYear(Integer.parseInt(pair.second));
                    break;
                case "genre":
                    song.setGenre(getGenreByName(pair.second));
                    break;
                case "subgenre":
                    song.setSubgenre(getGenreByName(pair.second));
                    break;
                case "duration":
                    song.setDuration(Integer.parseInt(pair.second));
                    break;
            }
        }
    }
    /**
     * Get the songSet in String
     * @return  string with all songs in songSet
     */
    public String getSongSet() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Song> songs = songSet.getSongSet();
        for (Song song : songs) {
            sb.append(song.getTitle() + "\t" + song.getArtist() + "\t" + song.getAlbum() + "\t" +
                        Integer.toString(song.getYear()) + "\t" + song.getGenre().getName() + "\t" +
                        song.getSubgenre().getName() + "\t" + Integer.toString(song.getDuration()) + "\n");
        }
        return sb.toString();
    }

    /**
     * Get a song
     * @param title     song title
     * @param artist    song artist
     * @return          song with title and artist required
     */
    public Song getSong(String title, String artist) {
        return songSet.getSong(title, artist);
    }

    /**
     * Search songs with the defined values for specified attributes
     * @param l     list with pairs of attributes and value to define search
     * @return      string with all the songs that match the search
     */
    public String searchSongs(ArrayList< Pair<String, String> > l) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Song> songs = songSet.getSongSet();
        for (Song song : songs) {
            boolean match = true;
            for (Pair<String, String> pair : l) {
                switch(pair.first) {
                    case "title":
                        match = song.getTitle().equals(pair.second);
                        break;
                    case "artist":
                        match = song.getArtist().equals(pair.second);
                        break;
                    case "album":
                        match = song.getAlbum().equals(pair.second);
                        break;
                    case "year":
                        match = Integer.toString(song.getYear()).equals(pair.second);
                        break;
                    case "genre":
                        match = song.getGenre().getName().equals(pair.second);
                        break;
                    case "subgenre":
                        match = song.getSubgenre().getName().equals(pair.second);
                        break;
                    case "duration":
                        match = Integer.toString(song.getDuration()).equals(pair.second);
                        break;
                }
                if (!match) break;
            }
            if (match) {
                sb.append(song.getTitle() + "\t" + song.getArtist() + "\t" + song.getAlbum() + "\t" +
                        Integer.toString(song.getYear()) + "\t" + song.getGenre().getName() + "\t" +
                        song.getSubgenre().getName() + "\t" + Integer.toString(song.getDuration()) + "\n");
            }
        }
    }

    /**
     * Save the songSet in the specified path
     * @param path      path to save the songSet
     */
    public void save(String path) {

    }

    /**
     * Load the songSet in the specified path
     * @param path      path to load the songSet
     */
    public void load(String path) {

    }
}



