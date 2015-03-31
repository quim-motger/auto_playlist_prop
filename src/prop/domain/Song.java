package prop.domain;

/**
 * A song
 * @author oscar.manas
 */
public class Song {

    private int id;
    private String title;
    private String artist;
    private String album;
    private int year;
    private String genre;
    private String subgenre;
    private int duration;

    /**
     * Constructor specifying a value for all attributes
     * @param id        song ID
     * @param title     song title
     * @param artist    song artist
     * @param album     song album
     * @param year      year of release
     * @param genre     song genre
     * @param subgenre  song subgenre
     * @param duration  song duration in seconds
     */
    public Song(int id, String title, String artist, String album, int year, String genre, String subgenre, int duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.subgenre = subgenre;
        this.duration = duration;
    }

    public int getId(){
        return id;
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

    public String getGenre() {
        return genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public int getDuration() {
        return duration;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
