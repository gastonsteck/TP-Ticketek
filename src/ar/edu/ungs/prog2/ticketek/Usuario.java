package ar.edu.ungs.prog2.ticketek;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa a un usuario del sistema Ticketek.
 * Un usuario tiene email, nombre, apellido, contraseña y un conjunto de entradas compradas.
 *
 * IREP (Invariante de Representación):
 * - email != null && !email.isEmpty()
 * - nombre != null && !nombre.isEmpty()
 * - apellido != null && !apellido.isEmpty()
 * - contrasenia != null && !contrasenia.isEmpty()
 * - entradas != null (puede estar vacío pero no null)
 * - Para todo codigoEntrada en entradas.keySet(): codigoEntrada != null && !codigoEntrada.isEmpty()
 * - Para toda entrada en entradas.values(): entrada != null
 * - Para toda entrada en entradas.values(): entrada.getEmailUsuario().equals(this.email)
 * - No existen códigos de entradas duplicados (garantizado por HashMap)
 * - verificarContrasenia(c) == true sii c.equals(this.contrasenia)
 * - listarEntradasFuturas() devuelve solo entradas donde entrada.esFutura() == true
 * - toString() devuelve formato "nombre apellido (email)"
 */
public class Usuario {

    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private Map<String, Entrada> entradas;

    /**
     * Constructor que crea un usuario con su email, nombre, apellido y contraseña.
     * Inicializa el mapa de entradas vacío.
     * 
     * @param email        Email único del usuario
     * @param nombre       Nombre del usuario
     * @param apellido     Apellido del usuario
     * @param contrasenia  Contraseña del usuario
     */
    public Usuario(String email, String nombre, String apellido, String contrasenia) {
    	chequeosConstructor(email, nombre, apellido, contrasenia);
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.entradas = new HashMap<>();
    }
    
    
    /**
     * Verifica que los datos ingresados al constructor del usuario sean válidos.
     *
     *
     * @param email        Correo electrónico del usuario (no puede ser nulo ni vacío).
     * @param nombre       Nombre del usuario (no puede ser nulo ni vacío).
     * @param apellido     Apellido del usuario (no puede ser nulo ni vacío).
     * @param contrasenia  Contraseña del usuario (no puede ser nula ni vacía).
     *
     * @throws IllegalArgumentException si alguno de los parámetros es inválido.
     */
    private void chequeosConstructor(String email, String nombre, String apellido, String contrasenia) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
    }

    /**
     * Verifica si la contraseña pasada coincide con la del usuario.
     * 
     * @param contrasenia Contraseña a verificar
     * @return true si coincide, false en caso contrario
     */
    public boolean verificarContrasenia(String contraseniaRecibida) {

        return this.contrasenia.equals(contraseniaRecibida);
    }

    /**
     * Registra la compra de una entrada asociándola con su código.
     * 
     * @param codigoEntrada Código único de la entrada
     * @param entrada       Objeto Entrada comprado
     */
    public void comprarEntrada(String codigoEntrada, Entrada entrada) {
        entradas.put(codigoEntrada, entrada);
    }

    /**
     * Elimina una entrada del usuario, simulando un reembolso o cancelación.
     * 
     * @param codigoEntrada Código único de la entrada a eliminar
     */
    public void reembolsarEntrada(String codigoEntrada) {
        entradas.remove(codigoEntrada);
    }

    /**
     * Devuelve una lista con todas las entradas del usuario (pasadas y futuras).
     * 
     * @return Lista de objetos Entrada
     */
    public List<Entrada> listarEntradas() {
        return new ArrayList<>(entradas.values());
    }

    /**
     * Devuelve una lista con las entradas futuras (no vencidas) del usuario.
     * 
     * @return Lista de entradas cuya fecha es futura
     */
    public List<Entrada> listarEntradasFuturas() {
        List<Entrada> lista = new ArrayList<>();
        Iterator<Entrada> iterator = listarEntradas().iterator();
        
        while (iterator.hasNext()) {
            Entrada entrada = iterator.next();
            if (entrada.esFutura()) {
                lista.add(entrada);
            }
        }

        return lista;
        
    }

    /**
     * Representación en texto del usuario, mostrando nombre, apellido y email.
     * 
     * @return String con información del usuario
     */
    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + email + ")";
    }

    /**
     * @return Nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return Apellido del usuario
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return Mapa con las entradas del usuario, donde la clave es el código de la entrada
     */
    public Map<String, Entrada> getEntradas() {
        return entradas;
    }

    /**
     * Obtiene una entrada específica por su código.
     * 
     * @param codigoEntrada Código de la entrada buscada
     * @return Entrada correspondiente o null si no existe
     */
    public Entrada getEntrada(String codigoEntrada) {
        return entradas.get(codigoEntrada);
    }

    /**
     * Devuelve la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Devuelve el email del usuario.
     * 
     * @return El email del usuario.
     */
    public String getEmail() {
        return email;
    }

}
