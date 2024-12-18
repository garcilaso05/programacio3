package accions;

import associacions.Associacio;
import membres.Membre;
import membres.Data;

public class Xerrada extends Accions {

    private Data fecha;
    private Membre[] ponente;
    private int asistentes;
    private int[] valoraciones;
    private int numValoraciones;

    public Xerrada(Associacio nombreAsociacion, String titulo, Membre responsable, Data fecha, Membre[] ponente,
            int asistentes) {
        super(nombreAsociacion, titulo, responsable);
        this.fecha = fecha;
        this.ponente = new Membre[3];

        for (int i = 0; i < ponente.length && i < 3; i++) {
            this.ponente[i] = ponente[i];
        }
        this.asistentes = asistentes;
        this.valoraciones = new int[100];
        this.numValoraciones = 0;
    }

    public Data getFecha() {
        return fecha;
    }

    public int getAsistentes() {
        return asistentes;
    }

    public void agregarValoracion(int valoracion) {
        if (valoracion < 0 || valoracion > 10) {
            throw new IllegalArgumentException("La valoración debe estar entre 0 y 10.");
        }
        if (numValoraciones < valoraciones.length) {
            valoraciones[numValoraciones] = valoracion;
            numValoraciones++;
        } else {
            throw new IllegalArgumentException("No se pueden agregar más valoraciones.");
        }
    }

    public double calcularValoracionMedia() {
        if (numValoraciones == 0) {
            return 0.0;
        }
        int suma = 0;
        for (int i = 0; i < numValoraciones; i++) {
            suma += valoraciones[i];
        }
        return (double) suma / numValoraciones;
    }

    public String obtenerPonente() {
        String resultado = "";
        for (int i = 0; i < ponente.length; i++) {
            if (ponente[i] != null) {
                resultado += ponente[i].getAlias() + (i < ponente.length - 1 && ponente[i + 1] != null ? ", " : "");
            }
        }
        return resultado.isEmpty() ? "Ninguno" : resultado;
    }

    @Override
    public String toString() {
        return super.toString() + ", Fecha: " + fecha + ", ponente: " + obtenerPonente() +
                ", Asistentes: " + asistentes + ", Valoración Media: " + calcularValoracionMedia();
    }
}