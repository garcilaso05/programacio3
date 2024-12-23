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

        String archivoAssociacions = "LlistaAssociacions.dat";          // Serializado
        String archivoAccions = "LlistaAccions.txt";                            // Texto
        String archivoMembres = "LlistaMembres.txt";                    // Texto

        LlistaAssociacions llistaAssociacions = new LlistaAssociacions(10);
        LlistaAccions llistaAccions = new LlistaAccions(100);   // Capacidad inicial
        LlistaMembres llistaMembres = new LlistaMembres(100);   // Capacidad inicial

        llistaMembres.afegir(new Alumne("Joan Pérez", "joanperez@urv.cat", new Data(15, 6, 2020), "Enginyeria Informàtica", 2));
        llistaMembres.afegir(new Alumne("Maria García", "mariagarcia@urv.cat", new Data(7, 3, 2018), "Matemàtiques", 1));
        llistaMembres.afegir(new Alumne("Carles Martínez", "carlesmartinez@urv.cat", new Data(21, 9, 2021), "Física", 3));
        llistaMembres.afegir(new Alumne("Laura Rodríguez", "laurarodriguez@urv.cat", new Data(12, 4, 2019), "Química", 4));
        llistaMembres.afegir(new Alumne("Anna López", "annalopez@urv.cat", new Data(30, 11, 2017), "Enginyeria Civil", 2));
        llistaMembres.afegir(new Alumne("Pere Vila", "perevila@urv.cat", new Data(11, 1, 2020), "Biologia", 1));
        llistaMembres.afegir(new Alumne("Marta Soler", "martasoler@urv.cat", new Data(22, 5, 2018), "Història", 2));
        llistaMembres.afegir(new Alumne("Jordi Font", "jordifont@urv.cat", new Data(13, 7, 2021), "Enginyeria Mecànica", 3));
        llistaMembres.afegir(new Alumne("Núria Roca", "nuriaroca@urv.cat", new Data(18, 2, 2019), "Arquitectura", 1));
        llistaMembres.afegir(new Alumne("Albert Ferrer", "albertferrer@urv.cat", new Data(5, 12, 2017), "Ciències Polítiques", 4));
        llistaMembres.afegir(new Alumne("Sergi Torres", "sergitorres@urv.cat", new Data(22, 3, 2021), "Medicina", 2));
        llistaMembres.afegir(new Alumne("Clara Ferran", "claraferran@urv.cat", new Data(14, 9, 2019), "Enginyeria Electrònica", 3));
        llistaMembres.afegir(new Alumne("Paula Vidal", "paulavidal@urv.cat", new Data(8, 6, 2018), "Química Industrial", 1));
        llistaMembres.afegir(new Alumne("Oriol Riera", "oriolriera@urv.cat", new Data(3, 2, 2020), "Dret", 4));
        llistaMembres.afegir(new Alumne("Helena Martí", "helenamarti@urv.cat", new Data(10, 10, 2017), "Filologia Catalana", 2));
        llistaMembres.afegir(new Alumne("Marc Serra", "marcserra@urv.cat", new Data(27, 8, 2019), "Periodisme", 3));
        llistaMembres.afegir(new Alumne("Gemma Salas", "gemmasalas@urv.cat", new Data(16, 5, 2021), "Enginyeria de Materials", 1));
        llistaMembres.afegir(new Alumne("Ramon Bosch", "ramonbosch@urv.cat", new Data(29, 7, 2020), "Educació Social", 2));
        llistaMembres.afegir(new Alumne("Carla Prats", "carlaprats@urv.cat", new Data(21, 4, 2018), "Enginyeria Ambiental", 1));
        llistaMembres.afegir(new Alumne("Arnau Mora", "arnaumora@urv.cat", new Data(11, 11, 2019), "Psicologia", 3));
        
        llistaMembres.afegir(new Professor("Lluís Fernández", "lluisfernandez@urv.cat", new Data(18, 2, 2016), "DEIM", 201));
        llistaMembres.afegir(new Professor("Marta Sánchez", "martasanchez@urv.cat", new Data(9, 10, 2015), "DEEEA", 202));
        llistaMembres.afegir(new Professor("Josep Ramírez", "josepramirez@urv.cat", new Data(5, 12, 2019), "DEIM", 203));
        llistaMembres.afegir(new Professor("Elena Ruiz", "elenaruiz@urv.cat", new Data(14, 7, 2018), "DEEEA", 204));
        llistaMembres.afegir(new Professor("Ferran Gómez", "ferrangomez@urv.cat", new Data(25, 5, 2017), "DEIM", 205));
        llistaMembres.afegir(new Professor("Jordi Serrano", "jordiserrano@urv.cat", new Data(10, 6, 2020), "DEIM", 206));
        llistaMembres.afegir(new Professor("Laura Pons", "laurapons@urv.cat", new Data(22, 3, 2019), "DEEEA", 207));
        llistaMembres.afegir(new Professor("Antoni Vidal", "antonividal@urv.cat", new Data(30, 8, 2021), "DEIM", 208));
        llistaMembres.afegir(new Professor("Cristina Pérez", "cristinaperez@urv.cat", new Data(17, 1, 2018), "DEEEA", 209));
        llistaMembres.afegir(new Professor("Pau Ferran", "pauferran@urv.cat", new Data(20, 4, 2017), "DEIM", 210));
        
        Associacio pataquers = new Associacio("Pataquers", "pataquers@urv.cat");
        llistaAssociacions.afegir(pataquers);
        for (int i = 0; i < llistaMembres.mida(); i++) {
        pataquers.afegirMembre(llistaMembres.consultar(i));      
        }
        // Cargar LlistaAssociacions (datos serializados)

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoAssociacions))) {
            llistaAssociacions = (LlistaAssociacions) ois.readObject();
            System.out.println("LlistaAssociacions cargada correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de LlistaAssociacions no encontrado. Se creará uno nuevo.");
            llistaAssociacions = new LlistaAssociacions(150); // Nueva lista vacía
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar LlistaAssociacions: " + e.getMessage());
            llistaAssociacions = new LlistaAssociacions(150); // Nueva lista vacía
        }

        // Cargar LlistaAccions (archivo de texto)

        try (BufferedReader br = new BufferedReader(new FileReader(archivoAccions))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                // Ejemplo de creación: tipo de acción diferenciado en partes[0]
                if (partes[0].equals("Demostracio")) {
                    llistaAccions.afegir(new Demostracio(null, partes[1], null, new Data(Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4])), Boolean.parseBoolean(partes[5]), Integer.parseInt(partes[6]), Double.parseDouble(partes[7])));
                } else if (partes[0].equals("Xerrada")) {
                    llistaAccions.afegir(new Xerrada(null, partes[1], null, new Data(Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4])), null, Integer.parseInt(partes[5])));
                }
            }
            System.out.println("LlistaAccions cargada correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de LlistaAccions no encontrado. Se creará uno nuevo.");
        } catch (IOException e) {
            System.out.println("Error al cargar LlistaAccions: " + e.getMessage());
        }

        // Cargar LlistaMembres (archivo de texto)

        try (BufferedReader br = new BufferedReader(new FileReader(archivoMembres))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes[0].equals("Professor")) {
                    llistaMembres.afegir(new Professor(
                            partes[1], partes[2], new Data(Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), Integer.parseInt(partes[5])), partes[6], Integer.parseInt(partes[7])));
                } else if (partes[0].equals("Alumne")) {
                    llistaMembres.afegir(new Alumne( partes[1], partes[2], new Data(Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), Integer.parseInt(partes[5])), partes[6], Integer.parseInt(partes[7]))); } } System.out.println("LlistaMembres cargada correctamente."); } catch (FileNotFoundException e) { System.out.println("Archivo de LlistaMembres no encontrado. Se creará uno nuevo."); } catch (IOException e) { System.out.println("Error al cargar LlistaMembres: " + e.getMessage()); }

        System.out.println("Cargando datos...");

        //Opciones del menu:

        int opcion = 0;
        boolean exit = false;
        Scanner op = new Scanner(System.in);
        String texto = " ";

        do {

            opcion = Integer.parseInt(op.nextLine());

            switch (opcion) {
                case 1:
                    // Acción para el caso 1
                    break;
            
                case 2:
                // No funciona bien del todo aun, revisar
                
                    System.out.println("Indica la Associació que vols consultar: ");
                    texto = op.nextLine();
                    int numero = llistaAssociacions.buscarNumeroAssociacio(texto);
                    System.out.println("1. Professors 2. Alumnes 3. Ambdos");
                    opcion = Integer.parseInt(op.nextLine());
                    switch (opcion) {
                        case 1:
                        for (int i = 0; i < (llistaAssociacions.consultar(numero--)).getLlistaMembre().mida(); i++) {
                            if((((llistaAssociacions.consultar(numero--)).getLlistaMembre()).consultar(i)).obtenirTipus().equals("Professor"))
                            System.out.println("Alumne: " + (((llistaAssociacions.consultar(numero--)).getLlistaMembre()).consultar(i)).getAlias());
                        }
                        break;

                        case 2:
                        for (int i = 0; i < (llistaAssociacions.consultar(numero)).getLlistaMembre().mida(); i++) {
                            if((((llistaAssociacions.consultar(numero)).getLlistaMembre()).consultar(i)).obtenirTipus().equals("Alumne"))
                            System.out.println("Alumne: " + (((llistaAssociacions.consultar(numero)).getLlistaMembre()).consultar(i)).getAlias());
                        }
                        break;

                        case 3:
                        for (int i = 0; i < (llistaAssociacions.consultar(numero)).getLlistaMembre().getNumMembres(); i++) {
                            System.out.println("Membre: " + (((llistaAssociacions.consultar(numero)).getLlistaMembre()).consultar(i)).getAlias());
                        }
                        break;

                    }
                    break;
            
                case 3:
                    // Acción para el caso 3
                    break;
            
                case 4:
                    // Acción para el caso 4
                    break;
            
                case 5:
                    // Acción para el caso 5
                    break;
            
                case 6:
                    // Acción para el caso 6
                    break;
            
                case 7:
                    // Acción para el caso 7
                    break;
            
                case 8:
                    // Acción para el caso 8
                    break;
            
                case 9:
                    // Acción para el caso 9
                    break;
            
                case 10:
                    // Acción para el caso 10
                    break;
            
                case 11:
                    // Acción para el caso 11
                    break;
            
                case 12:
                    // Acción para el caso 12
                    break;
            
                case 13:
                    // Acción para el caso 13
                    break;
            
                case 14:
                    // Acción para el caso 14
                    break;
            
                case 15:
                    // Acción para el caso 15
                    break;

                case 16:
                    // Acción para el caso 16
                    break;
            
                case 17:
                    // Acción para el caso 17
                    break;
            
                case 18:
                    // Acción para el caso 18
                    break;
            
                default:
                    // Acción por defecto
            }
            
    }while (exit != true);

        System.out.println("\n=== FI DELS TESTS ===");
    }
}
