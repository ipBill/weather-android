package weather.com.theweatherapp.menu.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import weather.com.theweatherapp.base.BasePresenter;
import weather.com.theweatherapp.menu.view.IMainMenuView;

/**
 * Created by bill on 8/3/2018 AD.
 */

public class MainMenuPresenter implements IMainMenuPresenter {

    private IMainMenuView mainMenuView;

    public MainMenuPresenter(IMainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void initMainMenuNavigationBottomView() {
        mainMenuView.showViewMainMenuNavigationBottomView();
    }

    @Override
    public void initViewWeather() {
        mainMenuView.showMenuViewWeather();
    }

    @Override
    public void initViewForecast() {
        mainMenuView.showMenuViewForecast();
    }
}
