package prop.domain;

/**
 * Abstract Relation - 30/3/2015
 * @author gerard.casas.saez
 * @version 1.0
 */
public abstract class Relation {

    /**
     * @param s1    Song 1 to evaluate
     * @param s2    Song 2 to evaluate
     * @return      <code>true</code> if <code>s1</code> and <code>s2</code> are related
     */
    public abstract boolean evaluate(Song s1, Song s2);

}
