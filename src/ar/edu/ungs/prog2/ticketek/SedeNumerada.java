package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Map;

public abstract class SedeNumerada extends Sede {
    protected int asientosPorFila;
    protected String[] sectores;
    protected int[] capacidad;
    protected int[] porcentajeAdicional;
    protected Map<String, Map<Integer, Boolean>> sectoresPorNombre;

    public SedeNumerada(String nombre, String tipo, int capacidadMaxima, String direccion,
                        int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, tipo, capacidadMaxima, direccion);

        if (asientosPorFila <= 0)
            throw new IllegalArgumentException("La cantidad de asientos por fila debe ser mayor que cero");
        if (sectores == null || sectores.length <= 0)
            throw new IllegalArgumentException("Debe indicarse al menos un sector");
        if (capacidad == null || capacidad.length != sectores.length)
            throw new IllegalArgumentException("La cantidad de capacidades debe coincidir con los sectores");
        if (porcentajeAdicional == null || porcentajeAdicional.length != sectores.length)
            throw new IllegalArgumentException("Los porcentajes deben coincidir con los sectores");

        this.asientosPorFila = asientosPorFila;
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
        this.sectoresPorNombre = new HashMap<>();
        inicializarSectores();
    }

    protected void inicializarSectores() {
        for (int i = 0; i < sectores.length; i++) {
            Map<Integer, Boolean> mapaAsientos = new HashMap<>();
            for (int num = 1; num <= capacidad[i]; num++) {
                mapaAsientos.put(num, true);
            }
            sectoresPorNombre.put(sectores[i], mapaAsientos);
        }
    }

    @Override
    public Map<String, Map<Integer, Boolean>> getDisponiblesInicialesNumerados() {
        Map<String, Map<Integer, Boolean>> copia = new HashMap<>();
        for (String sector : sectoresPorNombre.keySet()) {
            Map<Integer, Boolean> copiaButacas = new HashMap<>(sectoresPorNombre.get(sector));
            copia.put(sector, copiaButacas);
        }
        return copia;
    }

    @Override
    public Integer getDisponiblesInicialesSinNumerar() {
        return null;
    }

    @Override
    public int getCapacidadSector(String nombreSector) {
        for (int i = 0; i < sectores.length; i++) {
            if (sectores[i].equals(nombreSector)) {
                return capacidad[i];
            }
        }
        throw new IllegalArgumentException("Sector no válido: " + nombreSector);
    }

    @Override
    public String[] getSectores() {
        return sectores;
    }

    public int getAsientosPorFila() {
        return asientosPorFila;
    }

    public int[] getCapacidad() {
        return capacidad;
    }

    public int[] getPorcentajeAdicional() {
        return porcentajeAdicional;
    }

    public Map<String, Map<Integer, Boolean>> getSectoresPorNombre() {
        return sectoresPorNombre;
    }
}