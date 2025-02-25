package es.gmm.psp.virtualScape.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.gmm.psp.virtualScape.model.Contact;
import io.swagger.v3.oas.annotations.media.Schema;

public class ByDayDto {

    @JsonProperty("sala")
    @Schema(description = "Nombre de la sala", example = "Sala de ejemplo")
    private String roomName;

    @JsonProperty("dia")
    @Schema(description = "Día de la reserva", example = "1")
    private int day;

    @JsonProperty("hora")
    @Schema(description = "Hora de la reserva", example = "21")
    private int hour;

    @JsonProperty("contacto")
    private Contact contact;

    @JsonProperty("jugadores")
    @Schema(description = "Número de jugadores", example = "4")
    private int numPlayers;

    public ByDayDto(String roomName, int day, int hour, Contact contact, int numPlayers) {
        this.roomName = roomName;
        this.day = day;
        this.hour = hour;
        this.contact = contact;
        this.numPlayers = numPlayers;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
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
        return "{\n" +
                "\"sala\": \"" + roomName + "\",\n" +
                "\"dia\": " + day + ",\n" +
                "\"hora\": " + hour + ",\n" +
                "\"jugadores\": " + numPlayers + ",\n" +
                "\"contacto\": {\n" +
                "       \"titular\": \"" + contact.getResponsibleName() + "\",\n" +
                "       \"telefono\": \"" + contact.getPhoneNumber() + "\"\n" +
                "}";
    }
}
