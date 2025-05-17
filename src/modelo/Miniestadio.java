package modelo;

public class Miniestadio extends Sede {
    private int cantFilas;
    private int asientosPorFila;
    private int cantPuestosMerch;
    private int cantPuestosComida;
    private double valorFijo;

    public Miniestadio(String nombre, int capacidad, String direccion,
                       int cantFilas, int asientosPorFila,
                       int cantPuestosMerch, int cantPuestosComida, double valorFijo) {
        super(nombre, "Miniestadio", capacidad, direccion);
        this.cantFilas = cantFilas;
        this.asientosPorFila = asientosPorFila;
        this.cantPuestosMerch = cantPuestosMerch;
        this.cantPuestosComida = cantPuestosComida;
        this.valorFijo = valorFijo;
    }

    @Override
    public double calcularPrecioEntrada(double precioBase, String sector) {
        double precio = precioBase;

        if (sector.equals("Platea VIP")) {
            precio *= 1.70;
        } else if (sector.equals("Platea común")) {
            precio *= 1.40;
        } else if (sector.equals("Platea baja")) {
            precio *= 1.50;
        } else if (sector.equals("Platea alta")) {
            // sin incremento
        }

        return precio + valorFijo;
    }

    @Override
    public String obtenerInformacionCompleta() {
        return "Miniestadio: " + nombre +
               " | Capacidad: " + capacidad +
               " | Dirección: " + direccion +
               " | Puestos de merchandising: " + cantPuestosMerch +
               " | Pustos de comida: " + cantPuestosComida +
               " | Valor fijo: $" + valorFijo;
    }
    
    public int calcularAsientosPorSector() {
		return this.cantFilas * this.asientosPorFila;
	}
}
