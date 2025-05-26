package modelo;

import java.util.HashMap;
import java.util.Map;

public class Miniestadio extends Sede {
	private int asientosPorFila;
	private String[] sectores;
	private int[] capacidad;
	private int[] porcentajeAdicional;
	private Map<String, int[][]> sectoresPorNombre; 
    private int cantidadPuestos;
    private double precioConsumicion;

    public Miniestadio(String nombre, String direccion, int capacidadMaxima,  int asientosPorFila,
                       int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, "Miniestadio", capacidadMaxima, direccion);
        this.asientosPorFila = asientosPorFila;
        this.cantidadPuestos = cantidadPuestos;
        this.sectoresPorNombre = new HashMap<String, int[][]>();
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
        this.precioConsumicion = precioConsumicion;
        crearSectores();
    }

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
        	precio += precioBase * (porcentajeAdicional[aux] / 100 );

        return precio + precioConsumicion;
    }

    @Override
    public String obtenerInformacionCompleta() {
        return "Miniestadio: " + nombre +
               " | Capacidad: " + capacidad +
               " | Dirección: " + direccion +
               " | Puestos de venta: " + cantidadPuestos +
               " | Precio de consumición: $" + precioConsumicion;
    }
    
    public void crearSectores() {
		for (int i = 0; i < sectores.length; i++) {
				int[][] nuevoSector;
        	if (capacidad[i] % asientosPorFila == 0) 
        		nuevoSector = crearSector(capacidad[i] / asientosPorFila, asientosPorFila);
        	else 
        		nuevoSector =crearSector(capacidad[i] / asientosPorFila, asientosPorFila, capacidad[i] % asientosPorFila);
        	sectoresPorNombre.put(sectores[i], nuevoSector);
        }
	}
	
	public int[][] crearSector(int cantFilas, int asientosPorFila) {
	    int[][] matriz = new int[cantFilas][asientosPorFila];
	    return matriz;
	}
	
	public int[][] crearSector(int cantFilas, int asientosPorFila, int cantUltimaFila) {
	    int[][] matriz = new int[cantFilas+1][asientosPorFila];
	    for (int i = 0; i < asientosPorFila; i++) {
	    	if (i >= cantUltimaFila)
	    		matriz[cantFilas-1][i] = -1;
	    }
	    return matriz;
	}
	
	@Override
	public int getCapacidadSector(String nombreSector) {
	    for (int i = 0; i < sectores.length; i++) {
	        if (sectores[i].equals(nombreSector)) {
	            return capacidad[i];
	        }
	    }
	    throw new IllegalArgumentException("Sector no válido: " + nombreSector);
	}

	
	
	public int getAsientosPorFila() {
		return asientosPorFila;
	}

	public String[] getSectores() {
		return sectores;
	}

	public int[] getCapacidad() {
		return capacidad;
	}

	public int[] getPorcentajeAdicional() {
		return porcentajeAdicional;
	}
	

	public Map<String, int[][]> getSectoresPorNombre() {
		return sectoresPorNombre;
	}

	public int getCantidadPuestos() {
		return cantidadPuestos;
	}
	
	public double getPrecioConsumicion(){
		return precioConsumicion;
	}
	
	
	@Override
	public Map<String, int[][]> getDisponiblesInicialesNumerados() {
	    return this.sectoresPorNombre;
	}

	@Override
	public Integer getDisponiblesInicialesSinNumerar() {
	    return null;
	}


}
