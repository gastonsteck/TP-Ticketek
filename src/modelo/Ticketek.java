package modelo;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import ar.edu.ungs.prog2.ticketek.IEntrada;
import ar.edu.ungs.prog2.ticketek.ITicketek;
import java.util.Set;


/**
 * Clase principal del sistema de gestión de entradas para espectáculos.
 * Permite administrar usuarios, sedes y espectáculos, así como gestionar
 * la venta y administración de entradas.
 */
public class Ticketek implements ITicketek {
    private Map<String, Usuario> usuarios;
    private Map<String, Espectaculo> espectaculos;
    private Map<String, Sede> sedes;
    private Map<String, Espectaculo> espectaculosPorNombre;

    /**
     * Constructor que inicializa un nuevo sistema Ticketek.
     */
    public Ticketek() {
        this.usuarios = new HashMap<>();
        this.espectaculos = new HashMap<>();
        this.sedes = new HashMap<>();
        
        this.espectaculosPorNombre = new HashMap<>();
        
    }

    /**
     * Registra un nuevo estadio en el sistema.
     * @param nombre Nombre del estadio.
     * @param capacidadMaxima Capacidad total del estadio.
     * @param direccion Dirección del estadio.
     * @throws IllegalArgumentException Si el nombre ya existe o los datos son inválidos.
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
     * @param nombre Nombre del teatro.
     * @param capacidad Capacidad total del teatro.
     * @param direccion Dirección del teatro.
     * @param cantFilas Cantidad de filas por sector.
     * @param asientosPorFila Cantidad de asientos por fila.
     * @throws IllegalArgumentException Si el nombre ya existe, los datos son inválidos o la 
     * capacidad no coincide con el número de asientos calculados.
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
           
       
//           int asientosCalculados = cantFilas * asientosPorFila * 4;
//           if (asientosCalculados != capacidadMaxima) {
//               throw new IllegalArgumentException(
//                   "La capacidad indicada (" + capacidadMaxima + ") no coincide con el número de asientos calculados (" 
//                   + asientosCalculados + ")");
//           }

           Teatro teatro = new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila,
       			sectores, capacidad, porcentajeAdicional);
           sedes.put(nombre, teatro);
	}
    
    /**
     * Registra un nuevo miniestadio en el sistema.
     * @param nombre Nombre del miniestadio.
     * @param capacidad Capacidad total del miniestadio.
     * @param direccion Dirección del miniestadio.
     * @param cantFilas Cantidad de filas por sector.
     * @param asientosPorFila Cantidad de asientos por fila.
     * @param cantPuestosMerch Cantidad de puestos de merchandising.
     * @param cantPuestosComida Cantidad de puestos de comida.
     * @param valorFijo Valor fijo de consumición.
     * @throws IllegalArgumentException Si el nombre ya existe, los datos son inválidos o la 
     * capacidad no coincide con el número de asientos calculados.
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
     * @param email Email del usuario (único).
     * @param nombreCompleto Nombre completo del usuario.
     * @param contraseña Contraseña del usuario.
     * @throws IllegalArgumentException Si el email ya existe o los datos son inválidos.
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
     * @param nombre Nombre del espectáculo.
     * @param codigo Código único del espectáculo.
     * @param sedes Lista de sedes donde se realizará el espectáculo.
     * @param fechas Lista de fechas de las funciones.
     * @param preciosBases Lista de precios base para cada función.
     * @throws IllegalArgumentException Si el código ya existe, si alguna sede no existe,
     * si la cantidad de sedes no coincide con la cantidad de fechas o precios,
     * o si ya hay un espectáculo programado en alguna sede en esa fecha.
     */
    
    @Override
	public void registrarEspectaculo(String nombre) {
		espectaculosPorNombre.put(nombre, new Espectaculo(nombre));
	}
    
