package modelo;
import java.util.*;
public class Funcion {
     private Sede sede;
     private Fecha fecha;
     private double precioBase;
     private Map<String, Set<Integer>> disponibles; // sector → asientos disponibles

     public Funcion(Sede sede, Fecha fecha, double precioBase) {
         if (sede == null || fecha == null || precioBase < 0) {
             throw new IllegalArgumentException("Valores inválidos en la función");
         }
         this.sede = sede;
         this.fecha = fecha;
         this.precioBase = precioBase;
         this.disponibles = new HashMap<>();
     }
     public boolean verificarDisponibilidad(String sector, int asiento) {
         Set<Integer> asientos = disponibles.get(sector);
         return asientos != null && asientos.contains(asiento);
     }
     
     public int asientoDisponible(String sector){
    	 return disponibles.get(sector).iterator().next();
     }
     
     public boolean venderAsiento(String sector, int asiento) {
         if (verificarDisponibilidad(sector, asiento)) {
             disponibles.get(sector).remove(asiento);
             return true;
         }
         return false;
     }
     
     public void sumarAsiento(String sector, int asiento) {
    	    if (asiento <= 0) return;

    	    // Si el sector no existe en el mapa, lo agregamos con un nuevo HashSet
    	    if (!disponibles.containsKey(sector)) {
    	        disponibles.put(sector, new HashSet<>());
    	    }

    	    // Ahora que seguro existe, agregamos el asiento
    	    disponibles.get(sector).add(asiento);
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
     
     @Override
     public String toString() {
         return "Función en " + sede.getNombre() + " el " + fecha.toString();
     }

}
