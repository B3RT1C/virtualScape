package es.gmm.psp.virtualScape.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class ByMostBookedDto {

    @JsonProperty("nombre")
    @Schema(description = "Nombre de la sala", example = "Sala de ejemplo")
    private String roomName;

    @JsonProperty("totalReservas")
    @Schema(description = "Número total de reservas", example = "4")
    private long roomBookings;

    public ByMostBookedDto(String roomName, long roomBookings) {
        this.roomName = roomName;
        this.roomBookings = roomBookings;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"sala\": \"" + roomName + "\",\n" +
                "\"totalReservas\": " + roomBookings + "\n" +
                "}";
    }
}
