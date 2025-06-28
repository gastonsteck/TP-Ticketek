package ar.edu.ungs.prog2.ticketek;

/**
 * Clase que representa una sede de tipo Teatro.
 * Extiende de {@link SedeNumerada}, por lo tanto gestiona sectores y asientos numerados.
 *
 * Los teatros permiten configurar múltiples sectores, cada uno con una capacidad y un porcentaje adicional de precio.
 *
 * IREP (Invariante de Representación):
 * - Hereda el invariante de {@link SedeNumerada}.
 * - El tipo de sede es siempre "Teatro".
 */
public class Teatro extends SedeNumerada {

    /**
     * Crea una nueva sede de tipo Teatro.
     *
     * @param nombre               Nombre del teatro
     * @param direccion            Dirección física del teatro
     * @param capacidadMaxima      Capacidad total del teatro (suma de todos los sectores)
     * @param asientosPorFila      Cantidad de asientos por fila
     * @param sectores             Array de nombres de los sectores
     * @param capacidad            Array de capacidades de cada sector
     * @param porcentajeAdicional  Array de porcentajes adicionales de precio por sector
     * @throws IllegalArgumentException si alguno de los parámetros no cumple con el IREP de {@link SedeNumerada}
     */
    public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
                  String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, "Teatro", capacidadMaxima, direccion, asientosPorFila, sectores, capacidad, porcentajeAdicional);
    }

    /**
     * Calcula el precio final de una entrada para un sector del teatro.
     * Aplica un porcentaje adicional al precio base según el sector.
     *
     * @param precioBase Precio base de la entrada
     * @param sector     Nombre del sector
     * @return Precio final de la entrada en el sector indicado
     * @throws IllegalArgumentException si el sector no existe en el teatro
     */
    @Override
    public double calcularPrecioEntrada(double precioBase, String sector) {
        for (int i = 0; i < this.getSectores().length; i++) {
            if (this.getSectores()[i].equals(sector)) {
                return precioBase + precioBase * (this.getPorcentajeAdicional()[i] / 100.0);
            }
        }
        throw new IllegalArgumentException("El sector no es válido para la sede");
    }

    /**
     * Devuelve una descripción básica y legible del teatro, incluyendo nombre, dirección y capacidad.
     *
     * @return String con información básica del teatro.
     */
    @Override
    public String obtenerInformacionCompleta() {
        return "Sede: " + this.getNombre() + " | Dirección: " + this.getDireccion() + " | Capacidad máxima: " + this.getCapacidadMaxima();
    }

    /**
     * Devuelve un string con toda la información detallada del teatro, incluyendo todos los sectores.
     *
     * @return Información detallada del teatro en formato legible.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sede: ").append(this.getNombre())
          .append(" | Dirección: ").append(this.getDireccion())
          .append(" | Capacidad máxima: ").append(this.getCapacidadMaxima())
          .append(" | Asientos/fila: ").append(this.getAsientosPorFila())
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
