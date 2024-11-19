package servicio.clases;

public class PlazaEstacionamiento extends Servicio {
    private boolean prioritario;

    // Constructores
    public PlazaEstacionamiento() {
    }

    public PlazaEstacionamiento(int id, boolean ocupado, int capacidad, double precio, boolean prioritario) {
        super(id, ocupado, capacidad, precio);
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
