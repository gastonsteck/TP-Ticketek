package ar.edu.ungs.prog2.ticketek;

import java.util.UUID;


/**
 * Clase que representa una entrada comprada por un usuario.
 * Implementa la interfaz IEntrada.
 *
 * IREP (Invariante de Representación):
 * - codigoEntrada != null && !codigoEntrada.isEmpty()
 * - nombreEspectaculo != null && !nombreEspectaculo.isEmpty()
 * - nombreSede != null && !nombreSede.isEmpty()
 * - fecha != null
 * - sector != null && !sector.isEmpty()
 * - valorFinal >= 0.0
 * - emailUsuario != null && !emailUsuario.isEmpty()
 * - Si sector.equals("Campo") entonces numAsiento == null && fila == null
 * - Si !sector.equals("Campo") entonces numAsiento != null && numAsiento > 0 && fila != null && fila > 0
 * - anulada es un valor booleano válido (true o false)
 * - Si anulada == true, la entrada no puede ser reutilizada para nuevas operaciones de venta
 * - codigoEntrada es único (generado por UUID)
 * - devolverAsiento() devuelve -1 si numAsiento == null, sino devuelve numAsiento
 * - ubicacion() devuelve "CAMPO" si sector.equals("Campo"), sino devuelve formato "sector f:fila a:asiento"
 */
public class Entrada implements IEntrada {
    private String codigoEntrada;
    private String nombreEspectaculo;
    private String nombreSede;
    private Fecha fecha;
    private String sector;
    private Integer numAsiento;
    private double valorFinal;
    private Integer fila;
    private String emailUsuario;
    private boolean anulada=false;
    /**
     * Constructor para una entrada sin asiento numerado (sector "Campo").
     *
     * @param nombreEspectaculo Nombre del espectáculo
     * @param nombreSede        Nombre de la sede
     * @param fecha             Fecha de la función
     * @param valorFinal        Valor final de la entrada
     * @param emailUsuario      Email del usuario que compró la entrada
     */
    public Entrada(String nombreEspectaculo, String nombreSede,
                   Fecha fecha, double valorFinal, String emailUsuario) {
        this.codigoEntrada = UUID.randomUUID().toString();
        this.nombreEspectaculo = nombreEspectaculo;
        this.nombreSede = nombreSede;
        this.fecha = fecha;
        this.sector = "Campo";
        this.fila = null;
        this.numAsiento = null;
        this.valorFinal = valorFinal;
        this.emailUsuario = emailUsuario;
    }

    /**
     * Constructor completo para una entrada con asiento numerado.
     *
     * @param nombreEspectaculo Nombre del espectáculo
     * @param nombreSede        Nombre de la sede
     * @param fecha             Fecha de la función
     * @param sector            Sector donde está la entrada
     * @param fila              Fila del asiento
     * @param numAsiento        Número del asiento
     * @param valorFinal        Valor final de la entrada
     * @param emailUsuario      Email del usuario que compró la entrada
     */
    public Entrada(String nombreEspectaculo,
                   String nombreSede, Fecha fecha, String sector, int fila, int numAsiento, double valorFinal, String emailUsuario) {
        this.codigoEntrada = UUID.randomUUID().toString();
        this.nombreEspectaculo = nombreEspectaculo;
        this.nombreSede = nombreSede;
        this.fecha = fecha;
        this.sector = sector;
        this.fila = fila;
        this.numAsiento = numAsiento;
        this.valorFinal = valorFinal;
        this.emailUsuario = emailUsuario;
    }

    /**
     * Devuelve el valor final de la entrada.
     *
     * @return valor final
     */
    public double devolverValor() {
        return valorFinal;
    }

    /**
     * Indica si la fecha de la entrada es futura.
     *
     * @return true si la fecha es futura, false en caso contrario
     */
    public boolean esFutura() {
        return fecha.esFutura();
    }

