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
    private static final String delimiter = "\n";

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
        String s = song.getTitle() + delimiter + song.getArtist() + delimiter
                + date.get(Calendar.YEAR) + delimiter + date.get(Calendar.MONTH) + delimiter
                + date.get(Calendar.DAY_OF_MONTH) + delimiter + date.get(Calendar.HOUR) + delimiter
                + date.get(Calendar.MINUTE) + delimiter + date.get(Calendar.SECOND) + delimiter;
        return s;
    }

    public static Playback valueOf(String s, SongController songController) {
        String[] t = s.split(delimiter);
        Song song = songController.getSong(t[0], t[1]);
        Calendar d = Calendar.getInstance();
        d.set(Integer.parseInt(t[2]),Integer.parseInt(t[3]),Integer.parseInt(t[4]),Integer.parseInt(t[5]),
                Integer.parseInt(t[6]),Integer.parseInt(t[7]));
        return new Playback(song,d);
    }
}