package modelo;

import java.util.HashMap;

import java.util.Map;
public class Usuario {
	// Clase que representa a un usuario del sistema Ticketek

	    private String email;
	    private String nombre;
	    private String apellido;
	    private String contrasenia;
	    private Map<String, Entrada> entradas;
	    
	    // Constructor que crea un usuario con su email, nombre y contraseña
	    public Usuario(String email, String nombre,String apellido, String contrasenia) {
	        this.email = email;
	        this.nombre = nombre;
	        this.apellido=apellido;
	        this.contrasenia = contrasenia;
	        this.entradas = new HashMap<>();
	    }
	    
	 // Verifica si la contraseña ingresada es la misma que la del usuario
	    public boolean verificarContrasenia(String contrasenia) {
	        return this.contrasenia.equals(contrasenia);
	    }
  
	 // Elimina una entrada según su código
	    public void reembolsarEntrada(String codigoEntrada) {
	        entradas.remove(codigoEntrada);
	    }
	    
	    
	 // Representación en texto del usuario
	    @Override
	    public String toString() {
	        return nombre + " " + apellido + " (" + email + ")";
	    }

}
