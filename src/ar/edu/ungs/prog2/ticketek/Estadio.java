package ar.edu.ungs.prog2.ticketek;

import java.util.Map;

/**
 * Clase que representa un estadio, que es una sede sin numeración de asientos.
 * Hereda de la clase abstracta Sede.
 *
 * IREP (Invariante de Representación):
 * - capacidadMaxima > 0 (heredado de Sede)
 * - nombre != null && !nombre.isEmpty() (heredado de Sede)
 * - direccion != null && !direccion.isEmpty() (heredado de Sede)
 * - tipo.equals("Estadio") (heredado de Sede)
 * - esNumerada() == false (los estadios no son numerados)
 * - getDisponiblesInicialesNumerados() == null (no aplica para estadios)
 * - getDisponiblesInicialesSinNumerar() == capacidadMaxima
 * - calcularPrecioEntrada() siempre devuelve precioBase (sin recargos por sector)
 * - getCapacidadSector() siempre devuelve capacidadMaxima (independiente del sector)
 */
public class Estadio extends Sede {

    /**
     * Constructor que crea un estadio con nombre, capacidad máxima y dirección.
     * El tipo se establece automáticamente como "Estadio".
     * 
     * @param nombre          Nombre del estadio
     * @param capacidadMaxima Capacidad máxima del estadio
     * @param direccion       Dirección del estadio
     */
    public Estadio(String nombre, int capacidadMaxima, String direccion) {
        super(nombre, "Estadio", capacidadMaxima, direccion);
    }

    /**
     * Calcula el precio de entrada en un estadio.
     * En un estadio, solo se cobra el precio base sin diferencia por sector.
     * 
     * @param precioBase Precio base de la entrada
     * @param sector     Nombre del sector (no utilizado en esta clase)
     * @return Precio calculado (igual al precio base)
     */
    @Override
    public double calcularPrecioEntrada(double precioBase, String sector) {
        return precioBase;
    }

    /**
     * Devuelve una descripción completa del estadio.
     * 
     * @return String con información del estadio
     */
    @Override
    public String obtenerInformacionCompleta() {
        return "Sede: " + this.getNombre() + " | Capacidad máxima: " + this.getCapacidadMaxima() + " | Dirección: " + this.getDireccion();
    }

    /**
     * Obtiene la capacidad de un sector.
     * En el estadio, se considera toda la capacidad para cualquier sector.
     * 
     * @param nombreSector Nombre del sector (no relevante aquí)
     * @return Capacidad máxima del estadio
     */
    @Override
    public int getCapacidadSector(String nombreSector) {
        return this.getCapacidadMaxima();
    }

    /**
     * Devuelve la cantidad inicial de entradas disponibles en sedes sin numerar.
     * 
     * @return Capacidad máxima del estadio
     */
    @Override
    public Integer getDisponiblesInicialesSinNumerar() {
        return this.getCapacidadMaxima();
    }

    /**
     * Devuelve la disponibilidad inicial para sedes numeradas.
     * En estadio no aplica, por eso devuelve null.
     * 
     * @return null porque no es una sede numerada
     */
    @Override
    public Map<String, Map<Integer, Boolean>> getDisponiblesInicialesNumerados() {
        return null;
    }

    /**
     * Devuelve los sectores disponibles en el estadio.
     * 
     * @return null porque no es una sede numerada
     */
    @Override
    public String[] getSectores() {
        return null;
    }
    
    @Override
	public boolean esNumerada() {
		return false;
	}

    /**
     * Devuelve una representación en cadena del estadio, mostrando su nombre, dirección y capacidad máxima.
     * 
     * @return String que describe el estadio.
     */
    @Override
    public String toString() {
        return "Sede: " + this.getNombre() + " (" + this.getDireccion() + ") - Capacidad: " + this.getCapacidadMaxima();
    }

	

}
