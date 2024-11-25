import alquiler.clases.Alquiler;
import alquiler.clases.ComprobanteAlquiler;
import alquiler.clases.GestionAlquiler;
import alquiler.clases.GestionComprobanteAlquiler;
import alquiler.exception.ServiciosNoDisponiblesException;
import alquiler.json.AlquilerJsonUtil;
import servicio.clases.GestionServicio;
import usuario.GestionUsuarios;
import usuario.OperacionesLectoEscritura;
import usuario.Usuario;

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

        //Se crea un gestorde servicios al finalizar el log in.
        GestionServicio gestorServicio = new GestionServicio();

        //En este gestor de servicios se cargan Sombrillas, Plazas de estacionamiento y carpas.
        gestorServicio.cargarGestionServicioParaPruebas();

        //Creo un gestor para los alquileres
        GestionAlquiler GA = new GestionAlquiler();

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
                        MenuMisReservas.Menu(listaAlquileres,usuario);

                break;

                    case 2:

                        //Reservar

                        listaAlquileres = GA.crearAlquiler(gestorServicio,usuario.getDNI());   //Genera la lista de alquileres. (Basicamente alquilar).
                        //La guardo en una lista nueva así despues puedo mostrar el comprobante de la reserva más comodo.


                        //Mostrar comprobante de dicho alquiler
                        GestionComprobanteAlquiler gestionComprobanteAlquiler  = new GestionComprobanteAlquiler();
                        ComprobanteAlquiler comprobanteAlquiler = gestionComprobanteAlquiler.crearComprobanteAlquiler(listaAlquileres, gestorServicio);
                        System.out.println("Su reserva se realizó con éxito!");
                        comprobanteAlquiler.mostrarComprobanteAlquiler();

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
