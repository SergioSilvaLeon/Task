package com.ssilva.task.booklistscreen.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.master.glideimageview.GlideImageView;
import com.ssilva.task.R;
import com.ssilva.task.data.models.Book;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewAdapter> {

    private Subject<String> clickSubject = PublishSubject.<String>create().toSerialized();

    private List<Book> mBooks;

    public BooksAdapter() {
        mBooks = new ArrayList<>();
    }

    @Override
    public BookViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,
                parent, false);

        return new BookViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(BookViewAdapter holder, int position) {
        Book book = mBooks.get(position);
        holder.bindToView(book);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public Observable<String> getItemClickSubject() {
        return clickSubject;
    }

    public void updateDataSet(List<Book> newBooks){
        for (Book newBook: newBooks) {
            mBooks.add(newBook);
        }

        notifyDataSetChanged();
    }

    public void initDataSet(List<Book> books) {
        mBooks.clear();

        mBooks = books;
        notifyDataSetChanged();
    }

    public void clear() {
        mBooks.clear();
    }

    public class BookViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView bookTitle;
        TextView bookProductionDate;
        GlideImageView bookThumbnail;


        public BookViewAdapter(View itemView) {
            super(itemView);

            bookTitle = itemView.findViewById(R.id.tvBookTitle);
            bookProductionDate = itemView.findViewById(R.id.tvPublishedDate);
            bookThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
            itemView.setOnClickListener(this);
        }

        public void bindToView(Book book) {
            bookTitle.setText(book.volumeInfo().title());
            bookProductionDate.setText(book.volumeInfo().publishedDate());
            try {
                bookThumbnail.loadImageUrl(book.volumeInfo().imageLinks().smallThumbnail());
            } catch (Exception e) {
                // TODO: Handle Exception
            }
        }

        @Override
        public void onClick(View v) {
            clickSubject.onNext(mBooks.get(getAdapterPosition()).id());
        }
    }
}
