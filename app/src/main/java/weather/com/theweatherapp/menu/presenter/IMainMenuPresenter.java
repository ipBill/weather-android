package weather.com.theweatherapp.menu.presenter;

import weather.com.theweatherapp.base.BasePresenter;

/**
 * Created by bill on 8/3/2018 AD.
 */

public interface IMainMenuPresenter extends BasePresenter {

    void updateMenuCurrentWeather();

    void updateMenuForecastWeather();

    void initMainMenuNavigationBottomViewAndViewPagerMenu();
}
