package es.gmm.psp.virtualScape.exception;

import es.gmm.psp.virtualScape.util.consts.ExceptionMSG;

public class ReservationNotFoundException extends VirtualScapeException {
    public ReservationNotFoundException() {
        super(ExceptionMSG.RESERVATION_NOT_FOUND);
    }
}
