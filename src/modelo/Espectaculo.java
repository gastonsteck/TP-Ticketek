package modelo;

import java.util.*;

public class Espectaculo {

    private String nombre;
    private List<Sede> sedes;
    private List<Fecha> fechas;
    private Map<String, Funcion> funciones;
    private double totalRecaudado;
    private Map<String, Double> recaudacionPorSede = new HashMap<>();

    public Espectaculo(String nombre) {
        this.nombre = nombre;
        this.sedes = new ArrayList<>(sedes);
        this.fechas = new ArrayList<>(fechas);
        this.funciones = new HashMap<>();
        this.totalRecaudado = 0;
    }
 //lo comento temporalmente ya que capaz se usa mas adelante
   /* public void agregarFuncion(String fechaStr, Funcion funcion) {
        if (funciones.containsKey(fechaStr)) {
            throw new RuntimeException("Ya hay una función en esa fecha.");
        }
        funciones.put(fechaStr, funcion);
    }
    */
    public void agregarFuncion(Fecha fecha, Sede sede, double precioBase) {
        if (funciones == null)
            funciones = new HashMap<>();


        if (funciones.containsKey(fecha.toString())) {
            throw new IllegalArgumentException("Ya existe una función en esa sede.");
        }

        Funcion funcion = new Funcion(sede,fecha, precioBase);
        funciones.put(fecha.toString(), funcion);
    }

    
    public void sumarRecaudadoPorSede(String nombreSede, double valor) {
        if (recaudacionPorSede.containsKey(nombreSede)) {
            double actual = recaudacionPorSede.get(nombreSede);
            recaudacionPorSede.put(nombreSede, actual + valor);
        } else {
            recaudacionPorSede.put(nombreSede, valor);
        }
    }

    public double getRecaudadoPorSede(String nombreSede) {
        if (recaudacionPorSede.containsKey(nombreSede)) {
            return recaudacionPorSede.get(nombreSede);
        } else {
            return 0.0;
        }
    }

    

    public Funcion getFuncion(String fechaStr) {
        return funciones.get(fechaStr);
    }

    public void sumarRecaudado(double cantidad) {
        totalRecaudado += cantidad;
    }

    public void restarRecaudado(double cantidad) {
        totalRecaudado -= cantidad;
    }

    public double getTotalRecaudado() {
        return totalRecaudado;
    }

    public Map<String, Funcion> getFunciones() {
        return funciones;
    }

    public String getNombre() {
        return nombre;
    }


    public List<Sede> getSedes() {
        return sedes;
    }

    public List<Fecha> getFechas() {
        return fechas;
    }
}


