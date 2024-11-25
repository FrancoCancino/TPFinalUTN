
public class Main {
    public static void main(String[] args) {

        LogIn lg =  new LogIn();
        lg.Menu();

        //LEER PARA SABER COMO TESTEAR EL PROGRAMA

        //Si queres iniciate sesión con mi usuario  que ya tiene dos reservas cargadas (una acttiva y una inactttiva) dni 43740866 pass River!
        //Le puse un metodo momentaneo a GestionServicios para que inicialice y cargue varios servicios así poder testetar. Todos los que tenias vos antets en el main.
        //En el menu principal arriba del todo fui inicializando todo y explicando  por qué y todo eso,,, así iba probando  ya con los menues.



        // COSAS QUE FALTAN HACER

        //CHEQUEAR LEER BIEN TODOS LOS ARCHIVOS (No chequee el de comprobante. Esta en la parte de crearReserva comentado para que lo puedas chusmear)

        //AGREGAR FUNCIONES QUE VALIDAN SERVICIOS DISPONIBLES (Hecho a medias. No termina de andar bien pq le pifie en algunos returns.)

        //Hice una funcion(En gestorServicios) para ver si hay  servicios disponibles pero no termino de entender bien los returns si son false o no,
        //Si queres pegale un ojo a ver si lo podes dejar andando. Deberia ser un tema de arreglar cuando hay o cuando no hay servicios.


        //resolver el tema de la interfaz (No lo toque) --  falta implementar en cada menu...

        //armar los menues (Hecho, falta pulir las validaciones (En el menuMisReservas no puse ninguna))



        //COSAS A CHEQUEAR

        //Que el grabado y el uso de comprobante funcione bien. Yo ni lo miré ni nada.

        //El  usuario no puede ccargar un tipo de servicio distinto cuando hace un alquiler. Tiene que ser todos de un mismo tipo. Esto es porque
        //Se le pasa por parametro el tipo de servicio que elige cuando hace un UsuarioTemporal. No lo quise modificcar
        //Por si se empezaba a romper todo. Capaz lo habias hecho así porque no se podia hacer un alquiler de más de un tipo...
        //EN el caso de que se pueda es tan simple como borrar el metodo de pedir tipo de servicio y ponerlo fuera del bucle do while para q lo vuelva a pedir
        //Una vez hecho ya 1 alquiler.

        //Algunas validaciones dentttro de bucles do while son incoomodas.  Si se rompe el bucle tira el error de la excepcion cuando a veces no deberia (Más que nada alguna mia q ahora no me acuerdo)

        //Que el mostrado de datos este prolijo, toquetee algunos ToString para mostrar solo datos utiles. No le di tantta bola igual pq es más visual q ootra cosa

        //Hay algunos nombres poco intuiivos o con malas practticas (Yo hice algunas abreviaturas raras)


        //COOSAS QUE HICE ESTA MADRUGADA

        //lo pongo todo acá así no queda largo el mensaje por wpp.

        //Ya anda perfecto el grabado de alquileres  y lista de alquileres, hay una para sobreescribir (que se implementa cuando se da de baja un alquiler) y otro para
        //agregar una reserva a una lista que seria el grabado normal.

        //Modifique un toque tu funcion de crear reserva para modularizarla un toque, haciendo que el metoodo en si haga una listaAlquileres
        //Dps muesttre el coomprobante fuera del metodo y por ulitmo loo serialize y lo mande a JSON.

        //Esta el menuReservas para que el usuario elija si ver sus reservas y si quiere dar de baja alguna. La logica del menu esta pero como puse arriba falta validar dattos

        //Cuando se da de baja una reserva ignore complettamente el comprobantte. Habria q pensar si una vez cambiado el estado del archivo reservas habria q cambiarlo ttmb del archivo Comprobanttes

        //En teoria ahí los menus deberian estar al 100%, fijate si quisieras cambiar algo

        // ============================================= cosas para hacer

        // GRABAR TODOS LOS SERVICIOS EN EL MISMO ARCHIVO  // CARGAR LAS COLECCIONES DE SERVICIOS DESDE EL ARCHIVO

        // copiar fecha en creacionReserva ();

        // manejar la excepcion donde no hay serviciosdisponibles, que estaba en crearAlquiler()



        // DAR DE BAJA EL COMPROBANTE CUANDO SE CANCELA UNA RESERVA
        
        // FUNCIONES PARA INICIALIZAR LOS SERVICIOS, TIPO CREA LAS CARPAS, PLAZAS. SE USAN UNA UNICA VEZ Y SE CARGAN EL ARCHIVO. EL SISTEMA DESPUES LAS LEE DEL ARCHIVO

        // ACOMODAR LOS TEXTOS DE LAS INTERFACES

        //DIAGRAMA UML


/*

        System.out.println("---------------------- probando crear alquiler");
        Alquiler alquilerParcial = new Alquiler(LocalDate.of(2025, 11, 21), LocalDate.of(2025, 11, 24),TipoServicio.CARPA);
        try{
            gestorAlquiler.crearAlquilerUsuario(alquilerParcial, gestorServicio, gestionComprobanteAlquiler,"jjjjjjjjjjj");
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }

        */


        /*

        System.out.println("-------------- PROBANDO METODOS JSON ALQUILER/COMPROBANTE ------------------------");
        List<Alquiler> listaAlquileresPrueba = new ArrayList<>();

        Alquiler alquiler11 = new Alquiler(LocalDate.of(2024, 11, 21), LocalDate.of(2024, 11, 24), TipoServicio.CARPA,"CP-1","1");
        Alquiler alquiler22 = new Alquiler(LocalDate.of(2024, 11, 28), LocalDate.of(2024, 11, 29), TipoServicio.SOMBRILLA,"SM-1","2");
        Alquiler alquiler33 = new Alquiler(LocalDate.of(2024, 11, 23), LocalDate.of(2024, 11, 26), TipoServicio.CARPA,"CP-2","3");

        listaAlquileresPrueba.add(alquiler11);
        listaAlquileresPrueba.add(alquiler22);
        listaAlquileresPrueba.add(alquiler33);


        ComprobanteAlquiler comprobanteAlquiler = new ComprobanteAlquiler(89.000, 90.000, listaAlquileresPrueba);

        JSONObject jsonObject = ComprobanteJsonUtil.serializarComprobanteAlquiler(comprobanteAlquiler);

        System.out.println(jsonObject.toString());

        ComprobanteAlquiler comprobanteLeido = ComprobanteJsonUtil.deserializarComprobanteAlquiler(jsonObject);

        System.out.println("------ COMPROBANTE LEIDO ---------");
        System.out.println(comprobanteLeido);


*/



    }
}