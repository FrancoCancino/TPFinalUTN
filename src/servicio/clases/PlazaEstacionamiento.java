package servicio.clases;

import utils.Constantes;

import static utils.Constantes.PREFIJO_CARPA;
import static utils.Constantes.PREFIJO_PLAZA_ESTACIONAMIENTO;


public class PlazaEstacionamiento extends Servicio {

    private static int cantidadPlazas = 0;

    // Constructores
    public PlazaEstacionamiento() {
        ++cantidadPlazas;
        this.setId(Constantes.PREFIJO_PLAZA_ESTACIONAMIENTO + cantidadPlazas);
        this.setPrecio(Constantes.PRECIO_ACTUAL_PlAZA_ESTACIONAMIENTO);
    }

    /* toString
    @Override
    public String toString() {
        return  this.getId().replace(Constantes.PREFIJO_PLAZA_ESTACIONAMIENTO, "") + // no se muestra el prefijo al usuario
                " Precio = " + this.getPrecio()
                ;
    }

     */

    public String toString() {
        StringBuilder sb = new StringBuilder();

        String precio = "$ " + getPrecio();

        sb.append(String.format("%-10s %-10s%n",
                this.getId().replace(PREFIJO_PLAZA_ESTACIONAMIENTO,""),
                precio));

        return sb.toString();
    }
}
