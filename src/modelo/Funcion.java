package modelo;


import java.util.*;
public class Funcion {
     private Sede sede;
     private Fecha fecha;
     private double precioBase;
     private Map<String, Map<Integer, Boolean>> disponiblesNumerados;
     private Integer disponiblesSinNumerar;

     

     public Funcion(Sede sede, Fecha fecha, double precioBase) {
         if (sede == null || fecha == null || precioBase < 0) {
             throw new IllegalArgumentException("Valores inválidos en la función");
         }
         this.sede = sede;
         this.fecha = fecha;
         this.precioBase = precioBase;
         inicializarDisponibles(); 
         
     }
     
     public boolean verificarDisponibilidad(String sector, int asiento) {
    	 	Map<Integer, Boolean> asientosDelSector = disponiblesNumerados.get(sector);

    	    if (asientosDelSector == null) {
    	        throw new IllegalArgumentException("Sector no válido: " + sector);
    	    }

    	    if (asiento <= 0 || asiento >= Collections.max(asientosDelSector.keySet())) {
    	        throw new IllegalArgumentException("Asiento fuera de rango");
    	    }

    	    return asientosDelSector.get(asiento);
    	}

     
     public boolean verificarDisponibilidad( int cantidadSolicitada) {
    	    Integer cantidadDisponible = disponiblesSinNumerar;

    	    if (cantidadDisponible == null) return false;

    	    return cantidadSolicitada <= cantidadDisponible;
    	}

     public Map<Integer, Boolean> getDisponiblesSector(String sector) {
    	    return disponiblesNumerados.get(sector);
    	}

   
//     ARREGLAR
//     public int asientoDisponible(String sector) {
//         Map<String, Set<Integer>> mapa = (Map<String, Set<Integer>>) disponibles;
//         Set<Integer> asientos = mapa.get(sector);
//         
//         if (asientos == null || asientos.isEmpty()) {
//             throw new IllegalArgumentException("No hay asientos disponibles en el sector: " + sector);
//         }
//
//         return asientos.iterator().next();
//     }
     
//     public int[] asientoDisponible(String nombreSector) {
//    	    int[][] grilla = disponiblesNumerados.get(nombreSector);
//
//    	    if (grilla == null) {
//    	        throw new IllegalArgumentException("El sector no existe: " + nombreSector);
//    	    }
//
//    	    for (int fila = 0; fila < grilla.length; fila++) {
//    	        for (int asiento = 0; asiento < grilla[fila].length; asiento++) {
//    	            if (grilla[fila][asiento] == 0) {
//    	                return new int[] { fila, asiento };
//    	            }
//    	        }
//    	    }
//
//    	    throw new IllegalArgumentException("No hay asientos disponibles en el sector: " + nombreSector);
//    	}


     private void inicializarDisponibles() {
    	    if (sede.esNumerada()) {
    	        this.disponiblesNumerados = sede.getDisponiblesInicialesNumerados();
    	        this.disponiblesSinNumerar = null;
    	    } else {
    	        this.disponiblesSinNumerar = sede.getDisponiblesInicialesSinNumerar();
    	        this.disponiblesNumerados = null;
    	    }
    	}

    
     public boolean esFutura(){
    	 return fecha.esFutura();
    	 }

    	
     
   
     public Integer getDisponiblesSinNumerar() {
    	    return disponiblesSinNumerar;
    	}

       

     
     public double devolverPrecio(String sector) {
         return sede.calcularPrecioEntrada(precioBase, sector);
     }

     public Sede getSede() {
         return sede;
     }
     public boolean esNumerada() {
    	    return sede.esNumerada(); // le pregunta a la sede si es numerada
    	}

     public Fecha getFecha() {
         return fecha;
     }

     public double getPrecioBase() {
         return precioBase;
     }
     
     //PARA SEDES NUMERADAS
     public boolean venderAsiento(String sector, int asiento) {
    	 if (!esNumerada()) {
    		 throw new UnsupportedOperationException("Este método es solo para sedes numeradas.");
    	 }

    	 disponiblesNumerados.get(sector).put(asiento, false);
    	 return true;
    }

    public void sumarAsiento(String sector, int asiento) {
    	if (!esNumerada()) {
    		throw new UnsupportedOperationException("Este método es solo para sedes numeradas.");
    	}

    	disponiblesNumerados.get(sector).put(asiento, true);
    	    
    }
     
    	//PARA SEDES SIN NUMERAR
    	
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

    	public void sumarAsiento(int cantidad) {
    	    if (esNumerada()) return;

    	    Integer disponibles = disponiblesSinNumerar;
    	    if (disponibles != null) {
    	        disponiblesSinNumerar = disponibles + cantidad;
    	    }
    	}

    	
     
     @Override
     public String toString() {
         return "Función en " + sede.getNombre() + " el " + fecha.toString();
     }

}
