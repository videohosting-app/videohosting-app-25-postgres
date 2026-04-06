package com.videohost;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Колекція, в якій зберігається документ бази даних MongoDB,
 * що представляє сутність рядку таблиці відеохостингу.
 */
@Document(collection = "videohost")
public class ViewInfo {
    @Id
    private String id;
    private String viewer;
    private String producer;
    private String watchedDate;
    private String watchedTime;
    private String videoTitle;
    private String videoDuration;
    private String genre;
    private String producerCountry;
    private double videoRating;
    private String platform;

    public ViewInfo() {
    }

    public ViewInfo(String viewer, String producer, String watchedDate, String watchedTime,
                    String videoTitle, String videoDuration, String genre, String producerCountry,
                    double videoRating, String platform) {
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

    public String getId() {
        return id;
    }

    public String getViewer() {
        return viewer;
    }

    public void setViewer(String viewer) {
        this.viewer = viewer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getWatchedDate() {
        return watchedDate;
    }

    public void setWatchedDate(String watchedDate) {
        this.watchedDate = watchedDate;
    }

    public String getWatchedTime() {
        return watchedTime;
    }

    public void setWatchedTime(String watchedTime) {
        this.watchedTime = watchedTime;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    public double getVideoRating() {
        return videoRating;
    }

    public void setVideoRating(double videoRating) {
        this.videoRating = videoRating;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String toString() {
        return "ViewInfo {" +
                " id=\"" + id + "\"\n" +
                " viewerFirstName=\"" + viewer + "\"\n" +
                " producerFirstName=\"" + producer + "\"\n" +
                " watchedDate=\"" + watchedDate + "\"\n" +
                " watchedTime=\"" + watchedTime + "\"\n" +
                " videoTitle=\"" + videoTitle + "\"\n" +
                " videoDuration=\"" + videoDuration + "\"\n" +
                " videoGenre=\"" + genre + "\"\n" +
                " producerCountry=\"" + producerCountry + "\"\n" +
                " platformName=\"" + platform + "\"\n" +
                " videoRating=\"" + videoRating + "\"\n" +
                "}";
    }
}