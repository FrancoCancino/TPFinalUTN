import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Long.parseLong;

public class GestionUsuarios {

    public static Usuario registrarUsuario(){

        Scanner scan = new Scanner(System.in);
        Usuario usuario = new Usuario();

        System.out.println("Vamos a registrarte en nuestro gestor de balneario.");
        usuario.setActivo(true);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresa tu nombre (sin el apellido)");
        while (true) {
            String nombreTemp = scan.nextLine();
            try{
                if (!nombreTemp.matches("^[\\p{L}\\s]+$")) {
                    throw new AutenticacionFallidaExcepcion("Los números no son validos en este campo.");
                }else if (nombreTemp.length() < 3){
                    throw new AutenticacionFallidaExcepcion("El nombre no puede tener menos de 3 letras.");
                }else{
                    usuario.setNombre(nombreTemp);
                    break;
                }
            }catch (AutenticacionFallidaExcepcion e){
                System.out.println(e.getMessage());
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresa tu apellido");
        while (true) {
            String apellidoTemp = scan.nextLine();
            try{
                if (!apellidoTemp.matches("^[\\p{L}\\s]+$")) {
                    throw new AutenticacionFallidaExcepcion("Los números no son validos en este campo.");
                }else if (apellidoTemp.length() < 3){
                    throw new AutenticacionFallidaExcepcion("El apellido no puede tener menos de 3 letras.");
                }else{
                    usuario.setApellido(apellidoTemp);
                    break;
                }
            }catch (AutenticacionFallidaExcepcion e){
                System.out.println(e.getMessage());
            }
        }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresá tu DNI. Ingresa solo números.");
        while (true) {
            String DNITemp = scan.nextLine();
            try {
                parseLong(DNITemp);
                if (DNITemp.length() < 7){
                    throw new AutenticacionFallidaExcepcion("El DNI no puede tener menos de 7 digitos.");
                }
                usuario.setDNI(DNITemp);
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato introducido no es un DNI válido. Recordá que solo se admiten números.");
            }catch (AutenticacionFallidaExcepcion e){
                System.out.println(e.getMessage());
            }
        }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Ingresá tu contraseña.");
            System.out.println("Esta debe contener al menos 5 caracteres, siendo uno un caracter especial.");
                while (true) {
                    String contraseniaTemp = scan.nextLine();
                    try {
                        if (contraseniaTemp.length() < 5) {
                            throw new AutenticacionFallidaExcepcion("La contraseña es muy corta");
                        } else if (!contraseniaTemp.matches(".*[^a-zA-Z0-9].*")) {
                            throw new AutenticacionFallidaExcepcion("La contraseña no dispone de un caracter especial");
                        }

                        System.out.println("Confirma tu contraseña de vuelta.");
                        String contraseniaTemp2 = scan.nextLine();
                        if (contraseniaTemp.equals(contraseniaTemp2)) {
                            usuario.setContrasenia(contraseniaTemp);
                            break;
                        } else {
                            System.out.println("Las contraseñas ingresadas no son las mismas. Ingresalas de vuelta.");
                        }
                    } catch (AutenticacionFallidaExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                }

     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresa tu ciudad");
        while (true) {
            String ciudadTemp = scan.nextLine();
            try{
                if (!ciudadTemp.matches("^[\\p{L}\\s]+$")) {
                    throw new AutenticacionFallidaExcepcion("Los números no son validos en este campo");
                } else if (ciudadTemp.length() < 3) {
                    throw new AutenticacionFallidaExcepcion("El nombre de la ciudad debe tener al menos 3 letras");
                } else {
                        usuario.setCiudad(ciudadTemp);
                        break;
                    }
            }catch (AutenticacionFallidaExcepcion e){
                System.out.println(e.getMessage());
            }
        }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("De qué país sos?");
        while (true) {
            String nacionalidadTemp = scan.nextLine();
            try{
                if (!nacionalidadTemp.matches("^[\\p{L}\\s]+$")) {
                    throw new AutenticacionFallidaExcepcion("Los números no son validos en este campo");
                } else if (nacionalidadTemp.length() < 3) {
                    throw new AutenticacionFallidaExcepcion("El nombre del pais debe tener al menos 3 letras");
                } else {
                    usuario.setNacionalidad(nacionalidadTemp);
                    break;
                }
            }catch (AutenticacionFallidaExcepcion e){
                System.out.println(e.getMessage());
            }
        }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresá tu número de celular. Ingresa solo números.");
        while (true) {
            String celularTemp = scan.nextLine();
            try {
                parseLong(celularTemp);
                if(celularTemp.length() < 7){
                    throw new AutenticacionFallidaExcepcion("El número debe tener al menos 7 digitos.");
                }
                usuario.setCelular(celularTemp);
                break;
            } catch (NumberFormatException e) {
                System.out.println("El dato introducido no es un celular válido. Recordá que solo se admiten números.");
            }catch (AutenticacionFallidaExcepcion e){
                System.out.println(e.getMessage());
            }
        }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresá tu correo electronico. Recordá que debe ser una dirección de correo válida. ");
        while (true) {
            String mailTemp = scan.nextLine();
            try{
                if (mailTemp.matches(".*@(gmail\\.com|hotmail\\.com|yahoo\\.com|outlook\\.com|icloud\\.com|estudiante.mdp.utn.edu.ar|mail\\.com|.*\\.edu\\|*\\.es)")) {
                    usuario.setMail(mailTemp);
                    break;
                } else {
                    throw new AutenticacionFallidaExcepcion("El dato ingresado no es un correo electronico válido.");
                }
            }catch (AutenticacionFallidaExcepcion e){
                System.out.println(e.getMessage());
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

    public String registro(GestionUsuarios g1) {

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
            if(dni.equals("0")){
                System.out.println("Saliendo del programa...");
                break;
            }
            if (usuario == null){
                System.out.println("El DNI ingresado no esta registrado. Prueba nuevamente o escribí '0' para salir del programa");
            }
        }while (usuario == null);

        String contrasenia;

        if (usuario != null){
            do{
                System.out.println("Ingresá tu contraseña:");
                contrasenia = scan.nextLine();
                if(contrasenia.equals("0")){
                    System.out.println("Saliendo del programa...");
                    break;
                }
                if (!contrasenia.equals(usuario.getContrasenia())){
                    System.out.println("Contraseña incorrecta. Intente nuevamente. Si deseas salir del programa escribí '0'");
                } else {
                    System.out.println("Bienvenido: "+ usuario.getNombre());
                    System.out.println("------------------------------------------------------");
                }
            } while(!contrasenia.equals(usuario.getContrasenia()));
        }
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


