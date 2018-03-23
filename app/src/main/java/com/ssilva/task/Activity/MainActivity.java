package com.ssilva.task.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ssilva.task.R;
import com.ssilva.task.adapter.BooksAdapter;
import com.ssilva.task.model.BookList;
import com.ssilva.task.rest.ApiClient;
import com.ssilva.task.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BooksAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_BOOK_ID = "EXTRA_BOOK_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ApiInterface apiService =
                ApiClient.retrofit().create(ApiInterface.class);

        Call<BookList> call = apiService.getBookList();

        call.enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                BookList bookList = response.body();
                // specify an adapter (see also next example)
                mAdapter = new BooksAdapter(bookList.getBooks(), MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error, on Connection", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
