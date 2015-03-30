package prop.domain;

import java.util.Calendar;

/**
 * Una reproduccio d'una canco en una data i hora determinades
 * @author joaquim.motger
 * @version 1.0
 */

public class Reproduccio {

    private Canco canco;
    private Calendar data;

    /**
     * Creadora de la classe <code>Reproduccio</code>
     * @param canco     nom de la canco reproduida
     * @param data      data de la reproduccio
     */
    public Reproduccio (Canco canco, Calendar data) {
        this.canco = canco;
        this.data = data;
    }

    /**
     * Consultora de la canco de <code>Reproduccio</code>
     * @return      <code>Canco</code> de la reproduccio
     */
    public Canco consultaCanco () {
        return canco;
    }

    /**
     * Consultora de la data de <code>Reproduccio</code>
     * @return      <code>Data</code> de la reproduccio
     */
    public Calendar consultaData () {
        return data;
    }
}