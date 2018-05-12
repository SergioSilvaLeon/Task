
package com.ssilva.task.data.models;

import java.util.List;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

@AutoValue
public abstract class VolumeInfo {

    @Nullable
    @SerializedName("title")
    public abstract String title();
    @Nullable
    @SerializedName("authors")
    public abstract List<String> authors();
    @Nullable
    @SerializedName("publishedDate")
    public abstract String publishedDate();
    @Nullable
    @SerializedName("description")
    public abstract String description();
    @Nullable
    @SerializedName("imageLinks")
    public abstract ImageLinks imageLinks();

    public static TypeAdapter<VolumeInfo> typeAdapter(Gson gson) {
        return new AutoValue_VolumeInfo.GsonTypeAdapter(gson);
    }

}
