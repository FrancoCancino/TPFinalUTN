package servicio;
import java.util.Set;
import java.util.TreeSet;

public class GestionServicio {
    private Set<Carpa> listadoCarpas;
    private Set<Sombrilla> listadoSombrillas;
    private Set<PlazaEstacionamiento> listadoPlazasEstacionam;
    private final String nombreArchivoServicio = "servicios.json";

    // Constructores

    public GestionServicio() {
        this.listadoCarpas = new TreeSet<>();
        this.listadoSombrillas = new TreeSet<>();
        this.listadoPlazasEstacionam = new TreeSet<>();
    }

    // Metodos

}
