package alquiler.clases;

import alquiler.json.ComprobanteJsonUtil;
import org.json.JSONArray;
import servicio.clases.*;
import usuario.OperacionesLectoEscritura;
import utils.Constantes;

import javax.xml.stream.events.Comment;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class GestionComprobanteAlquiler {
    private List<ComprobanteAlquiler> listaComprobanteAlquiler;

    // Constructor
    public GestionComprobanteAlquiler() {
        this.listaComprobanteAlquiler = new ArrayList<>();
    }

    // Setters / Getters
    public List<ComprobanteAlquiler> getListaFacturas() {
        return listaComprobanteAlquiler;
    }

    public void setListaFacturas(List<ComprobanteAlquiler> listaComprobanteAlquilers) {
        this.listaComprobanteAlquiler = listaComprobanteAlquilers;
    }

    // CRUD
    /**
     * Elimina de la lista de alquileres que posee el comprobante, un alquiler en especifico.
     * @param idAlquilerCancelado representa el alquiler que se borro
     */

    public void eliminarAlquilerDelComprobante(String idAlquilerCancelado){
        for(ComprobanteAlquiler comprobanteAlquiler : listaComprobanteAlquiler){
            for(Alquiler alquiler : comprobanteAlquiler.getListaAlquileres()){
                if(alquiler.getId().equals(idAlquilerCancelado)){
                    comprobanteAlquiler.getListaAlquileres().remove(alquiler);
                    OperacionesLectoEscritura.grabarArchivoARRAY(ComprobanteJsonUtil.serializarListadoComprobanteAlquiler(listaComprobanteAlquiler), Constantes.nombreArchivoComprobante);
                }
            }
        }
    }

    public ComprobanteAlquiler crearComprobanteAlquiler(List<Alquiler> listaAlquileres, GestionServicio gestionServicio) {
        double importeTotal = 0;
        double subTotal = 0;

        // Recorre la lista de alquileres y calcula los montos subtotales
        for (Alquiler alquiler : listaAlquileres) {
            // Calcular la cantidad de días del alquiler
            long diasAlquiler = ChronoUnit.DAYS.between(alquiler.getFechaAlta(), alquiler.getFechaBaja()) + 1;

            switch (alquiler.getTipoServicio()) {
                case CARPA -> {
                    Carpa carpa = gestionServicio.obtenerCarpaPorID(alquiler.getIdServicio());
                    double precioFinal = carpa.getPrecio() * diasAlquiler;
                    subTotal += precioFinal;
                }
                case SOMBRILLA -> {
                    Sombrilla sombrilla = gestionServicio.obtenerSombrillaPorID(alquiler.getIdServicio());
                    double precioFinal = sombrilla.getPrecio() * diasAlquiler;
                    subTotal += precioFinal;
                }
                case PLAZA_ESTACIONAMIENTO -> {
                    PlazaEstacionamiento plaza = gestionServicio.obtenerPlazaEstacionamientoPorID(alquiler.getIdServicio());
                    double precioFinal = plaza.getPrecio() * diasAlquiler;
                    subTotal += precioFinal;
                }
                default -> throw new IllegalArgumentException("Tipo de servicio no reconocido.");
            }
        }

        // Calcular importe total
        importeTotal = subTotal;

        // Crear un nuevo ComprobanteAlquiler
        ComprobanteAlquiler comprobante = new ComprobanteAlquiler(subTotal, importeTotal, listaAlquileres);

        // Leer la lista de comprobantes actuales del archivo JSON
        List<ComprobanteAlquiler> comprobantesActuales = new ArrayList<>();
        try {
            // Leer el archivo como cadena
            String contenidoArchivo = OperacionesLectoEscritura.leerArchivoARRAY(Constantes.nombreArchivoComprobante).toString();

            // Verificar que el contenido no sea nulo ni esté vacío
            if (contenidoArchivo != null && !contenidoArchivo.isBlank()) {
                // Convertir el contenido a un JSONArray
                JSONArray jsonArray = new JSONArray(contenidoArchivo);

                // Deserializar la lista de comprobantes a partir del JSONArray
                comprobantesActuales = ComprobanteJsonUtil.deserializarListadoComprobanteAlquiler(jsonArray);
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de comprobantes: " + e.getMessage());
        }

        // Agregar el nuevo comprobante a la lista
        comprobantesActuales.add(comprobante);

        // Guardar la lista actualizada en el archivo JSON
        OperacionesLectoEscritura.grabarArchivoARRAY(
                ComprobanteJsonUtil.serializarListadoComprobanteAlquiler(comprobantesActuales),
                Constantes.nombreArchivoComprobante
        );

        return comprobante;
    }

    public void listarComprobantes(){
        for(ComprobanteAlquiler comprobanteAlquiler : listaComprobanteAlquiler){
            System.out.println(comprobanteAlquiler);
        }
    }


}
