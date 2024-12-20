package accions;

import membres.Membre;
import associacions.Associacio;

public abstract class Accions {

    private static int contadorCodigos = 100; // Contador para generar códigos únicos
    private String codigo; // Código único de la acción
    private String titulo; // Título descriptivo
    private Membre responsable; // Alias del responsable de la acción

    // Constructor
    public Accions(Associacio nombreAsociacion, String titulo, Membre responsable) {
        this.codigo = generarCodigo(nombreAsociacion.getNom());
        this.titulo = titulo;
        this.responsable = responsable;
        // Hay que controlar que el responsable sea miembro de alguna de las
        // associaciones que organiza
    }

    // Método para generar el código automáticamente
    private static String generarCodigo(String nombreAsociacion) {
        String prefijo = "";
        int caracteresTomar = 3;

        if (nombreAsociacion.length() < 3) {
            caracteresTomar = nombreAsociacion.length();
        }

        for (int i = 0; i < caracteresTomar; i++) {
            char c = nombreAsociacion.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char) (c - ('a' - 'A'));
            }
            prefijo += c;
        }

        String codigo = prefijo + contadorCodigos;
        contadorCodigos++;
        return codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Membre getResponsable() {
        return responsable;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + ", Titulo: " + titulo + ", Responsable: " + responsable;
    }

    public abstract Accions copia();
    
}