package utils;

import java.time.format.DateTimeFormatter;

public final class Constantes {

    public static final double PRECIO_ACTUAL_PREMIUM = 30000 ;
    public static final double PRECIO_ACTUAL_STANDARD = 25000;
    public static final String PREFIJO_CARPA = "CP-";
    public static final String PREFIJO_PLAZA_ESTACIONAMIENTO = "PE-";
    public static final String PREFIJO_SOMBRILLA = "SM-";
    public static final double PRECIO_ACTUAL_SOMBRILLA = 18000;
    public static final double PRECIO_ACTUAL_PlAZA_ESTACIONAMIENTO = 8000;

    public static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.n]");
    public static final DateTimeFormatter FORMATTER_DATE_TIME_MOSTRAR = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.n]");
    public static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd") ;

    // Tipos de servicio
    public static final String TIPO_CARPA = "Carpa";
    public static final String TIPO_SOMBRILLA = "Sombrilla";
    public static final String TIPO_PLAZA = "Plaza de Estacionamiento";


    private Constantes() {
    }


}
