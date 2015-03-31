package prop.domain;

import java.util.Calendar;

/**
 * A playback of a song in a specific date
 * @author joaquim.motger
 * @version 1.0
 */

public class Playback {

    private Song song;
    private Calendar date;

    /**
     * <b>Playback</b> class creator
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
}