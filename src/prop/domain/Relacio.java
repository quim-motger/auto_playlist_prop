package prop.domain;

/**
 * Relació abstracte - 30/3/2015
 * @author gerard.casas.saez
 * @version 1.0
 */
public abstract class Relacio {

    /**
     *
     * @param c1    Canço 1 a evaluar
     * @param c2    Canço 2 a evaluar
     * @return      <code>true</code> si <code>c1</code> i <code>c2</code> estan relacionades
     */
    public abstract boolean avalua(Canco c1, Canco c2);
}
