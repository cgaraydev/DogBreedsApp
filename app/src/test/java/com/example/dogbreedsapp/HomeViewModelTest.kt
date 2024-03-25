package com.example.dogbreedsapp

import com.example.dogbreedsapp.data.network.DogApi
import com.example.dogbreedsapp.data.remote.DogResponse
import com.example.dogbreedsapp.screens.HomeViewModel
import com.example.dogbreedsapp.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    @MockK
    lateinit var dogApi: DogApi

    private lateinit var homeViewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        homeViewModel = HomeViewModel()
    }

    @Test
    fun `test loading state`() = runBlocking {
        coEvery { dogApi.getDogsBreeds() } returns DogResponse(emptyList(), "success")

        homeViewModel.getDogImages()

        assertTrue(homeViewModel.dogImages.value is Resource.Loading)

        delay(5000)

        assertTrue(homeViewModel.dogImages.value is Resource.Success)


    }

}