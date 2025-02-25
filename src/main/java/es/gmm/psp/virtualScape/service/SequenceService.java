package es.gmm.psp.virtualScape.service;

import es.gmm.psp.virtualScape.model.Sequence;
import es.gmm.psp.virtualScape.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceService implements ISequenceService {

    private final SequenceRepository sequenceRepository;

    @Autowired
    public SequenceService(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public long getNextId(String sequenceName) {
        Sequence sequence = sequenceRepository.findOrCreate(sequenceName);
        long nextId = sequence.nextSeq();
        sequenceRepository.save(sequence);
        return nextId;
    }
}
