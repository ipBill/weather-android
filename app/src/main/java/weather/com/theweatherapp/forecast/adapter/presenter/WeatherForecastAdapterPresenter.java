package weather.com.theweatherapp.forecast.adapter.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import weather.com.theweatherapp.forecast.adapter.view.IWeatherForecastView;
import weather.com.theweatherapp.forecast.dao.WeatherForecastDao;

/**
 * Created by bill on 10/3/2018 AD.
 */

public class WeatherForecastAdapterPresenter implements IWeatherForecastAdapterPresenter {

    private IWeatherForecastView weatherForecastView;

    public WeatherForecastAdapterPresenter(IWeatherForecastView weatherForecastView) {
        this.weatherForecastView = weatherForecastView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void updateViewForecastWeatherList(WeatherForecastDao dao) {
        weatherForecastView.updateViewForecastWeatherList(dao);
    }
}
