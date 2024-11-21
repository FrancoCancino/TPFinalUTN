package servicio.clases;

import java.util.Objects;

public abstract class Servicio implements Comparable<Servicio> {
    private String id;
    private boolean ocupado;
    private double precio;

    //Constructores

    public Servicio() {
        this.ocupado = false;
    }

    //Setters / Getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    // Equals / Hashcode / Compare to
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servicio servicio)) return false;
        return Objects.equals(id, servicio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Servicio servicio) {
        return this.id.compareTo(servicio.id);
    }
}
