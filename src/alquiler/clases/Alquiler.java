package alquiler.clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import alquiler.enums.TipoServicio;
import utils.Constantes;


public class Alquiler {

    private String id;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private boolean activo;
    private TipoServicio tipoServicio;
    private String idServicio;
    private String idUsuario;


    //Constructores

    public Alquiler() {
        this.id = null;
        this.fechaAlta = null;
        this.fechaBaja = null;
        this.activo = false;
        this.tipoServicio = null;
        this.idServicio = null;
        this.idUsuario = null;
    }

    public Alquiler(LocalDate fechaAlta, LocalDate fechaBaja, TipoServicio tipoServicio) {
        this.id = null;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.activo = false;
        this.tipoServicio = tipoServicio;
        this.idServicio = null;
        this.idUsuario = null;
    }

    public Alquiler(LocalDate fechaAlta, LocalDate fechaBaja, TipoServicio tipoServicio, String idServicio, String idUsuario) {
        this.id = generarId();
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.activo = true;
        this.tipoServicio = tipoServicio;
        this.idServicio = idServicio;
        this.idUsuario = idUsuario;
    }

    //Setters / Getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    // Generar ID con caracteres y enteros aleatorios de 5 digitos
    public static String generarId() {
        long mostSigBits = UUID.randomUUID().getMostSignificantBits();
        return Long.toUnsignedString(mostSigBits, 36).substring(0, 4); // Convierte a base 36, se obtienen 13 caracteres y luego 5
    }

    //toString
    @Override
    public String toString() {
        return "Alquiler" +
                "ID= " + id +
                ", fechaAlta=" + fechaAlta.format(Constantes.FORMATTER_DATE) +
                ", fechaBaja=" + fechaBaja.format(Constantes.FORMATTER_DATE) +
                ", Servicio=" + tipoServicio +
                ", idServicio=" + idServicio +
                ", idUsuario=" + idUsuario
                ;
    }
}
