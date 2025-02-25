package es.gmm.psp.virtualScape.service;

import es.gmm.psp.virtualScape.dto.ByDayDto;
import es.gmm.psp.virtualScape.model.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation create(Reservation toCreate);
    List<Reservation> findAll();
    Reservation findById(long id);
    void update(Reservation toUpdate);
    void delete(Reservation toDelete);
    List<ByDayDto> findByDay(int day);
}
