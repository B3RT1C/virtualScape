package es.gmm.psp.virtualScape.exception;

import es.gmm.psp.virtualScape.util.consts.ExceptionMSG;

public class DuplicatedDatabaseField extends VirtualScapeException {
    public DuplicatedDatabaseField(String fieldName, String fieldValue) {
        super(ExceptionMSG.DUPLICATED_DATABASE_FIELD(fieldName, fieldValue));
    }
}
