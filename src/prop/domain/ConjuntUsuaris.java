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
     * Consultora de la posicio del <code>Usuari</code> amb nom <code>nom</code>
     * @params nom  nom de l'usuari a cercar
     * @return posicio  posicio de l'usuari al cjtUsuaris; -1 si no es troba
     */
    public int consultaPosUsuari(String nom) {
        int i = 0;
        boolean trobat = false;
        while(!trobat && i < mida) {
            Usuari usuari = cjtUsuaris.get(i);
            if (usuari.consultaNom().equals(nom)) trobat = true;
            else ++i;
        }
        if (trobat) return i;
        else return -1;
    }

    /**
     * Afegeix usuari a <code>cjtUsuaris</code>
     * @params  usuari  usuari a afegir
     * @return  i   posicio de l'usuari amb nom en us; -1 si esta disponible
     */
    public int afegeixUsuari(Usuari usuari) {
        int i = consultaPosUsuari(usuari.consultaNom());
        if (i == -1) {
            cjtUsuaris.add(usuari);
            ++mida;
        }
        return i;
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
