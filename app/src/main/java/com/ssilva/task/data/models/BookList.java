package com.ssilva.task.data.models;


import java.util.List;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class BookList {

    @SerializedName("items")
    public abstract List<Book> books();

}