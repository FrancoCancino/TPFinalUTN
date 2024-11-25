package utils;


import alquiler.clases.Alquiler;

public class ConsolaUtils {
    /**
     * Clase utilitaria para manejar la presentación en consola
     * Contiene métodos estáticos para facilitar la presentación uniforme y centrada de información
     */

    // Constantes
    private static final int ANCHO_LINEA = 60;
    private static final String CARACTER_LINEA = "-";
    private static final String CARACTER_DOBLE = "=";

    // EMOJIS
    private static final String EMOJI_CALENDARIO = "📅";
    private static final String EMOJI_DINERO = "💰";
    private static final String EMOJI_CASA = "🏠";
    private static final String EMOJI_DOCUMENTO = "📄";
    private static final String EMOJI_USUARIO = "👤";
    private static final String EMOJI_GUARDAR = "💾";
    private static final String EMOJI_BUSCAR = "🔍";
    private static final String EMOJI_CHECK = "✅";
    private static final String EMOJI_ERROR = "❌";
    private static final String EMOJI_WARNING = "⚠️";
    private static final String EMOJI_INFO = "ℹ️";
    private static final String EMOJI_STAR = "⭐";
    private static final String EMOJI_PUNTO = "•";
    private static final String EMOJI_FLECHA = "➜";
    public static final String PLAYA = "🏖";
    public static final String SOMBRILLA = "⛱";
    public static final String CARPA = "⛺";
    public static final String SOL = "☀";
    public static final String MAR = "🌊";
    public static final String PALMERA = "🌴";

    /**
     * Limpia la consola del IDE
     */
    public static void limpiarConsola() {
        try {
            String sistemaOperativo = System.getProperty("os.name").toLowerCase();

            if (sistemaOperativo.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Imprime línea divisoria simple
     */
    public static void imprimirLinea() {
        System.out.println(CARACTER_LINEA.repeat(ANCHO_LINEA));
    }

    /**
     * Imprime línea divisoria doble
     */
    public static void imprimirLineaDoble() {
        System.out.println(CARACTER_DOBLE.repeat(ANCHO_LINEA));
    }

    /**
     * Centra un texto en el ancho especificado
     *
     * @param texto El texto a centrar
     * @return El texto centrado con espacios
     */
    private static String centrarTexto(String texto) {
        int espacios = (ANCHO_LINEA - texto.length()) / 2;
        return " ".repeat(espacios) + texto;
    }

    /**
     * Imprime un texto centrado
     *
     * @param texto El texto a centrar e imprimir
     */
    public static void imprimirCentrado(String texto) {
        System.out.println(centrarTexto(texto));
    }

    /**
     * Imprime una opción de menú centrada
     *
     * @param numero Número de la opción
     * @param texto  Descripción de la opción
     */
    public static void imprimirOpcionCentrada(int numero, String texto) {
        String opcion = numero + ". " + texto;
        System.out.println(centrarTexto(opcion));
    }

    /**
     * Imprime un título centrado entre líneas dobles
     *
     * @param titulo El título a imprimir
     */
    public static void imprimirTitulo(String titulo) {
        imprimirLineaDoble();
        imprimirCentrado(titulo);
        imprimirLineaDoble();
    }

    /**
     * Imprime un subtítulo centrado entre líneas simples
     *
     * @param subtitulo El subtítulo a imprimir
     */
    public static void imprimirSubtitulo(String subtitulo) {
        imprimirLinea();
        imprimirCentrado(subtitulo);
        imprimirLinea();
    }

    /**
     * Imprime un menú centrado con opciones
     *
     * @param opciones Array con las opciones del menú
     */
    public static void imprimirMenuCentrado(String[] opciones) {
        for (int i = 0; i < opciones.length; i++) {
            imprimirOpcionCentrada(i + 1, opciones[i]);
        }
        imprimirOpcionCentrada(0, "Salir");
    }

    /**
     * Imprime un mensaje de error centrado y formateado
     *
     * @param mensaje El mensaje de error
     */
    public static void imprimirError(String mensaje) {
        imprimirLinea();
        imprimirCentrado("ERROR: " + mensaje);
        imprimirLinea();
    }

    /**
     * Imprime un mensaje de éxito centrado y formateado
     *
     * @param mensaje El mensaje de éxito
     */
    public static void imprimirExito(String mensaje) {
        imprimirLinea();
        imprimirCentrado("✓ " + mensaje);
        imprimirLinea();
    }

    /**
     * Imprime una lista de elementos centrados
     *
     * @param elementos Array con los elementos a imprimir
     */
    public static void imprimirListaCentrada(String[] elementos) {
        for (String elemento : elementos) {
            imprimirCentrado("• " + elemento);
        }
    }


    public static void imprimirEncabezadoCarpas() {

        System.out.println("Carpas disponibles");

        System.out.printf("%-10s %-15s %-15s %-8s%n",
                "ID", "Tipo de Carpa", "Precio", "N estacionamiento");
        imprimirLineaDoble();
    }

    public static void imprimirEncabezadoSombrillasYPlazas(String tipoServicio){

        System.out.println(tipoServicio + "disponibles");

        System.out.printf("%-10s %-10s%n",
                "ID", "Precio");
        imprimirLineaDoble();

    }
}
