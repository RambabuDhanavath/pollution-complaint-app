package com.rambabu.pollutioncomplaint;

/**
 * Data model for a pollution complaint.
 *
 * A complaint captures the pollution type, a description, the GPS
 * location where it was observed, an optional photo, and its current
 * status as it moves through review.
 */
public class Complaint {

    /** Complaint categories supported by the app. */
    public enum Type {
        NOISE,          // sound / noise pollution (loudspeakers, construction)
        AIR,            // air pollution (smoke, dust, burning)
        WATER,          // water pollution
        FOOD_ADULTERATION
    }

    /** Lifecycle of a complaint. */
    public enum Status {
        SUBMITTED,
        IN_REVIEW,
        RESOLVED
    }

    private long id;
    private Type type;
    private String description;
    private double latitude;
    private double longitude;
    private String photoPath;
    private Status status;
    private long createdAt;

    public Complaint() {
        this.status = Status.SUBMITTED;
        this.createdAt = System.currentTimeMillis();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
