package modelo;

import java.util.*;

public class Espectaculo {

    private String nombre;
    private String codigo;
    private List<Sede> sedes;
    private List<Fecha> fechas;
    private Map<String, Funcion> funciones;
    private double totalRecaudado;

    public Espectaculo(String nombre, String codigo, List<Sede> sedes, List<Fecha> fechas, List<Double> preciosBases) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.sedes = new ArrayList<>(sedes);
        this.fechas = new ArrayList<>(fechas);
        this.funciones = new HashMap<>();
        this.totalRecaudado = 0;
    }

    public void agregarFuncion(String fechaStr, Funcion funcion) {
        if (funciones.containsKey(fechaStr)) {
            throw new RuntimeException("Ya hay una función en esa fecha.");
        }
        funciones.put(fechaStr, funcion);
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

    public String getCodigo() {
        return codigo;
    }

    public List<Sede> getSedes() {
        return sedes;
    }

    public List<Fecha> getFechas() {
        return fechas;
    }
}


