package es.gmm.psp.virtualScape.service;

import es.gmm.psp.virtualScape.dto.ByDayDto;
import es.gmm.psp.virtualScape.exception.DuplicatedDatabaseField;
import es.gmm.psp.virtualScape.exception.InvalidFieldException;
import es.gmm.psp.virtualScape.exception.ReservationNotFoundException;
import es.gmm.psp.virtualScape.exception.RoomNotFoundException;
import es.gmm.psp.virtualScape.model.Date;
import es.gmm.psp.virtualScape.model.Reservation;
import es.gmm.psp.virtualScape.model.Room;
import es.gmm.psp.virtualScape.repository.ReservationRepository;
import es.gmm.psp.virtualScape.util.consts.ExceptionMSG;
import es.gmm.psp.virtualScape.util.dataValidator.ReservationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private Logger logger = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationRepository reservationRepository;
    private final ISequenceService sequenceService;
    private final IRoomService roomService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, SequenceService sequenceService, @Lazy RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.sequenceService = sequenceService;
        this.roomService = roomService;
    }

    @Override
    public Reservation create(Reservation toCreate) {
        // if made to avoid errors made by reservations with a pre-existing id
        if (toCreate.getId() != null) {
            toCreate.setId(null);
        }
        Room toReserve = roomService.findByName(toCreate.getRoomName());
        if (toReserve == null) {
            logger.error("Error al crear reserva: " + ExceptionMSG.ROOM_NOT_FOUND);
            throw new RoomNotFoundException();
        }
        List<Reservation> coliders = reservationRepository.findByDate(toCreate.getDate());
        if (!coliders.isEmpty()
                && coliders
                .stream().anyMatch(r ->
                        r.getRoomName().equals(toCreate.getRoomName()) && r.getId() != toCreate.getId()
                )
        ) {
            logger.error("Error al crear reserva: " + ExceptionMSG.DUPLICATED_DATABASE_FIELD("fecha", "dia: " + toCreate.getDate().getDay() + " hora: " + toCreate.getDate().getHour()));
            throw new DuplicatedDatabaseField("fecha", " dia: " + toCreate.getDate().getDay() + " hora: " + toCreate.getDate().getHour());
        }
        if (toCreate.getNumPlayers() < toReserve.getMinCapacity() || toCreate.getNumPlayers() > toReserve.getMaxCapacity()) {
            logger.error("Error al crear reserva: " + ExceptionMSG.INVALID_FIELD("jugadores", String.valueOf(toCreate.getNumPlayers()), "min: " + toReserve.getMinCapacity() + " max: " + toReserve.getMaxCapacity()));
            throw new InvalidFieldException("jugadores", String.valueOf(toCreate.getNumPlayers()), "min: " + toReserve.getMinCapacity() + " max: " + toReserve.getMaxCapacity());
        }

        try {
            ReservationValidator.validate(toCreate);
        } catch (InvalidFieldException e) {
            logger.error("Error al crear reserva: " + e.getMessage());
            throw e;
        }

        toCreate.setId(sequenceService.getNextId(Reservation.SEQUENCE_NAME));
        reservationRepository.insert(toCreate);
        logger.info("Reserva creada con éxito, id: " + toCreate.getId());
        return toCreate;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Reservation toUpdate) {
        if (!reservationRepository.existsById(toUpdate.getId())) {
            logger.error("Error al actualizar reserva con id: " + toUpdate.getId() + "," + ExceptionMSG.RESERVATION_NOT_FOUND);
            throw new ReservationNotFoundException();
        }
        List<Reservation> coliders = reservationRepository.findByDate(toUpdate.getDate());
        if (!coliders.isEmpty()
                && coliders.stream().anyMatch(r -> r.getRoomName().equals(toUpdate.getRoomName()) && r.getId() != toUpdate.getId())) {
            logger.error("Error al actualizar reserva con id: " + toUpdate.getId() + "," + ExceptionMSG.DUPLICATED_DATABASE_FIELD("fecha", "dia: " + toUpdate.getDate().getDay() + " hora: " + toUpdate.getDate().getHour()));
            throw new DuplicatedDatabaseField("fecha", "dia: " + toUpdate.getDate().getDay() + " hora: " + toUpdate.getDate().getHour());
        }

        try {
            ReservationValidator.validate(toUpdate);
        } catch (InvalidFieldException e) {
            logger.error("Error al actualizar reserva con id: " + toUpdate.getId() + "," + e.getMessage());
            throw e;
        }

        // .save() crea el registro si no existe pero las especificaciones piden lo contrario
        reservationRepository.save(toUpdate);
        logger.info("Reserva actualizada con éxito, id: " + toUpdate.getId());
    }

    @Override
    public void delete(Reservation toDelete) {
        if (!reservationRepository.existsById(toDelete.getId())) {
            logger.error("Error al eliminar reserva con id: " + toDelete.getId() + "," + ExceptionMSG.RESERVATION_NOT_FOUND);
            throw new ReservationNotFoundException();
        }
        reservationRepository.delete(toDelete);
        logger.info("Reserva eliminada con éxito, id: " + toDelete.getId());
    }

    @Override
    public List<ByDayDto> findByDay(int day) {
        if (day < 0 || day > 31) {
            logger.error("Error al buscar reservas por día: " + ExceptionMSG.INVALID_FIELD("dia", String.valueOf(day), "1 .. 31"));
            throw new InvalidFieldException("dia", String.valueOf(day), "1 .. 31");
        }

        List<Reservation> foundList = new ArrayList<>();
        for (int hour = 0; hour < 23; hour++) {
            foundList.addAll(reservationRepository.findByDate(new Date(day, hour)));
        }

        List<ByDayDto> foundData = new ArrayList<>();
        for (Reservation reservation : foundList) {
            foundData.add(new ByDayDto(
                    reservation.getRoomName(),
                    reservation.getDate().getDay(),
                    reservation.getDate().getHour(),
                    reservation.getContact(),
                    reservation.getNumPlayers()
            ));
        }
        return foundData;
    }
}
