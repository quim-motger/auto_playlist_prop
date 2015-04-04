package prop.domain;

import java.util.Calendar;
import java.util.Date;

/**
 * UserController in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 03/04/15
 */
public class UserController {
    UserSet userSet;

    public UserController() {
        userSet = new UserSet();        
    }
    
    public boolean addUser(String name, String gender, long birthday, String countryCode) {
        if(userSet.getUserPos(name) < 0) return false;
        Gender userGender = Gender.valueOf(gender);
        CountryCode userCountry = CountryCode.getByCode(countryCode);
        Calendar userBirthday = getCaledarFromLong(birthday);
        User user = new User(name,userGender,userBirthday,userCountry);
        userSet.addUser(user);
        return true;
    }

    
    /*PRIVATE METHODS*/
    private Calendar getCaledarFromLong(long birthday) {
        Date date = new Date(birthday);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return  cal;
    }
    //public boolean removeUser(String name);
    //public void editUser(String name, Pair<String,String>);
    //public String getUser(String name);
    //public String getUserSet();
    //public void playSong(String title, String artist, String name);
    //public void save(String path);
    //public void load(String path);


}
