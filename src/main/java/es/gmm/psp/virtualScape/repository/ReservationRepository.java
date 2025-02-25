package es.gmm.psp.virtualScape.repository;

import es.gmm.psp.virtualScape.model.Date;
import es.gmm.psp.virtualScape.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, Long> {
    boolean existsByDate(Date date);
    List<Reservation> findByDate(Date date);
    List<Reservation> findByRoomName(String roomName);
}
