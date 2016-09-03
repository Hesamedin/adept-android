package kian.mobilesoft.retrofit2.di;

import kian.mobilesoft.retrofit2.Constants;
import kian.mobilesoft.retrofit2.data.BookService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injector
{
    public static Retrofit provideRetrofit (String baseUrl)
    {
        return new Retrofit.Builder()
                .baseUrl( baseUrl )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
    }

    public static BookService provideBookService ()
    {
        return provideRetrofit( Constants.BASE_URL ).create( BookService.class );
    }

}
