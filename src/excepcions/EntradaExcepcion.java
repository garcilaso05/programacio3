package excepcions;

import java.util.Scanner;

public class EntradaExcepcion {

    public static int verificarEntradaInt(Scanner op){
        int entrada = -1;
        try {
            entrada = Integer.parseInt(op.nextLine());
        } catch (Exception e) {
            System.out.println("La entrada no és un número vàlid.");  
        }
        return entrada;
    }

    public static double verificarEntradaDouble(Scanner op) {
        double entrada = -1.0;
        try {
            entrada = Double.parseDouble(op.nextLine());
        } catch (Exception e) {
            System.out.println("La entrada no és un número vàlid.");
        }
        return entrada;
    }

    public static String verificarEntradaData(Scanner op) {
        String entrada = op.nextLine();
        String[] parts = entrada.split("/");
        

        if (parts.length != 3) {
            System.out.println("La entrada no té el format DD/MM/YYYY.");
        } else {
            try {
                int dia = Integer.parseInt(parts[0]);
                int mes = Integer.parseInt(parts[1]);
                int any = Integer.parseInt(parts[2]);
        
                if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || any < 0) {
                    System.out.println("La data conté valors fora del rang vàlid.");
                    entrada = "-1";
                }
        
            } catch (NumberFormatException e) {
                System.out.println("La data ha de contenir només números enters.");
                entrada = "-1";
            }
    
        }
        return entrada;
    } 
}
