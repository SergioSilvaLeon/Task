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
import com.ssilva.task.data.models.Book;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailActivity extends AppCompatActivity implements BookDetailViewPresenterContract.View{

    @BindView(R.id.imageViewBookCover)  GlideImageView bookCover;
    @BindView(R.id.tvPublishedDate)  TextView tvPublishedDate;
    @BindView(R.id.tvAuthors) TextView tvAuthors;
    @BindView(R.id.tvDescription)  TextView tvDescription;
    @BindView(R.id.progress_bar)  ProgressBar progressBar;

    @Inject
    BookDetailViewPresenterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_detail);
        // Go to abstract activity class
        ButterKnife.bind(this);
        doDagger();
    }

    private void doDagger() {
        TaskApp.component.provideBookDetailComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getBookInformation();
    }


    @Override
    public String getTitleId() {
        Intent intent = getIntent();
        return intent.getStringExtra(BookListActivity.EXTRA_BOOK_ID);
    }

    @Override
    public void onSuccess(Book book) {
        bookCover.loadImageUrl(book.volumeInfo().imageLinks().large());
        tvPublishedDate.setText(book.volumeInfo().publishedDate());
        tvAuthors.setText(book.volumeInfo().authors().toString());
        tvDescription.setText(book.volumeInfo().description());
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
