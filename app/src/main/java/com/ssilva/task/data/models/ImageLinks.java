
package com.ssilva.task.data.models;

import com.google.gson.annotations.SerializedName;

public class ImageLinks {

    @SerializedName("smallThumbnail")
    private String smallThumbnail;
    @SerializedName("large")
    private String large;

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

}
