package consola;
import java.util.Scanner;

import dades.accions.Accions;
import dades.accions.Demostracio;
import dades.accions.LlistaAccions;
import dades.accions.Xerrada;
import dades.associacions.Associacio;
import dades.associacions.LlistaAssociacions;
import dades.membres.Alumne;
import dades.membres.Data;
import dades.membres.LlistaMembres;
import dades.membres.Membre;
import dades.membres.Professor;
import excepcions.EntradaExcepcion;

public class MenuConsola {
    
    UtilsConsola utils = new UtilsConsola();
    public void mostrarAssociacions(LlistaAssociacions llista) {
        System.out.println("\n=== Llista d’Associacions ===");
        for (int i = 0; i < llista.getElementos(); i++) {
            System.out.println((i + 1) + ". " + llista.consultar(i).toString());
        }
    }

    public void mostrarMembresPerTipus(LlistaAssociacions llistaAssociacions, Scanner op) {
        System.out.println("Indica la Associació que vols consultar: ");
        String texto = op.nextLine().trim(); // Normaliza el texto ingresado
        int numero = llistaAssociacions.buscarNumeroAssociacio(texto);
        
        if (numero == -1) {
            System.out.println("Associació no trobada. Revisa el nom introduït.");
        } else {
            Associacio associacio = llistaAssociacions.consultar(numero);
            System.out.println("1. Professors 2. Alumnes 3. Ambdós");
            int opcion = EntradaExcepcion.verificarEntradaInt(op);
        
            switch (opcion) {
                case 1:
                    System.out.println("=== Professors registrats ===");
                    for (int i = 0; i < associacio.getLlistaMembre().mida(); i++) {
                        if (associacio.getLlistaMembre().consultar(i).obtenirTipus().equals("Professor")) {
                            System.out.println(associacio.getLlistaMembre().consultar(i).getAlias());
                        }
                    }
                    break;
        
                case 2:
                    System.out.println("=== Alumnes registrats ===");
                    for (int i = 0; i < associacio.getLlistaMembre().mida(); i++) {
                        if (associacio.getLlistaMembre().consultar(i).obtenirTipus().equals("Alumne")) {
                            System.out.println(associacio.getLlistaMembre().consultar(i).getAlias());
                        }
                    }
                    break;
        
                case 3:
                    System.out.println("=== Tots els Membres registrats ===");
                    for (int i = 0; i < associacio.getLlistaMembre().mida(); i++) {
                        System.out.println(associacio.getLlistaMembre().consultar(i).getAlias());
                    }
                    break;
        
                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }

    public void mostrarMembresActius(LlistaAssociacions llistaAssociacions, LlistaMembres llistaMembres, Scanner op) {
        System.out.println("Seleccioneu el tipus de membre a mostrar");
        System.out.println("1. Professors 2. Alumnes 3. Ambdós (-1 para cancelar):");
        int opcion = EntradaExcepcion.verificarEntradaInt(op);

        
        LlistaMembres membresActius = new LlistaMembres(llistaAssociacions.getElementos()*llistaMembres.mida());

        for (int i = 0; i < llistaAssociacions.getElementos(); i++) {
            Associacio associacio = llistaAssociacions.consultar(i);
            for (int j = 0; j < associacio.getLlistaMembre().mida(); j++) {
                Membre membre = associacio.getLlistaMembre().consultar(j);
                if (membre.getDataBaixa() == null) { 
                    membresActius.afegir(membre);;
                }
            }
        }

        switch (opcion) {
            case 1:
                System.out.println("=== Professors Actius ===");
                for (int i = 0; i < membresActius.mida(); i++) {
                    if (membresActius.consultar(i).obtenirTipus().equals("Professor")) {
                        System.out.println(membresActius.consultar(i).getAlias());
                    }
                }
                break;

            case 2:
                System.out.println("=== Alumnes Actius ===");
                for (int i = 0; i < membresActius.mida(); i++) {
                    if (membresActius.consultar(i).obtenirTipus().equals("Alumne")) {
                        System.out.println(membresActius.consultar(i).getAlias());
                    }
                }
                break;

            case 3:
                System.out.println("=== Tots els Membres Actius ===");
                for (int i = 0; i < membresActius.mida(); i++) {
                    System.out.println(membresActius.consultar(i).getAlias());
                }
                break;

            default:
                System.out.println("Opció no vàlida.");
        }
    }

    public void mostrarAccionsAssociacio(LlistaAssociacions llistaAssociacions, LlistaAccions llistaAccions, Scanner op) {
        System.out.println("Introdueix el nom de l\'associació:");
        String nomAssociacio = op.nextLine().trim(); 

        int index = llistaAssociacions.buscarNumeroAssociacio(nomAssociacio);

        if (index == -1) {
            System.out.println("Associació no trobada. Revisa el nom introduït.");
        } else {
            Associacio associacio = llistaAssociacions.consultar(index);

            System.out.println("\n=== Llista d’Accions per l’Associació " + associacio.getNom() + " ===");
            boolean found = false;
    
            for (int i = 0; i < llistaAccions.mida(); i++) {
                Accions accio = llistaAccions.consultar(i);
                if (associacio.getNom().equals(accio.getAssociacio())) { 
                    System.out.println(utils.convertirAccionLinea(accio));
                    found = true;
                }
            }
    
            if (!found) {
                System.out.println("No hi ha accions registrades per aquesta associació.");
            }
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
        String data  = EntradaExcepcion.verificarEntradaData(op);
        if (!data.equals("-1")){
            String[] partsData = data.split("/");
            if (partsData.length != 3) {
                System.out.println("Format de data incorrecte.");

            } else {
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
                int assistents = EntradaExcepcion.verificarEntradaInt(op);

                // Crear y añadir la xerrada
                Xerrada novaXerrada = new Xerrada(associacio, titol, responsable, dataXerrada, ponents, assistents);
                try {
                    llistaAccions.afegir(novaXerrada);
                    System.out.println("Xerrada afegida amb èxit!");
                } catch (IllegalStateException e) {
                    System.out.println("No es pot afegir la xerrada. La llista està plena.");
                }
            }
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
        int minimAssistents = EntradaExcepcion.verificarEntradaInt(op);
    
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
        String dataInput = EntradaExcepcion.verificarEntradaData(op);
        String[] parts = dataInput.split("/");
        if(!dataInput.equals("-1")){
            if (parts.length != 3) {
                System.out.println("Format de data incorrecte. Torna-ho a intentar.");
            } else {
                Data dataLimit;
                int dia = Integer.parseInt(parts[0]);
                int mes = Integer.parseInt(parts[1]);
                int any = Integer.parseInt(parts[2]);
                dataLimit = new Data(dia, mes, any);
            
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
       
    }

    public void mostraLlistaAccions(LlistaAccions llistaAccions, Scanner op){
        System.out.println("\n=== Mostrar Llista d'Accions ===");

    System.out.println("Escull el filtre: 1. Xerrada 2. Demostracio 3. Ambdós");
    int opcion = EntradaExcepcion.verificarEntradaInt(op);

    switch (opcion) {
        case 1:
            for (int i = 0; i < llistaAccions.mida(); i++) {
                if (llistaAccions.consultar(i).obtenirTipus().equals("Xerrada")) System.out.println("\n\t"+llistaAccions.consultar(i).toString());
            }
            break;

        case 2:
        for (int i = 0; i < llistaAccions.mida(); i++) {
            if (llistaAccions.consultar(i).obtenirTipus().equals("Demostracio")) System.out.println("\n\t"+llistaAccions.consultar(i).toString());
        }
            break;

        case 3:
        for (int i = 0; i < llistaAccions.mida(); i++) {
            System.out.println("\n\t"+llistaAccions.consultar(i).toString());
        }
            break;

        default:
            System.out.println("Opció no vàlida.");
    
        }
    }

    public void llistaXerrades(LlistaAccions llistaAccions, Scanner op){
        System.out.println("\n=== Llista de Xerrades en rang de dates  ===");

        System.out.println("\nIntrodueix els rangs de cerca...");
        int dia_i, mes_i, any_i, dia_f, mes_f, any_f;
        System.out.println("\nIntrodueixi el dia d'inici: ");
        dia_i = EntradaExcepcion.verificarEntradaInt(op);
        System.out.println("\nIntrodueixi el mes d'inici: ");
        mes_i = EntradaExcepcion.verificarEntradaInt(op);
        System.out.println("\nIntrodueixi l'any d'inici: ");
        any_i = EntradaExcepcion.verificarEntradaInt(op);
        Data inici = new Data(dia_i, mes_i, any_i);

        System.out.println("\nIntrodueixi el dia de final: ");
        dia_f = EntradaExcepcion.verificarEntradaInt(op);
        System.out.println("\nIntrodueixi el mes de final: ");
        mes_f = EntradaExcepcion.verificarEntradaInt(op);
        System.out.println("\nIntrodueixi l'any de final: ");
        any_f = EntradaExcepcion.verificarEntradaInt(op);
        Data fi = new Data(dia_f, mes_f, any_f);

        for (int i = 0; i < llistaAccions.mida(); i++) {
            if (llistaAccions.consultar(i).obtenirTipus().equals("Xerrada") && ((Xerrada)(llistaAccions.consultar(i))).getFecha().esAnterior(fi) && inici.esAnterior(((Xerrada)(llistaAccions.consultar(i))).getFecha())) {
                System.out.println("\n\t"+llistaAccions.consultar(i).toString());
            }
        }

        

    }

    public void afegirNouMembre(LlistaMembres llistaMembres, LlistaAssociacions llistaAssociacions, LlistaAccions llistaAccions, Scanner op){
                System.out.println("\n=== Afegir nou membre a una Associació ===");
                System.out.println("\nIndica la Associació: ");
                String texto = op.nextLine().trim(); // Normaliza el texto ingresado
                int numero = llistaAssociacions.buscarNumeroAssociacio(texto);
                
                if (numero == -1) {
                    System.out.println("\n>> Associació no trobada. Revisa el nom introduït.");
                } else {
                
                    System.out.println("\nIntrodueixi el nom del nou membre: ");
                    texto = op.nextLine().trim();
                    boolean trobat = false;
                    int i = 0;
                    while (i < llistaMembres.mida() && trobat == false) {
                        if (llistaMembres.consultar(i).getAlias().equals(texto)) {
                            trobat = true;
                        }
                        i++;
                    }

                    
                    if (trobat){
                        i--;
                        int j = 0;
                        trobat = false;
                        while (j < llistaAssociacions.consultar(numero).mida() && trobat == false) {
                            if (llistaMembres.consultar(i).equals(llistaAssociacions.consultar(numero).getLlistaMembre().consultar(j))) {
                                trobat = true;
                            }
                            j++;
                        }

                            if (trobat) {
                                System.out.println("\nEl membre ja està associat a la associació.");
                            }else {
                                System.out.println("\nEl membre no està associat a la associació així que s'afegirà.");
                                llistaAssociacions.consultar(numero).afegirMembre(llistaMembres.consultar(i));
                            }


                    }else{

                        System.out.println("El membre no està associat a cap associació");
                        System.out.println("\nIndica primer si es 1. Alumne o 2. Professor");
                        int opcion = EntradaExcepcion.verificarEntradaInt(op);
                        if (opcion == 1){
                            
                            System.out.println("Alias: " + texto);
                            System.out.println("\nEmail: ");
                            String email = op.nextLine().trim();
                            System.out.println("\nIntrodueix la data d'alta: ");
                            int dia, mes, any;
                            System.out.println("\nDia: ");
                            dia = EntradaExcepcion.verificarEntradaInt(op);
                            System.out.println("\nMes: ");
                            mes = EntradaExcepcion.verificarEntradaInt(op);
                            System.out.println("\nAny: ");
                            any = EntradaExcepcion.verificarEntradaInt(op);
                            Data alta = new Data(dia, mes, any);
                            System.out.println("\nEnsenyament: ");
                            String estudis = op.nextLine().trim();
                            System.out.println("\nAnys a l'ETSE: ");
                            int anys = EntradaExcepcion.verificarEntradaInt(op);
                            Alumne nouAlumne = new Alumne(texto, email, alta, estudis, anys);
                            llistaMembres.afegir(nouAlumne);
                            llistaAssociacions.consultar(numero).afegirMembre(nouAlumne);
                                                    
                        } else if (opcion == 2){

                            System.out.println("Alias: " + texto);
                            System.out.println("\nEmail: ");
                            String email = op.nextLine().trim();
                            System.out.println("\nIntrodueix la data d'alta: ");
                            int dia, mes, any;
                            System.out.println("\nDia: ");
                            dia = EntradaExcepcion.verificarEntradaInt(op);
                            System.out.println("\nMes: ");
                            mes = EntradaExcepcion.verificarEntradaInt(op);
                            System.out.println("\nAny: ");
                            any = EntradaExcepcion.verificarEntradaInt(op);
                            Data alta = new Data(dia, mes, any);
                            System.out.println("\nDepartament: ");
                            String departament = op.nextLine().trim();
                            System.out.println("\nDespatx: ");
                            int despatx = EntradaExcepcion.verificarEntradaInt(op);
                            Professor nouProfessor = new Professor(texto, email, alta, departament, despatx);
                            llistaMembres.afegir(nouProfessor);
                            llistaAssociacions.consultar(numero).afegirMembre(nouProfessor);

                        }

                        System.out.println("\nNou membre creat i afegit a la associació");




                    }

                }

    }

    public void novaDemostracio(LlistaMembres llistaMembres, LlistaAssociacions llistaAssociacions, LlistaAccions llistaAccions, Scanner op){
        System.out.println("\n=== Crear una nova Demostració ===");

        System.out.println("Indica la Associació organitzadora de la nova Demostració: ");
        String texto = op.nextLine().trim(); // Normaliza el texto ingresado
        int numero = llistaAssociacions.buscarNumeroAssociacio(texto);
        
        if (numero == -1) {
            System.out.println("Associació no trobada. Revisa el nom introduït.");
        } else {

            System.out.println("\nTítol de la nova Demostració: ");
            String titol = op.nextLine().trim();
            System.out.println("\nNom del membre responsable: ");
            String nom = op.nextLine().trim();


            boolean trobat = false;
            int i = 0;
            while (i < llistaMembres.mida() && trobat == false) {
                if (llistaMembres.consultar(i).getAlias().equals(nom)) {
                    trobat = true;
                }
                i++;
            }

            if (trobat){
                i--;

            }else{

                System.out.println("El membre no existeix, crea un de nou: ");
                System.out.println("\nIndica primer si es 1. Alumne o 2. Professor");
                int opcion = EntradaExcepcion.verificarEntradaInt(op);
                if (opcion == 1){
                    
                    System.out.println("Alias: " + nom);
                    System.out.println("\nEmail: ");
                    String email = op.nextLine().trim();
                    System.out.println("\nIntrodueix la data d'alta: ");
                    int dia, mes, any;
                    System.out.println("\nDia: ");
                    dia = EntradaExcepcion.verificarEntradaInt(op);
                    System.out.println("\nMes: ");
                    mes = EntradaExcepcion.verificarEntradaInt(op);
                    System.out.println("\nAny: ");
                    any = EntradaExcepcion.verificarEntradaInt(op);
                    Data alta = new Data(dia, mes, any);
                    System.out.println("\nEnsenyament: ");
                    String estudis = op.nextLine().trim();
                    System.out.println("\nAnys a l'ETSE: ");
                    int anys = EntradaExcepcion.verificarEntradaInt(op);
                    Alumne nouAlumne = new Alumne(texto, email, alta, estudis, anys);
                    llistaMembres.afegir(nouAlumne);
                    i = llistaMembres.mida();
                    i--;

                    
                } else if (opcion == 2){

                    System.out.println("Alias: " + texto);
                    System.out.println("\nEmail: ");
                    String email = op.nextLine().trim();
                    System.out.println("\nIntrodueix la data d'alta: ");
                    int dia, mes, any;
                    System.out.println("\nDia: ");
                    dia = EntradaExcepcion.verificarEntradaInt(op);
                    System.out.println("\nMes: ");
                    mes = EntradaExcepcion.verificarEntradaInt(op);
                    System.out.println("\nAny: ");
                    any = EntradaExcepcion.verificarEntradaInt(op);
                    Data alta = new Data(dia, mes, any);
                    System.out.println("\nDepartament: ");
                    String departament = op.nextLine().trim();
                    System.out.println("\nDespatx: ");
                    int despatx = EntradaExcepcion.verificarEntradaInt(op);
                    Professor nouProfessor = new Professor(texto, email, alta, departament, despatx);
                    llistaMembres.afegir(nouProfessor);
                    i = llistaMembres.mida();
                    i--;

                }

                System.out.println("\n>> Membre creat correctament, continuant amb la creació de la Demostració ");




            }


            System.out.println("\nIntrodueix la data de disseny: ");
            int dia, mes, any;
            System.out.println("\nDia: ");
            dia = EntradaExcepcion.verificarEntradaInt(op);
            System.out.println("\nMes: ");
            mes = EntradaExcepcion.verificarEntradaInt(op);
            System.out.println("\nAny: ");
            any = EntradaExcepcion.verificarEntradaInt(op);
            Data dataDisseny = new Data(dia, mes, any);
            boolean actiu = false;
            System.out.println("\nLa demostració està activa? 1. (si) 2. (no)");
            if(EntradaExcepcion.verificarEntradaInt(op) == 1){
                actiu = true;
            }
            System.out.println("\nVeguades que es farà: ");
            int vegades = EntradaExcepcion.verificarEntradaInt(op);
            System.out.println("\nCost de tot el material: ");
            double costMaterials = EntradaExcepcion.verificarEntradaDouble(op);

            llistaAccions.afegir(new Demostracio(llistaAssociacions.consultar(numero), titol, llistaMembres.consultar(i--), dataDisseny, actiu, vegades, costMaterials));
            System.out.println("Nova Demostracio creada!");

        }
    }

    public void personaMesActiva(LlistaMembres llistaMembres, LlistaAssociacions llistaAssociacions){
        System.out.println("\n=== Persona mes Activa ===");
        Data llistaDates[] = new Data[llistaMembres.mida()];
        int actiu[] = new int[llistaMembres.mida()];

        for (int i = 0; i <actiu.length; i++){
            actiu[i] = 0;
            llistaDates[i] = new Data(31, 12, 9999);
        }

        for (int i = 0; i<(llistaAssociacions.getElementos()); i++) { 
            for (int j = 0; j < (llistaAssociacions.consultar(i).getLlistaMembre().mida()); j++) {
                    for (int k = 0; k < llistaMembres.mida(); k++) {
                        if (llistaMembres.consultar(k).getAlias().equals(llistaAssociacions.consultar(i).getLlistaMembre().consultar(j).getAlias())) {
                            if (llistaAssociacions.consultar(i).getLlistaMembre().consultar(j).getDataAlta().esAnterior(llistaDates[k])){
                            llistaDates[k] = llistaAssociacions.consultar(i).getLlistaMembre().consultar(j).getDataAlta();
                            }
                            actiu[k] = actiu[k]++;
                        }
                    }
            }
        }
        int max = actiu[0];
        for (int i = 0; i < actiu.length; i++) {

            if (actiu[i] > max) {
                max = actiu[i];
            }

        }

        int count = 0;
        int membreMesActiu = 0;

        for (int i = 0; i < actiu.length; i++) {

            if (actiu[i] == max) {
                count++;
                membreMesActiu = i;
            }

        }

        Data mesAntic = new Data(31, 12, 9999);
        

        if (count != 1) {
            for (int i = 0; i < actiu.length; i++) {
                if (actiu[i] == max && llistaDates[i].esAnterior(mesAntic)) {
                    mesAntic = llistaDates[i];
                    membreMesActiu = i;
                }
            }

        } 

        System.out.println("\nMembre amb més activitat: " + llistaMembres.consultar(membreMesActiu).getAlias());

    }

    public void valorarXerrada (LlistaAccions llistaAccions, Scanner op) {
        System.out.println("\n=== Valorar Xerrada ===");
        System.out.println("Indica el nom de la Xarrada que vols valorar: ");
        String nom = op.nextLine().trim();
        for (int i = 0; i< llistaAccions.mida(); i++){
            if (llistaAccions.consultar(i).obtenirTipus().equals("Xerrada")){
                if (llistaAccions.consultar(i).getTitulo().equals(nom)){
                    System.out.println("Introdueix la valoració de la xarrada: ");
                    int valoracio = EntradaExcepcion.verificarEntradaInt(op);
                    ((Xerrada)llistaAccions.consultar(i)).agregarValoracion(valoracio);
                    System.out.println("Valoració afegida correctament!");
                }
            }
        }
    }

    public void xerradesPonent(LlistaMembres llistaMembres, LlistaAccions llistaAccions, Scanner op){
        System.out.println("\n=== Cercador de les Xarrades d'un membre ===");
        System.out.println("Indica el nom del ponent que vols consultar: ");
        String nombre = op.nextLine().trim();
        boolean teXerrada = false;

        boolean trobat = false;
        int i = 0;
        while (i < llistaMembres.mida() && trobat == false) {
            if (llistaMembres.consultar(i).getAlias().equals(nombre)) {
                trobat = true;
            }
            i++;
        }

        if (trobat){
            
            for (i = 0; i<(llistaAccions.mida()); i++) { 

                if (llistaAccions.consultar(i).obtenirTipus().equals("Xerrada")){

                    for (int j = 0; j <  (((Xerrada)llistaAccions.consultar(i)).obtenerListaPonentes()).mida(); j++) { 

                        if((((Xerrada)llistaAccions.consultar(i)).obtenerListaPonentes()).consultar(j).getAlias().equals(nombre)){
                    
                            System.out.println(llistaAccions.consultar(i).toString()+"\n");
                            teXerrada = true;

                        }
                    
                    }

                }


            }
            if (!teXerrada){
                System.out.println("El membre indicat no ha fet cap xarrada");
            }

        }else{

            System.out.println("El membre no existeix");

        }
    }
    

}


