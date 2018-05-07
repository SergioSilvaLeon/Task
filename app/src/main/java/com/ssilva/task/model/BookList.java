package com.ssilva.task.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookList {

    @SerializedName("items")
    @Expose
    private List<Book> books = null;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> items) {
        this.books = items;
    }

}