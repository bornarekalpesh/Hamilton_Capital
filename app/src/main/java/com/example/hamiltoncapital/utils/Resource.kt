package com.example.hamiltoncapital.utils

import retrofit2.Response

sealed class Resource<T>(val data:T?=null,val errorMessages:String?=null){
    class Loading<T>:Resource<T>()
    class Success<T>(data: T?=null):Resource<T>(data=data)
    class Failure<T>(errorMessages: String?):Resource<T>(errorMessages=errorMessages)
}
