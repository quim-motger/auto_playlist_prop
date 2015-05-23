package prop.presentation;

import prop.PropException;
import prop.domain.Genre;
import prop.domain.SongController;

public class SongPController {

    private SongController songController;

    public SongPController() {
        songController = new SongController();
    }

    public SongController getSongController() {
        return songController;
    }

    public void addSong(String title, String artist, String album, int year, int genre, int subgenre, int duration) throws PropException {
        // Genres should be created in the domain tier, so songController.addSong should get an integer as a Genre id
        songController.addSong(title,artist,album,year,Genre.getGenreById(genre),Genre.getGenreById(subgenre),duration);
    }

}
