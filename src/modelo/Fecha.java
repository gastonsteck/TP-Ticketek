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
    
    
 // Devuelve la fecha en formato texto (ej: 15/5/2025)
    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }

}
