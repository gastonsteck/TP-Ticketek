package ar.edu.ungs.prog2.ticketek;
import java.util.Iterator;
import java.util.*;

/**
 * Clase que representa una función de un espectáculo en una sede y fecha específicas.
 * Controla la disponibilidad y precio base de la función.
 *
 * IREP (Invariante de Representación):
 * - sede != null
 * - fecha != null
 * - precioBase >= 0.0
 * - Si sede.esNumerada() == true entonces:
 *   - disponiblesNumerados != null
 *   - disponiblesSinNumerar == null
 *   - Para todo sector en disponiblesNumerados.keySet(): sector != null
 *   - Para todo mapa de asientos en disponiblesNumerados.values(): mapa != null
 *   - Para todo número de asiento en cada mapa: número > 0
 * - Si sede.esNumerada() == false entonces:
 *   - disponiblesNumerados == null
 *   - disponiblesSinNumerar != null && disponiblesSinNumerar >= 0
 * - esNumerada() == sede.esNumerada() (consistencia)
 * - Los sectores en disponiblesNumerados deben corresponder a sectores válidos de la sede
 * - Las cantidades disponibles no pueden exceder las capacidades de la sede
 * - venderAsiento() y sumarAsiento() solo funcionan según el tipo de sede (numerada/no numerada)
 * - devolverPrecio(sector) == sede.calcularPrecioEntrada(precioBase, sector)
 */
public class Funcion {
    private Sede sede;
    private Fecha fecha;
    private double precioBase;
    private Map<String, Map<Integer, Boolean>> disponiblesNumerados;
    private Integer disponiblesSinNumerar;

    /**
     * Constructor que inicializa la función con sede, fecha y precio base.
     * 
     * @param sede       Sede donde se realiza la función
     * @param fecha      Fecha de la función
     * @param precioBase Precio base para calcular valor de entradas
     * @throws IllegalArgumentException si alguno de los parámetros es inválido
     */
    public Funcion(Sede sede, Fecha fecha, double precioBase) {
        if (sede == null || fecha == null || precioBase < 0) {
            throw new IllegalArgumentException("Valores inválidos en la función");
        }
        this.sede = sede;
        this.fecha = fecha;
        this.precioBase = precioBase;
        inicializarDisponibles();
    }

    /**
     * Verifica la disponibilidad de un asiento numerado en un sector dado.
     * 
     * @param sector Nombre del sector
     * @param asiento Número de asiento
     * @return true si el asiento está disponible, false si está ocupado
     * @throws IllegalArgumentException si el sector o asiento son inválidos
     */
    public boolean verificarDisponibilidad(String sector, int asiento) {
        Map<Integer, Boolean> asientosDelSector = disponiblesNumerados.get(sector);

        if (asientosDelSector == null) {
        	System.out.println("-------------------------------------------------------------------------------------------------");
            throw new IllegalArgumentException("Sector no válido: " + sector);
        }

        if (asiento <= 0 || asiento >= Collections.max(asientosDelSector.keySet())) {
        	System.out.println("-------------------------------------------------------------------------------------------------");
            throw new IllegalArgumentException("Asiento fuera de rango");
        }

        return asientosDelSector.get(asiento);
    }

    /**
     * Verifica la disponibilidad para una cantidad solicitada en sedes sin numerar.
     * 
     * @param cantidadSolicitada Cantidad de entradas solicitadas
     * @return true si hay suficientes entradas disponibles, false en caso contrario
     */
    public boolean verificarDisponibilidad(int cantidadSolicitada) {
        Integer cantidadDisponible = disponiblesSinNumerar;

        if (cantidadDisponible == null) return false;

        return cantidadSolicitada <= cantidadDisponible;
    }

    /**
     * Devuelve el mapa de asientos disponibles para un sector numerado.
     * 
     * @param sector Nombre del sector
     * @return Mapa con asiento -> disponibilidad (true=disponible)
     */
    public Map<Integer, Boolean> getDisponiblesSector(String sector) {
        return disponiblesNumerados.get(sector);
    }

