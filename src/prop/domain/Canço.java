package prop.domain;

/**
 * A song
 * @author Oscar Mañas Sanchez
 */
public class Canço {

    private String title;
    private String artist;
    private String album;
    private int year;
    private String genre;
    private String subgenre;
    private int duration;

    public Canço(String title, String artist, String album, int year, String genre, String subgenre, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.subgenre = subgenre;
        this.duration = duration;
    }

}
