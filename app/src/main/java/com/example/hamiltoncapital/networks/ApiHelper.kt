package com.example.hamiltoncapital.networks

import com.example.example.ResponseExchangeRate
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface ApiHelper {
    @Headers("Content-Type: application/json")
    @GET("/v6/9a9ce3a9e63026d645a724d7/latest/{currency}")
    suspend fun getExchangeRate(
        @Path("currency") currency: String
    ): Response<ResponseExchangeRate>

}