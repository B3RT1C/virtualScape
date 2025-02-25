package es.gmm.psp.virtualScape.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("salas")
public class Room {

    @Transient
    public static final String SEQUENCE_NAME = "salas_secuencia";

    @Id
    @Schema(description = "Identificador de la sala", example = "1")
    Long id;

    @Indexed(unique = true)
    @Field("nombre")
    @JsonProperty("nombre")
    @Schema(description = "Nombre de la sala", example = "Sala de ejemplo")
    String name;

    @Field("capacidadMin")
    @JsonProperty("capacidadMin")
    @Schema(description = "Capacidad mínima de la sala", example = "2")
    int minCapacity;

    @Field("capacidadMax")
    @JsonProperty("capacidadMax")
    @Schema(description = "Capacidad máxima de la sala", example = "6")
    int maxCapacity;

    @Field("tematicas")
    @JsonProperty("tematicas")
    @Schema(description = "Temáticas de la sala", example = "[\"Terror\", \"Aventura\"]")
    List<String> themes;

    public Room() {}

    public Room(String name, int minCapacity, int maxCapacity, List<String> themes) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minCapacity=" + minCapacity +
                ", maxCapacity=" + maxCapacity +
                ", themes=" + themes +
                '}';
    }
}
