package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.adapters.CountriesListAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.network.CountriesListAPIService
import com.example.myapplication.network.CountriesListRepository
import com.example.myapplication.viewmodels.CountriesListViewModel
import com.example.myapplication.viewmodels.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var countriesListModel: CountriesListViewModel
    private lateinit var countriesListAdapter: CountriesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countriesListAPIService = CountriesListAPIService.getInstance()
        val countriesListRepository = CountriesListRepository(countriesListAPIService)

        countriesListModel = ViewModelProvider(this , MyViewModelFactory(countriesListRepository))[CountriesListViewModel::class.java]
        countriesListAdapter = CountriesListAdapter(this)
        binding.countriesListRecyclerView.apply {
            adapter = countriesListAdapter
            addItemDecoration(DividerItemDecoration(context ,DividerItemDecoration.VERTICAL))
        }


        countriesListModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        countriesListModel.getAllCountriesList()

        countriesListModel.countriesList.observe(this, Observer{ countries ->
            countries?.let {
                countriesListAdapter.countriesList = it
            }
        })


    }

}