    /**
     * Inicializa las disponibilidades de asientos según el tipo de sede.
     * Para sedes numeradas, copia los asientos numerados; para no numeradas,
     * inicializa la cantidad disponible.
     */
    private void inicializarDisponibles() {
        if (esNumerada()) {
            this.disponiblesNumerados = new HashMap<>();
            Map<String, Map<Integer, Boolean>> originales = sede.getDisponiblesInicialesNumerados();

            Iterator<Map.Entry<String, Map<Integer, Boolean>>> iteratorSectores = 
                originales.entrySet().iterator();
            
            while (iteratorSectores.hasNext()) {
                Map.Entry<String, Map<Integer, Boolean>> entrySector = iteratorSectores.next();
                String sector = entrySector.getKey();
                Map<Integer, Boolean> butacasOriginales = entrySector.getValue();

                Map<Integer, Boolean> copiaButacas = new HashMap<>();
                
                Iterator<Map.Entry<Integer, Boolean>> iteratorAsientos = 
                    butacasOriginales.entrySet().iterator();
                
                while (iteratorAsientos.hasNext()) {
                    Map.Entry<Integer, Boolean> entryAsiento = iteratorAsientos.next();
                    copiaButacas.put(entryAsiento.getKey(), entryAsiento.getValue());
                }

                disponiblesNumerados.put(sector, copiaButacas);
            }
            
            this.disponiblesSinNumerar = null;
        } else {
            this.disponiblesSinNumerar = sede.getDisponiblesInicialesSinNumerar();
            this.disponiblesNumerados = null;
        }
    }


    /**
     * Indica si la función es para una fecha futura.
     * 
     * @return true si la fecha es futura, false en caso contrario
     */
    public boolean esFutura() {
        return fecha.esFutura();
    }

    /**
     * Devuelve la cantidad de entradas disponibles para sedes sin numerar.
     * 
     * @return Cantidad disponible o null si es numerada
     */
    public Integer getDisponiblesSinNumerar() {
        return disponiblesSinNumerar;
    }

    /**
     * Calcula el precio de la entrada para un sector dado.
     * 
     * @param sector Nombre del sector
     * @return Precio calculado
     */
    public double devolverPrecio(String sector) {
        return sede.calcularPrecioEntrada(precioBase, sector);
    }

    /**
     * @return La sede donde se realiza la función
     */
    public Sede getSede() {
        return sede;
    }

    /**
     * Indica si la sede de la función es numerada.
     * 
     * @return true si es numerada, false si no
     */
    public boolean esNumerada() {
        return sede.esNumerada();
    }

    /**
     * @return La fecha de la función
     */
    public Fecha getFecha() {
        return fecha;
    }

    /**
     * @return Mapa con la disponibilidad de asientos numerados
     */
    public Map<String, Map<Integer, Boolean>> getDisponiblesNumerados() {
        return disponiblesNumerados;
    }

    /**
     * @return Precio base de la función
     */
    public double getPrecioBase() {
        return precioBase;
    }

    /**
     * Vende un asiento numerado, marcándolo como no disponible.
     * 
     * @param sector Nombre del sector
     * @param asiento Número de asiento
     * @return true si la venta fue exitosa
     * @throws UnsupportedOperationException si la sede no es numerada
     */
    public boolean venderAsiento(String sector, int asiento) {
        if (!esNumerada()) {
            throw new UnsupportedOperationException("Este método es solo para sedes numeradas.");
        }

        disponiblesNumerados.get(sector).put(asiento, false);
        return true;
    }

    /**
     * Suma la disponibilidad de un asiento numerado (lo libera).
     * 
     * @param sector Nombre del sector
     * @param asiento Número de asiento
     * @throws UnsupportedOperationException si la sede no es numerada
     */
    public void sumarAsiento(String sector, int asiento) {
        if (!esNumerada()) {
            throw new UnsupportedOperationException("Este método es solo para sedes numeradas.");
        }

        disponiblesNumerados.get(sector).put(asiento, true);
    }

    /**
     * Vende una cantidad de entradas en sedes sin numerar.
     * 
     * @param cantidad Cantidad de entradas a vender
     * @return true si hay disponibilidad y la venta fue exitosa
     * @throws UnsupportedOperationException si la sede es numerada
     */
    public boolean venderAsiento(int cantidad) {
        if (esNumerada()) {
            throw new UnsupportedOperationException("Este método es solo para sedes sin numeración.");
        }

        Integer disponibles = disponiblesSinNumerar;
        if (disponibles == null || disponibles < cantidad) {
            return false;
        }

        disponiblesSinNumerar = disponibles - cantidad;
        return true;
    }

    /**
     * Suma entradas disponibles para sedes sin numerar.
     * 
     * @param cantidad Cantidad a sumar
     */
    public void sumarAsiento(int cantidad) {
        if (esNumerada()) return;

        Integer disponibles = disponiblesSinNumerar;
        if (disponibles != null) {
            disponiblesSinNumerar = disponibles + cantidad;
        }
    }

    /**
     * Representación textual de la función.
     * 
     * @return String con sede y fecha
     */
    @Override
    public String toString() {
        return "Función en " + sede.getNombre() + " el " + fecha.toString();
    }
}
