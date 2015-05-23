package prop.domain;

import prop.PropException;
import prop.data.DataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * UserController in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 03/04/15
 * @see prop.domain.UserSet
 * @see prop.domain.User
 */
public class UserController {
    //USEFUL STRINGS FOR USAGE OF editUser
    public static final String NAME="name";
    public static final String GENDER="gender";
    public static final String BIRTHDAY="birthday";

    private UserSet userSet;

    /**
     * Creator, resets the controller 
     */
    public UserController() {
        userSet = new UserSet();        
    }

    /**
     * Obtain Attribute names of a user
     * @param delimiter String between attribute names
     * @return Attribute names separated with the specified delimiter
     */
    public static String obtainAttributes(String delimiter) {
        return NAME + delimiter +
                GENDER + delimiter +
                BIRTHDAY + delimiter;
    }

    /**
     * Modifier that adds  new users
     * @param name name that will have the new user
     * @param gender gender of the User
     * @param year year of the user birthday
     * @param month month of the user birthday
     * @param date date of the user birthday
     * @see prop.domain.User
     */
    public void addUser(String name, String gender, int year, int month, int date) throws Exception {
        Gender userGender = Gender.valueOf(gender);
        Calendar userBirthday = Calendar.getInstance();
        userBirthday.set(year, month, date);
        User user = new User(name, userGender, userBirthday);
        userSet.addUser(user);
    }

    /**
     * Modifier that removes an existing user
     * @param name Name of the user that wants to be removed
     * @see prop.domain.User
     */
    public void removeUser(String name) throws Exception {
        userSet.removeUser(name);
    }

    /**
     * Modifier that edits an attribute of an specific user
     * @param name Name of the desired user
     * @param attribute Attribute to be modified
     * @param value New value for the desired attribute
     * @see prop.domain.User
     */
    public void editUser(String name, String attribute, String value) throws PropException {
        Pair<String, String> pair = Pair.create(attribute, value);
        editUser(name, pair);
    }

    /**
     * Modifier that edits an attribute of an specific user
     * @param name Name of the desired user
     * @param attributeValue Pair of Attribute and Value to be modified
     * @see prop.domain.Pair                       
     */
    public void editUser(String name, Pair<String, String> attributeValue) throws PropException {
        User user = userSet.getUserByName(name);
        switch (attributeValue.first()) {
            case (NAME):
                user.setName(attributeValue.second());
                break;
            case (GENDER):
                user.setGender(Gender.valueOf(attributeValue.second()));
                break;
            case (BIRTHDAY):
                user.setBirthdate(getCalendarFromLong(
                        Long.valueOf(attributeValue.second())
                ));
                break;
        }
        
    }

    /**
     * Associate List to an specified User
     * @param listController listController
     * @param title     list title
     * @param userName Valid name of an existing user
     */
    public void associateListToUser(ListController listController, String title, String userName) throws PropException {
        List list = listController.getList(title);
        associateListToUser(list,userName);
    }

    /**
     * Associate a List to a User
     * @param list List to be associated
     * @param userName Valid name of an existing user
     */
    public void associateListToUser(List list, String userName) throws PropException {
        userSet.getUserByName(userName).associate(list);
        
    }

    /**
     * Disassociate List to an specified User
     * @param listController listController
     * @param title     list title
     * @param userName Valid name of an existing user
     */
    public void disassociateListFromUser(ListController listController, String title, String userName) throws PropException {
        List list = listController.getList(title);
        userSet.getUserByName(userName).disassociate(list);
    }

    /**
     * Obtains Strings with the titles of all lists associated to an specified user
     * @param userName Specified user
     * @return Titles of the lists associated to the user, separated by \n
     */
    public String obtainListsAssociated(String userName) throws PropException {
        ArrayList<List> lists = userSet.getUserByName(userName).getAssociatedLists();
        String ret = "";
        for(List list : lists){
            ret += list.obtainTitle() + "\n";
        }
        return ret;
    }

    /**
     *Obtains user with the specified name in the form of a String
     * @param name Desired name user
     * @return String of the desired user
     */
    public String obtainUserToString(String name) throws PropException {
        return obtainUser(name).toString();
    }

    /**
     * Obtains user with the specified name
     * @param name Desired name user
     * @return Desired user
     */
    public User obtainUser(String name) throws PropException {
        return userSet.getUserByName(name);
    }

    /**
     * Obtains all users in the controller 
     * @return String of all the users contained in the controller
     */
    public String obtainUserSetToString() {
        return userSet.toString();
    }

    /**
     * Obtains all users in the controller 
     * @return All the users contained in the controller
     */
    public UserSet obtainUserSet() {
        return userSet;
    }

    public ArrayList<User> obtainUsers() { return userSet.getUsers();}
        
    /**
     *
     * @param title Title of the played song
     * @param artist Artist of the played song
     * @param userName Name of the user playing the song
     * @param songController Main SongController
     * @see prop.domain.SongController                       
     */
    public void playSong(String title, String artist, String userName, SongController songController) throws PropException {
        User user = userSet.getUserByName(userName);
        Song song = songController.getSong(title,artist);
        Calendar time = Calendar.getInstance();
        Playback playback = new Playback(song,time);
        user.add(playback);
    }

    /**
     * Saves to disk the users contained in the class
     * @param path Path to the file to be saved to
     * @see prop.data.DataController             
     */
    public void save(String path) throws IOException {
        DataController.save(userSet.toString(), path);
    }

    /**
     * Loads from the disk a UserSet 
     * @param path Path to the file to be load from
     * @see prop.domain.UserSet 
     * @see prop.data.DataController
     */
    public void load(String path, ListController lc, SongController sc) throws Exception {
        String data;
        data = DataController.load(path);
        userSet = UserSet.valueOf(data, lc, sc);
    }

    public String findUsers(String prefix) {
        ArrayList<User> l = userSet.findUsers(prefix);
        String p = "";
        for (User user : l) {
            p += user.getName() + "\n";
        }
        return p;
    }
    
    /*PRIVATE METHODS*/
    private Calendar getCalendarFromLong(long birthday) {
        Date date = new Date(birthday);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return  cal;
    }
}
