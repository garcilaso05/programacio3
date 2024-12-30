package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import accions.Accions;
import accions.Demostracio;
import accions.LlistaAccions;
import accions.Xerrada;
import associacions.Associacio;
import associacions.LlistaAssociacions;
import membres.Alumne;
import membres.Data;
import membres.LlistaMembres;
import membres.Membre;
import membres.Professor;

public class UtilsConsola {

    public void guardarAssociacions(String fileName, LlistaAssociacions llista) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Iterar sobre la lista de asociaciones y escribir en el archivo
            for (Associacio associacio : llista.getAllAssociacions()) {

                StringBuilder aliases = new StringBuilder();
                for (int i = 0; i < associacio.getNumMembers(); i++) {
                    Membre membre = associacio.getLlistaMembre().consultar(i);
                    aliases.append(membre.getAlias());
                    if (i < associacio.getNumMembers() - 1) {
                        aliases.append(",");
                    }
                }

                StringBuilder tituls = new StringBuilder();
                for (int i = 0; i < associacio.getNumTitulacions(); i++) {
                    if (associacio.getTitulacions()[i] != null) {
                        tituls.append(associacio.getTitulacions()[i]);
                        if (i < associacio.getNumTitulacions() - 1) {
                            tituls.append(",");
                        }
                    }
                }

                // Manejo de posibles null en los roles
                String presidentAlias = associacio.getPresident() != null ? associacio.getPresident().getAlias() : "No assignat";
                String secretariAlias = associacio.getSecretari() != null ? associacio.getSecretari().getAlias() : "No assignat";
                String tresorerAlias = associacio.getTresorer() != null ? associacio.getTresorer().getAlias() : "No assignat";

                writer.write(associacio.getNom()
                    + ";" + associacio.getCorreuElectronic()
                    + ";" + tituls.toString()
                    // + ";" + aliases.toString() // Opcional: escribir la lista de miembros
                    + ";" + presidentAlias
                    + ";" + secretariAlias
                    + ";" + tresorerAlias);
                writer.newLine(); // Salto de línea después de cada asociación
            }
        }
    }

    public void carregarAssociacions(String fileName,LlistaAssociacions llista, LlistaMembres llistaMembres) throws IOException {
        
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        
        while ((line = reader.readLine()) != null) {
            
            String[] parts = line.split(";");
            if (parts.length > 0) {
                // Extraer información de la asociación
                String nom = parts[0];
                String correuElectronic = parts[1];
                String[] titulacions = parts[2].split(",");
                //String[] aliases = parts[3].split(",");
                String presidentAlias = parts[3];
                String secretariAlias = parts[4];
                String tresorerAlias = parts[5];

                // Crear la asociación
                Associacio associacio = new Associacio(nom, correuElectronic);
                // Añadir titulacions
                for (String titulacio : titulacions) {
                    if (!titulacio.isEmpty()) {
                        associacio.afegirTitulacio(titulacio);
                    }
                }

                // Crear y añadir membres (puedes extender esto para manejar diferentes tipos de membres)
                for (int i = 0; i < llistaMembres.mida(); i++) {
                    for (int j = 0; j < llistaMembres.consultar(i).getNumAssociacions(); j++){
                        if (llistaMembres.consultar(i).getAssociacions()[j].equals(nom)) {
                            String alias = llistaMembres.consultar(i).getAlias();
                            Membre membre = llistaMembres.consultar(i); // Reemplaza por la lógica real para crear membres
                            associacio.afegirMembre(membre);
    
                            // Asignar cargos si el alias coincide
                            if (alias.equals(presidentAlias)) {
                                associacio.assignarPresident(membre);
                            }
                            if (alias.equals(secretariAlias)) {
                                associacio.assignarSecretari(membre);
                            }
                            if (alias.equals(tresorerAlias)) {
                                associacio.assignarTresorer(membre);
                            }
                        }
                    }
                }

                // Añadir la asociación a la lista
                llista.afegir(associacio);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + fileName);
        }
    }

    public void guardarMembres(String fileName, LlistaMembres llista) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < llista.mida(); i++) {
                Membre membre = llista.consultar(i);
                writer.write(convertirMembersLinea(membre));               
                writer.newLine(); // Añade un salto de línea después de cada membre
            }
        }
    }

    public void carregarMembres(String fileName, LlistaMembres llista) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                
                // Parse common member details
                String tipus = parts[0];
                String alias = parts[1];
                String email = parts[2];
                Data dataAlta = new Data(
                    Integer.parseInt(parts[3].split("/")[0]),
                    Integer.parseInt(parts[3].split("/")[1]),
                    Integer.parseInt(parts[3].split("/")[2])
                );
                Data dataBaixa = parts[4].equals("Actiu") ? null : new Data(
                    Integer.parseInt(parts[4].split("/")[0]),
                    Integer.parseInt(parts[4].split("/")[1]),
                    Integer.parseInt(parts[4].split("/")[2])
                );
                String[] associacions = parts[5].split(",");
    
                if (tipus.equals("Alumne")) {
                    // Parse Alumne-specific details
                    String ensenyament = parts[6];
                    int anysETSE = Integer.parseInt(parts[7]);
                    boolean isGraduat = Boolean.parseBoolean(parts[8]);
                    Alumne alumne = new Alumne(alias, email, dataAlta, ensenyament, anysETSE);
                    if(isGraduat == true) alumne.graduar();
                    if (dataBaixa != null) {
                        alumne.donarDeBaixa(dataBaixa);
                    }
                    for (String associacio : associacions) {
                        if (!associacio.equals("null")) {
                            alumne.afegirAssociacio(associacio);
                        }
                    }
                    llista.afegir(alumne);
                } else if (tipus.equals("Professor")) {
                    // Parse Professor-specific details
                    String departament = parts[6];
                    int despatx = Integer.parseInt(parts[7]);
                    Professor professor = new Professor(alias, email, dataAlta, departament, despatx);
                    if (dataBaixa != null) {
                        professor.donarDeBaixa(dataBaixa);
                    }
                    for (String associacio : associacions) {
                        if (!associacio.equals("null")) {
                            professor.afegirAssociacio(associacio);
                        }
                    }
                    llista.afegir(professor);
                }
            }
        }
    }

    public void guardarAccions(String fileName, LlistaAccions llista) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < llista.mida(); i++) {
                Accions accio = llista.consultar(i);
                writer.write(convertirAccionLinea(accio));
                writer.newLine();
            }
        }
    }  
 
    public void carregarAccions(String fileName, LlistaAccions llista, LlistaAssociacions llistaAssociacions, LlistaMembres llistaMembres) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String tipus = parts[0]; // "Xerrada" o "Demostracio"
                String codigo = parts[1];
                String titulo = parts[2];
                String associacio = parts[3];
                String responsableAlias = parts[4];
                
                // Buscar al responsable en la lista de miembros
                Membre responsable = null;
                for (int i = 0; i < llistaMembres.mida(); i++) {
                    if (llistaMembres.consultar(i).getAlias().equals(responsableAlias)) {
                        responsable = llistaMembres.consultar(i);
                        break;
                    }
                }
                if (responsable == null) {
                    throw new IllegalArgumentException("Responsable no encontrado: " + responsableAlias);
                }
    
                if (tipus.equals("Xerrada")) {
                    Data fecha = new Data(
                        Integer.parseInt(parts[5].split("/")[0]),
                        Integer.parseInt(parts[5].split("/")[1]),
                        Integer.parseInt(parts[5].split("/")[2])
                    );
                    String[] ponentesAlias = parts[6].split(",");
                    Membre[] ponentes = new Membre[ponentesAlias.length];
                    for (int i = 0; i < ponentesAlias.length; i++) {
                        for (int j = 0; j < llistaMembres.mida(); j++) {
                            if (llistaMembres.consultar(j).getAlias().equals(ponentesAlias[i])) {
                                ponentes[i] = llistaMembres.consultar(j);
                                break;
                            }
                        }
                    }
                    int asistentes = Integer.parseInt(parts[7]);

                    int[] valoraciones = new int[0]; // Par défaut à un tableau vide
                    if (parts.length > 8 && !parts[8].isEmpty()) {
                        String[] valoracionesStr = parts[8].split(",");
                        valoraciones = new int[valoracionesStr.length];
                        for (int i = 0; i < valoracionesStr.length; i++) {
                            valoraciones[i] = Integer.parseInt(valoracionesStr[i]);
                        }
                    }
                    Xerrada xerrada = new Xerrada(llistaAssociacions.consultar(llistaAssociacions.buscarNumeroAssociacio(associacio)), titulo, responsable, fecha, ponentes, asistentes);
                    for (int valoracion : valoraciones) {
                        xerrada.agregarValoracion(valoracion);
                    }
                    xerrada.setCodigo(codigo);
                    llista.afegir(xerrada);
    
                } else if (tipus.equals("Demostracio")) {
                    Data dataDisseny = new Data(
                        Integer.parseInt(parts[5].split("/")[0]),
                        Integer.parseInt(parts[5].split("/")[1]),
                        Integer.parseInt(parts[5].split("/")[2])
                    );
                    boolean activa = Boolean.parseBoolean(parts[6]);
                    int vegades = Integer.parseInt(parts[7]);
                    double costMaterials = Double.parseDouble(parts[8]);
                    Demostracio demostracio = new Demostracio(llistaAssociacions.consultar(llistaAssociacions.buscarNumeroAssociacio(associacio)), titulo, responsable, dataDisseny, activa, vegades, costMaterials);
                    demostracio.setCodigo(codigo);
                    llista.afegir(demostracio);
                }
            }
        } catch (FileNotFoundException e) {
            // Archivo no existe, iniciar vacío
        }
    }

    public String convertirAccionLinea(Accions accio){
        String linea = "";
        if(accio.obtenirTipus().equals("Xerrada")){
            Xerrada xerrada = (Xerrada) accio;

            StringBuilder val = new StringBuilder();
            for (int j = 0; j < xerrada.getNumValoraciones(); j++) {
                val.append("" + xerrada.getValoraciones()[j]);
                if(j < xerrada.getNumValoraciones()-1) val.append(",");
            }
                
            linea = xerrada.obtenirTipus()
            + ";" + xerrada.getCodigo() 
            + ";" + xerrada.getTitulo()
            + ";" + xerrada.getAssociacio()
            + ";" + xerrada.getResponsable().getAlias()
            + ";" + xerrada.getFecha()
            + ";" + xerrada.obtenerPonente()
            + ";" + xerrada.getAsistentes()
            + ";" +  val;

        }else if(accio.obtenirTipus().equals("Demostracio")){
            Demostracio demostracio = (Demostracio) accio;
            linea = demostracio.obtenirTipus()
            + ";" + demostracio.getCodigo() 
            + ";" + demostracio.getTitulo()
            + ";" + demostracio.getAssociacio()
            + ";" + demostracio.getResponsable().getAlias()
            + ";" + demostracio.getDataDisseny().toString()
            + ";" + demostracio.isActiva()
            + ";" + demostracio.getVegades()
            + ";" + demostracio.getcostMaterials();

        }

        return linea;

    }

    public String convertirMembersLinea(Membre membre){
        String linea = "";
        if(membre.obtenirTipus() == "Alumne"){
            Alumne alumne = (Alumne) membre; 
            linea = alumne.obtenirTipus()
            + ";" + alumne.getAlias()
            + ";" + alumne.getEmailInstitucional()
            + ";" + alumne.getDataAlta().toString()
            + ";" + (alumne.getDataBaixa() != null ? alumne.getDataBaixa().toString() : "Actiu")
            + ";" + String.join(",", alumne.getAssociacions())
            + ";" + alumne.getEnsenyament()
            + ";" + alumne.getAnysETSE()
            + ";" + alumne.isGraduat();
        } else if (membre.obtenirTipus() == "Professor"){
            Professor professor = (Professor) membre;
            linea = professor.obtenirTipus()
            + ";" + professor.getAlias()
            + ";" + professor.getEmailInstitucional()
            + ";" + professor.getDataAlta().toString()
            + ";" + (professor.getDataBaixa() != null ? professor.getDataBaixa().toString() : "Actiu")
            + ";" + String.join(",", professor.getAssociacions())
            + ";" + professor.getDepartament()
            + ";" + professor.getDespatx();
        } 
        return linea;

    }



}
