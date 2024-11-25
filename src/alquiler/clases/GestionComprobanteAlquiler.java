package alquiler.clases;

import servicio.clases.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static alquiler.enums.TipoServicio.CARPA;

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
     * Realiza la baja logica de un ComprobanteAlquiler. Modifica el boolean Activo a false del ComprobanteAlquiler.
     *
     * @param idComprobante id del Comprobante a dar de baja
     */
    public void darBajaAlquiler(String idComprobante) {
        for (ComprobanteAlquiler comprobanteAlquiler: listaComprobanteAlquiler) {
            if (comprobanteAlquiler.getId().equals(idComprobante)) {
                comprobanteAlquiler.setActivo(false); // Marcar como inactivo
                return;
            }
        }
        throw new IllegalArgumentException("Comprobante con ID " + idComprobante + " no encontrado.");
    }

    public ComprobanteAlquiler crearComprobanteAlquiler(List<Alquiler> listaAlquileres, GestionServicio gestionServicio){
        double importeTotal = 0;
        double subTotal = 0;

        // Recorre la lista de alquileres y calcula los montos subtotales
        for (Alquiler alquiler : listaAlquileres) {
            // Calcular la cantidad de días del alquiler
            //Se usa ChronoUnit.DAYS.between para calcular la diferencia en días entre fechaAlta y fechaBaja.
            //Se suma 1 para incluir el día de inicio, ya que ambos días son parte del alquiler

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
        importeTotal = subTotal;

        // crea un ComprobanteAlquiler nuevo
        ComprobanteAlquiler comprobante = new ComprobanteAlquiler(subTotal, importeTotal, listaAlquileres);

        // Lo agrega al listado de comprobantes
        listaComprobanteAlquiler.add(comprobante);

        return comprobante;
    }

    public void listarComprobantes(){
        for(ComprobanteAlquiler comprobanteAlquiler : listaComprobanteAlquiler){
            System.out.println(comprobanteAlquiler);
        }
    }


}
