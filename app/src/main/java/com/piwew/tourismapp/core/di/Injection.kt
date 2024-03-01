package com.piwew.tourismapp.core.di

import android.content.Context
import com.piwew.tourismapp.core.data.source.TourismRepository
import com.piwew.tourismapp.core.data.source.local.LocalDataSource
import com.piwew.tourismapp.core.data.source.local.room.TourismDatabase
import com.piwew.tourismapp.core.data.source.remote.RemoteDataSource
import com.piwew.tourismapp.core.domain.repository.ITourismRepository
import com.piwew.tourismapp.core.domain.usecase.TourismInteractor
import com.piwew.tourismapp.core.domain.usecase.TourismUseCase
import com.piwew.tourismapp.core.utils.AppExecutors
import com.piwew.tourismapp.core.utils.JsonHelper

object Injection {
    private fun provideRepository(context: Context): ITourismRepository {
        val database = TourismDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutors()
        return TourismRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): TourismUseCase {
        val repository = provideRepository(context)
        return TourismInteractor(repository)
    }
}