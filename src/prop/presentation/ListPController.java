package prop.presentation;

import prop.PropException;
import prop.domain.ListController;
import prop.domain.Pair;
import prop.domain.SongController;

import java.io.IOException;
import java.util.ArrayList;

public class ListPController {

    private ListController listController;
    private SongController songController;

    public ListPController(SongController sc) {
        listController = new ListController();
        songController = sc;
    }

    public void addList(String title) throws PropException {
        listController.addList(title);
    }

    public ArrayList<String> getListSetStringArray() {
        return listController.getListSetStringArray();
    }

    public void removeList(String title) throws PropException {
        listController.removeList(title);
    }

    public ArrayList<String> getListStringArray(String title) {
        return listController.getListStringArray(title);
    }

    public void addSong(String listTitle, String title, String artist) throws PropException {
        listController.addSong(listTitle, title, artist, songController);
    }

    public ArrayList<String> findLists(String prefix) {
        return listController.findLists(prefix);
    }

    public void removeSong(String title, int pos) {
        listController.removeSong(title,pos);
    }

    public ListController getListController() {
        return listController;
    }

    public ArrayList<String> getArtists() {
        return songController.getArtists();
    }

    public ArrayList<String> getTitlesFromArtists(String artist) throws PropException {
        return songController.getTitlesFromArtist(artist);
    }

    public void save(String path) throws IOException {
        listController.save(path);
    }

    public void load(String path) throws IOException, PropException {
        listController.load(path,songController);
    }

    public void createRandomList(String title, int n) throws PropException {
        listController.createRandomList(title,n,songController);
    }

    public int songSetSize() {
        return songController.size();
    }

}
