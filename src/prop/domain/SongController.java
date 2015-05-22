package prop.domain;

import prop.ErrorString;
import prop.PropException;
import prop.data.DataController;

import java.io.IOException;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Song Controller
 * @author  joaquim.motger
 * @see     Song
 * @see     SongSet
 */

public class SongController {

    SongSet songSet;

    private final static String delimiter1 = "\n";
    private final static String delimiter2 = " ";

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
     * Get the songs in the set
     * @return  the songs in the set
     */
    public ArrayList<Song> getSongs() {
        return songSet.getSongSet();
    }

    /**
     * Add a new <code>song</code> to the set
     * @param title     song title
     * @param artist    song artist
     * @param album     song album
     * @param year      year of release
     * @param genre     song genre
     * @param subgenre  song subgenre
     * @param duration  song duration in seconds
     * @throws PropException
     */
    public void addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration) throws PropException {
        Song song = new Song(title, artist, album, year, genre, subgenre, duration);
        songSet.addSong(song);
    }

    /**
     * Add a new <code>song</code> to the <code>songSet</code> of the <code>songController</code>
     * @param song  the song to be added
     * @throws PropException
     */
    public void addSong(Song song) throws PropException {
        songSet.addSong(song);
    }

    /**
     * Remove a <code>song</code> of the set
     * @param title     song title
     * @param artist    song artist
     * @throws  PropException
     */
    public void removeSong(String title, String artist) throws PropException {
        songSet.removeSong(title, artist);
    }

    /**
     * Edit an attribute of a song
     * @param title     song title
     * @param artist    song artist
     * @param pair      a pair defining attribute and new value
     * @throws  PropException
     */
    public void editSong(String title, String artist, Pair<String, String> pair) throws PropException {
        Song song = songSet.getSong(title, artist);
        if (song != null) {
            switch(pair.first()) {
                case "title":
                    song.setTitle(pair.second());
                    break;
                case "artist":
                    song.setArtist(pair.second());
                    break;
                case "album":
                    song.setAlbum(pair.second());
                    break;
                case "year":
                    song.setYear(Integer.parseInt(pair.second()));
                    break;
                case "genre":
                    song.setGenre(Genre.getGenreById(Integer.parseInt(pair.second())));
                    break;
                case "subgenre":
                    song.setSubgenre(Genre.getGenreById(Integer.parseInt(pair.second())));
                    break;
                case "duration":
                    song.setDuration(Integer.parseInt(pair.second()));
                    break;
                default:
                    throw new PropException(ErrorString.UNEXISTING_ATTRIBUTE);
            }
        }
        else throw new PropException(ErrorString.UNEXISTING_SONG);
    }
    /**
     * Get the songSet in String
     * @return  string with all songs in songSet
     */
    public String getSongSetString() {
        return songSet.toString();
    }

    /**
     * Get a song identified by title and artist
     * @param title         the title of the song to get
     * @param artist        the artist of the song to get
     * @return              the song if present
     * @throws PropException    if the song is not present
     */
    public Song getSong(String title, String artist) throws PropException {
        return songSet.getSong(title, artist);
    }

    /**
     * Search songs with the defined values for specified attributes
     * @param l     list with pairs of attributes and value to define search
     * @return      string with all the songs that match the search
     */
    public String searchSongs(ArrayList< Pair<String, String> > l) throws PropException {
        ArrayList<Song> songs = songSet.searchSongs(l);
        String s = "";
        for (Song song : songs) {
            s += song.getTitle() + " " + song.getArtist() + " " + song.getAlbum() + " " + song.getYear() +
                    " " + song.getGenre().getName() + " " + song.getSubgenre().getName() + " " + song.getDuration() + "\n";
        }
        return s;
    }

    public String findSongsByName(String prefix) {
        ArrayList<Song> songs = songSet.findSongs(prefix);
        String p = "";
        for (Song song : songs) {
            p += song.getTitle() + " " + song.getArtist();
        }
        return p;
    }

    /**
     * list of all Genres
     * @return  a String with the list of all genres
     */
    public String listGenres() {
        StringBuilder sb = new StringBuilder();
        int i;
        int m = Genre.values().length;
        for (i = 0; i < m; ++i) {
            sb.append(Genre.getGenreById(i).getId() + ": " + Genre.getGenreById(i).getName() + "\n");
        }
        return sb.toString();
    }

    /**
     * Save the songSet in the specified path
     * @param path      path to save the songSet
     */
    public void save(String path) throws Exception {
        DataController.save(getSongSetString(), path);
    }

    /**
     * Load the songSet in the specified path
     * @param path      path to load the songSet
     */
    public void load(String path) throws Exception {
        String s = DataController.load(path);
        songSet = new SongSet();
        String[] songs = s.split(Pattern.quote(delimiter1));
        for (String p : songs) {
            String[] tokens = p.split(Pattern.quote(delimiter2));
            Song song = new Song(tokens[0],tokens[1],tokens[2],
                    Integer.parseInt(tokens[3]),
                    Genre.getGenreById(Integer.parseInt(tokens[4])),
                    Genre.getGenreById(Integer.parseInt(tokens[5])),
                    Integer.parseInt(tokens[6]));
            songSet.addSong(song);
        }
    }
}



