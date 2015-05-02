package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.lang.Override;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * A playback of a song in a specific date
 * @author joaquim.motger
 */

public class Playback implements Comparable<Playback>{

    private Song song;
    private Calendar date;

    private static final String PLAYBACK_DELIMITER = "|P|\n";
    private static final String PLAYBACK_STRING_ID = "PLAYBACK_STRING";

    /**
     * <code>Playback</code> class constructor
     * @param song      <code>song</code> played
     * @param date      play <code>date</code>
     * @throws  Exception
     */
    public Playback (Song song, Calendar date) throws Exception {
        if (song != null && date != null) {
            this.song = song;
            this.date = date;
        }
        else throw new Exception(ErrorString.NULL);
    }

    /**
     * Getter method of the <code>song</code> played
     * @return      <code>song</code> played
     */
    public Song getSong () {
        return song;
    }

    /**
     * Getter method of the play <b>date</b>
     * @return      play <b>date</b>
     */
    public Calendar getDate () {
        return date;
    }

    /**
     * Setter method of the <b>song</b> played
     * @param   song    <b>song</b>
     * @throws  Exception
     */
    public void setSong (Song song) throws Exception {
        if (song != null) this.song = song;
        else throw new Exception(ErrorString.NULL);
    }

    /**
     * Setter method of the play <b>date</b>
     * @param   date    play <b>date</b>
     * @throws  Exception
     */
    public void setDate (Calendar date) throws Exception {
        if (date != null) this.date = date;
        else throw new Exception(ErrorString.NULL);
    }

    /**
     * Compare method of playbacks
     * @param p     playback to compare
     * @return      true if this playback's date is before p playback's date
     */
    public int compareTo(Playback p) {
        boolean b = this.date.before(p.getDate());
        if (b) return -1;
        b = this.date.after(p.getDate());
        if (b) return 1;
        else return 0;
    }

    @Override
    /**
     * toString method of a Playback
     * @return  a String in the specified format with the implicit playback
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
                + date.get(Calendar.HOUR) + PLAYBACK_DELIMITER
                + date.get(Calendar.MINUTE) + PLAYBACK_DELIMITER
                + date.get(Calendar.SECOND);
    }

    /**
     * valueOf method of a Playback
     * @param s     a String with the specified format of a Playback
     * @param songController    the songController that contains the songs
     * @return      the playback obtained by the string
     * @throws      Exception
     */
    public static Playback valueOf(String s, SongController songController) throws Exception {
        //Split the string by the playback delimiter
        String[] t = s.split(Pattern.quote(PLAYBACK_DELIMITER));
        //Check if the size and the identifier of the string are corrects
        if (t.length != 9 || !t[0].equals(PLAYBACK_STRING_ID)) {
            throw new Exception(ErrorString.INCORRECT_FORMAT);
        }
        //Get the song
        Song song = songController.getSong(t[1], t[2]);
        //Get the date
        Calendar d = Calendar.getInstance();
        d.set(Integer.parseInt(t[3]), Integer.parseInt(t[4]), Integer.parseInt(t[5]), Integer.parseInt(t[6]),
                Integer.parseInt(t[7]), Integer.parseInt(t[8]));
        //Return de playback with the defined song and date
        return new Playback(song,d);
    }
}