    /**
     * 14) Agrega una funcion nueva a un espectaculo ya registrado.
     * 
     * Si el espectaculo no está registrado o la sede o algun campo 
     * no es valido, se lanza una excepcion.
     * Si ya hay una funcion para esa fecha, lanza excepcion.
     * 
     * @param nombreEspectaculo
     * @param fecha en formato: dd/mm/YY
     * @param sede
     * @param precioBase
     */
    @Override
    public void agregarFuncion(String nombreEspectaculo, String fechaStr, String nombreSede, double precioBase) {
    	//convertir fecha
    			Fecha fecha = Fecha.desdeString(fechaStr);

    			
    			// Verificar que el espectáculo exista
    		    Espectaculo espectaculo = espectaculosPorNombre.get(nombreEspectaculo);
    		    if (espectaculo == null) {
    		        throw new IllegalArgumentException("Espectáculo no encontrado: " + nombreEspectaculo);
    		    }
    		    
    		 // Buscar la sede por nombre
    		    Sede sede = sedes.get(nombreSede); // ← acá corregido
    		    if (sede == null) {
    		        throw new IllegalArgumentException("Sede no encontrada: " + nombreSede);
    		    }
    		    
    		 // Verificar que no haya función en esa sede y fecha
    		    if (!verificarDisponibilidad(nombreSede, fecha)) {
    		        throw new IllegalArgumentException("Ya hay una función programada en la sede " + nombreSede + " para la fecha " + fechaStr);
    		    }

    		    // Agregar función (esto lo puede hacer el espectáculo directamente)
    		    espectaculo.agregarFuncion(fecha, sede, precioBase);
    }
    

