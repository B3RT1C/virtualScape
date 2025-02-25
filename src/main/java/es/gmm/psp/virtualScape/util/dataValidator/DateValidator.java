package es.gmm.psp.virtualScape.util.dataValidator;

import es.gmm.psp.virtualScape.exception.InvalidFieldException;
import es.gmm.psp.virtualScape.model.Date;

/**
 * Class meant to make basic validations on Date objects
 */
public class DateValidator {
    public static void validate(Date date) {
        if (date == null) {
            throw new InvalidFieldException("fecha", "null", "not null");
        }
        if (date.getDay() < 1 || date.getDay() > 31) {
            throw new InvalidFieldException("dia", String.valueOf(date.getDay()), "1 .. 31");
        }
        if (date.getHour() < 0 || date.getHour() > 23) {
            throw new InvalidFieldException("hora", String.valueOf(date.getHour()), "0 .. 23");
        }
    }
}
