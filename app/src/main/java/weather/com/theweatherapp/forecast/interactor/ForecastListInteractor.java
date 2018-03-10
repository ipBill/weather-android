package weather.com.theweatherapp.forecast.interactor;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import weather.com.theweatherapp.forecast.dao.WeatherForecastDao;
import weather.com.theweatherapp.manager.HttpServiceManager;
import weather.com.theweatherapp.manager.https.APIService;
import weather.com.theweatherapp.util.Constants;

/**
 * Created by bill on 10/3/2018 AD.
 */

public class ForecastListInteractor implements IForecastListInteractor {

    @Override
    public void loadForecastCityNameFromService(String cityName, String unit, final ForecastListListener listener) {

        APIService service = HttpServiceManager.getInstance().getService();
        service.getWeatherForecastFromSearch(cityName, unit, Constants.apiKey).enqueue(new Callback<WeatherForecastDao>() {
            @Override
            public void onResponse(@NonNull Call<WeatherForecastDao> call, @NonNull Response<WeatherForecastDao> response) {
                if (response.isSuccessful()) {
                    listener.loadForecastCityNameFromServiceFinished(true, response.body());
                } else {
                    listener.loadForecastCityNameFromServiceFinished(false, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherForecastDao> call, @NonNull Throwable t) {
                t.printStackTrace();
                listener.loadForecastCityNameFromServiceFinished(false, null);
            }
        });
    }

}
