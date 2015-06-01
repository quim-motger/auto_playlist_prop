package prop.presentation;

import prop.PropException;
import prop.domain.SongController;

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

    public void save(String path) throws Exception {
        songController.save(path);
    }

    public void load(String path) throws Exception {
        songController.load(path);
    }

    public void edit(String title, String artist, String attr, String val) throws PropException {
        songController.editSong(title, artist, attr, val);
    }

    public String searchSongs(String p) throws PropException {
        return songController.searchSongs(p);
    }

    public void removeAllSongs() {
        songController.removeAllSongs();
    }

    public int load(String path, int line) throws Exception {
        return songController.load(path,line);
    }
}
