package prop.domain;

import prop.PropException;

import java.util.ArrayList;

/**
 * Abstract Relation - 30/3/2015
 * @author gerard.casas.saez
 * @version 1.0
 */
public abstract class Relation {

    /** Evaluates the relation
     * @return      An ArrayList with all songs that match the relation
     */
    public abstract ArrayList<Song> evaluate() throws PropException;


}
