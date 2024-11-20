package alquiler.clases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Factura {
    private int id;
    private LocalDateTime fechaEmision;
    private double subTotal;
    private double importeTotal;
    private String descripcion;

    // Formato para mostrar las fechas de forma amigable
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    //Constructores
    public Factura(){}

    public Factura(int id,
                   LocalDateTime fechaEmision,
                   double subTotal,
                   double importeTotal,
                   String descripcion) {
        this.id = id;
        this.fechaEmision = LocalDateTime.now();
        this.subTotal = subTotal;
        this.importeTotal = importeTotal;
        this.descripcion = descripcion;
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
