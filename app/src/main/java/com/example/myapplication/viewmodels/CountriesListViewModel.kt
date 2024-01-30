package com.example.myapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.CountriesListModelItem
import com.example.myapplication.network.CountriesListRepository
import kotlinx.coroutines.*

class CountriesListViewModel constructor(private val countriesListRepository: CountriesListRepository): ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val countriesList = MutableLiveData<List<CountriesListModelItem>?>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllCountriesList() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = countriesListRepository.getCountriesList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countriesList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}