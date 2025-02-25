package es.gmm.psp.virtualScape.util.dataValidator;

import es.gmm.psp.virtualScape.exception.InvalidFieldException;
import es.gmm.psp.virtualScape.model.Contact;

/**
 * Class meant to make basic validations on a Contact object
 */
public class ContactValidator {
    public static void validate(Contact contact) {
        if (contact == null) {
            throw new InvalidFieldException("contacto", "null", "no null");
        }
        if (contact.getResponsibleName() == null || contact.getResponsibleName().isEmpty()) {
            throw new InvalidFieldException("nombre", contact.getResponsibleName(), "no null o vacío");
        }
        // PhoneNumber is a 9 digit number
        if (contact.getPhoneNumber() < 100000000 || contact.getPhoneNumber() > 999999999) {
            throw new InvalidFieldException("telefono", String.valueOf(contact.getPhoneNumber()), "número de 9 dígitos positivo");
        }
    }
}
