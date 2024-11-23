import alquiler.clases.Alquiler;
import alquiler.clases.GestionAlquiler;
import alquiler.enums.TipoServicio;
import servicio.clases.*;
import servicio.enums.VarianteCarpa;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Inicializar carpas
        Carpa carpa1 = new Carpa(VarianteCarpa.PREMIUM);
        Carpa carpa2 = new Carpa(VarianteCarpa.STANDARD);
        Carpa carpa3 = new Carpa(VarianteCarpa.PREMIUM);

        //Inicializar Sombrillas
        Sombrilla sombrilla1 = new Sombrilla();
        Sombrilla sombrilla2 = new Sombrilla();
        Sombrilla sombrilla3 = new Sombrilla();

        //Inicializar PlazasEstacionamiento
        PlazaEstacionamiento plaza1 = new PlazaEstacionamiento();
        PlazaEstacionamiento plaza2 = new PlazaEstacionamiento();
        PlazaEstacionamiento plaza3 = new PlazaEstacionamiento();
        PlazaEstacionamiento plaza4 = new PlazaEstacionamiento();

        GestionServicio gestorServicio = new GestionServicio();

        gestorServicio.agregarSombrilla(sombrilla1);
        gestorServicio.agregarSombrilla(sombrilla2);
        gestorServicio.agregarSombrilla(sombrilla3);

        gestorServicio.agregarPlazaEstacionamiento(plaza1);
        gestorServicio.agregarPlazaEstacionamiento(plaza2);
        gestorServicio.agregarPlazaEstacionamiento(plaza3);
        gestorServicio.agregarPlazaEstacionamiento(plaza4);

        // Se agrega plaza de estacionamiento en carpas
        carpa1.setIdPlazaEstacionamiento(gestorServicio.obtenerPlazaEstacionamientoVacia());
        carpa2.setIdPlazaEstacionamiento(gestorServicio.obtenerPlazaEstacionamientoVacia());
        carpa3.setIdPlazaEstacionamiento(gestorServicio.obtenerPlazaEstacionamientoVacia());


        gestorServicio.agregarCarpa(carpa1);
        gestorServicio.agregarCarpa(carpa2);
        gestorServicio.agregarCarpa(carpa3);


        System.out.println("-----------------------Carpas-----------------------");
        gestorServicio.listarCarpas();
        System.out.println("-----------------------Sombrillas-----------------------");
        gestorServicio.listarSombrillas();
        System.out.println("-----------------------Plazas-----------------------");
        gestorServicio.listarPlazasEstacionamiento();


        // Creacion de alquileres

        Alquiler alquiler1 = new Alquiler(LocalDate.of(2024, 11, 21), LocalDate.of(2024, 11, 24), TipoServicio.CARPA,"CP-1","1");
        Alquiler alquiler2 = new Alquiler(LocalDate.of(2024, 11, 28), LocalDate.of(2024, 11, 29), TipoServicio.SOMBRILLA,"SM-1","2");
        Alquiler alquiler3 = new Alquiler(LocalDate.of(2024, 11, 23), LocalDate.of(2024, 11, 26), TipoServicio.CARPA,"CP-2","3");

        GestionAlquiler gestorAlquiler = new GestionAlquiler();

        gestorAlquiler.agregarAlquiler(alquiler1);
        gestorAlquiler.agregarAlquiler(alquiler2);
        gestorAlquiler.agregarAlquiler(alquiler3);

        gestorAlquiler.construirMapa(gestorServicio);

        System.out.println("--------------------MAPA-------------------");
        gestorAlquiler.mostrarMapa();

        List<String> alquileresDisponibles = gestorAlquiler.obtenerIdsDisponibles(TipoServicio.CARPA, LocalDate.of(2024, 11, 21), LocalDate.of(2024, 11, 24));

        System.out.println("---------------------------------- disponibles");
        System.out.println(alquileresDisponibles);


        System.out.println("---------------------- probando crear alquiler");
        Alquiler alquilerParcial = new Alquiler(LocalDate.of(2025, 11, 21), LocalDate.of(2025, 11, 24),TipoServicio.CARPA);
        try{
            gestorAlquiler.crearAlquilerUsuario(alquilerParcial, gestorServicio);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }

    }
}