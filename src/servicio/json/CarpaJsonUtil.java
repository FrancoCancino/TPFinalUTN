package servicio.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servicio.GestionServicio;
import servicio.clases.Carpa;
import servicio.enums.VarianteCarpa;

import java.util.Set;
import java.util.TreeSet;


public class CarpaJsonUtil {

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
            jsonObject.put("capacidad", carpa.getCapacidad());
            jsonObject.put("precio", carpa.getPrecio());
            jsonObject.put("varianteCarpa", carpa.getVarianteCarpa());
            jsonObject.put("idPlazaEstacionamiento", carpa.getIdPlazaEstacionamiento());
            jsonObject.put("ubicacion", carpa.getUbicacion());

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
            carpa.setId(jsonObject.getInt("id"));
            carpa.setOcupado(jsonObject.getBoolean("ocupado"));
            carpa.setCapacidad(jsonObject.getInt("capacidad"));
            carpa.setPrecio(jsonObject.getDouble("precio"));

            carpa.setVarianteCarpa(VarianteCarpa.valueOf(jsonObject.getString("varianteCarpa")));
            carpa.setIdPlazaEstacionamiento(jsonObject.getInt("idPlazaEstacionamiento"));
            carpa.setUbicacion(jsonObject.getString("ubicacion"));

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return carpa;
    }

    public JSONArray serializarListadoCarpas(TreeSet<Carpa> listado) {
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

    public TreeSet<Carpa> deserializarListadoCarpas(JSONArray jsonArray){
        TreeSet<Carpa> listadoCarpas = new TreeSet<>();
        try {
            listadoCarpas = new TreeSet<>();
            for(int i = 0; i < jsonArray.length(); i++){
                Carpa carpa = deserializarCarpa(jsonArray.getJSONObject(i));
                listadoCarpas.add(carpa);
            }

        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }
        return listadoCarpas;
    }

}
