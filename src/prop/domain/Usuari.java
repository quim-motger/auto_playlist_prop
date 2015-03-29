package PROP.application;

import Genere

/**
 *
 * @author Carles Garcia Cabot
 */
public class Usuari {
    private String nom;
    private Genere genere;
    private Data data_naixement;
    private String pais;

    /**
     * Creadora d'Usuari per defecte
     */
    public Usuari() {
    }

    /**
     * Creadora d'Usuari amb tots els paràmetres
     * @param nom
     * @param gen
     * @param data
     * @param pais
     */
    public Usuari(String nom, Genere gen, Data data, String pais) {
        this.nom = nom;
        genere = gen;
        data_naixement = data;
        this.pais = pais;
    }

    public String consultaNom() {
        return nom;
    }

    public Genere consultaGenere() {
        return genere;
    }

    public Data consultaData_naixement() {
        return data_naixement;
    }

    public String consultaPais() {
        return pais;
    }

    public int edat() {
        // calcula edat
        return 0;
    }

    public void modificaNom(String nom) {
        this.nom = nom;
    }

    public void modificaGenere(Genere gen) {
        genere = gen;
    }

    public void modificaData_naixement(Data data) {
        data_naixement = data;
    }

    public void modificaPais(String pais) {
        this.pais = pais;
    }

}
