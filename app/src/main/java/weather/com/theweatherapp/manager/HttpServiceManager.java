package weather.com.theweatherapp.manager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import weather.com.theweatherapp.manager.https.APIService;

/**
 * Created by bill on 8/3/2018 AD.
 */

public class HttpServiceManager {

    private APIService service;

    private static final HttpServiceManager ourInstance = new HttpServiceManager();

    public static HttpServiceManager getInstance() {
        return ourInstance;
    }

    private HttpServiceManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(APIService.class);
    }

    public APIService getService() {
        return service;
    }
}
