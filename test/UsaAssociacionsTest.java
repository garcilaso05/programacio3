import associacions.*;
import membres.Alumne;
import membres.Professor;
import membres.Membre;
import membres.Data;
import membres.LlistaMembres;

import accions.*;

/**
 * Classe principal per provar el funcionament de totes les classes del
 * projecte.
 */
public class UsaAssociacionsTest {
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

   
        System.out.println("\n=== FI DELS TESTS ===");
    }
}
