package alquiler.clases;

import servicio.clases.Servicio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComprobanteAlquiler {
    private String id;
    private LocalDateTime fechaEmision;
    private double subTotal;
    private double importeTotal;
    private String descripcion;
    private List<Servicio> serviciosAlquilados;

    // Formato para mostrar fecha y hora
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    // Constante para descripcion
    private static final String DESCRIPCION = "Alquiler de servicios de playa";

    //Constructores
    public ComprobanteAlquiler(List<Servicio> serviciosAlquilados){
        this.id = generarID();
        this.fechaEmision = LocalDateTime.now();
        this.descripcion = DESCRIPCION;
        this.serviciosAlquilados = serviciosAlquilados;

    }

    public ComprobanteAlquiler(double subTotal,
                               double importeTotal,
                               List<Servicio> serviciosAlquilados) {
        this.id = generarID();
        this.fechaEmision = LocalDateTime.now();
        this.subTotal = subTotal;
        this.importeTotal = importeTotal;
        this.descripcion = DESCRIPCION;
        this.serviciosAlquilados = new ArrayList<>();
    }

    //Setters / Getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    // Metodos Utilitarios
    /**
     * Crea un ID de tipo String de 6 digitos numericos aleatorios mas significantes
     */
    public String generarID(){
        long id = UUID.randomUUID().getMostSignificantBits();
        String idFinal = String.valueOf(id);

        return idFinal.substring(0, 5 );
    }

    /**
     * Muestra la informacion del comprobante formateado como una salida de texto estructurada
     *
     */
    public void mostrarComprobanteAlquiler() {


      // Encabezado de la factura
        System.out.println("=========================================");
        System.out.println("               Comprobante               ");
        System.out.println("=========================================");
        System.out.printf("ID: %s%n", id);
        System.out.printf("Fecha de Emisión: %s%n", fechaEmision.format(FORMATTER));
        System.out.println("-----------------------------------------");

        // Descripción
        System.out.printf("Descripción: %s%n", descripcion);
        System.out.println("-----------------------------------------");

        // Detalles de los servicios alquilados
        System.out.println("Servicios Alquilados:");
        System.out.printf("%-15s %-25s %-10s%n", "ID Servicio", "Tipo Servicio", "Precio");
        System.out.println("-----------------------------------------");

        for (Servicio servicio : serviciosAlquilados) {
            System.out.printf("%-15s %-25s %-10.2f%n",
                    servicio.getId(),
                    servicio.getClass().getSimpleName(),
                    servicio.getPrecio());
        }

        System.out.println("-----------------------------------------");

        // Totales
        System.out.printf("Subtotal: %.2f%n", subTotal);
        System.out.printf("Importe Total: %.2f%n", importeTotal);
        System.out.println("=========================================");
    }
}

