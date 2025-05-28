package ar.edu.ungs.prog2.ticketek;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Clase que representa una fecha simple con día, mes y año.
 *
 * IREP (Invariante de Representación):
 * - dia >= 1 && dia <= 31
 * - mes >= 1 && mes <= 12
 * - anio >= 1900 && anio <= 3000
 * - La combinación (dia, mes, anio) debe representar una fecha válida del calendario gregoriano
 * - No puede haber fechas como 31/02 o 30/02 (febrero no tiene más de 29 días)
 * - En años no bisiestos, febrero tiene máximo 28 días
 * - Los meses 4, 6, 9, 11 tienen máximo 30 días
 * - Los meses 1, 3, 5, 7, 8, 10, 12 tienen máximo 31 días
 * - toString() devuelve formato "dd/mm/aa" donde aa son los últimos 2 dígitos del año
 * - enNumero() devuelve formato "ddmmaaaa" con año completo
 * - desdeString(String) valida que la fecha sea del calendario gregoriano antes de crear el objeto
 */
public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    /**
     * Constructor que crea una fecha con día, mes y año.
     * 
     * @param dia  Día de la fecha
     * @param mes  Mes de la fecha
     * @param anio Año de la fecha
     */
    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    /**
     * Indica si la fecha es futura con respecto a la fecha actual del sistema.
     * 
     * @return true si la fecha es posterior a hoy, false en caso contrario
     */
    public boolean esFutura() {
        LocalDate hoy = LocalDate.now();
        LocalDate estaFecha = LocalDate.of(anio, mes, dia);
        return estaFecha.isAfter(hoy);
    }

    /**
     * Devuelve la fecha en formato numérico continuo: ddMMyyyy (ejemplo: "15052025").
     * 
     * @return String con la fecha en formato numérico
     */
    public String enNumero() {
        return String.format("%02d%02d%04d", dia, mes, anio);
    }

    @Override
    public String toString() {
        // Obtener los últimos dos dígitos del año
        int anioCorto = anio % 100;
        // Formatear día, mes y año con dos dígitos para día y mes, y dos dígitos para año corto
        return String.format("%02d/%02d/%02d", dia, mes, anioCorto);
    }

    /**
     * Método estático que crea un objeto Fecha a partir de un String en formato "dd/mm/aa".
     * Convierte el año de dos dígitos a cuatro dígitos (asumiendo siglo 2000).
     * 
     * @param fechaStr Fecha en formato "dd/mm/aa"
     * @return Objeto Fecha correspondiente
     * @throws DateTimeException si la fecha no es válida
     * @throws NumberFormatException si el formato es incorrecto
     */
    public static Fecha desdeString(String fechaStr) {
        try {
            String[] partes = fechaStr.split("/");
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int anio = Integer.parseInt("20" + partes[2]);
            LocalDate.of(anio, mes, dia);
            return new Fecha(dia, mes, anio);
        } catch (DateTimeException | NumberFormatException e) {
            System.err.println("Fecha inválida: " + fechaStr);
            throw e;
        }
    }
}
