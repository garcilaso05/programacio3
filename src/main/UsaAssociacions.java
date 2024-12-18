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

import accions.*;

/**
 * Classe principal per provar el funcionament de totes les classes del
 * projecte.
 */
public class UsaAssociacions {
    public static void main(String[] args) {

        // TEST 1
        // Crear l'associacio
        System.out.println("\n>> Creant associació");
        Associacio associacio1 = new Associacio("Pataquers", "pataquers@epp.urv.cat");
        System.out.println("Associació: " + associacio1.getNom());
        System.out.println("Correu: " + associacio1.getCorreuElectronic());

        // TEST 2
        // Afegir membres
        System.out.println("\n>> Afegint membres");
        Data prova = new Data(17, 1, 2025);
        Alumne alumne1 = new Alumne("khady", "khady@epp.urv.cat", prova, "GEI", 2);
        Alumne alumne2 = new Alumne("ivette", "ivette@epp.urv.cat", prova, "GEB", 2);
        Professor professor1 = new Professor("mallafre", "mallafre@etse.com", prova, "DEIM", 104);

        associacio1.afegirMembre(alumne1);
        associacio1.afegirMembre(alumne2);
        associacio1.afegirMembre(professor1);

        System.out.println("Membres:");
        System.out.println("> Alumn@ 1: " + alumne1.getAlias());
        System.out.println("> Alumn@ 2: " + alumne2.getAlias());
        System.out.println("> Professor/a: " + professor1.getAlias());

        // TEST 3
        // Afegir càrrecs
        System.out.println("\n>> Càrrecs a membres");

        associacio1.assignarPresident(alumne1);
        associacio1.assignarSecretari(alumne2);
        associacio1.assignarTresorer(alumne1);

        System.out.println("President/a: " + associacio1.getPresident().getAlias());
        System.out.println("Secretari/a: " + associacio1.getSecretari().getAlias());
        System.out.println("Tresorer/a: " + associacio1.getTresorer().getAlias());

        System.out.println("Info de l'associacio:\n" + associacio1.mostrarInformacio());

        System.out.println("\n\n>> Creant accions:");

        // TEST 4
        // Accions
        Alumne responsable1 = new Alumne("marc", "marc@epp.urv.cat", prova, "GEI", 3); // Otro miembro
        Alumne responsable2 = new Alumne("antoni", "antoni@epp.urv.cat", prova, "GEI", 1); // Otro miembro
        Professor ponente1 = new Professor("pedro", "pedro@etse.com", prova, "DEIM", 210);
        Professor ponente2 = new Professor("manuel", "manuel@etse.com", prova, "DEIM", 211);
        Professor ponente3 = new Professor("sara", "sara@etse.com", prova, "DEIM", 212);
        Data fechaDemostracion = new Data(4, 12, 2018); // Fecha para la demostración
        Data fechaXerrada = new Data(7, 3, 2019); // Fecha para la xerrada

        // Creación de una demostración
        Demostracio demo1 = new Demostracio(associacio1, "Demostración de Pintura", responsable1, fechaDemostracion,
                true, 3, 120.50);

        // Creación de una xerrada
        Membre[] ponentes = { ponente1, ponente2, ponente3 };
        Xerrada xerrada1 = new Xerrada(associacio1, "Xerrada sobre Arte Moderno", responsable2, fechaXerrada, ponentes,
                50);

        // Probar métodos
        System.out.println("\n=== Demostración ===");
        System.out.println(demo1);
        System.out.println("¿Está activa? " + demo1.isActiva());
        System.out.println("Costo de materiales: " + demo1.getcostMaterials());

        System.out.println("\n=== Xerrada ===");
        System.out.println(xerrada1);
        xerrada1.agregarValoracion(8);
        xerrada1.agregarValoracion(9);
        xerrada1.agregarValoracion(10);
        System.out.println("Valoración media después de 3 valoraciones: " + xerrada1.calcularValoracionMedia());
        System.out.println("Ponentes: " + xerrada1.obtenerPonente());

        //TESTS 5

        LlistaMembres membres = new LlistaMembres(5);
        membres.afegir(responsable1);
        membres.afegir(responsable2);
        membres.afegir(ponente1);
        membres.afegir(ponente2);
        membres.afegir(ponente3);

        //TESTS 6

        String archivoAssociacions = "LlistaAssociacions.dat";          // Serializado
        String archivoAccions = "LlistaAccions.txt";                            // Texto
        String archivoMembres = "LlistaMembres.txt";                    // Texto

        LlistaAssociacions llistaAssociacions = null;
        LlistaAccions llistaAccions = new LlistaAccions(100);   // Capacidad inicial
        LlistaMembres llistaMembres = new LlistaMembres(100);   // Capacidad inicial



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

        

        System.out.println("\n=== FI DELS TESTS ===");
    }
}
