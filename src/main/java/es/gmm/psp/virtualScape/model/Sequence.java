package es.gmm.psp.virtualScape.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Class that allows the automatic generation of numeric ids
 */
@Document(collection = "secuencias")
public class Sequence {
    @Id
    private String name;
    @Field("secuencia")
    private long seq;

    public Sequence(String name) {
        this.name = name;
        this.seq = 0;
    }

    public String getId() {
        return name;
    }

    /**
     * Increments the sequence and returns the new value
     * @return the new value of the sequence
     */
    public long nextSeq() {
        return ++seq;
    }
}
