package weather.com.theweatherapp.forecast.adapter.presenter;

import weather.com.theweatherapp.base.BasePresenter;
import weather.com.theweatherapp.forecast.dao.WeatherForecastDao;

/**
 * Created by bill on 10/3/2018 AD.
 */

public interface IWeatherForecastAdapterPresenter extends BasePresenter {
    void updateViewForecastWeatherList(WeatherForecastDao dao);
}
