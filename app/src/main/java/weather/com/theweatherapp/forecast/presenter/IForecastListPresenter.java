package weather.com.theweatherapp.forecast.presenter;

import weather.com.theweatherapp.base.BasePresenter;

/**
 * Created by bill on 10/3/2018 AD.
 */

public interface IForecastListPresenter extends BasePresenter {
    void searchWeatherWithCityName(String cityName, String unit);
}
