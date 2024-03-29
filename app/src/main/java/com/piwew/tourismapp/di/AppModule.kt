package com.piwew.tourismapp.di

import com.piwew.tourismapp.core.domain.usecase.TourismInteractor
import com.piwew.tourismapp.core.domain.usecase.TourismUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideTourismUseCase(tourismInteractor: TourismInteractor): TourismUseCase
}