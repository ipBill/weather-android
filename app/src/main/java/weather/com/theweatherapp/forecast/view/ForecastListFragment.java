package weather.com.theweatherapp.forecast.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import weather.com.theweatherapp.R;
import weather.com.theweatherapp.forecast.adapter.presenter.WeatherForecastAdapterPresenter;
import weather.com.theweatherapp.forecast.adapter.view.WeatherForecastAdapter;
import weather.com.theweatherapp.forecast.dao.WeatherForecastDao;
import weather.com.theweatherapp.forecast.interactor.ForecastListInteractor;
import weather.com.theweatherapp.forecast.presenter.ForecastListPresenter;

public class ForecastListFragment extends Fragment implements IForecastListView {

    private String unit = "metric";

    ForecastListPresenter forecastListPresenter;
    WeatherForecastAdapterPresenter forecastAdapterPresenter;
    WeatherForecastAdapter adapter;

    ProgressDialog progressDialog;

    @BindView(R.id.textInputCityName)
    TextInputLayout textInputCityName;

    @BindView(R.id.edtCityName)
    EditText edtCityName;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    @BindView(R.id.recyclerViewForecast)
    RecyclerView recyclerViewForecast;

    @BindView(R.id.btnSearch)
    Button btnSearch;

    @OnClick(R.id.btnSearch)
    public void btnSearchPressed(View v) {
        forecastListPresenter.searchWeatherWithCityName(edtCityName.getText().toString(), unit);
    }

    public ForecastListFragment() {
        super();
    }

    public static ForecastListFragment newInstance() {
        ForecastListFragment fragment = new ForecastListFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast_list, container, false);
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

        adapter = new WeatherForecastAdapter();
        forecastListPresenter = new ForecastListPresenter(this, new ForecastListInteractor());
        forecastAdapterPresenter = new WeatherForecastAdapterPresenter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerViewForecast.setLayoutManager(manager);
        edtCityName.setOnEditorActionListener(onEditorActionListener);
        recyclerViewForecast.setAdapter(adapter);
        swipeLayout.setOnRefreshListener(onRefreshListener);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        forecastListPresenter.onSaveInstanceState(outState);
        forecastAdapterPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
        forecastListPresenter.onRestoreInstanceState(savedInstanceState);
        forecastAdapterPresenter.onRestoreInstanceState(savedInstanceState);
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

        hideSwipeLayout();
    }

    private void hideSwipeLayout() {
        if (swipeLayout != null && swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
    }

    @Override
    public void updateViewCityName() {
        textInputCityName.setErrorEnabled(false);
    }

    @Override
    public void showDialogCanNotLoadService() {
        showAlertDialog(getString(R.string.dialog_fail_server));
    }

    @Override
    public void showDialogMessage(String message) {
        showAlertDialog(message);
    }

    @Override
    public void updateViewForecastWeatherList(WeatherForecastDao dao) {
        hideSwipeLayout();
        forecastAdapterPresenter.updateViewForecastWeatherList(dao);
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
                forecastListPresenter.searchWeatherWithCityName(edtCityName.getText().toString(), unit);
                return true;
            }
            return false;
        }
    };

    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            forecastListPresenter.searchWeatherWithCityName(edtCityName.getText().toString(), unit);
        }
    };

}
