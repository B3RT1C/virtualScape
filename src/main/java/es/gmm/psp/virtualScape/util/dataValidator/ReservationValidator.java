package es.gmm.psp.virtualScape.util.dataValidator;

import es.gmm.psp.virtualScape.exception.InvalidFieldException;
import es.gmm.psp.virtualScape.model.Reservation;

/**
 * Class ment to make basic validations on a Reservation object
 */
public class ReservationValidator {
    public static void validate(Reservation reservation) {
        if (reservation == null) {
            throw new InvalidFieldException("reserva", "null", "no null");
        }
        if (reservation.getRoomName() == null || reservation.getRoomName().isEmpty()) {
            throw new InvalidFieldException("nombreSala", reservation.getRoomName(), "no null o vacío");
        }
        if (reservation.getNumPlayers() < 0) {
            throw new InvalidFieldException("jugadores", String.valueOf(reservation.getNumPlayers()), "jugadores > 0");
        }

        DateValidator.validate(reservation.getDate());
        ContactValidator.validate(reservation.getContact());
    }
}
