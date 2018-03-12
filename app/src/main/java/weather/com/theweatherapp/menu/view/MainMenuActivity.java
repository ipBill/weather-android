package weather.com.theweatherapp.menu.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import weather.com.theweatherapp.R;
import weather.com.theweatherapp.forecast.view.ForecastListFragment;
import weather.com.theweatherapp.menu.adapter.MainMenuViewPagerAdapter;
import weather.com.theweatherapp.menu.presenter.MainMenuPresenter;
import weather.com.theweatherapp.weather.view.WeatherSearchFragment;

public class MainMenuActivity extends AppCompatActivity implements IMainMenuView {

    private MainMenuPresenter mainMenuPresenter;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.viewPagerMainMenu)
    ViewPager viewPagerMainMenu;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_weather:
                    mainMenuPresenter.updateMenuCurrentWeather();
                    updateToolsBarMenu(getString(R.string.title_weather));
                    return true;
                case R.id.navigation_forecast:
                    mainMenuPresenter.updateMenuForecastWeather();
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
    public void showViewMainMenuNavigationBottomViewAndViewPagerMenu() {
        setupViewPager(viewPagerMainMenu);
        updateToolsBarMenu(getString(R.string.title_weather));
    }

    @Override
    public void showMenuViewWeather() {
        viewPagerMainMenu.setCurrentItem(0);
    }

    @Override
    public void showMenuViewForecast() {
        viewPagerMainMenu.setCurrentItem(1);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainMenuViewPagerAdapter adapter = new MainMenuViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(WeatherSearchFragment.newInstance());
        adapter.addFragment(ForecastListFragment.newInstance());
        viewPager.setAdapter(adapter);
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
        mainMenuPresenter.initMainMenuNavigationBottomViewAndViewPagerMenu();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
