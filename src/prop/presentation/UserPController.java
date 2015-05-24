package prop.presentation;

import prop.PropException;
import prop.domain.ListController;
import prop.domain.SongController;
import prop.domain.UserController;

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
}
