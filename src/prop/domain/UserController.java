package prop.domain;

import prop.ErrorString;
import prop.PropException;
import prop.data.DataController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;
import java.util.regex.Pattern;

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
    private static final String elemDelimiter = "|";
    private static final String setDelimiter = "\n";
    public static final int SAVING_BLOCK = 20;

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
        name.replace('|','/');
        Gender userGender = Gender.valueOf(gender.trim());
        Calendar userBirthday = Calendar.getInstance();
        checkDate(year+"-"+month+"-"+date);
        userBirthday.set(year, month, date);
        User user = new User(name.trim(), userGender, userBirthday);
        userSet.addUser(user);
    }

    private void checkDate(String date) throws PropException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            throw new PropException(ErrorString.INCORRECT_DATE);
        }
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
     * Gets all usernames in the userset
     * @return usernames for all users
     */
    public String[] obtainUserSetTitles() {
        ArrayList<User> users = userSet.getUsers();
        String[] userTitles = new String[users.size()];
        for(int i = 0; i<userTitles.length;++i) {
            userTitles[i] = users.get(i).getName();
        }
        return  userTitles;
    }

    /**
     * Obtains all users in the controller 
     * @return All the users contained in the controller
     */
    public UserSet obtainUserSet() {
        return userSet;
    }

    /**
     * Get all users from userset 
     * @return
     */
    public ArrayList<User> obtainUsers() { return userSet.getUsers();}
        
    /**
     *
     * @param title Title of the played song
     * @param artist Artist of the played song
     * @param userName Name of the user playing the song
     * @param songController Main SongController
     * @see prop.domain.SongController                       
     */
    public void playSong(String title, String artist, String userName, String date, SongController songController) throws PropException, ParseException {
        User user = userSet.getUserByName(userName);
        Song song = songController.getSong(title,artist);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Calendar time = Calendar.getInstance();
        time.setTime(format.parse(date));
        Playback playback = new Playback(song,time);
        user.add(playback);
    }
    
    /**
     * Saves to disk the users contained in the class
     * @param path Path to the file to be saved to
     * @see prop.data.DataController
     */    
    public void save(String path) throws IOException {
        save(path,false);
    }

    /**
     * Saves to disk the users contained in the class
     * @param path Path to the file to be saved to
     * @see prop.data.DataController             
     */
    public void save(String path, boolean append) throws IOException {
        int usersSaved = 0;
        DataController dataController = new DataController();
        dataController.open(path);
        if(!append)
            dataController.deleteContent();
        ArrayList<User> users = userSet.getUsers();
        boolean saved = false;
        while (usersSaved<userSet.getSize()){
            String cached = "";
            while(usersSaved<userSet.getSize() && (usersSaved%SAVING_BLOCK!=SAVING_BLOCK-1 || saved)) {
                cached =cached + users.get(usersSaved).toString();
                cached = cached + setDelimiter;
                ++usersSaved;
                saved = false;
            }
            dataController.append(cached);
            saved = true;
        }
        dataController.append("\n");
    }
    
    /**
     * Loads from the disk a UserSet 
     * @param path Path to the file to be load from
     * @see prop.domain.UserSet
     * @see prop.data.DataController
     */
    public void load(String path, ListController lc, SongController sc) throws Exception {
        load(path,0,lc,sc);
    }

    /**
     * Loads from the disk a UserSet 
     * @param path Path to the file to be load from
     * @see prop.domain.UserSet 
     * @see prop.data.DataController
     */
    public int load(String path, int startLine, ListController lc, SongController sc) throws Exception {
        DataController dc = new DataController();
        dc.open(path);
        dc.openToRead();
        removeAll();
        int currentLine = 0;
        while(currentLine<startLine){
            ++currentLine;
            dc.readLine();
        }
        String userString = dc.readLine();
        ++currentLine;
        while (userString != null && !userString.equals("")) {
            userSet.addUser(userValueOf(lc,sc,userString));
            userString = dc.readLine();
            ++currentLine;
        }
        return currentLine;
    }

    private User userValueOf(ListController lc, SongController sc, String user) throws PropException {
        String[] attributes = user.split(Pattern.quote(elemDelimiter));
        if (!attributes[0].equals(User.USER_STRING_ID))
            throw new PropException(ErrorString.INCORRECT_FORMAT);
        User u = new User();
        u.setName(attributes[1]);
        u.setGender(Gender.valueOf(attributes[2]));
        u.setBirthdate(getCalendarFromLong(Long.valueOf(attributes[3])));
        int nPlaybacks = Integer.valueOf(attributes[4]);
        for (int i = 0; i < nPlaybacks; i += 9) {
            u.add(playbackValueOf(attributes, 5 + i, sc));
        }
        int index = 5 + nPlaybacks * 9;
        int nLists = Integer.valueOf(attributes[index]);
        ++index;
        for (int i = 0; i < nLists; ++i) {
            u.associate(lc.getList(attributes[index + i]));
        }
        return u;
    }

    private Playback playbackValueOf(String[] attributes, int i, SongController sc) throws PropException {
        if (!attributes[i].equals(Playback.PLAYBACK_STRING_ID)) {
            throw new PropException(ErrorString.INCORRECT_FORMAT);
        }
        Song song = sc.getSong(attributes[i + 1], attributes[i + 2]);
        Calendar cal = Calendar.getInstance();
        cal.set(
                Integer.valueOf(attributes[i + 3]),
                Integer.valueOf(attributes[i + 4]),
                Integer.valueOf(attributes[i + 5]),
                Integer.valueOf(attributes[i + 6]),
                Integer.valueOf(attributes[i + 7]),
                Integer.valueOf(attributes[i + 8])
        );
        return new Playback(song, cal);
    }

    /**
     * Finds users starting with the desired prefix
     * @param prefix desired prefix
     * @return Usernames starting with the desired prefix
     */
    public String[] findUsers(String prefix) {
        ArrayList<User> l = userSet.findUsers(prefix);
        String[] ret = new String[l.size()];
        for (int i = 0; i < l.size(); ++i) {
            ret[i] = l.get(i).getName();
        }
        return ret;
    }
    
    /*PRIVATE METHODS*/
    private Calendar getCalendarFromLong(long birthday) {
        Date date = new Date(birthday);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return  cal;
    }

    /**
     * Obtain all genders into string 
     * @return
     */
    public String[] obtainGenders() {
        Gender[] genders = Gender.values();
        String[] ret = new String[genders.length];
        for(int i=0;i<genders.length;++i) {
            ret[i] = genders[i].toString();
        }
        return ret;
    }

    /**
     * Updates user identified by id
     * @param id old user id
     * @param name new user id
     * @param gender gender
     * @param day day of birth
     * @param month month of birth
     * @param year year of birth
     * @throws Exception
     */
    public void updateUser(String id, String name, String gender, int day, int month, int year) throws Exception {
        checkDate(year+"-"+month+"-"+day);
        name.replace('|','/');
        User user = userSet.getUserByName(id);
        userSet.removeUser(id);
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day);
        User nUser = new User(name,Gender.valueOf(gender),cal);
        nUser.setAssociatedLists(user.getAssociatedLists());
        nUser.setPlaybackRegister(user.getPlaybackRegister());
        try {
            userSet.addUser(nUser);
        } catch (PropException e){
            userSet.addUser(user);
            throw e;
        }
    }

    /**
     * Obtains playbacks from a user 
     * @param name username
     * @return Array of strings representing the playbacks of the indicated user
     * @throws PropException
     */
    public String[] obtainPlaybacks(String name) throws PropException {
        User user = userSet.getUserByName(name);
        TreeSet<Playback> playbacks = user.getPlaybackRegister();
        ArrayList<String> ret = new ArrayList<>();
        for(Playback playback:playbacks) {
            ret.add(playback.toString());
        }
        return ret.toArray(new String[ret.size()]);
    }

    /**
     * Deletes all users stored
     */
    public void removeAll() {
        userSet = new UserSet();
    }

}
