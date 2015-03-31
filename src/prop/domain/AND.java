package prop.domain;

import javax.management.relation.Relation;
import java.lang.Override;

/**
 * AND complex relation
 * @author joaquim.motger
 * @version 1.0
 */

public class AND extends ComplexRelation {

    /**
     * <b>AND</b> class creator
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
    public boolean evaluates(Song s1, Song s2) {
        return r1.evaluates(s1, s2) && r2.evaluates(s1, s2);
    }
}
