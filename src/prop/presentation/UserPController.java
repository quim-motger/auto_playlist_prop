package prop.presentation;

import prop.PropException;
import prop.domain.ListController;
import prop.domain.SongController;
import prop.domain.UserController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class UserPController {

    private final SongController songController;
    private final UserController userController;
    private ListController listController;

    /**
     * Default constructor 
     * @param listC ListController
     * @param songC SongController
     */
    public UserPController(ListController listC, SongController songC) {
        listController = listC;
        songController = songC;
        userController = new UserController();        
    }

    /**
     * get UserController 
     * @return UserController included in the class
     */
    public UserController getUserController() {
        return userController;
    }

    /**
     * Return the usernames starting with the search field
     * @param search prefix to be searched
     * @return usernames starting with the search field 
     */
    public ArrayList<String> getMatch(String search) {
        String[] users = userController.findUsers(search);
        return new ArrayList<String>(Arrays.asList(users));
    }

    /**
     * Gets all the users contained in the userController 
     * @return Usernames of all users contained in the userController
     */
    public ArrayList<String> getUsers() {
        String[] users = userController.obtainUserSetTitles();
        return new ArrayList<String>(Arrays.asList(users));
    }

    /**
     * Return array with all the genres 
     */
    public String[] getGenres() {
        return userController.obtainGenders();
    }

    /**
     * Create new user 
     * @param title username
     * @throws Exception
     */
    public void createNewUser(String title, String gender, int day, int month, int year) throws Exception {
        userController.addUser(title, gender,year,month,day);
    }


    /**
     * Get string from user 
     * @param name username to be extracted
     * @return string for the user
     * @throws PropException
     */
    public String getUser(String name) throws PropException {
        return userController.obtainUserToString(name);
    }

    /**
     *  Updates a user
     * @param id old username 
     * @param name new username
     * @throws Exception
     */
    public void updateUser(String id,String name, String gender, int day, int month, int year) throws Exception {
        userController.updateUser(id,name,gender,day,month,year);
    }

    /**
     *  
     * @param name username of the user to be deleted
     * @throws Exception user doesn't exist
     */
    public void deleteUser(String name) throws Exception {
        userController.removeUser(name);
    }

    /**
     * Saves state of the users 
     * @param path Path of the file to be saved
     * @throws IOException
     */
    public void save(String path) throws IOException {
        userController.save(path);
    }

    /**
     * Load state of the users
     * @param path Path of the file to be loaded
     * @throws Exception
     */
    public void load(String path) throws Exception {
        userController.load(path,listController,songController);
    }

    /**
     * Get all lists of the system 
     * @return Array separated for each loaded list
     */
    public String[] getAllLists() {
        String token = listController.getListSetString();
        return token.split("\n");
    }

    /**
     * Get all lists of the user
     * @return Array separated for each loaded list
     */
    public String[] getUserLists(String name) throws PropException {
        String token = userController.obtainListsAssociated(name);
        return token.split("\n");
    }

    /**
     * Action to associate List to user 
     * @param name name of the user
     * @param list name of the list
     * @throws PropException user or list doesn't exist
     */
    public void associateList(String name, String list) throws PropException {
        userController.associateListToUser(listController,list,name);
    }

    /**
     * Action to disassociate List to user
     * @param name name of the user
     * @param list name of the list
     * @throws PropException  user or list doesn't exist
     */
    public void disassociateList(String name, String list) throws PropException {
        userController.disassociateListFromUser(listController,list,name);
    }

    /**
     * Get all lists not of the user
     * @return Array separated for each loaded list
     */
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

    /**
     * Removes all users 
     */
    public void removeAll() {
        userController.removeAll();
    }

    /**
     * Get user plays 
     * @param name username
     * @return String array for each song played by the user
     * @throws PropException
     */
    public String[] getUserPlays(String name) throws PropException {
        return userController.obtainPlaybacks(name);
    }

    /**
     *
     * @param title
     * @param artist
     * @param userName
     * @param date
     * @throws PropException
     * @throws ParseException
     */
    public void playNewSong(String title, String artist, String userName, Date date) throws PropException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        String sDate = dateFormat.format(date);
        userController.playSong(title,artist,userName,sDate, songController);
    }

    public ArrayList<String> getArtists() {
        return songController.getArtists();
    }

    public ArrayList<String> getArtistSongs(String artist) throws PropException {
        return songController.getTitlesFromArtist(artist);
    }

    public void saveAppend(String path) throws IOException {
        userController.save(path,true);
    }

    public int load(String path, int currentLine) throws Exception {
        return userController.load(path,currentLine,listController,songController);
    }
}
