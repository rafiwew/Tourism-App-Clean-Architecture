package com.piwew.tourismapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.piwew.tourismapp.core.data.source.Resource
import com.piwew.tourismapp.core.domain.model.Tourism

interface ITourismRepository {
    fun getAllTourism(): LiveData<Resource<List<Tourism>>>
    fun getFavoriteTourism(): LiveData<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}