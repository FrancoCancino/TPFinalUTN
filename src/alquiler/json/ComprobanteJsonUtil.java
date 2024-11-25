package alquiler.json;

import alquiler.clases.Alquiler;
import alquiler.clases.ComprobanteAlquiler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Constantes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ComprobanteJsonUtil {


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
            for (Alquiler alquiler : comprobante.getListaAlquileres()) {
                JSONObject jsonObject1 = AlquilerJsonUtil.serializarAlquiler(alquiler);
                jsonArray.put(jsonObject1);
            }

            // Agregar el array de alquileres al objeto json
            jsonObject.put("listaAlquileres", jsonArray);

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
     * Si ocurre una excepción, se devuelve una ComprobanteAlquiler vacío.
     */
    public static ComprobanteAlquiler deserializarComprobanteAlquiler(JSONObject jsonObject) {
        ComprobanteAlquiler comprobante = new ComprobanteAlquiler();

        try {
            comprobante.setId(jsonObject.getString("id"));
            comprobante.setFechaEmision(LocalDateTime.parse(jsonObject.getString("fechaEmision"), Constantes.FORMATTER_DATE_TIME));
            comprobante.setSubTotal(jsonObject.getDouble("subTotal"));
            comprobante.setImporteTotal(jsonObject.getDouble("importeTotal"));
            comprobante.setDescripcion(jsonObject.getString("descripcion"));
            comprobante.setActivo(jsonObject.getBoolean("activo"));

            // DESERIALIZAR alquileres

            // Primero analiza si el jsonObject tiene una key para listaAlquileres
            if (jsonObject.has("listaAlquileres")) {
                // Obtiene el jsonArray
                JSONArray alquileresArray = jsonObject.getJSONArray("listaAlquileres");
                List<Alquiler> alquileres = new ArrayList<>();

                // Recorre el jsonArray y por cada jsonObject crea un Alquiler, el cual agrega al arrayList
                for (int i = 0; i < alquileresArray.length(); i++) {
                    JSONObject jsonObject1 = alquileresArray.getJSONObject(i);
                    Alquiler alquiler = AlquilerJsonUtil.deserializarAlquiler(jsonObject1);
                    alquileres.add(alquiler);
                }

                // Agrega la lista del alquileres al ComprobanteAlquiler
                comprobante.setListaAlquileres(alquileres);
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
