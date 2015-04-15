package prop.domain;

import java.util.ArrayList;

/**
 * A set of songs
 * @author oscar.manas
 */
public class SongSet {

    private ArrayList<Song> songSet;

    private static final char delimiter = '\n';

    /**
     * Constructor
     */
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
     * Get a group of songs
     * @param ids   the identifications of the songs to get; the first element of a pair is the {@code title}
     *              and the second element is the {@code artist} of the song
     * @return      list of present songs
     */
    public ArrayList<Song> getSongs(ArrayList<Pair<String,String>> ids) {
        ArrayList<Song> songList = new ArrayList<Song>();
        for (Pair<String,String>id : ids) {
            Song c = getSong(id.first,id.second);
            if (c != null)
                songList.add(c);
        }
        return songList;
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

    /**
     * Search for songs that have the specified values for the specified attributes
     * @param conditions    the list of conditions that searched songs must satisfy; the first element of a pair
     *                      is the {@code attribute} and the second element is the {@code value} of the condition
     * @return              a list of songs that meet the conditions
     */
    public ArrayList<Song> searchSongs(ArrayList<Pair<String,String>> conditions) throws Exception {
        ArrayList<Song> songs = new ArrayList<Song>();
        for (Song song : songSet) {
            boolean valid = true;
            for (Pair<String,String> condition : conditions) {
                if (!satisfies(song,condition.first,condition.second)) {
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
     * Returns true if the {@code song} has the specified {@code value} for the specified {@code attribute}
     * @param song          the song to validate
     * @param attribute     the attribute to validate
     * @param value         the value that the attribute of the song must have
     * @return              true if the song has the specified value for the specified attribute,
     *                      false otherwise
     * @throws Exception    if the parameter {@code attribute} is not valid attribute name
     */
    private boolean satisfies(Song song, String attribute, String value) throws Exception {
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
                throw new Exception("invalid attribute name");
        }
    }

    public String toString() {
        String s = "";
        int i;
        for (i = 0; i < songSet.size()-1; ++i) {
            s += songSet.get(i).toString() + delimiter;
        }
        s += songSet.get(i);
        return s;
    }

    public static SongSet valueOf(String s) {
        String[] songs = s.split(String.valueOf(delimiter));
        SongSet ss = new SongSet();
        for (String r : songs) {
            ss.addSong(Song.valueOf(r));
        }
        return ss;
    }

}
