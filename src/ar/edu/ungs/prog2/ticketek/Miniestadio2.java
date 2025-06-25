package ar.edu.ungs.prog2.ticketek;

public class Miniestadio2 extends SedeNumerada {
    private int cantidadPuestos;
    private double precioConsumicion;

    public Miniestadio2(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
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
        return "Miniestadio {nombre='" + nombre + "', dirección='" + direccion + "', capacidadMaxima=" + capacidadMaxima +
               ", cantidadPuestos=" + cantidadPuestos + ", precioConsumicion=" + precioConsumicion + "}";
    }
}
