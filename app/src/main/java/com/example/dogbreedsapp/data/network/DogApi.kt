package com.example.dogbreedsapp.data.network

import com.example.dogbreedsapp.data.remote.DogImageResponse
import com.example.dogbreedsapp.data.remote.DogResponse
import com.example.dogbreedsapp.utils.Constants.BREEDS_ENDPOINT
import com.example.dogbreedsapp.utils.Constants.IMAGES_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {

    @GET(BREEDS_ENDPOINT)
    suspend fun getDogsBreeds(): DogResponse

    @GET(IMAGES_ENDPOINT)
    suspend fun getDogsImagesByBreed(
        @Path("breed") breed: String
    ): DogImageResponse

}