import usuario.GestionUsuarios;
import usuario.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {

    public static void Menu(Usuario usuario){
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Bienvenid@ de vuelta, " + usuario.getNombre() + " " + usuario.getApellido() + ". Elegí una opción");
        System.out.println("1. Mis reservas.");
        System.out.println("2. Reservar.");
        System.out.println("3. Modificar datos personales.");
        System.out.println("0. Salir.");
        System.out.println("-------------------------------------------------------------------");

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
                        System.out.println("Hola");
                        
                        //To do list.

                        //Modificar alquiler (fecha y servicio).
                        //Hacer el listar reservas.

                        //Interacción usuario pedirle datos para hacer un alquiler.   -   (lo hago)
                        //Como hacer la interfaz promocionable.    - (espero a Sofi)


                        //Serializar alquiler(1) y factura(2).
                        //1) Pasar objeto alquiler a ObjetoJson y al archivo se guarda la lista alquiler (No el map).       (Lo hago)
                        //2) Coming soon        (Espero a Sofi [factura])


                        //Diagramar los menus.   -
                        //Investigar como dejar linda la consola.   -       (visuales, para lo ultimo)


                        break;

                    case 2:
                        //Reservar

                        //Pedir datos para hacer una reserva.

                        GestionUsuarios.solicitarInfoParaAlquiler();

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
            }
        } while (numero != 1 && numero != 2 && numero != 0 && numero != 3) ;
    }

}
