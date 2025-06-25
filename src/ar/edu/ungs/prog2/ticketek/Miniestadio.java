package ar.edu.ungs.prog2.ticketek;

public class Miniestadio extends SedeNumerada {
    private int cantidadPuestos;
    private double precioConsumicion;

    public Miniestadio(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
                       int cantidadPuestos, double precioConsumicion,
                       String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, "Miniestadio", capacidadMaxima, direccion, asientosPorFila, sectores, capacidad, porcentajeAdicional);
        this.cantidadPuestos = cantidadPuestos;
        this.precioConsumicion = precioConsumicion;
    }

    @Override
    public double calcularPrecioEntrada(double precioBase, String sector) {
        for (int i = 0; i < sectores.length; i++) {
            if (sectores[i].equals(sector)) {
                return precioBase + (precioBase * (porcentajeAdicional[i] / 100.0)) + precioConsumicion;
            }
        }
        throw new IllegalArgumentException("El sector no es válido para la sede");
    }

    @Override
    public String obtenerInformacionCompleta() {
        return "Miniestadio: " + nombre + " | Dirección: " + direccion + " | Capacidad máxima: " + capacidadMaxima;
    }

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
