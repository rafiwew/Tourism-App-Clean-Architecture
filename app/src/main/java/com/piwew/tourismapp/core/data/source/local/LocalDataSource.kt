package com.piwew.tourismapp.core.data.source.local

import androidx.lifecycle.LiveData
import com.piwew.tourismapp.core.data.source.local.entity.TourismEntity
import com.piwew.tourismapp.core.data.source.local.room.TourismDao

class LocalDataSource private constructor(private val tourismDao: TourismDao) {

    fun getAllTourism(): LiveData<List<TourismEntity>> = tourismDao.getAllTourism()

    fun getFavoriteTourism(): LiveData<List<TourismEntity>> = tourismDao.getFavoriteTourism()

    fun insertTourism(tourismList: List<TourismEntity>) = tourismDao.insertTourism(tourismList)

    fun setFavoriteTourism(tourismEntity: TourismEntity, newState: Boolean) {
        tourismEntity.isFavorite = newState
        tourismDao.updateFavoriteTourism(tourismEntity)
    }

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: TourismDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }
}