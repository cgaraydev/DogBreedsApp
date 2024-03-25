package com.example.dogbreedsapp.data.network

import com.example.dogbreedsapp.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val dogApi: DogApi by lazy {
        retrofit.create(DogApi::class.java)
    }
}