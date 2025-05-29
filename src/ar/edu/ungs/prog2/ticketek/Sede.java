package ar.edu.ungs.prog2.ticketek;

import java.util.Map;

/**
 * Clase abstracta que representa una sede para espectáculos.
 * Define métodos y atributos comunes para las sedes concretas.
 *
 * IREP (Invariante de Representación):
 * - nombre != null && !nombre.isEmpty()
 * - tipo != null && !tipo.isEmpty()
 * - capacidadMaxima > 0
 * - direccion != null && !direccion.isEmpty()
 * - tipo debe ser uno de los valores válidos: "Teatro", "Estadio", etc.
 * - La capacidad máxima debe ser consistente con la suma de capacidades de todos los sectores
 * - esNumerada() devuelve true para todos los tipos excepto "Estadio"
 */
public abstract class Sede {
    protected String nombre;
    protected String tipo;
    protected int capacidadMaxima;
    protected String direccion;

    /**
     * Constructor que inicializa una sede con nombre, tipo, capacidad y dirección.
     * 
     * @param nombre         Nombre de la sede
     * @param tipo           Tipo de sede (ej. Teatro, Estadio, etc.)
     * @param capacidadMaxima Capacidad máxima de la sede
     * @param direccion      Dirección física de la sede
     */
    public Sede(String nombre, String tipo, int capacidadMaxima, String direccion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadMaxima = capacidadMaxima;
        this.direccion = direccion;
    }

    /**
     * Método abstracto para calcular el precio de la entrada según el tipo de sede y sector.
     * 
     * @param precioBase Precio base de la entrada
     * @param sector     Nombre del sector
     * @return Precio calculado para la entrada
     */
    public abstract double calcularPrecioEntrada(double precioBase, String sector);

    /**
     * Método abstracto que devuelve toda la información detallada de la sede.
     * 
     * @return String con información completa
     */
    public abstract String obtenerInformacionCompleta();

    /**
     * Método abstracto que devuelve la capacidad de un sector específico de la sede.
     * 
     * @param nombreSector Nombre del sector
     * @return Capacidad máxima del sector
     */
    public abstract int getCapacidadSector(String nombreSector);

    /**
     * @return Nombre de la sede
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return Tipo de la sede
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return Capacidad máxima de la sede
     */
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * @return Dirección física de la sede
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Método abstracto que devuelve los asientos disponibles para sedes numeradas.
     * 
     * @return Mapa sector -> (asiento -> disponibilidad)
     */
    public abstract Map<String, Map<Integer, Boolean>> getDisponiblesInicialesNumerados();

    /**
     * Método abstracto que devuelve la cantidad disponible para sedes sin numerar.
     * 
     * @return Cantidad de entradas disponibles
     */
    public abstract Integer getDisponiblesInicialesSinNumerar();

    /**
     * Indica si la sede es numerada o no.
     * 
     * @return true si la sede es numerada, false si no
     */
    public boolean esNumerada() {
        return !tipo.equals("Estadio");
    }

    /**
     * Devuelve un array de String con los nombres de los sectores de la sede.
     * Si la sede no posee sectores (por ejemplo, en el caso de un estadio), puede devolver null o un array vacío.
     *
     * @return Array de Strnig con los nombres de los sectores, o null si no aplica.
     */
    public abstract String[] getSectores();

    /**
     * Devuelve una representación en String de la sede, incluyendo sus datos principales.
     *
     * @return String descriptivo de la sede.
     */
    @Override
    public abstract String toString();

}


