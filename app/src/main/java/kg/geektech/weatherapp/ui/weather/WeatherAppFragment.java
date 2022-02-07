package kg.geektech.weatherapp.ui.weather;

import static android.content.Context.ALARM_SERVICE;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.Prefs;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.base.BaseFragment;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MainResponse;
import kg.geektech.weatherapp.data.models.Weather;
import kg.geektech.weatherapp.databinding.FragmentWeatherAppBinding;

@AndroidEntryPoint
public class WeatherAppFragment extends BaseFragment<FragmentWeatherAppBinding> {

    private WeatherViewModel viewModel;
    private MainResponse weather;


    @Override
    protected FragmentWeatherAppBinding bind() {
        return FragmentWeatherAppBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {
        viewModel.weatherLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
            @Override
            public void onChanged(Resource<MainResponse> resource) {
                switch (resource.status) {
                    case SUCCESS: {
                        setupUI(resource.data);

                        break;
                    }
                    case ERROR: {
                        break;
                    }
                    case LOADING: {
                        break;
                    }
                }
            }
        });

    }

    @Override
    protected void setupListeners() {
        binding.btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigateUp();
                navController.navigate(R.id.weatherCityFragment);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        Calendar uh = Calendar.getInstance();
        int timeOfDay = uh.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {

        } else if (timeOfDay >= 12 && timeOfDay < 24) {

        }
    }

    @Override
    protected void setupViews() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);


//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView myList = (RecyclerView) binding.recyclerWeatherNextDays;
//        myList.setLayoutManager(layoutManager);

    }

    private String getCity(){
        Prefs prefs = new Prefs(requireActivity());
        String a = prefs.getCity();
        Log.d("Ray", a);
        return a;
    }

    @Override
    protected void callRequests() {
        viewModel.getWeather(getCity());


    }


    public void setupUI(MainResponse weather) {
        this.weather = weather;
        binding.mainTempIndicator.setText(weather.getMain().getTemp().toString() + " C°");
        binding.tempMax.setText(weather.getMain().getTempMax().toString());
        binding.tempMin.setText(weather.getMain().getTempMin().toString());
        binding.txtPressure.setText(weather.getMain().getPressure().toString());


    }

    public static String getDate(Integer milliSeconds, String dateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
