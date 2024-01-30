package com.example.myapplication.network

class CountriesListRepository constructor(private val countriesListAPIInterface: CountriesListAPIInterface) {

    suspend fun getCountriesList() = countriesListAPIInterface.getCountriesList()

}