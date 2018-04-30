package com.ssilva.task.bookdetailscreen;

import com.ssilva.task.data.IDataRepository;
import com.ssilva.task.model.Book;

public class BookDetailPresenter implements BookDetailPresenterContract.Presenter{

    private IDataRepository dataRepository;
    private BookDetailPresenterContract.View bookDetailView = null;

    public BookDetailPresenter(IDataRepository dataRepository) {
        this.dataRepository = dataRepository;
        dataRepository.setView(this);
    }

    @Override
    public void getBookInfomation() {
        dataRepository.getBookById(bookDetailView.getTitleId());
    }

    @Override
    public void onBookResponse(Book book) {
        if (book == null) {
            bookDetailView.onError(new Throwable("Ya valiste verga"));
        }else {
            bookDetailView.onSuccess(book);
        }
    }

    @Override
    public void setView(BookDetailPresenterContract.View view) {
        bookDetailView = view;
    }

    @Override
    public void dropView() {
        bookDetailView = null;
        dataRepository.dropView();
    }
}
