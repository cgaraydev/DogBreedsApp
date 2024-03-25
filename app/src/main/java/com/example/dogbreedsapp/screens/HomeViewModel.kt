package com.example.dogbreedsapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreedsapp.data.network.RetrofitService
import com.example.dogbreedsapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val dogApi = RetrofitService.dogApi

    private val _dogImages = MutableStateFlow<Resource<Map<String, String>>>(Resource.Loading())
    val dogImages: StateFlow<Resource<Map<String, String>>> get() = _dogImages

    init {
        getDogImages()
    }

    fun getDogImages() {
        viewModelScope.launch {

            try {
                val breedsResponse = dogApi.getDogsBreeds()
                val imagesMap = mutableMapOf<String, String>()

                val pageSize = 10
                val totalPages = (breedsResponse.message.size + pageSize - 1) / pageSize

                for (page in 0 until totalPages) {
                    val startIndex = page * pageSize
                    val endIndex = minOf(startIndex + pageSize, breedsResponse.message.size)
                    val breedsPage = breedsResponse.message.subList(startIndex, endIndex)

                    breedsPage.forEach { breed ->
                        val imageResponse = dogApi.getDogsImagesByBreed(breed)
                        imagesMap[breed] = imageResponse.message
                    }
                    _dogImages.value = Resource.Success(imagesMap.toMap())
                }
            } catch (e: Exception) {
                _dogImages.value = Resource.Error("Error de carga")
            }
        }
    }
}