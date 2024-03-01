package com.piwew.tourismapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.piwew.tourismapp.core.data.source.Resource
import com.piwew.tourismapp.core.domain.model.Tourism
import com.piwew.tourismapp.core.domain.repository.ITourismRepository

class TourismInteractor(private val tourismRepository: ITourismRepository): TourismUseCase {
    override fun getAllTourism(): LiveData<Resource<List<Tourism>>> {
        return tourismRepository.getAllTourism()
    }

    override fun getFavoriteTourism(): LiveData<List<Tourism>> {
        return tourismRepository.getFavoriteTourism()
    }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        tourismRepository.setFavoriteTourism(tourism, state)
    }
}