package com.videohost;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producer_name")
public class ProducerName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_name_id")
    private long producerNameId;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "fatherly", length = 100)
    private String fatherly;

    @OneToOne(mappedBy = "producerName")
    private Producer producer;

    // Getters and Setters
    public long getProducerNameId() {
        return producerNameId;
    }

    public void setProducerNameId(long producerNameId) {
        this.producerNameId = producerNameId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherly() {
        return fatherly;
    }

    public void setFatherly(String fatherly) {
        this.fatherly = fatherly;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
