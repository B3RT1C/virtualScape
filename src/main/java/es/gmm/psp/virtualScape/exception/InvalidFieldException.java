package es.gmm.psp.virtualScape.exception;

import es.gmm.psp.virtualScape.util.consts.ExceptionMSG;

public class InvalidFieldException extends VirtualScapeException {
    public InvalidFieldException(String fieldName, String fieldValue, String expectedValue) {
        super(ExceptionMSG.INVALID_FIELD(fieldName, fieldValue, expectedValue));
    }
}
