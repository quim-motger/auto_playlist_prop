package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;

/**
 * Simple Relation
 * @author  joaquim.motger
 * @see     Relation
 */

public class SimpleRelation extends Relation {

    private SongSet songSet;
    private UserSet userSet;
    private String attribute;
    private String value;


    //Attributes of song relations
    private static final String SONG_TITLE = "song_title";
    private static final String SONG_ARTIST = "song_artist";
    private static final String SONG_ALBUM = "song_album";
    private static final String SONG_YEAR = "song_year";
    private static final String SONG_GENRE = "song_genre";
    private static final String SONG_SUBGENRE = "song_subgenre";
    private static final String SONG_DURATION = "song_duration";

    //Attributes of user relations
    private static final String USER_NAME = "user_name";
    private static final String USER_GENDER = "user_gender";
    private static final String USER_AGE = "user_age_equal";

    /**
     * Simple class constructor
     * @param   attr    attribute name
     * @param   val     value of attribute
     */
    public SimpleRelation(SongSet ss, UserSet us, String attr, String val) {
        songSet = ss;
        userSet = us;
        attribute = attr;
        value = val;
    }

    /**
     * Get the relation attribute
     * @return  a String with the relation attribute
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * Get the relation value of the attribute
     * @return  a String with the relation value of the attribute
     */
    public String getValue() {
        return value;
    }

    /**
     * Get the songSet of the relation
     * @return the songSet
     */
    public SongSet getSongSet() {
        return songSet;
    }

    /**
     * Get the userSet of the relation
     * @return the userSet
     */
    public UserSet getUserSet() {
        return userSet;
    }

    /**
     * Evaluates the relation
     * @return      An ArrayList with all songs that match the relation
     */
    public ArrayList<Song> evaluate() throws PropException {
        ArrayList<Song> songs = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Pair<String, String>> l = new ArrayList<>();
        switch (attribute) {
            case SONG_TITLE:
                l.add(new Pair<>(attribute,value));
                songs = songSet.searchSongs(l);
                break;
            case SONG_ARTIST:
                l.add(new Pair<>(attribute,value));
                songs = songSet.searchSongs(l);
                break;
            case SONG_ALBUM:
                l.add(new Pair<>(attribute,value));
                songs = songSet.searchSongs(l);
                break;
            case SONG_YEAR:
                l.add(new Pair<>(attribute,value));
                songs = songSet.searchSongs(l);
                break;
            case SONG_GENRE:
                l.add(new Pair<>(attribute,value));
                songs = songSet.searchSongs(l);
                break;
            case SONG_SUBGENRE:
                l.add(new Pair<>(attribute,value));
                songs = songSet.searchSongs(l);
                break;
            case SONG_DURATION:
                l.add(new Pair<>(attribute,value));
                songs = songSet.searchSongs(l);
                break;
            case USER_NAME:
                l.add(new Pair<>(attribute, value));
                users = userSet.searchUsers(l);
                for (User user : users) {
                    ArrayList<List> lists = user.getAssociatedLists();
                    for (List list : lists) {
                        for (Song song : list.obtainSongs()) {
                            songs.add(song);
                        }
                    }
                }
                break;
            case USER_GENDER:
                l.add(new Pair<>(attribute, value));
                users = userSet.searchUsers(l);
                for (User user : users) {
                    ArrayList<List> lists = user.getAssociatedLists();
                    for (List list : lists) {
                        for (Song song : list.obtainSongs()) {
                            songs.add(song);
                        }
                    }
                }
                break;
            case USER_AGE:
                l.add(new Pair<>(attribute, value));
                users = userSet.searchUsers(l);
                for (User user : users) {
                    ArrayList<List> lists = user.getAssociatedLists();
                    for (List list : lists) {
                        for (Song song : list.obtainSongs()) {
                            songs.add(song);
                        }
                    }
                }
                break;
            default:
                throw new PropException(ErrorString.UNEXISTING_ATTRIBUTE);
        }
        return songs;
    }
}