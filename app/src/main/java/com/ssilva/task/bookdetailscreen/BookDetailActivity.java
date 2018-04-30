package com.ssilva.task.bookdetailscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.master.glideimageview.GlideImageView;
import com.ssilva.task.TaskApp;
import com.ssilva.task.booklist.MainActivity;
import com.ssilva.task.R;
import com.ssilva.task.model.Book;

import javax.inject.Inject;

public class BookDetailActivity extends AppCompatActivity implements BookDetailPresenterContract.View{

    private GlideImageView bookCover;
    private TextView tvPublishedDate;
    private TextView tvAuthors;
    private TextView tvDescription;

    // TODO: Inject Presenter
    @Inject
    BookDetailPresenterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_detail);
        initViews();
        injectDependencies();
    }

    private void injectDependencies() {
        TaskApp.component.provideBookDetailComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getBookInfomation();
    }

    @Override
    public void initViews() {
        bookCover = findViewById(R.id.imageViewBookCover);
        tvPublishedDate = findViewById(R.id.tvPublishedDate);
        tvAuthors = findViewById(R.id.tvAuthors);
        tvDescription = findViewById(R.id.tvDescription);
    }

    @Override
    public String getTitleId() {
        Intent intent = getIntent();
        return intent.getStringExtra(MainActivity.EXTRA_BOOK_ID);
    }

    @Override
    public void onSuccess(Book book) {
        bookCover.loadImageUrl(book.getVolumeInfo().getImageLinks().getLarge());
        tvPublishedDate.setText(book.getVolumeInfo().getPublishedDate());
        tvAuthors.setText(book.getVolumeInfo().getAuthors().toString());
        tvDescription.setText(book.getVolumeInfo().getDescription());
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this, "Error, on Connection!", Toast.LENGTH_SHORT).show();
    }
}
