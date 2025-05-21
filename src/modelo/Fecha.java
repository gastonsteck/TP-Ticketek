package modelo;

import java.time.LocalDate;

//Clase que representa una fecha simple con día, mes y año
public class Fecha {
    private int dia;
    private int mes;
    private int anio;
    
    
 // Clase que representa una fecha simple con día, mes y año
    public Fecha(int dia,int mes,int anio) {
    	this.dia=dia;
    	this.mes=mes;
    	this.anio=anio;
    }
 // Método que indica si la fecha es futura 
    public boolean esFutura() {
        LocalDate hoy = LocalDate.now();
        LocalDate estaFecha = LocalDate.of(anio, mes, dia);
        return estaFecha.isAfter(hoy);
    }
    
    public String enNumero() {
        return String.format("%02d%02d%04d", dia, mes, anio);
    }
    
 // Devuelve la fecha en formato texto (ej: 15/5/2025)
    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }

    public static Fecha desdeString(String fechaStr) {
        String[] partes = fechaStr.split("/");

        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt("20" + partes[2]); // convierte "25" → "2025"

        // Validación implícita con LocalDate
        java.time.LocalDate.of(anio, mes, dia); // si es inválida, lanza error

        return new Fecha(dia, mes, anio);
    }


}
