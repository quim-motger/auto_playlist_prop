package prop.domain;

import java.lang.Integer;

/**
 * Simple Relation
 * @author  joaquim.motger
 * @see     Relation
 */

public class Simple extends Relation {

    private String attribute;
    private String value;

    /**
     * Simple class constructor
     * @params  attribute  attribute name
     * @params  value  value of attribute
     */
    public Simple(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public boolean evaluate(Song s1, Song s2) {
        switch(attribute) {
            case title:
                String s = s1.getTitle();
                return s.equals(s2.getTitle()) && s.equals(value);
            case artist:
                String s = s1.getArtist();
                return s.equals(s2.getArtist()) && s.equals(value);
            case album:
                String s = s1.getAlbum():
                return s.equals(s2.getAlbum()) && s.equals(value);
            case year:
                int i = s1.getYear();
                return i == s2.getYear() && i == Integer.parseInt(value);
            case genre:
                String s = s1.getGenre().getName();
                return s.equals(s2.getGenre().getName()) && s.equals(value);
            case subgenre:
                String s = s1.getSubgenre().getName();
                return s.equals(s2.getSubgenre().getName()) && s.equals(value);
            case duration:
                int i = s1.getDuration();
                return i == s2.getDuration() && i == Integer.parseInt(value);
        }
    }

}