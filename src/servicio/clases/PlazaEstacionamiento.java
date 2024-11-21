package servicio.clases;

public class PlazaEstacionamiento extends Servicio {
    private boolean prioritario;

    private static int cantidadPlazas = 0;
    private static final double PRECIO_ACTUAL = 8000;

    // Constructores
    public PlazaEstacionamiento() {
    }

    public PlazaEstacionamiento(boolean ocupado, boolean prioritario) {
        super(ocupado);
        this.setId(++cantidadPlazas);
        this.setPrecio(PRECIO_ACTUAL);
        this.prioritario = prioritario;
    }

    //Setters / getters
    public boolean getIsPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    //to String
    @Override
    public String toString() {
        return "PlazaEstacionamiento{" + super.toString() +
                "prioritario=" + prioritario +
                '}';
    }
}
