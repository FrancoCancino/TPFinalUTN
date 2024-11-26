import alquiler.clases.*;
import alquiler.exception.ServiciosNoDisponiblesException;
import alquiler.json.AlquilerJsonUtil;
import alquiler.json.ComprobanteJsonUtil;
import org.json.JSONObject;
import servicio.clases.GestionServicio;
import servicio.clases.PlazaEstacionamiento;
import servicio.clases.Sombrilla;
import servicio.json.CarpaJsonUtil;
import servicio.json.GestorServiciosJsonUtil;
import servicio.json.PlazaEstacionamientoJsonUtil;
import servicio.json.SombrillaJsonUtil;
import usuario.GestionUsuarios;
import usuario.OperacionesLectoEscritura;
import usuario.Usuario;
import utils.ConsolaUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private final static Scanner scan = new Scanner(System.in);

    // Sobreescritura de metodos de la interfaz IMenuPresentable

    public static void Menu(Usuario usuario){

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Bienvenid@ de vuelta, " + usuario.getNombre() + " " + usuario.getApellido() + ". Elegí una opción");
        System.out.println("1. Mis reservas.");
        System.out.println("2. Reservar.");
        System.out.println("3. Modificar datos personales.");
        System.out.println("0. Salir.");
        System.out.println("-------------------------------------------------------------------");

        //Se crea un gestor de servicios al finalizar el log in.
        GestionServicio gestorServicio = new GestionServicio();
        //gestorServicio.CargarGestion();

        //Este metodo graba el archivo servicios. Usar cuando pongamos muchos servicios en un GestorServicio.
        //OperacionesLectoEscritura.grabarArchivo(GestorServiciosJsonUtil.serializarServicios(gestorServicio),"servicios.json");



        //Metodo para leer el archivo servicios y cargarlo en el GestorServicio
        JSONObject jsonObj = new JSONObject(OperacionesLectoEscritura.leerArchivo("servicios.json"));   //Guardo el tokener en un JsonObject
        gestorServicio = GestorServiciosJsonUtil.deserializarServicios(jsonObj);    //Pongo el object y lo deserializo, cargandolo en el gestorServicio vacio.


        //Creo un gestor para los alquileres
        GestionAlquiler GA = new GestionAlquiler();
        GestionComprobanteAlquiler GC = new GestionComprobanteAlquiler();

        //Le cargo los servicios que se pusieron en el gestor de servicios al mapa de gestor Alquiler.
        GA.construirMapa(gestorServicio);

        //Creo una lista de alquileres que se va a usar en Mis Reservas y en Reservar.
        List<Alquiler> listaAlquileres = new ArrayList<>();



        int numero;
        do {
            try {
                numero = scan.nextInt();
                scan.nextLine();

                switch (numero) {

                    case 0:
                        System.out.println("¡Gracias por usar nuestro gestor de balneario, " + usuario.getNombre() + "!");

                        break;

                    case 1:
                        //Mis reservas
                        //-------------------------------------------- AGREGUE GA
                        MenuMisReservas.Menu(listaAlquileres,usuario, GA, GC);

                break;

                    case 2:

                        //Reservar

                        listaAlquileres = GA.crearAlquiler(gestorServicio, usuario.getDNI(),GA);   //Genera la lista de alquileres. (Basicamente alquilar).
                        //La guardo en una lista nueva así despues puedo mostrar el comprobante de la reserva más comodo.


                        //Mostrar comprobante de dicho alquiler
                        GestionComprobanteAlquiler gestionComprobanteAlquiler  = new GestionComprobanteAlquiler();
                        // Se crear un comprobante a partir de los Alquileres realizados
                        ComprobanteAlquiler comprobanteAlquiler = gestionComprobanteAlquiler.crearComprobanteAlquiler(listaAlquileres, gestorServicio);

                        System.out.println("Su reserva se realizó con éxito!");
                        comprobanteAlquiler.mostrarComprobanteAlquiler(gestorServicio);

                        //Serializar reserva y comprobante.

                        //Acá grabo la lista de alquileres generada arriba.
                        OperacionesLectoEscritura.grabarArchivoARRAY(AlquilerJsonUtil.serializarListaAlquiler(listaAlquileres),"AlquilerPrueba.json");
                        //OperacionesLectoEscritura.grabarArchivoARRAY();


                        break;
                    case 3:
                        //Modificar datos personales
                        GestionUsuarios.modificarUsuario(usuario);  //Por ahora retorna el usuario nuevo pero quizá la hacemos void.

                        break;

                    default:
                        System.out.println("Opción incorrecta. Ingresá 1 para ver tus reservas, 2 para reservar o 3 para modificar tus datos personales.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: No se ingresó un número. Ingresá 1 para ver tus reservas, 2 para reservar o 3 para modificar tus datos personales.");
                scan.nextLine();
                numero = -1;
            } catch (ServiciosNoDisponiblesException e) {
                throw new RuntimeException(e);
            }
        } while (numero != 1 && numero != 2 && numero != 0 && numero != 3) ;
    }

}
