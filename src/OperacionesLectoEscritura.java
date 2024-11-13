import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OperacionesLectoEscritura {

    //Grabar y leer JSON OBJECTS !
    public static void grabarArchivo(JSONObject obj, String nombreArchivo){
        try{
            FileWriter file = new FileWriter(nombreArchivo);
            file.write(obj.toString());
            file.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static JSONTokener leerArchivo(String nombreArchivo){
        JSONTokener tokener = null;
        try{
            tokener = new JSONTokener(new FileReader(nombreArchivo));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
            return tokener;
    }


}
