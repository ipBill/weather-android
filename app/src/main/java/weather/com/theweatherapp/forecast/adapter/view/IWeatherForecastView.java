package weather.com.theweatherapp.forecast.adapter.view;

import weather.com.theweatherapp.forecast.dao.WeatherForecastDao;

/**
 * Created by bill on 10/3/2018 AD.
 */

public interface IWeatherForecastView {
    void updateViewForecastWeatherList(WeatherForecastDao dao);
}
