package weather.com.theweatherapp.weather.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import weather.com.theweatherapp.R;
import weather.com.theweatherapp.util.Constants;
import weather.com.theweatherapp.util.HideKeyBoardUtil;
import weather.com.theweatherapp.weather.dao.WeatherSearchDao;
import weather.com.theweatherapp.weather.interactor.WeatherSearchInteractor;
import weather.com.theweatherapp.weather.presenter.WeatherSearchPresenter;

public class WeatherSearchFragment extends Fragment implements IWeatherSearchView {

    ProgressDialog progressDialog;
    WeatherSearchPresenter weatherSearchPresenter;
    private String unit = "metric";

    @BindView(R.id.textInputCityName)
    TextInputLayout textInputCityName;

    @BindView(R.id.edtCityName)
    EditText edtCityName;

    @BindView(R.id.cvResult)
    CardView cvResult;

    @BindView(R.id.tvTitleWeather)
    TextView tvTitleWeather;

    @BindView(R.id.tvDetailWeather)
    TextView tvDetailWeather;

    @BindView(R.id.ivWeather)
    ImageView ivWeather;

    @BindView(R.id.tvTemperatureMax)
    TextView tvTemperatureMax;

    @BindView(R.id.tvTemperatureResultMax)
    TextView tvTemperatureResultMax;

    @BindView(R.id.tvTemperatureMin)
    TextView tvTemperatureMin;

    @BindView(R.id.tvTemperatureResultMin)
    TextView tvTemperatureResultMin;

    @BindView(R.id.tvHumidity)
    TextView tvHumidity;

    @BindView(R.id.tvHumidityResult)
    TextView tvHumidityResult;

    @BindView(R.id.btnChangeTemperature)
    FancyButton btnChangeTemperature;

    @BindView(R.id.btnSearch)
    FancyButton btnSearch;

    @OnClick(R.id.btnSearch)
    public void btnSearchPressed(View v) {
        HideKeyBoardUtil.getInstance().hideKeyBoard(v);
        weatherSearchPresenter.searchWeatherWithCityName(edtCityName.getText().toString(), unit);
    }

    @OnClick(R.id.btnChangeTemperature)
    public void btnChangeTemperaturePressed(View v) {
        HideKeyBoardUtil.getInstance().hideKeyBoard(v);
        if (unit.equals("imperial")) {
            unit = "metric";
            btnChangeTemperature.setText(getString(R.string.btn_change_to_fahrenheit));
            weatherSearchPresenter.searchWeatherWithCityName(edtCityName.getText().toString(), "metric");
        } else {
            unit = "imperial";
            btnChangeTemperature.setText(getString(R.string.btn_change_to_celsius));
            weatherSearchPresenter.searchWeatherWithCityName(edtCityName.getText().toString(), "imperial");
        }
    }

    public WeatherSearchFragment() {
        super();
    }

    public static WeatherSearchFragment newInstance() {
        WeatherSearchFragment fragment = new WeatherSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current_weather, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState
        ButterKnife.bind(this, rootView);
        weatherSearchPresenter = new WeatherSearchPresenter(this, new WeatherSearchInteractor());
        edtCityName.setOnEditorActionListener(onEditorActionListener);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        weatherSearchPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
        weatherSearchPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.dialog_just_moment_please));
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void updateViewCityNameEmpty() {
        textInputCityName.setError(getString(R.string.edt_please_enter_city_name));
        textInputCityName.setErrorEnabled(true);
    }

    @Override
    public void updateViewCityName() {
        textInputCityName.setErrorEnabled(false);
    }

    @Override
    public void showDialogCanNotLoadService() {
        if (isAdded()) {
            showAlertDialog(getString(R.string.dialog_fail_server));
        }
    }

    @Override
    public void showDialogMessage(String message) {
        showAlertDialog(message);
    }

    @Override
    public void updateViewTemperatureObject(WeatherSearchDao.WeatherTemperatureObject currentWeatherObject) {
        if (isAdded()) {
            if (unit.equals("metric")) { //Celsius
                tvTemperatureMax.setText(getString(R.string.text_max_temperature));
                tvTemperatureResultMax.setText(String.valueOf(currentWeatherObject.getTempMax() + Constants.degreeCelsius));
                tvTemperatureMin.setText(getString(R.string.text_min_temperature));
                tvTemperatureResultMin.setText(String.valueOf(currentWeatherObject.getTempMin() + Constants.degreeCelsius));
                tvHumidity.setText(getString(R.string.text_humidity));
                tvHumidityResult.setText(String.valueOf(currentWeatherObject.getHumidity() + Constants.degreePercent));
            } else { //Fahrenheit
                tvTemperatureMax.setText(getString(R.string.text_max_temperature));
                tvTemperatureResultMax.setText(String.valueOf(currentWeatherObject.getTempMax() + Constants.degreeFahrenheit));
                tvTemperatureMin.setText(getString(R.string.text_min_temperature));
                tvTemperatureResultMin.setText(String.valueOf(currentWeatherObject.getTempMin() + Constants.degreeFahrenheit));
                tvHumidity.setText(getString(R.string.text_humidity));
                tvHumidityResult.setText(String.valueOf(currentWeatherObject.getHumidity() + Constants.degreePercent));
            }
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void updateViewWeatherDetail(WeatherSearchDao.WeatherDetailObject weatherDetailObject) {
        if (isAdded()) {
            cvResult.setVisibility(View.VISIBLE);
            tvTitleWeather.setText(weatherDetailObject.getMain());
            tvDetailWeather.setText(weatherDetailObject.getDescription());
            String url = Constants.urlIconWeather + weatherDetailObject.getIcon() + Constants.imagePNGType;
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(R.drawable.ic_cancel);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(this)
                    .load(url)
                    .apply(requestOptions)
                    .into(ivWeather);
        }
    }

    @Override
    public void hideWeatherCard() {
        if (isAdded()) {
            cvResult.setVisibility(View.GONE);
        }
    }

    private void showAlertDialog(String message) {
        Context context = getContext();
        if (context != null) {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(context);
            builder.setTitle(getString(R.string.app_name))
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                HideKeyBoardUtil.getInstance().hideKeyBoard(getView());
                weatherSearchPresenter.searchWeatherWithCityName(edtCityName.getText().toString(), unit);
                return true;
            }
            return false;
        }
    };
}
