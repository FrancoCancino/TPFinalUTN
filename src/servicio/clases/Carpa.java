package servicio.clases;

import servicio.enums.VarianteCarpa;

public class Carpa extends Servicio {
    private VarianteCarpa varianteCarpa;
    private int idPlazaEstacionamiento;
    private String ubicacion;

    private static int cantidadCarpas = 0;
    private static final double PRECIO_ACTUAL_STANDARD = 20000;
    private static final double PRECIO_ACTUAL_PREMIUM = 25000;

    // Constructores
    public Carpa() {
    }

    public Carpa(boolean ocupado, VarianteCarpa varianteCarpa, int idPlazaEstacionamiento) {
        super(ocupado);
        this.varianteCarpa = varianteCarpa;

        if(varianteCarpa == VarianteCarpa.PREMIUM){
            this.setPrecio(PRECIO_ACTUAL_PREMIUM);
        }else
            this.setPrecio(PRECIO_ACTUAL_STANDARD);

        this.setId(++cantidadCarpas);
        this.idPlazaEstacionamiento = idPlazaEstacionamiento;
    }

    // Setters / Getters
    public VarianteCarpa getVarianteCarpa() {
        return varianteCarpa;
    }

    public void setVarianteCarpa(VarianteCarpa varianteCarpa) {
        this.varianteCarpa = varianteCarpa;
    }

    public int getIdPlazaEstacionamiento() {
        return idPlazaEstacionamiento;
    }

    public void setIdPlazaEstacionamiento(int idPlazaEstacionamiento) {
        this.idPlazaEstacionamiento = idPlazaEstacionamiento;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + "Carpa{" + super.toString() +
                "varianteCarpa=" + varianteCarpa +
                ", idPlazaEstacionamiento=" + idPlazaEstacionamiento +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }

}
