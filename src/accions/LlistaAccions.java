package accions;

public class LlistaAccions {
    private Accions[] llista;
    private int capacitat;
    private int count;

    public LlistaAccions(int capacitat) {
        if (capacitat <= 0) {
            throw new IllegalArgumentException("La capacitat ha de ser major a 0.");
        }
        this.capacitat = capacitat;
        this.llista = new Accions[capacitat];
        this.count = 0;
    }

    // Añadir una instancia a la lista
    public void afegir(Accions accio) {
        if (count >= capacitat) {
            throw new IllegalStateException("La llista està plena.");
        }
        llista[count++] = accio;
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
    public void modificar(int posicio, Accions novaAccio) {
        if (posicio < 0 || posicio >= count) {
            throw new IndexOutOfBoundsException("Posició fora de límits.");
        }
        llista[posicio] = novaAccio;
    }

    // Consultar una instancia en una posición específica
    public Accions consultar(int posicio) {
        if (posicio < 0 || posicio >= count) {
            throw new IndexOutOfBoundsException("Posició fora de límits.");
        }
        return llista[posicio];
    }

    // Obtener el tamaño actual de la lista
    public int mida() {
        return count;
    }

    @Override
public String toString() {
    StringBuilder resultat = new StringBuilder("LlistaAccions: [\n");
    for (int i = 0; i < count; i++) {
        resultat.append("  Posició ").append(i).append(": ").append(llista[i].toString()).append("\n");
    }
    resultat.append("]");
    return resultat.toString();
}

}
