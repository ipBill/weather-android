package weather.com.theweatherapp.weather.presenter;

import weather.com.theweatherapp.base.BasePresenter;

/**
 * Created by bill on 8/3/2018 AD.
 */

public interface IWeatherSearchPresenter extends BasePresenter {
    void searchWeatherWithCityName(String cityName,String unit);
}
