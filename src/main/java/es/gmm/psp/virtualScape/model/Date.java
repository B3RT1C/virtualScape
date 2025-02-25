package es.gmm.psp.virtualScape.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

public class Date {

    @Field("diaReserva")
    @JsonProperty("diaReserva")
    @Schema(description = "Día de la reserva", example = "1")
    int day;

    @Field("horaReserva")
    @JsonProperty("horaReserva")
    @Schema(description = "Hora de la reserva", example = "21")
    int hour;

    public Date() {}

    public Date(int day, int hour) {
        this.day = day;
        this.hour = hour;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day && hour == date.hour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, hour);
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", hour=" + hour +
                '}';
    }
}
