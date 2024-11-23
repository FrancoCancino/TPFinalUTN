package servicio.clases;

public class PlazaEstacionamiento extends Servicio {

    private static int cantidadPlazas = 0;
    private static final double PRECIO_ACTUAL = 8000;
    private static final String PREFIJO_PLAZA_ESTACIONAMIENTO ="PE-";

    // Constructores
    public PlazaEstacionamiento() {
        ++cantidadPlazas;
        this.setId(PREFIJO_PLAZA_ESTACIONAMIENTO + cantidadPlazas);
        this.setPrecio(PRECIO_ACTUAL);
    }

    // toString
    @Override
    public String toString() {
        return  this.getId().replace(PREFIJO_PLAZA_ESTACIONAMIENTO, "") + // no se muestra el prefijo al usuario
                " Ocupado= " + this.getOcupado() +
                " Precio= " + this.getPrecio()
                ;
    }
}
