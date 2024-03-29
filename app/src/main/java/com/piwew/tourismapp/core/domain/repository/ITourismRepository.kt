package com.piwew.tourismapp.core.domain.repository

import com.piwew.tourismapp.core.data.source.Resource
import com.piwew.tourismapp.core.domain.model.Tourism
import io.reactivex.Flowable

interface ITourismRepository {
    fun getAllTourism(): Flowable<Resource<List<Tourism>>>
    fun getFavoriteTourism(): Flowable<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}