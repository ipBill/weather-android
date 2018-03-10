package weather.com.theweatherapp.weather.dao;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bill on 10/3/2018 AD.
 */

@Parcel
public class WeatherSearchDao {

    @SerializedName("cod")
    int cod;

    @SerializedName("message")
    String message;

    @SerializedName("name")
    String name;

    @SerializedName("id")
    int id;

    @SerializedName("dt")
    int dt;

    @SerializedName("visibility")
    int visibility;

    @SerializedName("base")
    String base;

    @SerializedName("main")
    WeatherTemperatureObject weatherTemperatureObject;

    @SerializedName("weather")
    List<WeatherDetailObject> weatherDetailList = new ArrayList<>();

    public int getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getDt() {
        return dt;
    }

    public int getVisibility() {
        return visibility;
    }

    public String getBase() {
        return base;
    }

    public WeatherTemperatureObject getWeatherTemperatureObject() {
        return weatherTemperatureObject;
    }

    public List<WeatherDetailObject> getWeatherDetailList() {
        return weatherDetailList;
    }

    @Parcel
    public static class WeatherDetailObject {

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
    public static class WeatherTemperatureObject {

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

