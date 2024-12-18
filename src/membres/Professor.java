package membres;

public class Professor extends Membre {
    private String departament;
    private int despatx;

    /**
     * @brief Constructor principal de la classe Professor.
     * @param alias              l'àlies del professor.
     * @param emailInstitucional el correu electrònic institucional del professor.
     * @param alta               la data d'alta del professor.
     * @param departament        el departament al qual pertany (només DEIM o
     *                           DEEEA).
     * @param despatx            el número o identificador del despatx del
     *                           professor.
     * @throws IllegalArgumentException si el departament no és DEIM o DEEEA, o si
     *                                  el despatx és null o buit.
     */
    public Professor(String alias, String emailInstitucional, Data alta, String departament, int despatx) {
        super(alias, emailInstitucional, alta);
        if (!departament.equals("DEIM") && !departament.equals("DEEEA")) {
            throw new IllegalArgumentException("El departament ha de ser DEIM o DEEEA.");
        }
        this.departament = departament;
        this.despatx = despatx;
    }

    /**
     * @brief Obté el departament del professor.
     * @return el departament del professor (DEIM o DEEEA).
     */
    public String getDepartament() {
        return departament;
    }

    /**
     * @brief Obté el despatx del professor.
     * @return el despatx del professor.
     */
    public int getDespatx() {
        return despatx;
    }

    /**
     * @brief Retorna el tipus de membre, en aquest cas "Professor".
     * @return una cadena que indica que és un professor.
     */
    @Override
    public String obtenirTipus() {
        return "Professor";
    }
}
