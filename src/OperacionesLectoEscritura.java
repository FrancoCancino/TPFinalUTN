import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class OperacionesLectoEscritura {

    //Grabar y leer JSON OBJECTS !
    public static void grabarArchivo(JSONObject obj, String nombreArchivo){
        try{
            FileWriter file = new FileWriter(nombreArchivo);
            file.write(obj.toString(3));
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

    //Estos dos metodos estan en prueba. Todavía no funcionan bien

    //Grabar y leer JSON Arrays !
    public static JSONArray leerArchivoARRAY(String nombreArchivo){

        JSONArray JSONArr = new JSONArray(); // Iniciar con un array vacío

        try {
            File file = new File(nombreArchivo);
            if (file.exists() && file.length() > 0) {        // Si el archivo existe y no está vacío
                JSONTokener tokener = new JSONTokener(new FileReader(nombreArchivo));
                JSONArr = new JSONArray(tokener);
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró.");
        } catch (JSONException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
        return JSONArr;
    }

    public static void grabarArchivoARRAY(JSONArray arr, String nombreArchivo){
        try{
            FileWriter file = new FileWriter(nombreArchivo);
            file.write(arr.toString(3));
            file.close();
        } catch(IOException e){
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }



}
