package prop.domain;

import prop.PropException;

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
     * Evaluates AND relation between two songs
     * @param s1    first <code>Song</code>
     * @param s2    second <code>Song</code>
     * @return      true if <code>s1</code> and <code>s2</code> are related by <code>r1</code> and <code>r2</code>; false otherwise
     * @throws      PropException
     */
    @Override
    public boolean evaluateSongs(Song s1, Song s2) throws PropException{
        return r1.evaluateSongs(s1, s2) && r2.evaluateSongs(s1, s2);
    }

}
