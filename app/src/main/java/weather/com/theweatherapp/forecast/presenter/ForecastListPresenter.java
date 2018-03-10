package weather.com.theweatherapp.forecast.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.parceler.Parcels;

import weather.com.theweatherapp.forecast.dao.WeatherForecastDao;
import weather.com.theweatherapp.forecast.interactor.IForecastListInteractor;
import weather.com.theweatherapp.forecast.view.IForecastListView;

/**
 * Created by bill on 10/3/2018 AD.
 */

public class ForecastListPresenter implements IForecastListPresenter, IForecastListInteractor.ForecastListListener {

    private WeatherForecastDao dao;
    private IForecastListView forecastListView;
    private IForecastListInteractor forecastListInteractor;

    public ForecastListPresenter(IForecastListView forecastListView, IForecastListInteractor forecastListInteractor) {
        this.forecastListView = forecastListView;
        this.forecastListInteractor = forecastListInteractor;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("dao", Parcels.wrap(dao));
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        dao = Parcels.unwrap(savedInstanceState.getParcelable("dao"));
        forecastListView.updateViewForecastWeatherList(dao);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void searchWeatherWithCityName(String cityName, String unit) {
        if (!cityName.isEmpty()) {
            forecastListView.showProgressDialog();
            forecastListView.updateViewCityName();
            forecastListInteractor.loadForecastCityNameFromService(cityName, unit, this);
        } else {
            forecastListView.updateViewCityNameEmpty();
        }
    }

    @Override
    public void loadForecastCityNameFromServiceFinished(boolean isSuccess, WeatherForecastDao dao) {
        if (isSuccess) {
            if (dao.getCod() == 200) {
                this.dao = dao;
                forecastListView.updateViewForecastWeatherList(dao);
            } else {
                forecastListView.showDialogMessage(dao.getMessage());
            }
        } else {
            forecastListView.showDialogCanNotLoadService();
        }
        forecastListView.hideProgressDialog();
    }
}
