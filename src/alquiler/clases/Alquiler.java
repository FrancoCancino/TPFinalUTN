package alquiler.clases;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import alquiler.enums.TipoServicio;
import servicio.clases.Servicio;

public class Alquiler {
    private int id;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private TipoServicio tipoServicio;
    private int idServicio;
    private int idUsuario;
    private int idFactura;

    // Formato para mostrar las fechas de forma amigable
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    //Constructores
    public Alquiler(){}

    public Alquiler(int id, LocalDate fechaAlta, LocalDate fechaBaja, TipoServicio tipoServicio, int idServicio, int idUsuario, int idFactura) {
        this.id = id;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.tipoServicio = tipoServicio;
        this.idServicio = idServicio;
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

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
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
                ", fechaAlta=" + fechaAlta.format(FORMATTER) +
                ", fechaBaja=" + fechaBaja.format(FORMATTER) +
                ", Servicio=" + tipoServicio +
                ", idServicio=" + idServicio +
                ", idUsuario=" + idUsuario +
                ", idFactura=" + idFactura +
                '}';
    }
}
