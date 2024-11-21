package servicio.clases;

public class Sombrilla extends Servicio {

    private static int cantidadSombrillas = 0;
    private static final double PRECIO_ACTUAL = 18000;

    // Constructor
    public Sombrilla() {
        this.setId(++cantidadSombrillas);
        this.setPrecio(PRECIO_ACTUAL);
    }

}
