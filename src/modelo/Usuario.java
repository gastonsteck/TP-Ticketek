package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Usuario {
	// Clase que representa a un usuario del sistema Ticketek

	    private String email;
	    private String nombreCompleto;
	    private String contrasenia;
	    private Map<String, Entrada> entradas;
	    
	    // Constructor que crea un usuario con su email, nombre y contraseña
	    public Usuario(String email, String nombreCompleto, String contrasenia) {
	        this.email = email;
	        this.nombreCompleto = nombreCompleto;
	        this.contrasenia = contrasenia;
	        this.entradas = new HashMap<>();
	    }
	    
	 // Verifica si la contraseña ingresada es la misma que la del usuario
	    public boolean verificarContrasenia(String contrasenia) {
	        return this.contrasenia.equals(contrasenia);
	    }
	    
	    public void obtenerEntrada(String codigoEntrada, Entrada entrada) {
			entradas.put(codigoEntrada, entrada);
		}
	    
	    
	 // Elimina una entrada según su código
	    public void reembolsarEntrada(String codigoEntrada) {
	        entradas.remove(codigoEntrada);
	    }
	   
	    public List<Entrada> listarEntradas() {
	        return new ArrayList<>(entradas.values());
		}
	    
	    public List<Entrada> listarEntradasFuturas() {
			List<Entrada> lista = new ArrayList<>();
			for (Entrada entrada : listarEntradas()) {
			    if (entrada.esFutura())
			    	lista.add(entrada);
			}

			return lista;
		}

	    
	 // Representación en texto del usuario
	    @Override
	    public String toString() {
	        return nombreCompleto + " (" + email + ")";
	    }

		public String getNombreCompleto() {
			return nombreCompleto;
		}


		public Map<String, Entrada> getEntradas() {
			return entradas;
		}

		public Entrada getEntrada(String codigoEntrada) {
			return entradas.get(codigoEntrada);
		}


		
		

		
}
