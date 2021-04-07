package fr.mdasilva.mareu;

import android.app.Application;

import timber.log.Timber;

public class MareuApp extends Application {
    @Override public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
