package prop.domain;

import prop.PropException;

import java.util.ArrayList;

/**
 * AND complex relation
 * @author joaquim.motger
 */

public class AND extends ComplexRelation {

    /**
     * <code>AND</code> class constructor
     * @param R1    first <code>Relation</code>
     * @param R2    second <code>Relation</code>
     */
    public AND(Relation R1, Relation R2) {
        r1 = R1;
        r2 = R2;
    }

    /**
     * Evaluates AND relation
     * @return      the intersection of the two sets obtained by <code>R1</code> and <code>R2</code>
     * @throws      PropException
     */
    @Override
    public ArrayList<Song> evaluate() {
        ArrayList<Song> songs = r1.evaluate();
        songs.retainAll(r2.evaluate());
        return songs;
    }

}
