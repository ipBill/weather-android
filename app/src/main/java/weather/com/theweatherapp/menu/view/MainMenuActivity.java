package weather.com.theweatherapp.menu.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import weather.com.theweatherapp.R;
import weather.com.theweatherapp.forecast.ForecastListFragment;
import weather.com.theweatherapp.menu.presenter.MainMenuPresenter;
import weather.com.theweatherapp.weather.view.WeatherSearchFragment;

public class MainMenuActivity extends AppCompatActivity implements IMainMenuView {

    private static final String TAG = "MainMenuActivity";
    private static final String TAG_ForecastList = "ForecastListFragment";
    private static final String TAG_WeatherSearch = "WeatherSearchFragment";
    MainMenuPresenter mainMenuPresenter;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_weather:
                    mainMenuPresenter.initViewWeather();
                    return true;
                case R.id.navigation_forecast:
                    mainMenuPresenter.initViewForecast();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initView(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mainMenuPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mainMenuPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void showViewMainMenuNavigationBottomView() {
        WeatherSearchFragment weatherFragment = WeatherSearchFragment.newInstance();
        ForecastListFragment forecastFragment = ForecastListFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerMenu, weatherFragment, TAG_WeatherSearch)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerMenu, forecastFragment, TAG_ForecastList)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .detach(forecastFragment)
                .attach(weatherFragment)
                .commit();
    }

    @Override
    public void showMenuViewWeather() {
        WeatherSearchFragment weatherFragment = (WeatherSearchFragment) getSupportFragmentManager()
                .findFragmentByTag(TAG_WeatherSearch);

        ForecastListFragment forecastFragment = (ForecastListFragment) getSupportFragmentManager()
                .findFragmentByTag(TAG_ForecastList);

        getSupportFragmentManager()
                .beginTransaction()
                .detach(forecastFragment)
                .attach(weatherFragment)
                .commit();

    }

    @Override
    public void showMenuViewForecast() {
        WeatherSearchFragment weatherFragment = (WeatherSearchFragment) getSupportFragmentManager()
                .findFragmentByTag(TAG_WeatherSearch);

        ForecastListFragment forecastFragment = (ForecastListFragment) getSupportFragmentManager()
                .findFragmentByTag(TAG_ForecastList);

        getSupportFragmentManager()
                .beginTransaction()
                .detach(weatherFragment)
                .attach(forecastFragment)
                .commit();
    }

    private void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mainMenuPresenter = new MainMenuPresenter(this);
        if (savedInstanceState == null) {
            mainMenuPresenter.initMainMenuNavigationBottomView();
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
