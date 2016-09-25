package kian.mobilesoft.retrofit2.book;

import kian.mobilesoft.retrofit2.models.Book;

/**
 * The contract between the view and presenter
 */
public interface BookContract {

    interface View {

        void showBook(Book book);

        void showErrorMessage();
    }

}
