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

    private final static String set_delimiter = "\n";
    private final static String delimiter2 = "|";
    private static final int SAVING_BLOCK = 20;
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
        else if (title.contains("|") || artist.contains("|") || album.contains("|")) throw new PropException(ErrorString.ILLEGAL_CHARACTER);
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

    /**
     * Finds songs by name
     * @param prefix prefix to be searched
     * @return Songs starting with prefix
     */
    public String findSongsByName(String prefix) {
        ArrayList<Song> songs = songSet.findSongs(prefix);
        String p = "";
        for (Song song : songs) {
            p += song.getTitle() + " - " + song.getArtist() + "\n";
        }
        return p;
    }

    /**
     * get Song List 
     * @return String with all the songs to string
     */
    public String getList() {
        return songSet.getSongList();
    }

    /**
     * get artists 
     * @return array of artists
     */
    public ArrayList<String> getArtists() {
        ArrayList<String> artists = new ArrayList<String>();
        for (Song s : songSet.getSongSet())
            artists.add(s.getArtist());
        return artists;
    }

    /**
     * Get song titles from artists
     * @param artist artist to be searched
     * @return
     * @throws PropException
     */
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
            try {
                genres[i] = Genre.getGenreById(i).getName();
            } catch (PropException e) {
                e.printStackTrace();
            }
        }
        return genres;
    }

    /**
     * saves in a textfile the songSet of the implicit SongController
     * @param path  the path to save the SongSet content
     * @throws Exception if path is not correct
     */
    public void save(String path) throws Exception {
        save(path,false);
    }

    /**
     * Save the songSet in the specified path
     * @param path      path to save the songSet
     */
    public void save(String path, boolean append) throws Exception {
        DataController dc = new DataController();
        dc.open(path);
        if(!append)
            dc.deleteContent();
        ArrayList<Song> songs = songSet.getSongSet();
        boolean saved = false;
        int usersSaved = 0;
        while (usersSaved<songSet.size()){
            String cached = "";
            while(usersSaved<songSet.size() && (usersSaved%SAVING_BLOCK!=SAVING_BLOCK-1 || saved)) {
                cached =cached + songs.get(usersSaved).toString();
                cached = cached + set_delimiter;
                ++usersSaved;
                saved = false;
            }
            dc.append(cached);
            saved = true;
        }
        dc.append("\n");
    }

    /**
     * Load the songSet in the specified path
     * @param path      path to load the songSet
     */
    public void load (String path) throws Exception {
        load(path,0);
    }

    /**
     * Load the songSet in the specified path
     * @param path      path to load the songSet
     */
    public int load(String path, int line) throws Exception {
        DataController dc = new DataController();
        dc.open(path);
        dc.openToRead();
        removeAllSongs();
        int currentLine = 0;
        while (currentLine<line) {
            dc.readLine();
            ++currentLine;
        }
        ++currentLine;
        String songString = dc.readLine();
        while(songString != null && !songString.equals("")) {
            songSet.addSong(valueOfSong(songString));
            songString = dc.readLine();
            ++currentLine;
        }
        return currentLine;
    }

    private Song valueOfSong(String p) throws PropException {
        String[] tokens = p.split(Pattern.quote(delimiter2));
        if (tokens.length != 7) throw new PropException(ErrorString.CORRUPT_FILE);
        return new Song(tokens[0],tokens[1],tokens[2],
                Integer.parseInt(tokens[3]),
                Genre.getGenreById(Integer.parseInt(tokens[4])),
                Genre.getGenreById(Integer.parseInt(tokens[5])),
                Integer.parseInt(tokens[6]));
    }

    /**
     * obtains a String with the Song defined by title and artist
     * @param title     song title
     * @param artist    song artist
     * @return          a String with all attributes of the song
     * @throws PropException
     */
    public String getSongString(String title, String artist) throws PropException {
        Song song = songSet.getSong(title, artist);
        String s = song.getTitle() + "|" + song.getArtist() + "|" + song.getAlbum() + "|"
                + song.getYear() + "|" + song.getGenre().getId() + "|" + song.getSubgenre().getId() + "|"
                + song.getDuration();
        return s;
    }
}



