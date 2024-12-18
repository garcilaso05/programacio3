package accions;

import associacions.Associacio;
import membres.Data;
import membres.Membre;

public class Demostracio extends Accions {

    private Data dataDisseny;
    private boolean activa;
    private int vegades;
    private double costMaterials;

    public Demostracio(Associacio nombreAsociacion, String titulo, Membre responsable, Data dataDisseny, boolean activa,
            int vegades, double costMaterials) {
        super(nombreAsociacion, titulo, responsable);
        this.dataDisseny = dataDisseny;
        this.activa = activa;
        this.vegades = vegades;
        this.costMaterials = costMaterials;
    }

    public boolean isActiva() {
        return activa;
    }

    public double getcostMaterials() {
        return costMaterials;
    }

    // Demanem el to string de la classe pare afegint les dades de la filla
    @Override
    public String toString() {
        return super.toString() + ", Fecha Diseño: " + dataDisseny + ", Activa: " + activa + ", Veces Ofrecida: "
                + vegades + ", Costo Materiales: " + costMaterials;
    }
}