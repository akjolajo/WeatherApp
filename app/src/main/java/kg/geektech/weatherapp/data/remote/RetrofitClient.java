package kg.geektech.weatherapp.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)).build();



    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client )
            .build();

    public WeatherAppApi provideApi() {return retrofit.create(WeatherAppApi.class); }

}
