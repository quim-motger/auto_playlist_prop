package prop.domain;

import prop.data.DataController;

import java.io.IOException;
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
    UserSet userSet;
    
    //USEFUL STRINGS FOR USAGE OF editUser
    public static final String NAME="name";
    public static final String GENDER="gender";
    public static final String BIRTHDAY="birthday";
    public static final String COUNTRY_CODE="countryCode";

    /**
     * Creator, resets the controller 
     */
    public UserController() {
        userSet = new UserSet();        
    }

    /**
     * Modifier that adds  new users
     * @param name name that will have the new user
     * @param gender gender of the User
     * @param birthday Birthday expressed in long (Date to Long)
     * @param countryCode Country where the user is from
     * @return <code>true</code> if the user is succesfully added, <code>false</code> if the user name already exists
     * @see prop.domain.User 
     */
    public void addUser(String name, String gender, long birthday, String countryCode) throws Exception {
        Gender userGender = Gender.valueOf(gender);
        CountryCode userCountry = CountryCode.getByCode(countryCode);
        Calendar userBirthday = getCaledarFromLong(birthday);
        User user = new User(name,userGender,userBirthday,userCountry);
        userSet.addUser(user);
    }

    /**
     * Modifier that removes an existing user
     * @param name Name of the user that wants to be removed
     * @return <code>false</code> the user doesn't exist and it can't be removed
     * @see prop.domain.User 
     */
    public void removeUser (String name) throws Exception {
        userSet.removeUser(name);
    }

    /**
     * Modifier that edits an attribute of an specific user
     * @param name Name of the desired user
     * @param attribute Attribute to be modified
     * @param value New value for the desired attribute
     * @see prop.domain.User              
     */
    public void editUser(String name, String attribute, String value) {
        Pair<String,String> pair = Pair.create(attribute,value);
        editUser(name,pair);        
    }

    /**
     * Modifier that edits an attribute of an specific user
     * @param name Name of the desired user
     * @param attributeValue Pair of Attribute and Value to be modified
     * @see prop.domain.Pair                       
     */
    public void editUser(String name, Pair<String,String> attributeValue) {
        User user = userSet.getUserByName(name);
        if(user!=null) {
            switch (attributeValue.first) {
                case (NAME):
                    user.setName(attributeValue.second);
                    break;
                case (GENDER):
                    user.setGender(Gender.valueOf(attributeValue.second));
                    break;
                case (BIRTHDAY):
                    user.setBirthdate(getCaledarFromLong(
                            Long.valueOf(attributeValue.second)                            
                    ));
                    break;
                case (COUNTRY_CODE):
                    user.setCountry(
                            CountryCode.getByCode(attributeValue.second)
                    );
            }
        }
        
    }

    /**
     *Obtains user with the specified name
     * @param name Desired name user
     * @return String of the desired user
     */
    public String obtainUser(String name) {
       return userSet.getUserByName(name)
                     .toString();
    }

    /**
     * Obtains all users in the controller 
     * @return String of all the users contained in the controller
     */
    public String obtainUserSet() {
        return userSet.toString();
    }

    /**
     *
     * @param title Title of the played song
     * @param artist Artist of the played song
     * @param name Name of the user playing the song
     * @param songController Main SongController
     * @see prop.domain.SongController                       
     */
    public void playSong(String title, String artist, String name, SongController songController) {
        //TODO: HOW TO COMMUNICATE BETWEEN CONTROLLERS
        User user = userSet.getUserByName(name);
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
    public void save (String path) {
        try {
            DataController.save(obtainUserSet(), path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads from the disk a UserSet 
     * @param path Path to the file to be load from
     * @see prop.domain.UserSet 
     * @see prop.data.DataController
     */
    public void load (String path) {
        String data;
        try {
            data = DataController.load(path);
            //userSet = UserSet.valueOf(data);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    /*PRIVATE METHODS*/
    private Calendar getCaledarFromLong(long birthday) {
        Date date = new Date(birthday);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return  cal;
    }
    
    private boolean contains(String name) {
        return userSet.getUserByName(name)!=null;
        
    } 
}