    /**
     * Obtiene el email del usuario que compró la entrada.
     *
     * @return email del usuario
     */
    public String getEmailUsuario() {
        return emailUsuario;
    }

    /**
     * Devuelve el nombre de la sede.
     *
     * @return nombre de la sede
     */
    public String devolverSede() {
        return nombreSede;
    }

    /**
     * Devuelve el sector de la entrada.
     *
     * @return sector
     */
    public String devolverSector() {
        return sector;
    }

    /**
     * Devuelve el número de asiento.
     *
     * @return número de asiento, puede ser null si es sector "Campo"
     */
    public int devolverAsiento() {
    	if (numAsiento == null)
    		return -1;
        return numAsiento;
    }

    /**
     * Devuelve el código único de la entrada.
     *
     * @return código de la entrada
     */
    public String devolverCodigo() {
        return codigoEntrada;
    }

    /**
     * Devuelve la fila del asiento.
     *
     * @return número de fila, puede ser null
     */
    public int devolverFila() {
        return fila;
    }

    /**
     * Obtiene el nombre del espectáculo.
     *
     * @return nombre del espectáculo
     */
    public String getNombreEspectaculo() {
        return nombreEspectaculo;
    }

    /**
     * Obtiene la fecha de la función.
     *
     * @return objeto Fecha
     */
    public Fecha getFecha() {
        return fecha;
    }

    /**
     * Devuelve la ubicación de la entrada en texto.
     *
     * @return descripción de la ubicación
     */
    @Override
    public String ubicacion() {
        if (sector.equals("Campo"))
            return "CAMPO";
        else
            return sector + " f:" + fila + " a:" + numAsiento;
    }

    /**
     * Representación en texto de la entrada.
     *
     * @return String con información de la entrada
     */
    @Override
    public String toString() {
        String fechaStr = fecha.toString();
        if (!fecha.esFutura())
            fechaStr += " P";

        return "- " + codigoEntrada + " - " + nombreEspectaculo + " - " + fechaStr + " - " + nombreSede + " - " + ubicacion();
    }

    /**
     * Cambia la sede y fecha de la entrada.
     *
     * @param nombreSede2 Nueva sede
     * @param fecha2      Nueva fecha
     */
    public void cambiarSede(String nombreSede2, Fecha fecha2) {
        this.nombreSede = nombreSede2;
        this.fecha = fecha2;
    }

    /**
     * Cambia la sede, fecha y número de asiento de la entrada.
     *
     * @param nombreSede2 Nuevo nombre de la sede
     * @param fecha2      Nueva fecha
     * @param nuevoAsiento Nuevo número de asiento
     */
    public void cambiarSede(String nombreSede2, Fecha fecha2, Integer nuevoAsiento) {
        nombreSede = nombreSede2;
        fecha = fecha2;
        numAsiento = nuevoAsiento;
    }

    /**
     * Cambia la sede, fecha, fila y número de asiento de la entrada.
     *
     * @param nombreSede2 Nuevo nombre de la sede
     * @param fecha2      Nueva fecha
     * @param fila        Nueva fila
     * @param asiento     Nuevo número de asiento
     */
    public void cambiarSede(String nombreSede2, Fecha fecha2, int fila, int asiento) {
        this.nombreSede = nombreSede2;
        this.fecha = fecha2;
        this.fila = fila;
        this.numAsiento = asiento;
    }

    /**
     * Devuelve el precio de la entrada (implementación de IEntrada).
     *
     * @return valor final
     */
    @Override
    public double precio() {
        return valorFinal;
    }
    
    /**
     * Indica si la entrada ha sido anulada.
     *
     * @return true si la entrada está anulada, false en caso contrario.
     */
    public boolean estaAnulada() {
        return anulada;
    }

    /**
     * Marca la entrada como anulada.
     * Establece el estado de la entrada en anulada.
     */
    public void anular() {
        this.anulada = true;
    }

    
    
}
