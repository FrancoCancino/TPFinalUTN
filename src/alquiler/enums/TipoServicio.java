package alquiler.enums;

public enum TipoServicio {
        CARPA,
        SOMBRILLA,
        PLAZA_ESTACIONAMIENTO;

        public static TipoServicio obtenerTipoPorId(String id) {
                if (id.startsWith("CP")) return CARPA;
                if (id.startsWith("SM")) return SOMBRILLA;
                if (id.startsWith("PE")) return PLAZA_ESTACIONAMIENTO;
                throw new IllegalArgumentException("ID de servicio no reconocido: " + id);
        }
}
