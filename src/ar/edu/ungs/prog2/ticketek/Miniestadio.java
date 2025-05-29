package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa un miniestadio, una sede con asientos numerados y características propias.
 * Hereda de la clase abstracta Sede.
 *
 * IREP (Invariante de Representación):
 * - asientosPorFila > 0
 * - sectores != null && sectores.length > 0
 * - capacidad != null && capacidad.length == sectores.length
 * - porcentajeAdicional != null && porcentajeAdicional.length == sectores.length
 * - cantidadPuestos > 0
 * - precioConsumicion >= 0.0
 * - Para todo i: capacidad[i] > 0
 * - Para todo i: porcentajeAdicional[i] >= 0
 * - sectoresPorNombre != null
 * - Para todo sector en sectores: existe sectoresPorNombre.get(sector)
 * - Para todo sector: sectoresPorNombre.get(sector).size() == capacidad[índice_sector]
 * - Para todo asiento en sectoresPorNombre: número de asiento >= 1 && <= capacidad del sector
 * - La suma de todas las capacidades debe ser <= capacidadMaxima (heredado de Sede)
 * - tipo.equals("Miniestadio") (heredado de Sede)
 * - esNumerada() == true (los miniestadios son numerados)
 */
public class Miniestadio extends Sede {
    private int asientosPorFila;
    private String[] sectores;
    private int[] capacidad;
    private int[] porcentajeAdicional;
    private Map<String, Map<Integer, Boolean>> sectoresPorNombre;
    private int cantidadPuestos;
    private double precioConsumicion;

