package com.videohost;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producer")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_id")
    private long producerId;

    @OneToOne
    @JoinColumn(name = "producer_name_id")
    private ProducerName producerName;

    @Column(name = "country", length = 100)
    private String country;

    @OneToMany(mappedBy = "producer")
    private List<Video> videos;

    // Getters and Setters
    public long getProducerId() {
        return producerId;
    }

    public void setProducerId(long producerId) {
        this.producerId = producerId;
    }

    public ProducerName getProducerName() {
        return producerName;
    }

    public void setProducerName(ProducerName producerName) {
        this.producerName = producerName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
