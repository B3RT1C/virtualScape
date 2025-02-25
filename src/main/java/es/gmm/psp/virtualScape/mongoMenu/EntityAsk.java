package es.gmm.psp.virtualScape.mongoMenu;

import es.gmm.psp.virtualScape.model.Contact;
import es.gmm.psp.virtualScape.model.Date;
import es.gmm.psp.virtualScape.model.Reservation;
import es.gmm.psp.virtualScape.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntityAsk {
    public Reservation askReservation(Scanner in) {
        String roomName = askRoomName(in);
        Date date = askDate(in);
        Contact contact = askContact(in);
        int players = askNumPlayers(in);

        return new Reservation(roomName, date, contact, players);
    }

    public Room askRoom(Scanner in) {
        String roomName = askRoomName(in);
        int minCapacity = askMinCapacity(in);
        int maxCapacity = askMaxCapacity(in);
        List<String> themes = askThemes(in);

        return new Room(roomName, minCapacity, maxCapacity, themes);
    }

    public int askId(Scanner in) {
        System.out.println("Introduce el id");
        return Integer.parseInt(in.nextLine());
    }

    public String askRoomName(Scanner in) {
        System.out.println("Introduce el nombre de la sala");
        return in.nextLine();
    }

    public Date askDate(Scanner in) {
        return new Date(askDay(in), askHour(in));
    }

    public int askDay(Scanner in) {
        System.out.println("Introduce el día (1 .. 31)");
        return Integer.parseInt(in.nextLine());
    }

    public int askHour(Scanner in) {
        System.out.println("Introduce la hora (0 .. 23)");
        return Integer.parseInt(in.nextLine());
    }

    public Contact askContact(Scanner in) {
        return new Contact(askResponsibleName(in), askPhoneNumber(in));
    }

    public String askResponsibleName(Scanner in) {
        System.out.println("Introduce el titular");
        return in.nextLine();
    }

    public int askPhoneNumber(Scanner in) {
        System.out.println("Introduce el número de teléfono (número de 9 cifras -> 123456789)");
        return Integer.parseInt(in.nextLine());
    }

    public int askNumPlayers(Scanner in) {
        System.out.println("Introduce el número de jugadores");
        return Integer.parseInt(in.nextLine());
    }

    public int askMinCapacity(Scanner in) {
        System.out.println("Introduce la capacidad mínima de la sala");
        return Integer.parseInt(in.nextLine());
    }

    public int askMaxCapacity(Scanner in) {
        System.out.println("Introduce la capacidad máxima de la sala");
        return Integer.parseInt(in.nextLine());
    }

    public String askTheme(Scanner in) {
        System.out.println("Introduce la temática");
        return in.nextLine();
    }

    public List<String> askThemes(Scanner in) {
        System.out.println("Dime cuantas temáticas va a tener la sala");
        int numThemes = Integer.parseInt(in.nextLine());
        List<String> themes = new ArrayList<>();
        for (int i = 1; i <= numThemes; i++) {
            System.out.println("Introduce la temática " + i);
            themes.add(in.nextLine());
        }
        return themes;
    }

    public int askAmountToReturn(Scanner in) {
        System.out.println("¿Cuantos registros quieres recuperar?");
        return Integer.parseInt(in.nextLine());
    }
}
