package es.gmm.psp.virtualScape.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseData {

    @JsonProperty("exito")
    @Schema(description = "Indica si la operación ha sido exitosa", example = "true")
    private boolean successful;

    @JsonProperty("mensaje")
    @Schema(description = "Mensaje de la operación", example = "Operación realizada con éxito")
    private String message;

    @JsonProperty("idGenerado")
    @Schema(description = "Identificador generado al insertar en la base de datos", example = "1")
    private Long id;

    public ResponseData(boolean successful, String message, Long id) {
        this.successful = successful;
        this.message = message;
        this.id = id;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
