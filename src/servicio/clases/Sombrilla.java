package servicio.clases;

public class Sombrilla extends Servicio {

    private static int cantidadSombrillas = 0;
    private static final double PRECIO_ACTUAL = 18000;

    // Constructor
    public Sombrilla() {
        ++cantidadSombrillas;
        this.setId("SM-" + cantidadSombrillas);
        this.setPrecio(PRECIO_ACTUAL);
    }

    // toString
    @Override
    public String toString() {
        return  this.getId() +
                " Ocupado= " + this.getOcupado() +
                " Precio= " + this.getPrecio()
                ;
    }

}
