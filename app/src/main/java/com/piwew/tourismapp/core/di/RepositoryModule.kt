package com.piwew.tourismapp.core.di

import com.piwew.tourismapp.core.data.source.TourismRepository
import com.piwew.tourismapp.core.domain.repository.ITourismRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: TourismRepository): ITourismRepository
}