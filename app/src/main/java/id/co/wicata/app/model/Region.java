package id.co.wicata.app.model;

import com.google.gson.annotations.SerializedName;

public class Region {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("bannerUrl")
    private String bannerUrl;

    public Region(int id, String title, String bannerUrl) {
        this.id = id;
        this.title = title;
        this.bannerUrl = bannerUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}
