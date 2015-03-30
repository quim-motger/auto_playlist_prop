package prop.domain;

/**
 * A complex relation between songs, composed of simple or complex relations
 * @author Oscar Ma�as Sanchez
 * @see prop.domain.Relacio
 */
public abstract class RelacioComplexa extends Relacio {

    protected Relacio r1;
    protected Relacio r2;

}
