package com.piwew.tourismapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.piwew.core.domain.usecase.TourismUseCase

class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val tourism = tourismUseCase.getAllTourism().asLiveData()
}