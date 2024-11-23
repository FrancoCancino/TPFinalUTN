package alquiler.clases;

import servicio.clases.Servicio;

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

    public ComprobanteAlquiler crearComprobanteAlquiler(List<Servicio> serviciosAlquilados){
        double importeTotal = 0;
        double subTotal = 0;

        // Recorre la lista se ServiciosAlquilados y calcula los montos subtotales y totales
        for(Servicio servicio: serviciosAlquilados){
            subTotal+= servicio.getPrecio();
            importeTotal = subTotal;
        }

        // crea un ComprobanteAlquiler nuevo, solo falta el importaTotal
        ComprobanteAlquiler comprobante = new ComprobanteAlquiler(subTotal,importeTotal, serviciosAlquilados);

        listaComprobanteAlquiler.add(comprobante);

        return comprobante;
    }

    public void listarComprobantes(){
        for(ComprobanteAlquiler comprobanteAlquiler : listaComprobanteAlquiler){
            System.out.println(comprobanteAlquiler);
        }
    }


}
