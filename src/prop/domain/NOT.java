package prop.domain;

import prop.PropException;

/**
 * Classe NOT, relació complexe 30/03/15.
 *
 * @author gerard.casas.saez
 * @version 1.0
 * @see ComplexRelation
 */
public class NOT extends ComplexRelation {

    /**
     * Class NOT creator
     * @param relation   relation to evaluate
     */
    public NOT(Relation relation) {
        r1 = relation;
    }

    /**
     * *
     * @param s1    Song 1 to evaluate
     * @param s2    Song 2 to evaluate
     * @return      <code>true</code> if <code>s1</code> and <code>s2</code> are not related
     */
    @Override
    public boolean evaluateSongs(Song s1, Song s2) throws PropException{
        return !r1.evaluateSongs(s1, s2);
    }

    @Override
    public boolean evaluateUser(User u) throws PropException{
        return !r1.evaluateUser(u);
    }
}
