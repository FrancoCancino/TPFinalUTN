package servicio.clases;

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
}
