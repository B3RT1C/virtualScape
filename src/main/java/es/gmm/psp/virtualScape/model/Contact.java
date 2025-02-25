package es.gmm.psp.virtualScape.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.Field;

public class Contact {
    @Field("titular")
    @JsonProperty("titular")
    @Schema(description = "Nombre del titular", example = "Juan")
    String responsibleName;

    @Field("telefono")
    @JsonProperty("telefono")
    @Schema(description = "Número de teléfono", example = "123456789")
    int phoneNumber;

    public Contact() {}

    public Contact(String responsibleName, int phoneNumber) {
        this.responsibleName = responsibleName;
        this.phoneNumber = phoneNumber;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "responsibleName='" + responsibleName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
