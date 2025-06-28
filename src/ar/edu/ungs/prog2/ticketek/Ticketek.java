package ar.edu.ungs.prog2.ticketek;
import java.time.DateTimeException;
import java.util.*;
import java.util.Iterator;

/**
 * Clase principal del sistema de gestión de entradas para espectáculos.
 * Permite administrar usuarios, sedes y espectáculos, así como gestionar
 * la venta y administración de entradas.
 *
 * IREP (Invariante de Representación):
 * - usuarios != null
 * - sedes != null
 * - espectaculos != null
 * - Para todo email en usuarios.keySet(): email != null && !email.isEmpty()
 * - Para todo nombre en sedes.keySet(): nombre != null && !nombre.isEmpty()
 * - Para todo nombre en espectaculos.keySet(): nombre != null && !nombre.isEmpty()
 * - No existen emails de usuarios duplicados (garantizado por HashMap)
 * - No existen nombres de sedes duplicados (garantizado por HashMap)
 * - No existen nombres de espectáculos duplicados (garantizado por HashMap)
 * - Para todo usuario en usuarios.values(): usuario != null
 * - Para todo sede en sedes.values(): sede != null
 * - Para todo espectaculo en espectaculos.values(): espectaculo != null
 * - autenticarUsuario(email, contraseña) es consistente con usuarios.get(email).verificarContrasenia()
 * - Las funciones de espectáculos no pueden solaparse en la misma sede y fecha
 * - Todas las entradas vendidas deben estar asociadas a usuarios y funciones existentes
 */
public class Ticketek implements ITicketek {
    private Map<String, Usuario> usuarios;
    private Map<String, Sede> sedes;
    private Map<String, Espectaculo> espectaculos;

    /**
     * Constructor que inicializa un nuevo sistema Ticketek.
     */
    public Ticketek() {
        this.usuarios = new HashMap<>();
        this.espectaculos = new HashMap<>();
        this.sedes = new HashMap<>();
    }

