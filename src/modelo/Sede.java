package modelo;

import java.util.Map;

public abstract class Sede {
	 protected String nombre;
	    protected String tipo;
	    protected int capacidadMaxima;
	    protected String direccion;
	    
	    public Sede(String nombre, String tipo, int capacidadMaxima, String direccion) {
	        this.nombre = nombre;
	        this.tipo = tipo;
	        this.capacidadMaxima = capacidadMaxima;
	        this.direccion = direccion;
	    } 
	    
	    // Método que calcula el precio de la entrada según el tipo de sede
	    public abstract double calcularPrecioEntrada(double precioBase, String sector);

	    // Método que devuelve toda la info de la sede
	    public abstract String obtenerInformacionCompleta();
	    
	    // Devuelve la capacidad total de un sector específico de la sede
	    public abstract int getCapacidadSector(String nombreSector);

	    
	    public String getNombre() {
	        return nombre;
	    }
	    
	    public String getTipo() {
			return tipo;
		}

		public int getCapacidadMaxima() {
			return capacidadMaxima;
		}

		public String getDireccion() {
			return direccion;
		}
		
		// Devuelve la grilla de asientos para sedes numeradas (Teatro, Miniestadio)
		public abstract Map<String, int[][]> getDisponiblesInicialesNumerados();

		// Devuelve la cantidad de entradas disponibles para sedes no numeradas (Estadio)
		public abstract Integer getDisponiblesInicialesSinNumerar();

		

		public boolean esNumerada() {
	    	return !tipo.equals("Teatro");
	    }
	         

}
