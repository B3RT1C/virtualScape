package es.gmm.psp.virtualScape.exception;

import es.gmm.psp.virtualScape.util.consts.ExceptionMSG;

public class RoomNotFoundException extends VirtualScapeException {
    public RoomNotFoundException() {
        super(ExceptionMSG.ROOM_NOT_FOUND);
    }
}
