package weather.com.theweatherapp.forecast.dao;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bill on 10/3/2018 AD.
 */

@Parcel
public class WeatherForecastDao {

    @SerializedName("cod")
    int cod;

    @SerializedName("message")
    String message;

    @SerializedName("cnt")
    String cnt;

    @SerializedName("city")
    WeatherForecastCityObject weatherForecastCityObject;

    @SerializedName("list")
    List<WeatherForecastObject> forecastList = new ArrayList<>();

    public int getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }

    public String getCnt() {
        return cnt;
    }

    public WeatherForecastCityObject getWeatherForecastCityObject() {
        return weatherForecastCityObject;
    }

    public List<WeatherForecastObject> getForecastList() {
        return forecastList;
    }

    @Parcel
    public static class WeatherForecastCityObject {

        @SerializedName("id")
        int id;

        @SerializedName("name")
        String name;

        @SerializedName("country")
        String country;

        @SerializedName("population")
        String population;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCountry() {
            return country;
        }

        public String getPopulation() {
            return population;
        }
    }

    @Parcel
    public static class WeatherForecastObject {

        @SerializedName("dt")
        int dt;

        @SerializedName("dt_txt")
        String dtText;

        @SerializedName("main")
        WeatherForecastTemperatureObject temperatureObject;

        @SerializedName("weather")
        List<WeatherForecastDetailObject> weatherDetailList = new ArrayList<>();

        public int getDt() {
            return dt;
        }

        public String getDtText() {
            return dtText;
        }

        public WeatherForecastTemperatureObject getTemperatureObject() {
            return temperatureObject;
        }

        public List<WeatherForecastDetailObject> getWeatherDetailList() {
            return weatherDetailList;
        }

        @Parcel
        public static class WeatherForecastDetailObject {

            @SerializedName("id")
            int id;

            @SerializedName("main")
            String main;

            @SerializedName("description")
            String description;

            @SerializedName("icon")
            String icon;

            public int getId() {
                return id;
            }

            public String getMain() {
                return main;
            }

            public String getDescription() {
                return description;
            }

            public String getIcon() {
                return icon;
            }
        }

        @Parcel
        public static class WeatherForecastTemperatureObject {

            @SerializedName("temp")
            float temp;

            @SerializedName("pressure")
            float pressure;

            @SerializedName("humidity")
            float humidity;

            @SerializedName("temp_min")
            float tempMin;

            @SerializedName("temp_max")
            float tempMax;

            public float getTemp() {
                return temp;
            }

            public float getPressure() {
                return pressure;
            }

            public float getHumidity() {
                return humidity;
            }

            public float getTempMin() {
                return tempMin;
            }

            public float getTempMax() {
                return tempMax;
            }
        }

    }
}
