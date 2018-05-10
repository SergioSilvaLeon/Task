package com.ssilva.task.booklistscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ssilva.task.R;
import com.ssilva.task.TaskApp;
import com.ssilva.task.bookdetailscreen.BookDetailActivity;
import com.ssilva.task.booklistscreen.adapter.BooksAdapter;
import com.ssilva.task.booklistscreen.adapter.PaginationScroll;
import com.ssilva.task.booklistscreen.adapter.RxSearch;
import com.ssilva.task.booklistscreen.dagger.BookListModule;
import com.ssilva.task.data.models.BookList;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class BookListActivity extends AppCompatActivity implements BookListViewPresenterContract.View {

    public static final String EXTRA_BOOK_ID = "EXTRA_BOOK_ID";
    private BooksAdapter mAdapter;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    BookListViewPresenterContract.Presenter presenter;
    @Named("welcomeMessage")
    @Inject
    String welcomeMessage;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    PaginationScroll scroller;
    @Inject
    RxSearch rxSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        goDagger();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(scroller);

        Toast.makeText(this, welcomeMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.menuSearch);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(rxSearch);

        setUpQuerySubject();

        return true;
    }

    private void setUpQuerySubject() {
        Disposable subscription = rxSearch.getQuerySubject()
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribe(query -> Log.i("searchviewquery", query));

        presenter.subscribe(subscription);
    }

    public void setUpIndexItem() {
        Disposable subscription = scroller.getTotalListener()
                .doOnNext(__ -> showProgressBar())
                .subscribe(startIndex -> presenter.loadMoreListOfBooks(startIndex));

        presenter.subscribe(subscription);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadListOfBooks();
    }

    private void goDagger() {
        TaskApp.component
                .provideBookListComponentBuilder()
                .bookDetailComponentBuilder(new BookListModule("Hello world!", this))
                .build()
                .inject(this);
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

        dismissProgressBar();
    }

    @Override
    public void onSuccess(BookList listOfBooks) {
        mAdapter = new BooksAdapter(listOfBooks.getBooks());
        mRecyclerView.setAdapter(mAdapter);
        setUpItemClicked();
        setUpIndexItem();

        dismissProgressBar();

    }

    @Override
    public void onFetchSuccess(BookList listOfBooks) {
        mAdapter.updateDataSet(listOfBooks.getBooks());
        scroller.setLoading(false);

        dismissProgressBar();
    }

    private void setUpItemClicked() {
        Disposable subscription = mAdapter.getClickListener()
                .subscribe(id -> {
                    Intent intent = new Intent(BookListActivity.this, BookDetailActivity.class);
                    intent.putExtra(BookListActivity.EXTRA_BOOK_ID, id);
                    startActivity(intent);
                });

        presenter.subscribe(subscription);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribe();
        presenter.dropView();
    }
}
