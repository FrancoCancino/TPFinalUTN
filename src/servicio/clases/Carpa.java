package servicio.clases;

import servicio.enums.VarianteCarpa;

import java.util.Set;

public class Carpa extends Servicio {
    private VarianteCarpa varianteCarpa;
    private String idPlazaEstacionamiento;

    private static int cantidadCarpas = 0;
    private static final double PRECIO_ACTUAL_STANDARD = 20000;
    private static final double PRECIO_ACTUAL_PREMIUM = 25000;

    // Constructores
    public Carpa() {
    }

    public Carpa(VarianteCarpa varianteCarpa) {
        this.varianteCarpa = varianteCarpa;

        if(varianteCarpa == VarianteCarpa.PREMIUM){
            this.setPrecio(PRECIO_ACTUAL_PREMIUM);
        }else
            this.setPrecio(PRECIO_ACTUAL_STANDARD);

        ++cantidadCarpas;
        this.setId("CP-" + cantidadCarpas);
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
        return  this.getId() +
                " Tipo de carpa= " + varianteCarpa +
                " Ocupado= " + this.getOcupado() +
                " Precio= " + this.getPrecio() +
                ", Plaza de estacionamiento asignada= " + idPlazaEstacionamiento
                ;
    }

}
