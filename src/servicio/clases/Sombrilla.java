package servicio.clases;

import utils.Constantes;

public class Sombrilla extends Servicio {

    private static int cantidadSombrillas = 0;

    // Constructor
    public Sombrilla() {
        ++cantidadSombrillas;
        this.setId(Constantes.PREFIJO_SOMBRILLA + cantidadSombrillas);
        this.setPrecio(Constantes.PRECIO_ACTUAL_SOMBRILLA);
    }

    // toString
    @Override
    public String toString() {
        return  this.getId().replace(Constantes.PREFIJO_SOMBRILLA, "") +
                " Precio = " + this.getPrecio()
                ;
    }

}
