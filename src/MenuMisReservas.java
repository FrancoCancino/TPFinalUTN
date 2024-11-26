import alquiler.clases.Alquiler;
import alquiler.clases.GestionAlquiler;
import alquiler.clases.GestionComprobanteAlquiler;
import alquiler.json.AlquilerJsonUtil;
import usuario.GestionUsuarios;
import usuario.OperacionesLectoEscritura;
import usuario.Usuario;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static alquiler.clases.GestionAlquiler.listarAlquileres;

public class MenuMisReservas {

    private final static Scanner scan = new Scanner(System.in);

    // Sobreescritura de metodos de la interfaz IMenuPresentable

    public static void Menu(List<Alquiler> listaAlquileres, Usuario usuario, GestionAlquiler gestionAlquiler, GestionComprobanteAlquiler gestionComprobanteAlquiler) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Ingresá una opción:");
        System.out.println("1. Listar mis reservas");
        System.out.println("2. Cancelar reserva");
        System.out.println("0. Volver al menu principal.");
        System.out.println("-------------------------------------------------------------------");

        int numero;
        do {
            try {
                numero = scan.nextInt();
                scan.nextLine();

                if (numero == 1 || numero == 2){    //Leo el archivo acá directamente para evitar abrir el archivo 2 veces
                    listaAlquileres = AlquilerJsonUtil.deserializarListaAlquiler(OperacionesLectoEscritura.leerArchivoARRAY("AlquilerPrueba.json"));
                }


                switch (numero) {

                    case 0:
                        System.out.println("Volviendo al menu principal...");
                        menuPrincipal.Menu(usuario);

                        break;

                    case 1:
                        //Listar mis reservas
                        listarAlquileres(listaAlquileres, usuario.getDNI());

                        System.out.println("Querés volver al menú principal? (s/n)");
                        String volver = scan.nextLine();
                        if (volver.equalsIgnoreCase("s")){
                            menuPrincipal.Menu(usuario);
                        }else{
                            System.out.println("¡Gracias por usar nuestro gestor de balneario, " + usuario.getNombre() + " !");
                        }

                        break;

                    case 2:
                        //Cancelar reserva


                        // Se muestran las reservas que pueden ser canceladas
                        listarAlquileres(listaAlquileres, usuario.getDNI());

                        if (listaAlquileres.isEmpty()){

                            System.out.println("Querés volver al menú principal? (s/n)");
                            volver = scan.nextLine();
                            if (volver.equalsIgnoreCase("s")){
                                menuPrincipal.Menu(usuario);
                            }else{
                                System.out.println("¡Gracias por usar nuestro gestor de balneario, " + usuario.getNombre() + " !");
                            }

                            break;
                        }

                        boolean resultadoDarBaja;
                        do{
                            System.out.println("Escribí el ID de la reserva que queres cancelar: (escribí 'volver' para volver al menu principal)");
                            String opcion = scan.nextLine();

                            if (opcion.equalsIgnoreCase("volver")){
                                menuPrincipal.Menu(usuario);
                                break;
                            }

                            resultadoDarBaja = gestionAlquiler.darBajaAlquiler(opcion, gestionComprobanteAlquiler,listaAlquileres);

                            if(!resultadoDarBaja){
                                System.out.println("El ID ingresado no existe. Intentelo nuevamente");
                            }else{
                                System.out.println("La reserva fue cancelada con éxito!");
                            }

                        }while(!resultadoDarBaja); // se ejecuta hasta que se pudo dar de baja el alquiler, es decir, hasta que el id ingresado es correcto

                        System.out.println("Querés volver al menú principal? (s/n)");
                        volver = scan.nextLine();
                        if (volver.equalsIgnoreCase("s")){
                            menuPrincipal.Menu(usuario);
                        }else{
                            System.out.println("¡Gracias por usar nuestro gestor de balneario, " + usuario.getNombre() + " !");
                        }

                        break;

                    default:
                        System.out.println("Opción incorrecta. Ingresá 1 para listar tus reservas o 2 para cancelar una.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: No se ingresó un número. Ingresá 1 para listar tus reservas o 2 para cancelar una.");
                scan.nextLine();
                numero = -1;
            }
        } while (numero != 1 && numero != 2 && numero != 0) ;
    }
}
