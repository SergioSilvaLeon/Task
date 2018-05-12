
package com.ssilva.task.data.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Book {

    @SerializedName("volumeInfo")
    public abstract VolumeInfo volumeInfo();
    @SerializedName("id")
    public abstract String id();

    public static TypeAdapter<Book> typeAdapter(Gson gson) {
        return new AutoValue_Book.GsonTypeAdapter(gson);
    }

}
