package prop.domain;

/**
 * OR class, ComplexRelation
 * Creation Date: 30/03/15.
 * @author gerard.casas.saez
 * @version 1.0
 * @see prop.domain.RelacioComplexa
 */
public class OR extends RelacioComplexa {

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
     * *
     * @param s1    Song 1 to evaluate
     * @param s2    Song 2 to evaluate
     * @return      <code>true</code> if s1 and s2 are related in R1 or R2
     */
    @Override
    public boolean evaluate(Canco s1, Canco s2) {
        return r1.evaluate(s1, s2) || r2.evaluate(s1, s2);
    }
}
