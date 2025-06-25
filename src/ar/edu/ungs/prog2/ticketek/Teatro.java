package ar.edu.ungs.prog2.ticketek;

public class Teatro extends SedeNumerada {

    public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
                  String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, "Teatro", capacidadMaxima, direccion, asientosPorFila, sectores, capacidad, porcentajeAdicional);
    }

    @Override
    public double calcularPrecioEntrada(double precioBase, String sector) {
        for (int i = 0; i < sectores.length; i++) {
            if (sectores[i].equals(sector)) {
                return precioBase + precioBase * (porcentajeAdicional[i] / 100.0);
            }
        }
        throw new IllegalArgumentException("El sector no es válido para la sede");
    }

    @Override
    public String obtenerInformacionCompleta() {
        return "Teatro: " + nombre + " | Dirección: " + direccion + " | Capacidad máxima: " + capacidadMaxima;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Teatro: ").append(nombre)
          .append(" | Dirección: ").append(direccion)
          .append(" | Capacidad máxima: ").append(capacidadMaxima)
          .append(" | Asientos/fila: ").append(asientosPorFila)
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
