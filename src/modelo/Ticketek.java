package modelo;
import java.util.*;
public class Ticketek {
	private Map<String, Usuario> usuariosPorEmail;
    private Map<String, Espectaculo> espectaculosPorNombre;
    private Map<String, Sede> sedesPorNombre;
    
    public Ticketek() {
        this.usuariosPorEmail = new HashMap<>();
        this.espectaculosPorNombre = new HashMap<>();
        this.sedesPorNombre = new HashMap<>();
    }
    
    public void registrarUsuario(String email, String nombreCompleto, String contraseña) {
        if (usuariosPorEmail.containsKey(email)) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
        }
        Usuario nuevoUsuario = new Usuario(email, nombreCompleto, contraseña);
        usuariosPorEmail.put(email, nuevoUsuario);
    } 
    
    public void registrarEstadio(String nombreSede, int capacidadMaxima, String direccion) {
        if (sedesPorNombre.containsKey(nombreSede)) {
            throw new IllegalArgumentException("Ya existe una sede con ese nombre.");
        }
        Sede estadio = new Estadio(nombreSede, capacidadMaxima, direccion);
        sedesPorNombre.put(nombreSede, estadio);
    }
    
    
    public void registrarTeatro(String nombreSede, int capacidadMaxima, String direccion,
            int cantidadFilas, int asientosPorFila) {
   if (sedesPorNombre.containsKey(nombreSede)) {
    throw new IllegalArgumentException("Ya existe una sede con ese nombre.");
   }
   Sede teatro = new Teatro(nombreSede, capacidadMaxima, direccion, cantidadFilas, asientosPorFila);
   sedesPorNombre.put(nombreSede, teatro);
 }

    public void registrarMiniestadio(String nombreSede, int capacidadMaxima, String direccion,
            int cantidadFilas, int asientosPorFila,
            int cantidadPuestosMerch, int cantidadPuestosComida,
            double valorFijoAdicional) {
if (sedesPorNombre.containsKey(nombreSede)) {
throw new IllegalArgumentException("Ya existe una sede con ese nombre.");
}
Sede miniestadio = new Miniestadio(nombreSede, capacidadMaxima, direccion,
                  cantidadFilas, asientosPorFila,
                  cantidadPuestosMerch, cantidadPuestosComida,
                  valorFijoAdicional);
sedesPorNombre.put(nombreSede, miniestadio);
}
    
    public void registrarEspectaculo(String nombreEspectaculo) {
        if (espectaculosPorNombre.containsKey(nombreEspectaculo)) {
            throw new IllegalArgumentException("Ya existe un espectáculo con ese nombre.");
        }
        Espectaculo nuevoEspectaculo = new Espectaculo(nombreEspectaculo);
        espectaculosPorNombre.put(nombreEspectaculo, nuevoEspectaculo);
    }
    
    
    public void agregarFuncionAEspectaculo(String nombreEspectaculo, Funcion nuevaFuncion) {
        Espectaculo espectaculo = espectaculosPorNombre.get(nombreEspectaculo);
        if (espectaculo == null) {
            throw new IllegalArgumentException("El espectáculo no existe.");
        }
        espectaculo.agregarFuncion(nuevaFuncion);
    }
    
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();

        resultado.append("===== LISTADO DE USUARIOS =====\n");
        for (Usuario usuarioActual : usuariosPorEmail.values()) {
            resultado.append(usuarioActual.toString()).append("\n");
        }

        resultado.append("\n===== LISTADO DE SEDES =====\n");
        for (Sede sedeActual : sedesPorNombre.values()) {
            resultado.append(sedeActual.toString()).append("\n");
        }

        resultado.append("\n===== LISTADO DE ESPECTÁCULOS =====\n");
        for (Espectaculo espectaculoActual : espectaculosPorNombre.values()) {
            resultado.append(espectaculoActual.toString()).append("\n");
        }

        return resultado.toString();
    }

}
