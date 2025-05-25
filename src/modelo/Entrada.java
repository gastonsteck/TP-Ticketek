package modelo;

import ar.edu.ungs.prog2.ticketek.IEntrada;

//Clase que representa una entrada comprada por un usuario 
  public class Entrada implements IEntrada {
    private String codigoEntrada;
    private String codigoEspectaculo;
    private String nombreEspectaculo;
    private String nombreSede;
    private Fecha fecha;
    private String sector;
    private int numAsiento;
    private double valorFinal;
    private int fila;

    
    
 // Constructor completo de entrada
    public Entrada(String codigoEntrada, String codigoEspectaculo, String nombreEspectaculo,
                   String nombreSede, Fecha fecha, String sector,int fila, int numAsiento, double valorFinal) {
        this.codigoEntrada = codigoEntrada;
        this.codigoEspectaculo = codigoEspectaculo;
        this.nombreEspectaculo = nombreEspectaculo;
        this.nombreSede = nombreSede;
        this.fecha = fecha;
        this.sector = sector;
        this.fila = fila;
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
    
    public String devolverCodigo() {
        return codigoEntrada;
    }
    public int devolverFila() {
        return fila;
    }
   
    
    @Override
    public String ubicacion() {
        if (fila >= 0) {
            return "Fila " + fila + " - Asiento " + numAsiento;
        } else {
            return "Asiento " + numAsiento;
        }
    }

    
 // Representación en texto de la entrada
    @Override
    public String toString() {
        return nombreEspectaculo + " - " + nombreSede + " - " + sector + " Asiento " + numAsiento;
    }

	public void cambiarSede(String nombreSede2, Fecha fecha2) {
		this.nombreSede = nombreSede2;
		this.fecha = fecha2;
	}

	public void cambiarSede(String nombreSede2, Fecha fecha2, Integer nuevoAsiento) {
		nombreSede = nombreSede2;
		fecha = fecha2;
		numAsiento = nuevoAsiento;
	}
	
	public void cambiarSede(String nombreSede2, Fecha fecha2, int fila, int asiento) {
	    this.nombreSede = nombreSede2;
	    this.fecha = fecha2;
	    this.fila = fila;
	    this.numAsiento = asiento;
	}
	@Override
	public double precio() {
	    return valorFinal;
	}


}
