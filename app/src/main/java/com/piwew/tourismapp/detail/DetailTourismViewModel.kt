package com.piwew.tourismapp.detail

import androidx.lifecycle.ViewModel
import com.piwew.tourismapp.core.data.source.TourismRepository
import com.piwew.tourismapp.core.data.source.local.entity.TourismEntity

class DetailTourismViewModel(private val tourismRepository: TourismRepository) : ViewModel() {
    fun setFavoriteTourism(tourismEntity: TourismEntity, newStatus: Boolean) =
        tourismRepository.setFavoriteTourism(tourismEntity, newStatus)
}