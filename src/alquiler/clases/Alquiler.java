package alquiler.clases;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import servicio.clases.Servicio;

public class Alquiler {
    private int id;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private List<Servicio> serviciosAlquilados;
    private int idUsuario;
    private int idFactura;

    //Constructores
    public Alquiler(){}
    public Alquiler(int id, LocalDate fechaBaja, LocalDate fechaAlta, List<Servicio> serviciosAlquilados, int idUsuario, int idFactura) {
        this.id = id;
        this.fechaBaja = fechaBaja;
        this.fechaAlta = fechaAlta;
        this.serviciosAlquilados = new ArrayList<>();
        this.idUsuario = idUsuario;
        this.idFactura = idFactura;
    }

    //Setters / Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public List<Servicio> getServiciosAlquilados() {
        return serviciosAlquilados;
    }

    public void setServiciosAlquilados(List<Servicio> serviciosAlquilados) {
        this.serviciosAlquilados = serviciosAlquilados;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    //toString
    @Override
    public String toString() {
        return "Alquiler{" +
                "id=" + id +
                ", fechaAlta=" + fechaAlta +
                ", fechaBaja=" + fechaBaja +
                ", serviciosAlquilados=" + serviciosAlquilados +
                ", idUsuario=" + idUsuario +
                ", idFactura=" + idFactura +
                '}';
    }
}
