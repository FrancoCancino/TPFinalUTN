package servicio.clases;

import servicio.enums.VarianteCarpa;

import java.util.Set;

public class Carpa extends Servicio {
    private VarianteCarpa varianteCarpa;
    private int idPlazaEstacionamiento;

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

        this.setId(++cantidadCarpas);
        asignarIdPlazaEstacionamiento();
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

    public void asignarIdPlazaEstacionamiento() {
        GestionServicio<PlazaEstacionamiento> gestor = new GestionServicio<>();
        // se obtiene un set con las plazas de estacionamiento
        Set<PlazaEstacionamiento> listado = gestor.getListadoPlazasEstacionamiento();
         for(PlazaEstacionamiento plaza : listado){
             if(!plaza.getOcupado()){
                 // guarda el id de la plaza asignada
                 this.idPlazaEstacionamiento = plaza.getId();
                 // cambia el estado de ocupado a true
                 plaza.setOcupado(true);
                 break;
             }
         }
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + "Carpa{" + super.toString() +
                "varianteCarpa=" + varianteCarpa +
                ", idPlazaEstacionamiento=" + idPlazaEstacionamiento +
                '\'' +
                '}';
    }

}
