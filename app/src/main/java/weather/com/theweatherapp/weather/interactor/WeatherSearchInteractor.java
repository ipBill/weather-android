package weather.com.theweatherapp.weather.interactor;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import weather.com.theweatherapp.manager.HttpServiceManager;
import weather.com.theweatherapp.manager.https.APIService;
import weather.com.theweatherapp.util.Constants;
import weather.com.theweatherapp.weather.dao.WeatherSearchDao;

/**
 * Created by bill on 8/3/2018 AD.
 */

public class WeatherSearchInteractor implements IWeatherSearchInteractor {


    @Override
    public void loadCityNameFromService(String cityName, String unit, final WeatherSearchListener listener) {

        APIService service = HttpServiceManager.getInstance().getService();
        service.getWeatherFromSearch(cityName, unit, Constants.apiKey).enqueue(new Callback<WeatherSearchDao>() {
            @Override
            public void onResponse(@NonNull Call<WeatherSearchDao> call, @NonNull Response<WeatherSearchDao> response) {
                if (response.isSuccessful()) {
                    listener.loadWeatherSearchFinished(true, response.body());
                } else {
                    listener.loadWeatherSearchFinished(false, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherSearchDao> call, @NonNull Throwable t) {
                t.printStackTrace();
                listener.loadWeatherSearchFinished(false, null);
            }
        });
    }
}
