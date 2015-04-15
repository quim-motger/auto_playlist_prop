package prop.domain;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * A song, identified by title and artist
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

    private static final char delimiter = '\n';

    /**
     * The default constructor
     */
    public Song(){}

    /**
     * Constructor specifying a value for all attributes
     * @param title     song title
     * @param artist    song artist
     * @param album     song album
     * @param year      year of release
     * @param genre     song genre
     * @param subgenre  song subgenre
     * @param duration  song duration in seconds
     */
    public Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.subgenre = subgenre;
        this.duration = duration;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setSubgenre(Genre subgenre) {
        this.subgenre = subgenre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

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

    public static Song valueOf(String s) {
        String[] t = s.split(String.valueOf(delimiter));
        return new Song(t[0],t[1],t[2],Integer.parseInt(t[3]),Genre.getGenreById(Integer.parseInt(t[4])),
                Genre.getGenreById(Integer.parseInt(t[5])),Integer.parseInt(t[6]));
    }

}
