package es.gmm.psp.virtualScape.util.consts;

public class ExceptionMSG {
    public static final String RESERVATION_NOT_FOUND = "Reserva no encontrada";
    public static final String ROOM_NOT_FOUND = "Habitación no encontrada";

    public static final String CAPACITY_EXCEEDED(int maxCapacity, int currentCapacity, int newCapacity) {
        return String.format("Capacidad excedida, capacidad máxima = %1$d, capacidad actual = %2$d, capacidad a añadir = %3$d", maxCapacity, currentCapacity, newCapacity);
    }
    public static final String DUPLICATED_DATABASE_FIELD(String fieldName, String fieldValue) {
        return String.format("Campo %1$s duplicado en la base datos con valor = %2$s", fieldName, fieldValue);
    }
    public static String INVALID_FIELD(String fieldName, String fieldValue, String expectedValue) {
        return String.format("Campo %1$s no válido con valor = %2$s, valor esperado = %3$s", fieldName, fieldValue, expectedValue);
    }
}
