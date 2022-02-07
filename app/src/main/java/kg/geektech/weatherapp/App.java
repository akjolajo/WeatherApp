package kg.geektech.weatherapp;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

import kg.geektech.weatherapp.data.remote.WeatherAppApi;
import kg.geektech.weatherapp.data.repositories.MainRepository;

@HiltAndroidApp
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
