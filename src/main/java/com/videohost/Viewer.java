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
@Table(name = "viewer")
public class Viewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viewer_id")
    private long viewerId;

    @OneToOne
    @JoinColumn(name = "viewer_name_id")
    private ViewerName viewerName;

    @OneToMany(mappedBy = "viewer")
    private List<ViewEvent> viewEvents;

    // Getters and Setters
    public long getViewerId() {
        return viewerId;
    }

    public void setViewerId(long viewerId) {
        this.viewerId = viewerId;
    }

    public ViewerName getViewerName() {
        return viewerName;
    }

    public void setViewerName(ViewerName viewerName) {
        this.viewerName = viewerName;
    }

    public List<ViewEvent> getViewEvents() {
        return viewEvents;
    }

    public void setViewEvents(List<ViewEvent> viewEvents) {
        this.viewEvents = viewEvents;
    }
}
