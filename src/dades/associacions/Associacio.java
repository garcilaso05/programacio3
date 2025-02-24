package dades.associacions;

import dades.membres.Alumne;
import dades.membres.LlistaMembres;
import dades.membres.Membre;

/**
 * Classe que representa una Associació.
 * Gestiona el nom, correu de contacte, titulacions i els membres associats.
 * També inclou els càrrecs de president, secretari i tresorer, que només poden
 * ser ocupats per alumnes.
 */
public class Associacio {
    private String nom; // Nom de l'associació
    private String correuElectronic; // Correu electrònic de contacte

    private String[] titulacions; // Taula de titulacions (sigles)
    private int numTitulacions; // Nombre actual de titulacions

    private LlistaMembres membres; // Taula de membres de l'associació
    private int numMembres; // Nombre actual de membres en l'associació

    private Membre president; // President de l'associació (ha de ser alumne)
    private Membre secretari; // Secretari de l'associació (ha de ser alumne)
    private Membre tresorer; // Tresorer de l'associació (ha de ser alumne)

    private static final int MAX_MEMBRES = 100; // Nombre màxim de membres per associació
    private static final int MAX_TITULACIONS = 10; // Nombre màxim de titulacions

    /**
     * Constructor de la classe Associacio.
     * 
     * @param nom              Nom de l'associació.
     * @param correuElectronic Correu electrònic de contacte.
     */
    public Associacio(String nom, String correuElectronic) {
        this.nom = nom;
        this.correuElectronic = correuElectronic;

        // Inicialització de les taules
        this.titulacions = new String[MAX_TITULACIONS];
        this.numTitulacions = 0;

        this.membres = new LlistaMembres(MAX_MEMBRES);
        this.numMembres = 0;

        this.president = null;
        this.secretari = null;
        this.tresorer = null;
    }

    public String getNom() {
        return nom;
    }

    public String getCorreuElectronic() {
        return correuElectronic;
    }

    public Membre getPresident() {
        return president;
    }

    public Membre getSecretari() {
        return secretari;
    }

    public Membre getTresorer() {
        return tresorer;
    }

    public int getNumMembers(){
        return numMembres;
    }

    /**
     * Afegeix un membre a l'associació si no existeix prèviament.
     * 
     * @param membre Membre a afegir.
     */
    public boolean afegirMembre(Membre membre) {
        for (int i = 0; i < numMembres; i++) {
            if ((membres.consultar(i).getAlias()).equals(membre.getAlias())) {
                return false;
            }
        }

        if (numMembres < MAX_MEMBRES) {
            membres.afegir(membre);
            numMembres++;
            if (membre instanceof Alumne)
                afegirTitulacio(((Alumne) membre).getEnsenyament());
            return true;
        } else {
            return false;
        }
    }

    public LlistaMembres getLlistaMembre() {
        return membres;
    }

    public int getNumTitulacions(){
        return numTitulacions;
    }

    public String[] getTitulacions(){
        return titulacions;
    }
    
    /**
     * Afegeix una titulació a la llista, si no existeix prèviament.
     * 
     * @param titulacio Sigles de la titulació.
     */
    public void afegirTitulacio(String titulacio) {
        // Verifiquem que no es trobi duplicada
        for (int i = 0; i < numTitulacions; i++) {
            if (titulacions[i].equals(titulacio)) {
                return;
            }
        }

        // Afegim la titulació
        if (numTitulacions < MAX_TITULACIONS) {
            titulacions[numTitulacions] = titulacio;
            numTitulacions++;
        }
    }

    /**
     * Assigna un president (ha de ser alumne).
     * 
     * @param membre Membre a assignar com a president.
     */
    public boolean assignarPresident(Membre membre) {
        if (esAlumne(membre)) {
            (this.president) = membre;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Assigna un secretari (ha de ser alumne).
     * 
     * @param membre Membre a assignar com a secretari.
     */
    public boolean assignarSecretari(Membre membre) {
        if (esAlumne(membre)) {
            this.secretari = membre;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Assigna un tresorer (ha de ser alumne).
     * 
     * @param membre Membre a assignar com a tresorer.
     */
    public boolean assignarTresorer(Membre membre) {
        if (esAlumne(membre)) {
            this.tresorer = membre;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica si un membre és un alumne.
     * 
     * @param membre Membre a verificar.
     * @return Cert si és alumne, fals en cas contrari.
     */
    private boolean esAlumne(Membre membre) {
        return (membre instanceof Alumne);
    }

    /**
     * Mostra la informació de l'associació.
     */
    public String mostrarInformacio() {
        String info = "Nom de l'associació: " + nom + "\nCorreu de contacte: " + correuElectronic + "\nTitulacions: ";
        for (int i = 0; i < numTitulacions; i++) {
            info = info + "\n\t- " + titulacions[i];
        }
        info = info + "\nNombre de membres: " + numMembres + "\nPresident/a: " + president.getAlias()
                + "\nSecretari/a: "
                + secretari.getAlias() + "\nTresorer/a: " + tresorer.getAlias();
        return info;
    }

    public int mida() {
        return numMembres;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Nom de l'associació: ").append(nom)
            .append("\nCorreu de contacte: ").append(correuElectronic)
            .append("\nTitulacions: ");
        
        for (int i = 0; i < numTitulacions; i++) {
            info.append("\n\t- ").append(titulacions[i]);
        }
        
        info.append("\nNombre de membres: ").append(numMembres)
            .append("\nPresident/a: ").append(president != null ? president.getAlias() : "No assignat")
            .append("\nSecretari/a: ").append(secretari != null ? secretari.getAlias() : "No assignat")
            .append("\nTresorer/a: ").append(tresorer != null ? tresorer.getAlias() : "No assignat");
        
        return info.toString();
    }



}