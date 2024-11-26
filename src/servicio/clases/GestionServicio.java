package servicio.clases;

import alquiler.clases.Alquiler;
import alquiler.clases.GestionAlquiler;
import alquiler.enums.TipoServicio;
import servicio.enums.VarianteCarpa;
import servicio.json.GestorServiciosJsonUtil;
import utils.Constantes;

import java.util.*;



public class GestionServicio {
    private static Set<Carpa> listadoCarpas;
    private static Set<Sombrilla> listadoSombrillas;
    private static Set<PlazaEstacionamiento> listadoPlazasEstacionamiento;

    // Constructor
    public GestionServicio() {
        this.listadoCarpas = new TreeSet<>();
        this.listadoSombrillas = new TreeSet<>();
        this.listadoPlazasEstacionamiento = new TreeSet<>();
    }

    // Getters y Setters
    public Set<Carpa> getListadoCarpas() {
        return listadoCarpas;
    }

    public void setListadoCarpas(Set<Carpa> listadoCarpas) {
        this.listadoCarpas = listadoCarpas;
    }

    public Set<Sombrilla> getListadoSombrillas() {
        return listadoSombrillas;
    }

    public void setListadoSombrillas(Set<Sombrilla> listadoSombrillas) {
        this.listadoSombrillas = listadoSombrillas;
    }

    public Set<PlazaEstacionamiento> getListadoPlazasEstacionamiento() {
        return listadoPlazasEstacionamiento;
    }

    public void setListadoPlazasEstacionamiento(Set<PlazaEstacionamiento> listadoPlazasEstacionamiento) {
        this.listadoPlazasEstacionamiento = listadoPlazasEstacionamiento;
    }

    // Métodos CRUD
    public boolean agregarCarpa(Carpa carpa) {
        return listadoCarpas.add(carpa);
    }

    public boolean agregarSombrilla(Sombrilla sombrilla) {
        return listadoSombrillas.add(sombrilla);
    }

    public boolean agregarPlazaEstacionamiento(PlazaEstacionamiento plaza) {
        return listadoPlazasEstacionamiento.add(plaza);
    }

    public boolean eliminarCarpa(Carpa carpa) {
        return listadoCarpas.remove(carpa);
    }

    public boolean eliminarSombrilla(Sombrilla sombrilla) {
        return listadoSombrillas.remove(sombrilla);
    }

    public boolean eliminarPlazaEstacionamiento(PlazaEstacionamiento plaza) {
        return listadoPlazasEstacionamiento.remove(plaza);
    }

    public boolean modificarCarpa(Carpa original, Carpa modificada) {
        if (eliminarCarpa(original)) {
            return agregarCarpa(modificada);
        }
        return false;
    }

    public boolean modificarSombrilla(Sombrilla original, Sombrilla modificada) {
        if (eliminarSombrilla(original)) {
            return agregarSombrilla(modificada);
        }
        return false;
    }

    public boolean modificarPlazaEstacionamiento(PlazaEstacionamiento original, PlazaEstacionamiento modificada) {
        if (eliminarPlazaEstacionamiento(original)) {
            return agregarPlazaEstacionamiento(modificada);
        }
        return false;
    }

    // Listar Servicios
    public void listarCarpas() {
        System.out.println("Listado de Carpas:");
        for (Carpa carpa : listadoCarpas) {
            System.out.println(carpa);
        }
    }

    public void listarSombrillas() {
        System.out.println("Listado de Sombrillas:");
        for (Sombrilla sombrilla : listadoSombrillas) {
            System.out.println(sombrilla);
        }
    }

    public void listarPlazasEstacionamiento() {
        System.out.println("Listado de Plazas de Estacionamiento:");
        for (PlazaEstacionamiento plaza : listadoPlazasEstacionamiento) {
            System.out.println(plaza);
        }
    }

    // Métodos Utilitarios
    // Cambia el estado ocupado igual a false por true
    public void ocupar(String id){
        if(id.startsWith(Constantes.PREFIJO_CARPA)){
            for(Carpa carpa : listadoCarpas){
                if(carpa.getId().equals(id)){
                    carpa.setOcupado(true);
                    break;
                }
            }
        }else if(id.startsWith(Constantes.PREFIJO_SOMBRILLA)){
            for(Sombrilla sombrilla : listadoSombrillas){
                if(sombrilla.getId().equals(id)){
                    sombrilla.setOcupado(true);
                    break;
                }
            }
        }else{
            for(PlazaEstacionamiento plazaEstacionamiento : listadoPlazasEstacionamiento){
                if (plazaEstacionamiento.getId().equals(id)){
                    plazaEstacionamiento.setOcupado(true);
                    break;
                }
            }
        }
    }


