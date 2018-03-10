package weather.com.theweatherapp;

import android.app.Application;

import weather.com.theweatherapp.manager.Contextor;

/**
 * Created by bill on 8/3/2018 AD.
 */

public class MainApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
