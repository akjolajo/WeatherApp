package kg.geektech.weatherapp.data.repositories;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import kg.geektech.weatherapp.Prefs;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MainResponse;
import kg.geektech.weatherapp.data.remote.WeatherAppApi;
import kg.geektech.weatherapp.ui.weather.WeatherCityFragment;
import kg.geektech.weatherapp.ui.weather.WeatherViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private WeatherAppApi api;


    @Inject
    public MainRepository(WeatherAppApi api) {
        this.api = api;
    }


    public MutableLiveData<Resource<MainResponse>> getWeather(String city) {

        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather(city, "bff2008a7f2e0a8857d1b0fd6a47a5f9", "metric").enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });

        return liveData;

    }
    public MutableLiveData<Resource<MainResponse>> getWeather5Days(String city){

        MutableLiveData<Resource<MainResponse>> liveDataFor5Days = new MutableLiveData<>();
        liveDataFor5Days.setValue(Resource.loading());
        api.getWeather5Days(city, "bff2008a7f2e0a8857d1b0fd6a47a5f9", "metric").enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveDataFor5Days.setValue(Resource.success(response.body()));
                } else {
                    liveDataFor5Days.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveDataFor5Days.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveDataFor5Days;
    }

}
