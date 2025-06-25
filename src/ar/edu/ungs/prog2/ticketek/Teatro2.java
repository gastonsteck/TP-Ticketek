package ar.edu.ungs.prog2.ticketek;

public class Teatro2 extends SedeNumerada {

    public Teatro2(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
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
        return "Teatro {nombre='" + nombre + "', dirección='" + direccion + "', capacidadMaxima=" + capacidadMaxima + "}";
    }
}
