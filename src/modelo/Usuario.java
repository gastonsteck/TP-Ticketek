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
  
	 // Elimina una entrada según su código
	    public void reembolsarEntrada(String codigoEntrada) {
	        entradas.remove(codigoEntrada);
	    }
	    
	    
	 // Representación en texto del usuario
	    @Override
	    public String toString() {
	        return nombreCompleto + " (" + email + ")";
	    }
}
