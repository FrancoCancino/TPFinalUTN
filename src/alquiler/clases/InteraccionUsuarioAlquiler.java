package alquiler.clases;

import alquiler.enums.TipoServicio;
import servicio.clases.GestionServicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InteraccionUsuarioAlquiler {
    private final GestionAlquiler gestionAlquiler; // Clase previamente creada que gestiona los alquileres
    private static final Scanner scanner = new Scanner(System.in);

    // Constructor
    public InteraccionUsuarioAlquiler(GestionAlquiler gestionAlquiler) {
        this.gestionAlquiler = gestionAlquiler;
    }

    // Getter
    public GestionAlquiler getGestionAlquiler() {
        return gestionAlquiler;
    }



    //Metodo para solicitar las fechas y el tipo de servicio.
    public static Alquiler solicitarInfoParaAlquiler(GestionServicio gestionservicio){


        //Crea el objeto alquiler
        Alquiler alquiler = new Alquiler();

        if (!gestionservicio.verificarSiExistenServiciosDisponibles()){
                return alquiler;        //Si no hay servicios disponibles devuelve un objeto alquiler vacio.
        }

        System.out.println("Estas a punto de realizar una reserva.");
        //Le defino el ID con un setter. Si es autoincremental lo hacemos de una. Si no lectura de archivos.
        alquiler.setActivo(true);

        System.out.println("Ingresa la fecha en la que vas a venir al balneario.");
        alquiler.setFechaAlta(generarFechaAlta());

        System.out.println("Ingresa la fecha en la que te vas a ir del balneario.");
        alquiler.setFechaBaja(generarFechaBaja(alquiler.getFechaAlta()));

        System.out.println("Ingresa el servicio que queres alquilar.");
        alquiler.setTipoServicio(generarTipoServicio(gestionservicio));

        return alquiler;

    }

    public static TipoServicio generarTipoServicio(GestionServicio gestionServicio){

        int numero;
        do {
            try {

                System.out.println("-------------------------------------------------------------------");
                System.out.println("1. Carpa.");
                System.out.println("2. Sombrilla.");
                System.out.println("3. Estacionamiento");
                System.out.println("-------------------------------------------------------------------");

                numero = scanner.nextInt();
                scanner.nextLine();

                switch (numero) {

                    case 1:

                        if (gestionServicio.contarCarpasDisponibles() > 0){
                            return TipoServicio.CARPA;
                        } else {
                            System.err.println("El servicio ingresado no esta disponible. Prueba con otro");
                        }

                    case 2:

                        if (gestionServicio.contarSombrillasDisponibles() > 0){
                            return TipoServicio.SOMBRILLA;
                        } else {
                            System.err.println("El servicio ingresado no esta disponible. Prueba con otro");
                        }

                    case 3:

                        // Se evalua si hay plazas disponibles para cubrir las carpas disponibles y el alquiler de la plaza que quiere realizar el usuario
                        // retorna true si existe aunque sea una plaza para alquilar
                        if (gestionServicio.verificarSiExistenPlazasDisponibles()){
                            return TipoServicio.PLAZA_ESTACIONAMIENTO;
                        } else {
                            System.err.println("El servicio ingresado no esta disponible. Prueba con otro"); // pensar si lo hacemos una constante
                        }

                    default:
                        if(numero != 1 && numero != 2 && numero != 3){
                            System.out.println("Opción incorrecta. Ingresá una de las tres opciones.");
                        }
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                numero = -1;
                throw new InputMismatchException("Error: No se ingresó un número. Ingresá una de las tres opciones");
            }
        } while (true) ;
    }

    public static LocalDate generarFechaAlta(){     //No digo si es alta o baja pq se puede implementar en ambas
        LocalDate fecha = null;
        LocalDate hoy = LocalDate.now();      //Hacer que no puedan poner fechas viejas. /WIP/.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Hay que actualizar para accceder al foramttter de la clase

        while (fecha == null){

            System.out.println("El formato tiene que ser 'dd/MM/yyyy'. Por ejemplo: 24/01/2025 sería 24 de enero del 2025.");
            String fechaIngresada = scanner.nextLine();

            if (esFormatoValido(fechaIngresada)){
                try{
                    fecha = LocalDate.parse(fechaIngresada,formatter);
                    if (fecha.isBefore(hoy)){
                        System.err.println("La fecha tiene que ser a partir de hoy.");
                        fecha = null;
                    }else {
                        return fecha;
                    }

                }catch (DateTimeParseException e){
                    System.err.println("La fecha ingresada no es valida. Verificá los datos e intenta de nuevo.");
                }
            }else {
                System.err.println("El formato ingresado no es válido.");
            }

        }
        return fecha;
    }

    public static LocalDate generarFechaBaja(LocalDate fechaAlta){

        LocalDate fechaBaja = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Define el formato esperado
        while (fechaBaja == null){

            System.out.println("El formato tiene que ser 'dd/MM/AAAA'. Por ejemplo: 24/01/2025 sería 24 de enero del 2025.");
            String fechaIngresada = scanner.nextLine();

            if (esFormatoValido(fechaIngresada)){
                try{
                    fechaBaja = LocalDate.parse(fechaIngresada,formatter);

                    if (fechaBaja.isAfter(fechaAlta) || fechaBaja.isEqual(fechaAlta)){
                        return fechaBaja;
                    }else{
                        System.err.println("La fecha de salida (" + fechaBaja.format(formatter) + ") no puede ser antes que la fecha de entrada (" + fechaAlta.format(formatter) + ").");
                        fechaBaja = null;
                    }

                }catch (DateTimeParseException e){
                    System.err.println("La fecha ingresada no es valida. Verificá los datos e intenta de nuevo.");
                }
            }else {
                System.err.println("El formato ingresado no es válido. Recordá usar el formato dd/MM/AAAA.");
            }

        }
        return fechaBaja;
    }

    public static boolean esFormatoValido(String fecha) {
        return fecha.matches("^\\d{2}/\\d{2}/\\d{4}$");     //Este regex verifica que se cummpla el formato dd/MM/yyyy.
    }

    public void listarReservas(){           //Borrar...

        ArrayList<Alquiler> alquileres = new ArrayList<>(gestionAlquiler.getListaAlquileres());
        System.out.println("Mis reservas: ");
        for (Alquiler alquiler : alquileres){
            System.out.println(alquiler);
        }

    }


    //
}
