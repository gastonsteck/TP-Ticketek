package modelo;

public abstract class Sede {
	 protected String nombre;
	    protected String tipo;
	    protected int capacidad;
	    protected String direccion;
	    
	    public Sede(String nombre, String tipo, int capacidad, String direccion) {
	        this.nombre = nombre;
	        this.tipo = tipo;
	        this.capacidad = capacidad;
	        this.direccion = direccion;
	    } 
	    
	    // Método que calcula el precio de la entrada según el tipo de sede
	    public abstract double calcularPrecioEntrada(double precioBase, String sector);

	    // Método que devuelve toda la info de la sede
	    public abstract String obtenerInformacionCompleta();
	    
	    public String getNombre() {
	        return nombre;
	    }

}
