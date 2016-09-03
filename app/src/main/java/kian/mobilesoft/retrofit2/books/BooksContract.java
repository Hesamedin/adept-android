package kian.mobilesoft.retrofit2.books;

import java.util.List;

import kian.mobilesoft.retrofit2.models.Book;

/**
 * The contract between the view and presenter
 */
public interface BooksContract
{

    interface View
    {

        void showBooks (List<Book> books);

        void showErrorMessage ();
    }

}
