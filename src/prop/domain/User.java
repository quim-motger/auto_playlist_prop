package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Class User represents a user identity. Contains their personal information, a register of songs played
 * by them and a list of playlists associated to them.
 * @author Carles Garcia Cabot
 */
public class User {
    private String name;
    private Gender gender;
    private Calendar birthdate;
    private CountryCode country;
    private TreeSet<Playback> playbackRegister; // Ordered from oldest to newest
    private ArrayList<List> associatedLists; // Can't contain repeated lists

    private static final String USER_DELIMITER = "|U|\n";
    private static final String USER_ID = "USER_ID";
    private static final String PLAYBACK_DELIMITER = "|P|\n";
    private static final String LIST_DELIMITER = "|ID|\n";


    /* CONSTRUCTORS */

    /**
     * User default constructor
     * */
    public User() {
        this.name = "Default";
        this.gender = Gender.MALE;
        this.birthdate = Calendar.getInstance();
        this.country = CountryCode.AD;
        playbackRegister = new TreeSet<>();
        associatedLists = new ArrayList<>();
    }

    /**
     * User constructor with personal data. The playbackRegister and the associatedLists are empty.
     * @param name String which contains the User's name
     * @param gender User's gender
     * @param birthdate User's birthdate
     * @param country User's country of residence
     */
    public User(String name, Gender gender, Calendar birthdate, CountryCode country) {
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.country = country;
        playbackRegister = new TreeSet<>();
        associatedLists = new ArrayList<>();
    }

    /* GETTERS */
    public ArrayList<List> getAssociatedLists() {
        return associatedLists;
    }

    public TreeSet<Playback> getPlaybackRegister() {
        return playbackRegister;
    }

    public CountryCode getCountry() {
        return country;
    }

    public Calendar getBirthdate() {
        return birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    /* SETTERS */
    public void setName(String name) {
        if (name == null) throw new NullPointerException("name is null");
        this.name = name;
    }

    public void setGender(Gender gender) {
        if (gender == null) throw new NullPointerException("gender is null");
        this.gender = gender;
    }

    public void setBirthdate(Calendar birthdate) {
        if (birthdate == null) throw new NullPointerException("birthdate is null");
        this.birthdate = birthdate;
    }

    public void setCountry(CountryCode country) {
        if (country == null) throw new NullPointerException("country is null");
        this.country = country;
    }

    public void setPlaybackRegister(TreeSet<Playback> playbackRegister) {
        if (playbackRegister == null) throw new NullPointerException("playbackRegister is null");
        this.playbackRegister = playbackRegister;
    }

    public void setAssociatedLists(ArrayList<List> associatedLists) {
        if (associatedLists == null) throw new NullPointerException("associatedLists is null");
        this.associatedLists = associatedLists;
    }

    /* OTHER METHODS */

    /**
     * Returns the user's current age
     * @return integer number that contains the user's age
     */
    public int age() {
        if (birthdate == null) throw new NullPointerException("birthdate is null");
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
        if (now.get(Calendar.MONTH) < birthdate.get(Calendar.MONTH)) --age;
        else if (now.get(Calendar.MONTH) == birthdate.get(Calendar.MONTH)
                && now.get(Calendar.DAY_OF_MONTH) < birthdate.get(Calendar.DAY_OF_MONTH)) --age;
        return age;
    }

    /**
     * Adds the playback to the user's playback register
     * @param play Playback to be added
     */
    public boolean add(Playback play) {
        if (play == null) throw new NullPointerException("play is null");
        return playbackRegister.add(play);
    }

    /**
     * Associates list to the user
     * @param list List to be associated.
     */
    public void associate(List list) {
        if (list == null) throw new NullPointerException("list is null");
        if (!associatedLists.contains(list)) associatedLists.add(list);
    }

    /**
     * Disassociates list from the user.
     * @param list List to be disassociated
     * @return true if the list was found and disassociated, false otherwise
     */
    public boolean disassociate(List list) {
        if (list == null) throw new NullPointerException("list is null");
        return associatedLists.remove(list);
    }

    /**
     * Returns true if the user has the list associated
     * @param list list to look up
     * @return true if associated, false otherwise
     */
    public boolean hasList(List list) {
        if (list == null) throw new NullPointerException("list is null");
        return associatedLists.contains(list);
    }

    /**
     * Clear the user's playback register
     */
    public void clearRegister() {
        playbackRegister.clear();
    }

    /**
     * Disassociates all lists from the user
     */
    public void clearLists() {
        associatedLists.clear();
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(USER_ID);
        ret.append(USER_DELIMITER);
        ret.append(name);
        ret.append(USER_DELIMITER);
        ret.append(gender.toString());
        ret.append(USER_DELIMITER);
        ret.append(birthdate.get(Calendar.YEAR));
        ret.append(USER_DELIMITER);
        ret.append(birthdate.get(Calendar.MONTH));
        ret.append(USER_DELIMITER);
        ret.append(birthdate.get(Calendar.DAY_OF_MONTH));
        ret.append(USER_DELIMITER);
        ret.append(country.toString());
        ret.append(USER_DELIMITER);
        ret.append(playbackRegister.size());
        ret.append(USER_DELIMITER);
        for (Playback p : playbackRegister) {
            ret.append(p.toString());
            ret.append(USER_DELIMITER);
        }
        ret.append(associatedLists.size());
        ret.append(USER_DELIMITER);
        int i = 0;
        if (!associatedLists.isEmpty()) {
            for (; i < associatedLists.size() - 1; ++i) {
                ret.append(associatedLists.get(i).obtainId());
                ret.append(USER_DELIMITER);
            }
            ret.append(associatedLists.get(i).obtainId());
        }
        return ret.toString();
    }

    public static User valueOf(String origin, ListController listController, SongController songController)
            throws Exception {
        String[] tokens = origin.split(Pattern.quote(USER_DELIMITER));
        if (!tokens[0].equals(USER_ID) || tokens.length < 9) {
            throw new PropException(ErrorString.INCORRECT_FORMAT);
        }
        User u = new User();
        TreeSet<Playback> plays = new TreeSet<>();
        ArrayList<List> lists = new ArrayList<>();
        u.setName(tokens[1]);
        u.setGender(Gender.valueOf(tokens[2]));
        Calendar d = Calendar.getInstance();
        d.set(Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
        u.setBirthdate(d);
        u.setCountry(CountryCode.valueOf(tokens[6]));
        int i = 8;
        int size = i + Integer.valueOf(tokens[7]);
        for (; i < size; ++i) {
            plays.add(Playback.valueOf(tokens[i], songController));
        }
        u.setPlaybackRegister((plays));
        size = i+1 + Integer.valueOf(tokens[i]);
        ++i;
        for (; i < size; ++i) {
            lists.add(listController.getList(Integer.parseInt(tokens[i])));
        }
        u.setAssociatedLists(lists);
        return u;
    }
}
