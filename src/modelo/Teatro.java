package modelo;

public class Teatro extends Sede {
	  private int cantFilas;
	    private int asientosPorFila;
	public Teatro(String nombre, int capacidad, String direccion, int cantFilas, int asientosPorFila) {
	        super(nombre, "Teatro", capacidad, direccion);
	        this.cantFilas = cantFilas;
	        this.asientosPorFila = asientosPorFila;
	    }
	
	@Override
	public double calcularPrecioEntrada(double precioBase, String sector) {
	    if (sector.equalsIgnoreCase("Platea VIP")) {
	        return precioBase * 1.70;
	    } else if (sector.equalsIgnoreCase("Platea común")) {
	        return precioBase * 1.40;
	    } else if (sector.equalsIgnoreCase("Platea baja")) {
	        return precioBase * 1.50;
	    } else if (sector.equalsIgnoreCase("Platea alta")) {
	        return precioBase; // sin incremento
	    } else {
	        return precioBase; // por defecto si no reconoce el sector
	    }
	}
	@Override
	public String obtenerInformacionCompleta() {
	    return "Teatro: " + nombre +
	           " | Capacidad: " + capacidad +
	           " | Dirección: " + direccion;
	}

}
