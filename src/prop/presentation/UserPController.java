package prop.presentation;

import prop.PropException;
import prop.domain.ListController;
import prop.domain.SongController;
import prop.domain.UserController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UserPController {

    private final SongController songController;
    private final UserController userController;
    private ListController listController;

    public UserPController(ListController listC, SongController songC) {
        listController = listC;
        songController = songC;
        userController = new UserController();        
    }
    
    public ArrayList<String> getMatch(String search) {
        String[] users = userController.findUsers(search);
        return new ArrayList<String>(Arrays.asList(users));
    }
    
    public ArrayList<String> getUsers() {
        String[] users = userController.obtainUserSetTitles();
        return new ArrayList<String>(Arrays.asList(users));
    }
    
    public String[] getGenres() {
        return userController.obtainGenders();
    }
    
    public void createNewUser(String title, String gender, int day, int month, int year) throws Exception {
        userController.addUser(title, gender,year,month,day);
    }


    public String getUser(String name) throws PropException {
        return userController.obtainUserToString(name);
    }

    public void updateUser(String id,String name, String gender, int day, int month, int year) throws Exception {
        userController.updateUser(id,name,gender,day,month,year);
    }

    public void deleteUser(String name) throws Exception {
        userController.removeUser(name);
    }

    public void save(String path) throws IOException {
        userController.save(path);
    }

    public void load(String path) throws Exception {
        userController.load(path,listController,songController);
    }

    public String[] getAllLists() {
        String token = listController.getListSetString();
        return token.split("\n");
    }

    public String[] getUserLists(String name) throws PropException {
        String token = userController.obtainListsAssociated(name);
        return token.split("\n");
    }

    public void associateList(String name, String list) throws PropException {
        userController.associateListToUser(listController,list,name);
    }

    public void disassociateList(String name, String list) throws PropException {
        userController.disassociateListFromUser(listController,list,name);
    }

    public String[] getNotUserLists(String name) throws PropException {
        String [] allLists = getAllLists();
        ArrayList<String> usersLists = new ArrayList<>(Arrays.asList(getUserLists(name)));
        
        ArrayList<String> ret = new ArrayList<>();
        for(String list : allLists) {
            if(!usersLists.contains(list)){
                ret.add(list);
            }
        }
        return ret.toArray(new String[ret.size()]);
    }

    public void removeAll() {
        userController.removeAll();
    }

    public String[] getUserPlays(String name) throws PropException {
        return userController.obtainPlaybacks(name);
    }
}
