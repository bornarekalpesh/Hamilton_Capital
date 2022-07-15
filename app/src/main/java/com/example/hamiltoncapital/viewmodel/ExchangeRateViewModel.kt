package com.example.hamiltoncapital.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.ResponseExchangeRate
import com.example.hamiltoncapital.repository.ExchangeRateRepository
import com.example.hamiltoncapital.utils.Event
import com.example.hamiltoncapital.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class ExchangeRateViewModel(val repository: ExchangeRateRepository):ViewModel() {

    val responseExchangeRate: LiveData<Event<Resource<ResponseExchangeRate>>>
        get() = repository.exchangeRateLiveData
    fun getExchangeRate(curency:String){
        CoroutineScope(Dispatchers.IO).launch{
            repository.getExchangeRate(curency)
        }
    }
}