package prop.domain;

/**
 * A complex relation between songs, composed of simple or complex relations.
 * @author oscar.manas
 * @see Relation
 */
public abstract class ComplexRelation extends Relation {

    protected Relation r1;
    protected Relation r2;

    /**
     * Default constructor.
     */
    public ComplexRelation() {}

}
