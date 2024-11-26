import servicio.json.SombrillaJsonUtil;
import usuario.GestionUsuarios;
import usuario.Usuario;
import utils.ConsolaUtils;
import utils.Constantes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LogIn implements iMenuPresentable {

    private final Scanner scan = new Scanner(System.in);

    // Sobreescritura de metodos de la interfaz IMenuPresentable
    @Override
    public void imprimirEncabezado() {
        System.err.println(
                " _    _   _ _   _    _      ____   ___      _   _     \n" +
                        "| |  | | | | \\ | |  / \\    |  _ \\ / _ \\    | | / \\    \n" +
                        "| |  | | | |  \\| | / _ \\   | |_) | | | |_  | |/ _ \\   \n" +
                        "| |__| |_| | |\\  |/ ___ \\  |  _ <| |_| | |_| / ___ \\  \n" +
                        "|_____\\___/|_| \\_/_/   \\_\\ |_| \\_\\\\___/ \\___/_/   \\_\\ "
        );
        ConsolaUtils.imprimirTitulo("Bienvenid@ a nuestro Club de Mar");
    }

    @Override
    public void imprimirInfo() {
        ConsolaUtils.imprimirCentrado("Luna Roja, el sur de Marpla te espera");
    }

    @Override
    public void imprimirOpciones() {

        String[] opciones ={
                "Iniciar sesión.",
                "Registrarse.",
        };

        ConsolaUtils.imprimirLineaDoble();

        ConsolaUtils.imprimirMenuCentrado(opciones);

        ConsolaUtils.imprimirLineaDoble();

        ConsolaUtils.imprimirCentrado("Ingresá una opción:");

    }

    public void Menu() {
        GestionUsuarios g1 = new GestionUsuarios();
        MenuPrincipal menuPrincipal = new MenuPrincipal();

        imprimirEncabezado();
        imprimirInfo();
        imprimirOpciones();

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
                        //Inicio sesión
                        Usuario usuarioInicio = new Usuario();
                        usuarioInicio = g1.inicioSesion();

                        if(!usuarioInicio.isActivo()){
                            System.err.println("TU CUENTA HA SIDO ELIMINADA. CONTACTATE CON SOPORTE.");
                        }else{
                            menuPrincipal.Menu(usuarioInicio);
                        }

                        break;

                    case 2:
                        //Registro
                        System.out.println(g1.registro(g1));

                        break;


                    default:
                        System.out.println("Opción incorrecta. Ingresá 1 para iniciar sesión o 2 para registrarse.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: No se ingresó un número. Ingresá 1 para iniciar sesión o 2 para registrarse.");
                scan.nextLine();
                numero = -1;
            }
        } while (numero != 1 && numero != 2 && numero != 0);
    }
}


