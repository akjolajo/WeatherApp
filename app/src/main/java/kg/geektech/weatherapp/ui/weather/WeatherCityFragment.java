package kg.geektech.weatherapp.ui.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektech.weatherapp.Prefs;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.base.BaseFragment;
import kg.geektech.weatherapp.databinding.FragmentWeatherCityBinding;

public class WeatherCityFragment extends BaseFragment<FragmentWeatherCityBinding> {


    @Override
    protected FragmentWeatherCityBinding bind() {
        return FragmentWeatherCityBinding.inflate(getLayoutInflater());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void setupListeners() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs prefs = new Prefs(requireContext());
                prefs.saveCity(binding.editCity.getText().toString());
                navController.navigateUp();
                navController.navigate(R.id.weatherAppFragment);
            }
        });
    }




    @Override
    protected void setupViews() {

    }

    @Override
    protected void callRequests() {

    }
}