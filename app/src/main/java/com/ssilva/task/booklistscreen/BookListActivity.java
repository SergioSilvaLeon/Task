package com.ssilva.task.booklistscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ssilva.task.R;
import com.ssilva.task.TaskApp;
import com.ssilva.task.bookdetailscreen.BookDetailActivity;
import com.ssilva.task.booklistscreen.adapter.BooksAdapter;
import com.ssilva.task.model.BookList;

import javax.inject.Inject;

public class BookListActivity extends AppCompatActivity
        implements BookListViewPresenterContract.View, ItemSelectedListener {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private BooksAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static final String EXTRA_BOOK_ID = "EXTRA_BOOK_ID";

    @Inject
    BookListViewPresenterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        goDagger();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadListOfBooks();
    }

    private void goDagger() {
        TaskApp.component.provideBookListComponent().inject(this);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(BookList listOfBooks) {
        mAdapter = new BooksAdapter(listOfBooks.getBooks(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemSelected(String id) {
        Intent intent = new Intent(BookListActivity.this, BookDetailActivity.class);
        intent.putExtra(BookListActivity.EXTRA_BOOK_ID, id);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribe();
        presenter.dropView();
    }
}
