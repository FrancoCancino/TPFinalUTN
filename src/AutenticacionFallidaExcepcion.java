public class AutenticacionFallidaExcepcion extends Exception {
    public AutenticacionFallidaExcepcion(String message) {
        super("Error de formato: " + message);
    }
}