    /**
     * Registra un nuevo estadio en el sistema.
     * 
     * @param nombre Nombre del estadio.
     * @param direccion Dirección del estadio.
     * @param capacidadMaxima Capacidad total del estadio.
     * @throws IllegalArgumentException Si el nombre ya existe, es nulo o vacío,
     *         si la capacidad es menor o igual a cero, o si la dirección es nula o vacía.
     */
    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
        if (sedes.containsKey(nombre)) {
            throw new IllegalArgumentException("Ya existe una sede con el nombre: " + nombre);
        }
        Estadio estadio = new Estadio(nombre, capacidadMaxima, direccion);
        sedes.put(nombre, estadio);
	}
  
    /**
     * Registra un nuevo teatro en el sistema.
     * 
     * @param nombre Nombre del teatro.
     * @param direccion Dirección del teatro.
     * @param capacidadMaxima Capacidad total del teatro.
     * @param asientosPorFila Cantidad de asientos por fila.
     * @param sectores Array con los nombres de los sectores.
     * @param capacidad Array con la capacidad de cada sector.
     * @param porcentajeAdicional Array con los porcentajes adicionales por sector.
     * @throws IllegalArgumentException Si el nombre ya existe, alguno de los parámetros es inválido,
     *         o si la suma de las capacidades de los sectores no coincide con la capacidad máxima.
     */
    @Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
    	  
           if (sedes.containsKey(nombre)) {
               throw new IllegalArgumentException("Ya existe una sede con el nombre: " + nombre);
           }

           Teatro teatro = new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila,
       			sectores, capacidad, porcentajeAdicional);
           sedes.put(nombre, teatro);
	}
    
    /**
     * Registra un nuevo miniestadio en el sistema.
     * 
     * @param nombre Nombre del miniestadio.
     * @param direccion Dirección del miniestadio.
     * @param capacidadMaxima Capacidad total del miniestadio.
     * @param asientosPorFila Cantidad de asientos por fila.
     * @param cantidadPuestos Cantidad de puestos (comida o merchandising).
     * @param precioConsumicion Precio fijo de consumición.
     * @param sectores Array con los nombres de los sectores.
     * @param capacidad Array con la capacidad de cada sector.
     * @param porcentajeAdicional Array con los porcentajes adicionales por sector.
     * @throws IllegalArgumentException Si el nombre ya existe, alguno de los parámetros es inválido,
     *         o si la suma de las capacidades de los sectores no coincide con la capacidad máxima.
     */
    @Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad,
			int[] porcentajeAdicional) {
    	
        if (sedes.containsKey(nombre)) {
            throw new IllegalArgumentException("Ya existe una sede con el nombre: " + nombre);
        }

        Miniestadio miniestadio = new Miniestadio(nombre, direccion, capacidadMaxima, asientosPorFila, cantidadPuestos,
        							precioConsumicion, sectores, capacidad, porcentajeAdicional);
        sedes.put(nombre, miniestadio);
		
	}

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param email Email único del usuario.
     * @param nombre Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param contrasenia Contraseña del usuario.
     * @throws IllegalArgumentException Si el email ya existe o alguno de los parámetros es nulo o vacío.
     */
    @Override
	public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {
    	
        if (usuarios.containsKey(email)) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + email);
        }

        Usuario usuario = new Usuario(email, nombre, apellido, contrasenia);
        usuarios.put(email, usuario);
		
	}
   

    /**
     * Registra un nuevo espectáculo en el sistema.
     * 
     * @param nombre Nombre del espectáculo.
     */
    @Override
	public void registrarEspectaculo(String nombre) {
    	if(!(espectaculos.get(nombre) == null))
    		throw new IllegalArgumentException("El espectáculo ya existe");
		espectaculos.put(nombre, new Espectaculo(nombre));
	}
    
    /**
     * Agrega una función a un espectáculo ya registrado.
     * 
     * @param nombreEspectaculo Nombre del espectáculo.
     * @param fechaStr Fecha de la función en formato dd/mm/YY.
     * @param nombreSede Nombre de la sede donde se realizará la función.
     * @param precioBase Precio base de la función.
     * @throws IllegalArgumentException Si el espectáculo o la sede no existen,
     *         o si ya hay una función programada en esa sede para la fecha dada.
     */
    @Override
	public void agregarFuncion(String nombreEspectaculo, String fechaStr, String nombreSede, double precioBase) {
		try {
			Fecha.desdeString(fechaStr);
		} catch (DateTimeException e) {
			System.err.println("Fecha inválida: " + fechaStr + ". No se agregó la función.");
	        return;
		}
		
		Fecha fecha = Fecha.desdeString(fechaStr);

		Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
		if (espectaculo == null) {
			throw new IllegalArgumentException("Espectáculo no encontrado: " + nombreEspectaculo);
		}

		Sede sede = sedes.get(nombreSede);
		if (sede == null) {
			throw new IllegalArgumentException("Sede no encontrada: " + nombreSede);
		}

		if (!verificarDisponibilidad(nombreSede, fecha)) {
			throw new IllegalArgumentException(
					"Ya hay una función programada en la sede " + nombreSede + " para la fecha " + fechaStr);
		}

		espectaculo.agregarFuncion(fecha, sede, precioBase);
	}
    

    /**
     * Vende entradas sin numerar (sector "Campo") para un espectáculo en una función específica.
     * 
     * @param nombreEspectaculo Nombre del espectáculo.
     * @param fecha Fecha de la función en formato String.
     * @param email Email del usuario que realiza la compra.
     * @param contrasenia Contraseña del usuario para autenticación.
     * @param cantidadEntradas Cantidad de entradas a comprar.
     * @return Lista de entradas vendidas.
     * @throws IllegalArgumentException Si algún parámetro es inválido,
     *         si el usuario no se autentica correctamente,
     *         si el espectáculo o la función no existen,
     *         si la función ya ocurrió,
     *         o si no hay suficientes entradas disponibles.
     */
    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
                                         int cantidadEntradas) {
    	try {
            chequeosVenta(nombreEspectaculo, fecha, email, contrasenia);
            
            Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
            Funcion funcion = espectaculo.getFuncion(fecha);
            Usuario usuario = usuarios.get(email);
            
            
            if (!funcion.verificarDisponibilidad(cantidadEntradas)) {
                throw new IllegalArgumentException("Solo hay " + funcion.getDisponiblesSinNumerar() + " entradas disponibles.");
            }

            String sector = "Campo";
            Fecha fechaObj = Fecha.desdeString(fecha);
            String nombreSede = funcion.getSede().getNombre();
            double precio = funcion.devolverPrecio(sector);
            List<IEntrada> listaEntradas = new ArrayList<>();

            for (int i = 0; i < cantidadEntradas; i++) {
                Entrada entrada = new Entrada(nombreEspectaculo, nombreSede, fechaObj, precio, email);
                usuario.comprarEntrada(entrada.devolverCodigo(), entrada);
                listaEntradas.add(entrada);
                espectaculo.agregarRecaudacion(nombreSede, precio);        
            }

            funcion.venderAsiento(cantidadEntradas);

            return listaEntradas;
        } catch (RuntimeException e) {
            System.err.println("Error al vender entrada: " + e.getMessage());
            throw e;
        }
    }


    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {
    	try {
    		
    		chequeosVenta(nombreEspectaculo, fecha, email, contrasenia);
            
            Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
            Funcion funcion = espectaculo.getFuncion(fecha);
            Usuario usuario = usuarios.get(email);
            
            for (int asiento : asientos) {
                if (!funcion.verificarDisponibilidad(sector, asiento)) {
                    throw new IllegalArgumentException("El asiento " + asiento + " del sector " + sector + " no está disponible.");
                }
            }

            Fecha fechaObj = Fecha.desdeString(fecha);
            Sede sede = funcion.getSede();
            int asientosPorFila = (sede instanceof Teatro)
                    ? ((Teatro) sede).getAsientosPorFila()
                    : ((Miniestadio) sede).getAsientosPorFila();

            String nombreSede = sede.getNombre();
            double precio = funcion.devolverPrecio(sector);
            
            List<IEntrada> listaEntradas = new ArrayList<>();

            for (int asiento : asientos) {
                int fila = (asiento - 1) / asientosPorFila + 1;
                Entrada entrada = new Entrada(nombreEspectaculo, nombreSede, fechaObj, sector, fila, asiento, precio, email);
                usuario.comprarEntrada(entrada.devolverCodigo(), entrada);
                listaEntradas.add(entrada);
                funcion.venderAsiento(sector, asiento);
                espectaculo.agregarRecaudacion(nombreSede, precio);
            }

            return listaEntradas;

        } catch (IllegalArgumentException e) {
            System.err.println("Error al vender entrada: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Lista todas las funciones de un espectáculo.
     * Para sedes sin numerar, muestra la cantidad de entradas vendidas y la capacidad.
     * Para sedes numeradas, indica que es una sede numerada.
     * 
     * @param nombreEspectaculo Nombre del espectáculo.
     * @return Cadena con la lista de funciones o mensaje si no se encuentra el espectáculo.
     */
    
    @Override
    public String listarFunciones(String nombreEspectaculo) {
        Espectaculo espectaculoBuscado = espectaculos.get(nombreEspectaculo);

        if (espectaculoBuscado == null) {
            return "Espectáculo no encontrado.";
        }

        StringBuilder resultado = new StringBuilder();
      
        Iterator<Funcion> iterator = espectaculoBuscado.getFunciones().values().iterator();

        while (iterator.hasNext()) {
            Funcion funcion = iterator.next();
            Sede sede = funcion.getSede();
            String nombreSede = sede.getNombre();
            String fecha = funcion.getFecha().toString();

            resultado.append(" - (")
                    .append(fecha)
                    .append(") ")
                    .append(nombreSede)
                    .append(" - ");

            if (!sede.esNumerada()) {
                String sector = "Campo";
                int capacidad = sede.getCapacidadSector(sector);
                int disponibles = funcion.getDisponiblesSinNumerar();
                int vendidas = capacidad - disponibles;
                resultado.append(vendidas).append("/").append(capacidad);
            } else {
                Map<String, Map<Integer, Boolean>> sectoresDisponibles = funcion.getDisponiblesNumerados();
                if (sectoresDisponibles != null) {
                    boolean primero = true;
                    for (String sector : sede.getSectores()) {
                        int capacidadSector = sede.getCapacidadSector(sector);
                        Map<Integer, Boolean> asientosSector = sectoresDisponibles.get(sector);
                        int disponibles = 0;

                        if (asientosSector != null && !asientosSector.isEmpty()) {
                            for (boolean disponible : asientosSector.values()) {
                                if (disponible) disponibles++;
                            }
                        } else {
                            disponibles = capacidadSector;
                        }

                        int vendidas = capacidadSector - disponibles;

                        if (!primero) {
                            resultado.append(" | ");
                        } else {
                            primero = false;
                        }
                        resultado.append(sector).append(": ").append(vendidas).append("/").append(capacidadSector);
                    }
                }
            }

            resultado.append("\n");
        }

        return resultado.toString();
    }



	
    /**
     * Lista las entradas futuras de un usuario autenticado.
     *
     * @param email       el correo electrónico del usuario
     * @param contrasenia la contraseña del usuario
     * @return una lista de entradas futuras del usuario como objetos {@link IEntrada}
     * @throws IllegalArgumentException si el email es nulo o vacío,
     *         o si no existe un usuario con el email proporcionado
     */
    @Override
	public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
		if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (!usuarios.containsKey(email)) {
            throw new IllegalArgumentException("No existe un usuario con el email: " + email);
        }
        
        Usuario usuario = usuarios.get(email);
        
        List<Entrada> listaEntradas = usuario.listarEntradasFuturas();
        List<IEntrada> listaIEntradas = new ArrayList<>();

        for (Entrada entrada : listaEntradas) {
            listaIEntradas.add(entrada);
        }
		return listaIEntradas;
	}
	
    /**
     * Lista todas las entradas asociadas a un usuario autenticado.
     *
     * @param email       el correo electrónico del usuario
     * @param contrasenia la contraseña del usuario
     * @return una lista de todas las entradas del usuario como objetos {@link IEntrada}
     * @throws IllegalArgumentException si el email es nulo o vacío,
     *         o si no existe un usuario con el email proporcionado
     */
    @Override
	public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
		if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (!usuarios.containsKey(email)) {
            throw new IllegalArgumentException("No existe un usuario con el email: " + email);
        }
        
        Usuario usuario = usuarios.get(email);
        List<Entrada> listaEntradas = usuario.listarEntradas();
        List<IEntrada> listaIEntradas = new ArrayList<>();

        for (Entrada entrada : listaEntradas) {
            listaIEntradas.add(entrada);
        }
		return listaIEntradas;
	}
	
    /**
     * Anula una entrada si el usuario y la contraseña son válidos y la entrada es para una función futura.
     *
     * Este método realiza varias validaciones:
     * - Verifica que el usuario exista en el sistema.
     * - Verifica que la contraseña proporcionada coincida con la del usuario.
     * - Confirma que la entrada sea para una fecha futura (no se anulan entradas pasadas).
     * - Restaura la disponibilidad del asiento en la función correspondiente.
     * - Remueve la entrada del registro de entradas del usuario.
     *
     * @param entrada      La entrada a anular.
     * @param contrasenia  La contraseña del usuario para validar la operación.
     * @return {@code true} si la entrada fue anulada correctamente; {@code false} si la entrada ya no puede anularse (por ejemplo, si ya pasó la fecha del evento).
     * @throws RuntimeException si el usuario no existe, la contraseña es incorrecta, o el espectáculo asociado no se encuentra.
     */
    @Override
	public boolean anularEntrada(IEntrada entrada, String contrasenia) {
	    
    	if (!chequeosAnular(entrada, contrasenia))
    		throw new RuntimeException("El espectáculo ya tuvo lugar.");
    	Entrada e = (Entrada) entrada;
        Usuario usuario = usuarios.get(e.getEmailUsuario());
        Espectaculo espectaculo = espectaculos.get(e.getNombreEspectaculo());
        Funcion funcion = espectaculo.getFuncion(e.getFecha());

        String sector = e.devolverSector();
        int asiento = e.devolverAsiento();
        double precio = e.precio();
        String nombreSede = e.devolverSede();
	    
	    if (funcion.getDisponiblesNumerados() == null) {
	    	funcion.sumarAsiento(1);
	        espectaculo.restarRecaudacion(nombreSede,precio);
	    	
	    }else {
	    	if (funcion.getDisponiblesNumerados().containsKey(sector)) {
		    	funcion.sumarAsiento(sector, asiento);
		        espectaculo.restarRecaudacion(nombreSede,precio);
		    }
	    }

	    usuario.reembolsarEntrada(e.devolverCodigo());
	    e.anular(); 
	    
	    return true;
	}
	
	
    /**
     * Cambia una entrada existente a una nueva fecha, permitiendo elegir nuevamente sector y asiento.
     * 
     * El cambio solo se realiza si:
     * - La entrada es para una función futura.
     * - La contraseña del usuario es válida.
     * - La nueva entrada puede ser asignada.
     *
     * @param entrada       La entrada original a cambiar.
     * @param contrasenia   La contraseña del usuario.
     * @param fechaNueva    La nueva fecha para la entrada.
     * @param sector        El sector deseado (debe coincidir con el original).
     * @param asiento       El nuevo número de asiento.
     * @return              La nueva entrada emitida.
     * @throws RuntimeException si alguna condición falla.
     */
    @Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fechaNueva, String sector, int asiento) {
		Entrada e = (Entrada) entrada;
		String email = e.getEmailUsuario();
		String nombreEspectaculo = e.getNombreEspectaculo();
	    chequeosCambio(entrada, contrasenia);
	    
	    int[] asientos = new int[] { asiento };
	    List<IEntrada> nuevas = venderEntrada(nombreEspectaculo, fechaNueva, email, contrasenia, sector, asientos);

	    if (nuevas.isEmpty()) {
	        throw new RuntimeException("No se pudo asignar nueva entrada.");
	    }
	    
	    boolean anulada = anularEntrada(entrada, contrasenia);
	    if (!anulada) {
	        throw new RuntimeException("No se pudo anular la entrada original.");
	    }
	    
	    return nuevas.get(0);
	}

	
	
    /**
     * Cambia una entrada existente por otra en una nueva fecha, para espectáculos en sedes sin numeración de asientos.
     *
     * Este método permite reemplazar una entrada comprada previamente (para una sede sin asientos numerados),
     * verificando que la entrada sea futura y autenticando al usuario mediante su contraseña.
     * Primero se anula la entrada original, y luego se intenta generar una nueva entrada con la misma información
     * del espectáculo y del usuario, pero en la nueva fecha.
     *
     * @param entrada        Entrada original a cambiar (debe ser futura y sin asiento numerado)
     * @param contrasenia    Contraseña del usuario dueño de la entrada
     * @param fecha          Nueva fecha para la entrada, en formato compatible con el sistema
     * @return               Nueva entrada generada para la nueva fecha
     * @throws RuntimeException si la entrada ya ocurrió, si no se puede anular, o si no se puede generar una nueva entrada
     */
    @Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
    	Entrada e = (Entrada) entrada;
		String email = e.getEmailUsuario();
		String nombreEspectaculo = e.getNombreEspectaculo();
	    chequeosCambio(entrada, contrasenia);

	    List<IEntrada> nuevas = venderEntrada(nombreEspectaculo, fecha, email, contrasenia, 1);

	    if (nuevas.isEmpty()) {
	        throw new RuntimeException("No se pudo asignar nueva entrada.");
	    }
	    
	    boolean anulada = anularEntrada(entrada, contrasenia);
	    if (!anulada) {
	        throw new RuntimeException("No se pudo anular la entrada original.");
	    }
	    
	    return nuevas.get(0);
	}

	
    /**
     * Calcula y devuelve el precio base de una entrada para un espectáculo en una fecha determinada.
     * 
     * Este método busca el espectáculo por nombre y luego localiza la función correspondiente a la fecha indicada.
     * Devuelve el precio base establecido para dicha función, sin aplicar modificaciones por sede o sector.
     * 
     * @param nombreEspectaculo  Nombre del espectáculo
     * @param fecha              Fecha de la función en formato "dd/MM/yyyy"
     * @return                   Precio base de la función en la fecha indicada
     * @throws RuntimeException si no se encuentra el espectáculo o la función en la fecha dada
     */
    @Override
	public double costoEntrada(String nombreEspectaculo, String fecha) {
	    Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);

	    if (espectaculo == null) {
	        throw new RuntimeException("Espectáculo no encontrado: " + nombreEspectaculo);
	    }

	    Fecha fechaFuncion = Fecha.desdeString(fecha);

	    Funcion funcion = espectaculo.getFuncion(fechaFuncion);

	    if (funcion == null) {
	        throw new RuntimeException("Función no encontrada para la fecha: " + fecha);
	    }

	    return funcion.getPrecioBase(); 
	}

    /**
     * Calcula y devuelve el precio final de una entrada para un espectáculo en una fecha y sector determinados.
     * 
     * Este método busca el espectáculo por nombre, luego obtiene la función correspondiente a la fecha indicada
     * y finalmente calcula el precio final de la entrada considerando el sector dentro de la sede.
     * 
     * @param nombreEspectaculo  Nombre del espectáculo
     * @param fecha              Fecha de la función en formato "dd/MM/yyyy"
     * @param sector             Nombre del sector (por ejemplo: "Platea VIP", "Platea común", etc.)
     * @return                   Precio final de la entrada para el sector especificado
     * @throws RuntimeException si no se encuentra el espectáculo o la función en la fecha dada
     */
    @Override
	public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
	    Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);

	    if (espectaculo == null) {
	        throw new RuntimeException("Espectáculo no encontrado: " + nombreEspectaculo);
	    }

	    Fecha fechaFuncion = Fecha.desdeString(fecha);
	    Funcion funcion = espectaculo.getFuncion(fechaFuncion);

	    if (funcion == null) {
	        throw new RuntimeException("Función no encontrada para la fecha: " + fecha);
	    }

	    return funcion.devolverPrecio(sector);
	}
	
    /**
     * Calcula y devuelve el monto total recaudado por un espectáculo en todas sus funciones y sedes.
     *
     * Este método busca el espectáculo por su nombre y retorna la suma total de ingresos generados
     * por la venta de entradas en todas las sedes y fechas asociadas al espectáculo.
     *
     * @param nombreEspectaculo Nombre del espectáculo del cual se desea conocer la recaudación total
     * @return                  Monto total recaudado por el espectáculo
     * @throws RuntimeException si no se encuentra un espectáculo con el nombre especificado
     */
    @Override
	public double totalRecaudado(String nombreEspectaculo) {
    	
	    Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);

	    if (espectaculo == null) {
	        throw new RuntimeException("El espectáculo no existe: " + nombreEspectaculo);
	    }
	    return espectaculo.getRecaudacionTotal();
	}

    /**
     * Calcula y devuelve el monto total recaudado para un espectáculo específico en una sede determinada.
     *
     * Este método busca el espectáculo por su nombre y luego solicita al espectáculo el monto recaudado
     * en la sede indicada. El total se basa en todas las funciones realizadas en esa sede.
     *
     * @param nombreEspectaculo Nombre del espectáculo
     * @param nombreSede        Nombre de la sede donde se realizó el espectáculo
     * @return                  Monto total recaudado en la sede indicada
     * @throws IllegalArgumentException si no se encuentra un espectáculo con el nombre especificado
     */
    @Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
	    Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);

	    if (espectaculo == null) {
	        throw new IllegalArgumentException("El espectáculo no existe: " + nombreEspectaculo);
	    }

	    return espectaculo.getRecaudadoPorSede(nombreSede);
	}
	
    /**
     * Devuelve una lista con todas las entradas vendidas correspondientes a un espectáculo específico.
     *
     * Este método recorre todas las entradas de todos los usuarios registrados en el sistema
     * y filtra aquellas que correspondan al nombre del espectáculo indicado.
     *
     * @param nombreEspectaculo Nombre del espectáculo cuyas entradas se desean listar
     * @return Lista de entradas correspondientes al espectáculo especificado
     */
    @Override
	public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
	    List<IEntrada> resultado = new ArrayList<>();
       
	    Iterator<Usuario> iteratorUsuarios = usuarios.values().iterator();
	    
	    while (iteratorUsuarios.hasNext()) {
	        Usuario usuario = iteratorUsuarios.next();
	        
	        Iterator<Entrada> iteratorEntradas = usuario.listarEntradas().iterator();
	        
	        while (iteratorEntradas.hasNext()) {
	            Entrada entrada = iteratorEntradas.next();
	            if (entrada.getNombreEspectaculo().equals(nombreEspectaculo)) {
	                resultado.add(entrada);
	            }
	        }
	    }
	    
	    return resultado;
	}
	
	// ----------------------------------------------------------- AUXILIARES -----------------------------------------------------------------------

    /**
     * Verifica que un usuario exista y la contraseña sea correcta.
     * 
     * @param email Email del usuario.
     * @param contrasenia Contraseña del usuario.
     * @return true si la autenticación es correcta, false en caso contrario.
     */
    public boolean autenticarUsuario(String email, String contrasenia) {
        if (email == null || email.isEmpty() || contrasenia == null || contrasenia.isEmpty()) {
            return false;
        }
        Usuario usuario = usuarios.get(email);
        return usuario != null && usuario.verificarContrasenia(contrasenia);
    }

    /**
     * Verifica que no haya ningún espectáculo programado en una sede en una fecha.
     * 
     * @param nombreSede Nombre de la sede.
     * @param fecha Fecha a verificar.
     * @return true si la sede está disponible en esa fecha, false en caso contrario.
     */
    public boolean verificarDisponibilidad(String nombreSede, Fecha fecha) {
       
        Iterator<Espectaculo> iteratorEspectaculos = espectaculos.values().iterator();
        
        while (iteratorEspectaculos.hasNext()) {
            Espectaculo espectaculo = iteratorEspectaculos.next();
            
            Iterator<Funcion> iteratorFunciones = espectaculo.getFunciones().values().iterator();
            
            while (iteratorFunciones.hasNext()) {
                Funcion funcion = iteratorFunciones.next();
                if (funcion.getSede().getNombre().equals(nombreSede) && funcion.getFecha().equals(fecha)) {
                    return false; // Se encontró conflicto
                }
            }
        }

        return true;
    }
    
    /**
     * Realiza las verificaciones necesarias antes de procesar la venta de una entrada.
     *
     * Este método valida que el usuario exista y esté autenticado, que el espectáculo y la función
     * para la fecha indicada existan, y que los datos requeridos no sean nulos o vacíos.
     *
     * @param nombreEspectaculo Nombre del espectáculo
     * @param fecha             Fecha de la función
     * @param email             Email del usuario
     * @param contrasenia       Contraseña del usuario
     * @throws IllegalArgumentException si algún dato es inválido o no se encuentra el espectáculo, la función o el usuario
     * @throws RuntimeException si la autenticación del usuario falla
     */
	public void chequeosVenta(String nombreEspectaculo, String fecha, String email, String contrasenia) {
		if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (nombreEspectaculo == null || nombreEspectaculo.isEmpty()) {
            throw new IllegalArgumentException("El nombre del espectáculo no puede estar vacío");
        }
        if (fecha == null || fecha.isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        }

        if (!autenticarUsuario(email, contrasenia)) {
            throw new RuntimeException("Usuario '" + email + "' no encontrado o contraseña incorrecta");
        }

        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        if (espectaculo == null) {
            throw new IllegalArgumentException("El espectáculo '" + nombreEspectaculo + "' no existe");
        }

        Funcion funcion = espectaculo.getFuncion(fecha);
        if (funcion == null) {
            throw new IllegalArgumentException("La función de '" + nombreEspectaculo +"' no existe para la fecha" + fecha);
        }
        
        
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

//       if (!funcion.esFutura()) {
//           throw new IllegalArgumentException("La función seleccionada para el dia " + fecha + " ya ha tenido lugar");
//        }
	}
	
	/**
	 * Realiza las verificaciones necesarias para anular una entrada.
	 *
	 * Este método valida que la entrada no sea nula ni esté ya anulada, que el usuario asociado exista
	 * y que la contraseña sea correcta. También comprueba que la entrada corresponda a una función futura.
	 *
	 * @param entrada       Entrada a anular
	 * @param contrasenia   Contraseña del usuario
	 * @return              true si la entrada puede anularse (es de una función futura), false en caso contrario
	 * @throws RuntimeException si la entrada es nula, ya fue anulada, el usuario no existe o la contraseña es incorrecta
	 */
	public boolean chequeosAnular(IEntrada entrada, String contrasenia) {
    	
    	if (entrada == null) {
    	    throw new RuntimeException("Entrada nula");
    	}
    	Entrada entradaConcreta = (Entrada) entrada;
        if (entradaConcreta.estaAnulada()) {
            throw new RuntimeException("La entrada ya fue anulada anteriormente");
        }
	    String emailUsuario = entradaConcreta.getEmailUsuario();
	    Usuario usuario = usuarios.get(emailUsuario);
	    if (usuario == null) {
	        throw new RuntimeException("Usuario no encontrado");
	    }
	    
	    if (!autenticarUsuario(emailUsuario, contrasenia)) {
	        throw new RuntimeException("Contraseña incorrecta");
	    }

	    if (!entradaConcreta.esFutura()) {
	        return false;
	    }
	    return true;
	}
	
	
	/**
	 * Realiza las verificaciones necesarias para permitir el cambio de una entrada.
	 *
	 * Este método verifica que la entrada sea para una función futura y que la contraseña del usuario asociado sea correcta.
	 *
	 * @param entrada       Entrada que se desea cambiar
	 * @param contrasenia   Contraseña del usuario asociado a la entrada
	 * @throws RuntimeException si la función ya ocurrió o la contraseña es incorrecta
	 */
	public void chequeosCambio(IEntrada entrada, String contrasenia) {
		Entrada e = (Entrada) entrada;

	    if (!e.esFutura()) {
	        throw new RuntimeException("La entrada ya no se puede cambiar (fecha pasada)");
	    }

	    String email = e.getEmailUsuario();

	    if (!autenticarUsuario(email, contrasenia)) {
	        throw new RuntimeException("Contraseña incorrecta");
	    }
	}
	
    
    /**
     * Obtiene un usuario por su email.
     * 
     * @param email Email del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuario getUsuario(String email) {
        return usuarios.get(email);
    }

    /**
     * Obtiene un espectáculo por su código.
     * 
     * @param codigo Código del espectáculo.
     * @return Espectáculo encontrado o null si no existe.
     */
    public Espectaculo getEspectaculo(String codigo) {
        return espectaculos.get(codigo);
    }

    /**
     * Obtiene una sede por su nombre.
     * @param nombre Nombre de la sede.
     * @return Sede encontrada o null si no existe.
     */
    public Sede getSede(String nombre) {
        return sedes.get(nombre);
    }

    /**
     * Obtiene todas las sedes registradas en el sistema.
     * @return Mapa de sedes registradas.
     */
    public Map<String, Sede> getSedes() {
        return new HashMap<>(sedes);
    }

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     * @return Mapa de usuarios registrados.
     */
    public Map<String, Usuario> getUsuarios() {
        return new HashMap<>(usuarios);
    }

    /**
     * Obtiene todos los espectáculos registrados en el sistema.
     * @return Mapa de espectáculos registrados.
     */
    public Map<String, Espectaculo> getEspectaculos() {
        return new HashMap<>(espectaculos);
    }
    
    /**
     * Devuelve una representación en String del sistema Ticketek,
     * mostrando todos los usuarios, sus entradas, las sedes y los espectáculos registrados.
     *
     * @return String con el estado completo del sistema Ticketek.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("======= SISTEMA TICKETEK =======\n\n");

        sb.append("--- Usuarios Registrados ---\n");
        for (Usuario usuario : usuarios.values()) {
            sb.append(usuario).append("\n");

            List<IEntrada> entradas = listarTodasLasEntradasDelUsuario(usuario.getEmail(), usuario.getContrasenia());
            if (entradas.isEmpty()) {
                sb.append("  (Sin entradas)\n");
            } else {
                for (IEntrada entrada : entradas) {
                    sb.append("  - ").append(entrada).append("\n");
                }
            }
            sb.append("\n");
        }
        sb.append("\n");

        // Sedes
        sb.append("--- Sedes Registradas ---\n");
        for (Sede s : sedes.values()) {
            sb.append(s).append("\n");
        }
        sb.append("\n");

        // Espectáculos
        sb.append("--- Espectáculos Registrados ---\n");
        for (Espectaculo e : espectaculos.values()) {
            sb.append(e).append("\n");
        }

        sb.append("\n======= FIN SISTEMA TICKETEK =======");
        return sb.toString();
    }



}