package com.ssilva.task.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.master.glideimageview.GlideImageView;
import com.ssilva.task.R;
import com.ssilva.task.model.Book;
import com.ssilva.task.rest.ApiClient;
import com.ssilva.task.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_detail);

        final GlideImageView bookCover = findViewById(R.id.imageViewBookCover);
        final TextView tvPublishedDate = findViewById(R.id.tvPublishedDate);
        final TextView tvAuthors = findViewById(R.id.tvAuthors);
        final TextView tvDescription = findViewById(R.id.tvDescription);

        Intent intent = getIntent();
        String id = intent.getStringExtra(MainActivity.EXTRA_BOOK_ID);

        ApiInterface apiService =
                ApiClient.retrofit().create(ApiInterface.class);

        Call<Book> call = apiService.getBook(id);

        call.enqueue(new Callback<Book>() {
            @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                Book book = response.body();
                bookCover.loadImageUrl(book.getVolumeInfo().getImageLinks().getLarge());
                tvPublishedDate.setText(book.getVolumeInfo().getPublishedDate());
                tvAuthors.setText(book.getVolumeInfo().getAuthors().toString());
                tvDescription.setText(book.getVolumeInfo().getDescription());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(BookDetailActivity.this, "Error, on Connection!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
