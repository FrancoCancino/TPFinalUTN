package servicio;

public class Carpa extends Servicio {
    private VarianteCarpa varianteCarpa;
    private int idPlazaEstacionamiento;
    private String ubicacion;

    // Constructores
    public Carpa() {
    }

    public Carpa(int id, boolean ocupado, int capacidad, double precio, VarianteCarpa varianteCarpa, int idPlazaEstacionamiento, String ubicacion) {
        super(id, ocupado, capacidad, precio);
        this.varianteCarpa = varianteCarpa;
        this.idPlazaEstacionamiento = idPlazaEstacionamiento;
        this.ubicacion = ubicacion;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + "Carpa{" +
                "varianteCarpa=" + varianteCarpa +
                ", idPlazaEstacionamiento=" + idPlazaEstacionamiento +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }

}
