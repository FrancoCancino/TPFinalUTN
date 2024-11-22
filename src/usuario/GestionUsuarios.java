package usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Scanner;

import static java.lang.Long.parseLong;

public class GestionUsuarios {

    public static Usuario crearUsuario(){

        //Crea el objeto usuario.Usuario cargado de información para el registro
        Scanner scan = new Scanner(System.in);
        Usuario usuario = new Usuario();

        System.out.println("Vamos a registrarte en nuestro gestor de balneario.");
        usuario.setActivo(true);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ingresá tu DNI. Ingresa solo números.");
        try{
            while (true) {
                String DNITemp = scan.nextLine();
                Usuario usuarioParaVerificar = extraerUsuarioPorDNI(DNITemp);
                //Este try engloba todo el metodo para asegurarse que el usuario que se este registrando no este registrado.
                // Si lo esta, retorna un usuario.Usuario con el DNI -1 para evitar ser subido en el archivo.
                if(usuarioParaVerificar != null){
                    throw new AutenticacionFallidaExcepcion("El DNI ingresado ya esta registrado.");
                }

                try {
                    parseLong(DNITemp);
                    if (DNITemp.length() < 7) {
                        throw new AutenticacionFallidaExcepcion("El DNI no puede tener menos de 7 digitos.");
                    }
                    usuario.setDNI(DNITemp);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("El dato introducido no es un DNI válido. Recordá que solo se admiten números.");
                } catch (AutenticacionFallidaExcepcion e) {
                    System.out.println(e.getMessage());
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Ingresa tu nombre (sin el apellido)");
            while (true) {
                String nombreTemp = scan.nextLine();
                try{
                    if (!nombreTemp.matches("^[\\p{L}\\s]+$")) {    //Se asegura de que el nombre no tenga numeros.
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
                    if (!apellidoTemp.matches("^[\\p{L}\\s]+$")) { //Se asegura de que el apellido no tenga numeros.
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

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Ingresá tu contraseña.");
            System.out.println("Esta debe contener al menos 5 caracteres, siendo uno un caracter especial.");
            while (true) {
                String contraseniaTemp = scan.nextLine();
                try {
                    if (contraseniaTemp.length() < 5) {
                        throw new AutenticacionFallidaExcepcion("La contraseña es muy corta");
                    } else if (!contraseniaTemp.matches(".*[^a-zA-Z0-9].*")) {      //Se asegura de que tenga un caracter especial
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
                    if (!ciudadTemp.matches("^[\\p{L}\\s]+$")) { //Se asegura de que la ciudad no tenga numeros.
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
                    if (!nacionalidadTemp.matches("^[\\p{L}\\s]+$")) { //Se asegura de que el pais no tenga numeros.
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
                        //Con este regex nos aseguramos de que el mail ingresado por el usuario contenga dichos dominios de correo.
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
        } catch (AutenticacionFallidaExcepcion e){
            usuario.setDNI("-1");
        }

        return usuario;
    }

    public JSONObject pasarUsuarioAObject(Usuario usuario){

        //Pasa un objeto usuario.Usuario a JsonObject
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

        //Crea un objeto Json a un objeto usuario.Usuario
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

        Usuario usuario = GestionUsuarios.crearUsuario();
        //Se guarda el usuario registrado en un objeto usuario temporal para luego ser grabado en el archivo.

        if (usuario.getDNI().equals("-1")){
            //Este if sirve para ver si un usuario se quiere registrar con su DNI ya cargado en el sistema.
            return "Error. El usuario ya está registrado.";
        }

        JSONObject temp; //Creo un objeto Json temporal

        temp = g1.pasarUsuarioAObject(usuario);     //Guardo el usuario en el objetoJson(Para eso uso la función que lo convierte en objetoJson


        JSONArray arrTemp = OperacionesLectoEscritura.leerArchivoARRAY("usuarios.json");
        //Creo un JsonArr temporal y le guardo el array que tengamos en el archivo. Si no hay ninguno crea uno nuevo

            arrTemp.put(temp);   //Mete el registro nuevo al array.

            OperacionesLectoEscritura.grabarArchivoARRAY(arrTemp,"usuarios.json");
            //Graba todo el array con el ultimo registro en el array.

            return "Registro éxitoso";
    }

    public void inicioSesion(){
        Scanner scan = new Scanner(System.in);

        System.out.println("------------------------------------------------------");
        Usuario usuario;
        do {
            System.out.println("Ingresá tu DNI:");
            String dni = scan.nextLine();
            usuario = extraerUsuarioPorDNI(dni);        //Se guarda el usuario que tenga el DNI ingresado por el usuario
            if(dni.equals("0")){        //Si el DNI es 0 es porque el usuario quiere salir del programa
                System.out.println("Saliendo del programa...");
                break;
            }
            if (usuario == null){
                System.out.println("El DNI ingresado no esta registrado. Prueba nuevamente o escribí '0' para salir del programa");
            }
        }while (usuario == null);

        String contrasenia;

        if (usuario != null){       //Si el usuario esta registrado(No es Null) se ejecuta esta parte del código.
            do{
                System.out.println("Ingresá tu contraseña:");
                contrasenia = scan.nextLine();
                if(contrasenia.equals("0")){    //Si la contraseña es 0 es porque el usuario quiere salir del programa
                    System.out.println("Saliendo del programa...");
                    break;
                }
                if (!contrasenia.equals(usuario.getContrasenia())){     //Se compara el string con la  contraseña leida del archivo.
                    System.out.println("Contraseña incorrecta. Intente nuevamente. Si deseas salir del programa escribí '0'");
                } else {
                    System.out.println("Bienvenido: "+ usuario.getNombre());
                    System.out.println("------------------------------------------------------");
                }
            } while(!contrasenia.equals(usuario.getContrasenia()));
        }
    }

    public static Usuario extraerUsuarioPorDNI(String DNI){

        //Creo un JsonArray para guardar el archivo actual del programa.
        //Si el  array esta vacio se retorna un usuario Vacio.
        JSONArray arrTemp = OperacionesLectoEscritura.leerArchivoARRAY("usuarios.json");
        for (int i = 0;i < arrTemp.length();i++){       //Recorro el JsonArray objeto por objeto
            JSONObject JSONObj = arrTemp.getJSONObject(i);      //Guardo cada objeto
            String dniTemp = JSONObj.getString("DNI");      //Guardo el DNI de cada objeto del array.
            if (dniTemp.equals(DNI)){           //Si ambos DNIs son iguales, se guarda el usuario  y se retorna.
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


