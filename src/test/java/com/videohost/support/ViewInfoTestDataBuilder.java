package com.videohost.support;

import com.videohost.ViewInfo;

public final class ViewInfoTestDataBuilder {
    private String viewer = "Коваленко Олег Іванович";
    private String producer = "Петренко Ірина Василівна";
    private String watchedDate = "2024-09-14";
    private String watchedTime = "20:30:00";
    private String videoTitle = "Весняний ранок";
    private String videoDuration = "00:15:45";
    private String genre = "Драма";
    private String producerCountry = "Україна";
    private double videoRating = 8.7;
    private String platform = "VideoHub";

    public static ViewInfoTestDataBuilder aViewInfo() {
        return new ViewInfoTestDataBuilder();
    }

    public ViewInfoTestDataBuilder withViewer(String value) {
        this.viewer = value;
        return this;
    }

    public ViewInfoTestDataBuilder withProducer(String value) {
        this.producer = value;
        return this;
    }

    public ViewInfoTestDataBuilder withWatchedDate(String value) {
        this.watchedDate = value;
        return this;
    }

    public ViewInfoTestDataBuilder withWatchedTime(String value) {
        this.watchedTime = value;
        return this;
    }

    public ViewInfoTestDataBuilder withVideoTitle(String value) {
        this.videoTitle = value;
        return this;
    }

    public ViewInfoTestDataBuilder withVideoDuration(String value) {
        this.videoDuration = value;
        return this;
    }

    public ViewInfoTestDataBuilder withGenre(String value) {
        this.genre = value;
        return this;
    }

    public ViewInfoTestDataBuilder withProducerCountry(String value) {
        this.producerCountry = value;
        return this;
    }

    public ViewInfoTestDataBuilder withVideoRating(double value) {
        this.videoRating = value;
        return this;
    }

    public ViewInfoTestDataBuilder withPlatform(String value) {
        this.platform = value;
        return this;
    }

    public ViewInfo build() {
        return new ViewInfo(viewer, producer, watchedDate, watchedTime,
            videoTitle, videoDuration, genre, producerCountry, videoRating, platform);
    }
}