package es.gmm.psp.virtualScape.repository;

import es.gmm.psp.virtualScape.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends MongoRepository<Room, Long> {
    boolean existsByName(String name);
    Room findByName(String name);
    List<Room> findByThemes(String theme);
}
