package prop.domain;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Carles Garcia Cabot
 */
public class User {
    private String name;
    private Gender gender;
    private Data birthdate;
    private String country;
    private ArrayList<Playback> playbackRegister; // Ordered from oldest to newest
    private ArrayList<List> associatedLists; // Can't contain repeated lists


    /* CONSTRUCTORS */

    /**
     * User default constructor
     * */
    public User() {}

    /**
     * User constructor with personal data
     * @param name String which contains the User name
     * @param gender User's gender
     * @param data User's birthdate
     * @param country String which contains the User's country of residence
     */
    public User(String name, Gender gender, Data birthdate, String country) {
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.country = country;
    }

    /**
     * User constructor with all members
     * @param name
     * @param gender
     * @param birthdate
     * @param country
     * @param playbackRegister
     * @param associatedLists
     */
    public User(String name, Gender gender, Data birthdate, String country, ArrayList<Playback> playbackRegister, ArrayList<List> associatedLists) {
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

    public ArrayList<Playback> getPlaybackRegister() {
        return playbackRegister;
    }

    public String getCountry() {
        return country;
    }

    public Data getBirthdate() {
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

    public void setBirthdate(Data birthdate) {
        this.birthdate = birthdate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPlaybackRegister(ArrayList<Playback> playbackRegister) {
        this.playbackRegister = playbackRegister;
    }

    public void setAssociatedLists(ArrayList<List> associatedLists) {
        this.associatedLists = associatedLists;
    }

    /* OTHER METHODS */

    public int age() {
        // calcula edat
        return 0;
    }

    /**
     * Adds the playback to the user's playback register
     * @param play
     */
    public void addPlayback(Playback play) {
        playbackRegister.add(play);
    }

    /**
     * Associates list to the user
     * @param list List to be associated.
     *             (Precondition: the list isn't already associated to the user)
     */
    public void associateList(List list) {
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




}
