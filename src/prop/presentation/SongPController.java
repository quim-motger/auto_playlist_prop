package prop.presentation;

import prop.PropException;
import prop.domain.Genre;
import prop.domain.SongController;

import java.util.ArrayList;

public class SongPController {

    private SongController songController;

    public SongPController() {
        songController = new SongController();
    }

    public SongController getSongController() {
        return songController;
    }

    public void addSong(String title, String artist, String album, String year, String genre, String subgenre, String duration) throws PropException {
        // Genres should be created in the domain tier, so songController.addSong should get an integer as a Genre id
        songController.addSong(title,artist,album,year,genre,subgenre,duration);
    }

    public void removeSong(String title, String artist) throws PropException {
        songController.removeSong(title, artist);
    }

    public String findSongsByName(String prefix) {
       return songController.findSongsByName(prefix);
    }

    public String findSongs() {
        return songController.getList();
    }

    public String[] listGenres() {
        return songController.listGenres();
    }

    public String getSong(String title, String artist) throws PropException {
        return songController.getSongString(title, artist);
    }

}
