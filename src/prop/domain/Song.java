package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * A song, identified by title and artist.
 * @author oscar.manas
 */
public class Song {

    private String title;
    private String artist;
    private String album;
    private int year;
    private Genre genre;
    private Genre subgenre;
    private int duration;

    private static final String delimiter = "|";

    /**
     * Default constructor.
     */
    public Song(){}

    /**
     * Constructor specifying a value for all attributes.
     * @param _title     song title
     * @param _artist    song artist
     * @param _album     song album
     * @param _year      year of release
     * @param _genre     song genre
     * @param _subgenre  song subgenre
     * @param _duration  song duration in seconds
     */
    public Song(String _title, String _artist, String _album, int _year, Genre _genre, Genre _subgenre, int _duration) throws PropException {
        if (_title.length() == 0) throw new PropException(ErrorString.MISSING_TITLE);
        else if (_artist.length() == 0) throw new PropException(ErrorString.MISSING_ARTIST);
        else if (_album.length() == 0) throw new PropException(ErrorString.MISSING_ALBUM);
        else if (_year < 0) throw new PropException(ErrorString.INVALID_YEAR);
        else if (_duration < 0) throw new PropException(ErrorString.INVALID_DURATION);
        else {
            title = _title;
            artist = _artist;
            album = _album;
            year = _year;
            genre = _genre;
            subgenre = _subgenre;
            duration = _duration;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public String getAlbum() {
        return album;
    }

    public Genre getGenre() {
        return genre;
    }

    public Genre getSubgenre() {
        return subgenre;
    }

    public int getDuration() {
        return duration;
    }

    public void setTitle(String _title) {
        title = _title;
    }

    public void setArtist(String _artist) {
        artist = _artist;
    }

    public void setAlbum(String _album) {
        album = _album;
    }

    public void setYear(int _year) {
        year = _year;
    }

    public void setGenre(Genre _genre) {
        genre = _genre;
    }

    public void setSubgenre(Genre _subgenre) {
        subgenre = _subgenre;
    }

    public void setDuration(int _duration) {
        duration = _duration;
    }

    /**
     * Convert a {@code Song} into a {@code String}.
     * @return the {@code String} representing the song
     */
    public String toString() {
        String s = "";
        s += title + delimiter;
        s += artist + delimiter;
        s += album + delimiter;
        s += String.valueOf(year) + delimiter;
        s += String.valueOf(genre.getId()) + delimiter;
        s += String.valueOf(subgenre.getId()) + delimiter;
        s += String.valueOf(duration);

        return s;
    }

}
