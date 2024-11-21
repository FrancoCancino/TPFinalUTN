package alquiler.clases;

import servicio.clases.Servicio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int id;
    private LocalDateTime fechaEmision;
    private double subTotal;
    private double importeTotal;
    private String descripcion;
    private List<Servicio> serviciosAlquilados;

    // Formato para mostrar las fechas de forma amigable
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Constructores
    public Factura(){}

    public Factura(int id,
                   double subTotal,
                   double importeTotal,
                   String descripcion,
                   List<Servicio> serviciosAlquilados) {
        this.id = id;
        this.fechaEmision = LocalDateTime.now();
        this.subTotal = subTotal;
        this.importeTotal = importeTotal;
        this.descripcion = descripcion;
        this.serviciosAlquilados = serviciosAlquilados;
    }

    //Setters / Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Servicio> getServiciosAlquilados() {
        return serviciosAlquilados;
    }

    public void setServiciosAlquilados(List<Servicio> serviciosAlquilados) {
        this.serviciosAlquilados = serviciosAlquilados;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", fechaEmision=" + fechaEmision.format(FORMATTER) +
                ", subTotal=" + subTotal +
                ", importeTotal=" + importeTotal +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
