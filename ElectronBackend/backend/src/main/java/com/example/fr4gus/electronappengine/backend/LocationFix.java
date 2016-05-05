package com.example.fr4gus.electronappengine.backend;

import java.util.Date;

/** The object model for the data we are sending through endpoints */
public class LocationFix {

    private Double latitude;
    private Double longitude;
    private Date createdAt;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}