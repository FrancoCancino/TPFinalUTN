package servicio.clases;

import servicio.enums.VarianteCarpa;
import utils.Constantes;

import java.util.*;



public class GestionServicio {
    private static Set<Carpa> listadoCarpas;
    private static Set<Sombrilla> listadoSombrillas;
    private static Set<PlazaEstacionamiento> listadoPlazasEstacionamiento;

    private final String nombreArchivoCarpas = "carpas.json";
    private final String nombreArchivoSombrillas = "sombrillas.json";
    private final String nombreArchivoPlazasEstacionamiento = "plazasEstacionamiento.json";

    // Constructor
    public GestionServicio() {
        this.listadoCarpas = new TreeSet<>();
        this.listadoSombrillas = new TreeSet<>();
        this.listadoPlazasEstacionamiento = new TreeSet<>();
    }

    public void cargarGestionServicioParaPruebas() {

        Carpa carpa1 = new Carpa(VarianteCarpa.PREMIUM);
        Carpa carpa2 = new Carpa(VarianteCarpa.STANDARD);
        Carpa carpa3 = new Carpa(VarianteCarpa.PREMIUM);
        PlazaEstacionamiento plaza1 = new PlazaEstacionamiento();
        PlazaEstacionamiento plaza2 = new PlazaEstacionamiento();
        PlazaEstacionamiento plaza3 = new PlazaEstacionamiento();
        PlazaEstacionamiento plaza4 = new PlazaEstacionamiento();
        agregarPlazaEstacionamiento(plaza1);
        agregarPlazaEstacionamiento(plaza2);
        agregarPlazaEstacionamiento(plaza3);
        agregarPlazaEstacionamiento(plaza4);
        carpa1.setIdPlazaEstacionamiento(obtenerPlazaEstacionamientoVacia());
        carpa2.setIdPlazaEstacionamiento(obtenerPlazaEstacionamientoVacia());
        carpa3.setIdPlazaEstacionamiento(obtenerPlazaEstacionamientoVacia());
        agregarCarpa(carpa1);
        agregarCarpa(carpa2);
        agregarCarpa(carpa3);
        Sombrilla sombrilla1 = new Sombrilla();
        Sombrilla sombrilla2 = new Sombrilla();
        Sombrilla sombrilla3 = new Sombrilla();
        agregarSombrilla(sombrilla1);
        agregarSombrilla(sombrilla2);
        agregarSombrilla(sombrilla3);

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
    public List<String> obtenerIDServiciosExistentes() {
        List<String> ids = new ArrayList<>();
        for (Carpa carpa : listadoCarpas) {
            ids.add(carpa.getId());
        }
        for (Sombrilla sombrilla : listadoSombrillas) {
            ids.add(sombrilla.getId());
        }
        for (PlazaEstacionamiento plaza : listadoPlazasEstacionamiento) {
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
    public boolean verificarSiExistenServiciosDisponibles(){
        int cantidadCarpas = contarCarpasDisponibles();
        int cantidadPlazas = contarPlazasEstacionamientoDisponibles();
        int cantidadSombrillas = contarSombrillasDisponibles();

        return (cantidadPlazas > cantidadCarpas || cantidadSombrillas > 0);     //Si hay servicios disponibles esto devuelve false. Chequear
    }


}
