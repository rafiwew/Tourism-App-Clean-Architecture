package com.piwew.tourismapp.home

import androidx.lifecycle.ViewModel
import com.piwew.tourismapp.core.data.source.TourismRepository

class HomeViewModel(tourismRepository: TourismRepository) : ViewModel() {
    val tourism = tourismRepository.getAllTourism()
}