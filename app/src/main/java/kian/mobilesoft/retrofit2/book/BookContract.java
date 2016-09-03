package kian.mobilesoft.retrofit2.book;

import kian.mobilesoft.retrofit2.models.Book;

/**
 * The contract between the view and presenter
 */
public interface BookContract
{
    interface View
    {
        void showErrorMessage ();

        void showBook (Book book);
    }

}
