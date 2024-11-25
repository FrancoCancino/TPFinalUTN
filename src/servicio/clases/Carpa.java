package servicio.clases;

import servicio.enums.VarianteCarpa;
import utils.Constantes;

import java.util.Set;

import static utils.Constantes.PREFIJO_CARPA;

public class Carpa extends Servicio {
    private VarianteCarpa varianteCarpa;
    private String idPlazaEstacionamiento;

    private static int cantidadCarpas = 0;


    // Constructores
    public Carpa() {
    }

    public Carpa(VarianteCarpa varianteCarpa) {
        this.varianteCarpa = varianteCarpa;

        if(varianteCarpa == VarianteCarpa.PREMIUM){
            this.setPrecio(Constantes.PRECIO_ACTUAL_PREMIUM);
        }else
            this.setPrecio(Constantes.PRECIO_ACTUAL_STANDARD);

        ++cantidadCarpas;
        this.setId(Constantes.PREFIJO_CARPA + cantidadCarpas);
    }

    // Setters / Getters
    public VarianteCarpa getVarianteCarpa() {
        return varianteCarpa;
    }

    public void setVarianteCarpa(VarianteCarpa varianteCarpa) {
        this.varianteCarpa = varianteCarpa;
    }

    public String getIdPlazaEstacionamiento() {
        return idPlazaEstacionamiento;
    }

    public void setIdPlazaEstacionamiento(String idPlazaEstacionamiento) {
        this.idPlazaEstacionamiento = idPlazaEstacionamiento;
    }

    // toString
    @Override
    public String toString() {
        return  this.getId().replace(PREFIJO_CARPA,"") +
                " Tipo de carpa= " + varianteCarpa +
                " Ocupado= " + this.getOcupado() +
                " Precio= " + this.getPrecio() +
                ", Plaza de estacionamiento asignada= " + idPlazaEstacionamiento
                ;
    }

}
