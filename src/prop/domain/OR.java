package prop.domain;

import prop.PropException;

import java.util.ArrayList;

/**
 * OR class, ComplexRelation
 * Creation Date: 30/03/15.
 * @author gerard.casas.saez
 * @version 1.0
 * @see ComplexRelation
 */
public class OR extends ComplexRelation {

    /**
     * OR class creator
     * @param relation1 First relation of OR
     * @param relation2 Second relation of OR
     */
    public OR(Relation relation1, Relation relation2) {
        r1 = relation1;
        r2 = relation2;
    }

    /**
     * Evaluates OR relation
     * @return      the union of the two sets obtained by <code>R1</code> and <code>R2</code>
     * @throws      PropException
     */
    @Override
    public ArrayList<Song> evaluate() throws PropException{
        ArrayList<Song> songsr2 = r2.evaluate();

        ArrayList<Song> songs = r1.evaluate();

        for (Song songr2 : songsr2) {
            if (!songs.contains(songr2)) songs.add(songr2);
        }
        return songs;
    }

}
