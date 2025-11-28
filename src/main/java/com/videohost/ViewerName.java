package com.videohost;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "viewer_name")
public class ViewerName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viewer_name_id")
    private long viewerNameId;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "fatherly", length = 100)
    private String fatherly;

    @OneToOne(mappedBy = "viewerName")
    private Viewer viewer;

    // Getters and Setters
    public long getViewerNameId() {
        return viewerNameId;
    }

    public void setViewerNameId(long viewerNameId) {
        this.viewerNameId = viewerNameId;
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

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }
}
