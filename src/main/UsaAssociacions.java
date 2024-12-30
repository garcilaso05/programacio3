package main;

import associacions.*;
import membres.Alumne;
import membres.Professor;
import membres.Membre;
import membres.Data;
import membres.LlistaMembres;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import accions.*;

/**
 * Classe principal per provar el funcionament de totes les classes del
 * projecte.
 */
public class UsaAssociacions {
    public static void main(String[] args) {

        //Gestion de archvios
        //YASSIR acabar


        LlistaAssociacions llistaAssociacions = new LlistaAssociacions(10);
        LlistaAccions llistaAccions = new LlistaAccions(100);   // Capacidad inicial
        LlistaMembres llistaMembres = new LlistaMembres(100);   // Capacidad inicial



        // Crear instancias de Alumne
        Alumne joanPerez = new Alumne("Joan Pérez", "joanperez@urv.cat", new Data(15, 6, 2020), "Enginyeria Informàtica", 2);
        Alumne mariaGarcia = new Alumne("Maria García", "mariagarcia@urv.cat", new Data(7, 3, 2018), "Matemàtiques", 1);
        Alumne carlesMartinez = new Alumne("Carles Martínez", "carlesmartinez@urv.cat", new Data(21, 9, 2021), "Física", 3);
        Alumne lauraRodriguez = new Alumne("Laura Rodríguez", "laurarodriguez@urv.cat", new Data(12, 4, 2019), "Química", 4);
        Alumne annaLopez = new Alumne("Anna López", "annalopez@urv.cat", new Data(30, 11, 2017), "Enginyeria Civil", 2);
        Alumne pereVila = new Alumne("Pere Vila", "perevila@urv.cat", new Data(11, 1, 2020), "Biologia", 1);
        Alumne martaSoler = new Alumne("Marta Soler", "martasoler@urv.cat", new Data(22, 5, 2018), "Història", 2);
        Alumne jordiFont = new Alumne("Jordi Font", "jordifont@urv.cat", new Data(13, 7, 2021), "Enginyeria Mecànica", 3);
        Alumne nuriaRoca = new Alumne("Núria Roca", "nuriaroca@urv.cat", new Data(18, 2, 2019), "Arquitectura", 1);
        Alumne albertFerrer = new Alumne("Albert Ferrer", "albertferrer@urv.cat", new Data(5, 12, 2017), "Ciències Polítiques", 4);
        Alumne sergiTorres = new Alumne("Sergi Torres", "sergitorres@urv.cat", new Data(22, 3, 2021), "Medicina", 2);
        Alumne claraFerran = new Alumne("Clara Ferran", "claraferran@urv.cat", new Data(14, 9, 2019), "Enginyeria Electrònica", 3);
        Alumne paulaVidal = new Alumne("Paula Vidal", "paulavidal@urv.cat", new Data(8, 6, 2018), "Química Industrial", 1);
        Alumne oriolRiera = new Alumne("Oriol Riera", "oriolriera@urv.cat", new Data(3, 2, 2020), "Dret", 4);
        Alumne helenaMarti = new Alumne("Helena Martí", "helenamarti@urv.cat", new Data(10, 10, 2017), "Filologia Catalana", 2);
        Alumne marcSerra = new Alumne("Marc Serra", "marcserra@urv.cat", new Data(27, 8, 2019), "Periodisme", 3);
        Alumne gemmaSalas = new Alumne("Gemma Salas", "gemmasalas@urv.cat", new Data(16, 5, 2021), "Enginyeria de Materials", 1);
        Alumne ramonBosch = new Alumne("Ramon Bosch", "ramonbosch@urv.cat", new Data(29, 7, 2020), "Educació Social", 2);
        Alumne carlaPrats = new Alumne("Carla Prats", "carlaprats@urv.cat", new Data(21, 4, 2018), "Enginyeria Ambiental", 1);
        Alumne arnauMora = new Alumne("Arnau Mora", "arnaumora@urv.cat", new Data(11, 11, 2019), "Psicologia", 3);
        
        // Agregar instancias de Alumne a la lista
        llistaMembres.afegir(joanPerez);
        llistaMembres.afegir(mariaGarcia);
        llistaMembres.afegir(carlesMartinez);
        llistaMembres.afegir(lauraRodriguez);
        llistaMembres.afegir(annaLopez);
        llistaMembres.afegir(pereVila);
        llistaMembres.afegir(martaSoler);
        llistaMembres.afegir(jordiFont);
        llistaMembres.afegir(nuriaRoca);
        llistaMembres.afegir(albertFerrer);
        llistaMembres.afegir(sergiTorres);
        llistaMembres.afegir(claraFerran);
        llistaMembres.afegir(paulaVidal);
        llistaMembres.afegir(oriolRiera);
        llistaMembres.afegir(helenaMarti);
        llistaMembres.afegir(marcSerra);
        llistaMembres.afegir(gemmaSalas);
        llistaMembres.afegir(ramonBosch);
        llistaMembres.afegir(carlaPrats);
        llistaMembres.afegir(arnauMora);

        // Crear instancias de Professor
        Professor lluisFernandez = new Professor("Lluís Fernández", "lluisfernandez@urv.cat", new Data(18, 2, 2016), "DEIM", 201);
        Professor martaSanchez = new Professor("Marta Sánchez", "martasanchez@urv.cat", new Data(9, 10, 2015), "DEEEA", 202);
        Professor josepRamirez = new Professor("Josep Ramírez", "josepramirez@urv.cat", new Data(5, 12, 2019), "DEIM", 203);
        Professor elenaRuiz = new Professor("Elena Ruiz", "elenaruiz@urv.cat", new Data(14, 7, 2018), "DEEEA", 204);
        Professor ferranGomez = new Professor("Ferran Gómez", "ferrangomez@urv.cat", new Data(25, 5, 2017), "DEIM", 205);
        Professor jordiSerrano = new Professor("Jordi Serrano", "jordiserrano@urv.cat", new Data(10, 6, 2020), "DEIM", 206);
        Professor lauraPons = new Professor("Laura Pons", "laurapons@urv.cat", new Data(22, 3, 2019), "DEEEA", 207);
        Professor antoniVidal = new Professor("Antoni Vidal", "antonividal@urv.cat", new Data(30, 8, 2021), "DEIM", 208);
        Professor cristinaPerez = new Professor("Cristina Pérez", "cristinaperez@urv.cat", new Data(17, 1, 2018), "DEEEA", 209);
        Professor pauFerran = new Professor("Pau Ferran", "pauferran@urv.cat", new Data(20, 4, 2017), "DEIM", 210);

        // Agregar instancias de Professor a la lista
        llistaMembres.afegir(lluisFernandez);
        llistaMembres.afegir(martaSanchez);
        llistaMembres.afegir(josepRamirez);
        llistaMembres.afegir(elenaRuiz);
        llistaMembres.afegir(ferranGomez);
        llistaMembres.afegir(jordiSerrano);
        llistaMembres.afegir(lauraPons);
        llistaMembres.afegir(antoniVidal);
        llistaMembres.afegir(cristinaPerez);
        llistaMembres.afegir(pauFerran);

        Associacio pataquers = new Associacio("Pataquers", "pataquers@urv.cat");
        llistaAssociacions.afegir(pataquers);
        for (int i = 0; i < llistaMembres.mida(); i++) {
        llistaAssociacions.consultar(0).afegirMembre(llistaMembres.consultar(i));
        }

        Alumne roger = new Alumne("Roger", "rogergarciado@urv.cat", new Data(21, 9, 2022), "GEI", 2);
        llistaMembres.afegir(roger);

        // Instancia 1
        Membre[] ponents1 = new Membre[3];
        ponents1[0] = lauraPons;
        ponents1[1] = ferranGomez;
        ponents1[2] = jordiSerrano;
        llistaAccions.afegir(new Xerrada(pataquers, "Festa de l'esport", llistaMembres.consultar(6), new Data(17, 1, 2018), ponents1, 210));
        
        // Instancia 2
        Membre[] ponents2 = new Membre[2];
        ponents2[0] = lauraPons;
        ponents2[1] = josepRamirez;
        llistaAccions.afegir(new Xerrada(pataquers, "Historia dels castells", llistaMembres.consultar(10), new Data(5, 5, 2023), ponents2, 150));

        // Instancia 3
        Membre[] ponents3 = new Membre[4];
        ponents3[0] = claraFerran;
        ponents3[1] = ramonBosch;
        ponents3[2] = annaLopez;
        ponents3[3] = albertFerrer;
        llistaAccions.afegir(new Xerrada(pataquers, "Busquem nous integrants", llistaMembres.consultar(2), new Data(12, 3, 2022), ponents3, 300));
        
        // Instancia 4
        Membre[] ponents4 = new Membre[3];
        ponents4[0] = cristinaPerez;
        ponents4[1] = pauFerran;
        ponents4[2] = oriolRiera;
        llistaAccions.afegir(new Xerrada(pataquers, "Seguretat als castells", llistaMembres.consultar(14), new Data(20, 11, 2021), ponents4, 180));
        
        // Instancia 5
        Membre[] ponents5 = new Membre[1];
        ponents5[0] = antoniVidal;
        llistaAccions.afegir(new Xerrada(pataquers, "La física dels castells", llistaMembres.consultar(12), new Data(9, 8, 2020), ponents5, 120));
                
                // Ejemplo 1
                llistaAccions.afegir(new Demostracio(pataquers, "Com es fan castells?", pauFerran, new Data(20, 11, 2021), false, 5, 1500.3));
                
                // Ejemplo 2
                llistaAccions.afegir(new Demostracio(pataquers, "Prova a fer-ne", joanPerez, new Data(15, 6, 2022), true, 10, 3000.5));
                
                // Ejemplo 3
                llistaAccions.afegir(new Demostracio(pataquers, "Juga a fer castells", mariaGarcia, new Data(7, 3, 2023), true, 7, 1250.75));
                
                // Ejemplo 4
                llistaAccions.afegir(new Demostracio(pataquers, "Fisiques", carlesMartinez, new Data(21, 9, 2024), false, 3, 500.25));
                
                // Ejemplo 5
                llistaAccions.afegir(new Demostracio(pataquers, "Cultura!", lauraRodriguez, new Data(12, 4, 2025), true, 8, 2000.0));
                

        

        //Opciones del menu:

        int opcion = 0;
        boolean exit = false;
        Scanner op = new Scanner(System.in);
        String texto = " ";
        int numero;

        do {

            opcion = Integer.parseInt(op.nextLine());

            switch (opcion) {
                case 1:
                    // Acción para el caso 1
                    break;
            
                case 2:
                
                
                System.out.println("Indica la Associació que vols consultar: ");
                texto = op.nextLine().trim(); // Normaliza el texto ingresado
                numero = llistaAssociacions.buscarNumeroAssociacio(texto);
                
                if (numero == -1) {
                    System.out.println("Associació no trobada. Revisa el nom introduït.");
                } else {
                    Associacio associacio = llistaAssociacions.consultar(numero);
                    System.out.println("1. Professors 2. Alumnes 3. Ambdós");
                    opcion = Integer.parseInt(op.nextLine());
                
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
                    break;
            
                case 3:
                    // Acción para el caso 3
                    break;
            
                case 4:
                   
                    System.out.println("1. Xerrada 2. Demostracio 3. Ambdós");
                    opcion = Integer.parseInt(op.nextLine());
                
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

                    break;
            
                case 5:
                    // Acción para el caso 5
                    break;
            
                case 6:

                    System.out.println("Introdueix els rangs de cerca...");
                    int dia_i, mes_i, any_i, dia_f, mes_f, any_f;
                    System.out.println("Introdueixi el dia d'inici: ");
                    dia_i = Integer.parseInt(op.nextLine());
                    System.out.println("\nIntrodueixi el mes d'inici: ");
                    mes_i = Integer.parseInt(op.nextLine());
                    System.out.println("\nIntrodueixi l'any d'inici: ");
                    any_i = Integer.parseInt(op.nextLine());
                    Data inici = new Data(dia_i, mes_i, any_i);

                    System.out.println("Introdueixi el dia de final: ");
                    dia_f = Integer.parseInt(op.nextLine());
                    System.out.println("\nIntrodueixi el mes de final: ");
                    mes_f = Integer.parseInt(op.nextLine());
                    System.out.println("\nIntrodueixi l'any de final: ");
                    any_f = Integer.parseInt(op.nextLine());
                    Data fi = new Data(dia_f, mes_f, any_f);

                    for (int i = 0; i < llistaAccions.mida(); i++) {
                        if (llistaAccions.consultar(i).obtenirTipus().equals("Xerrada") && ((Xerrada)(llistaAccions.consultar(i))).getFecha().esAnterior(fi) && inici.esAnterior(((Xerrada)(llistaAccions.consultar(i))).getFecha())) {
                            System.out.println("\n\t"+llistaAccions.consultar(i).toString());
                        }
                    }

                    break;
            
                case 7:
                    // Acción para el caso 7
                    break;
            
                case 8:
                    
                System.out.println("Indica la Associació: ");
                texto = op.nextLine().trim(); // Normaliza el texto ingresado
                numero = llistaAssociacions.buscarNumeroAssociacio(texto);
                
                if (numero == -1) {
                    System.out.println("Associació no trobada. Revisa el nom introduït.");
                } else {
                
                    System.out.println("Introdueixi el nom del nou membre: ");
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
                                System.out.println("El membre ja està associat a la associació.");
                            }else {
                                System.out.println("El membre no està associat a la associació així que ara s'afegira.");
                                llistaAssociacions.consultar(numero).afegirMembre(llistaMembres.consultar(i));
                            }


                    }else{

                        System.out.println("El membre no està associat a cap associació");
                        System.out.println("Indica primer si es 1. Alumne o 2. Professor");
                        opcion = Integer.parseInt(op.nextLine());
                        if (opcion == 1){
                            
                            System.out.println("Alias: " + texto);
                            System.out.println("\nEmail: ");
                            String email = op.nextLine().trim();
                            System.out.println("\nIntrodueix la data d'alta: ");
                            int dia, mes, any;
                            System.out.println("\nDia: ");
                            dia = Integer.parseInt(op.nextLine());
                            System.out.println("\nMes: ");
                            mes = Integer.parseInt(op.nextLine());
                            System.out.println("\nAny: ");
                            any = Integer.parseInt(op.nextLine());
                            Data alta = new Data(dia, mes, any);
                            System.out.println("\nEnsenyament: ");
                            String estudis = op.nextLine().trim();
                            System.out.println("\nAnys a l'ETSE: ");
                            int anys = Integer.parseInt(op.nextLine().trim());
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
                            dia = Integer.parseInt(op.nextLine());
                            System.out.println("\nMes: ");
                            mes = Integer.parseInt(op.nextLine());
                            System.out.println("\nAny: ");
                            any = Integer.parseInt(op.nextLine());
                            Data alta = new Data(dia, mes, any);
                            System.out.println("\nDepartament: ");
                            String departament = op.nextLine().trim();
                            System.out.println("\nDespatx: ");
                            int despatx = Integer.parseInt(op.nextLine().trim());
                            Professor nouProfessor = new Professor(texto, email, alta, departament, despatx);
                            llistaMembres.afegir(nouProfessor);
                            llistaAssociacions.consultar(numero).afegirMembre(nouProfessor);

                        }




                    }

                }


                    break;
            
                case 9:
                    // Acción para el caso 9
                    break;
            
                case 10:

                System.out.println("Indica la Associació organitzadora de la nova Demostració: ");
                texto = op.nextLine().trim(); // Normaliza el texto ingresado
                numero = llistaAssociacions.buscarNumeroAssociacio(texto);
                
                if (numero == -1) {
                    System.out.println("Associació no trobada. Revisa el nom introduït.");
                } else {

                    System.out.println("\nTítol de la nova Demostració: ");
                    String titol = op.nextLine().trim();
                    System.out.println("\nNom del membre responsable: ");
                    String nom = op.nextLine().trim();
                    Membre nouMembre;

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
                        opcion = Integer.parseInt(op.nextLine());
                        if (opcion == 1){
                            
                            System.out.println("Alias: " + nom);
                            System.out.println("\nEmail: ");
                            String email = op.nextLine().trim();
                            System.out.println("\nIntrodueix la data d'alta: ");
                            int dia, mes, any;
                            System.out.println("\nDia: ");
                            dia = Integer.parseInt(op.nextLine());
                            System.out.println("\nMes: ");
                            mes = Integer.parseInt(op.nextLine());
                            System.out.println("\nAny: ");
                            any = Integer.parseInt(op.nextLine());
                            Data alta = new Data(dia, mes, any);
                            System.out.println("\nEnsenyament: ");
                            String estudis = op.nextLine().trim();
                            System.out.println("\nAnys a l'ETSE: ");
                            int anys = Integer.parseInt(op.nextLine().trim());
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
                            dia = Integer.parseInt(op.nextLine());
                            System.out.println("\nMes: ");
                            mes = Integer.parseInt(op.nextLine());
                            System.out.println("\nAny: ");
                            any = Integer.parseInt(op.nextLine());
                            Data alta = new Data(dia, mes, any);
                            System.out.println("\nDepartament: ");
                            String departament = op.nextLine().trim();
                            System.out.println("\nDespatx: ");
                            int despatx = Integer.parseInt(op.nextLine().trim());
                            Professor nouProfessor = new Professor(texto, email, alta, departament, despatx);
                            llistaMembres.afegir(nouProfessor);
                            i = llistaMembres.mida();
                            i--;

                        }




                    }


                    System.out.println("\nIntrodueix la data de disseny: ");
                    int dia, mes, any;
                    System.out.println("\nDia: ");
                    dia = Integer.parseInt(op.nextLine());
                    System.out.println("\nMes: ");
                    mes = Integer.parseInt(op.nextLine());
                    System.out.println("\nAny: ");
                    any = Integer.parseInt(op.nextLine());
                    Data dataDisseny = new Data(dia, mes, any);
                    boolean actiu = false;
                    System.out.println("\nLa demostració està activa? 1. (si) 2. (no)");
                    if(Integer.parseInt(op.nextLine()) == 1){
                        actiu = true;
                    }
                    System.out.println("\nVeguades que es farà: ");
                    int vegades = Integer.parseInt(op.nextLine().trim());
                    System.out.println("\nCost de tot el material: ");
                    double costMaterials = Double.parseDouble(op.nextLine().trim());

                    llistaAccions.afegir(new Demostracio(llistaAssociacions.consultar(numero), titol, llistaMembres.consultar(i--), dataDisseny, actiu, vegades, costMaterials));
                    System.out.println("Nova Demostracio creada!");

                }

                    break;
            
                case 11:

                    // Acción para el caso 11
                    break;
            
                case 12:
                    
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



                    break;
            
                case 13:
                    // Acción para el caso 13
                    break;
            
                case 14:
                    System.out.println("Indica el nom de la Xarrada que vols valorar: ");
                    String nom = op.nextLine().trim();
                    for (int i = 0; i< llistaAccions.mida(); i++){
                        if (llistaAccions.consultar(i).obtenirTipus().equals("Xerrada")){
                            if (llistaAccions.consultar(i).getTitulo().equals(nom)){
                                System.out.println("Introdueix la valoració de la xarrada: ");
                                int valoracio = Integer.parseInt(op.nextLine().trim());
                                ((Xerrada)llistaAccions.consultar(i)).agregarValoracion(valoracio);
                                System.out.println("Valoració afegida correctament!");
                            }
                        }
                    }

                    
                    break;
            
                case 15:
                    // Acción para el caso 15
                    break;

                case 16:

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

                
                    break;
            
                case 17:
                    // Acción para el caso 17
                    break;
            
                case 18:
                    exit = true;
                    break;
            
                default:
                    exit = true;
                    break;
            }
            
    }while (exit != true);

        System.out.println("\n=== FI DELS TESTS ===");
    }
}
