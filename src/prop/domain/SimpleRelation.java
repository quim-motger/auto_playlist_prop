package prop.domain;

import java.lang.Integer;

/**
 * Simple Relation
 * @author  joaquim.motger
 * @see     Relation
 */

public class SimpleRelation extends Relation {

    private String attribute;
    private String value;

    /**
     * Simple class constructor
     * @param   attribute   attribute name
     * @param   value       value of attribute
     */
    public SimpleRelation(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * Evaluates s1 and s2
     * @param   s1      first song
     * @param   s2      second song
     * @return      true if they match the specified relation; false otherwise
     */
    public boolean evaluateSongs(Song s1, Song s2) {
        String s;
        int i;
        switch(attribute) {
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
                i = s1.getYear();
                return i < s2.getYear() && s2.getYear() < Integer.parseInt(value) && i < Integer.parseInt(value);
            case "yearequal":
                i = s1.getYear();
                return i == s2.getYear() && i == Integer.parseInt(value);
            case "yearmore":
                i = s1.getYear();
                return i > s2.getYear() && s2.getYear() > Integer.parseInt(value) && i > Integer.parseInt(value);
            case "genre":
                s = s1.getGenre().getName();
                return s.equals(s2.getGenre().getName()) && s.equals(value);
            case "subgenre":
                s = s1.getSubgenre().getName();
                return s.equals(s2.getSubgenre().getName()) && s.equals(value);
            case "duration":
                i = s1.getDuration();
                return i == s2.getDuration() && i == Integer.parseInt(value);
            default:
                return false;
        }
    }

    public boolean evaluateUser(User u) {
        switch(attribute) {
            case "name":
                return u.getName().equals(value);
            case "gender":
                return u.getGender().toString().equals(value);
            case "country":
                return u.getCountry().getName().equals(value);
            case "ageless":
                return u.age() < Integer.parseInt(value);
            case "ageequal":
                return u.age() == Integer.parseInt(value);
            case "agemore":
                return u.age() > Integer.parseInt(value);
            default:
                return false;
        }
    }

}