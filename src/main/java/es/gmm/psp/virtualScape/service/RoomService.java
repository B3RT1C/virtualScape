package es.gmm.psp.virtualScape.service;

import es.gmm.psp.virtualScape.dto.ByMostBookedDto;
import es.gmm.psp.virtualScape.dto.ByThemeDto;
import es.gmm.psp.virtualScape.exception.InvalidFieldException;
import es.gmm.psp.virtualScape.util.consts.Const;
import es.gmm.psp.virtualScape.exception.CapacityExceededException;
import es.gmm.psp.virtualScape.exception.DuplicatedDatabaseField;
import es.gmm.psp.virtualScape.exception.RoomNotFoundException;
import es.gmm.psp.virtualScape.model.Reservation;
import es.gmm.psp.virtualScape.model.Room;
import es.gmm.psp.virtualScape.repository.RoomRepository;
import es.gmm.psp.virtualScape.util.consts.ExceptionMSG;
import es.gmm.psp.virtualScape.util.dataValidator.RoomValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    Logger logger = LoggerFactory.getLogger(RoomService.class);

    private final RoomRepository roomRepository;
    private final ISequenceService sequenceService;
    private final IReservationService reservationService;

    @Autowired
    public RoomService(RoomRepository roomRepository, SequenceService sequenceService, @Lazy ReservationService reservationService) {
        this.roomRepository = roomRepository;
        this.sequenceService = sequenceService;
        this.reservationService = reservationService;
    }

    @Override
    public Room create(Room toCreate) {
        // if made to avoid errors made by reservations with a pre-existing id
        if (toCreate.getId() != null) {
            toCreate.setId(null);
        }
        if (roomRepository.existsByName(toCreate.getName())) {
            logger.error("Error al crear sala: " + ExceptionMSG.DUPLICATED_DATABASE_FIELD("nombre", toCreate.getName()));
            throw new DuplicatedDatabaseField("nombre", toCreate.getName());
        }
        int totalCurrentCapacity = findAll().stream().mapToInt(Room::getMaxCapacity).sum();
        if (totalCurrentCapacity + toCreate.getMaxCapacity() > Const.MAX_TOTAL_PLAYERS) {
            logger.error("Error al crear sala: " + ExceptionMSG.CAPACITY_EXCEEDED(Const.MAX_TOTAL_PLAYERS, totalCurrentCapacity, toCreate.getMaxCapacity()));
            throw new CapacityExceededException(Const.MAX_TOTAL_PLAYERS, totalCurrentCapacity, toCreate.getMaxCapacity());
        }

        try {
            RoomValidator.validate(toCreate);
        } catch (InvalidFieldException e) {
            logger.error("Error al crear sala: " + e.getMessage());
            throw e;
        }

        toCreate.setId(sequenceService.getNextId(Room.SEQUENCE_NAME));
        roomRepository.insert(toCreate);
        logger.info("Sala creada con éxito, id: " + toCreate.getId());
        return toCreate;
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room findByName(String name) {
        return roomRepository.findByName(name);
    }

    @Override
    public void update(Room toUpdate) {
        if (!roomRepository.existsById(toUpdate.getId())) {
            logger.error("Error al actualizar sala con id: " + toUpdate.getId() + "," + ExceptionMSG.ROOM_NOT_FOUND);
            throw new RoomNotFoundException();
        }
        Room colider = roomRepository.findByName(toUpdate.getName());
        if (colider != null && colider.getId() != toUpdate.getId()) {
            logger.error("Error al actualizar sala con id: " + toUpdate.getId() + "," + ExceptionMSG.DUPLICATED_DATABASE_FIELD("nombre", toUpdate.getName()));
            throw new DuplicatedDatabaseField("nombre", toUpdate.getName());
        }

        try {
            RoomValidator.validate(toUpdate);
        } catch (InvalidFieldException e) {
            logger.error("Error al actualizar sala con id: " + toUpdate.getId() + "," + e.getMessage());
            throw e;
        }

        // .save() crea el registro si no existe pero las especificaciones piden lo contrario
        roomRepository.save(toUpdate);
        logger.info("Sala actualizada con éxito, id: " + toUpdate.getId());
    }

    @Override
    public List<ByThemeDto> findByTheme(String theme) {
        List<Room> foundList = roomRepository.findByThemes(theme);
        List<ByThemeDto> foundData = new ArrayList<>();
        for (Room room : foundList) {
            foundData.add(new ByThemeDto(
                    room.getId(),
                    room.getName(),
                    room.getMinCapacity(),
                    room.getMaxCapacity(),
                    room.getThemes()
            ));
        }
        return foundData;
    }

    @Override
    public List<ByMostBookedDto> findMostBooked(int amountToReturn) {
        List<String> allRoomNames = roomRepository.findAll().stream().map(Room::getName).toList();
        List<Reservation> allReservations = reservationService.findAll();

        String[] roomNames = new String[amountToReturn];
        long[] roomBookings = new long[amountToReturn];
        Arrays.fill(roomBookings, -1);

        long timesBooked;
        for (int i = 0; i < allRoomNames.size(); i++) {
            int lambdaI = i;
            timesBooked = allReservations.stream().filter(r -> r.getRoomName().equals(allRoomNames.get(lambdaI))).count();

            for (int y = 0; y < amountToReturn; y++) {
                if (timesBooked > roomBookings[y]) {
                    roomBookings[y] = timesBooked;
                    roomNames[y] = allRoomNames.get(lambdaI);
                    break;
                }
            }
        }

        List<ByMostBookedDto> foundData = new ArrayList<>();
        for (int i = 0; i < amountToReturn; i++) {
            if (roomBookings[i] != -1) {
                foundData.add(new ByMostBookedDto(roomNames[i], roomBookings[i]));
            }
        }
        return foundData;
    }
}
