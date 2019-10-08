/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    - Ibai Monle�n - 
 * 
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if (sexo == HOMBRE) {
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
        else {
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }
    }

    /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        switch(dia){
            case 1:
            case 2:
            case 3: 
            case 4:
            case 5: totalPasosLaborables += pasos;
            break;
            case SABADO: totalPasosSabado += pasos;
            break;        
            case DOMINGO: totalPasosDomingo += pasos;
        }
        totalDistanciaSemana = longitudZancada * (totalPasosLaborables + totalPasosSabado + totalPasosDomingo);
        totalDistanciaFinSemana = longitudZancada * (totalPasosSabado + totalPasosDomingo);
        totalDistanciaFinSemana = longitudZancada * (totalPasosSabado + totalPasosDomingo);
        tiempo += (horaFin - horaInicio) / 100 * 60 + (horaFin - horaInicio) % 100;
        if (horaFin / 100 >= 21) {
            caminatasNoche++;
        }
    }

    /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {      
        String aux = "";
        if (sexo == HOMBRE) {
            aux = "HOMBRE";
        } else {
            aux = "MUJER";
        }
        System.out.println("Configuraci�n del pod�metro\n*********************************\nAltura: " + altura / 100 + "mtos\nSexo: " + 
            aux + "\nLongitud zancada: " + longitudZancada / 100 + "mtos");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        int horas = 0;
        int minutos = 0;
        if (tiempo >= 60) {
            horas = tiempo / 60;
            minutos = tiempo % 60;
        }
        System.out.println("\nEstad�sticas\n*********************************\nDistancia recorrida toda la semana: " + 
            totalDistanciaSemana / 100000 + "km\nDistancia recorrida fin de semana: " + totalDistanciaFinSemana /100000 
            + "km\n\nN� pasos d�as laborables: " + totalPasosLaborables + "\nN� pasos S�BADO: " + totalPasosSabado + 
            "\nN� pasos DOMINGO: " + totalPasosDomingo + "\nN� caminatas realizadas en la semana a partir de las 21h: " 
            + caminatasNoche + "\nTiempo total caminando en la semana: "+ horas +"h. y " + minutos + "m.");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String aux = "";  
        if (totalPasosLaborables > totalPasosSabado && 
        totalPasosLaborables > totalPasosDomingo) {
            aux = "LABORABLES";
        }
        else if (totalPasosSabado > totalPasosLaborables && 
        totalPasosSabado > totalPasosDomingo) {
            aux = "SABADO";
        }
        else if (totalPasosDomingo > totalPasosSabado && 
        totalPasosLaborables > totalPasosDomingo) {
            aux = "LABORABLES";
        }    
        return aux;

    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;

    }

}
