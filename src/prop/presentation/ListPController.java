package prop.presentation;

import prop.PropException;
import prop.domain.ListController;
import prop.domain.SongController;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The presentation controller for the lists.
 * @author oscar.manas
 * @see ListController
 * @see SongController
 */
public class ListPController {

    private ListController listController;
    private SongController songController;

    public ListPController(SongController sc) {
        listController = new ListController();
        songController = sc;
    }

    public ListController getListController() {
        return listController;
    }

    public void addList(String title) throws PropException {
        listController.addList(title);
    }

    public String[] getListSetStringArray() {
        return listController.getListSetStringArray();
    }

    public void removeList(String title) throws PropException {
        listController.removeList(title);
    }

    public void removeAll() {
        listController.removeAll();
    }

    public String[] getListStringArray(String title) {
        return listController.getListStringArray(title);
    }

    public void addSong(String listTitle, String title, String artist) throws PropException {
        listController.addSong(listTitle, title, artist, songController);
    }

    public String[] findLists(String prefix) {
        return listController.findLists(prefix);
    }

    public void removeSong(String title, int pos) {
        listController.removeSong(title,pos);
    }

    public ArrayList<String> getArtists() {
        return songController.getArtists();
    }

    public ArrayList<String> getTitlesFromArtists(String artist) throws PropException {
        return songController.getTitlesFromArtist(artist);
    }

    public void createRandomList(String title, int n) throws PropException {
        listController.createRandomList(title,n,songController);
    }

    public int songSetSize() {
        return songController.size();
    }

    public void swapSongs(String title, int pos1, int pos2) {
        listController.swapSongs(title,pos1,pos2);
    }

    public void setListTitle(String oldTitle, String newTitle) throws PropException {
        listController.setListTitle(oldTitle,newTitle);
    }

    public String[] getSongId(int listIndex, int songIndex) {
        return listController.getSongId(listIndex,songIndex);
    }

    public void save(String path) throws IOException {
        listController.save(path);
    }

    public void load(String path) throws IOException, PropException {
        listController.load(path,songController);
    }

    public void saveAppend(String path) throws IOException {
        listController.save(path,true);
    }

    public int load(String path, int currentLine) throws IOException, PropException {
        return listController.load(path,currentLine,songController);
    }

    public boolean contains(String title) {
        return listController.contains(title);
    }
}
