package servicio.clases;

import utils.Constantes;

import static utils.Constantes.PREFIJO_PLAZA_ESTACIONAMIENTO;
import static utils.Constantes.PREFIJO_SOMBRILLA;

public class Sombrilla extends Servicio {

    private static int cantidadSombrillas = 0;

    // Constructor
    public Sombrilla() {
        ++cantidadSombrillas;
        this.setId(Constantes.PREFIJO_SOMBRILLA + cantidadSombrillas);
        this.setPrecio(Constantes.PRECIO_ACTUAL_SOMBRILLA);
    }

    /* toString
    @Override
    public String toString() {
        return  this.getId().replace(Constantes.PREFIJO_SOMBRILLA, "") +
                " Precio = " + this.getPrecio()
                ;
    }

     */

    public String toString() {
        StringBuilder sb = new StringBuilder();

        String precio = "$ " + getPrecio();

        sb.append(String.format("%-10s %-10s%n",
                this.getId().replace(PREFIJO_SOMBRILLA,""),
                precio));

        return sb.toString();
    }

}
