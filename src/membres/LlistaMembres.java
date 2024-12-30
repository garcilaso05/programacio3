package membres;

public class LlistaMembres {
    private Membre[] llista;
    private int capacitat;
    private int count;

    public LlistaMembres(int capacitat) {
        if (capacitat <= 0) {
            throw new IllegalArgumentException("La capacitat ha de ser major a 0.");
        }
        this.capacitat = capacitat;
        this.llista = new Membre[capacitat];
        count = 0;
    }

    // Añadir una instancia a la lista
    public void afegir(Membre membre) {
        if (count >= capacitat) {
            throw new IllegalStateException("La llista està plena.");
        }
        llista[count++] = membre;
    }

    // Eliminar una instancia por posición
    public void eliminar(int posicio) {
        if (posicio < 0 || posicio >= count) {
            throw new IndexOutOfBoundsException("Posició fora de límits.");
        }
        for (int i = posicio; i < count - 1; i++) {
            llista[i] = llista[i + 1];
        }
        llista[--count] = null;
    }

    // Modificar una instancia en una posición específica
    public void modificar(int posicio, Membre nouMembre) {
        if (posicio < 0 || posicio >= count) {
            throw new IndexOutOfBoundsException("Posició fora de límits.");
        }
        llista[posicio] = nouMembre;
    }

    // Consultar una instancia en una posición específica
    public Membre consultar(int posicio) {
        if (posicio < 0 || posicio >= count) {
            throw new IndexOutOfBoundsException("Posició fora de límits.");
        }
        return llista[posicio];
    }

    /**
     * Busca un membre por su alias en la lista.
     * 
     * @param alias Alias del membre a buscar.
     * @return El membre correspondiente si se encuentra, null en caso contrario.
     */
    public Membre consultarPorAlias(String alias) {
        if (alias == null || alias.isEmpty()) {
            throw new IllegalArgumentException("El alias no puede ser null o vacío.");
        }

        for (int i = 0; i < count; i++) {
            if (llista[i].getAlias().equals(alias)) {
                return llista[i];
            }
        }
        return null; // No encontrado
    }

    // Obtener el tamaño actual de la lista
    public int mida() {
        return count;
    }

    public boolean esAlumne(int i){
        
        if (llista[i] instanceof Alumne) 
        return true;
        else
        return false;

    }

    @Override
public String toString() {
    StringBuilder resultat = new StringBuilder("LlistaMembres: [\n");
    for (int i = 0; i < count; i++) {
        resultat.append("  Posició ").append(i).append(": ").append(llista[i].toString()).append("\n");
    }
    resultat.append("]");
    return resultat.toString();
}


}
