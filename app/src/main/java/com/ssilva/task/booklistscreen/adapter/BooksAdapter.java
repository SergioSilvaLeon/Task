package com.ssilva.task.booklistscreen.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.master.glideimageview.GlideImageView;
import com.ssilva.task.R;
import com.ssilva.task.booklistscreen.ItemSelectedListener;
import com.ssilva.task.model.Book;

import java.util.List;


public class BooksAdapter  extends RecyclerView.Adapter<BooksAdapter.BookViewAdapter>{

    private List<Book> mBooks;
    private ItemSelectedListener mItemSelectedListener;

    public BooksAdapter(List<Book> books, ItemSelectedListener itemSelectedListener){
        mBooks = books;
        mItemSelectedListener = itemSelectedListener;
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

    public class BookViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

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

        public void bindToView(Book book){
            bookTitle.setText(book.getVolumeInfo().getTitle());
            bookProductionDate.setText(book.getVolumeInfo().getPublishedDate());
            bookThumbnail.loadImageUrl(book.getVolumeInfo().getImageLinks().getSmallThumbnail());
        }

        @Override
        public void onClick(View v) {
            mItemSelectedListener.onItemSelected(mBooks.get(getAdapterPosition()).getId());
        }
    }
}
