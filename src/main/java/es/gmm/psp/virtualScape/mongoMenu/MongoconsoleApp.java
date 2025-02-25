package es.gmm.psp.virtualScape.mongoMenu;

import es.gmm.psp.virtualScape.model.Reservation;
import es.gmm.psp.virtualScape.model.Room;
import es.gmm.psp.virtualScape.service.IReservationService;
import es.gmm.psp.virtualScape.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MongoconsoleApp {

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private IRoomService roomService;

    private Scanner in = new Scanner(System.in);
    private EntityAsk entityAsk = new EntityAsk();

    public void start() {
        int option = 0;
        do {
            MenuManager.mainMenu();
            try {
                option = Integer.parseInt(in.nextLine());

                switch (option) {
                    case 1:
                        reservations();
                        break;
                    case 2:
                        rooms();
                        break;
                    case 3:
                        specialQueries();
                        break;
                    case 4:
                        in.close();
                        System.out.println("Saliendo del menú...");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.err.println("Formato de número incorrecto");
            } catch (Exception e) {
                // Managed by Logger
            }
        } while (option != 4);
    }

    public void reservations() {
        MenuManager.reservationsMenu();

        int option = Integer.parseInt(in.nextLine());
        switch (option) {
            case 1:
                createReservation();
                break;
            case 2:
                findAllReservations();
                break;
            case 3:
                findReservationById();
                break;
            case 4:
                updateReservation();
                break;
            case 5:
                deleteReservation();
                break;
        }
    }

    public void createReservation() {
        Reservation toCreate = entityAsk.askReservation(in);
        reservationService.create(toCreate);
        System.out.println("Reserva creada");
    }

    public void findAllReservations() {
        reservationService.findAll().forEach(System.out::println);
    }

    public void findReservationById() {
        int id = entityAsk.askId(in);
        System.out.println(reservationService.findById(id));
    }

    public void updateReservation() {
        int idToUpdate = entityAsk.askId(in);
        Reservation toUpdate = reservationService.findById(idToUpdate);

        int option = 0;
        do {
            MenuManager.reservationUpdateMenu();
            option = Integer.parseInt(in.nextLine());

            switch (option) {
                case 1:
                    toUpdate.setRoomName(entityAsk.askRoomName(in));
                    break;
                case 2:
                    toUpdate.setDate(entityAsk.askDate(in));
                    break;
                case 3:
                    toUpdate.setContact(entityAsk.askContact(in));
                    break;
                case 4:
                    toUpdate.setNumPlayers(entityAsk.askNumPlayers(in));
                    break;
                case 5:
                    reservationService.update(toUpdate);
                    break;
            }
        } while (option == 1 || option == 2 || option == 3 || option == 4);


        System.out.println("Reserva actualizada");
    }

    public void deleteReservation() {
        int idToDelete = entityAsk.askId(in);
        Reservation toDelete = reservationService.findById(idToDelete);
        reservationService.delete(toDelete);
        System.out.println("Reserva eliminada");
    }

    public void rooms() {
        MenuManager.roomsMenu();

        int option = Integer.parseInt(in.nextLine());
        switch (option) {
            case 1:
                createRoom();
                break;
            case 2:
                findAllRooms();
                break;
            case 3:
                findRoomById();
                break;
            case 4:
                updateRoom();
                break;
        }
    }

    public void createRoom() {
        Room toCreate = entityAsk.askRoom(in);
        roomService.create(toCreate);
        System.out.println("Sala creada");
    }

    public void findAllRooms() {
        roomService.findAll().forEach(System.out::println);
    }

    public void findRoomById() {
        int id = entityAsk.askId(in);
        System.out.println(roomService.findById(id));
    }

    public void updateRoom() {
        int idToUpdate = entityAsk.askId(in);
        Room toUpdate = roomService.findById(idToUpdate);

        int option = 0;
        do {
            MenuManager.roomUpdateMenu();
            option = Integer.parseInt(in.nextLine());

            switch (option) {
                case 1:
                    toUpdate.setName(entityAsk.askRoomName(in));
                    break;
                case 2:
                    toUpdate.setMinCapacity(entityAsk.askMinCapacity(in));
                    break;
                case 3:
                    toUpdate.setMaxCapacity(entityAsk.askMaxCapacity(in));
                    break;
                case 4:
                    toUpdate.setThemes(entityAsk.askThemes(in));
                    break;
                case 5:
                    roomService.update(toUpdate);
                    break;
            }
        } while (option == 1 || option == 2 || option == 3 || option == 4);

        System.out.println("Sala actualizada");
    }

    public void specialQueries() {
        MenuManager.specialQueriesMenu();
        int option = Integer.parseInt(in.nextLine());

        switch (option) {
            case 1:
                reservationService.findByDay(entityAsk.askDay(in)).forEach(System.out::println);
                break;
            case 2:
                roomService.findByTheme(entityAsk.askTheme(in)).forEach(System.out::println);
                break;
            case 3:
                roomService.findMostBooked(entityAsk.askAmountToReturn(in)).forEach(System.out::println);
                break;
        }
    }
}
