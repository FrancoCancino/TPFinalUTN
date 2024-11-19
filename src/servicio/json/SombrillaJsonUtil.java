package servicio.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servicio.clases.Sombrilla;

import java.util.TreeSet;

public final class SombrillaJsonUtil {

    /**
     * Serializa un objeto de tipo {@link Sombrilla} en un {@link JSONObject}.
     */
    public static JSONObject serializarSombrilla(Sombrilla sombrilla) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("id", sombrilla.getId());
            jsonObject.put("ocupado", sombrilla.getOcupado());
            jsonObject.put("capacidad", sombrilla.getCapacidad());
            jsonObject.put("precio", sombrilla.getPrecio());

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return jsonObject;
    }

    /**
     * Deserializa un {@link JSONObject} en un objeto de tipo {@link Sombrilla}.
     */
    public static Sombrilla deserializarSombrilla(JSONObject jsonObject) {
        Sombrilla sombrilla = new Sombrilla();

        try {
            sombrilla.setId(jsonObject.getInt("id"));
            sombrilla.setOcupado(jsonObject.getBoolean("ocupado"));
            sombrilla.setCapacidad(jsonObject.getInt("capacidad"));
            sombrilla.setPrecio(jsonObject.getDouble("precio"));


        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return sombrilla;
    }

    /**
     * Serializa un objeto de tipo {@link TreeSet < Sombrilla >} en un {@link JSONArray}.
     *
     * @param listado Set de tipo {@code TreeSet<Sombrilla>} que se desea serializar.
     * @return un {@link JSONArray} que representa los datos del {@code listado}.
     * Si ocurre una excepción, se devuelve un JSONArray vacío.
     */
    public static JSONArray serializarListadoSombrillas(TreeSet<Sombrilla> listado) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();

            for (Sombrilla sombrilla : listado) {
                JSONObject jsonObject = serializarSombrilla(sombrilla);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return jsonArray;
    }

    /**
     * Deserializa  un {@link JSONArray} en un Set de tipo {@link TreeSet<Sombrilla>}
     *
     * @param jsonArray array de tipo {@code JSONArray} que se desea deserializar.
     * @return un {@link TreeSet<Sombrilla>}
     * Si ocurre una excepción, se devuelve un listado vacío.
     */
    public static TreeSet<Sombrilla> deserializarListadoSombrillas(JSONArray jsonArray) {
        TreeSet<Sombrilla> listadoSombrillas = new TreeSet<>();
        try {
            listadoSombrillas = new TreeSet<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Sombrilla sombrilla = deserializarSombrilla(jsonArray.getJSONObject(i));
                listadoSombrillas.add(sombrilla);
            }

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return listadoSombrillas;
    }
}
