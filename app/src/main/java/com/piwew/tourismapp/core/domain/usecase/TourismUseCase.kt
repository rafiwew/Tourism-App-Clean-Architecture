package com.piwew.tourismapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.piwew.tourismapp.core.data.source.Resource
import com.piwew.tourismapp.core.domain.model.Tourism

interface TourismUseCase {
    fun getAllTourism(): LiveData<Resource<List<Tourism>>>
    fun getFavoriteTourism(): LiveData<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}