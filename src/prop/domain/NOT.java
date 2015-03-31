package prop.domain;

/**
 * Classe NOT, relació complexe 30/03/15.
 *
 * @author gerard.casas.saez
 * @version 1.0
 * @see prop.domain.RelacioComplexa
 */
public class NOT extends RelacioComplexa {

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
    public boolean evaluate(Canco s1, Canco s2) {
        return !r1.evaluate(s1, s2);
    }
}
