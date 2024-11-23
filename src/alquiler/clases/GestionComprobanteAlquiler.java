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
    public ComprobanteAlquiler crearComprobanteAlquiler(List<Servicio> serviciosAlquilados){

        // crea un ComprobanteAlquiler con todos los atributos, menos el importeTotal y subTotal
        ComprobanteAlquiler comprobante = new ComprobanteAlquiler(serviciosAlquilados);

        // agrega el ComprobanteAlquiler a la coleccion
        listaComprobanteAlquiler.add(comprobante);

        return comprobante;
    }

    public void listarComprobantes(){
        for(ComprobanteAlquiler comprobanteAlquiler : listaComprobanteAlquiler){
            System.out.println(comprobanteAlquiler);
        }
    }


}
