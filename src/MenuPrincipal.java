import alquiler.clases.*;
import alquiler.exception.ServiciosNoDisponiblesException;
import alquiler.json.AlquilerJsonUtil;
import org.json.JSONObject;
import servicio.clases.GestionServicio;
import servicio.json.GestorServiciosJsonUtil;
import usuario.GestionUsuarios;
import usuario.OperacionesLectoEscritura;
import usuario.TipoUsuario;
import usuario.Usuario;
import utils.ConsolaUtils;
import utils.Constantes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal implements iMenuPresentable {

    private final static Scanner scan = new Scanner(System.in);

    // Sobreescritura de metodos de la interfaz IMenuPresentable

    public void Menu(Usuario usuario){



        //Se crea un gestor de servicios al finalizar el log in.
        GestionServicio gestorServicio = new GestionServicio();


        //Metodo para leer el archivo servicios y cargarlo en el GestorServicio

        //Guardo el tokener en un JsonObject
        JSONObject jsonObj = new JSONObject(OperacionesLectoEscritura.leerArchivo("servicios.json"));

        //Pongo el object y lo deserializo, cargandolo en el gestorServicio vacio.
        gestorServicio = GestorServiciosJsonUtil.deserializarServicios(jsonObj);


        //Creo un gestor para los alquileres y un gestor para los comprobantes.
        GestionAlquiler GA = new GestionAlquiler();
        GestionComprobanteAlquiler GC = new GestionComprobanteAlquiler();

        //Le cargo los servicios que se pusieron en el gestor de servicios al mapa de gestor Alquiler.
        GA.construirMapa(gestorServicio);

        //Creo una lista de alquileres que se va a usar en Mis Reservas y en Reservar.
        List<Alquiler> listaAlquileres = new ArrayList<>();


        int numero;
        String control;
        do {
            imprimirEncabezado();
            System.out.println(ConsolaUtils.CIAN + usuario.getNombre() + " " + usuario.getApellido() + ConsolaUtils.RESET);
            ConsolaUtils.imprimirLinea();
            imprimirInfo();
            imprimirOpciones();

            try {
                numero = scan.nextInt();
                scan.nextLine();
                MenuMisReservas menuMisReservas = new MenuMisReservas();

                switch (numero) {

                    case 0:
                        ConsolaUtils.imprimirCentrado("¡Gracias por usar nuestro gestor de balneario, " + usuario.getNombre() + "!");

                        break;

                    case 1:
                        //Mis reservas
                        menuMisReservas.Menu(listaAlquileres,usuario, GA, GC);

                        System.out.println("Queres volver al menu principal? (s/n)");
                        control = scan.nextLine();
                        if (control.equalsIgnoreCase("s")){
                            numero = -1;
                        }

                break;

                    case 2:

                        //Reservar

                        listaAlquileres = GA.crearAlquiler(gestorServicio, usuario.getDNI(),GA);   //Genera la lista de alquileres. (Basicamente alquilar).
                        //La guardo en una lista nueva así despues puedo mostrar el comprobante de la reserva más comodo.

                        OperacionesLectoEscritura.grabarArchivoARRAY(AlquilerJsonUtil.serializarListaAlquiler(listaAlquileres), Constantes.nombreArchivoAlquiler);

                        //Mostrar comprobante de dicho alquiler
                        GestionComprobanteAlquiler gestionComprobanteAlquiler  = new GestionComprobanteAlquiler();
                        // Se crear un comprobante a partir de los Alquileres realizados
                        ComprobanteAlquiler comprobanteAlquiler = gestionComprobanteAlquiler.crearComprobanteAlquiler(listaAlquileres, gestorServicio,usuario);

                        System.err.println("Su reserva se realizó con éxito!");
                        comprobanteAlquiler.mostrarComprobanteAlquiler(gestorServicio);

                        System.out.println("Queres volver al menu principal? (s/n)");
                        control = scan.nextLine();
                        if (control.equalsIgnoreCase("s")){
                            numero = -1;
                        }

                        break;
                    case 3:
                        //Modificar datos personales
                        GestionUsuarios.modificarUsuario(usuario);

                        System.out.println("Queres volver al menu principal? (s/n)");
                        control = scan.nextLine();
                        if (control.equalsIgnoreCase("s")){
                            numero = -1;
                        }

                        break;

                    case 4:
                        //Dar de baja usuarios
                        if(usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR){
                            System.out.println("Esta es la lista de los usuarios activos:");

                            GestionUsuarios.sobreescribirUsuario(GestionUsuarios.bajaPasivaUsuario());
                            System.out.println("El usuario fue dado de baja con éxito.");

                        } else {
                            System.err.println("No tenes los permisos para ingresar a esta opción");
                        }

                        System.out.println("Queres volver al menu principal? (s/n)");
                        control = scan.nextLine();
                        if (control.equalsIgnoreCase("s")){
                            numero = -1;
                        }

                        break;
                    case 5:
                        // informacion de serivicios
                        informacionSobreServicios();

                        System.out.println("Queres volver al menu principal? (s/n)");
                        control = scan.nextLine();
                        if (control.equalsIgnoreCase("s")){
                            numero = -1;
                        }
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
        } while (numero != 1 && numero != 2 && numero != 0 && numero != 3 && numero != 4 && numero != 5 ) ;
    }

    public void informacionSobreServicios(){
        ConsolaUtils.imprimirCentrado( ConsolaUtils.SOL + " Alquiler de carpas y sombrillas con amplios pasillos, ");
        ConsolaUtils.imprimirCentrado("playa de estacionamiento, vestuarios con locker,");
        ConsolaUtils.imprimirCentrado("piscina para adultos y para niños");

        System.out.println("");
        System.out.println(ConsolaUtils.CIAN +   "Contamos con un Bar de playa, actividades al aire libre, clases de surf, " +
                 ConsolaUtils.RESET);

        System.out.println(ConsolaUtils.CIAN +   "canchas de fútbol, guardería infantil, guardavidas y mucho más" +
                ConsolaUtils.RESET);

        System.out.println("");

    }

    @Override
    public void imprimirEncabezado() {
        System.out.println(ConsolaUtils.CIAN + "Bienvenid@ de vuelta " + ConsolaUtils.RESET);
    }

    @Override
    public void imprimirInfo() {
        System.out.println("");
        System.out.println("Club de Playa " + ConsolaUtils.MAR + ConsolaUtils.SOMBRILLA);
        System.out.println("Ofrecemos diversas actividades para disfrutar del mar y el verano");
    }

    @Override
    public void imprimirOpciones() {

        String[] opciones ={
                "Mis reservas.",
                "Reservar.",
                "Modificar datos personales.",
                "Dar de baja usuarios (Opcion de administrador).",
                "Ver servicios incluidos en el Club"

        };

        ConsolaUtils.imprimirLineaDoble();

        ConsolaUtils.imprimirMenuCentrado(opciones);

        ConsolaUtils.imprimirLineaDoble();

        ConsolaUtils.imprimirCentrado("Ingresá una opción:");

    }
}