    /**
     * Constructor que inicializa un miniestadio con sus atributos específicos.
     * 
     * @param nombre           Nombre del miniestadio
     * @param direccion        Dirección física
     * @param capacidadMaxima  Capacidad máxima total
     * @param asientosPorFila  Cantidad de asientos por fila
     * @param cantidadPuestos  Cantidad de puestos de venta
     * @param precioConsumicion Precio por consumición incluida en la entrada
     * @param sectores         Array con nombres de sectores
     * @param capacidad        Array con capacidades por sector
     * @param porcentajeAdicional Array con porcentaje adicional aplicado a cada sector
     */
    public Miniestadio(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
                       int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, "Miniestadio", capacidadMaxima, direccion);
        this.asientosPorFila = asientosPorFila;
        this.cantidadPuestos = cantidadPuestos;
        this.sectoresPorNombre = new HashMap<>();
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
        this.precioConsumicion = precioConsumicion;
        inicializarSectores();
    }

    /**
     * Calcula el precio de entrada para un sector específico, sumando el porcentaje adicional y el precio de consumición.
     * 
     * @param precioBase Precio base de la entrada
     * @param sector     Nombre del sector
     * @return Precio total calculado
     * @throws IllegalArgumentException si el sector no es válido
     */
    @Override
    public double calcularPrecioEntrada(double precioBase, String sector) {
        double precio = precioBase;
        int aux = -1;
        for (int i = 0; i < sectores.length; i++) {
            if (sectores[i].equals(sector)) {
                aux = i;
                break;
            }
        }

        if (aux == -1)
            throw new IllegalArgumentException("El sector no es válido para la sede");
        else
            precio += precioBase * (porcentajeAdicional[aux] / 100.0);

        return precio + precioConsumicion;
    }

    /**
     * Devuelve información completa del miniestadio, incluyendo capacidad, puestos y precio de consumición.
     * 
     * @return String con información del miniestadio
     */
    @Override
    public String obtenerInformacionCompleta() {
        return "Miniestadio: " + nombre +
               " | Capacidad: " + capacidad +
               " | Dirección: " + direccion +
               " | Puestos de venta: " + cantidadPuestos +
               " | Precio de consumición: $" + precioConsumicion;
    }

    /**
     * Inicializa los sectores con la disponibilidad de asientos, marcando inicialmente como libres (false).
     */
    public void inicializarSectores() {
        sectoresPorNombre = new HashMap<>(); // 

        for (int i = 0; i < sectores.length; i++) {
            String nombreSector = sectores[i];
            int cantidadAsientos = capacidad[i];

            Map<Integer, Boolean> mapaAsientos = new HashMap<>();
            for (int numeroAsiento = 1; numeroAsiento <= cantidadAsientos; numeroAsiento++) {
                mapaAsientos.put(numeroAsiento, true);
            }

            sectoresPorNombre.put(nombreSector, mapaAsientos);
        }
    }


    /**
     * Obtiene la capacidad de un sector específico.
     * 
     * @param nombreSector Nombre del sector
     * @return Capacidad del sector
     * @throws IllegalArgumentException si el sector no existe
     */
    @Override
    public int getCapacidadSector(String nombreSector) {
        for (int i = 0; i < sectores.length; i++) {
            if (sectores[i].equals(nombreSector)) {
                return capacidad[i];
            }
        }
        throw new IllegalArgumentException("Sector no válido: " + nombreSector);
    }

    /**
     * @return Cantidad de asientos por fila
     */
    public int getAsientosPorFila() {
        return asientosPorFila;
    }

    /**
     * @return Array con nombres de sectores
     */
    public String[] getSectores() {
        return sectores;
    }

    /**
     * @return Array con capacidades de cada sector
     */
    public int[] getCapacidad() {
        return capacidad;
    }

    /**
     * @return Array con porcentaje adicional aplicado a cada sector
     */
    public int[] getPorcentajeAdicional() {
        return porcentajeAdicional;
    }

    /**
     * @return Mapa con sectores y disponibilidad de asientos
     */
    public Map<String, Map<Integer, Boolean>> getSectoresPorNombre() {
        return sectoresPorNombre;
    }

    /**
     * @return Cantidad de puestos de venta
     */
    public int getCantidadPuestos() {
        return cantidadPuestos;
    }

    /**
     * @return Precio de consumición incluido en la entrada
     */
    public double getPrecioConsumicion() {
        return precioConsumicion;
    }

    /**
     * Devuelve los asientos disponibles iniciales para sedes numeradas.
     * 
     * @return Mapa sectores con asientos disponibles
     */
    @Override
    public Map<String, Map<Integer, Boolean>> getDisponiblesInicialesNumerados() {
        Map<String, Map<Integer, Boolean>> copia = new HashMap<>();

        for (String sector : sectoresPorNombre.keySet()) {
            Map<Integer, Boolean> butacasOriginales = sectoresPorNombre.get(sector);
            Map<Integer, Boolean> copiaButacas = new HashMap<>();

            for (Integer nro : butacasOriginales.keySet()) {
                copiaButacas.put(nro, butacasOriginales.get(nro));
            }

            copia.put(sector, copiaButacas);
        }

        return copia;
    }


    /**
     * No aplica para miniestadio ya que tiene sedes numeradas.
     * 
     * @return null
     */
    @Override
    public Integer getDisponiblesInicialesSinNumerar() {
        return null;
    }
    
    /**
     * Devuelve una representación en String del miniestadio, incluyendo nombre,
     * dirección, capacidad máxima, asientos por fila, cantidad de puestos de venta,
     * precio de consumición y el detalle de los sectores con sus capacidades y porcentajes adicionales.
     *
     * @return String descriptivo del miniestadio y sus sectores.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Miniestadio: ").append(nombre)
          .append(" | Dirección: ").append(direccion)
          .append(" | Capacidad máxima: ").append(capacidadMaxima)
          .append(" | Asientos/fila: ").append(asientosPorFila)
          .append(" | Puestos de venta: ").append(cantidadPuestos)
          .append(" | Precio consumición: $").append(precioConsumicion)
          .append("\n  Sectores:\n");

        for (int i = 0; i < sectores.length; i++) {
            sb.append("    - ").append(sectores[i])
              .append(" | Capacidad: ").append(capacidad[i])
              .append(" | % adicional: ").append(porcentajeAdicional[i])
              .append("\n");
        }
        return sb.toString();
    }


}
