package kg.geektech.weatherapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import kg.geektech.weatherapp.data.remote.WeatherAppApi;
import kg.geektech.weatherapp.data.repositories.MainRepository;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {


    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client){

        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    @Provides
    public static OkHttpClient provideMainClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    }



    @Provides
    public static WeatherAppApi provideApi(Retrofit retrofit){
        return retrofit.create(WeatherAppApi.class);
    }

    @Provides
    public static MainRepository provideMainRepository(WeatherAppApi api){
        return new MainRepository(api);
    }

}
