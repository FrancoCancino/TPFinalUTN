package alquiler.clases;

import alquiler.enums.TipoServicio;
import alquiler.exception.ServiciosNoDisponiblesException;
import servicio.clases.*;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestionAlquiler {
    private Map<TipoServicio, Map<String, List<Alquiler>>> mapaAlquileres;
    private List<Alquiler> listaAlquileres;
    // Cada vez que se ejecuta el programa, mapaAlquileres se carga con la informacion que persiste a traves de listaAlquileres

    private static final Scanner scanner = new Scanner(System.in);

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
    }


    // Metodos Utilitarios

    // Método para reconstruir el mapa a partir de listaAlquileres y de los IDs de los servicios existentes
    public void construirMapa(GestionServicio gestionServicio) {
        // Crear un nuevo mapa de mapas anidados:
        // Clave externa (primer nivel): TipoServicio
        // Clave interna (segundo nivel): String (ID del servicio)
        // Valor final: Lista de Alquileres
        Map<TipoServicio, Map<String, List<Alquiler>>> mapa = new HashMap<>();

        //Variable utilizada para cambiar el estado activo de los Alquileres
        LocalDate fechaActual = LocalDate.now();

        // Obtener una lista con los IDs de todos los Servicios existentes
        List<String> lista = gestionServicio.obtenerIDServiciosExistentes();

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
     * Lista los Servicios Disponibles segun el TipoServicio
     * @param listaIdsDisponibles contiene los IDs ya filtrados por TipoServicio.
     * @param tipoServicio utilizado para el switch
     * muestra como maximo 8 servicios disponibles
     *
     */

    public void mostrarServiciosDisponibles(TipoServicio tipoServicio, GestionServicio gestionServicio, List<String> listaIdsDisponibles) {
        System.out.println("Servicios disponibles en las fechas ingresadas:");

        int limite = 8;
        int cantidadAMostrar = Math.min(listaIdsDisponibles.size(), limite); // Determina la cantidad a mostrar en base al numero menor entre el limite y el tamno de la lista

        // Iterar solo hasta `cantidadAMostrar`
        for (int i = 0; i < cantidadAMostrar; i++) {
            // Guarda el id del objeto
            String id = listaIdsDisponibles.get(i);
            // Segun el tipoServicio, obtiene el Servicio a partir del id leido
            switch (tipoServicio) {
                case CARPA -> {
                    Carpa carpa = gestionServicio.obtenerCarpaPorID(id);
                    System.out.println(carpa);
                }
                case SOMBRILLA -> {
                    Sombrilla sombrilla = gestionServicio.obtenerSombrillaPorID(id);
                    System.out.println(sombrilla);
                }
                case PLAZA_ESTACIONAMIENTO -> {
                    PlazaEstacionamiento plaza = gestionServicio.obtenerPlazaEstacionamientoPorID(id);
                    System.out.println(plaza);
                }
                default -> throw new IllegalArgumentException("Tipo de servicio no reconocido.");
            }
        }
    }

    /**
     * Solicita al usuario que ingrese el Id del Servicio a alquilar, retorna unicamente los digitos numericos del Id
     */
    public String solicitarIDServicio(){
        String control;
        String entrada;

        do{
            System.out.println("Ingrese el ID del servicio que desea alquilar:");

            entrada = scanner.nextLine();

            // Valida la entrada con regex
            if (!entrada.matches("\\d+")) { // Verifica que solo contenga dígitos
                throw new IllegalArgumentException("La entrada debe ser un número entero positivo.");
            }

            System.out.println("El ID ingresado es "+ entrada + " \"¿Confirma el ID seleccionado? (s/n): ");
            control = scanner.next();
            scanner.nextLine();

        }while(!control.equalsIgnoreCase("s"));

        return entrada;
    }

    // Metodos CRUD

    // Método para agregar un Alquiler a listaAlquileres
    public boolean agregarAlquiler(Alquiler alquiler) {
        return listaAlquileres.add(alquiler);
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

    /**
     * Crea un Alquiler, obteniendo los datos a traves del Usuario
     */
    public List<Alquiler> crearAlquiler(GestionServicio gestionServicio, String DNIusuario) throws ServiciosNoDisponiblesException {

        List<Alquiler> listaAlquileres = new ArrayList<>();
        String control;
        Alquiler alquilerParcial = new Alquiler();
        alquilerParcial = InteraccionUsuarioAlquiler.solicitarInfoParaAlquiler(gestionServicio);
        //!!!Habria que pedirle el tipo de servicio fuera de esta función para que el usuario pueda hacer una reserva con más  de 1 tipo de servicio.

        do{
            List<String> listaIdsDisponibles = obtenerIdsDisponibles(alquilerParcial.getTipoServicio(), alquilerParcial.getFechaAlta(), alquilerParcial.getFechaBaja());

            // En caso de retornar una lista vacia, se lanza una excepcion
            if(listaIdsDisponibles.isEmpty()){
                throw new ServiciosNoDisponiblesException(toString());
            }


            // Se listan los Servicios relacionados a los ids de la lista
            mostrarServiciosDisponibles(alquilerParcial.getTipoServicio(), gestionServicio, listaIdsDisponibles);

            // Retorna solo los numeros del ID
            String numerosID = solicitarIDServicio();

            // Reconstruye el ID completo a partir del TipoServicio
            String idCompleto;
            switch (alquilerParcial.getTipoServicio()) {
                case CARPA -> {
                    idCompleto = "CP-" + numerosID;
                }
                case SOMBRILLA -> {
                    idCompleto = "SM-" + numerosID;
                }
                case PLAZA_ESTACIONAMIENTO -> {
                    idCompleto = "PE-" + numerosID;
                }
                default -> throw new IllegalArgumentException("Tipo de servicio no reconocido.");
            }

            // Se cambia el estado ocupado a true
            gestionServicio.ocupar(idCompleto);

            Alquiler alquilerNuevo = new Alquiler(alquilerParcial.getFechaAlta(), alquilerParcial.getFechaBaja(), alquilerParcial.getTipoServicio(),idCompleto, DNIusuario);

            // Se agrega el id del alquiler recien generado al arrayList
            listaAlquileres.add(alquilerNuevo);

            //Quito el ID elegido para no volver a alquiler el mismo.
            eliminarId(alquilerNuevo.getIdServicio(),alquilerNuevo.getTipoServicio());


            System.out.print("¿Quiere reservar otro servicio? (s/n): ");
            control = scanner.next();
            scanner.nextLine();


        } while (control.equalsIgnoreCase("s"));

            return listaAlquileres;
    }


    //Metodo para eliminar un ID  disponible del mapa (Así al usuario se le acortan las opciones una vez que ya eligio ese servicio)
    public void eliminarId(String idServicio, TipoServicio tipoServicio) {

        // Obtener el submapa correspondiente al tipo de servicio
        Map<String, List<Alquiler>> alquileresPorId = mapaAlquileres.get(tipoServicio);

        // Verificar si el ID del servicio existe en el submapa
        if (alquileresPorId.containsKey(idServicio)) {
            alquileresPorId.remove(idServicio); // Eliminar el ID del servicio
        } else {
            System.out.println("El ID especificado no existe en el submapa.");
        }

    }



}

