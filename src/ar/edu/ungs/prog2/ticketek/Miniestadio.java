package ar.edu.ungs.prog2.ticketek;

/**
 * Clase que representa una sede de tipo Miniestadio.
 * Extiende de {@link SedeNumerada}, gestionando sectores numerados y agregando un costo fijo por consumición.
 *
 * Además de los sectores con porcentajes adicionales, los miniestadios incluyen puestos de venta
 * y un valor fijo de consumición que se suma al precio de la entrada.
 *
 * IREP (Invariante de Representación):
 * - Hereda el invariante de {@link SedeNumerada}
 * - cantidadPuestos >= 0
 * - precioConsumicion >= 0
 */
public class Miniestadio extends SedeNumerada {
    private int cantidadPuestos;
    private double precioConsumicion;

    /**
     * Crea una nueva sede Miniestadio.
     *
     * @param nombre               Nombre del miniestadio
     * @param direccion            Dirección física del miniestadio
     * @param capacidadMaxima      Capacidad total (suma de todos los sectores)
     * @param asientosPorFila      Cantidad de asientos por fila
     * @param cantidadPuestos      Cantidad de puestos de venta o consumo
     * @param precioConsumicion    Precio fijo adicional por consumición
     * @param sectores             Array de nombres de sectores
     * @param capacidad            Capacidad por sector
     * @param porcentajeAdicional  Porcentaje adicional de precio por sector
     * @throws IllegalArgumentException si hay inconsistencias en los datos según el IREP
     */
    public Miniestadio(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
                       int cantidadPuestos, double precioConsumicion,
                       String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, "Miniestadio", capacidadMaxima, direccion, asientosPorFila, sectores, capacidad, porcentajeAdicional);

        chequeosConstructor(cantidadPuestos, precioConsumicion, sectores, porcentajeAdicional);
        this.cantidadPuestos = cantidadPuestos;
        this.precioConsumicion = precioConsumicion;
    }
    
    
    /**
     * Realiza los chequeos necesarios para validar los parámetros del constructor de Miniestadio.
     *
     * @param cantidadPuestos     Cantidad de puestos de venta
     * @param precioConsumicion   Precio fijo adicional por consumición
     * @param sectores            Array con los nombres de los sectores
     * @param porcentajeAdicional Array con los porcentajes adicionales por sector
     * @throws IllegalArgumentException si algún parámetro es inválido según el IREP
     */
    private void chequeosConstructor(int cantidadPuestos, double precioConsumicion,
                                     String[] sectores, int[] porcentajeAdicional) {

        if (porcentajeAdicional == null || sectores == null || porcentajeAdicional.length != sectores.length) {
            throw new IllegalArgumentException("La cantidad de porcentajes adicionales debe ser igual a la cantidad de sectores");
        }

        if (cantidadPuestos < 0) {
            throw new IllegalArgumentException("La cantidad de puestos no puede ser negativa");
        }

        if (precioConsumicion < 0) {
            throw new IllegalArgumentException("El valor fijo no puede ser negativo");
        }
    }


    /**
     * Calcula el precio de una entrada sumando:
     * - El precio base
     * - El porcentaje adicional por sector
     * - El costo fijo de consumición
     *
     * @param precioBase Precio base de la entrada
     * @param sector     Nombre del sector
     * @return Precio total de la entrada para ese sector
     * @throws IllegalArgumentException si el sector no existe
     */
    @Override
    public double calcularPrecioEntrada(double precioBase, String sector) {
        for (int i = 0; i < this.getSectores().length; i++) {
            if (this.getSectores()[i].equals(sector)) {
                return precioBase + (precioBase * (this.getPorcentajeAdicional()[i] / 100.0)) + precioConsumicion;
            }
        }
        throw new IllegalArgumentException("El sector no es válido para la sede");
    }

    /**
     * Devuelve información resumida de la sede.
     *
     * @return Descripción corta con nombre, dirección y capacidad.
     */
    @Override
    public String obtenerInformacionCompleta() {
        return "Sede: " + this.getNombre() + " | Dirección: " + this.getDireccion() + " | Capacidad máxima: " + this.getCapacidadMaxima();
    }

    /**
     * Devuelve una descripción detallada del miniestadio, incluyendo puestos de venta y consumición.
     *
     * @return String con todos los datos relevantes del miniestadio.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sede: ").append(this.getNombre())
          .append(" | Dirección: ").append(this.getDireccion())
          .append(" | Capacidad máxima: ").append(this.getCapacidadMaxima())
          .append(" | Asientos/fila: ").append(this.getAsientosPorFila())
          .append(" | Puestos de venta: ").append(cantidadPuestos)
          .append(" | Precio consumición: $").append(precioConsumicion)
          .append("\n  Sectores:\n");

        for (int i = 0; i < this.getSectores().length; i++) {
            sb.append("    - ").append(this.getSectores()[i])
              .append(" | Capacidad: ").append(this.getCapacidad()[i])
              .append(" | % adicional: ").append(this.getPorcentajeAdicional()[i])
              .append("\n");
        }
        return sb.toString();
    }
}
