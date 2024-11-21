import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.Long.parseLong;

public class GestionUsuarios {

    //Esta clase va a leer los usuarios que haya registrados en el archivo.
    //para determinar si el usuario va a iniciar sesion o va a registrarse.

    public static Usuario registrarUsuario() {

        Scanner scan = new Scanner(System.in);
        Usuario usuario = new Usuario();

        System.out.println("Vamos a registrarte en nuestro gestor de balneario. Los campos que tengan un asterisco (*) son de caracter obligatorio.");
        usuario.setActivo(true);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresa tu nombre");
        while (true) {
            String nombreTemp = scan.nextLine();
            if (nombreTemp.matches("^[\\p{L}\\s]+$")) {
                usuario.setNombre(nombreTemp);
                break;
            } else {
                System.out.println("Error: Los números no son validos en este campo.");
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresa tu apellido");
        while (true) {
            String apellidoTemp = scan.nextLine();
            if (apellidoTemp.matches("^[\\p{L}\\s]+$")) {
                usuario.setApellido(apellidoTemp);
                break;
            } else {
                System.out.println("Error: Los números no son validos en este campo.");
            }
        }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("Ingresá tu DNI (*). Ingresa solo números.");
        while (true) {
            String DNITemp = scan.nextLine();
            try {
                parseLong(DNITemp);
                usuario.setDNI(DNITemp);
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato introducido no es un DNI válido. Recordá que solo se admiten números.");
            }
        }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Ingresá tu contraseña (*)");
            while (true) {
            String contraseniaTemp = scan.nextLine();
            System.out.println("Ingresá tu contraseña de vuelta (*)");
            String contraseniaTemp2 = scan.nextLine();
            if (contraseniaTemp.equals(contraseniaTemp2)) {
                usuario.setContrasenia(contraseniaTemp);
                break;
            } else {
                System.out.println("Las contraseñas ingresadas no son las mismas. Ingresalas de vuelta.");
            }
        }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresa tu ciudad");
        while (true) {
            String ciudadTemp = scan.nextLine();
            if (ciudadTemp.matches("^[\\p{L}\\s]+$")) {
                usuario.setCiudad(ciudadTemp);
                break;
            } else {
                System.out.println("Error: Los números no son validos en este campo.");
            }
        }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresa tu nacionalidad");
        while (true) {
            String nacionalidadTemp = scan.nextLine();
            if (nacionalidadTemp.matches("^[\\p{L}\\s]+$")) {
                usuario.setNacionalidad(nacionalidadTemp);
                break;
            } else {
                System.out.println("Error: Los números no son validos en este campo.");
            }
        }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresá tu número de celular. Ingresa solo números.");
        while (true) {
            String celularTemp = scan.nextLine();
            try {
                parseLong(celularTemp);
                usuario.setCelular(celularTemp);
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato introducido no es un celular válido. Recordá que solo se admiten números.");
            }
        }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresá tu correo electronico (*). Recordá que debe ser una dirección de correo válida. ");
        while (true) {
            String mailTemp = scan.nextLine();
            if (mailTemp.matches(".*@(gmail\\.com|hotmail\\.com|yahoo\\.com|outlook\\.com|icloud\\.com|estudiante.mdp.utn.edu.ar|mail\\.com|.*\\.edu\\|*\\.es)")) {
                usuario.setMail(mailTemp);
                break;
            } else {
                System.out.println("Error: El dato ingresado no es un correo electronico válido.");
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Felicitaciones " + usuario.getNombre() + ", te has registrado con éxito");
        return usuario;
    }

    public JSONObject pasarUsuarioAObject(Usuario usuario){
        JSONObject JsonObj = null;
        try {
            JsonObj = new JSONObject();
            JsonObj.put("DNI",usuario.getDNI());
            JsonObj.put("contrasenia",usuario.getContrasenia());
            JsonObj.put("nacionalidad",usuario.getNacionalidad());
            JsonObj.put("ciudad",usuario.getCiudad());
            JsonObj.put("celular",usuario.getCelular());
            JsonObj.put("mail",usuario.getMail());
            JsonObj.put("nombre",usuario.getNombre());
            JsonObj.put("apellido",usuario.getApellido());
            JsonObj.put("estado",usuario.isActivo());
        } catch(JSONException e){
            e.printStackTrace();
        }
        return JsonObj;
    }

    public static Usuario pasarJSONObjectAUsuario(JSONTokener tokener){
        JSONObject JSONObj = new JSONObject(tokener);
        Usuario usuario = new Usuario();

        usuario.setDNI(JSONObj.getString("DNI"));
        usuario.setContrasenia(JSONObj.getString("contrasenia"));
        usuario.setNacionalidad(JSONObj.getString("nacionalidad"));
        usuario.setCiudad(JSONObj.getString("ciudad"));
        usuario.setCelular(JSONObj.getString("celular"));
        usuario.setMail(JSONObj.getString("mail"));
        usuario.setNombre(JSONObj.getString("nombre"));
        usuario.setApellido(JSONObj.getString("apellido"));
        usuario.setActivo(JSONObj.getBoolean("estado"));
            return usuario;
    }

    public String registro(GestionUsuarios g1){

        Usuario usuario = GestionUsuarios.registrarUsuario();
        //Se guarda el usuario registrado en un objeto usuario temporal para luego ser grabado en el archivo.


        JSONObject temp; //Creo un objeto temporal
        temp = g1.pasarUsuarioAObject(usuario);     //Guardo el dicho objeto el usuario pasado a JSONOBject


        JSONArray arrTemp = OperacionesLectoEscritura.leerArchivoARRAY("usuarios.json");
        //Creo un JsonArr temporal y le guardo el array que tengamos en el archivo. Si no hay ninguno crea uno nuevo

        arrTemp.put(temp);   //Mete el registro nuevo al array.

        OperacionesLectoEscritura.grabarArchivoARRAY(arrTemp,"usuarios.json");
        //Graba todo el array con el ultimo registro en el array.
            return "El archivo se ha grabado con éxito.";
    }

    public void inicioSesion(){
        Scanner scan = new Scanner(System.in);

        System.out.println("------------------------------------------------------");
        Usuario usuario;
        do {
            System.out.println("Ingresá tu DNI:");
            String dni = scan.nextLine();
            usuario = extraerUsuarioPorDNI(dni);

            if (usuario == null){
                System.out.println("El DNI ingresado no esta registrado, prueba nuevamente o pulsa 0 para registrarte..");
            }
        }while (usuario == null);

        String contrasenia;

        do{
            System.out.println("Ingresá tu contraseña:");
            contrasenia = scan.nextLine();
            if (!contrasenia.equals(usuario.getContrasenia())){
                System.out.println("Contraseña incorrecta. Intente nuevamente");
            }
        } while(!contrasenia.equals(usuario.getContrasenia()));
        System.out.println("Bienvenido: "+ usuario.getNombre());
        System.out.println("------------------------------------------------------");
    }

    public Usuario extraerUsuarioPorDNI(String DNI){

        JSONArray arrTemp = OperacionesLectoEscritura.leerArchivoARRAY("usuarios.json");
        for (int i = 0;i < arrTemp.length();i++){
            JSONObject JSONObj = arrTemp.getJSONObject(i);
            String dniTemp = JSONObj.getString("DNI");
            if (dniTemp.equals(DNI)){
                Usuario usuario = new Usuario();

                usuario.setDNI(JSONObj.getString("DNI"));
                usuario.setContrasenia(JSONObj.getString("contrasenia"));
                usuario.setNacionalidad(JSONObj.getString("nacionalidad"));
                usuario.setCiudad(JSONObj.getString("ciudad"));
                usuario.setCelular(JSONObj.getString("celular"));
                usuario.setMail(JSONObj.getString("mail"));
                usuario.setNombre(JSONObj.getString("nombre"));
                usuario.setApellido(JSONObj.getString("apellido"));
                usuario.setActivo(JSONObj.getBoolean("estado"));
                return usuario;
            }
        }
            return null;
    }
}


