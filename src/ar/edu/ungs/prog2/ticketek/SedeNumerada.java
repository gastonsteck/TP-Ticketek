package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase abstracta que representa una sede con asientos numerados, como teatros o miniestadios.
 * Extiende la clase abstracta Sede y agrega lógica para sectores, asientos por fila,
 * capacidad sectorizada y porcentajes de recargo por sector.
 *
 * IREP (Invariante de Representación):
 * - asientosPorFila > 0
 * - sectores != null y sectores.length > 0
 * - capacidad.length == sectores.length
 * - porcentajeAdicional.length == sectores.length
 * - Ningún nombre de sector debe ser vacío
 * - La capacidad de cada sector debe estar entre asientosPorFila y capacidadMaxima
 * - La suma de todas las capacidades de sectores debe ser igual a capacidadMaxima
 * - sectoresPorNombre contiene una clave por cada sector con sus asientos como disponibles
 */
public abstract class SedeNumerada extends Sede {
    private int asientosPorFila;
    private String[] sectores;
    private int[] capacidad;
    private int[] porcentajeAdicional;
    private Map<String, Map<Integer, Boolean>> sectoresPorNombre;

    /**
     * Constructor que inicializa una sede numerada con sus sectores, capacidades y recargos.
     *
     * @param nombre               Nombre de la sede
     * @param tipo                 Tipo de sede (ej. Teatro, Miniestadio)
     * @param capacidadMaxima      Capacidad total de la sede
     * @param direccion            Dirección física
     * @param asientosPorFila      Cantidad de asientos por fila
     * @param sectores             Array con los nombres de los sectores
     * @param capacidad            Array con las capacidades de cada sector
     * @param porcentajeAdicional  Array con el recargo porcentual por sector
     * @throws IllegalArgumentException si alguna condición del invariante no se cumple
     */
    public SedeNumerada(String nombre, String tipo, int capacidadMaxima, String direccion,
                        int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, tipo, capacidadMaxima, direccion);

        chequeosConstructor(capacidadMaxima, asientosPorFila, sectores, capacidad, porcentajeAdicional);

        this.asientosPorFila = asientosPorFila;
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
        this.sectoresPorNombre = new HashMap<>();
        inicializarSectores();
    }
    
    /**
     * Verifica todas las condiciones previas para construir una SedeNumerada.
     * 
     * @param capacidadMaxima      Capacidad total de la sede
     * @param asientosPorFila      Cantidad de asientos por fila (debe ser > 0)
     * @param sectores             Array con los nombres de los sectores (no nulo ni vacío)
     * @param capacidad            Array con las capacidades por sector (mismo largo que sectores)
     * @param porcentajeAdicional  Array con los porcentajes adicionales por sector (mismo largo que sectores)
     * @throws IllegalArgumentException si no se cumplen las condiciones del IREP
     */
    private void chequeosConstructor(int capacidadMaxima, int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        if (asientosPorFila <= 0)
            throw new IllegalArgumentException("La cantidad de asientos por fila debe ser mayor que cero");
        if (sectores == null || sectores.length == 0)
            throw new IllegalArgumentException("Debe indicarse al menos un sector");
        if (capacidad == null || capacidad.length != sectores.length)
            throw new IllegalArgumentException("La cantidad de capacidades debe coincidir con los sectores");
        if (porcentajeAdicional == null || porcentajeAdicional.length != sectores.length)
            throw new IllegalArgumentException("Los porcentajes deben coincidir con los sectores");

        int sumaSectores = 0;
        for (int i = 0; i < sectores.length; i++) {
            if (sectores[i] == null || sectores[i].isEmpty()) {
                throw new IllegalArgumentException("El nombre de los sectores no puede ser vacío");
            }
            if (!(capacidad[i] <= capacidadMaxima && capacidad[i] >= asientosPorFila)) {
                throw new IllegalArgumentException("La capacidad del sector debe ser mayor o igual a los asientos por fila y menor o igual a la capacidad máxima");
            }
            sumaSectores += capacidad[i];
        }

        if (sumaSectores != capacidadMaxima) {
            throw new IllegalArgumentException("La suma de la capacidad de los sectores debe ser igual a la capacidad máxima");
        }
    }


    /**
     * Inicializa los sectores y sus asientos como disponibles.
     */
    protected void inicializarSectores() {
        for (int i = 0; i < sectores.length; i++) {
            Map<Integer, Boolean> mapaAsientos = new HashMap<>();
            for (int num = 1; num <= capacidad[i]; num++) {
                mapaAsientos.put(num, true);
            }
            sectoresPorNombre.put(sectores[i], mapaAsientos);
        }
    }

    /**
     * Devuelve una copia del mapa de sectores con sus asientos disponibles.
     *
     * @return Mapa sector -> (asiento -> disponibilidad)
     */
    @Override
    public Map<String, Map<Integer, Boolean>> getDisponiblesInicialesNumerados() {
        Map<String, Map<Integer, Boolean>> copia = new HashMap<>();
        for (String sector : sectoresPorNombre.keySet()) {
            Map<Integer, Boolean> copiaButacas = new HashMap<>(sectoresPorNombre.get(sector));
            copia.put(sector, copiaButacas);
        }
        return copia;
    }

    /**
     * No aplica para sedes numeradas, retorna null.
     *
     * @return null
     */
    @Override
    public Integer getDisponiblesInicialesSinNumerar() {
        return null;
    }

    /**
     * Devuelve la capacidad de un sector dado.
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
     * Devuelve el array de sectores disponibles en la sede.
     *
     * @return Array con los nombres de los sectores
     */
    @Override
    public String[] getSectores() {
        return sectores;
    }

    /**
     * Indica si la sede es numerada.
     *
     * @return true
     */
    @Override
    public boolean esNumerada() {
        return true;
    }

    /**
     * @return Cantidad de asientos por fila
     */
    public int getAsientosPorFila() {
        return asientosPorFila;
    }

    /**
     * @return Arreglo con las capacidades de cada sector
     */
    public int[] getCapacidad() {
        return capacidad;
    }

    /**
     * @return Arreglo con los porcentajes adicionales por sector
     */
    public int[] getPorcentajeAdicional() {
        return porcentajeAdicional;
    }

    /**
     * @return Mapa de sectores con sus asientos y disponibilidad
     */
    public Map<String, Map<Integer, Boolean>> getSectoresPorNombre() {
        return sectoresPorNombre;
    }
}
