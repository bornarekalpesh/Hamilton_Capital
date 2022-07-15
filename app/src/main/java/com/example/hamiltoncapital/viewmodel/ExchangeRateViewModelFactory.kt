package com.example.hamiltoncapital.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hamiltoncapital.repository.ExchangeRateRepository

class ExchangeRateViewModelFactory(var exchangeRateRepository: ExchangeRateRepository):
ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExchangeRateViewModel(exchangeRateRepository) as T
    }
}