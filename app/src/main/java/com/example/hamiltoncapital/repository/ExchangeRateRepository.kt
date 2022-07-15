package com.example.hamiltoncapital.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.example.ResponseExchangeRate
import com.example.hamiltoncapital.networks.ApiHelper
import com.example.hamiltoncapital.utils.Event
import com.example.hamiltoncapital.utils.Resource
import org.json.JSONArray
import org.json.JSONObject

class ExchangeRateRepository(
    var apiHelper: ApiHelper,
) {
    private val exchangeRateMutableLiveData=MutableLiveData<Event<Resource<ResponseExchangeRate>>>()
    val exchangeRateLiveData:LiveData<Event<Resource<ResponseExchangeRate>>>get() = exchangeRateMutableLiveData
    suspend fun getExchangeRate(curency:String){
        exchangeRateMutableLiveData.postValue(Event(Resource.Loading()))
        val response=apiHelper.getExchangeRate(curency)
        Log.d("Response",""+response)
        try {
            if(response.isSuccessful){
                exchangeRateMutableLiveData.postValue(Event(Resource.Success(response.body())))
            }
            else{
                exchangeRateMutableLiveData.postValue(Event(Resource.Failure("Unable to Fetch Data")))
            }
        }
        catch (e:Exception){
            Log.e("Error Message",e.stackTrace.toString())
            exchangeRateMutableLiveData.postValue(Event(Resource.Failure("Unable to Fetch Data")))
        }
    }
}