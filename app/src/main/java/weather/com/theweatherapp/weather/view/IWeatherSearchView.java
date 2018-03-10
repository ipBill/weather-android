package weather.com.theweatherapp.weather.view;

import weather.com.theweatherapp.weather.dao.WeatherSearchDao;

/**
 * Created by bill on 8/3/2018 AD.
 */

public interface IWeatherSearchView {

    void updateViewCityNameEmpty();

    void updateViewCityName();

    void showDialogCanNotLoadService();

    void updateViewTemperatureObject(WeatherSearchDao.WeatherTemperatureObject weatherTemperatureObject);

    void updateViewWeatherDetail(WeatherSearchDao.WeatherDetailObject weatherDetailObject);

    void showProgressDialog();

    void hideProgressDialog();

    void showDialogMessage(String message);

    void hideWeatherCard();

}
