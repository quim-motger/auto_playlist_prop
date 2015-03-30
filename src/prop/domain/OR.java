package prop.domain;

/**
 * OR class, relacio Complexe
 * Creation Date: 30/03/15.
 * @author gerard.casas.saez
 * @version 1.0
 * @see prop.domain.RelacioComplexa
 */
public class OR extends RelacioComplexa {

    /**
     * Creadora Classe OR <code>relacio1 or relacio2</code>
     * @param relacio1 Primera relació del OR
     * @param relacio2 Segona relació del OR
     */
    public OR(Relacio relacio1, Relacio relacio2) {
        r1 = relacio1;
        r2 = relacio2;
    }

    /**
     * *
     * @param c1    Canço 1 a evaluar
     * @param c2    Canço 2 a evaluar
     * @return      <code>true</code> si c1 i c2 estan relacionades o bé a R1 o bé a R2
     */
    @Override
    public boolean avalua(Canco c1, Canco c2) {
        return r1.avalua(c1, c2) || r2.avalua(c1, c2);
    }
}
