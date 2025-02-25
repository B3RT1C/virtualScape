package es.gmm.psp.virtualScape.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ByThemeDto {

    @Schema(description = "Identificador de la sala", example = "1")
    private Long id;

    @JsonProperty("nombre")
    @Schema(description = "Nombre de la sala", example = "Sala de ejemplo")
    private String roomName;

    @JsonProperty("capacidadMin")
    @Schema(description = "Capacidad mínima de la sala", example = "2")
    private int minCapacity;

    @JsonProperty("capacidadMax")
    @Schema(description = "Capacidad máxima de la sala", example = "6")
    private int maxCapacity;

    @JsonProperty("tematicas")
    @Schema(description = "Temáticas de la sala", example = "[\"Terror\", \"Aventura\"]")
    private List<String> themes;

    public ByThemeDto(Long id, String roomName, int minCapacity, int maxCapacity, List<String> themes) {
        this.id = id;
        this.roomName = roomName;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.themes = themes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"id\": " + id + ",\n" +
                "\"nombre\": \"" + roomName + "\",\n" +
                "\"capacidadMin\": " + minCapacity + ",\n" +
                "\"capacidadMax\": " + maxCapacity + ",\n" +
                "\"tematicas\": \"" + themes + "\"\n" +
                "}";
    }
}
