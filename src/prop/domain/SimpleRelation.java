package prop.domain;

import prop.ErrorString;
import prop.PropException;

/**
 * Simple Relation
 * @author  joaquim.motger
 * @see     Relation
 */

public class SimpleRelation extends Relation {

    private String type;
    private String attribute;
    private String value;

    /**
     * Simple class constructor
     * @param   attr    attribute name
     * @param   val     value of attribute
     */
    public SimpleRelation(String attr, String val) {
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
     * Evaluates s1 and s2
     * @param   s1      first song
     * @param   s2      second song
     * @return      true if they match the specified relation; false otherwise
     */
    public boolean evaluateSongs(Song s1, Song s2) throws PropException{
        String s;
        int i;
        if (type.equals("SONG")) {
            switch (attribute) {
                case "title":
                    s = s1.getTitle();
                    return s.equals(s2.getTitle()) && s.equals(value);
                case "artist":
                    s = s1.getArtist();
                    return s.equals(s2.getArtist()) && s.equals(value);
                case "album":
                    s = s1.getAlbum();
                    return s.equals(s2.getAlbum()) && s.equals(value);
                case "yearless":
                    return s2.getYear() < Integer.parseInt(value) && s1.getYear() < Integer.parseInt(value);
                case "yearequal":
                    i = s1.getYear();
                    return i == s2.getYear() && i == Integer.parseInt(value);
                case "yearmore":
                    return s2.getYear() > Integer.parseInt(value) && s1.getYear() > Integer.parseInt(value);
                case "genre":
                    s = s1.getGenre().getName();
                    return s.equals(s2.getGenre().getName()) && s.equals(value);
                case "subgenre":
                    s = s1.getSubgenre().getName();
                    return s.equals(s2.getSubgenre().getName()) && s.equals(value);
                case "duration":
                    i = s1.getDuration();
                    return i == s2.getDuration() && i == Integer.parseInt(value);
                case "name":

                default:
                    throw new PropException(ErrorString.UNEXISTING_ATTRIBUTE);
            }
        }
        else throw new PropException(ErrorString.WRONG_RELATION_TYPE);
    }


    /**
     * Evaluates s1 and s2
     * @param   u      first song
     * @return      true if the user match the specified relation; false otherwise
     */
    public boolean evaluateUser(User u) throws PropException {
        if (type.equals("USER")) {
            switch (attribute) {
                case "name":
                    return u.getName().equals(value);
                case "gender":
                    return u.getGender().toString().equals(value);
                case "ageless":
                    return u.age() < Integer.parseInt(value);
                case "ageequal":
                    return u.age() == Integer.parseInt(value);
                case "agemore":
                    return u.age() > Integer.parseInt(value);
                default:
                    throw new PropException(ErrorString.UNEXISTING_ATTRIBUTE);
            }
        }
        else throw new PropException(ErrorString.WRONG_RELATION_TYPE);
    }
}