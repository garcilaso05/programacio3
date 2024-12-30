package main;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import accions.Accions;
import accions.Demostracio;
import accions.LlistaAccions;
import accions.Xerrada;
import associacions.Associacio;
import associacions.LlistaAssociacions;
import membres.LlistaMembres;
import membres.Membre;
import membres.Data;

public class MenuConsola {
    
    UtilsConsola utils = new UtilsConsola();
    public void mostrarAssociacions(LlistaAssociacions llista) {
        System.out.println("\n=== Llista d’Associacions ===");
        for (int i = 0; i < llista.getElementos(); i++) {
            System.out.println((i + 1) + ". " + llista.consultar(i).toString());
        }
    }

    public void mostrarMembresPerTipus(LlistaAssociacions llistaAssociacions, Scanner op) {
        String texto = op.nextLine().trim(); // Normaliza el texto ingresado
        int numero = llistaAssociacions.buscarNumeroAssociacio(texto);
        
        if (numero == -1) {
            System.out.println("Associació no trobada. Revisa el nom introduït.");
        } else {
            Associacio associacio = llistaAssociacions.consultar(numero);
            System.out.println("1. Professors 2. Alumnes 3. Ambdós");
            int opcion = Integer.parseInt(op.nextLine());
            
            switch (opcion) {
                case 1:
                    for (int i = 0; i < associacio.getLlistaMembre().mida(); i++) {
                        if (associacio.getLlistaMembre().consultar(i).obtenirTipus().equals("Professor")) {
                            System.out.println("Professor: " + associacio.getLlistaMembre().consultar(i).getAlias());
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < associacio.getLlistaMembre().mida(); i++) {
                        if (associacio.getLlistaMembre().consultar(i).obtenirTipus().equals("Alumne")) {
                            System.out.println("Alumne: " + associacio.getLlistaMembre().consultar(i).getAlias());
                        }
                    }
                    break;

                case 3:
                    for (int i = 0; i < associacio.getLlistaMembre().mida(); i++) {
                        System.out.println("Membre: " + associacio.getLlistaMembre().consultar(i).getAlias());
                    }
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }

    public void mostrarMembresActius(LlistaAssociacions llistaAssociacions, Scanner op) {
        System.out.println("Seleccioneu el tipus de membre a mostrar");
        System.out.println("1. Professors 2. Alumnes 3. Ambdós:");
        int opcion = Integer.parseInt(op.nextLine());
        
        Set<Membre> membresActius = new HashSet<>(); // To avoid duplicates

        for (int i = 0; i < llistaAssociacions.getElementos(); i++) {
            Associacio associacio = llistaAssociacions.consultar(i);
            for (int j = 0; j < associacio.getLlistaMembre().mida(); j++) {
                Membre membre = associacio.getLlistaMembre().consultar(j);
                if (membre.getDataBaixa() == null) { // Only active members
                    membresActius.add(membre);
                }
            }
        }

        switch (opcion) {
            case 1:
                System.out.println("=== Professors Actius ===");
                membresActius.stream()
                    .filter(m -> m.obtenirTipus().equals("Professor"))
                    .forEach(m -> System.out.println(m.getAlias()));
                break;

            case 2:
                System.out.println("=== Alumnes Actius ===");
                membresActius.stream()
                    .filter(m -> m.obtenirTipus().equals("Alumne"))
                    .forEach(m -> System.out.println(m.getAlias()));
                break;

            case 3:
                System.out.println("=== Tots els Membres Actius ===");
                membresActius.forEach(m -> System.out.println(m.getAlias()));
                break;

            default:
                System.out.println("Opció no vàlida.");
        }
    }

    public void mostrarAccionsAssociacio(LlistaAssociacions llistaAssociacions, LlistaAccions llistaAccions, Scanner op) {
        System.out.println("Introdueix el nom de l'associació:");
        String nomAssociacio = op.nextLine().trim(); // Normalize input

        // Search for the association
        int index = llistaAssociacions.buscarNumeroAssociacio(nomAssociacio);

        if (index == -1) {
            System.out.println("Associació no trobada. Revisa el nom introduït.");
            return;
        }

        // Get the association object
        Associacio associacio = llistaAssociacions.consultar(index);

        // Find actions related to this association
        System.out.println("\n=== Llista d’Accions per l’Associació " + associacio.getNom() + " ===");
        boolean found = false;

        for (int i = 0; i < llistaAccions.mida(); i++) {
            Accions accio = llistaAccions.consultar(i);
            if (associacio.getNom().equals(accio.getAssociacio())) { // Assume this method checks if the action belongs to the association
                System.out.println(utils.convertirAccionLinea(accio));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No hi ha accions registrades per aquesta associació.");
        }
    }

    public void afegirNovaAssociacio(LlistaAssociacions llistaAssociacions, LlistaMembres llistaMembres, Scanner op) {
        System.out.println("\n=== Afegir Nova Associació ===");

        // Collect association details
        System.out.print("Nom de l'associació: ");
        String nom = op.nextLine().trim();
        if (llistaAssociacions.buscarNumeroAssociacio(nom) != -1) {
            System.out.println("Ja existeix una associació amb aquest nom.");
            return;
        }

        System.out.print("Correu electrònic de contacte: ");
        String correuElectronic = op.nextLine().trim();

        System.out.print("Titulacions (separades per comes): ");
        String[] titulacions = op.nextLine().split(",");

        System.out.print("Alias del President (alumne): ");
        String aliasPresident = op.nextLine().trim();

        System.out.print("Alias del Secretari (alumne): ");
        String aliasSecretari = op.nextLine().trim();

        System.out.print("Alias del Tresorer (alumne): ");
        String aliasTresorer = op.nextLine().trim();

        Membre president = llistaMembres.consultarPorAlias(aliasPresident);
        Membre secretari = llistaMembres.consultarPorAlias(aliasSecretari);
        Membre tresorer = llistaMembres.consultarPorAlias(aliasTresorer);


        
        if (president == null || !president.obtenirTipus().equals("Alumne") 
        || secretari == null || !secretari.obtenirTipus().equals("Alumne") 
        || tresorer == null || !tresorer.obtenirTipus().equals("Alumne")) {
            System.out.println("Els càrrecs només poden ser ocupats per alumnes vàlids.");
            return;
        }

        llistaMembres.consultarPorAlias(aliasPresident).afegirAssociacio(nom);
        llistaMembres.consultarPorAlias(aliasSecretari).afegirAssociacio(nom);
        llistaMembres.consultarPorAlias(aliasTresorer).afegirAssociacio(nom);
        
        Associacio novaAssociacio = new Associacio(nom, correuElectronic);

        for (String titulacio : titulacions) {
            if (!titulacio.trim().isEmpty()) {
                novaAssociacio.afegirTitulacio(titulacio.trim());
            }
        }

        novaAssociacio.assignarPresident(president);
        novaAssociacio.assignarSecretari(secretari);
        novaAssociacio.assignarTresorer(tresorer);

        try {
            llistaAssociacions.afegir(novaAssociacio);
            System.out.println("Associació afegida amb èxit!");
        } catch (IllegalStateException e) {
            System.out.println("No es pot afegir l'associació. La llista està plena.");
        }
    }

    public void afegirNovaXerrada(LlistaAssociacions llistaAssociacions, LlistaMembres llistaMembres, LlistaAccions llistaAccions, Scanner op) {
        System.out.println("\n=== Afegir Nova Xerrada ===");

        // Solicitar la asociación organizadora
        System.out.print("Nom de l'associació organitzadora: ");
        String nomAssociacio = op.nextLine().trim();
        int indexAssociacio = llistaAssociacions.buscarNumeroAssociacio(nomAssociacio);
        if (indexAssociacio == -1) {
            System.out.println("Associació no trobada.");
            return;
        }
        Associacio associacio = llistaAssociacions.consultar(indexAssociacio);

        // Solicitar título de la xerrada
        System.out.print("Títol de la xerrada: ");
        String titol = op.nextLine().trim();

        // Solicitar responsable
        System.out.print("Alias del responsable de la xerrada: ");
        String aliasResponsable = op.nextLine().trim();
        Membre responsable = llistaMembres.consultarPorAlias(aliasResponsable);
        if (responsable == null) {
            System.out.println("El responsable no existeix.");
            return;
        }

        // Solicitar fecha
        System.out.print("Data de la xerrada (format DD/MM/YYYY): ");
        String data = op.nextLine().trim();
        String[] partsData = data.split("/");
        if (partsData.length != 3) {
            System.out.println("Format de data incorrecte.");
            return;
        }
        Data dataXerrada = new Data(Integer.parseInt(partsData[0]), Integer.parseInt(partsData[1]), Integer.parseInt(partsData[2]));

        // Solicitar ponents
        System.out.print("Alias dels ponents (separats per comes, màxim 3): ");
        String[] aliasPonents = op.nextLine().split(",");
        Membre[] ponents = new Membre[3];
        for (int i = 0; i < aliasPonents.length && i < 3; i++) {
            Membre ponent = llistaMembres.consultarPorAlias(aliasPonents[i].trim());
            if (ponent != null) {
                ponents[i] = ponent;
            }
        }

        // Solicitar número de assistents
        System.out.print("Nombre d'assistents esperats: ");
        int assistents = Integer.parseInt(op.nextLine().trim());

        // Crear y añadir la xerrada
        Xerrada novaXerrada = new Xerrada(associacio, titol, responsable, dataXerrada, ponents, assistents);
        try {
            llistaAccions.afegir(novaXerrada);
            System.out.println("Xerrada afegida amb èxit!");
        } catch (IllegalStateException e) {
            System.out.println("No es pot afegir la xerrada. La llista està plena.");
        }
    }

    public void mostrarDemostracionsNoActives(LlistaAccions llistaAccions) {
        System.out.println("\n=== Demostracions No Actives ===");

        double costTotal = 0.0; // Acumulador del costo total de materiales
        boolean found = false; // Bandera para indicar si se encontraron demostraciones no activas

        for (int i = 0; i < llistaAccions.mida(); i++) {
            Accions accio = llistaAccions.consultar(i);

            // Verificar si es una demostración y si está inactiva
            if (accio instanceof Demostracio) {
                Demostracio demostracio = (Demostracio) accio;
                if (!demostracio.isActiva()) {
                    found = true;
                    System.out.println(utils.convertirAccionLinea(demostracio));
                    costTotal += demostracio.getcostMaterials();
                }
            }
        }

        if (!found) {
            System.out.println("No hi ha demostracions no actives registrades.");
        } else {
            System.out.printf("Cost econòmic total de les demostracions no actives: %.2f %n", costTotal);
        }
    }

    public void mostrarXerradesAmbAssistents(LlistaAccions llistaAccions, Scanner op) {
        System.out.println("\n=== Consultar Xerrades amb Assistents ===");
    
        // Solicitar el número mínimo de asistentes
        System.out.print("Indica el número mínim d'assistents: ");
        int minimAssistents = Integer.parseInt(op.nextLine().trim());
    
        boolean found = false; // Bandera para verificar si se encuentran resultados
    
        System.out.println("\nXerrades amb més de " + minimAssistents + " assistents:");
        for (int i = 0; i < llistaAccions.mida(); i++) {
            Accions accio = llistaAccions.consultar(i);
    
            // Verificar si la acción es una Xerrada y cumple con el criterio
            if (accio instanceof Xerrada) {
                Xerrada xerrada = (Xerrada) accio;
                if (xerrada.getAsistentes() > minimAssistents) {
                    System.out.println(utils.convertirAccionLinea(xerrada));
                    found = true;
                }
            }
        }
    
        // Si no se encontraron xerrades, mostrar un mensaje
        if (!found) {
            System.out.println("No s'han trobat xerrades amb més de " + minimAssistents + " assistents.");
        }
    }
    
    public void mostrarXerradaMillorValorada(LlistaAccions llistaAccions) {
        System.out.println("\n=== Xerrada Millor Valorada ===");
    
        Xerrada millorXerrada = null;
        double millorMitjana = -1.0; // Para guardar la mejor media encontrada
        int millorNumValoracions = -1; // Para guardar el número de valoraciones de la mejor charla
    
        for (int i = 0; i < llistaAccions.mida(); i++) {
            Accions accio = llistaAccions.consultar(i);
    
            // Verificar si la acción es una Xerrada
            if (accio instanceof Xerrada) {
                Xerrada xerrada = (Xerrada) accio;
                double mitjanaActual = xerrada.calcularValoracionMedia();
                int numValoracionsActual = xerrada.getNumValoraciones();
    
                // Comparar según los criterios establecidos
                if (mitjanaActual > millorMitjana || (mitjanaActual == millorMitjana && numValoracionsActual > millorNumValoracions)) {
                    millorXerrada = xerrada;
                    millorMitjana = mitjanaActual;
                    millorNumValoracions = numValoracionsActual;
                }
            }
        }
    
        // Mostrar resultado
        if (millorXerrada != null) {
            System.out.println("La xerrada millor valorada és:");
            System.out.println(utils.convertirAccionLinea(millorXerrada));
            System.out.printf("Mitjana de valoracions: %.2f%n", millorMitjana);
        } else {
            System.out.println("No s'ha trobat cap xerrada.");
        }
    }
    
    public void donarDeBaixaDemostracions(LlistaAccions llistaAccions, Scanner op) {
        System.out.println("\n=== Donar de Baixa Demostracions ===");
    
        // Solicitar la fecha límite
        System.out.print("Introdueix la data límit (format DD/MM/YYYY): ");
        String dataInput = op.nextLine().trim();
        String[] parts = dataInput.split("/");
    
        if (parts.length != 3) {
            System.out.println("Format de data incorrecte. Torna-ho a intentar.");
            return;
        }
    
        Data dataLimit;
        try {
            int dia = Integer.parseInt(parts[0]);
            int mes = Integer.parseInt(parts[1]);
            int any = Integer.parseInt(parts[2]);
            dataLimit = new Data(dia, mes, any);
        } catch (NumberFormatException e) {
            System.out.println("Format de data incorrecte. Torna-ho a intentar.");
            return;
        }
    
        // Recorrer y eliminar las demostraciones que cumplen los criterios
        int eliminades = 0;
    
        for (int i = 0; i < llistaAccions.mida(); i++) {
            Accions accio = llistaAccions.consultar(i);
    
            if (accio instanceof Demostracio) {
                Demostracio demostracio = (Demostracio) accio;
    
                // Verificar las condiciones: no activa y diseñada antes de la fecha límite
                if (!demostracio.isActiva() && demostracio.getDataDisseny().esAnterior(dataLimit)) {
                    System.out.println("Eliminant demostració: " + utils.convertirAccionLinea(demostracio));
                    llistaAccions.eliminar(i);
                    i--; // Ajustar el índice después de eliminar
                    eliminades++;
                }
            }
        }
    
        // Mostrar resultados
        if (eliminades == 0) {
            System.out.println("No s'han trobat demostracions que compleixin els criteris.");
        } else {
            System.out.println("Demostracions eliminades: " + eliminades);
        }
    }
    

}


