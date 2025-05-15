package modelo;

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
 // Método que debería indicar si la fecha es futura (por ahora lo dejamos true)
    public boolean esFutura() {
    	return true;
    }
    
 // Devuelve la fecha en formato texto (ej: 15/5/2025)
    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }

}
