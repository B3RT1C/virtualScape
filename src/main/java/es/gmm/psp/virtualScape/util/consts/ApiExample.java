package es.gmm.psp.virtualScape.util.consts;

public class ApiExample {
    public static final String ROOM = "{\n" +
            "  \"nombre\": \"Sala de ejemplo\",\n" +
            "  \"capacidadMin\": 2,\n" +
            "  \"capacidadMax\": 6,\n" +
            "  \"tematicas\": [\n" +
            "    \"Terror\",\n" +
            "    \"Aventura\"\n" +
            "  ]\n" +
            "}";

    public static final String ROOM_LIST = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"nombre\": \"Sala de ejemplo\",\n" +
            "    \"capacidadMin\": 2,\n" +
            "    \"capacidadMax\": 6,\n" +
            "    \"tematicas\": [\n" +
            "      \"Terror\",\n" +
            "      \"Aventura\"\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"nombre\": \"Sala de ejemplo 2\",\n" +
            "    \"capacidadMin\": 2,\n" +
            "    \"capacidadMax\": 6,\n" +
            "    \"tematicas\": [\n" +
            "      \"Terror\",\n" +
            "      \"Aventura\"\n" +
            "    ]\n" +
            "  }\n" +
            "]";

    public static final String RESERVATION = "{\n" +
            "  \"id\": 1,\n" +
            "  \"nombreSala\": \"Sala de ejemplo\",\n" +
            "  \"fecha\": {\n" +
            "    \"dia\": 1,\n" +
            "    \"hora\": 12\n" +
            "  },\n" +
            "  \"contacto\": {\n" +
            "    \"titular\": \"Juan\",\n" +
            "    \"telefono\": \"123456789\"\n" +
            "  },\n" +
            "  \"jugadores\": 4\n" +
            "}";

    public static final String RESERVATION_LIST = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"nombreSala\": \"Sala de ejemplo\",\n" +
            "    \"fecha\": {\n" +
            "      \"dia\": 1,\n" +
            "      \"hora\": 12\n" +
            "    },\n" +
            "    \"contacto\": {\n" +
            "      \"titular\": \"Juan\",\n" +
            "      \"telefono\": \"123456789\"\n" +
            "    },\n" +
            "    \"jugadores\": 4\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"nombreSala\": \"Sala de ejemplo 2\",\n" +
            "    \"fecha\": {\n" +
            "      \"dia\": 1,\n" +
            "      \"hora\": 12\n" +
            "    },\n" +
            "    \"contacto\": {\n" +
            "      \"titular\": \"Juan\",\n" +
            "      \"telefono\": \"123456789\"\n" +
            "    },\n" +
            "    \"jugadores\": 4\n" +
            "  }\n" +
            "]";

    public static final String BY_DAY_DTO_LIST = "[\n" +
            "  {\n" +
            "    \"sala\": \"Sala de ejemplo\",\n" +
            "    \"dia\": 1,\n" +
            "    \"hora\": 12,\n" +
            "    \"jugadores\": 4,\n" +
            "    \"contacto\": {\n" +
            "      \"titular\": \"Juan\",\n" +
            "      \"telefono\": \"123456789\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"sala\": \"Sala de ejemplo 2\",\n" +
            "    \"dia\": 1,\n" +
            "    \"hora\": 12,\n" +
            "    \"jugadores\": 4,\n" +
            "    \"contacto\": {\n" +
            "      \"titular\": \"Juan\",\n" +
            "      \"telefono\": \"123456789\"\n" +
            "    }\n" +
            "  }\n" +
            "]";

    public static final String BY_THEME_DTO_LIST = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"nombre\": \"Sala de ejemplo\",\n" +
            "    \"capacidadMin\": 2,\n" +
            "    \"capacidadMax\": 6,\n" +
            "    \"tematicas\": [\n" +
            "      \"Terror\",\n" +
            "      \"Aventura\"\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"nombre\": \"Sala de ejemplo 2\",\n" +
            "    \"capacidadMin\": 2,\n" +
            "    \"capacidadMax\": 6,\n" +
            "    \"tematicas\": [\n" +
            "      \"Terror\",\n" +
            "      \"Aventura\"\n" +
            "    ]\n" +
            "  }\n" +
            "]";

    public static final String BY_MOST_BOOKED_DTO_LIST = "[\n" +
            "  {\n" +
            "    \"nombre\": \"Sala de ejemplo\",\n" +
            "    \"totalReservas\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"nombre\": \"Sala de ejemplo 2\",\n" +
            "    \"totalReservas\": 2\n" +
            "  }\n" +
            "]";

    public static final String RESPONSE_DATA_OK = "{\n" +
            "  \"exito\": true,\n" +
            "  \"mensaje\": \"Operación realizada con éxito\",\n" +
            "  \"idGenerado\": 1\n" +
            "}";

    public static final String RESPONSE_DATA_ERROR = "{\n" +
            "  \"exito\": false,\n" +
            "  \"mensaje\": \"Error en la operación\",\n" +
            "  \"idGenerado\": -1\n" +
            "}";
}
