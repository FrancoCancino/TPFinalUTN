package servicio.clases;

public class PlazaEstacionamiento extends Servicio {

    private static int cantidadPlazas = 0;
    private static final double PRECIO_ACTUAL = 8000;

    // Constructores
    public PlazaEstacionamiento() {
        ++cantidadPlazas;
        this.setId("PE-" + cantidadPlazas);
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
