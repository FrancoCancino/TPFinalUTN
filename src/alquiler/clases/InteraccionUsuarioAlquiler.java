package alquiler.clases;

import java.util.Scanner;

public class InteraccionUsuarioAlquiler {
    private final GestionAlquiler gestionAlquiler; // Clase previamente creada que gestiona los alquileres
    private final Scanner scanner = new Scanner(System.in);

    // Constructor
    public InteraccionUsuarioAlquiler(GestionAlquiler gestionAlquiler) {
        this.gestionAlquiler = gestionAlquiler;
    }

    // Getter
    public GestionAlquiler getGestionAlquiler() {
        return gestionAlquiler;
    }

    // Metodo que solicita la informacion necesaria para crear un Alquiler
    public void solicitarInformacionParaAlquiler(){
        System.out.println("Realizar una reserva");
    }

    //
}
