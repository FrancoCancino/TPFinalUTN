import java.util.Scanner;

public class LogIn {
Scanner scan = new Scanner(System.in);
     public void Menu() {
         System.out.println("-------------------------------------------------------------------");
         System.out.println("Bienvenid@ a nuestro gestor de balneario. Ingrese la opción deseada.");
         System.out.println("1. Iniciar sesión.");
         System.out.println("2. Registrarse.");
         System.out.println("0. Salir.");
         System.out.println("-------------------------------------------------------------------");
         int numero;
         do{
             numero = scan.nextInt();
             scan.nextLine();
             switch (numero) {

                 case 0:

                 break;

                 case 1:
                     System.out.println("------------------------------------------------------");
                     System.out.println("Ingrese su DNI");
                     String dni = scan.nextLine();
                     System.out.println("Ingrese su contraseña");
                     String contrasenia = scan.nextLine();
                     System.out.println("------------------------------------------------------");
                     break;

                 case 2:
                     System.out.println("Hola 2");
                     break;

                 default:
                     System.out.println("Opción incorrecta, ingrese 1 para iniciar sesión o 2 para registrarse..");
                     break;
             }

         } while (numero != 1 && numero != 2 && numero != 0);
        }
     }

