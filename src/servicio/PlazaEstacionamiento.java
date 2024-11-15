package servicio;

public class PlazaEstacionamiento extends Servicio {

    // Constructores
    public PlazaEstacionamiento() {
    }

    public PlazaEstacionamiento(int id, boolean ocupado, int capacidad, double precio) {
        super(id, ocupado, capacidad, precio);
    }
}
