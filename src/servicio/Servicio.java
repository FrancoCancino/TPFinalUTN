package servicio;

import java.util.Objects;

public class Servicio implements Comparable{
    private int id;
    private boolean ocupado;
    private int capacidad;
    private double precio;

    //Constructores

    public Servicio() {
    }

    public Servicio(int id, boolean ocupado, int capacidad, double precio) {
        this.id = id;
        this.ocupado = ocupado;
        this.capacidad = capacidad;
        this.precio = precio;
    }

    //Setters / Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // to String

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", ocupado=" + ocupado +
                ", capacidad=" + capacidad +
                ", precio=" + precio +
                '}';
    }

    // Equals / Hashcode / Compare to

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servicio servicio)) return false;
        return id == servicio.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
