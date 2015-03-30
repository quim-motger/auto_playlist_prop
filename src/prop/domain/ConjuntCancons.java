package prop.domain;

import java.util.ArrayList;

/**
 * A set of songs
 * @author Oscar Mañas Sanchez
 */
public class ConjuntCancons {

    private ArrayList<Canco> songSet;

    public ConjuntCancons() {
        songSet = new ArrayList<Canco>();
    }

    /**
     * @return  the size of the set
     */
    public int mida() {
        return songSet.size();
    }

    /**
     * Add a song to the set
     * @param canco the song to add
     * @return      true if the song was added
     *              false if the song was present, and thus not added
     */
    public boolean afegirCanco(Canco canco) {
        if (cercaCanco(canco.consultaId()) == null) {
            songSet.add(canco);
            return true;
        }
        else
            return false;
    }

    /**
     * Remove a song from the set
     * @param id    the identification of a song
     * @return      the song if present
     *              null if not present
     */
    public Canco eliminaCanco(int id) {
        int i = cercaPosCanco(id);
        if (i != -1)
            return songSet.remove(i);
        else
            return null;
    }

    /**
     * Search for a group of songs
     * @param ids    list of identifications of the songs to search
     * @return      list of present songs
     */
    public ArrayList<Canco> cercaCancons(int[] ids) {
        ArrayList<Canco> songList = new ArrayList<Canco>();
        for (int i = 0; i < ids.length; ++i) {
            Canco c = cercaCanco(ids[i]);
            if (c != null)
                songList.add(c);
        }
        return songList;
    }

    /**
     * Search for a song
     * @param id    the identification of the song to search
     * @return      the song if present
     *              null if not present
     */
    public Canco cercaCanco(int id) {
        for (int i = 0; i < songSet.size(); ++i) {
            if (songSet.get(i).consultaId() == id)
                return songSet.get(i);
        }
        return null;
    }

    /**
     * Search for a song index
     * @param id    the identification of the song to search
     * @return      the index of the song in the set if present
     *              -1 if not present
     */
    private int cercaPosCanco(int id) {
        for (int i = 0; i < songSet.size(); ++i) {
            if (songSet.get(i).consultaId() == id)
                return i;
        }
        return -1;
    }
}
