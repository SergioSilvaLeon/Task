package com.ssilva.task.data.models;


import java.util.List;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class BookList {

    @SerializedName("items")
    public abstract List<Book> books();

    public static TypeAdapter<BookList> typeAdapter(Gson gson) {
        return new AutoValue_BookList.GsonTypeAdapter(gson);
    }

}