    /**
     * Vende una cantidad determinada de entradas para un espectáculo en una función específica,
     * en un sector sin numerar (por ejemplo, "Campo").
     * 
     * Realiza validaciones sobre los datos de entrada, autentica al usuario, verifica que la función
     * exista y sea futura, y que haya disponibilidad suficiente de entradas. Luego genera las entradas,
     * las asocia al usuario y actualiza la recaudación y disponibilidad de la función.
     * 
     * @param nombreEspectaculo Nombre del espectáculo para el cual se quieren comprar entradas.
     * @param fecha Fecha de la función en formato String.
     * @param email Email del usuario que realiza la compra.
     * @param contrasenia Contraseña del usuario para autenticación.
     * @param cantidadEntradas Cantidad de entradas que se desean comprar.
     * @return Lista de objetos IEntrada correspondientes a las entradas vendidas.
     * @throws IllegalArgumentException Si alguno de los parámetros es inválido, 
     *         si el usuario no se autentica correctamente, 
     *         si el espectáculo o función no existen, 
     *         si la función ya ocurrió, o si no hay suficientes entradas disponibles.
     */
    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
                                         int cantidadEntradas) {
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
            throw new IllegalArgumentException("Usuario no encontrado o contraseña incorrecta");
        }

        Espectaculo espectaculo = espectaculosPorNombre.get(nombreEspectaculo);
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
        String sector = "Campo";
        Fecha fechaObj = Fecha.desdeString(fecha);
        String nombreSede = funcion.getSede().getNombre();
        double precio = funcion.devolverPrecio(sector);

        List<IEntrada> listaEntradas = new ArrayList<>();

        for (int i = 0; i < cantidadEntradas; i++) {
            Entrada entrada = new Entrada(nombreEspectaculo, nombreSede, fechaObj, precio);
            usuario.comprarEntrada(entrada.devolverCodigo(), entrada);
            listaEntradas.add(entrada);
            espectaculo.sumarRecaudadoPorSede(nombreSede, precio);
        }

        funcion.venderAsiento(cantidadEntradas);

        return listaEntradas;
    }

    /**
     * Vende entradas para un espectáculo en una función específica, en un sector con asientos numerados.
     * 
     * Valida los parámetros de entrada, autentica al usuario y verifica que la función exista, sea futura y
     * que los asientos solicitados estén disponibles en el sector indicado. Luego genera las entradas para
     * cada asiento solicitado, las asocia al usuario, actualiza la recaudación del espectáculo y la disponibilidad
     * de los asientos en la función.
     * 
     * @param nombreEspectaculo Nombre del espectáculo para el cual se quieren comprar entradas.
     * @param fecha Fecha de la función en formato String.
     * @param email Email del usuario que realiza la compra.
     * @param contrasenia Contraseña del usuario para autenticación.
     * @param sector Nombre del sector donde están ubicados los asientos.
     * @param asientos Array con los números de asientos que se desean comprar.
     * @return Lista de objetos IEntrada correspondientes a las entradas vendidas.
     * @throws IllegalArgumentException Si algún parámetro es inválido, si el usuario no se autentica,
     *         si la función ya ocurrió, o si algún asiento solicitado no está disponible.
     */
    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {
        // Validaciones iniciales
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
            throw new IllegalArgumentException("Usuario no encontrado o contraseña incorrecta");
        }

        // Validar existencia del espectáculo y función
        Espectaculo espectaculo = espectaculosPorNombre.get(nombreEspectaculo);
        Funcion funcion = espectaculo.getFuncion(fecha);

        if (!funcion.esFutura()) {
            throw new IllegalArgumentException("La función seleccionada ya ha tenido lugar");
        }

        // Verificar disponibilidad de cada asiento
        for (int asiento : asientos) {
            if (!funcion.verificarDisponibilidad(sector, asiento)) {
                throw new IllegalArgumentException("El asiento " + asiento + " del sector " + sector + " no está disponible.");
            }
        }

        // Conversión de datos y preparativos
        Usuario usuario = usuarios.get(email);
        Fecha fechaObj = Fecha.desdeString(fecha);
        Sede sede = funcion.getSede();
        int asientosPorFila = (sede instanceof Teatro)
                ? ((Teatro) sede).getAsientosPorFila()
                : ((Miniestadio) sede).getAsientosPorFila();

        String nombreSede = sede.getNombre();
        double precio = funcion.devolverPrecio(sector);
        List<IEntrada> listaEntradas = new ArrayList<>();

        // Crear entradas, registrar compra y actualizar recaudación
        for (int asiento : asientos) {
            int fila = (asiento - 1) / asientosPorFila + 1;
            Entrada entrada = new Entrada(nombreEspectaculo, nombreSede, fechaObj, sector, fila, asiento, precio);
            usuario.comprarEntrada(entrada.devolverCodigo(), entrada);
            listaEntradas.add(entrada);
            funcion.venderAsiento(sector, asiento);
            espectaculo.sumarRecaudadoPorSede(nombreSede, precio);
        }

        return listaEntradas;
    }

    /**
	 * Lista todas las funciones de un espectáculo según su nombre.
	 * El formato depende del tipo de sede:
	 * - Si es estadio (sin numerar): "(FECHA) NOMBRE SEDE - ENTRADAS VENDIDAS / CAPACIDAD"
	 * - Si es numerada: se indica "Sede numerada" (por ahora)
	 *
	 * @param nombreEspectaculo Nombre del espectáculo a listar.
	 * @return Listado formateado de funciones o mensaje si no se encuentra.
	 */
	@Override
	public String listarFunciones(String nombreEspectaculo) {
	    Espectaculo espectaculoBuscado = espectaculosPorNombre.get(nombreEspectaculo);

	    if (espectaculoBuscado == null) {
	        return "Espectáculo no encontrado.";
	    }

	    StringBuilder resultado = new StringBuilder();

	    for (Funcion funcion : espectaculoBuscado.getFunciones().values()) {
	        Sede sedeDeLaFuncion = funcion.getSede();
	        String nombreDeSede = sedeDeLaFuncion.getNombre();
	        String fechaFuncion = funcion.getFecha().toString();

	        resultado.append("(")
	                 .append(fechaFuncion)
	                 .append(") ")
	                 .append(nombreDeSede)
	                 .append(" - ");

	        if (sedeDeLaFuncion.esNumerada()) {
	            resultado.append("Sede numerada");
	        } else {
	            String nombreSector = "Campo";
	            int capacidadTotal = sedeDeLaFuncion.getCapacidadSector(nombreSector);
	            int cantidadDisponible = funcion.getDisponiblesSinNumerar(); 
	            int cantidadVendida = capacidadTotal - cantidadDisponible;

	            resultado.append(cantidadVendida)
	                     .append(" / ")
	                     .append(capacidadTotal);
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
	
	@Override
	public boolean anularEntrada(IEntrada entrada, String contrasenia) {
		// TODO Auto-generated method stub
		return false;
	}
	

    /**
     * Anula una entrada.
     * @param email Email del usuario.
     * @param contraseña Contraseña del usuario.
     * @param codigoEntrada Código de la entrada.
     * @throws IllegalArgumentException Si el usuario no existe, la contraseña es incorrecta,
     * o la entrada no pertenece al usuario.
     */
    public void anularEntrada(String email, String contrasenia, String codigoEntrada) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (codigoEntrada == null || codigoEntrada.isEmpty()) {
            throw new IllegalArgumentException("El código de entrada no puede estar vacío");
        }
        
        // Verificar si el usuario existe y la contraseña es correcta
        if (!autenticarUsuario(email, contrasenia)) {
            throw new IllegalArgumentException("Usuario no encontrado o contraseña incorrecta");
        }
        
        Usuario usuario = usuarios.get(email);
        Entrada entrada = usuario.getEntrada(codigoEntrada);
        
        if (entrada == null) {
            throw new IllegalArgumentException("La entrada con código " + codigoEntrada + 
                                            " no pertenece al usuario con email " + email);
        }
        
        // Obtener información de la entrada para restaurar el asiento
        String codigoEspectaculo = entrada.devolverEspectaculo();
        String nombreSede = entrada.devolverSede();
        String sector = entrada.devolverSector();
        int numAsiento = entrada.devolverAsiento();
        double valorEntrada = entrada.devolverValor();
        
        // Obtener el espectáculo
        Espectaculo espectaculo = espectaculos.get(codigoEspectaculo);
        if (espectaculo == null) {
            throw new IllegalArgumentException("No se encontró el espectáculo con código " + codigoEspectaculo);
        }
        
        // Obtener la función
        Funcion funcion = espectaculo.getFuncion(nombreSede);
        if (funcion == null) {
            throw new IllegalArgumentException("No se encontró la función para la sede " + nombreSede);
        }
        
        // Restaurar el asiento
        funcion.sumarAsiento(sector, numAsiento);
        
        // Restar del total recaudado
        espectaculo.restarRecaudado(valorEntrada);
        
        // Anular la entrada en el usuario
        usuario.reembolsarEntrada(codigoEntrada);
    }

    /**
     * Cambia la sede de una entrada.
     * @param nombreSede Nombre de la nueva sede.
     * @param codigoEntrada Código de la entrada.
     * @throws IllegalArgumentException Si la sede no existe, la entrada no existe 
     * o no es posible cambiar la sede.
     */
    public void cambiarSede(String nombreSede, String codigoEntrada) {
        if (nombreSede == null || nombreSede.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sede no puede estar vacío");
        }
        if (codigoEntrada == null || codigoEntrada.isEmpty()) {
            throw new IllegalArgumentException("El código de entrada no puede estar vacío");
        }
        
        // Verificar si la sede existe
        if (!sedes.containsKey(nombreSede)) {
            throw new IllegalArgumentException("No existe una sede con el nombre: " + nombreSede);
        }
        
        // Buscar la entrada en todos los usuarios
        Entrada entrada = null;
        Usuario propietario = null;
        
        for (Usuario usuario : usuarios.values()) {
            entrada = usuario.getEntrada(codigoEntrada);
            if (entrada != null) {
                propietario = usuario;
                break;
            }
        }
        
        if (entrada == null || propietario == null) {
            throw new IllegalArgumentException("No se encontró la entrada con código " + codigoEntrada);
        }
        
        // Obtener información de la entrada original
        String codigoEspectaculo = entrada.devolverEspectaculo();
        String sedeOriginal = entrada.devolverSede();
        String sector = entrada.devolverSector();
        int asientoOriginal = entrada.devolverAsiento();
        
        // Si la sede original es la misma que la nueva, no hacer nada
        if (sedeOriginal.equals(nombreSede)) {
            throw new IllegalArgumentException("La entrada ya pertenece a la sede " + nombreSede);
        }
        
        // Obtener el espectáculo
        Espectaculo espectaculo = espectaculos.get(codigoEspectaculo);
        if (espectaculo == null) {
            throw new IllegalArgumentException("No se encontró el espectáculo con código " + codigoEspectaculo);
        }
        
        // Obtener las funciones
        Funcion funcionOriginal = espectaculo.getFuncion(sedeOriginal);
        Funcion funcionNueva = espectaculo.getFuncion(nombreSede);
        
        if (funcionOriginal == null) {
            throw new IllegalArgumentException("No se encontró la función original para la sede " + sedeOriginal);
        }
        if (funcionNueva == null) {
            throw new IllegalArgumentException("No se encontró función programada para la sede " + nombreSede);
        }
        
        // Verificar disponibilidad del asiento en la nueva sede
        if (funcionNueva.verificarDisponibilidad(sector, asientoOriginal)) {
            // Si el asiento está disponible, cambiar la sede directamente
            funcionOriginal.sumarAsiento(sector, asientoOriginal);
            funcionNueva.venderAsiento(sector, asientoOriginal);
            entrada.cambiarSede(nombreSede, funcionNueva.getFecha());
        } else {
            // Si el asiento no está disponible, buscar otro asiento disponible en el mismo sector
            int[]  nuevoAsiento = seleccionarNuevoAsiento(funcionNueva, sector);
            if (nuevoAsiento == null) {
                throw new IllegalArgumentException("No hay asientos disponibles en el sector " + 
                                                sector + " de la sede " + nombreSede);
            }
            
            funcionOriginal.sumarAsiento(sector, asientoOriginal);
           
            funcionNueva.venderAsiento(sector, nuevoAsiento[0], nuevoAsiento[1]); // fila, asiento
            entrada.cambiarSede(nombreSede, funcionNueva.getFecha(), nuevoAsiento[0], nuevoAsiento[1]);
        }
    }

    private int[] seleccionarNuevoAsiento(Funcion funcionNueva, String sector) {
        if (!funcionNueva.esNumerada()) {
            return null; // no aplica para no numeradas
        }

        int[][] grilla = funcionNueva.getGrillaSector(sector); // método que vamos a agregar

        if (grilla == null) return null;

        for (int fila = 0; fila < grilla.length; fila++) {
            for (int asiento = 0; asiento < grilla[fila].length; asiento++) {
                if (grilla[fila][asiento] == 0) {
                    return new int[]{fila, asiento};
                }
            }
        }

        return null;
    }


	/**
     * Calcula el costo de una entrada autenticando al usuario.
     * @param email Email del usuario.
     * @param contraseña Contraseña del usuario.
     * @param codigoEntrada Código de la entrada.
     * @return Costo de la entrada.
     * @throws IllegalArgumentException Si el usuario no existe, la contraseña es incorrecta,
     * o la entrada no pertenece al usuario.
     */
    public double calcularCostoEntrada(String email, String contrasenia, String codigoEntrada) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (codigoEntrada == null || codigoEntrada.isEmpty()) {
            throw new IllegalArgumentException("El código de entrada no puede estar vacío");
        }
        
        // Verificar si el usuario existe y la contraseña es correcta
        if (!autenticarUsuario(email, contrasenia)) {
            throw new IllegalArgumentException("Usuario no encontrado o contraseña incorrecta");
        }
        
        Usuario usuario = usuarios.get(email);
        Entrada entrada = usuario.getEntrada(codigoEntrada);
        
        if (entrada == null) {
            throw new IllegalArgumentException("La entrada con código " + codigoEntrada + 
                                            " no pertenece al usuario con email " + email);
        }
        
        return entrada.devolverValor();
    }

    /**
     * Consulta el valor de una entrada a un espectáculo.
     * @param codigoEspectaculo Código del espectáculo.
     * @param nombreSede Nombre de la sede.
     * @param sector Sector.
     * @return Valor de la entrada.
     * @throws IllegalArgumentException Si el espectáculo no existe, la sede no existe,
     * o el sector no es válido para esa sede.
     */
    public double consultarValorEntrada(String codigoEspectaculo, String nombreSede, String sector) {
        if (codigoEspectaculo == null || codigoEspectaculo.isEmpty()) {
            throw new IllegalArgumentException("El código del espectáculo no puede estar vacío");
        }
        if (nombreSede == null || nombreSede.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sede no puede estar vacío");
        }
        
        // Verificar si el espectáculo existe
        if (!espectaculos.containsKey(codigoEspectaculo)) {
            throw new IllegalArgumentException("No existe un espectáculo con el código: " + codigoEspectaculo);
        }
        
        // Verificar si la sede existe
        if (!sedes.containsKey(nombreSede)) {
            throw new IllegalArgumentException("No existe una sede con el nombre: " + nombreSede);
        }
        
        Espectaculo espectaculo = espectaculos.get(codigoEspectaculo);
        Sede sede = sedes.get(nombreSede);
        
        // Si la sede es un estadio, el sector se ignora según la especificación
        if (sede instanceof Estadio) {
            sector = "Campo"; // Valor por defecto para estadios
        } else if (sector == null || sector.isEmpty()) {
            throw new IllegalArgumentException("El sector no puede estar vacío para sedes de tipo Teatro o Miniestadio");
        }
        
        // Obtener la función
        Funcion funcion = espectaculo.getFuncion(nombreSede);
        if (funcion == null) {
            throw new IllegalArgumentException("El espectáculo no tiene función programada en la sede: " + nombreSede);
        }

        return funcion.devolverPrecio(sector);
    }

    /**
     * Calcula el total recaudado para un espectáculo.
     * @param codigoEspectaculo Código del espectáculo.
     * @return Total recaudado.
     * @throws IllegalArgumentException Si el espectáculo no existe.
     */
    public double calcularTotalRecaudado(String codigoEspectaculo) {
        if (codigoEspectaculo == null || codigoEspectaculo.isEmpty()) {
            throw new IllegalArgumentException("El código del espectáculo no puede estar vacío");
        }
        
        // Verificar si el espectáculo existe
        if (!espectaculos.containsKey(codigoEspectaculo)) {
            throw new IllegalArgumentException("No existe un espectáculo con el código: " + codigoEspectaculo);
        }
        
        Espectaculo espectaculo = espectaculos.get(codigoEspectaculo);
        return espectaculo.getTotalRecaudado();
    }

    /**
     * Verifica que un usuario exista y la contraseña sea correcta.
     * @param email Email del usuario.
     * @param contraseña Contraseña del usuario.
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
     * @param nombreSede Nombre de la sede.
     * @param fecha Fecha a verificar.
     * @return true si la sede está disponible en esa fecha, false en caso contrario.
     */
    public boolean verificarDisponibilidad(String nombreSede, Fecha fecha) {
        // Verificar todas las funciones de todos los espectáculos
        for (Espectaculo espectaculo : espectaculos.values()) {
            Map<String, Funcion> funciones = espectaculo.getFunciones();
            if (funciones.containsKey(nombreSede)) {
                Funcion funcion = funciones.get(nombreSede);
                if (funcion.getFecha().equals(fecha)) {
                    return false; // Ya hay un espectáculo programado en esta sede y fecha
                }
            }
        }
        return true; // No hay espectáculos programados en esta sede y fecha
    }
    

    
    
    
    /**
     * Permite seleccionar un nuevo asiento en el mismo sector.
     * @param funcion Función donde se buscará el asiento.
     * @param sector Sector donde se buscará el asiento.
     * @return Número del nuevo asiento disponible o null si no hay asientos disponibles.
     */
    /**
     * Obtiene un usuario por su email.
     * @param email Email del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuario getUsuario(String email) {
        return usuarios.get(email);
    }

    /**
     * Obtiene un espectáculo por su código.
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
     * @return Colección de sedes registradas.
     */
    public Map<String, Sede> getSedes() {
        return new HashMap<>(sedes);
    }

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     * @return Colección de usuarios registrados.
     */
    public Map<String, Usuario> getUsuarios() {
        return new HashMap<>(usuarios);
    }

    /**
     * Obtiene todos los espectáculos registrados en el sistema.
     * @return Colección de espectáculos registrados.
     */
    public Map<String, Espectaculo> getEspectaculos() {
        return new HashMap<>(espectaculos);
    }


	


	

	

	

	@Override
	public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
	    List<IEntrada> resultado = new ArrayList<>();

	    // Buscar todas las entradas de todos los usuarios
	    for (Usuario usuario : usuarios.values()) {
	        for (Entrada entrada : usuario.listarEntradas()) {
	            // Si la entrada pertenece al espectáculo pedido
	            if (entrada.devolverEspectaculo().equals(nombreEspectaculo)) {
	                resultado.add(entrada);
	            }
	        }
	    }

	    return resultado;
	}




	



	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalRecaudado(String nombreEspectaculo) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
	    Espectaculo espectaculo = espectaculosPorNombre.get(nombreEspectaculo);

	    if (espectaculo == null) {
	        throw new IllegalArgumentException("El espectáculo no existe: " + nombreEspectaculo);
	    }

	    return espectaculo.getRecaudadoPorSede(nombreSede);
	}

	
}