package servicio.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servicio.clases.Carpa;
import servicio.enums.VarianteCarpa;

import java.util.Set;
import java.util.TreeSet;


public final class CarpaJsonUtil {

    /**
     * Serializa un objeto de tipo {@link Carpa} en un {@link JSONObject}.
     *
     * @param carpa el objeto de tipo {@code Carpa} que se desea serializar.
     * @return un {@link JSONObject} que representa los datos de la {@code Carpa}.
     * Si ocurre una excepción, se devuelve un JSON vacío.
     */
    public static JSONObject serializarCarpa(Carpa carpa) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("id", carpa.getId());
            jsonObject.put("ocupado", carpa.getOcupado());
            jsonObject.put("precio", carpa.getPrecio());
            jsonObject.put("varianteCarpa", carpa.getVarianteCarpa());
            jsonObject.put("idPlazaEstacionamiento", carpa.getIdPlazaEstacionamiento());

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return jsonObject;
    }

    /**
     * Deserializa  un {@link JSONObject} un objeto de tipo {@link Carpa}
     *
     * @param jsonObject el objeto de tipo {@code JSONObject} que se desea deserializar.
     * @return un {@link Carpa}
     * Si ocurre una excepción, se devuelve una Carpa vacía.
     */
    public static Carpa deserializarCarpa(JSONObject jsonObject) {
        Carpa carpa = new Carpa();

        try {
            carpa.setId(jsonObject.getString("id"));
            carpa.setOcupado(jsonObject.getBoolean("ocupado"));
            carpa.setPrecio(jsonObject.getDouble("precio"));

            carpa.setVarianteCarpa(VarianteCarpa.valueOf(jsonObject.getString("varianteCarpa")));
            carpa.setIdPlazaEstacionamiento(jsonObject.getString("idPlazaEstacionamiento"));

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return carpa;
    }

    /**
     * Serializa un objeto de tipo {@link TreeSet<Carpa>} en un {@link JSONArray}.
     *
     * @param listado Set de tipo {@code TreeSet<Carpa>} que se desea serializar.
     * @return un {@link JSONArray} que representa los datos del {@code Listado}.
     * Si ocurre una excepción, se devuelve un JSONArray vacío.
     */
    public static JSONArray serializarListadoCarpas(Set<Carpa> listado) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();

            for (Carpa carpa : listado) {
                JSONObject jsonObject = serializarCarpa(carpa);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return jsonArray;
    }

    /**
     * Deserializa  un {@link JSONArray} un Set de tipo {@link TreeSet<Carpa>}
     *
     * @param jsonArray array de tipo {@code JSONArray} que se desea deserializar.
     * @return un {@link TreeSet<Carpa>}
     * Si ocurre una excepción, se devuelve un listado vacío.
     */
    public static Set<Carpa> deserializarListadoCarpas(JSONArray jsonArray) {
        Set<Carpa> listadoCarpas = new TreeSet<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Carpa carpa = deserializarCarpa(jsonArray.getJSONObject(i));
                listadoCarpas.add(carpa);
            }

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return listadoCarpas;
    }

}
