package com.piwew.tourismapp.favorite

import androidx.lifecycle.ViewModel
import com.piwew.tourismapp.core.data.source.TourismRepository

class FavoriteViewModel(tourismRepository: TourismRepository) : ViewModel() {
    val favoriteTourism = tourismRepository.getFavoriteTourism()
}