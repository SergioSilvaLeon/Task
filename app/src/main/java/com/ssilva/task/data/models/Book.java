
package com.ssilva.task.data.models;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;
    @SerializedName("id")
    private String id;


    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
