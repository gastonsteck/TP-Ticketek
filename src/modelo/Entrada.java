package modelo;

  //Clase que representa una entrada comprada por un usuario 
  public class Entrada  {
    private String codigoEntrada;
    private String codigoEspectaculo;
    private String nombreEspectaculo;
    private String nombreSede;
    private Fecha fecha;
    private String sector;
    private int numAsiento;
    private double valorFinal;
    
    
 // Constructor completo de entrada
    public Entrada(String codigoEntrada, String codigoEspectaculo, String nombreEspectaculo,
                   String nombreSede, Fecha fecha, String sector, int numAsiento, double valorFinal) {
        this.codigoEntrada = codigoEntrada;
        this.codigoEspectaculo = codigoEspectaculo;
        this.nombreEspectaculo = nombreEspectaculo;
        this.nombreSede = nombreSede;
        this.fecha = fecha;
        this.sector = sector;
        this.numAsiento = numAsiento;
        this.valorFinal = valorFinal;
    }
    
 // Devuelve el valor final de la entrada
    public double devolverValor() {
        return valorFinal;
    }
    
 // Devuelve si la entrada corresponde a una fecha futura
    public boolean esFutura() {
        return fecha.esFutura();
    }
    
 // Métodos para acceder a datos individuales
    public String devolverEspectaculo() {
        return codigoEspectaculo;
    }
    
    public String devolverSede() {
        return nombreSede;
    }
    
    public String devolverSector() {
        return sector;
    }
    
    public int devolverAsiento() {
        return numAsiento;
    }
    
 // Representación en texto de la entrada
    @Override
    public String toString() {
        return nombreEspectaculo + " - " + nombreSede + " - " + sector + " Asiento " + numAsiento;
    }
}
