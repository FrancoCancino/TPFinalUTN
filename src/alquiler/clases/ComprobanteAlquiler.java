package alquiler.clases;

import alquiler.enums.TipoServicio;
import servicio.clases.*;
import utils.Constantes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComprobanteAlquiler {
    private String id;
    private LocalDateTime fechaEmision;
    private double subTotal;
    private double importeTotal;
    private String descripcion;
    private boolean activo;
    private List<Alquiler> listaAlquileres;

    // Formato para mostrar fecha y hora
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    // Constante para descripcion
    private static final String DESCRIPCION = "Alquiler de servicios de playa";

    //Constructores
    public ComprobanteAlquiler() {
        this.id = null;
        this.fechaEmision = null;
        this.descripcion = null;
        this.subTotal = 0;
        this.importeTotal = 0;
        this.activo = true;
        this.listaAlquileres = new ArrayList<>();
    }

    public ComprobanteAlquiler(List<Alquiler> listaAlquileres) {
        this.id = generarID();
        this.fechaEmision = LocalDateTime.now();
        this.descripcion = DESCRIPCION;
        this.subTotal = 0;
        this.importeTotal = 0;
        this.activo = true;
        this.listaAlquileres = listaAlquileres;
    }

    public ComprobanteAlquiler(double subTotal,
                               double importeTotal,
                               List<Alquiler> listaAlquileres) {
        this.id = generarID();
        this.fechaEmision = LocalDateTime.now();
        this.subTotal = subTotal;
        this.importeTotal = importeTotal;
        this.descripcion = DESCRIPCION;
        this.activo = true;
        this.listaAlquileres = listaAlquileres;
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

    public List<Alquiler> getListaAlquileres() {
        return listaAlquileres;
    }

    public void setListaAlquileres(List<Alquiler> listaAlquileres) {
        this.listaAlquileres = listaAlquileres;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Metodos Utilitarios

    /**
     * Crea un ID de tipo String de 6 digitos numericos aleatorios mas significantes
     */
    public String generarID() {
        Random random = new Random();
        int id = 10000 + random.nextInt(90000); // Genera un número entre 10000 y 99999
        return String.valueOf(id);
    }

    /**
     * Muestra la informacion del comprobante formateado como una salida de texto estructurada
     */
    public void mostrarComprobanteAlquiler(GestionServicio gestorServicio) {
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

        for (Alquiler alquiler : listaAlquileres) {
            String tipoServicio = alquiler.getTipoServicio().toString(); // Convertir el tipo a texto
            double precio = 0.0;


            // Obtener el precio basado en el tipo de servicio
            switch (alquiler.getTipoServicio()) {
                case CARPA -> {
                    Carpa carpa = gestorServicio.obtenerCarpaPorID(alquiler.getIdServicio());
                    precio = carpa.getPrecio();
                }
                case SOMBRILLA -> {
                    Sombrilla sombrilla = gestorServicio.obtenerSombrillaPorID(alquiler.getIdServicio());
                    precio = sombrilla.getPrecio();
                }
                case PLAZA_ESTACIONAMIENTO -> {
                    PlazaEstacionamiento plaza = gestorServicio.obtenerPlazaEstacionamientoPorID(alquiler.getIdServicio());
                    precio = plaza.getPrecio();
                }
            }

            // Imprimir los datos del alquiler
            System.out.printf("%-15s %-25s %-10.2f%n", alquiler.getIdServicio(), tipoServicio, precio);
        }

        System.out.println("-----------------------------------------");

        // Totales
        System.out.printf("Subtotal: %.2f%n", subTotal);
        System.out.printf("Importe Total: %.2f%n", importeTotal);
        System.out.println("=========================================");
    }


    @Override
    public String toString() {
        return "ComprobanteAlquiler{" +
                "id='" + id + '\'' +
                ", fechaEmision=" + fechaEmision.format(Constantes.FORMATTER_DATE_TIME_MOSTRAR) +
                ", subTotal=" + subTotal +
                ", importeTotal=" + importeTotal +
                ", descripcion='" + descripcion + '\'' +
                ", activo=" + activo +
                ", listaAlquileres=" + listaAlquileres +
                '}';
    }
}

