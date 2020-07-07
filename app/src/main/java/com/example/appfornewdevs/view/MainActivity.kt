package com.example.appfornewdevs.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfornewdevs.R
import com.example.appfornewdevs.api.Resource
import com.example.appfornewdevs.databinding.ActivityMainBinding
import com.example.appfornewdevs.viewmodels.BaseViewModelFactory
import com.example.appfornewdevs.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory.viewModelFactory { MainActivityViewModel(this.application) }
        ).get(MainActivityViewModel::class.java)

        binding.regionsList.layoutManager = LinearLayoutManager(this)
        viewModel.loadLanguages().observe(this::getLifecycle) {
            if (it.status == Resource.Status.SUCCESS) {
                binding.regionsList.adapter = RegionAdapter(it.data!!)
            } else {
                Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
