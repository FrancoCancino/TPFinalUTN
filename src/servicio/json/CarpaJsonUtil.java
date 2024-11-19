package servicio.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import servicio.clases.Carpa;
import servicio.enums.VarianteCarpa;

import java.io.IOException;

public class CarpaJsonUtil {

    public static JSONObject serializar(Carpa carpa) {
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
            System.out.println(exception.getMessage());
        }
        return jsonObject;
    }

    public static Carpa deserializar(JSONObject jsonObject) {
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
            System.out.println(exception.getMessage());
        }
        return carpa;
    }
}
