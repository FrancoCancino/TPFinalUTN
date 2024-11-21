package servicio.clases;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class GestionServicio<T extends Servicio> {
    private Set<Carpa> listadoCarpas;
    private Set<Sombrilla> listadoSombrillas;
    private Set<PlazaEstacionamiento> listadoPlazasEstacionamiento;

    private final String nombreArchivoCarpas = "carpas.json";
    private final String nombreArchivoSombrillas = "sombrillas.json";
    private final String nombreArchivoPlazasEstacionamiento = "plazasEstacionamiento.json";

    // Constructor
    public GestionServicio() {
        this.listadoCarpas = new TreeSet<>();
        this.listadoSombrillas = new TreeSet<>();
        this.listadoPlazasEstacionamiento = new TreeSet<>();
    }

    // Getters
    public Set<Sombrilla> getListadoSombrillas() {
        return listadoSombrillas;
    }

    public Set<Carpa> getListadoCarpas() {
        return listadoCarpas;
    }

    public Set<PlazaEstacionamiento> getListadoPlazasEstacionamiento() {
        return listadoPlazasEstacionamiento;
    }

    //
    public String obtenerPlazaEstacionamientoVacia(){
        for (PlazaEstacionamiento plaza : this.listadoPlazasEstacionamiento) {
            if (!plaza.getOcupado()) {
                plaza.setOcupado(true);
                return plaza.getId();
            }
        }
        return "No hay plazas";
    }

    // -------------------------------- CRUD
    /**
     * Agrega un servicio al conjunto correspondiente basado en su tipo.
     * @param servicio: servicio a agregar. Puede ser de tipo {@link Carpa}, {@link Sombrilla}, o {@link PlazaEstacionamiento}.
     * @return {@code true} si el servicio se agregó correctamente; {@code false} si ya existía o el tipo no es válido.
     */
    public boolean agregarServicio(T servicio) {
        if (servicio instanceof Carpa) {
            return listadoCarpas.add((Carpa) servicio);
        }
        if (servicio instanceof Sombrilla) {
            return listadoSombrillas.add((Sombrilla) servicio);
        }
        if (servicio instanceof PlazaEstacionamiento) {
            return listadoPlazasEstacionamiento.add((PlazaEstacionamiento) servicio);
        }
        return false;
    }

    /**
     * Elimina un servicio del conjunto correspondiente basado en su tipo.
     * @param servicio el servicio a eliminar. Puede ser de tipo {@link Carpa}, {@link Sombrilla}, o {@link PlazaEstacionamiento}.
     * @return {@code true} si el servicio se eliminó correctamente; {@code false} si no existía o el tipo no es válido.
     */
    public boolean eliminarServicio(T servicio) {
        if (servicio instanceof Carpa) {
            return listadoCarpas.remove((Carpa) servicio);
        }
        if (servicio instanceof Sombrilla) {
            return listadoSombrillas.remove((Sombrilla) servicio);
        }
        if (servicio instanceof PlazaEstacionamiento) {
            return listadoPlazasEstacionamiento.remove((PlazaEstacionamiento) servicio);
        }
        return false;
    }

    /**
     * Modifica un servicio existente reemplazándolo con una nueva versión.
     * @param servicioOriginal el servicio original que se desea modificar. Debe existir en el conjunto correspondiente.
     * @param servicioModificado el nuevo servicio que reemplazará al original. Debe ser del mismo tipo que el servicio original.
     * @return {@code true} si el servicio fue modificado correctamente; {@code false} si el servicio original no existía o no se pudo agregar el nuevo servicio.
     */
    public boolean modificarlistadoServicio(T servicioOriginal, T servicioModificado){
        if(eliminarServicio(servicioOriginal)){
            return agregarServicio(servicioModificado);
        }else {
            return false;
        }
    }

    /**
     * Muestra todos los servicios de tipo {@link Carpa}
     */
    public void listarCarpas(){
        for(Carpa carpa : listadoCarpas){
            System.out.println(carpa);
        }
    }
    /**
     * Muestra todos los servicios de tipo {@link Sombrilla}
     */
    public void listarSombrillas(){
        for(Sombrilla sombrilla : listadoSombrillas){
            System.out.println(sombrilla);
        }
    }

    /**
     * Muestra todos los servicios de tipo {@link PlazaEstacionamiento}
     */
    public void listarPlazasEstacionaiento() {
        Iterator<PlazaEstacionamiento> iterator = listadoPlazasEstacionamiento.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    

}
