package membres;
/*
 * Classe reutilitzada (amb modificacions) de la tercera sessió del laboratori 3
 * S'ha afegit més controls y s'ha practicat l'és de excepcións
 */

public class Data {

    //Definició dels atributs de la classe 
    private int dia;
    private int mes;
    private int any;

    /**
	 * Contructor principal de la classe Data
	 * 
	 * @param dia - valor del dia
     * @param mes - valor del mes
     * @param any - valor del any
     * 
     * Mitjançant mètodes de la pròpia clase es controla la data (s'avisa a l'usuari que ha comés un error)
	 */

    public Data(int dia, int mes, int any) {
        if (!esDataValida(dia, mes, any)) {
            throw new IllegalArgumentException("La data no es correcta");
        }   //Controlem amb excepcions pròpies que la data sigui correcta
        this.dia = dia;
        this.mes = mes;
        this.any = any;
    }

     /**
	 * Metode privat per fer el control de la data (correcta o no)
	 * 
	 * @param dia - valor del dia a comprovar
     * @param mes - valor del mes a comprovar
     * @param any - valor del any a comprovar
     * 
     * @return Retorna true si la data es correcta, false si la data no ho es
	 */

    private boolean esDataValida(int dia, int mes, int any) {
        if (mes < 1 || mes > 12 || dia < 1) {
            return false;
        }
        int[] diesPerMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (esAnyDeTraspas(any)) {
            diesPerMes[1] = 29;
        }
        return (dia <= diesPerMes[mes - 1]);
    }

     /**
	 * Metode privat per comprovar si es any de traspás
	 * 
     * @param any - valor del any a evaluar
     * 
     * @return Retorna true si l'any es de traspas, false si l'any no ho es
	 */

    private boolean esAnyDeTraspas(int any) {
        return (any % 4 == 0 && any % 100 != 0) || (any % 400 == 0);
    }

    /**
	 * getters dels atributs
	 * 
	 * @return dia - valor del dia
     * 
	 */

    public int getDia() {
        return dia;
    }

    /**
	 * getters dels atributs
	 * 
     * @return mes - valor del mes
     * 
	 */

    public int getMes() {
        return mes;
    }

     /**
	 * getters dels atributs
	 * 
     * @return any - valor del any
     * 
	 */

    public int getAny() {
        return any;
    }

    /**
	 * Mètode per mostrar la data
     *  
	 * @return String - text amb la data
     * 
	 */


     //Hem de possar Override per a que no hi hagin problemes al compilador en trobar-se amb mes d'un toString
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, any);
    }

    /**
	 * Mètode per evaluar si la dues dates son mes antigues entre elles
     * L'ús d'aquest mètode a Membres es per evaluar si la data d'alta es posterior a la de baixa
     * 
     * @param  Data (data a comparar)
	 * @return boolean - cert si es anterior, fals si no ho es
     * 
	 */


    public boolean esAnterior(Data altra) {
        if (this.any != altra.any) {
            return this.any < altra.any;
        }
        if (this.mes != altra.mes) {
            return this.mes < altra.mes;
        }
        return this.dia < altra.dia;
    }

    /**
	 * Mètode per evaluar si la dues dates son iguals
     * 
     * @param  Data (data a comparar)
	 * @return boolean - cert si son iguals, fals si no ho son
     * 
	 */

    public boolean esIgual(Data altra) {
        return this.dia == altra.dia && this.mes == altra.mes && this.any == altra.any;
    }
}
