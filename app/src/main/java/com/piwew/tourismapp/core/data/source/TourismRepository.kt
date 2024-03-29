package com.piwew.tourismapp.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.piwew.tourismapp.core.data.source.local.LocalDataSource
import com.piwew.tourismapp.core.data.source.remote.RemoteDataSource
import com.piwew.tourismapp.core.data.source.remote.network.ApiResponse
import com.piwew.tourismapp.core.data.source.remote.response.TourismResponse
import com.piwew.tourismapp.core.domain.model.Tourism
import com.piwew.tourismapp.core.domain.repository.ITourismRepository
import com.piwew.tourismapp.core.utils.AppExecutors
import com.piwew.tourismapp.core.utils.DataMapper

class TourismRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : ITourismRepository {

    override fun getAllTourism(): LiveData<Resource<List<Tourism>>> =
        object : NetworkBoundResource<List<Tourism>, List<TourismResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Tourism>> {
                return localDataSource.getAllTourism().switchMap { data ->
                    MutableLiveData(DataMapper.mapEntitiesToDomain(data))
                }
            }
            override fun createCall(): LiveData<ApiResponse<List<TourismResponse>>> = remoteDataSource.getAllTourism()
            override fun shouldFetch(data: List<Tourism>?): Boolean = data.isNullOrEmpty()
            override fun saveCallResult(data: List<TourismResponse>) = localDataSource.insertTourism(DataMapper.mapResponsesToEntities(data))
        }.asLiveData()

    override fun getFavoriteTourism(): LiveData<List<Tourism>> {
        return localDataSource.getFavoriteTourism().switchMap { data ->
            MutableLiveData(DataMapper.mapEntitiesToDomain(data))
        }
    }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
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