package associacions;

import java.io.Serializable;

public class LlistaAssociacions implements Serializable {
    private Associacio[] llista; // Array que almacena las instancias
    private int capacitat;       // Capacidad máxima del array
    private int nombreActual;    // Número actual de elementos en la lista

    // Constructor
    public LlistaAssociacions(int capacitat) {
        if (capacitat <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0.");
        }
        this.capacitat = capacitat;
        this.llista = new Associacio[capacitat];
        this.nombreActual = 0;
    }
    
    public int getElementos(){
        return nombreActual;
    }

    // Método para añadir una nueva associació
    public void afegir(Associacio associacio) {
        if (associacio == null) {
            throw new IllegalArgumentException("La associació no puede ser null.");
        }
        if (nombreActual >= capacitat) {
            throw new IllegalStateException("La lista está llena.");
        }
        llista[nombreActual] = associacio;
        nombreActual++;
    }

    // Método para eliminar una associació por índice
    public void eliminar(int index) {
        if (index < 0 || index >= nombreActual) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        // Mover todos los elementos una posición hacia arriba
        for (int i = index; i < nombreActual - 1; i++) {
            llista[i] = llista[i + 1];
        }
        llista[nombreActual - 1] = null; // Limpiar el último elemento
        nombreActual--;
    }

    // Método para modificar una associació en un índice específico
    public void modificar(int index, Associacio novaAssociacio) {
        if (novaAssociacio == null) {
            throw new IllegalArgumentException("La nueva associació no puede ser null.");
        }
        if (index < 0 || index >= nombreActual) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        llista[index] = novaAssociacio;
    }

    // Método para consultar una associació por índice
    public Associacio consultar(int index) {
        if (index < 0 || index >= nombreActual) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        return llista[index];
    }

    // Método para obtener todas las associacions (copia del array actual)
    public Associacio[] getAllAssociacions() {
        Associacio[] copia = new Associacio[nombreActual];
        for (int i = 0; i < nombreActual; i++) {
            copia[i] = llista[i];
        }
        return copia;
    }


    public int buscarNumeroAssociacio(String nom) {
        for (int i = 0; i < nombreActual; i++) {
            if (llista[i].getNom().equals(nom)) {
                return i;
            }
        }
        return -1; // No encontrado
    }

    // Método toString para mostrar todas las associacions
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Llista d'Associacions:\n");
        for (int i = 0; i < nombreActual; i++) {
            sb.append(i).append(": ").append(llista[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
