import alquiler.clases.Alquiler;
import alquiler.clases.GestionAlquiler;
import alquiler.enums.TipoServicio;
import servicio.clases.*;
import servicio.enums.VarianteCarpa;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {



        /*
        System.out.println("-----------------------Carpas-----------------------");
        gestorServicio.listarCarpas();
        System.out.println("-----------------------Sombrillas-----------------------");
        gestorServicio.listarSombrillas();
        System.out.println("-----------------------Plazas-----------------------");
        gestorServicio.listarPlazasEstacionamiento();

*/
        //Acá iria una iniciación de gestion de alquileres. Lo movi a MenuPrincipal

/*
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
*/
        LogIn lg = new LogIn();
        lg.Menu();


    }
}