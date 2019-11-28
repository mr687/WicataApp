package id.co.wicata.app.model;

import com.google.gson.annotations.SerializedName;

public class Destination {
    @SerializedName("id")
    int id;
    @SerializedName("imageUrl")
    String imageUrl;
    @SerializedName("title")
    String title;
    @SerializedName("locationName")
    String locationName;

    public Destination(int id, String imageUrl, String title, String locationName) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getLocationName() {
        return locationName;
    }
}
