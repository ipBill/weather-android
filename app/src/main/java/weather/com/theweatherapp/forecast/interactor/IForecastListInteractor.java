package weather.com.theweatherapp.forecast.interactor;

import weather.com.theweatherapp.forecast.dao.WeatherForecastDao;
import weather.com.theweatherapp.forecast.presenter.ForecastListPresenter;

/**
 * Created by bill on 10/3/2018 AD.
 */

public interface IForecastListInteractor {
    void loadForecastCityNameFromService(String cityName, String unit, ForecastListListener listener);

    public interface ForecastListListener {

        void loadForecastCityNameFromServiceFinished(boolean isSuccess, WeatherForecastDao dao);
    }
}
