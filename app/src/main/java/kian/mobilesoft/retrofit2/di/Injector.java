package kian.mobilesoft.retrofit2.di;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import kian.mobilesoft.retrofit2.Constants;
import kian.mobilesoft.retrofit2.Retrofit2Application;
import kian.mobilesoft.retrofit2.data.BookService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injector {
    private static final long CONNECTION_TIMEOUT = 60 * 1000; // one minute

    private static Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClientBuilder())
                .build();
    }

    public static BookService provideBookService() {
        return provideRetrofit(Constants.BASE_URL).create(BookService.class);
    }

    private static OkHttpClient getHttpClientBuilder() {
        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor();
        mLoggingInterceptor.setLevel(Retrofit2Application.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder() //
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS) //
                .addInterceptor(mLoggingInterceptor);

        if (Retrofit2Application.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        return builder.build();
    }
}
