package com.videohost;

import java.time.LocalDate;
import java.time.LocalTime;

public class ViewInfoDTO {
    private String viewerFirstName;
    private String viewerLastName;
    private String viewerFatherly;
    private String producerFirstName;
    private String producerLastName;
    private String producerFatherly;
    private String videoTitle;
    private String videoGenre;
    private String producerCountry;
    private String platformName;
    private double videoRating;
    private LocalDate viewDate;
    private LocalTime viewTime;

    // Constructor
    public ViewInfoDTO(String viewerFirstName, String viewerLastName, String viewerFatherly, String producerFirstName, String producerLastName, String producerFatherly,
                    String videoTitle, String videoGenre, String producerCountry, String platformName,
                    double videoRating, LocalDate viewDate, LocalTime viewTime) {
        this.viewerFirstName = viewerFirstName;
        this.viewerLastName = viewerLastName;
        this.viewerFatherly = viewerFatherly;
        this.producerFirstName = producerFirstName;
        this.producerLastName = producerLastName;
        this.producerFatherly = producerFatherly;
        this.videoTitle = videoTitle;
        this.videoGenre = videoGenre;
        this.producerCountry = producerCountry;
        this.platformName = platformName;
        this.videoRating = videoRating;
        this.viewDate = viewDate;
        this.viewTime = viewTime;
    }

    // Getters and Setters
    public String getViewerFirstName() {
        return viewerFirstName;
    }

    public void setViewerFirstName(String viewerFirstName) {
        this.viewerFirstName = viewerFirstName;
    }

    public String getViewerLastName() {
        return viewerLastName;
    }

        public String getViewerFatherly() {
        return viewerFatherly;
    }

    public void setViewerLastName(String viewerLastName) {
        this.viewerLastName = viewerLastName;
    }

    public String getProducerFirstName() {
        return producerFirstName;
    }

public String getProducerFatherly() {
    return producerFatherly;
}

    public void setProducerFirstName(String producerFirstName) {
        this.producerFirstName = producerFirstName;
    }

    public String getProducerLastName() {
        return producerLastName;
    }

    public void setProducerLastName(String producerLastName) {
        this.producerLastName = producerLastName;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoGenre() {
        return videoGenre;
    }

    public void setVideoGenre(String videoGenre) {
        this.videoGenre = videoGenre;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public double getVideoRating() {
        return videoRating;
    }

    public void setVideoRating(double videoRating) {
        this.videoRating = videoRating;
    }

    public LocalDate getViewDate() {
        return viewDate;
    }

    public void setViewDate(LocalDate viewDate) {
        this.viewDate = viewDate;
    }

    public LocalTime getViewTime() {
        return viewTime;
    }

    public void setViewTime(LocalTime viewTime) {
        this.viewTime = viewTime;
    }
}
