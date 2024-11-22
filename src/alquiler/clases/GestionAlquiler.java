package alquiler.clases;

import alquiler.enums.TipoServicio;
import servicio.clases.GestionServicio;

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


    // Método para reconstruir el mapa a partir de listaAlquileres y de los IDs de los servicios existentes
    public void construirMapa() {
        // Crear un nuevo mapa de mapas anidados:
        // Clave externa (primer nivel): TipoServicio
        // Clave interna (segundo nivel): String (ID del servicio)
        // Valor final: Lista de Alquileres
        Map<TipoServicio, Map<String, List<Alquiler>>> mapa = new HashMap<>();

        //Variable utilizada para cambiar el estado activo de los Alquileres
        LocalDate fechaActual = LocalDate.now();

        // Obtener una lista con los IDs de todos los Servicios existentes
        List<String> lista = GestionServicio.obtenerIDServiciosExistentes();

        // Crear la estructura del mapa
        for (String idServicio : lista) {
            // A partir del ID se obtiene el TipoServicio
            TipoServicio tipo = TipoServicio.obtenerTipoPorId(idServicio);

            // Si el TipoServicio o ID NO existe en el mapa, se crea un nuevo HashMap
            mapa.computeIfAbsent(tipo, k -> new HashMap<>())
                    .computeIfAbsent(idServicio, k -> new ArrayList<>());
        }

        //  Agregar los Alquileres al mapa
        for (Alquiler alquiler : listaAlquileres) {
            // Saltar alquileres que no están activos
            if (!alquiler.isActivo()) continue;

            // Verificar si la fecha de baja ya pasó, de ser asi, cambia su estado Activo a false
            if (alquiler.getFechaBaja() != null && alquiler.getFechaBaja().isBefore(fechaActual)) {
                alquiler.setActivo(false);
            }

            // Obtener el tipo de servicio y ID de Servicio del alquiler actual
            TipoServicio tipo = alquiler.getTipoServicio();
            String idServicio = alquiler.getIdServicio();

            // Agregar el alquiler a la lista correspondiente
            mapa
                    .computeIfAbsent(tipo, k -> new HashMap<>())
                    .computeIfAbsent(idServicio, k -> new ArrayList<>())
                    .add(alquiler);
        }

        // Establecer el mapa de alquileres con los datos filtrados
        setMapaAlquileres(mapa);
    }

    /**
     * Método para mostrar el mapaAlquileres, que representa los Alquileres ordenados por ID y tipoServicio
     */
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
     * Crea un Alquiler, obteniendo los datos a traves del Usuario
     */


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

