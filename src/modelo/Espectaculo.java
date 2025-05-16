package modelo;

import java.util.*;

public class Espectaculo {
    private String nombre;
    private List<Funcion> funciones;
    private double totalRecaudado;

    public Espectaculo(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del espectáculo no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
        this.funciones = new ArrayList<>();
        this.totalRecaudado = 0;
    }

    public void agregarFuncion(Funcion funcionNueva) {
        Fecha fechaDeLaFuncionNueva = funcionNueva.getFecha();

        for (Funcion funcionExistente : funciones) {
            Fecha fechaDeLaFuncionExistente = funcionExistente.getFecha();

            if (fechaDeLaFuncionExistente.equals(fechaDeLaFuncionNueva)) {
                throw new IllegalArgumentException("Ya existe una función registrada en esa fecha.");
            }
        }

        funciones.add(funcionNueva);
    }


    public void sumarRecaudado(double monto) {
        totalRecaudado += monto;
    }

    public void restarRecaudado(double monto) {
        totalRecaudado -= monto;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public double getTotalRecaudado() {
        return totalRecaudado;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Espectáculo: " + nombre + " - Funciones: " + funciones.size() +
               " - Recaudación total: $" + totalRecaudado;
    }
}

