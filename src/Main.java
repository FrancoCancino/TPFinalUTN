import alquiler.clases.Alquiler;
import alquiler.clases.ComprobanteAlquiler;
import alquiler.clases.GestionAlquiler;
import alquiler.clases.GestionComprobanteAlquiler;
import alquiler.enums.TipoServicio;
import alquiler.json.AlquilerJsonUtil;
import alquiler.json.ComprobanteJsonUtil;
import org.json.JSONObject;
import servicio.clases.*;
import servicio.enums.VarianteCarpa;
import usuario.OperacionesLectoEscritura;

import java.time.LocalDate;
import java.util.ArrayList;
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
        GestionComprobanteAlquiler gestionComprobanteAlquiler = new GestionComprobanteAlquiler();

        gestorAlquiler.agregarAlquiler(alquiler1);
        gestorAlquiler.agregarAlquiler(alquiler2);
        gestorAlquiler.agregarAlquiler(alquiler3);

        gestorAlquiler.construirMapa(gestorServicio);

        System.out.println("--------------------MAPA-------------------");
        gestorAlquiler.mostrarMapa();

        List<String> alquileresDisponibles = gestorAlquiler.obtenerIdsDisponibles(TipoServicio.CARPA, LocalDate.of(2024, 11, 21), LocalDate.of(2024, 11, 24));

        System.out.println("---------------------------------- disponibles");
        System.out.println(alquileresDisponibles);
        /*

        System.out.println("---------------------- probando crear alquiler");
        Alquiler alquilerParcial = new Alquiler(LocalDate.of(2025, 11, 21), LocalDate.of(2025, 11, 24),TipoServicio.CARPA);
        try{
            gestorAlquiler.crearAlquilerUsuario(alquilerParcial, gestorServicio, gestionComprobanteAlquiler,"jjjjjjjjjjj");
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        */


        System.out.println("-------------- PROBANDO METODOS JSON ALQUILER/COMPROBANTE ------------------------");
        List<Alquiler> listaAlquileresPrueba = new ArrayList<>();

        Alquiler alquiler11 = new Alquiler(LocalDate.of(2024, 11, 21), LocalDate.of(2024, 11, 24), TipoServicio.CARPA,"CP-1","1");
        Alquiler alquiler22 = new Alquiler(LocalDate.of(2024, 11, 28), LocalDate.of(2024, 11, 29), TipoServicio.SOMBRILLA,"SM-1","2");
        Alquiler alquiler33 = new Alquiler(LocalDate.of(2024, 11, 23), LocalDate.of(2024, 11, 26), TipoServicio.CARPA,"CP-2","3");

        listaAlquileresPrueba.add(alquiler11);
        listaAlquileresPrueba.add(alquiler22);
        listaAlquileresPrueba.add(alquiler33);


        ComprobanteAlquiler comprobanteAlquiler = new ComprobanteAlquiler(89.000, 90.000, listaAlquileresPrueba);

        JSONObject jsonObject = ComprobanteJsonUtil.serializarComprobanteAlquiler(comprobanteAlquiler);

        System.out.println(jsonObject.toString());

        ComprobanteAlquiler comprobanteLeido = ComprobanteJsonUtil.deserializarComprobanteAlquiler(jsonObject);

        System.out.println("------ COMPROBANTE LEIDO ---------");
        System.out.println(comprobanteLeido);

         /*




        OperacionesLectoEscritura.grabarArchivo(AlquilerJsonUtil.serializarAlquiler(alquiler1),"AlquilerPrueba.json");
        System.out.println(AlquilerJsonUtil.deserializarAlquiler(OperacionesLectoEscritura.leerArchivo("AlquilerPrueba.json")));

        LogIn lg =  new LogIn();
        lg.Menu();

        // COSAS QUE FALTAN HACER
        CHEQUEAR LEER BIEN TODOS LOS ARCHIVOS, AGREGAR FUNCIONES QUE VALIDAN SERVICIOS DISPONIBLES (por ejemplo que haya Carpas libres, los metodos de contar serviciosdisponibles estan hechas en GestioServicios
        resolver el tema de la interfaz
        armar los menues



          */

    }
}