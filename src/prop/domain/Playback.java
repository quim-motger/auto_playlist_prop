package prop.domain;

import prop.ErrorString;

import java.lang.Override;
import java.util.Calendar;

/**
 * A playback of a song in a specific date
 * @author joaquim.motger
 */

public class Playback {

    private Song song;
    private Calendar date;

    private static final String PLAYBACK_DELIMITER = "|P|";
    private static final String PLAYBACK_STRING_ID = "PLAYBACK_STRING\n";

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
     * @param   song    <b>song</b>
     */
    public void setSong (Song song) {
        this.song = song;
    }

    /**
     * Setter method of the play <b>date</b>
     * @param   date    play <b>date</b>
     */
    public void setDate (Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        Song song = getSong();
        Calendar date = getDate();
        String s = PLAYBACK_STRING_ID + PLAYBACK_DELIMITER
                + song.getTitle() + PLAYBACK_DELIMITER
                + song.getArtist() + PLAYBACK_DELIMITER
                + date.get(Calendar.YEAR) + PLAYBACK_DELIMITER
                + date.get(Calendar.MONTH) + PLAYBACK_DELIMITER
                + date.get(Calendar.DAY_OF_MONTH) + PLAYBACK_DELIMITER
                + date.get(Calendar.HOUR) + PLAYBACK_DELIMITER
                + date.get(Calendar.MINUTE) + PLAYBACK_DELIMITER
                + date.get(Calendar.SECOND) + PLAYBACK_DELIMITER;
        return s;
    }

    public static Playback valueOf(String s, SongController songController) throws Exception {
        String[] t = s.split(PLAYBACK_DELIMITER);
        if (t.length!=9 || !t[0].equals(PLAYBACK_STRING_ID)) {
            throw new Exception(ErrorString.INCORRECT_FORMAT);
        }
        Song song = songController.getSong(t[1], t[2]);
        Calendar d = Calendar.getInstance();
        d.set(Integer.parseInt(t[3]),Integer.parseInt(t[4]),Integer.parseInt(t[5]),Integer.parseInt(t[6]),
                Integer.parseInt(t[7]),Integer.parseInt(t[8]));
        return new Playback(song,d);
    }
}