package prop.domain;

import prop.ErrorString;
import prop.data.DataController;

import java.io.IOException;
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

    private final static String delimiter = "\n\n";

    /**
     * Song Controller constructor
     */
    public SongController() {
        songSet = new SongSet();
    }

    /**
     * Get the size of the set
     * @return  the size of the set
     */
    public int size() {
        return songSet.size();
    }

    /**
     * Get the song set
     * @return  the song set
     */
    public SongSet getSongSet() { return songSet;}

    /**
     * Add a new <code>song</code> to the set
     * @param title     song title
     * @param artist    song artist
     * @param album     song album
     * @param year      year of release
     * @param genre     song genre
     * @param subgenre  song subgenre
     * @param duration  song duration in seconds
     * @throws Exception
     */
    public void addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration) throws Exception {
        Song song = new Song(title, artist, album, year, genre, subgenre, duration);
        songSet.addSong(song);
    }

    /**
     * Add a new <code>song</code> to the <code>songSet</code> of the <code>songController</code>
     * @param song  the song to be added
     * @throws Exception
     */
    public void addSong(Song song) throws Exception {
        songSet.addSong(song);
    }

    /**
     * Remove a <code>song</code> of the set
     * @param title     song title
     * @param artist    song artist
     * @throws  Exception
     */
    public void removeSong(String title, String artist) throws Exception {
        songSet.removeSong(title, artist);
    }

    /**
     * Edit an attribute of a song
     * @param title     song title
     * @param artist    song artist
     * @param pair      a pair defining attribute and new value
     * @throws  Exception
     */
    public void editSong(String title, String artist, Pair<String, String> pair) throws Exception {
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
                    song.setGenre(Genre.getGenreByName(pair.second));
                    break;
                case "subgenre":
                    song.setSubgenre(Genre.getGenreByName(pair.second));
                    break;
                case "duration":
                    song.setDuration(Integer.parseInt(pair.second));
                    break;
                default:
                    throw new Exception(ErrorString.UNEXISTING_ATTRIBUTE);
            }
        }
    }
    /**
     * Get the songSet in String
     * @return  string with all songs in songSet
     */
    public String getSongSetString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Song> songs = songSet.getSongSet();
        for (Song song : songs) {
            sb.append(song.toString()+delimiter);
        }
        return sb.toString();
    }

    /**
     * Get a song identified by title and artist
     * @param title         the title of the song to get
     * @param artist        the artist of the song to get
     * @return              the song if present
     * @throws Exception    if the song is not present
     */
    public Song getSong(String title, String artist) throws Exception {
        return songSet.getSong(title, artist);
    }

    /**
     * Get a song by position
     * @param i             the position of the song to get
     * @return              the song if present
     * @throws Exception    if the song is not present
     */
    public Song getSong(int i) throws Exception {
        return songSet.getSong(i);
    }

    /**
     * Search songs with the defined values for specified attributes
     * @param l     list with pairs of attributes and value to define search
     * @return      string with all the songs that match the search
     */
    public String searchSongs(ArrayList< Pair<String, String> > l) throws Exception {
        ArrayList<Song> songs = songSet.searchSongs(l);
        String s = "";
        for (Song song : songs) {
            s += song.getTitle() + " " + song.getArtist() + " " + song.getAlbum() + " " + song.getYear() +
                    " " + song.getGenre().getName() + " " + song.getSubgenre().getName() + " " + song.getDuration() + "\n";
        }
        return s;
    }

    /**
     * list of all Genres
     * @return  a String with the list of all genres
     */
    public String listGenres() {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < 132; ++i) {
            sb.append(Genre.getGenreById(i).getId() + ": " + Genre.getGenreById(i).getName() + "\n");
        }
        return sb.toString();
    }

    /**
     * Save the songSet in the specified path
     * @param path      path to save the songSet
     */
    public void save(String path) {
        try {
            DataController.save(getSongSetString(), path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Load the songSet in the specified path
     * @param path      path to load the songSet
     */
    public void load(String path) {
        String s;
        try {
            s = DataController.load(path);
            this.songSet = SongSet.valueOf(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



