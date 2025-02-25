package es.gmm.psp.virtualScape.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.annotation.processing.Generated;

@Document(collection = "reservas")
public class Reservation {

    @Transient
    public static final String SEQUENCE_NAME = "reservas_secuencia";

    @Id
    @Schema(description = "Identificador de la reserva", example = "1")
    Long id;

    @Field("nombreSala")
    @JsonProperty("nombreSala")
    @Schema(description = "Nombre de la sala", example = "Sala de ejemplo")
    String roomName;

    @Field("fecha")
    @JsonProperty("fecha")
    Date date;

    @Field("contacto")
    @JsonProperty("contacto")
    Contact contact;

    @Field("jugadores")
    @JsonProperty("jugadores")
    @Schema(description = "Número de jugadores", example = "4")
    int numPlayers;

    public Reservation() {}

    public Reservation(String roomName, Date date, Contact contact, int numPlayers) {
        this.roomName = roomName;
        this.date = date;
        this.contact = contact;
        this.numPlayers = numPlayers;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", date=" + date +
                ", contact=" + contact +
                ", numPlayers=" + numPlayers +
                '}';
    }
}
