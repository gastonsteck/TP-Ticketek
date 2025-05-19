package modelo;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Clase principal del sistema de gestión de entradas para espectáculos.
 * Permite administrar usuarios, sedes y espectáculos, así como gestionar
 * la venta y administración de entradas.
 */
public class Ticketek {
    private Map<String, Usuario> usuarios;
    private Map<String, Espectaculo> espectaculos;
    private Map<String, Sede> sedes;

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
     * @param nombre Nombre del estadio.
     * @param capacidad Capacidad total del estadio.
     * @param direccion Dirección del estadio.
     * @throws IllegalArgumentException Si el nombre ya existe o los datos son inválidos.
     */
    public void registrarEstadio(String nombre, int capacidad, String direccion) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del estadio no puede estar vacío");
        }
        if (sedes.containsKey(nombre)) {
            throw new IllegalArgumentException("Ya existe una sede con el nombre: " + nombre);
        }
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        }

        Estadio estadio = new Estadio(nombre, capacidad, direccion);
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
    public void registrarTeatro(String nombre, int capacidad, String direccion, int cantFilas, int asientosPorFila) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del teatro no puede estar vacío");
        }
        if (sedes.containsKey(nombre)) {
            throw new IllegalArgumentException("Ya existe una sede con el nombre: " + nombre);
        }
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        }
        if (cantFilas <= 0) {
            throw new IllegalArgumentException("La cantidad de filas debe ser mayor que cero");
        }
        if (asientosPorFila <= 0) {
            throw new IllegalArgumentException("La cantidad de asientos por fila debe ser mayor que cero");
        }

        // Verificar que la capacidad coincida con el número de asientos calculados
        // (Se asume 4 sectores según la especificación)
        int asientosCalculados = cantFilas * asientosPorFila * 4;
        if (asientosCalculados != capacidad) {
            throw new IllegalArgumentException(
                "La capacidad indicada (" + capacidad + ") no coincide con el número de asientos calculados (" 
                + asientosCalculados + ")");
        }

        Teatro teatro = new Teatro(nombre, capacidad, direccion, cantFilas, asientosPorFila);
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
    public void registrarMiniestadio(String nombre, int capacidad, String direccion, int cantFilas, 
                                    int asientosPorFila, int cantPuestosMerch, int cantPuestosComida, 
                                    double valorFijo) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del miniestadio no puede estar vacío");
        }
        if (sedes.containsKey(nombre)) {
            throw new IllegalArgumentException("Ya existe una sede con el nombre: " + nombre);
        }
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        }
        if (cantFilas <= 0) {
            throw new IllegalArgumentException("La cantidad de filas debe ser mayor que cero");
        }
        if (asientosPorFila <= 0) {
            throw new IllegalArgumentException("La cantidad de asientos por fila debe ser mayor que cero");
        }
        if (cantPuestosMerch < 0) {
            throw new IllegalArgumentException("La cantidad de puestos de merchandising no puede ser negativa");
        }
        if (cantPuestosComida < 0) {
            throw new IllegalArgumentException("La cantidad de puestos de comida no puede ser negativa");
        }
        if (valorFijo < 0) {
            throw new IllegalArgumentException("El valor fijo no puede ser negativo");
        }

        // Verificar que la capacidad coincida con el número de asientos calculados
        // (Se asume 4 sectores según la especificación)
        int asientosCalculados = cantFilas * asientosPorFila * 4;
        if (asientosCalculados != capacidad) {
            throw new IllegalArgumentException(
                "La capacidad indicada (" + capacidad + ") no coincide con el número de asientos calculados (" 
                + asientosCalculados + ")");
        }

        Miniestadio miniestadio = new Miniestadio(nombre, capacidad, direccion, cantFilas, 
                                                asientosPorFila, cantPuestosMerch, cantPuestosComida, valorFijo);
        sedes.put(nombre, miniestadio);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param email Email del usuario (único).
     * @param nombreCompleto Nombre completo del usuario.
     * @param contraseña Contraseña del usuario.
     * @throws IllegalArgumentException Si el email ya existe o los datos son inválidos.
     */
    public void registrarUsuario(String email, String nombreCompleto, String contrasenia) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (nombreCompleto == null || nombreCompleto.isEmpty()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacío");
        }
        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (usuarios.containsKey(email)) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + email);
        }

        Usuario usuario = new Usuario(email, nombreCompleto, contrasenia);
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
    public void registrarEspectaculo(String nombre, String codigo, List<Sede> listaSedes, 
                                    List<Fecha> fechas, List<Double> preciosBases) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del espectáculo no puede estar vacío");
        }
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El código del espectáculo no puede estar vacío");
        }
        if (espectaculos.containsKey(codigo)) {
            throw new IllegalArgumentException("Ya existe un espectáculo con el código: " + codigo);
        }
        if (listaSedes == null || listaSedes.isEmpty()) {
            throw new IllegalArgumentException("La lista de sedes no puede estar vacía");
        }
        if (fechas == null || fechas.isEmpty()) {
            throw new IllegalArgumentException("La lista de fechas no puede estar vacía");
        }
        if (preciosBases == null || preciosBases.isEmpty()) {
            throw new IllegalArgumentException("La lista de precios base no puede estar vacía");
        }
        if (listaSedes.size() != fechas.size() || listaSedes.size() != preciosBases.size()) {
            throw new IllegalArgumentException("La cantidad de sedes debe coincidir con la cantidad de fechas y precios");
        }

        // Verificar que todas las sedes existan y que no haya espectáculos programados en las mismas fechas
        for (int i = 0; i < listaSedes.size(); i++) {
            Sede sede = listaSedes.get(i);
            Fecha fecha = fechas.get(i);
            
            if (!sedes.containsValue(sede)) {
                throw new IllegalArgumentException("La sede " + sede.getNombre() + " no está registrada en el sistema");
            }
            
            if (!verificarDisponibilidad(sede.getNombre(), fecha)) {
                throw new IllegalArgumentException("La sede " + sede.getNombre() + 
                                                " ya tiene un espectáculo programado en la fecha " + fecha);
            }
        }

        Espectaculo espectaculo = new Espectaculo(nombre, codigo, listaSedes, fechas, preciosBases);
        espectaculos.put(codigo, espectaculo);
    }

    /**
     * Vende una entrada a un usuario.
     * @param email Email del usuario.
     * @param codigoEspectaculo Código del espectáculo.
     * @param nombreSede Nombre de la sede.
     * @param asientos Lista de números de asientos.
     * @param sector Sector (ignorado en estadios).
     * @param contraseña Contraseña del usuario.
     * @throws IllegalArgumentException Si algún dato es inválido, el usuario o espectáculo no existen,
     * la contraseña es incorrecta, o los asientos no están disponibles.
     */
    public void venderEntrada(String email, String codigoEspectaculo,  String nombreSede, 
                            List<Integer> asientos, String sector, String contrasenia) {
        // Verificaciones
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (codigoEspectaculo == null || codigoEspectaculo.isEmpty()) {
            throw new IllegalArgumentException("El código del espectáculo no puede estar vacío");
        }
        if (nombreSede == null || nombreSede.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sede no puede estar vacío");
        }
        if (asientos == null || asientos.isEmpty()) {
            throw new IllegalArgumentException("La lista de asientos no puede estar vacía");
        }
        
        // Verificar si el usuario existe y la contraseña es correcta
        if (!autenticarUsuario(email, contrasenia)) {
            throw new IllegalArgumentException("Usuario no encontrado o contraseña incorrecta");
        }
        
        // Obtener usuario
        Usuario usuario = usuarios.get(email);
        
        // Verificar si el espectáculo existe
        if (!espectaculos.containsKey(codigoEspectaculo)) {
            throw new IllegalArgumentException("No existe un espectáculo con el código: " + codigoEspectaculo);
        }
        
        // Obtener espectáculo
        Espectaculo espectaculo = espectaculos.get(codigoEspectaculo);
        
        // Verificar si la sede existe
        if (!sedes.containsKey(nombreSede)) {
            throw new IllegalArgumentException("No existe una sede con el nombre: " + nombreSede);
        }
        
        // Obtener sede
        Sede sede = sedes.get(nombreSede);
        
        // Si la sede es un estadio, el sector se ignora
        if (sede instanceof Estadio) {
            sector = "General"; // Valor por defecto para estadios
        } else if (sector == null || sector.isEmpty()) {
            throw new IllegalArgumentException("El sector no puede estar vacío para sedes de tipo Teatro o Miniestadio");
        }
        
        // Verificar disponibilidad de asientos y vender entradas
        Funcion funcion = espectaculo.getFuncion(nombreSede);
        if (funcion == null) {
            throw new IllegalArgumentException("El espectáculo no tiene función programada en la sede: " + nombreSede);
        }
       
        
        double valorTotal = 0;
        List<String> codigosEntradas = new ArrayList<>();
        
        for (Integer asiento : asientos) {
            if (!funcion.verificarDisponibilidad(sector, asiento)) {
                // Si algún asiento no está disponible, anular todas las ventas anteriores
                for (String codigoEntrada : codigosEntradas) {
                    anularEntrada(email, contrasenia, codigoEntrada);
                }
                throw new IllegalArgumentException("El asiento " + asiento + " del sector " + sector + " no está disponible");
            }
            
            // Si está disponible, vender el asiento
            funcion.venderAsiento(sector, asiento);
            
            // Calcular precio de la entrada según el tipo de sede
            double precioBase = funcion.getPrecioBase();
            double valorFinal = sede.calcularPrecioEntrada(precioBase, sector);
            valorTotal += valorFinal;
            
            // Generar código único para la entrada
            String codigoEntrada = generarCodigoEntrada(codigoEspectaculo, nombreSede);
            codigosEntradas.add(codigoEntrada);
            
            // Crear entrada y asignarla al usuario
            Entrada entrada = new Entrada(codigoEntrada, codigoEspectaculo, espectaculo.getNombre(), 
                                        nombreSede, funcion.getFecha(), sector, asiento, valorFinal);
            usuario.obtenerEntrada(codigoEntrada, entrada);
            
            // Sumar al total recaudado del espectáculo
            espectaculo.sumarRecaudado(valorFinal);
        }
    }

    /**
     * Lista las sedes para un espectáculo.
     * @param codigo Código del espectáculo.
     * @return Lista de sedes donde se realizará el espectáculo.
     * @throws IllegalArgumentException Si el espectáculo no existe.
     */
    public List<Sede> listarSedesParaEspectaculo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El código del espectáculo no puede estar vacío");
        }
        if (!espectaculos.containsKey(codigo)) {
            throw new IllegalArgumentException("No existe un espectáculo con el código: " + codigo);
        }
        
        Espectaculo espectaculo = espectaculos.get(codigo);
        return espectaculo.getSedes();
    }

    /**
     * Lista todas las entradas compradas por un usuario.
     * @param email Email del usuario.
     * @return Lista de entradas compradas.
     * @throws IllegalArgumentException Si el usuario no existe.
     */
    public List<Entrada> listarEntradas(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (!usuarios.containsKey(email)) {
            throw new IllegalArgumentException("No existe un usuario con el email: " + email);
        }
        
        Usuario usuario = usuarios.get(email);
        return usuario.listarEntradas();
    }

    /**
     * Lista las entradas futuras compradas por un usuario.
     * @param email Email del usuario.
     * @return Lista de entradas futuras.
     * @throws IllegalArgumentException Si el usuario no existe.
     */
    public List<Entrada> listarEntradasFuturas(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (!usuarios.containsKey(email)) {
            throw new IllegalArgumentException("No existe un usuario con el email: " + email);
        }
        
        Usuario usuario = usuarios.get(email);
        return usuario.listarEntradasFuturas();
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
            Integer nuevoAsiento = seleccionarNuevoAsiento(funcionNueva, sector);
            if (nuevoAsiento == null) {
                throw new IllegalArgumentException("No hay asientos disponibles en el sector " + 
                                                sector + " de la sede " + nombreSede);
            }
            
            funcionOriginal.sumarAsiento(sector, asientoOriginal);
            funcionNueva.venderAsiento(sector, nuevoAsiento);
            entrada.cambiarSede(nombreSede, funcionNueva.getFecha(), nuevoAsiento);
        }
    }

    private Integer seleccionarNuevoAsiento(Funcion funcionNueva, String sector) {
		funcionNueva.asientoDisponible(sector);
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
    
    public String generarCodigoEntrada(String codigoEspectaculo, String sede) {
    	Random random = new Random();
    	return codigoEspectaculo + espectaculos.get(codigoEspectaculo).getFunciones().get(sede).getFecha().enNumero() + String.format("%09d", random.nextInt(1000000000));
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
}