    // Recorre los Sets para obtener un ArrayList con los IDs de los Servicios existentes
    public List<String> obtenerIDServiciosExistentes(GestionServicio gestorServicio ) {

        Set<Carpa> listadoCarpa = gestorServicio.getListadoCarpas();
        Set<Sombrilla> listadoSombrilla = gestorServicio.getListadoSombrillas();
        Set<PlazaEstacionamiento> listadoPlazaEstacionamiento = gestorServicio.getListadoPlazasEstacionamiento();

        List<String> ids = new ArrayList<>();

        for (Carpa carpa : listadoCarpa) {
            ids.add(carpa.getId());
        }
        for (Sombrilla sombrilla : listadoSombrilla) {
            ids.add(sombrilla.getId());
        }
        for (PlazaEstacionamiento plaza : listadoPlazaEstacionamiento) {
            ids.add(plaza.getId());
        }
        return ids;

    }


    public String obtenerPlazaEstacionamientoVacia() {
        for (PlazaEstacionamiento plaza : listadoPlazasEstacionamiento) {
            if (!plaza.getOcupado()) {
                plaza.setOcupado(true);
                return plaza.getId();
            }
        }
        throw new IllegalStateException("No hay plazas disponibles.");
    }

    // Busca una Carpa por medio del ID, en caso de no encuentrarlo retorna un objeto vacio
    public Carpa obtenerCarpaPorID(String id) {
        for (Carpa carpa : listadoCarpas) {
            if (carpa.getId().equals(id)) {
                return carpa;
            }
        }
        return new Carpa();
    }

    // Busca una Sombrilla por medio del ID, en caso de no encuentrarlo retorna un objeto vacio
    public Sombrilla obtenerSombrillaPorID(String id) {
        for (Sombrilla sombrilla : listadoSombrillas) {
            if (sombrilla.getId().equals(id)) {
                return sombrilla;
            }
        }
        return new Sombrilla();
    }
    // Busca una Carpa por medio del ID, en caso de no encuentrarlo retorna un objeto vacio
    public PlazaEstacionamiento obtenerPlazaEstacionamientoPorID(String id) {
        for (PlazaEstacionamiento plaza : listadoPlazasEstacionamiento) {
            if (plaza.getId().equals(id)) {
                return plaza;
            }
        }
        return new PlazaEstacionamiento();
    }

    // Metodos que cuentan cantidad de servicios disponibles
    public int contarCarpasDisponibles(){
        int cantidadCarpas = 0;
        for(Carpa carpa : listadoCarpas){
            if(!carpa.getOcupado()){
                cantidadCarpas++;
            }
        }
        return cantidadCarpas;
    }

    public int contarPlazasEstacionamientoDisponibles(){
        int cantidadPlazaEstacionamientos = 0;
        for(PlazaEstacionamiento plaza : listadoPlazasEstacionamiento){
            if(!plaza.getOcupado()){
                cantidadPlazaEstacionamientos++;
            }
        }
        return cantidadPlazaEstacionamientos;
    }

    public int contarSombrillasDisponibles(){
        int cantidadSombrillas = 0;
        for(Sombrilla sombrilla : listadoSombrillas){
            if(!sombrilla.getOcupado()){
                cantidadSombrillas++;
            }
        }
        return cantidadSombrillas;
    }


    // Valida si existen plazas disponibles para alquilar. Si la cantidad libre de plazas es mayor a las carpas, existen
    // plazas disponibles para cubrir las carpas libres y para alquiler, por lo cual retorna true
    public boolean verificarSiExistenPlazasDisponibles(){
        int cantidadCarpas = contarCarpasDisponibles();
        int cantidadPlazas = contarPlazasEstacionamientoDisponibles();

        return cantidadPlazas > cantidadCarpas;
    }

    //Funcion para  verificar que haya por lo menos 1 servicio disponible
    public boolean verificarSiExistenServiciosDisponibles(GestionAlquiler gestionAlquiler, Alquiler alquiler) {


        List<String> listaIdsCarpasDisponibles = gestionAlquiler.obtenerIdsDisponibles(TipoServicio.CARPA, alquiler.getFechaAlta(), alquiler.getFechaBaja());
        List<String> listaIdsSombrillasDisponibles = gestionAlquiler.obtenerIdsDisponibles(TipoServicio.SOMBRILLA, alquiler.getFechaAlta(), alquiler.getFechaBaja());
        List<String> listaIdsPlazasEstacionamientoDisponibles = gestionAlquiler.obtenerIdsDisponibles(TipoServicio.PLAZA_ESTACIONAMIENTO, alquiler.getFechaAlta(), alquiler.getFechaBaja());

        return !listaIdsCarpasDisponibles.isEmpty() || !listaIdsSombrillasDisponibles.isEmpty() || !listaIdsPlazasEstacionamientoDisponibles.isEmpty();
        //Si hay servicios disponibles esto devuelve false. Chequear
    }



}
