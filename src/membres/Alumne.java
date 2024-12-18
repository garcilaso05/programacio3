package membres;
/**
 * Classe Alumne que representa un alumne membre d'una associació.
 * Hereta de la classe Membre.
 */
public class Alumne extends Membre {
    private String ensenyament;
    private int anysETSE;
    private boolean graduat;

    /**
     * @brief Constructor per crear un nou alumne.
     * @param alias             l'àlies del membre.
     * @param emailInstitucional el correu electrònic institucional del membre.
     * @param alta              la data d'alta del membre.
     * @param ensenyament       l'ensenyament on està matriculat.
     * @param anysETSE          el número d'anys que porta a l'ETSE.
     * @throws IllegalArgumentException si l'ensenyament és null o els anys són negatius.
     */
    public Alumne(String alias, String emailInstitucional, Data alta, String ensenyament, int anysETSE) {
        super(alias, emailInstitucional, alta);
        if (ensenyament == null || anysETSE < 0) {
            throw new IllegalArgumentException("Dades incorrecte para l'alumne.");
        }
        this.ensenyament = ensenyament;
        this.anysETSE = anysETSE;
        this.graduat = false;
    }

     /**
     * @brief Retorna l'ensenyament de l'alumne.
     * @return l'ensenyament de l'alumne.
     */
    public String getEnsenyament() {
        return ensenyament;
    }

    /**
     * @brief Estableix l'ensenyament de l'alumne.
     * @param ensenyament el nou ensenyament.
     * @throws IllegalArgumentException si l'ensenyament és null.
     */
    public void setEnsenyament(String ensenyament) {
        if (ensenyament == null) {
            throw new IllegalArgumentException("L'ensenyament no pot ser null.");
        }
        this.ensenyament = ensenyament;
    }

    /**
     * @brief Retorna el número d'anys que porta l'alumne a l'ETSE.
     * @return el número d'anys.
     */
    public int getAnysETSE() {
        return anysETSE;
    }

    /**
     * @brief Estableix el número d'anys que porta l'alumne a l'ETSE.
     * @param anysETSE el nou número d'anys.
     * @throws IllegalArgumentException si el número d'anys és negatiu.
     */
    public void setAnysETSE(int anysETSE) {
        if (anysETSE < 0) {
            throw new IllegalArgumentException("Els anys no poden ser negatius.");
        }
        this.anysETSE = anysETSE;
    }

     /**
     * @brief Comprova si l'alumne està graduat.
     * @return true si l'alumne està graduat, false en cas contrari.
     */
    public boolean isGraduat() {
        return graduat;
    }

    /**
     * @brief Gradua l'alumne, controlant si ja està graduat.
     * @throws IllegalStateException si l'alumne ja està graduat.
     */
    public void graduar() {
        if (graduat) {
            throw new IllegalStateException("L'alumne ja està graduat.");
        }
        this.graduat = true;
    }

    @Override
    public String obtenirTipus() {
        return "Alumne";
    }


    @Override
    public String toString() {
        return (super.toString() + ", Ensenyament: " + ensenyament + ", Anys a l'ETSE: " + anysETSE);
    }

    @Override
public Alumne copia() {
    return new Alumne(
        this.getAlias(), 
        this.getEmailInstitucional(),
        new Data(this.getDataAlta().getDia(), this.getDataAlta().getMes(), this.getDataAlta().getAny()),
        this.ensenyament, 
        this.anysETSE
    );
}


}


