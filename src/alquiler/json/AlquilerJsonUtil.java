package alquiler.json;

import alquiler.clases.Alquiler;
import alquiler.clases.ComprobanteAlquiler;
import alquiler.enums.TipoServicio;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import usuario.OperacionesLectoEscritura;
import usuario.Usuario;
import utils.Constantes;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlquilerJsonUtil {

    /**
     * Serializa un objeto de tipo {@link Alquiler} en un {@link JSONObject}.
     *
     * @param alquiler el objeto de tipo {@code Alquiler} que se desea serializar.
     * @return un {@link JSONObject} que representa los datos del {@code Alquiler}.
     * Si ocurre una excepción, se devuelve un JSON vacío.
     */
    public static JSONObject serializarAlquiler(Alquiler alquiler) {

        JSONObject JsonObj = null;
        try {
            JsonObj = new JSONObject();

            JsonObj.put("id", alquiler.getId());
            JsonObj.put("fechaAlta", alquiler.getFechaAlta().toString());
            JsonObj.put("fechaBaja", alquiler.getFechaBaja().toString());
            JsonObj.put("activo", alquiler.isActivo());
            JsonObj.put("tipoServicio", alquiler.getTipoServicio().toString());
            JsonObj.put("idServicio", alquiler.getIdServicio());
            JsonObj.put("idUsuario", alquiler.getIdUsuario());
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
        return JsonObj;
    }

    /**
     * Deserializa  un {@link JSONObject} en un objeto de tipo {@link Alquiler}
     *
     * @param jsonObject el objeto de tipo {@code JSONObject} que se desea deserializar.
     * @return un {@link Alquiler}
     * Si ocurre una excepción, se devuelve una Alquiler vacío.
     */

    public static Alquiler deserializarAlquiler(JSONObject jsonObject) {

        Alquiler alquiler = new Alquiler();

        try {
            alquiler.setId(jsonObject.getString("id"));
            alquiler.setFechaAlta(LocalDate.parse(jsonObject.getString("fechaAlta"), Constantes.FORMATTER_DATE));
            alquiler.setFechaBaja(LocalDate.parse(jsonObject.getString("fechaBaja"), Constantes.FORMATTER_DATE));
            alquiler.setActivo(jsonObject.getBoolean("activo"));
            alquiler.setTipoServicio(TipoServicio.valueOf(jsonObject.getString("tipoServicio")));
            alquiler.setIdServicio(jsonObject.getString("idServicio"));
            alquiler.setIdUsuario(jsonObject.getString("idUsuario"));
        } catch (JSONException exception) {
            System.err.println(exception.getMessage());
        }

        return alquiler;
    }



    public static JSONArray serializarListaAlquiler(List<Alquiler> listaAlquiler) {

        JSONObject temp; //Creo un objeto Json temporal

        JSONArray arrTemp = OperacionesLectoEscritura.leerArchivoARRAY(Constantes.nombreArchivoAlquiler);
        //Creo un JsonArr temporal y le guardo el array que tengamos en el archivo. Si no hay ninguno crea uno nuevo
    try{
        for (Alquiler alquiler : listaAlquiler){
            temp = new JSONObject();
            temp = serializarAlquiler(alquiler);  //Guardo el alquiler  en el objetoJson(Para eso uso la función que lo convierte en objetoJson
            arrTemp.put(temp);
        }
        }catch (JSONException e){
    System.out.println(e.getMessage());
    }

            return arrTemp;
    }

    public static JSONArray serializarListaAlquilerSobreescribiendo(List<Alquiler> listaAlquiler) {

        JSONObject temp; //Creo un objeto Json temporal
        JSONArray arrTemp = new JSONArray();
        //Creo un JsonArr temporal vacio
        try{
            for (Alquiler alquiler : listaAlquiler){
                temp = new JSONObject();
                temp = serializarAlquiler(alquiler);  //Guardo el alquiler  en el objetoJson(Para eso uso la función que lo convierte en objetoJson
                arrTemp.put(temp);
            }
        }catch (JSONException e){
            System.out.println(e.getMessage());
        }

        return arrTemp;
    }


    public static List<Alquiler> deserializarListaAlquiler(JSONArray jsonArray) {

        List<Alquiler> listaAlquileres = new ArrayList<>();

        JSONObject jsonObject;
        try{
            for (int i = 0; i < jsonArray.length();i++){
                jsonObject = new JSONObject();
                jsonObject = jsonArray.getJSONObject(i);
                listaAlquileres.add(deserializarAlquiler(jsonObject));
            }
        }catch(JSONException e){
            System.out.println(e.getMessage());
        }

        return listaAlquileres;
    }



}
