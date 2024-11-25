package servicio.clases;

import utils.Constantes;


public class PlazaEstacionamiento extends Servicio {

    private static int cantidadPlazas = 0;

    // Constructores
    public PlazaEstacionamiento() {
        ++cantidadPlazas;
        this.setId(Constantes.PREFIJO_PLAZA_ESTACIONAMIENTO + cantidadPlazas);
        this.setPrecio(Constantes.PRECIO_ACTUAL_PlAZA_ESTACIONAMIENTO);
    }

    // toString
    @Override
    public String toString() {
        return  this.getId().replace(Constantes.PREFIJO_PLAZA_ESTACIONAMIENTO, "") + // no se muestra el prefijo al usuario
                " Ocupado= " + this.getOcupado() +
                " Precio= " + this.getPrecio()
                ;
    }
}
