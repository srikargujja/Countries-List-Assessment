package com.example.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CountriesListAPIService {
    companion object {
        var retrofitService: CountriesListAPIInterface? = null
        fun getInstance() : CountriesListAPIInterface {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://gist.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(CountriesListAPIInterface::class.java)
            }
            return retrofitService!!
        }
    }
}