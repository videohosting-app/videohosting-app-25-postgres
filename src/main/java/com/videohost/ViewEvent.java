package com.videohost;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "view_event")
public class ViewEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_id")
    private long viewId;

    @ManyToOne
    @JoinColumn(name = "viewer_id")
    private Viewer viewer;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "view_date")
    private LocalDate viewDate;

    @Column(name = "view_time")
    private LocalTime viewTime;

    // Getters and Setters
    public long getViewId() {
        return viewId;
    }

    public void setViewId(long viewId) {
        this.viewId = viewId;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
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
