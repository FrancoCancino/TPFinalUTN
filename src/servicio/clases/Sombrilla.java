package servicio.clases;

public class Sombrilla extends Servicio {

    private static int cantidadSombrillas = 0;
    private static final double PRECIO_ACTUAL = 18000;

    // Constructores
    public Sombrilla() {
    }

    public Sombrilla(int id, boolean ocupado, int capacidad, double precio) {
        super(ocupado);
        this.setId(++cantidadSombrillas);
        this.setPrecio(PRECIO_ACTUAL);
    }

}
