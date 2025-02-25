package es.gmm.psp.virtualScape.controller;

import es.gmm.psp.virtualScape.dto.ByDayDto;
import es.gmm.psp.virtualScape.exception.DuplicatedDatabaseField;
import es.gmm.psp.virtualScape.exception.InvalidFieldException;
import es.gmm.psp.virtualScape.exception.ReservationNotFoundException;
import es.gmm.psp.virtualScape.model.ResponseData;
import es.gmm.psp.virtualScape.model.Reservation;
import es.gmm.psp.virtualScape.service.IReservationService;
import es.gmm.psp.virtualScape.util.consts.ApiMSG;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController implements IReservationController {

    @Autowired
    private IReservationService reservationService;

    @Override
    public ResponseEntity<List<Reservation>> getReservation() {
        List<Reservation> reservations = reservationService.findAll();

        if (reservations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = reservationService.findById(id);
        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseData> postReservation(@RequestBody Reservation toPost) {
        try {
            Long createdId = reservationService.create(toPost).getId();
            return new ResponseEntity<>(new ResponseData(true, ApiMSG.RESERVATION_CREATED_SUCCESSFUL, createdId), HttpStatus.CREATED);

        } catch (DuplicatedDatabaseField e) {
            return new ResponseEntity<>(new ResponseData(false, e.getMessage(), -1L), HttpStatus.CONFLICT);
        } catch (InvalidFieldException e) {
            return new ResponseEntity<>(new ResponseData(false, e.getMessage(), -1L), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseData> putReservation(@PathVariable Long id, @RequestBody Reservation toPut) {
        try {
            toPut.setId(id);
            reservationService.update(toPut);
            return new ResponseEntity<>(new ResponseData(true, ApiMSG.RESERVATION_UPDATED_SUCCESSFUL, id), HttpStatus.OK);

        } catch (DuplicatedDatabaseField e) {
            return new ResponseEntity<>(new ResponseData(false, e.getMessage(), -1L), HttpStatus.CONFLICT);
        } catch (InvalidFieldException e) {
            return new ResponseEntity<>(new ResponseData(false, e.getMessage(), -1L), HttpStatus.BAD_REQUEST);
        } catch (ReservationNotFoundException e) {
            return new ResponseEntity<>(new ResponseData(false, e.getMessage(), -1L), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseData> deleteReservation(@PathVariable Long id) {
        try {
            Reservation toDelete = reservationService.findById(id);
            if (toDelete == null) {
                throw new ReservationNotFoundException();
            }
            reservationService.delete(toDelete);
            return new ResponseEntity<>(new ResponseData(true, ApiMSG.RESERVATION_DELETED_SUCCESSFUL, id), HttpStatus.OK);

        } catch (ReservationNotFoundException e) {
            return new ResponseEntity<>(new ResponseData(false, e.getMessage(), -1L), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ByDayDto>> getReservationByDay(@PathVariable Integer numDia) {
        List<ByDayDto> reservations = reservationService.findByDay(numDia);

        if (reservations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
