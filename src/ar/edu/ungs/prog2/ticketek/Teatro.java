package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa un teatro, una sede con asientos numerados y sectores.
 * Hereda de la clase abstracta Sede.
 */
public class Teatro extends Sede {
    private int asientosPorFila;
    private String[] sectores;
    private int[] capacidad;
    private int[] porcentajeAdicional;
    private Map<String, Map<Integer, Boolean>> sectoresPorNombre;

    /**
     * Constructor que crea un teatro con sus características y sectores.
     *
     * @param nombre           Nombre del teatro
     * @param direccion        Dirección física del teatro
     * @param capacidadMaxima  Capacidad máxima total del teatro
     * @param asientosPorFila  Cantidad de asientos por fila
     * @param sectores         Array con nombres de sectores
     * @param capacidad        Array con capacidades por sector
     * @param porcentajeAdicional Array con porcentaje adicional para cada sector
     */
    public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
                  String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, "Teatro", capacidadMaxima, direccion);
        this.asientosPorFila = asientosPorFila;
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
        this.sectoresPorNombre = new HashMap<>();
        inicializarSectores();
    }

    /**
     * Calcula el precio de entrada para un sector específico aplicando el porcentaje adicional.
     *
     * @param precioBase Precio base de la entrada
     * @param sector     Nombre del sector
     * @return Precio calculado para el sector
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
//        	System.out.println("precio " + precio);
//        	System.out.println("porcentaje " + porcentajeAdicional[aux]);
//        	System.out.println("total " + ( precio + precioBase * (porcentajeAdicional[aux] / 100.0)));
            precio += precioBase * (porcentajeAdicional[aux] / 100.0);

        return precio;
    }

    /**
     * Devuelve la información completa del teatro.
     *
     * @return String con nombre, capacidad y dirección
     */
    @Override
    public String obtenerInformacionCompleta() {
        return "Teatro: " + nombre +
               " | Capacidad: " + capacidad +
               " | Dirección: " + direccion;
    }

    /**
     * Inicializa el mapa de sectores con sus asientos disponibles.
     * Cada asiento está marcado como disponible inicialmente.
     */
    public void inicializarSectores() {
        for (int i = 0; i < sectores.length; i++) {
            String nombreSector = sectores[i];
            int cantidadAsientos = capacidad[i];

            Map<Integer, Boolean> mapaAsientos = new HashMap<>();

            for (int numeroAsiento = 1; numeroAsiento <= cantidadAsientos; numeroAsiento++) {
                mapaAsientos.put(numeroAsiento, true); // true = disponible
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
     * @return Array con los nombres de los sectores
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
     * @return Mapa con los sectores y la disponibilidad de sus asientos
     */
    public Map<String, Map<Integer, Boolean>> getSectoresPorNombre() {
        return sectoresPorNombre;
    }

    /**
     * Devuelve los asientos disponibles iniciales para sedes numeradas.
     *
     * @return Mapa sectores con sus asientos disponibles
     */
    @Override
    public Map<String, Map<Integer, Boolean>> getDisponiblesInicialesNumerados() {
        return this.sectoresPorNombre;
    }

    /**
     * En teatros no hay sedes sin numerar, por lo que retorna null.
     *
     * @return null
     */
    @Override
    public Integer getDisponiblesInicialesSinNumerar() {
        return null;
    }
}
