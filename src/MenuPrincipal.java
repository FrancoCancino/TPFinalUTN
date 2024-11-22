import usuario.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {

    public static void Menu(Usuario usuario){
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Bienvenid@ de vuelta, " + usuario.getNombre() + " " + usuario.getApellido() + ".Elegí una opción");
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


                        break;

                    case 2:
                        //Reservar


                        break;
                    case 3:
                        //Modificar datos personales


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
        } while (numero != 1 && numero != 2 && numero != 0) ;
    }

    }

