package com.piwew.tourismapp.home

import androidx.lifecycle.ViewModel
import com.piwew.tourismapp.core.domain.usecase.TourismUseCase

class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val tourism = tourismUseCase.getAllTourism()
}