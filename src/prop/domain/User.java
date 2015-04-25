package prop.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;

/**
 * Class User represents a user identity and contains their personal information as well as a register of songs played
 * by them and a list of playlists associated to them.
 * @author Carles Garcia Cabot
 */
public class User {
    private String name;
    private Gender gender;
    private Calendar birthdate;
    private CountryCode country;
    private TreeSet<Playback> playbackRegister; // Playback must be comparable
    private ArrayList<List> associatedLists; // Can't contain repeated lists

    private static final String USER_DELIMITER = "|U|\n";
    private static final String USER_STRING_ID = "USER_STRING";
    private static final String SONG_DELIMITER = "|S|\n";


    /* CONSTRUCTORS */

    /**
     * User default constructor, creates an empty User
     * */
    public User() {}

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
        playbackRegister = new TreeSet<Playback>();
        associatedLists = new ArrayList<List>();
    }

    /**
     * User constructor with all members
     * @param name String which contains the User's name
     * @param gender User's gender
     * @param birthdate User's birthdate
     * @param country User's country of residence
     * @param playbackRegister User's playback begister
     * @param associatedLists User's associated lists
     */
    public User(String name, Gender gender, Calendar birthdate, CountryCode country, TreeSet<Playback> playbackRegister, ArrayList<List> associatedLists) {
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.country = country;
        this.playbackRegister = playbackRegister;
        this.associatedLists = associatedLists;
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
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthdate(Calendar birthdate) {
        this.birthdate = birthdate;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }

    public void setPlaybackRegister(TreeSet<Playback> playbackRegister) {
        this.playbackRegister = playbackRegister;
    }

    public void setAssociatedLists(ArrayList<List> associatedLists) {
        this.associatedLists = associatedLists;
    }

    /* OTHER METHODS */

    /**
     * Returns the user's current age
     * @return integer number that contains the user's age
     */
    public int age() {
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
    public void add(Playback play) {
        playbackRegister.add(play);
    }

    /**
     * Associates list to the user
     * @param list List to be associated.
     *             (Precondition: the list isn't already associated to the user)
     */
    public void associate(List list) {
        associatedLists.add(list);
    }

    /**
     * Disassociates list from the user.
     * @param list List to be disassociated
     * @return true if the list was found and disassociated, false otherwise
     */
    public boolean disassociate(List list) {
        return associatedLists.remove(list);
    }

    /**
     * Returns true if the user has the list associated
     * @param list list to look up
     * @return true if associated, false otherwise
     */
    public boolean hasList(List list) {
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
        StringBuilder sb = new StringBuilder();
        sb.append(name + "\n");
        sb.append(gender.toString() + "\n");
        sb.append(birthdate.toString() + "\n");
        sb.append(country.toString() + "\n");
        sb.append(playbackRegister.size() + "\n");
        for (Playback p : playbackRegister) {
            //write every playback
        }
        sb.append(associatedLists.size() + "\n");
        for (List l : associatedLists) {
            sb.append(l.obtainId() + " ");
        }
        sb.append("\n\n");
        return sb.toString();
    }

    public static User valueOf(String source, ListSet ls) {
        return new User();
    }
}
