package modelo;
import java.util.*;
public class Ticketek {
	private Map<String, Usuario> usuarios;				// la key es el email
    private Map<String, Espectaculo> espectaculos;		// la key es el codigo
    private Map<String, Sede> sedes;					// la key es el nombre
    
    public Ticketek() {
        this.usuarios = new HashMap<>();
        this.espectaculos = new HashMap<>();
        this.sedes = new HashMap<>();
    }
    
    public void registrarUsuario(String email, String nombreCompleto, String contraseña) {
        if (usuarios.containsKey(email)) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
        }
        Usuario nuevoUsuario = new Usuario(email, nombreCompleto, contraseña);
        usuarios.put(email, nuevoUsuario);
    } 
    
    public void registrarEstadio(String nombreSede, int capacidadMaxima, String direccion) {
        if (sedes.containsKey(nombreSede)) {
            throw new IllegalArgumentException("Ya existe una sede con ese nombre.");
        }
        Sede estadio = new Estadio(nombreSede, capacidadMaxima, direccion);
        sedes.put(nombreSede, estadio);
    }
    
    
	public void registrarTeatro(String nombreSede, int capacidadMaxima, String direccion, int cantidadFilas,
			int asientosPorFila) {
		if (sedes.containsKey(nombreSede)) {
			throw new IllegalArgumentException("Ya existe una sede con ese nombre.");
		}
		Sede teatro = new Teatro(nombreSede, capacidadMaxima, direccion, cantidadFilas, asientosPorFila);
		sedes.put(nombreSede, teatro);
	}

	public void registrarMiniestadio(String nombreSede, int capacidadMaxima, String direccion, int cantidadFilas,
			int asientosPorFila, int cantidadPuestosMerch, int cantidadPuestosComida, double valorFijoAdicional) {
		if (sedes.containsKey(nombreSede)) {
			throw new IllegalArgumentException("Ya existe una sede con ese nombre.");
		}
		Sede miniestadio = new Miniestadio(nombreSede, capacidadMaxima, direccion, cantidadFilas, asientosPorFila,
				cantidadPuestosMerch, cantidadPuestosComida, valorFijoAdicional);
		sedes.put(nombreSede, miniestadio);
	}
    
    public void registrarEspectaculo(String nombreEspectaculo) {
        if (espectaculos.containsKey(nombreEspectaculo)) {
            throw new IllegalArgumentException("Ya existe un espectáculo con ese nombre.");
        }
        Espectaculo nuevoEspectaculo = new Espectaculo(nombreEspectaculo);
        espectaculos.put(nombreEspectaculo, nuevoEspectaculo);
    }
    
    
    public void venderEntrada(String email, String codigoEspectaculo, String sede, List<Integer> asientos, String sector, String contrasenia) {
    	 // 1. Verificar que el usuario existe y la contraseña es correcta
        if (!usuarios.containsKey(email)) {
            throw new IllegalArgumentException("El usuario no existe en el sistema.");
        }
        
        Usuario usuario = usuarios.get(email);
        if (!usuario.verificarContrasenia(contrasenia)) {
            throw new IllegalArgumentException("La contraseña es incorrecta.");
        }
        
        // 2. Verificar que el espectáculo existe
        if (!espectaculos.containsKey(codigoEspectaculo)) {
            throw new IllegalArgumentException("El espectáculo no existe en el sistema.");
        }
        
        Espectaculo espectaculo = espectaculos.get(codigoEspectaculo);
        
        // 3. Verificar que la sede existe
        if (!sedes.containsKey(sede)) {
            throw new IllegalArgumentException("La sede no existe en el sistema.");
        }
        
        Sede lugarEvento = sedes.get(sede);
        
        // 4. Verificar que el espectáculo tenga una función en esa sede
        if (!espectaculo.getFunciones().containsKey(sede)) {
            System.out.println("Error: El espectáculo no tiene funciones programadas en esta sede.");
            return false;
        }
        
        Funcion funcion = espectaculo.getFunciones().get(sede);
        
        // 5. Verificar el tipo de sede para validar el sector
        if (lugarEvento instanceof Estadio) {
            // En estadios solo se vende campo general, ignorar el sector
            sector = "campo";
        } else if ((lugarEvento instanceof Teatro || lugarEvento instanceof Miniestadio) && sector == null) {
            System.out.println("Error: Debe especificar un sector para esta sede.");
            return false;
        }
        
        // 6. Verificar que los asientos estén disponibles
        for (Integer asiento : asientos) {
            if (!funcion.verificarDisponibilidad(sector, asiento)) {
                System.out.println("Error: El asiento " + asiento + " del sector " + sector + " no está disponible.");
                return false;
            }
        }
        
        // 7. Calcular el precio total
        float precioBase = funcion.devolverPrecio();
        float precioTotal = 0;
        
        for (Integer asiento : asientos) {
            float precioAsiento = lugarEvento.calcularPrecioEntrada(precioBase, sector);
            precioTotal += precioAsiento;
            
            // 8. Generar código único para la entrada
            String codigoEntrada = generarCodigoEntrada(email, codigoEspectaculo, sede, sector, asiento);
            
            // 9. Crear y registrar la entrada
            Entrada entrada = new Entrada(
                codigoEntrada,
                codigoEspectaculo,
                espectaculo.getNombre(),
                sede,
                funcion.getFecha(),
                sector,
                asiento,
                precioAsiento
            );
            
            // 10. Asignar entrada al usuario
            usuario.agregarEntrada(codigoEntrada, entrada);
            
            // 11. Marcar asiento como vendido
            funcion.venderAsiento(sector, asiento);
        }
        
        // 12. Sumar al total recaudado del espectáculo
        espectaculo.sumarRecaudado(precioTotal);
        
        // 13. Informar éxito
        System.out.println("Venta exitosa. Se han vendido " + asientos.size() + 
                          " entradas para el espectáculo " + espectaculo.getNombre() + 
                          " en la sede " + sede + " por un total de $" + precioTotal);
        
        return true;
        }
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
