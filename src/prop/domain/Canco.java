package prop.domain;

/**
 * A song
 * @author Oscar Manas Sanchez
 */
public class Canco {

    private String title;
    private String artist;
    private String album;
    private int year;
    private String genre;
    private String subgenre;
    private int duration;

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
    public Canco(String title, String artist, String album, int year, String genre, String subgenre, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.subgenre = subgenre;
        this.duration = duration;
    }

    public String consultaTitol() {
        return title;
    }

    public String consultaArtista() {
        return artist;
    }

    public int consultaAny() {
        return year;
    }

    public String consultaAlbum() {
        return album;
    }

    public String consultaGenere() {
        return genre;
    }

    public String consultaSubgenere() {
        return subgenre;
    }

    public int consultaDurada() {
        return duration;
    }

    public void modificaTitol(String title) {
        this.title = title;
    }

    public void modificaArtista(String artist) {
        this.artist = artist;
    }

    public void modificaAlbum(String album) {
        this.album = album;
    }

    public void modificaAny(int year) {
        this.year = year;
    }

    public void modificaGenere(String genre) {
        this.genre = genre;
    }

    public void modificaSubgenere(String subgenre) {
        this.subgenre = subgenre;
    }

    public void modificaDurada(int duration) {
        this.duration = duration;
    }

}
