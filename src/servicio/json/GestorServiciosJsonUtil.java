package servicio.json;

import alquiler.clases.Alquiler;
import alquiler.enums.TipoServicio;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import servicio.clases.GestionServicio;
import utils.Constantes;

import java.time.LocalDate;

public class GestorServiciosJsonUtil {

    //Serializar GestorServicios
    public static JSONObject serializarServicios(GestionServicio gestorServicios) {

        JSONObject JsonObj = null;
        try {
            JsonObj = new JSONObject();
            JsonObj.put("carpas",CarpaJsonUtil.serializarListadoCarpas(gestorServicios.getListadoCarpas()));
            JsonObj.put("plazasEstacionamiento",PlazaEstacionamientoJsonUtil.serializarListadoPlazasEstacionamiento(gestorServicios.getListadoPlazasEstacionamiento()));
            JsonObj.put("sombrillas",SombrillaJsonUtil.serializarListadoSombrillas(gestorServicios.getListadoSombrillas()));

        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
        return JsonObj;
    }


    //Deserializar GestorServicios
    public static GestionServicio deserializarServicios(JSONObject jsonObject) {
        GestionServicio gestorServicios = new GestionServicio();

        try {
            // Deserializar lista de carpas
            if (jsonObject.has("carpas")) {
                JSONArray carpasArray = jsonObject.getJSONArray("carpas");
                gestorServicios.setListadoCarpas(CarpaJsonUtil.deserializarListadoCarpas(carpasArray));
            }

            // Deserializar lista de sombrillas
            if (jsonObject.has("sombrillas")) {
                JSONArray sombrillasArray = jsonObject.getJSONArray("sombrillas");
                gestorServicios.setListadoSombrillas(SombrillaJsonUtil.deserializarListadoSombrillas(sombrillasArray));
            }

            // Deserializar lista de plazas de estacionamiento
            if (jsonObject.has("plazasEstacionamiento")) {
                JSONArray plazasArray = jsonObject.getJSONArray("plazasEstacionamiento");
                gestorServicios.setListadoPlazasEstacionamiento(PlazaEstacionamientoJsonUtil.deserializarListadoPlazasEstacionamiento(plazasArray));
            }
        } catch (JSONException e) {
            System.err.println("Error al deserializar los servicios: " + e.getMessage());
        }

        return gestorServicios;
    }



}
