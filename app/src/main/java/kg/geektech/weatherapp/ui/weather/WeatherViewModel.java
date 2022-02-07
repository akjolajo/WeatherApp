package kg.geektech.weatherapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MainResponse;
import kg.geektech.weatherapp.data.repositories.MainRepository;


@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public MainRepository repository;

    @Inject
    public WeatherViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<MainResponse>> weatherLiveData;

    public void getWeather(String city) {
        weatherLiveData = repository.getWeather(city);
    }


}
