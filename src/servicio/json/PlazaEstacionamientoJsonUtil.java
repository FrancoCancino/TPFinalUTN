package servicio.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servicio.clases.PlazaEstacionamiento;

import java.util.TreeSet;

public final class PlazaEstacionamientoJsonUtil {
    /**
     * Serializa un objeto de tipo {@link PlazaEstacionamiento} en un {@link JSONObject}.
     */
    public static JSONObject serializarPlazaEstacionamiento(PlazaEstacionamiento plazaEstacionamiento) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("id", plazaEstacionamiento.getId());
            jsonObject.put("ocupado", plazaEstacionamiento.getOcupado());
            jsonObject.put("precio", plazaEstacionamiento.getPrecio());
            jsonObject.put("prioritario", plazaEstacionamiento.getIsPrioritario());

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return jsonObject;
    }

    /**
     * Deserializa  un {@link JSONObject} un objeto de tipo {@link PlazaEstacionamiento}
     */
    public static PlazaEstacionamiento deserializarPlazaEstacionamiento(JSONObject jsonObject) {
        PlazaEstacionamiento plaza = new PlazaEstacionamiento();

        try {
            plaza.setId(jsonObject.getInt("id"));
            plaza.setOcupado(jsonObject.getBoolean("ocupado"));
            plaza.setPrecio(jsonObject.getDouble("precio"));
            plaza.setPrioritario(jsonObject.getBoolean("prioritario"));

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return plaza;
    }

    /**
     * Serializa un objeto de tipo {@link TreeSet <PlazaEstacionamiento>} en un {@link JSONArray}.
     *
     * @param listado Set de tipo {@code TreeSet<PlazaEstacionamiento>} que se desea serializar.
     * @return un {@link JSONArray} que representa los datos del {@code listado}.
     * Si ocurre una excepción, se devuelve un JSONArray vacío.
     */
    public static JSONArray serializarListadoPlazasEstacionamiento(TreeSet<PlazaEstacionamiento> listado) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();

            for (PlazaEstacionamiento plaza : listado) {
                JSONObject jsonObject = serializarPlazaEstacionamiento(plaza);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return jsonArray;
    }

    /**
     * Deserializa  un {@link JSONArray} en un Set de tipo {@link TreeSet<PlazaEstacionamiento>}
     *
     * @param jsonArray array de tipo {@code JSONArray} que se desea deserializar.
     * @return un {@link TreeSet<PlazaEstacionamiento>}
     * Si ocurre una excepción, se devuelve un listado vacío.
     */
    public static TreeSet<PlazaEstacionamiento> deserializarListadoPlazasEstacionamiento(JSONArray jsonArray) {
        TreeSet<PlazaEstacionamiento> listado = new TreeSet<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                PlazaEstacionamiento plaza = deserializarPlazaEstacionamiento(jsonArray.getJSONObject(i));
                listado.add(plaza);
            }
        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return listado;
    }
}
