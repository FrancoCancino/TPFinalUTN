package servicio.clases;

public class Sombrilla extends Servicio {

    private static int cantidadSombrillas = 0;
    private static final double PRECIO_ACTUAL = 18000;
    private static final String PREFIJO_SOMBRILLA ="SM-";

    // Constructor
    public Sombrilla() {
        ++cantidadSombrillas;
        this.setId(PREFIJO_SOMBRILLA + cantidadSombrillas);
        this.setPrecio(PRECIO_ACTUAL);
    }

    // toString
    @Override
    public String toString() {
        return  this.getId().replace(PREFIJO_SOMBRILLA, "") +
                " Ocupado= " + this.getOcupado() +
                " Precio= " + this.getPrecio()
                ;
    }

}
