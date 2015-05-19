package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * A playback of a song in a specific date
 * @author joaquim.motger
 */

public class Playback implements Comparable<Playback>{

    private static final String PLAYBACK_DELIMITER = "|P|\n";
    private static final String PLAYBACK_STRING_ID = "PLAYBACK_STRING";
    private Song song;
    private Calendar date;

    /**
     * <code>Playback</code> class constructor
     * @param s      <code>song</code> played
     * @param d     play <3 code>date</code>
     * @throws PropException
     */
    public Playback(Song s, Calendar d) {
        song = s;
        date = d;
    }

    /**
     * valueOf method of a Playback
     * @param s     a String with the specified format of a Playback
     * @param songController    the songController that contains the songs
     * @return the playback obtained by the string
     * @throws Exception
     */
    public static Playback valueOf(String s, SongController songController) throws PropException {
        //Split the string by the playback delimiter
        String[] t = s.split(Pattern.quote(PLAYBACK_DELIMITER));
        //Check if the size and the identifier of the string are corrects
        if (t.length != 9 || !t[0].equals(PLAYBACK_STRING_ID)) {
            throw new PropException(ErrorString.INCORRECT_FORMAT);
        }
        //Get the song
        Song song = songController.getSong(t[1], t[2]);
        //Get the date
        Calendar d = Calendar.getInstance();
        d.set(Integer.parseInt(t[3]), Integer.parseInt(t[4]), Integer.parseInt(t[5]), Integer.parseInt(t[6]),
                Integer.parseInt(t[7]), Integer.parseInt(t[8]));
        //Return de playback with the defined song and date
        return new Playback(song, d);
    }

    /**
     * Getter method of the <code>song</code> played
     * @return      <code>song</code> played
     */
    public Song getSong() {
        return song;
    }

    /**
     * Setter method of the <b>song</b> played
     * @param   s   <b>song</b>
     * @throws  Exception
     */
    public void setSong (Song s) {
        song = s;
    }

    /**
     * Getter method of the play <b>date</b>
     * @return play <b>date</b>
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Setter method of the play <b>date</b>
     * @param   d    play <b>date</b>
     * @throws Exception
     */
    public void setDate(Calendar d) {
        date = d;
    }

    /**
     * Compare method of playbacks
     * @param p     playback to compare
     * @return true if this playback's date is before p playback's date
     */
    public int compareTo(Playback p) {
        boolean b = date.before(p.getDate());
        if (b) return -1;
        b = date.after(p.getDate());
        if (b) return 1;
        else return 0;
    }

    @Override
    /**
     * toString method of a Playback
     * @return a String in the specified format with the implicit playback
     */
    public String toString() {
        //Get the song of the playback
        Song song = getSong();
        //Get the date of the playback
        Calendar date = getDate();
        //Return a String with the identifier of the song (title+artist) and the date in the specified format
        return PLAYBACK_STRING_ID + PLAYBACK_DELIMITER
                + song.getTitle() + PLAYBACK_DELIMITER
                + song.getArtist() + PLAYBACK_DELIMITER
                + date.get(Calendar.YEAR) + PLAYBACK_DELIMITER
                + date.get(Calendar.MONTH) + PLAYBACK_DELIMITER
                + date.get(Calendar.DAY_OF_MONTH) + PLAYBACK_DELIMITER
                + date.get(Calendar.HOUR_OF_DAY) + PLAYBACK_DELIMITER
                + date.get(Calendar.MINUTE) + PLAYBACK_DELIMITER
                + date.get(Calendar.SECOND);
    }
}