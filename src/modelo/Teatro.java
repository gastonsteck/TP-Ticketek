package modelo;

import java.util.HashMap;
import java.util.Map;

public class Teatro extends Sede {
	  private int asientosPorFila;
	  private String[] sectores;
	  private int[] capacidad;
	  private int[] porcentajeAdicional;
	  private Map<String, int[][]> sectoresPorNombre; 
	  
	  
	  
	public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
	        super(nombre, "Teatro", capacidadMaxima, direccion);
	        this.asientosPorFila = asientosPorFila;
	        this.sectores = sectores;
	        this.capacidad = capacidad;
	        this.porcentajeAdicional = porcentajeAdicional;
	        this.sectoresPorNombre = new HashMap<String, int[][]>();
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

        return precio;
    }
	@Override
	public String obtenerInformacionCompleta() {
	    return "Teatro: " + nombre +
	           " | Capacidad: " + capacidad +
	           " | Dirección: " + direccion;
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
	
	@Override
	public Object getDisponiblesIniciales() {
	    return this.sectoresPorNombre; 
	}

	public Map<String, int[][]> getSectoresPorNombre() {
		return sectoresPorNombre;
	}
	
	
	
}
