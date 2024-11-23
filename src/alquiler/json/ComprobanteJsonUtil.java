package alquiler.json;

import alquiler.clases.ComprobanteAlquiler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servicio.clases.Carpa;
import servicio.clases.PlazaEstacionamiento;
import servicio.clases.Servicio;
import servicio.clases.Sombrilla;
import servicio.json.CarpaJsonUtil;
import servicio.json.PlazaEstacionamientoJsonUtil;
import servicio.json.SombrillaJsonUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ComprobanteJsonUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * Serializa un objeto de tipo {@link ComprobanteAlquiler} en un {@link JSONObject}.
     *
     * @param comprobante el objeto de tipo {@code Carpa} que se desea serializar.
     * @return un {@link JSONObject} que representa los datos de la {@code Carpa}.
     * Si ocurre una excepción, se devuelve un JSON vacío.
     */

    public static JSONObject serializarComprobanteAlquiler(ComprobanteAlquiler comprobante) {
        JSONObject jsonObject = null;
        JSONArray jsonArray;
        try {
            jsonObject = new JSONObject();
            jsonArray = new JSONArray();

            jsonObject.put("id", comprobante.getId());
            jsonObject.put("fechaEmision", comprobante.getFechaEmision().toString());
            jsonObject.put("subTotal", comprobante.getSubTotal());
            jsonObject.put("importeTotal", comprobante.getImporteTotal());
            jsonObject.put("descripcion", comprobante.getDescripcion());
            jsonObject.put("activo", comprobante.isActivo());

            // Recorre la lista de Servicios alquilados y segun la instancia que sea de Servicio lo serializa a jsonObject
            for (Servicio servicio : comprobante.getServiciosAlquilados()) {
                if (servicio instanceof Carpa) {
                    JSONObject jsonObject1 = CarpaJsonUtil.serializarCarpa((Carpa) servicio);
                    jsonArray.put(jsonObject1);
                } else if (servicio instanceof Sombrilla) {
                    JSONObject jsonObject1 = SombrillaJsonUtil.serializarSombrilla((Sombrilla) servicio);
                    jsonArray.put(jsonObject1);
                } else {
                    JSONObject jsonObject1 = PlazaEstacionamientoJsonUtil.serializarPlazaEstacionamiento((PlazaEstacionamiento) servicio);
                    jsonArray.put(jsonObject1);
                }
            }

            // Agregar el array de servicios al objeto json
            jsonObject.put("serviciosAlquilados", jsonArray);

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return jsonObject;
    }

    /**
     * Deserializa  un {@link JSONObject} en un objeto de tipo {@link ComprobanteAlquiler}
     *
     * @param jsonObject el objeto de tipo {@code JSONObject} que se desea deserializar.
     * @return un {@link ComprobanteAlquiler}
     * Si ocurre una excepción, se devuelve una Carpa vacía.
     */
    public static ComprobanteAlquiler deserializarComprobanteAlquiler(JSONObject jsonObject) {
        ComprobanteAlquiler comprobante = new ComprobanteAlquiler();

        try {
            comprobante.setId(jsonObject.getString("id"));
            comprobante.setFechaEmision(LocalDateTime.parse(jsonObject.getString("fechaEmision"), FORMATTER));
            comprobante.setSubTotal(jsonObject.getDouble("subTotal"));
            comprobante.setImporteTotal(jsonObject.getDouble("importeTotal"));
            comprobante.setDescripcion(jsonObject.getString("descripcion"));
            comprobante.setActivo(jsonObject.getBoolean("activo"));

            // DESERIALIZAR SERVICIOS ALQUILADOS

            // Primero analiza si el jsonObject tiene una key para serviciosAlquilados
            if (jsonObject.has("serviciosAlquilados")) {
                // Se crea un jsonArray para guardarlo
                JSONArray serviciosArray = jsonObject.getJSONArray("serviciosAlquilados");
                List<Servicio> serviciosAlquilados = new ArrayList<>();

                // Se recorre el jsonArray y por cada jsonObject
                for (int i = 0; i < serviciosArray.length(); i++) {
                    JSONObject servicioJson = serviciosArray.getJSONObject(i);

                    // Identificar el tipo de servicio y deserializar
                    if (servicioJson.has("tipo")) {
                        String tipo = servicioJson.getString("tipo");
                        switch (tipo) {
                            case "Carpa" -> serviciosAlquilados.add(CarpaJsonUtil.deserializarCarpa(servicioJson));
                            case "Sombrilla" ->
                                    serviciosAlquilados.add(SombrillaJsonUtil.deserializarSombrilla(servicioJson));
                            case "PlazaEstacionamiento" ->
                                    serviciosAlquilados.add(PlazaEstacionamientoJsonUtil.deserializarPlazaEstacionamiento(servicioJson));
                        }
                    }
                }
                // Se settea serviciosAlquilados con el arrayList generado
                comprobante.setServiciosAlquilados(serviciosAlquilados);
            }

            } catch(JSONException exception){
                System.err.println(exception.getMessage());
            }
        return comprobante;
    }


    /**
     * Serializa un objeto de tipo {@link List<ComprobanteAlquiler>} en un {@link JSONArray}.
     *
     * @param listado Set de tipo {@code List<ComprobanteAlquiler>} que se desea serializar.
     * @return un {@link JSONArray} que representa los datos del {@code listado}.
     * Si ocurre una excepción, se devuelve un JSONArray vacío.
     */
    public static JSONArray serializarListadoComprobanteAlquiler(List<ComprobanteAlquiler> listado) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();

            for (ComprobanteAlquiler comprobanteAlquiler : listado) {
                JSONObject jsonObject = serializarComprobanteAlquiler(comprobanteAlquiler);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return jsonArray;
    }

    /**
     * Deserializa  un {@link JSONArray} un ArrayList de tipo {@link List<ComprobanteAlquiler>}
     *
     * @param jsonArray array de tipo {@code JSONArray} que se desea deserializar.
     * @return un {@link List<ComprobanteAlquiler>}
     * Si ocurre una excepción, se devuelve un listado vacío.
     */
    public static List<ComprobanteAlquiler> deserializarListadoComprobanteAlquiler(JSONArray jsonArray) {
        List<ComprobanteAlquiler> listadoComprobantes = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                ComprobanteAlquiler comprobanteAlquiler = deserializarComprobanteAlquiler(jsonArray.getJSONObject(i));
                listadoComprobantes.add(comprobanteAlquiler);
            }

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return listadoComprobantes;
    }

}
