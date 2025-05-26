package modelo;
import java.util.Map;
import java.util.HashMap;

// Clase que representa un estadio, hereda de Sede
public class Estadio extends Sede {
	 public Estadio(String nombre, int capacidadMaxima, String direccion) {
	        super(nombre, "Estadio", capacidadMaxima, direccion);
	    }

	    // En un estadio, solo se cobra el precio base (no hay sectores)
	    @Override
	    public double calcularPrecioEntrada(double precioBase, String sector) {
	        return precioBase;
	    }

	    @Override
	    public String obtenerInformacionCompleta() {
	        return "Estadio: " + nombre + " | Capacidad máxima: " + capacidadMaxima + " | Dirección: " + direccion;
	    }
	    
	  
	    
	    @Override
	    public int getCapacidadSector(String nombreSector) {
	        return capacidadMaxima; // si siempre es único sector: "Campo"
	    }
 
	    @Override
	    public Integer getDisponiblesInicialesSinNumerar() {
	        
	        return this.getCapacidadMaxima();
	    }

	    @Override
	    public Map<String, int[][]> getDisponiblesInicialesNumerados() {
	        return null; // no aplica para Estadio
	    }

	    
	    
}
