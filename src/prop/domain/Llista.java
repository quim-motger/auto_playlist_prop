package prop.domain;

import java.util.ArrayList;

/**
 * Conjunt de cancons ordenades
 * @author gerard.casas.saez
 * @version 1.0
 */
public class Llista {
    
    String titol;
    ArrayList<Canco> cancons;

    /**
     * Creadora de la Classe <code>Llista</code>
     * @param title   Titol de la Llista
     */
    public Llista (String title) { titol=title;}

    /**
     * Consultora del titol de la <code>Llista</code> 
     * @return titol de la <code>Llista</code>
     */
    public String consultaTitol() {return titol;}

    /**
     * Consultora de la canço a la posició pos
     * @param pos   <code>pos</code> més petit que <code>consultaMida()</code> i més gran que 0
     * @return      <code>Canço</code> de la llista en la posicio <code>pos</code>
     */
    public Canco consultaCanço(int pos) {
        return cancons.get(pos);
    }

    /**
     * Consultora de la mida la llista
     * @return Numero de cancons que té la llista
     */
    public int consultaMida() { return cancons.size();}
    
    public int consultaDurada() {
        int total=0;
        for (int i=0; i < consultaMida();++i) {
            total += cancons.get(i).consultaDurada();
        }
        return total;
    }

    /**
     *  Modificadora del titol de la Llista
     * @param title String que volem que p.i. prengui per titol
     */
    public void modificaTitol(String title) {
        titol=title;
    }

    /**
     *  Modificadora que afegeix una canço al final de la Llista
     *  <code>canço</code> està afegida a la Llista amb index <code>consultaMida()</code> - 1  
     *  @see prop.domain.Canco
     */
    public void afegeixCanço(Canco canço) {
        cancons.add(canço);
    }

    /**
     *  Modificadora que esborra una canço de la llista
     *  Les cancons de sota de la canço de index <code>pos</code>, el seu index disminueix en una unitat  
     * @param pos   0 < <code>pos</code> < <code>consultaMida()</code>
     */
    public void esborraCanço(int pos) {
        cancons.remove(pos);
    }

    /**
     * Modificadora de l'Ordre de la Llista
     * Intercanvia d'ordre dos cançons en els indexs senyalats
     * @param index1    0 < <code>index1</code> < <code>consultaMida()</code>
     * @param index2    0 < <code>index2</code> < <code>consultaMida()</code>
     */
    public void canviaOrdre(int index1, int index2) {
        Canco canço= cancons.get(index1);
        cancons.set(index1, cancons.get(index2));
        cancons.set(index2, canço);
    }
}
