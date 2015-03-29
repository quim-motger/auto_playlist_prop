package prop.domain;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A song
 * @author Oscar Mañas Sanchez
 */
public class Canco {

    private String title;
    private String artist;
    private String album;
    private int year;
    private String genre;
    private String subgenre;
    private int duration;

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
