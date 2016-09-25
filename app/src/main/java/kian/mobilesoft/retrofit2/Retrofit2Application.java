package kian.mobilesoft.retrofit2;

import com.facebook.stetho.Stetho;

import android.app.Application;

import timber.log.Timber;

public class Retrofit2Application extends Application {

    // Will be accessible across the app
    public static boolean DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {

            Retrofit2Application.DEBUG = true;

            Timber.plant(new Timber.DebugTree());

            Stetho.initializeWithDefaults(this);
        }

        Timber.i("Creating our Application");
    }
}
