package com.piwew.tourismapp.core.data.source

import androidx.lifecycle.LiveData
import com.piwew.tourismapp.core.data.source.local.LocalDataSource
import com.piwew.tourismapp.core.data.source.local.entity.TourismEntity
import com.piwew.tourismapp.core.data.source.remote.RemoteDataSource
import com.piwew.tourismapp.core.data.source.remote.network.ApiResponse
import com.piwew.tourismapp.core.data.source.remote.response.TourismResponse
import com.piwew.tourismapp.core.utils.AppExecutors
import com.piwew.tourismapp.core.utils.DataMapper

class TourismRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) {

    fun getAllTourism(): LiveData<Resource<List<TourismEntity>>> =
        object : NetworkBoundResource<List<TourismEntity>, List<TourismResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TourismEntity>> = localDataSource.getAllTourism()
            override fun createCall(): LiveData<ApiResponse<List<TourismResponse>>> = remoteDataSource.getAllTourism()
            override fun shouldFetch(data: List<TourismEntity>?): Boolean = data.isNullOrEmpty()
            override fun saveCallResult(data: List<TourismResponse>) = localDataSource.insertTourism(DataMapper.mapResponsesToEntities(data))
        }.asLiveData()

    fun getFavoriteTourism(): LiveData<List<TourismEntity>> {
        return localDataSource.getFavoriteTourism()
    }

    fun setFavoriteTourism(tourismEntity: TourismEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }

    companion object {
        @Volatile
        private var instance: TourismRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
        ): TourismRepository =
            instance ?: synchronized(this) {
                instance ?: TourismRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }
}