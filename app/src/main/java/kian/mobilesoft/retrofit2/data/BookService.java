package kian.mobilesoft.retrofit2.data;

import java.util.List;

import kian.mobilesoft.retrofit2.models.Book;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookService
{
    @GET( "books.json" )
    Call<List<Book>> getBooks();

    @GET( "books/{id}.json" )
    Call<Book> getBook( @Path( "id" ) Long id );
}
