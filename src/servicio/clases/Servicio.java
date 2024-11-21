package servicio.clases;

import java.util.Objects;

public abstract class Servicio implements Comparable {
    private int id;
    private boolean ocupado;
    private double precio;

    //Constructores

    public Servicio() {
        this.ocupado = false;
    }

    //Setters / Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
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
        return "id=" + id +
                ", ocupado=" + ocupado +
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
