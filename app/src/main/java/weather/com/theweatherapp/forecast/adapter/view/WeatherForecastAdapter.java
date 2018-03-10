package weather.com.theweatherapp.forecast.adapter.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import weather.com.theweatherapp.R;
import weather.com.theweatherapp.forecast.dao.WeatherForecastDao;
import weather.com.theweatherapp.util.Constants;
import weather.com.theweatherapp.util.DateTimeUtil;

/**
 * Created by bill on 10/3/2018 AD.
 */

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.WeatherForecastViewHolder>
        implements IWeatherForecastView {

    private WeatherForecastDao dao;
    private Context context;

    public WeatherForecastAdapter() {

    }

    @NonNull
    @Override
    public WeatherForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast_list, parent, false);
        context = parent.getContext();
        return new WeatherForecastViewHolder(rootView);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull WeatherForecastViewHolder holder, int position) {
        WeatherForecastDao.WeatherForecastObject weatherObject = dao.getForecastList().get(position);

        holder.tvTitleWeather.setText(weatherObject.getWeatherDetailList().get(0).getMain());
        holder.tvDetailWeather.setText(weatherObject.getWeatherDetailList().get(0).getDescription());
        String url = Constants.urlIconWeather + weatherObject.getWeatherDetailList().get(0).getIcon() + Constants.imagePNGType;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.drawable.ic_cancel);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(holder.ivWeather);

        holder.tvTemperatureMax.setText(context.getString(R.string.text_max_temperature));
        holder.tvTemperatureResultMax.setText(String.valueOf(weatherObject.getTemperatureObject().getTempMax() + Constants.degreeCelsius));
        holder.tvTemperatureMin.setText(context.getString(R.string.text_min_temperature));
        holder.tvTemperatureResultMin.setText(String.valueOf(weatherObject.getTemperatureObject().getTempMin() + Constants.degreeCelsius));
        holder.tvHumidity.setText(context.getString(R.string.text_humidity));
        holder.tvHumidityResult.setText(String.valueOf(weatherObject.getTemperatureObject().getHumidity() + Constants.degreePercent));

        holder.tvDateTime.setText(String.valueOf(DateTimeUtil.getInstance().getDateFormatOutput(weatherObject.getDtText())));
    }

    @Override
    public int getItemCount() {
        if (dao == null) {
            return 0;
        }
        if (dao.getForecastList() == null) {
            return 0;
        }
        return dao.getForecastList().size();
    }

    @Override
    public void updateViewForecastWeatherList(WeatherForecastDao dao) {
        this.dao = dao;
        notifyDataSetChanged();
    }

    public static class WeatherForecastViewHolder extends RecyclerView.ViewHolder {

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

        @BindView(R.id.tvDateTime)
        TextView tvDateTime;

        public WeatherForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
