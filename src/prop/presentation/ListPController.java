package prop.presentation;

import prop.domain.List;
import prop.domain.ListController;

import java.util.ArrayList;

public class ListPController {

    private ListController listController;

    public ListPController() {
        listController = new ListController();
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

}
