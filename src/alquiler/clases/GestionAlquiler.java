package alquiler.clases;

import alquiler.enums.TipoServicio;
import servicio.clases.GestionServicio;
import servicio.clases.Servicio;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestionAlquiler {
    private Map<TipoServicio, Map<String, List<Alquiler>>> mapaAlquileres;
    private List<Alquiler> listaAlquileres;
    // Cada vez que se ejecuta el programa, mapaAlquileres se carga con la informacion que persiste a traves de listaAlquileres


    // Constructores
    public GestionAlquiler() {
        this.mapaAlquileres = new HashMap<>();
        this.listaAlquileres = new ArrayList<>();
    }

    // Getters y Setters
    public Map<TipoServicio, Map<String, List<Alquiler>>> getMapaAlquileres() {
        return mapaAlquileres;
    }

    public void setMapaAlquileres(Map<TipoServicio, Map<String, List<Alquiler>>> mapaAlquileres) {
        this.mapaAlquileres = mapaAlquileres;
    }

    public List<Alquiler> getListaAlquileres() {
        return listaAlquileres;
    }

    public void setListaAlquileres(List<Alquiler> listaAlquileres) {
        this.listaAlquileres = listaAlquileres;
        construirMapa(); // Se actualiza el mapa si se setea una nueva lista
    }

    // Método para agregar un Alquiler a listaAlquileres
    public boolean agregarAlquiler(Alquiler alquiler) {
        return listaAlquileres.add(alquiler);
    }

    //EL METODO TIENE QUE CONSTRUIR LOS IDS A PARTIR DE GESTION SERVICIO PARA OBTENER TODOS LOS IDS, NO SOLO LOS DE LOS ALQUILERES

    // Método para reconstruir el mapa a partir de listaAlquileres
    public void construirMapa() {
        Map<TipoServicio, Map<String, List<Alquiler>>> mapa = new HashMap<>();

        for (Alquiler alquiler : listaAlquileres) {
            if (!alquiler.isActivo()) continue; // Saltea alquileres inactivos

            TipoServicio tipo = alquiler.getTipoServicio();
            String idServicio = alquiler.getIdServicio();

            mapa
                    .computeIfAbsent(tipo, k -> new HashMap<>())
                    .computeIfAbsent(idServicio, k -> new ArrayList<>())
                    .add(alquiler);
        }
        setMapaAlquileres(mapa);
    }

    public void construirMappa() {
        Map<TipoServicio, Map<String, List<Alquiler>>> mapa = new HashMap<>();


        List<String> lista = GestionServicio.obtenerIDServiciosExistentes();

        System.out.println(lista.size());

        for (String idServicio : lista) {
            // Obtener el tipo de servicio
            TipoServicio tipo = TipoServicio.obtenerTipoPorId(idServicio); // Método que asocia IDs con tipos

            // Inicializar los mapas internos para cada ID
            mapa.computeIfAbsent(tipo, k -> new HashMap<>())
                    .computeIfAbsent(idServicio, k -> new ArrayList<>());
        }

        // Agregar alquileres activos al mapa
        for (Alquiler alquiler : listaAlquileres) {
            if (!alquiler.isActivo()) continue; // Saltar alquileres inactivos

            TipoServicio tipo = alquiler.getTipoServicio();
            String idServicio = alquiler.getIdServicio();

            // Solo agregar alquileres a IDs ya inicializados
            mapa
                    .computeIfAbsent(tipo, k -> new HashMap<>())
                    .computeIfAbsent(idServicio, k -> new ArrayList<>())
                    .add(alquiler);
        }

        setMapaAlquileres(mapa);
    }


    public void mostrarMapa() {
        // Se recorre el primer nivel del mapa (TipoServicio)

        for (Map.Entry<TipoServicio, Map<String, List<Alquiler>>> entradaTipoServicio : mapaAlquileres.entrySet()) {

            TipoServicio tipoServicio = entradaTipoServicio.getKey();
            Map<String, List<Alquiler>> mapaInterno = entradaTipoServicio.getValue();

            System.out.println("Tipo de Servicio: " + tipoServicio);

            // Recorre el segundo nivel del mapa (String)
            for (Map.Entry<String, List<Alquiler>> entradaString : mapaInterno.entrySet()) {
                String idServicio = entradaString.getKey();
                List<Alquiler> listaAlquileres = entradaString.getValue();

                System.out.println("\tID: " + idServicio);

                // Recorre la lista de alquileres relacionada al ID
                for (Alquiler alquiler : listaAlquileres) {
                    System.out.println("\t\tAlquiler: " + alquiler.toString());
                }
            }
            System.out.println(); // Línea en blanco para separar cada tipo de servicio
        }
    }

    /**
     * Método para obtener las carpas/sombrillas/plazas disponibles en una fecha determinada.
     * recibe como parametros el tipoServicio , fechaInicio y fechaFin solicitados por el usuario
     * Retorna una lista con los ids del servicio solicitado que se encuentran disponibles.
     * si no existe el tipoServicio solicitado en el mapa, devuelve una lista vacia
     */
    public List<String> obtenerIdsDisponibles(TipoServicio tipoServicio, LocalDate fechaInicio, LocalDate fechaFin) {

        // Evalua si existe una clave asociada al tipoServicio
        if (!mapaAlquileres.containsKey(tipoServicio)) {
            return Collections.emptyList();
        }

        Map<String, List<Alquiler>> alquileresPorId = mapaAlquileres.get(tipoServicio);

        return alquileresPorId.entrySet().stream() // el mapa pasa a ser Steam
                .filter(entry -> entry.getValue().stream()
                        .noneMatch(alquiler -> alquiler.getFechaAlta().isBefore(fechaFin)
                                && alquiler.getFechaBaja().isAfter(fechaInicio)))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Realiza la baja logica de un Alquiler. Modifica el boolean Activo a false del Alquiler.
     *
     * @param idAlquiler id del Alquiler a dar de baja
     */
    public void darBajaAlquiler(String idAlquiler) {
        for (Alquiler alquiler : listaAlquileres) {
            if (alquiler.getId().equals(idAlquiler)) {
                alquiler.setActivo(false); // Marcar como inactivo
                return;
            }
        }
        throw new IllegalArgumentException("Alquiler con ID " + idAlquiler + " no encontrado.");
    }
}

