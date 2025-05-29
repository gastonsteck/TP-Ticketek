package ar.edu.ungs.prog2.ticketek;

import java.util.*;
/**
 * Clase que representa un espectáculo.
 * Contiene sedes, fechas, funciones y lleva el control de la recaudación.
 *
 * IREP (Invariante de Representación):
 * - nombre != null && !nombre.isEmpty()
 * - sedes != null (puede estar vacía pero no null)
 * - fechas != null (puede estar vacía pero no null)
 * - funciones != null (puede estar vacía pero no null)
 * - recaudacionPorSede != null
 * - Para toda fecha en funciones.keySet(): fecha != null && !fecha.isEmpty()
 * - Para toda funcion en funciones.values(): funcion != null
 * - Para todo nombreSede en recaudacionPorSede.keySet(): nombreSede != null && !nombreSede.isEmpty()
 * - Para todo valor en recaudacionPorSede.values(): valor >= 0.0
 * - recaudacionTotal >= 0.0
 * - recaudacionTotal == suma de todos los valores en recaudacionPorSede
 * - Las fechas en funciones.keySet() deben corresponder a fechas válidas
 * - No puede haber funciones duplicadas para la misma fecha (clave única en funciones)
 * - getFuncion(fecha) debe ser consistente con getFuncion(fecha.toString())
 */
public class Espectaculo {

    private String nombre;
    private List<Sede> sedes;
    private List<Fecha> fechas;
    private Map<String, Funcion> funciones;
    private Map<String, Double> recaudacionPorSede = new HashMap<>();
    private double recaudacionTotal = 0;

    /**
     * Constructor que inicializa un espectáculo con un nombre.
     * Inicializa las colecciones para sedes, fechas y funciones.
     * 
     * @param nombre Nombre del espectáculo
     */
    public Espectaculo(String nombre) {
        this.nombre = nombre;
        this.sedes = new ArrayList<>();
        this.fechas = new ArrayList<>();
        this.funciones = new HashMap<>();
    }

    /**
     * Agrega una función al espectáculo en una fecha y sede determinada con un precio base.
     * 
     * @param fecha      Fecha de la función
     * @param sede       Sede donde se realiza la función
     * @param precioBase Precio base de la función
     * @throws IllegalArgumentException si ya existe una función para esa fecha
     */
    public void agregarFuncion(Fecha fecha, Sede sede, double precioBase) {
        if (funciones == null)
            funciones = new HashMap<>();

        if (funciones.containsKey(fecha.toString())) {
            throw new IllegalArgumentException("Ya existe una función en esa sede.");
        }

        Funcion funcion = new Funcion(sede, fecha, precioBase);
        funciones.put(fecha.toString(), funcion);
    }

    /**
     * Obtiene la recaudación acumulada para una sede específica.
     * 
     * @param nombreSede Nombre de la sede
     * @return Monto recaudado para esa sede, 0 si no existe recaudación
     */
    public double getRecaudadoPorSede(String nombreSede) {
        if (recaudacionPorSede.containsKey(nombreSede)) {
            return recaudacionPorSede.get(nombreSede);
        } else {
            return 0.0;
        }
    }

    /**
     * Agrega un monto a la recaudación de una sede específica.
     * También actualiza la recaudación total del espectáculo.
     * 
     * @param nombreSede Nombre de la sede
     * @param monto      Monto a agregar
     */
    public void agregarRecaudacion(String nombreSede, double monto) {
        if (recaudacionPorSede.containsKey(nombreSede)) {
            double actual = recaudacionPorSede.get(nombreSede);
            recaudacionPorSede.put(nombreSede, actual + monto);
        } else {
            recaudacionPorSede.put(nombreSede, monto);
        }

        recaudacionTotal += monto;
    }
    
    /**
     * Resta un monto de la recaudación de una sede específica.
     * También actualiza la recaudación total del espectáculo.
     * 
     * @param nombreSede Nombre de la sede
     * @param monto      Monto a restar
     */
    public void restarRecaudacion(String nombreSede, double monto) {
        double actual = recaudacionPorSede.get(nombreSede);
        recaudacionPorSede.put(nombreSede, actual - monto);
        recaudacionTotal -= monto;
    }

    /**
     * Obtiene una función por su fecha en formato String.
     * 
     * @param fechaStr Fecha de la función en formato String
     * @return Objeto Funcion correspondiente o null si no existe
     */
    public Funcion getFuncion(String fechaStr) {
        return funciones.get(fechaStr);
    }

    /**
     * Obtiene una función por su objeto Fecha.
     * 
     * @param fecha Objeto Fecha de la función
     * @return Objeto Funcion correspondiente o null si no existe
     */
    public Funcion getFuncion(Fecha fecha) {
        return getFuncion(fecha.toString());
    }


    /**
     * @return La recaudación total acumulada por el espectáculo
     */
    public double getRecaudacionTotal() {
        return recaudacionTotal;
    }

    /**
     * @return Mapa de funciones, donde la clave es la fecha en String
     */
    public Map<String, Funcion> getFunciones() {
        return funciones;
    }

    /**
     * @return Nombre del espectáculo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return Lista de sedes donde se realizan funciones
     */
    public List<Sede> getSedes() {
        return sedes;
    }

    /**
     * @return Lista de fechas para funciones
     */
    public List<Fecha> getFechas() {
        return fechas;
    }
    
    /**
     * Devuelve una representación en forma de cadena del espectáculo, 
     * incluyendo su nombre y la lista de funciones asociadas.
     *
     * @return String con el nombre del espectáculo y sus funciones.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Espectáculo: ").append(nombre).append("\n");
        sb.append("Funciones:\n");
        for (Funcion funcion : funciones.values()) {
            sb.append("  - ").append(funcion).append("\n");
        }
        return sb.toString();
    }
}
