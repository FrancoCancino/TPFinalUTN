import org.json.JSONArray;
import org.json.JSONObject;

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
                         //Este va a ser el inicio de sesión, la idea es que lea el archivo para verificar que exista
                         //el DNI que ingreso el usuario, si no existe le avisa el error (Posible excepción personalizada) y una vez
                         //ingresado el DNI verificado, le pide la contraseña y la verifica en el archivo para dejarlo ingresar
                         //y ahí que vaya al menú principal.
                        g1.inicioSesion();

                         break;

                     case 2:
                         //Esto vendría a ser el registro.

                         System.out.println(g1.registro(g1));


                         //Una vez registrado, se deberia de llevar al usuario al menú principal.
                         break;

                     default:
                         System.out.println("Opción incorrecta. Ingresá 1 para iniciar sesión o 2 para registrarse...");
                         break;
                 }
             } catch (InputMismatchException e) {
                 System.out.println("Error: No se ingresó un número. Ingresá 1 para iniciar sesión o 2 para registrarse...");
                 scan.nextLine();
                 numero = -1;
             }
             } while (numero != 1 && numero != 2 && numero != 0) ;
         }
     }


