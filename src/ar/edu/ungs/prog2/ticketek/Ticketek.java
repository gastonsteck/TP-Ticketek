package ar.edu.ungs.prog2.ticketek;
import java.time.DateTimeException;
import java.util.*;


/**
 * Clase principal del sistema de gestión de entradas para espectáculos.
 * Permite administrar usuarios, sedes y espectáculos, así como gestionar
 * la venta y administración de entradas.
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
    	if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del estadio no puede estar vacío");
        }
        if (sedes.containsKey(nombre)) {
            throw new IllegalArgumentException("Ya existe una sede con el nombre: " + nombre);
        }
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
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
    	   if (nombre == null || nombre.isEmpty()) {
               throw new IllegalArgumentException("El nombre del teatro no puede estar vacío");
           }
           if (sedes.containsKey(nombre)) {
               throw new IllegalArgumentException("Ya existe una sede con el nombre: " + nombre);
           }
           if (capacidadMaxima <= 0) {
               throw new IllegalArgumentException("La capacidad debe ser mayor que cero");
           }
           if (direccion == null || direccion.isEmpty()) {
               throw new IllegalArgumentException("La dirección no puede estar vacía");
           }
           
           if (asientosPorFila <= 0) {
               throw new IllegalArgumentException("La cantidad de asientos por fila debe ser mayor que cero");
           }
           
           if (sectores == null || sectores.length <0) {
               throw new IllegalArgumentException("Debe indicarse al menos un sector");
           }
           
           if (capacidad == null || capacidad.length != sectores.length) {
               throw new IllegalArgumentException("La cantidad de capacidades debe ser igual a la cantidad de sectores");
           }
           
           int sumaSectores = 0;
           for (int i = 0; i < sectores.length; i++) {
        	   if (sectores[i].isEmpty()) {
        		   throw new IllegalArgumentException("El nombre de los sectores no puede ser vacío");
        	   }
        	   if (!(capacidad[i] <= capacidadMaxima || capacidad[i] >= asientosPorFila)) {
        		   throw new IllegalArgumentException("La capacidad de los sectores debe ser mayor o igual a la cantidad de asientos por fila "
        		   		+ "y menor o igual a la capacidad máxima");
        	   }
        	   sumaSectores += capacidad[i];
           }
           if (sumaSectores != capacidadMaxima) {
        	   throw new IllegalArgumentException("La suma de la capacidad de cada sector debe ser igual a la capacidad máxima");
           }
           
           if (porcentajeAdicional == null || porcentajeAdicional.length != sectores.length) {
               throw new IllegalArgumentException("La cantidad de porcentajes adicionales debe ser igual a la cantidad de sectores");
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
    	if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del miniestadio no puede estar vacío");
        }
        if (sedes.containsKey(nombre)) {
            throw new IllegalArgumentException("Ya existe una sede con el nombre: " + nombre);
        }
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        }
        if (asientosPorFila <= 0) {
            throw new IllegalArgumentException("La cantidad de asientos por fila debe ser mayor que cero");
        }
        
        if (sectores == null || sectores.length <0) {
            throw new IllegalArgumentException("Debe indicarse al menos un sector");
        }
        
        if (capacidad == null || capacidad.length != sectores.length) {
            throw new IllegalArgumentException("La cantidad de capacidades debe ser igual a la cantidad de sectores");
        }
        
        int sumaSectores = 0;
        for (int i = 0; i < sectores.length; i++) {
     	   if (sectores[i].isEmpty()) {
     		   throw new IllegalArgumentException("El nombre de los sectores no puede ser vacío");
     	   }
     	   if (!(capacidad[i] <= capacidadMaxima || capacidad[i] >= asientosPorFila)) {
     		   throw new IllegalArgumentException("La capacidad de los sectores debe ser mayor o igual a la cantidad de asientos por fila"
     		   		+ "y menor o igual a la capacidad máxima");
     	   }
     	   sumaSectores += capacidad[i];
        }
        if (sumaSectores != capacidadMaxima) {
     	   throw new IllegalArgumentException("La suma de la capacidad de cada sector debe ser igual a la capacidad máxima");
        }
        
        if (porcentajeAdicional == null || porcentajeAdicional.length != sectores.length) {
            throw new IllegalArgumentException("La cantidad de porcentajes adicionales debe ser igual a la cantidad de sectores");
        }
        
        if (cantidadPuestos < 0) {
            throw new IllegalArgumentException("La cantidad de puestos no puede ser negativa");
        }
        
        if (precioConsumicion < 0) {
            throw new IllegalArgumentException("El valor fijo no puede ser negativo");
        }

        Miniestadio miniestadio = new Miniestadio(nombre, direccion, capacidadMaxima, asientosPorFila, cantidadPuestos, precioConsumicion, sectores, capacidad, porcentajeAdicional);
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
    	if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellidoS no puede estar vacío");
        }
        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
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
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("El email no puede estar vacío");
            }
            if (nombreEspectaculo == null || nombreEspectaculo.isEmpty()) {
                throw new IllegalArgumentException("El nombre del espectáculo no puede estar vacío");
            }
            if (fecha == null || fecha.isEmpty()) {
                throw new IllegalArgumentException("La fecha no puede estar vacía");
            }

            if (!usuarios.get(email).verificarContrasenia(contrasenia)) {
                throw new IllegalArgumentException("Usuario no encontrado o contraseña incorrecta");
            }

            Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
            if (espectaculo == null) {
                throw new IllegalArgumentException("El espectáculo no existe");
            }

            Funcion funcion = espectaculo.getFuncion(fecha);
            if (funcion == null) {
                throw new IllegalArgumentException("La función no existe para esa fecha");
            }

            if (!funcion.esFutura()) {
                throw new IllegalArgumentException("La función seleccionada ya ha tenido lugar");
            }

            if (!funcion.verificarDisponibilidad(cantidadEntradas)) {
                throw new IllegalArgumentException("Solo hay " + funcion.getDisponiblesSinNumerar() + " entradas disponibles.");
            }

            Usuario usuario = usuarios.get(email);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
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
        } catch (IllegalArgumentException e) {
            System.err.println("Error al vender entradas: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {
        try {
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("El email no puede estar vacío");
            }
            if (nombreEspectaculo == null || nombreEspectaculo.isEmpty()) {
                throw new IllegalArgumentException("El nombre del espectáculo no puede estar vacío");
            }
            if (fecha == null || fecha.isEmpty()) {
                throw new IllegalArgumentException("La fecha no puede estar vacía");
            }
            if (!usuarios.get(email).verificarContrasenia(contrasenia)) {
                throw new IllegalArgumentException("Usuario no encontrado o contraseña incorrecta");
            }

            Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
            if (espectaculo == null) {
                throw new IllegalArgumentException("Espectáculo no encontrado");
            }
            Funcion funcion = espectaculo.getFuncion(fecha);

            if (funcion == null) {
            	throw new IllegalArgumentException("No se encontró la función de la fecha " + fecha + " para el espectáculo " + nombreEspectaculo);
            }

            if (!funcion.esFutura()) {
            	throw new IllegalArgumentException("La función seleccionada ya ha tenido lugar");
            }

            for (int asiento : asientos) {
                if (!funcion.verificarDisponibilidad(sector, asiento)) {
                    throw new IllegalArgumentException("El asiento " + asiento + " del sector " + sector + " no está disponible.");
                }
            }

            Usuario usuario = usuarios.get(email);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
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

        for (Funcion funcion : espectaculoBuscado.getFunciones().values()) {
            Sede sedeDeLaFuncion = funcion.getSede();
            String nombreDeSede = sedeDeLaFuncion.getNombre();
            String fechaFuncion = funcion.getFecha().toString();

            resultado.append("(").append(fechaFuncion).append(") ").append(nombreDeSede).append(" - ");

            if (sedeDeLaFuncion.esNumerada()) {
                resultado.append("Sede numerada");
            } else {
                String nombreSector = "Campo";
                int capacidadTotal = sedeDeLaFuncion.getCapacidadSector(nombreSector);
                int cantidadDisponible = funcion.getDisponiblesSinNumerar();
                int cantidadVendida = capacidadTotal - cantidadDisponible;

                resultado.append(cantidadVendida).append(" / ").append(capacidadTotal);
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
		Entrada entradaConcreta = (Entrada) entrada;
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

	    String codigoEntrada = entradaConcreta.devolverCodigo();
	    String nombreEspectaculo = entradaConcreta.getNombreEspectaculo(); 
	    
	    Fecha fecha = entradaConcreta.getFecha();
	    String sector = entradaConcreta.devolverSector();
	    Integer asiento = entradaConcreta.devolverAsiento();
	    Double precio = entradaConcreta.precio();
	    String nombreSede = entradaConcreta.devolverSede();
	    Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
	    if (espectaculo == null) {
	        throw new RuntimeException("Espectáculo no encontrado");
	    }

	    Funcion funcion = espectaculo.getFuncion(fecha);
	    if (funcion.getDisponiblesNumerados() != null) {
	    	if (funcion.getDisponiblesNumerados().containsKey(sector)) {
		    	funcion.sumarAsiento(sector, asiento);
		        espectaculo.restarRecaudacion(nombreSede,precio);
		    }
	    }else {
	    	funcion.sumarAsiento(1);
	        espectaculo.restarRecaudacion(nombreSede,precio);
	    }

	    usuario.reembolsarEntrada(codigoEntrada);
	    return true;
	}
	
	
    /**
     * Cambia una entrada existente a una nueva fecha y asiento dentro del mismo sector.
     * 
     * El cambio solo se realiza si:
     * - La entrada es para una función futura.
     * - La contraseña del usuario es válida.
     * - El sector solicitado es el mismo que el original.
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
		Entrada entradaConcreta = (Entrada) entrada;

	    if (!entradaConcreta.esFutura()) {
	        throw new RuntimeException("La entrada ya no se puede cambiar (fecha pasada)");
	    }
	    
	    if (!entradaConcreta.devolverSector().equalsIgnoreCase(sector)) {
	        throw new RuntimeException("El nuevo sector debe ser el mismo que el sector original");
	    }
	    

	    String email = entradaConcreta.getEmailUsuario();
	    String nombreEspectaculo = entradaConcreta.getNombreEspectaculo();

	    if (!autenticarUsuario(email, contrasenia)) {
	        throw new RuntimeException("Contraseña incorrecta");
	    }

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
	    Entrada entradaConcreta = (Entrada) entrada;

	    if (!entradaConcreta.esFutura()) {
	        throw new RuntimeException("La entrada ya no se puede cambiar (fecha pasada)");
	    }

	    String email = entradaConcreta.getEmailUsuario();
	    String nombreEspectaculo = entradaConcreta.getNombreEspectaculo();
	    if (!autenticarUsuario(email, contrasenia)) {
	        throw new RuntimeException("Contraseña incorrecta");
	    }

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

	    for (Usuario usuario : usuarios.values()) {
	        for (Entrada entrada : usuario.listarEntradas()) {
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
        for (Espectaculo espectaculo : espectaculos.values()) {
            for (Funcion funcion : espectaculo.getFunciones().values()) {
                if (funcion.getSede().getNombre().equals(nombreSede) && funcion.getFecha().equals(fecha)) {
                    return false;
                }
            }
        }
        return true;
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


}