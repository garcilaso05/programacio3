

package membres;
/**
 * Classe abstracta que representa un membre d'una associació.
 * Conté atributs i funcionalitats comunes per a tots els membres.
 */
public abstract class Membre {

    private String alias;
    private String emailInstitucional;
    private Data dataAlta;
    private Data dataBaixa;
    private String[] associacions;
    private int nombreAssociacions;

    /**
     * @brief Constructor principal de la classe Membre.
     * @param alias              l'àlies del membre.
     * @param emailInstitucional el correu electrònic institucional del membre.
     * @param alta               la data d'alta del membre.
     */
    public Membre(String alias, String emailInstitucional, Data alta) {
        this.alias = alias;
        this.emailInstitucional = emailInstitucional;

        // Copiem valor per valor per no passar la referència
        this.dataAlta = new Data(alta.getDia(), alta.getMes(), alta.getAny());

        // Màxim tres associacions
        this.associacions = new String[3];
        this.nombreAssociacions = 0;
    }

    /**
     * @brief Obté l'àlies del membre.
     * @return l'àlies del membre.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @brief Obté el correu electrònic institucional del membre.
     * @return el correu electrònic institucional.
     */
    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    /**
     * @brief Obté la data d'alta del membre.
     * @return la data d'alta.
     */
    public Data getDataAlta() {
        return dataAlta;
    }

    /**
     * @brief Obté la data de baixa del membre.
     * @return la data de baixa, o null si el membre no està donat de baixa.
     */
    public Data getDataBaixa() {
        return dataBaixa;
    }

    public String[] getAssociacions(){
        return associacions;
    }

    public int getNumAssociacions(){
        return nombreAssociacions;
    }

    /**
     * @brief Dona de baixa el membre.
     * @param dataBaixa la data en què es vol donar de baixa.
     * @return true si la baixa s'ha realitzat correctament, false si el membre ja està de baixa o si la data de baixa és anterior a la d'alta.
     */
    public boolean donarDeBaixa(Data dataBaixa) {
        // Controlar si la persona ja està donada de baixa
        if (this.dataBaixa != null) {
            return false;
        }

        // Controlar que la baixa no sigui abans de l'alta
        if (dataBaixa.esAnterior(dataAlta)) {
            return false;
        }
        this.dataBaixa = dataBaixa;
        return true;
    }

    /**
     * @brief Afegeix una associació al membre si no existeix prèviament.
     * @param nomAssociacio el nom de l'associació a afegir.
     * @return true si s'ha afegit correctament, false si el membre ja està associat a tres associacions
     *         o si l'associació ja existeix.
     */
    public boolean afegirAssociacio(String nomAssociacio) {
        // Comprova si l'associació ja existeix a la llista
        for (int i = 0; i < nombreAssociacions; i++) {
            if (associacions[i].equals(nomAssociacio)) {
                return false; // Ja existeix
            }
        }
        // Comprova si el membre ja està associat a tres associacions
        if (nombreAssociacions >= 3) {
            return false; // Límits superats
        }
        // Afegeix l'associació i incrementa el comptador
        this.associacions[nombreAssociacions++] = nomAssociacio;
        return true;
    }


    /**
     * @brief Obté el tipus específic del membre.
     * @return una cadena amb el tipus de membre.
     */
    //Aquest mètode és abstracte i ha de ser implementat a les subclasses.
    public abstract String obtenirTipus();

    public abstract Membre copia();
}
