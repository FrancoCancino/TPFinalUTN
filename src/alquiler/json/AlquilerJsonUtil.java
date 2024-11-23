package alquiler.json;

import alquiler.clases.Alquiler;
import alquiler.clases.ComprobanteAlquiler;
import alquiler.enums.TipoServicio;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import usuario.Usuario;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlquilerJsonUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



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
            JsonObj.put("id",alquiler.getId());
            JsonObj.put("fechaAlta", alquiler.getFechaAlta().format(FORMATTER));
            JsonObj.put("fechaBaja", alquiler.getFechaBaja().format(FORMATTER));
            JsonObj.put("activo",alquiler.isActivo());
            JsonObj.put("tipoServicio",alquiler.getTipoServicio().toString());
            JsonObj.put("idServicio",alquiler.getIdServicio());
            JsonObj.put("idUsuario",alquiler.getIdUsuario());
            JsonObj.put("idComprobante",alquiler.getIdComprobante());
        } catch(JSONException e){
            e.printStackTrace();
        }
        return JsonObj;
    }


    public static Alquiler deserializarAlquiler(JSONTokener jsonTokener) {

        //Crea un objeto Json a un objeto Alquiler
        JSONObject JSONObj = new JSONObject(jsonTokener);
        Alquiler alquiler = new Alquiler();

        alquiler.setId(JSONObj.getString("id"));
        alquiler.setFechaAlta(LocalDate.parse(JSONObj.getString("fechaAlta"),FORMATTER));
        alquiler.setFechaBaja(LocalDate.parse(JSONObj.getString("fechaBaja"),FORMATTER));
        alquiler.setActivo(JSONObj.getBoolean("activo"));
        alquiler.setTipoServicio(TipoServicio.valueOf(JSONObj.getString("tipoServicio")));
        alquiler.setIdServicio(JSONObj.getString("idServicio"));
        alquiler.setIdUsuario(JSONObj.getString("idUsuario"));
        alquiler.setIdComprobante(JSONObj.getString("idComprobante"));

        return alquiler;
    }
//
//    public static JSONArray serializarListaAlquiler(List<Alquiler> listaAlquiler) {
//
//    }
//
//    public static List<Alquiler> deserializarListaAlquiler(JSONArray jsonArray) {
//        List<Alquiler> listaAlquiler = new ArrayList<>();
//
//
//    }
}
