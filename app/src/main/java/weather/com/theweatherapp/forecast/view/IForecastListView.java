package weather.com.theweatherapp.forecast.view;

import weather.com.theweatherapp.forecast.dao.WeatherForecastDao;

/**
 * Created by bill on 10/3/2018 AD.
 */

public interface IForecastListView {
    void showProgressDialog();

    void updateViewCityName();

    void updateViewCityNameEmpty();

    void hideProgressDialog();

    void showDialogCanNotLoadService();

    void showDialogMessage(String message);

    void updateViewForecastWeatherList(WeatherForecastDao dao);
}
