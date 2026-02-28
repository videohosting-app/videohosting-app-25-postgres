package com.videohost;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
Колекція, в якій зберігатися документ баз даних MongoDB, що представляє сутність рядку з таблиці з розкладом коледжу.
*/
@Document(collection = "videohost")
public class ViewInfo {
    @Id
    private String id;
    private String viewer;
    private String producer;
    private LocalDate watchedDate;
    private LocalTime watchedTime;
    private String videoTitle;
    private String videoDuration;
    private String genre;
    private String producerCountry;
    private double videoRating;
    private String platform;
    
public ViewInfo() {}

public ViewInfo(String viewer, String producer, LocalDate watchedDate, LocalTime watchedTime, String videoTitle,
                String videoDuration, String genre, String producerCountry,double videoRating, String platform
                    ) {
        this.viewer = viewer;
        this.producer = producer;
        this.watchedDate = watchedDate;
        this.watchedTime = watchedTime;
        this.videoTitle = videoTitle;
        this.videoDuration = videoDuration;
        this.genre = genre;
        this.producerCountry = producerCountry;
        this.videoRating = videoRating;
        this.platform = platform;
    }



    public ViewInfo(String viewer2, String producer2, String string, LocalDate localDate, LocalTime localTime,
        String videoDuration2, String genre2, String producerCountry2, double double1, String platform2) {
    //TODO Auto-generated constructor stub
}



    public String toString() {
        return "ViewInfo {" +
                " id=\"" + id + "\"\n" +
                " viewerFirstName=\"" + viewer + "\"\n" +
                " producerFirstName=\"" + producer + "\"\n" +
                " watchedDate=\"" + watchedDate + "\"\n" +
                " watchedTime=\"" + watchedTime + "\"\n" +
                " videoTitle=\"" + videoTitle + "\"\n" +
                "videoDuration=\"" + videoDuration +"\"n" +
                " videoGenre=\"" + genre + "\"\n" +
                " producerCountry=\"" + producerCountry + "\"\n" +
                " platformName=\"" + platform + "\"\n" +
                " videoRating=\"" + videoRating + "\"\n" +
                                "}";
    }
}