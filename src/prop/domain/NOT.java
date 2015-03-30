package prop.domain;

/**
 * Classe NOT, relació complexe 30/03/15.
 *
 * @author gerard.casas.saez
 * @version 1.0
 * @see prop.domain.Relacio
 */
public class NOT extends RelacioComplexa {

    /**
     * Creadora de la classe NOT subclasse de Complexe
     * @param relacio   Relacio a evaluar
     */
    public NOT(Relacio relacio) {
        r1 = relacio;
    }

    /**
     * *
     * @param c1    Canço 1 a evaluar
     * @param c2    Canço 2 a evaluar
     * @return      <code>true</code> si <code>c1</code> i <code>c2</code> no estan relacionades
     */
    @Override
    public boolean avalua(Canco c1, Canco c2) {
        return !r1.avalua(c1, c2);
    }
}
