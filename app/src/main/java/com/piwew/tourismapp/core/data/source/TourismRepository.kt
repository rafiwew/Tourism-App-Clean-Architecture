package com.piwew.tourismapp.core.data.source

import com.piwew.tourismapp.core.data.source.local.LocalDataSource
import com.piwew.tourismapp.core.data.source.remote.RemoteDataSource
import com.piwew.tourismapp.core.data.source.remote.network.ApiResponse
import com.piwew.tourismapp.core.data.source.remote.response.TourismResponse
import com.piwew.tourismapp.core.domain.model.Tourism
import com.piwew.tourismapp.core.domain.repository.ITourismRepository
import com.piwew.tourismapp.core.utils.AppExecutors
import com.piwew.tourismapp.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TourismRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : ITourismRepository {

    override fun getAllTourism(): Flowable<Resource<List<Tourism>>> =
        object : NetworkBoundResource<List<Tourism>, List<TourismResponse>>() {
            override fun loadFromDB(): Flowable<List<Tourism>> {
                return localDataSource.getAllTourism().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun createCall(): Flowable<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            override fun shouldFetch(data: List<Tourism>?): Boolean = data.isNullOrEmpty()

            override fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getFavoriteTourism(): Flowable<List<Tourism>> {
        return localDataSource.getFavoriteTourism().map { DataMapper.mapEntitiesToDomain(it) }
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