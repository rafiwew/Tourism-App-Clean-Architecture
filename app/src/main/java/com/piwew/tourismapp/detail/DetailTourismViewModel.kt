package com.piwew.tourismapp.detail

import androidx.lifecycle.ViewModel
import com.piwew.tourismapp.core.data.source.TourismRepository
import com.piwew.tourismapp.core.domain.model.Tourism

class DetailTourismViewModel(private val tourismRepository: TourismRepository) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus: Boolean) =
        tourismRepository.setFavoriteTourism(tourism, newStatus)
}