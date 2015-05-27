package prop.domain;

import prop.ErrorString;
import prop.PropException;
import prop.data.DataController;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Song Controller
 * @author  joaquim.motger
 * @see     Song
 * @see     SongSet
 */

public class SongController {

    private final static String delimiter1 = "\n";
    private final static String delimiter2 = "|";
    SongSet songSet;

    /**
     * Song Controller constructor
     */
    public SongController() {
        songSet = new SongSet();
    }

    public void removeAllSongs() {
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
    public void addSong(String title, String artist, String album, String year, String genre, String subgenre, String duration) throws PropException {
        if (year.length() == 0) throw new PropException(ErrorString.MISSING_YEAR);
        else if (duration.length() == 0) throw new PropException(ErrorString.MISSING_DURATION);
        int y; int dur;
        try {
            y = Integer.parseInt(year.trim());
        } catch (Exception e) {
            throw new PropException(ErrorString.INVALID_YEAR);
        }
        try {
            dur = Integer.parseInt(duration.trim());
        } catch (Exception e) {
            throw new PropException(ErrorString.INVALID_DURATION);
        }
        Song song = new Song(
                title.trim(),
                artist.trim(),
                album.trim(),
                y,
                Genre.getGenreById(Integer.parseInt(genre)),
                Genre.getGenreById(Integer.parseInt(subgenre)),
                dur);
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
     * @param attr
     * @param val
     * @throws  PropException
     */
    public void editSong(String title, String artist, String attr, String val) throws PropException {
        Song song = songSet.getSong(title, artist);
        if (song != null) {
            switch(attr) {
                case "title":
                    songSet.removeSong(title,artist);
                    song.setTitle(val);
                    songSet.addSong(song);
                    break;
                case "artist":
                    songSet.removeSong(title,artist);
                    song.setArtist(val);
                    songSet.addSong(song);
                    break;
                case "album":
                    song.setAlbum(val);
                    break;
                case "year":
                    song.setYear(Integer.parseInt(val));
                    break;
                case "genre":
                    song.setGenre(Genre.getGenreById(Integer.parseInt(val)));
                    break;
                case "subgenre":
                    song.setSubgenre(Genre.getGenreById(Integer.parseInt(val)));
                    break;
                case "duration":
                    song.setDuration(Integer.parseInt(val));
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

    public String getSongString(String title, String artist) throws PropException {
        Song song = songSet.getSong(title, artist);
        String s = song.getTitle() + "|" + song.getArtist() + "|" + song.getAlbum() + "|"
                + song.getYear() + "|" + song.getGenre().getId() + "|" + song.getSubgenre().getId() + "|"
                + song.getDuration();
        return s;
    }

    /**
     * Search songs with the defined values for specified attributes
     * @param criteria    list with pairs of attributes and value to define search
     * @return      string with all the songs that match the search
     */
    public String searchSongs(String criteria) throws PropException {
        String[] p = criteria.split(Pattern.quote("\n"));
        ArrayList<Pair<String, String>> l = new ArrayList<>();
        for (String s : p) {
            String[] pp = s.split(Pattern.quote("|"));
            l.add(new Pair<>(pp[0],pp[1]));
        }
        ArrayList<Song> songs = songSet.searchSongs(l);
        String s = "";
        for (Song song : songs) {
            s += song.getTitle() + " | " + song.getArtist() + " | " + song.getAlbum() + " | " + song.getYear() +
                    " | " + song.getGenre().getName() + " | " + song.getSubgenre().getName() + " | " + song.getDuration() + "\n";
        }
        return s;
    }

    public ArrayList<Song> searchSongsList(ArrayList<Pair<String, String>> l) throws PropException {
        return songSet.searchSongs(l);
    }

    public String findSongsByName(String prefix) {
        ArrayList<Song> songs = songSet.findSongs(prefix);
        String p = "";
        for (Song song : songs) {
            p += song.getTitle() + " - " + song.getArtist() + "\n";
        }
        return p;
    }

    public String getList() {
        return songSet.getSongList();
    }

    public ArrayList<String> getArtists() {
        ArrayList<String> artists = new ArrayList<String>();
        for (Song s : songSet.getSongSet())
            artists.add(s.getArtist());
        return artists;
    }

    public ArrayList<String> getTitlesFromArtist(String artist) throws PropException {
        ArrayList<String> titles = new ArrayList<String>();
        Pair<String,String> cond = new Pair<>("song_artist",artist);
        ArrayList<Pair<String,String>> conditions = new ArrayList<>();
        conditions.add(cond);
        ArrayList<Song> songs = songSet.searchSongs(conditions);
        for (Song s : songs)
            titles.add(s.getTitle());
        return titles;
    }

    /**
     * list of all Genres
     * @return  a String with the list of all genres
     */
    public String[] listGenres() {
        int m = Genre.values().length;
        String[] genres = new String[m];
        int i;
        for (i = 0; i < m; ++i) {
            genres[i] = Genre.getGenreById(i).getName();
        }
        return genres;
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
            if (tokens.length != 7) throw new PropException(ErrorString.CORRUPT_FILE);
            Song song = new Song(tokens[0],tokens[1],tokens[2],
                    Integer.parseInt(tokens[3]),
                    Genre.getGenreById(Integer.parseInt(tokens[4])),
                    Genre.getGenreById(Integer.parseInt(tokens[5])),
                    Integer.parseInt(tokens[6]));
            songSet.addSong(song);
        }
    }
}



