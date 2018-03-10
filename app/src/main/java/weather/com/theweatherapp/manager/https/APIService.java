package weather.com.theweatherapp.manager.https;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weather.com.theweatherapp.weather.dao.WeatherSearchDao;

/**
 * Created by bill on 8/3/2018 AD.
 */

public interface APIService {

    @GET("weather")
    Call<WeatherSearchDao> getWeatherFromSearch(@Query("q") String searchCity
            , @Query("units") String units, @Query("apikey") String apiKey);
}
