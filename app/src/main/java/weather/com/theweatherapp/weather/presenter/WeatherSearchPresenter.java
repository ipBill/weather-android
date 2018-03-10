package weather.com.theweatherapp.weather.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import org.parceler.Parcels;

import weather.com.theweatherapp.weather.dao.WeatherSearchDao;
import weather.com.theweatherapp.weather.interactor.IWeatherSearchInteractor;
import weather.com.theweatherapp.weather.view.IWeatherSearchView;

/**
 * Created by bill on 8/3/2018 AD.
 */

public class WeatherSearchPresenter implements IWeatherSearchPresenter, IWeatherSearchInteractor.WeatherSearchListener {

    private IWeatherSearchView weatherSearchView;
    private IWeatherSearchInteractor weatherSearchInteractor;
    private WeatherSearchDao dao;

    public WeatherSearchPresenter(IWeatherSearchView weatherSearchView
            , IWeatherSearchInteractor weatherSearchInteractor) {
        this.weatherSearchView = weatherSearchView;
        this.weatherSearchInteractor = weatherSearchInteractor;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("dao", Parcels.wrap(dao));
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        dao = Parcels.unwrap(savedInstanceState.getParcelable("dao"));
        if (dao != null) {
            weatherSearchView.updateViewTemperatureObject(dao.getWeatherTemperatureObject());
            if (dao.getWeatherDetailList() != null && dao.getWeatherDetailList().size() != 0) {
                weatherSearchView.updateViewWeatherDetail(dao.getWeatherDetailList().get(0));
            }
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void searchWeatherWithCityName(@NonNull String cityName, @NonNull String unit) {
        if (!cityName.isEmpty()) {
            weatherSearchView.showProgressDialog();
            weatherSearchView.updateViewCityName();
            weatherSearchInteractor.loadCityNameFromService(cityName, unit, this);
        } else {
            weatherSearchView.updateViewCityNameEmpty();
        }
    }

    @Override
    public void loadWeatherSearchFinished(boolean isSuccess, WeatherSearchDao dao) {
        if (isSuccess) {
            this.dao = dao;
            if (dao.getCod() == 200) {
                weatherSearchView.updateViewTemperatureObject(dao.getWeatherTemperatureObject());
                if (dao.getWeatherDetailList() != null && dao.getWeatherDetailList().size() != 0) {
                    weatherSearchView.updateViewWeatherDetail(dao.getWeatherDetailList().get(0));
                }
            } else {
                weatherSearchView.hideWeatherCard();
                weatherSearchView.showDialogMessage(dao.getMessage());
            }
        } else {
            weatherSearchView.hideWeatherCard();
            weatherSearchView.showDialogCanNotLoadService();
        }
        weatherSearchView.hideProgressDialog();
    }

}
