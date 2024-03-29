package com.piwew.tourismapp.di

import com.piwew.tourismapp.core.domain.usecase.TourismInteractor
import com.piwew.tourismapp.core.domain.usecase.TourismUseCase
import com.piwew.tourismapp.detail.DetailTourismViewModel
import com.piwew.tourismapp.favorite.FavoriteViewModel
import com.piwew.tourismapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TourismUseCase> { TourismInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailTourismViewModel(get()) }
}