package alquiler.clases;

import alquiler.enums.TipoServicio;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestionAlquiler {
    private Map<TipoServicio, Map<Integer, List<Alquiler>>> mapaAlquileres;
    private List<Alquiler> listaAlquileres;
    // Cada vez que se ejecuta el programa, mapaAlquileres se carga con la informacion que persiste a traves
    // de listaAlquileres

    // Constructores
    public GestionAlquiler() {
        this.mapaAlquileres = new HashMap<>();
        this.listaAlquileres = new ArrayList<>();
    }

    public GestionAlquiler(List<Alquiler> listaAlquileres) {
        this.listaAlquileres = listaAlquileres;
        this.mapaAlquileres = reconstruirMapa();
    }

    // Getters y Setters
    public Map<TipoServicio, Map<Integer, List<Alquiler>>> getMapaAlquileres() {
        return mapaAlquileres;
    }

    public void setMapaAlquileres(Map<TipoServicio, Map<Integer, List<Alquiler>>> mapaAlquileres) {
        this.mapaAlquileres = mapaAlquileres;
    }

    public List<Alquiler> getListaAlquileres() {
        return listaAlquileres;
    }

    public void setListaAlquileres(List<Alquiler> listaAlquileres) {
        this.listaAlquileres = listaAlquileres;
        this.mapaAlquileres = reconstruirMapa(); // Reconstruimos el mapa al actualizar la lista
    }

    // Método para agregar un alquiler a la lista y al mapa
    public void agregarAlquiler(Alquiler alquiler) {
        // Agregar a la lista
        this.listaAlquileres.add(alquiler);

        // Agregar al mapa
        TipoServicio tipo = alquiler.getTipoServicio();
        int idServicio = alquiler.getIdServicio();

        this.mapaAlquileres
                .computeIfAbsent(tipo, k -> new HashMap<>())
                .computeIfAbsent(idServicio, k -> new ArrayList<>())
                .add(alquiler);
    }

    // Método para reconstruir el mapa a partir de la lista de alquileres
    public Map<TipoServicio, Map<Integer, List<Alquiler>>> reconstruirMapa() {
        Map<TipoServicio, Map<Integer, List<Alquiler>>> nuevoMapa = new HashMap<>();

        for (Alquiler alquiler : listaAlquileres) {
            TipoServicio tipo = alquiler.getTipoServicio();
            int idServicio = alquiler.getIdServicio();

            nuevoMapa
                    .computeIfAbsent(tipo, k -> new HashMap<>())
                    .computeIfAbsent(idServicio, k -> new ArrayList<>())
                    .add(alquiler);
        }

        return nuevoMapa;
    }

    // Método para obtener las carpas/sombrillas/plazas disponibles en una fecha.Retorna una lista con los ids
    public List<Integer>  obtenerIdsDisponibles(TipoServicio tipoServicio, LocalDate fechaInicio, LocalDate fechaFin) {
        if (!mapaAlquileres.containsKey(tipoServicio)) {
            return Collections.emptyList(); // No hay datos para este tipo de servicio
        }

        Map<Integer, List<Alquiler>> alquileresPorId = mapaAlquileres.get(tipoServicio);

        return alquileresPorId.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .noneMatch(alquiler -> alquiler.getFechaAlta().isBefore(fechaFin)
                                && alquiler.getFechaBaja().isAfter(fechaInicio)))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

