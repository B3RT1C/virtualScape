package es.gmm.psp.virtualScape.util.dataValidator;

import es.gmm.psp.virtualScape.util.consts.Const;
import es.gmm.psp.virtualScape.exception.InvalidFieldException;
import es.gmm.psp.virtualScape.model.Room;

public class RoomValidator {
    public static void validate(Room toValidate) {
        if (toValidate.getName() == null || toValidate.getName().isEmpty()) {
            throw new InvalidFieldException("nombre", toValidate.getName(), " no null o vacío");
        }
        if (toValidate.getMaxCapacity() <= 0 || toValidate.getMaxCapacity() > Const.MAX_TOTAL_PLAYERS) {
            throw new InvalidFieldException("capacidadMax", String.valueOf(toValidate.getMaxCapacity()), " 0 < capacidadMax <= " + Const.MAX_TOTAL_PLAYERS);
        }
        if (toValidate.getMinCapacity() <= 0) {
            throw new InvalidFieldException("capacidadMin", String.valueOf(toValidate.getMinCapacity()), " capacidadMin > 0");
        }
        if (toValidate.getMinCapacity() > toValidate.getMaxCapacity()) {
            throw new InvalidFieldException("capacidadMin y capacidadMax", "capacidadMin: " + toValidate.getMinCapacity() + " capacidadMax: " + toValidate.getMaxCapacity() , " capacidadMin <= capacidadMax");
        }
    }
}
