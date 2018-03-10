package weather.com.theweatherapp.menu.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import weather.com.theweatherapp.R;
import weather.com.theweatherapp.forecast.view.ForecastListFragment;
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
                    updateToolsBarMenu(getString(R.string.title_weather));
                    return true;
                case R.id.navigation_forecast:
                    mainMenuPresenter.initViewForecast();
                    updateToolsBarMenu(getString(R.string.title_forecast));
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
        mainMenuPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mainMenuPresenter.onRestoreInstanceState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void showViewMainMenuNavigationBottomView() {
        WeatherSearchFragment weatherFragment = WeatherSearchFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerMenu, weatherFragment)
                .commit();

        updateToolsBarMenu(getString(R.string.title_weather));
    }

    @Override
    public void showMenuViewWeather() {
        WeatherSearchFragment weatherFragment = WeatherSearchFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerMenu, weatherFragment)
                .commit();

    }

    @Override
    public void showMenuViewForecast() {

        ForecastListFragment forecastFragment = ForecastListFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerMenu, forecastFragment)
                .commit();
    }

    private void updateToolsBarMenu(String menu) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(menu);
        }
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
