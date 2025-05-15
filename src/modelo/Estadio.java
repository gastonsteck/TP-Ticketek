package modelo;
// Clase que representa un estadio, hereda de Sede
public class Estadio extends Sede {
	 public Estadio(String nombre, int capacidad, String direccion) {
	        super(nombre, "Estadio", capacidad, direccion);
	    }

	    // En un estadio, solo se cobra el precio base (no hay sectores)
	    @Override
	    public double calcularPrecioEntrada(double precioBase, String sector) {
	        return precioBase;
	    }

	    @Override
	    public String obtenerInformacionCompleta() {
	        return "Estadio: " + nombre + " | Capacidad: " + capacidad + " | Dirección: " + direccion;
	    }
}
