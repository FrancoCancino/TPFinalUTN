import alquiler.clases.Alquiler;
import alquiler.json.AlquilerJsonUtil;
import usuario.GestionUsuarios;
import usuario.OperacionesLectoEscritura;
import usuario.Usuario;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuMisReservas {

    private final static Scanner scan = new Scanner(System.in);

    // Sobreescritura de metodos de la interfaz IMenuPresentable

    public static void Menu(List<Alquiler> listaAlquileres, Usuario usuario) {

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Ingrese la opción deseada.");
        System.out.println("1. Listar mis reservas");
        System.out.println("2. Cancelar reserva");
        System.out.println("0. Salir.");
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
                        System.out.println("Volviendo...");

                        break;

                    case 1:
                        //Listar mis reservas


                        for (Alquiler alquiler : listaAlquileres){
                            //Recorro la lista
                            if(alquiler.getIdUsuario().equals(usuario.getDNI())){
                                //Encuentro los alquileres que coincidan con el Dni del usuario logeado y los imprimo
                                System.out.println(alquiler);
                            }
                        }


                        break;

                    case 2:
                        //Cancelar reserva

                        System.out.println("Tus reservas:");

                        for (Alquiler alquiler : listaAlquileres){
                            //Recorro la lista
                            if(alquiler.getIdUsuario().equals(usuario.getDNI())){
                                //Encuentro los alquileres que coincidan con el Dni del usuario logeado y los imprimo
                                System.out.println(alquiler);
                            }
                        }

                        System.out.println("Escribí el ID de la reserva que queres cancelar:");
                        String opcion = scan.nextLine();


                        for (Alquiler alquiler : listaAlquileres){
                            //Recorro la lista

                            if(alquiler.getId().equals(opcion)){
                                //Encuentro el alquiler con el ID ingresado del usuario y lo seteo  en false.
                                alquiler.setActivo(false);
                                //Una  vez modificado este dato, serializo el array ccon la modiifacccion y  lo grabo
                                OperacionesLectoEscritura.grabarArchivoARRAY(AlquilerJsonUtil.serializarListaAlquilerSobreescribiendo(listaAlquileres),"AlquilerPrueba.json");
                                System.out.println("La reserva fue cancelada con éxito!");
                            }
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
