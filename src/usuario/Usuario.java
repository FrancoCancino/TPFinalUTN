package usuario;

public class Usuario {
    //Atributos
    private String DNI;
    private boolean activo;
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String nacionalidad;
    private String ciudad;
    private String contrasenia;

    //Constructores
    //Constructor con todos los datos.
    public Usuario(String contrasenia, String nacionalidad, String ciudad, String celular, String mail, String apellido, String nombre, String DNI) {
        this.DNI = DNI;
        this.contrasenia = contrasenia;
        this.nacionalidad = nacionalidad;
        this.ciudad = ciudad;
        this.celular = celular;
        this.mail = mail;
        this.apellido = apellido;
        this.nombre = nombre;
        this.activo = true;
    }
    //Constructor con info más importante.
    public Usuario(String nombre, String apellido, String celular, String contrasenia, String mail, String DNI) {
        this.DNI = DNI;
        this.activo = true;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.contrasenia = contrasenia;
        this.mail = mail;
    }
    //Constructor con email y contraseña
    public Usuario(String mail, String contrasenia) {
        this.mail = mail;
        this.contrasenia = contrasenia;
    }
    //Constructor nulo
    public Usuario() {
        this.contrasenia = null;
        this.ciudad = null;
        this.nacionalidad = null;
        this.celular = null;
        this.mail = null;
        this.nombre = null;
        this.apellido = null;
        this.activo = false;
        this.DNI = null;
    }

    //Getters y Setters
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    //Metodos
    @Override
    public String toString() {
        return
                "Nombre: '" + nombre + '\'' +
                        ", apellido:'" + apellido + '\'' +
                        "DNI:'" + DNI + '\'' +
                ", activo:" + activo +
                ", mail:'" + mail + '\'' +
                        ", contraseña:'" + contrasenia + '\'' +
                        ", celular:'" + celular + '\'' +
                ", nacionalidad:'" + nacionalidad + '\'' +
                ", ciudad:'" + ciudad + '\'';
    }

}
