package alquiler.exception;

public class ServiciosNoDisponiblesException extends Exception{

    public ServiciosNoDisponiblesException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "No existen servicios disponibles para los datos ingresados";
    }
}
