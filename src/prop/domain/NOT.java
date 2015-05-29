package prop.domain;

import prop.PropException;

import java.util.ArrayList;

/**
 * Classe NOT, relació complexe 30/03/15.
 *
 * @author gerard.casas.saez
 * @version 1.0
 * @see ComplexRelation
 */
public class NOT extends ComplexRelation {

    private SongSet songSet;

    /**
     * Class NOT creator
     * @param relation   relation to evaluate
     */
    public NOT(Relation relation, SongSet ss) {
        r1 = relation;
        songSet = ss;
    }

    /**
     * Evaluates NOT relation
     * @return      the intersection of the two sets obtained by <code>R1</code> and <code>R2</code>
     * @throws      PropException
     */
    @Override
    public ArrayList<Song> evaluate() {
        ArrayList<Song> songs = songSet.getSongSet();
        for (Song song : r1.evaluate()) {
            songs.remove(song);
        }
        return songs;
    }
}
