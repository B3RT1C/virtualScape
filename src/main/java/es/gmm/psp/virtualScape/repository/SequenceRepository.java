package es.gmm.psp.virtualScape.repository;

import es.gmm.psp.virtualScape.model.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends MongoRepository<Sequence, String> {
    default Sequence findOrCreate(String name) {
        return findById(name).orElseGet(() -> save(new Sequence(name)));
    }
}
