import usuario.GestionUsuarios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LogIn {
Scanner scan = new Scanner(System.in);
     public void Menu() {
         GestionUsuarios g1 = new GestionUsuarios();

         System.out.println("-------------------------------------------------------------------");
         System.out.println("Bienvenid@ a nuestro gestor de balneario. Ingrese la opción deseada.");
         System.out.println("1. Iniciar sesión.");
         System.out.println("2. Registrarse.");
         System.out.println("0. Salir.");
         System.out.println("-------------------------------------------------------------------");

         int numero;
         do {
             try {
                 numero = scan.nextInt();
                 scan.nextLine();

                 switch (numero) {

                     case 0:
                         System.out.println("¡Gracias por usar nuestro gestor de balneario!");

                         break;

                     case 1:
                         //Registro

                         MenuPrincipal.Menu(g1.inicioSesion());     //Se crea un menu principal con el usuario que devuelve el log in de inicio sesión

                         break;

                     case 2:
                         //Inicio Sesión

                         System.out.println(g1.registro(g1));

                         break;

                     default:
                         System.out.println("Opción incorrecta. Ingresá 1 para iniciar sesión o 2 para registrarse.");
                         break;
                 }
             } catch (InputMismatchException e) {
                 System.out.println("Error: No se ingresó un número. Ingresá 1 para iniciar sesión o 2 para registrarse.");
                 scan.nextLine();
                 numero = -1;
             }
             } while (numero != 1 && numero != 2 && numero != 0) ;
         }
     }


