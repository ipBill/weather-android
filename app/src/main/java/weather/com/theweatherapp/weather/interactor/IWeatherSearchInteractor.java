package weather.com.theweatherapp.weather.interactor;

import weather.com.theweatherapp.weather.dao.WeatherSearchDao;

/**
 * Created by bill on 8/3/2018 AD.
 */

public interface IWeatherSearchInteractor {

    void loadCityNameFromService(String cityName, String util, WeatherSearchListener listener);

    public interface WeatherSearchListener {
        void loadWeatherSearchFinished(boolean isSuccess, WeatherSearchDao dao);
    }

}
