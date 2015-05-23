package prop.presentation;

import prop.PropException;
import prop.domain.ListController;
import prop.domain.SongController;

import java.util.ArrayList;

public class ListPController {

    private ListController listController;
    private SongController songController;

    public ListPController(SongController sc) {
        listController = new ListController();
        songController = sc;
    }

    public void addList(String title) {
        listController.addList(title);
    }

    public ArrayList<String> getListSetStringArray() {
        return listController.getListSetStringArray();
    }

    public void removeList(int id) {
        listController.removeList(id);
    }

    public ArrayList<String> getListStringArray(int id) {
        return listController.getListStringArray(id);
    }

    public void addSong(int id, String title, String artist) throws PropException {
        listController.addSong(id,title,artist,songController);
    }

}
