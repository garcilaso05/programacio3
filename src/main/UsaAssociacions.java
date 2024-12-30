package main;

import associacions.*;
import membres.LlistaMembres;

import java.io.IOException;
import java.util.Scanner;
import accions.*;
/**
 * Classe principal per provar el funcionament de totes les classes del
 * projecte.
 */
public class UsaAssociacions {
    public static void main(String[] args) {
        
        UtilsConsola consola = new UtilsConsola();
        MenuConsola menu = new MenuConsola();

        
        final String FILE_ASSOCIACIONS = "data/LlistaAssociacions.dat";
        final String FILE_MEMBRES = "data/LlistaMembres.txt";
        final String FILE_ACCIONS = "data/LlistaAccions.txt";

    
        LlistaAssociacions llistaAssociacions = new LlistaAssociacions(10);
        LlistaAccions llistaAccions = new LlistaAccions(100);   // Capacidad inicial
        LlistaMembres llistaMembres = new LlistaMembres(100);   // Capacidad inicial

        
         try {
            consola.carregarMembres(FILE_MEMBRES, llistaMembres);
            consola.carregarAssociacions(FILE_ASSOCIACIONS, llistaAssociacions, llistaMembres);
            consola.carregarAccions(FILE_ACCIONS, llistaAccions, llistaAssociacions, llistaMembres);
        } catch (IOException e) {
            System.out.println("Error carregant dades: " + e.getMessage());
        } 

        //Opciones del menu:

        int opcion = 0;
        boolean exit = false;
        Scanner op = new Scanner(System.in);

        do {
            System.out.println("\nMenú Principal");
            System.out.println("1. Mostrar dades de la llista d’associacions");
            System.out.println("2. Mostrar dades de membres d’una associació");
            System.out.println("3. Mostra membres Actius");

            System.out.println("5. Mostrar Accions de UNA Associacio");

            System.out.println("7. Afegir nova Associacio");

            System.out.println("9. Afegir nova Xerrada");

            System.out.println("11. Mostrar demostracions no actius i cost total");

            System.out.println("13. Mostrar Amb mes Assistents indicat");

            System.out.println("15. Mostrar Xerrada millor valoradas");

            System.out.println("17. Donar de baixa les demostracions no actives dissenyades abans d’una certa data");
            System.out.println("18. Sortir");
            System.out.print("Escull una opció: ");

            opcion = Integer.parseInt(op.nextLine());

            switch (opcion) {
                case 1:
                    menu.mostrarAssociacions(llistaAssociacions);
                    break;
            
                case 2:
                // No funciona bien del todo aun, revisar
                    menu.mostrarMembresPerTipus(llistaAssociacions, op);
                    break;
            
                case 3:
                    menu.mostrarMembresActius(llistaAssociacions, op);
                    break;
            
                case 4:
                    // Acción para el caso 4
                    break;

                case 5:
                    menu.mostrarAccionsAssociacio(llistaAssociacions, llistaAccions, op);
                    break;
            
                case 6:
                    // Acción para el caso 6
                    break;
            
                case 7:
                    menu.afegirNovaAssociacio(llistaAssociacions, llistaMembres, op);
                    break;
            
                case 8:
                    // Acción para el caso 8
                    break;
            
                case 9:
                    menu.afegirNovaXerrada(llistaAssociacions, llistaMembres, llistaAccions, op);
                    break;
            
                case 10:
                    // Acción para el caso 10
                    break;
            
                case 11:
                    menu.mostrarDemostracionsNoActives(llistaAccions);
                    break;
            
                case 12:
                    // Acción para el caso 12
                    break;
            
                case 13:
                    menu.mostrarXerradesAmbAssistents(llistaAccions, op);
                    break;
            
                case 14:
                    // Acción para el caso 14
                    break;
            
                case 15:
                    menu.mostrarXerradaMillorValorada(llistaAccions);
                    break;

                case 16:
                    // Acción para el caso 16
                    break;
            
                case 17:
                    menu.donarDeBaixaDemostracions(llistaAccions, op);
                    break;
        
                case 18:
                    // Acción para el caso 18
                    exit = true;
                    break;
            
                default:
                    // Acción por defecto
            }
            
    }while (exit != true);

        op.close();
         
        try {
            consola.guardarAssociacions(FILE_ASSOCIACIONS, llistaAssociacions);
            consola.guardarMembres(FILE_MEMBRES, llistaMembres);
            consola.guardarAccions(FILE_ACCIONS, llistaAccions);
        } catch (IOException e) {
            System.out.println("Error guardant dades: " + e.getMessage());
        }
        
        System.out.println("\n=== FI DELS TESTS ===");
    }

    

}
