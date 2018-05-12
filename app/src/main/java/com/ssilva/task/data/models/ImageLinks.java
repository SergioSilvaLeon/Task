
package com.ssilva.task.data.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

@AutoValue
public abstract class ImageLinks {

    @Nullable
    @SerializedName("smallThumbnail")
    public abstract String smallThumbnail();
    @Nullable
    @SerializedName("large")
    public abstract String large();

    public static TypeAdapter<ImageLinks> typeAdapter(Gson gson) {
     return new AutoValue_ImageLinks.GsonTypeAdapter(gson);
    }

}
