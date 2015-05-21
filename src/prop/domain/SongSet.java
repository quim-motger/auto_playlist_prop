package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A set of songs.
 * @author oscar.manas
 * @see Song
 */
public class SongSet {

    private HashMap<String,Song> songSet;

    private static final String delimiter = "\n";

    /**
     * Default constructor.
     */
    public SongSet() {
        songSet = new HashMap<String,Song>();
    }

    /**
     * Get the size of the set.
     * @return  the size of the set
     */
    public int size() {
        return songSet.size();
    }

    /**
     * Get the whole set of songs.
     * @return  the set of songs
     */
    public ArrayList<Song> getSongSet() {
        // This is a shallow copy, so the songs are not duplicated
        return new ArrayList<Song>(songSet.values());
    }

    /**
     * Get a song identified by title and artist.
     * @param title             the title of the song to get
     * @param artist            the artist of the song to get
     * @return                  the song if present
     * @throws PropException    if the song is not present
     */
    public Song getSong(String title, String artist) throws PropException {
        String key = getKey(title,artist);
        Song s = songSet.get(key);
        if (s == null) throw new PropException(ErrorString.UNEXISTING_SONG);
        return s;
    }

    /**
     * Get a group of songs.
     * @param ids   the identifications of the songs to get; the first element of a pair is the {@code title}
     *              and the second element is the {@code artist} of the song
     * @return      list of present songs
     */
    public ArrayList<Song> getSongs(ArrayList<Pair<String,String>> ids) throws PropException {
        ArrayList<Song> songList = new ArrayList<Song>();
        for (Pair<String,String>id : ids) {
            Song c = getSong(id.first(),id.second());
            if (c != null)
                songList.add(c);
        }
        return songList;
    }

    /**
     * Add a song to the set.
     * @param song              the song to add
     * @throws PropException    if the song already exists or if the {@code song} parameter is null
     */
    public void addSong(Song song) throws PropException {
        String title = song.getTitle();
        String artist = song.getArtist();
        if (!contains(title,artist)) {
            String key = getKey(title,artist);
            songSet.put(key,song);
        }
        else throw new PropException(ErrorString.EXISTING_SONG);
    }

    /**
     * Remove a song from the set.
     * @param title             the title of the song
     * @param artist            the artist of the song
     * @throws PropException    if the song is not present in the set
     */
    public void removeSong(String title, String artist) throws PropException {
        String key = getKey(title,artist);
        songSet.remove(key);
    }

    /**
     * Returns true if the song set contains the specified song.
     * @param title     the title of the specified song
     * @param artist    the artist of the specified song
     * @return          true if present,
     *                  false if not present
     */
    public boolean contains(String title, String artist) {
        String key = getKey(title,artist);
        return songSet.containsKey(key);
    }

    /**
     * Get the total duration of all songs in the set.
     * @return  the total duration
     */
    public int getTotalDuration() {
        int sum = 0;
        for (Song song : songSet.values()) {
            sum += song.getDuration();
        }
        return sum;
    }

    /**
     * Search for songs that have the specified values for the specified attributes.
     * @param conditions    the list of conditions that searched songs must satisfy; the first element of a pair
     *                      is the {@code attribute} and the second element is the {@code value} of the condition
     * @return              a list of songs that meet the conditions
     */
    public ArrayList<Song> searchSongs(ArrayList<Pair<String,String>> conditions) throws PropException {
        ArrayList<Song> songs = new ArrayList<Song>();
        for (Song song : songSet.values()) {
            boolean valid = true;
            for (Pair<String,String> condition : conditions) {
                if (!satisfies(song,condition.first(),condition.second())) {
                    valid = false;
                    break;
                }
            }
            if (valid)
                songs.add(song);
        }
        return songs;
    }

    /**
     * Returns true if the {@code song} has the specified {@code value} for the specified {@code attribute}.
     * @param song              the song to validate
     * @param attribute         the attribute to validate
     * @param value             the value that the attribute of the song must have
     * @return                  true if the song has the specified value for the specified attribute,
     *                          false otherwise
     * @throws PropException    if the parameter {@code attribute} is not valid attribute name
     */
    private boolean satisfies(Song song, String attribute, String value) throws PropException {
        switch (attribute) {
            case "title":
                return song.getTitle().equals(value);
            case "artist":
                return song.getArtist().equals(value);
            case "album":
                return song.getAlbum().equals(value);
            case "year":
                return song.getYear() == Integer.parseInt(value);
            case "genre":
                return song.getGenre().getId() == Integer.parseInt(value);
            case "subgenre":
                return song.getSubgenre().getId() == Integer.parseInt(value);
            case "duration":
                return song.getDuration() == Integer.parseInt(value);
            default:
                throw new PropException(ErrorString.UNEXISTING_ATTRIBUTE);
        }
    }

    /**
     * Convert a song set into a String.
     * @return  the String representing the song set
     */
    public String toString() {
        String s = "";
        for (Song song : songSet.values()) {
            s += song.toString() + delimiter;
        }
        return s;
    }

    private String getKey(String title, String artist) {
        return title + ":" + artist;
    }

}
