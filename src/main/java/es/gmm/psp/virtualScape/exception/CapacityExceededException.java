package es.gmm.psp.virtualScape.exception;

import es.gmm.psp.virtualScape.util.consts.ExceptionMSG;

public class CapacityExceededException extends VirtualScapeException {
    public CapacityExceededException(int maxCapacity, int currentCapacity, int newCapacity) {
        super(ExceptionMSG.CAPACITY_EXCEEDED(maxCapacity, currentCapacity, newCapacity));
    }
}
