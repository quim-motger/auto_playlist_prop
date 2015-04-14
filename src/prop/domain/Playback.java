package prop.domain;

import java.lang.Override;
import java.util.Calendar;

/**
 * A playback of a song in a specific date
 * @author joaquim.motger
 */

public class Playback {

    private Song song;
    private Calendar date;

    /**
     * <b>Playback</b> class constructor
     * @param song      <b>song</b> played
     * @param date      play <b>date</b>
     */
    public Playback (Song song, Calendar date) {
        this.song = song;
        this.date = date;
    }

    /**
     * Getter method of the <b>song</b> played
     * @return      <b>song</b> played
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
     * @param     <b>song</b>
     */
    public void setSong (Song song) {
        this.song = song;
    }

    /**
     * Setter method of the play <b>date</b>
     * @param     play <b>date</b>
     */
    public void setDate (Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String s = getSong.getTitle() + "\n" + getSong.getArtist() + "\n"
                + getDate.get(Calendar.YEAR) + "\n" + getDate.get(Calendar.MONTH) + "\n"
                + getDate.get(Calendar.DAY_OF_MONTH) + "\n" + getDate.get(Calendar.HOUR) + "\n"
                + getDate.get(Calendar.MINUTE) + "\n" + getDate.get(Calendar.SECOND) + "\n";
        return s;
    }

    public static Playback valueOf(String s) {

    }
}