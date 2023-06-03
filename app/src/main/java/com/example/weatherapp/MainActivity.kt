package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val viewModel: WeatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        if(viewModel.result.value!=null)
            runBlocking {
                binding.recyclerView.adapter = ItemAdapter(this@MainActivity, viewModel.result.value!!.await().forecast?.forecastday)
            }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.textInputLayout.setEndIconOnClickListener {
            viewModel.setCityValue(binding.textInputEditText.text.toString())
            MainScope().launch {
                viewModel.loadWeatherData()
                binding.recyclerView.adapter = ItemAdapter(this@MainActivity, viewModel.result.value!!.await().forecast?.forecastday)
            }
        }
    }
}