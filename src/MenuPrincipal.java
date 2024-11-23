import alquiler.clases.Alquiler;
import alquiler.clases.GestionAlquiler;
import alquiler.clases.InteraccionUsuarioAlquiler;
import alquiler.enums.TipoServicio;
import servicio.clases.Carpa;
import servicio.clases.GestionServicio;
import servicio.clases.PlazaEstacionamiento;
import servicio.clases.Sombrilla;
import servicio.enums.VarianteCarpa;
import usuario.GestionUsuarios;
import usuario.Usuario;

import java.time.LocalDate;
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
                        //Inicializar carpas
                        Carpa carpa1 = new Carpa(VarianteCarpa.PREMIUM);
                        Carpa carpa2 = new Carpa(VarianteCarpa.STANDARD);
                        Carpa carpa3 = new Carpa(VarianteCarpa.PREMIUM);

                        //Inicializar Sombrillas
                        Sombrilla sombrilla1 = new Sombrilla();
                        Sombrilla sombrilla2 = new Sombrilla();
                        Sombrilla sombrilla3 = new Sombrilla();

                        //Inicializar PlazasEstacionamiento
                        PlazaEstacionamiento plaza1 = new PlazaEstacionamiento();
                        PlazaEstacionamiento plaza2 = new PlazaEstacionamiento();
                        PlazaEstacionamiento plaza3 = new PlazaEstacionamiento();
                        PlazaEstacionamiento plaza4 = new PlazaEstacionamiento();

                        GestionServicio gestorServicio = new GestionServicio();

                        gestorServicio.agregarSombrilla(sombrilla1);
                        gestorServicio.agregarSombrilla(sombrilla2);
                        gestorServicio.agregarSombrilla(sombrilla3);

                        gestorServicio.agregarPlazaEstacionamiento(plaza1);
                        gestorServicio.agregarPlazaEstacionamiento(plaza2);
                        gestorServicio.agregarPlazaEstacionamiento(plaza3);
                        gestorServicio.agregarPlazaEstacionamiento(plaza4);

                        // Se agrega plaza de estacionamiento en carpas
                        carpa1.setIdPlazaEstacionamiento(gestorServicio.obtenerPlazaEstacionamientoVacia());
                        carpa2.setIdPlazaEstacionamiento(gestorServicio.obtenerPlazaEstacionamientoVacia());
                        carpa3.setIdPlazaEstacionamiento(gestorServicio.obtenerPlazaEstacionamientoVacia());


                        gestorServicio.agregarCarpa(carpa1);
                        gestorServicio.agregarCarpa(carpa2);
                        gestorServicio.agregarCarpa(carpa3);

                        // Creacion de alquileres

                        Alquiler alquiler1 = new Alquiler(LocalDate.of(2024, 11, 21), LocalDate.of(2024, 11, 24), TipoServicio.CARPA,"CP-1","1");
                        Alquiler alquiler2 = new Alquiler(LocalDate.of(2024, 11, 28), LocalDate.of(2024, 11, 29), TipoServicio.SOMBRILLA,"SM-1","2");
                        Alquiler alquiler3 = new Alquiler(LocalDate.of(2024, 11, 23), LocalDate.of(2024, 11, 26), TipoServicio.CARPA,"CP-2","3");

                        GestionAlquiler gestorAlquiler = new GestionAlquiler();

                        gestorAlquiler.agregarAlquiler(alquiler1);
                        gestorAlquiler.agregarAlquiler(alquiler2);
                        gestorAlquiler.agregarAlquiler(alquiler3);

                        gestorAlquiler.construirMapa(gestorServicio);

                        InteraccionUsuarioAlquiler a = new InteraccionUsuarioAlquiler(gestorAlquiler);
                        a.listarReservas();
                        System.out.println("Queres dar de baja alguna?");
                        gestorAlquiler.darBajaAlquiler("137a");

                        //To do list.

                        //Modificar alquiler (fecha y servicio).
                        //Hacer el listar reservas.



                        //Serializar alquiler(1) y comprobante(2) y la lista de alquileres (3).
                        //1) Pasar objeto alquiler a ObjetoJson y al archivo se guarda la lista alquiler (No el map).       (Lo hago)
                        //2) Coming soon        (Espero a Sofi [factura])


                        //Diagramar los menus.   -
                        //Investigar como dejar linda la consola.   -       (visuales, para lo ultimo)


                        break;

                    case 2:
                        //Reservar

                        //Pedir datos para hacer una reserva.

                        InteraccionUsuarioAlquiler.solicitarInfoParaAlquiler();

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
