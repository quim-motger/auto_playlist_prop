package prop.domain;

import java.util.ArrayList;

/**
 * Conjunt d'usuaris
 * @author joaquim.motger
 * @version 1.0
 */

public class ConjuntUsuaris {

    private ArrayList<Usuari> cjtUsuaris;
    private int mida;


    /**
     * Creadora de <code>ConjuntUsuaris</code>
     */
    public ConjuntUsuaris() {
        mida = 0;
    }

    /**
     * Consultora del <code>cjtUsuaris</code>
     * @return      <code>cjtUsuaris</code>
     */
    public ArrayList<Usuari> consultaUsuaris() {
        return cjtUsuaris;
    }

    /**
     * Consultora de <code>mida</code>
     * @return      <code>mida</code>
     */
    public int consultaMida() {
        return mida;
    }

    /**
     * Afegeix usuari a <code>cjtUsuaris</code>
     * @params  usuari  usuari a afegir
     */
    public void afegeixUsuari(Usuari usuari) {
        cjtUsuaris.add(usuari);
        ++mida;
    }

    /**
     * Elimina l'usuari <code>i</code> de <code>cjtUsuaris</code>
     * @params  i   posicio de l'usuari a cjtUsuaris
     */
    public void eliminaUsuari (int i) {
        if (i >= 0 && i < mida) {
            cjtUsuaris.remove(i);
            --mida;
        }
    }
}
