package com.example.hamiltoncapital

import android.app.Application
import com.example.hamiltoncapital.networks.ApiHelper
import com.example.hamiltoncapital.networks.ApiObject
import com.example.hamiltoncapital.repository.ExchangeRateRepository

class App:Application() {
    lateinit var repository:ExchangeRateRepository

    override fun onCreate() {
        super.onCreate()
        initialer()

    }

    private fun initialer() {
        val apiHelpers=ApiObject.getRetrofit().create(ApiHelper::class.java)
        repository= ExchangeRateRepository(apiHelpers)

    }
}