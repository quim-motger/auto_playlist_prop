package prop.domain;

import prop.PropException;

/**
 * AND complex relation
 * @author joaquim.motger
 */

public class AND extends ComplexRelation {

    /**
     * <b>AND</b> class constructor
     * @param R1    first <b>Relation</b>
     * @param R2    second <b>Relation</b>
     */
    public AND(Relation R1, Relation R2) {
        r1 = R1;
        r2 = R2;
    }

    /**
     * Evaluates AND relation between two songs
     * @param s1    first <b>Song</b>
     * @param s2    second <b>Song</b>
     * @return      true if <b>s1</b> and <b>s2</b> are related by <b>r1</b> and <b>r2</b>; false otherwise
     */
    @Override
    public boolean evaluateSongs(Song s1, Song s2) throws PropException{
        return r1.evaluateSongs(s1, s2) && r2.evaluateSongs(s1, s2);
    }

    @Override
    public boolean evaluateUser(User u) throws PropException {
        return r1.evaluateUser(u) && r2.evaluateUser(u);
    }
}
