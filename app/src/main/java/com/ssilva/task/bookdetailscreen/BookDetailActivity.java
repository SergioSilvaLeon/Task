package com.ssilva.task.bookdetailscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.master.glideimageview.GlideImageView;
import com.ssilva.task.TaskApp;
import com.ssilva.task.booklistscreen.BookListActivity;
import com.ssilva.task.R;
import com.ssilva.task.model.Book;

import javax.inject.Inject;

public class BookDetailActivity extends AppCompatActivity implements BookDetailViewPresenterContract.View{

    private GlideImageView bookCover;
    private TextView tvPublishedDate;
    private TextView tvAuthors;
    private TextView tvDescription;
    private ProgressBar progressBar;

    // TODO: Inject Presenter
    @Inject
    BookDetailViewPresenterContract.Presenter presenter;

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
        presenter.getBookInformation();
    }

    @Override
    public void initViews() {
        bookCover = findViewById(R.id.imageViewBookCover);
        tvPublishedDate = findViewById(R.id.tvPublishedDate);
        tvAuthors = findViewById(R.id.tvAuthors);
        tvDescription = findViewById(R.id.tvDescription);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public String getTitleId() {
        Intent intent = getIntent();
        return intent.getStringExtra(BookListActivity.EXTRA_BOOK_ID);
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

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribe();
        presenter.dropView();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
