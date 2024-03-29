package com.piwew.tourismapp.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.piwew.tourismapp.core.domain.usecase.TourismUseCase
import com.piwew.tourismapp.detail.DetailTourismViewModel
import com.piwew.tourismapp.di.AppScope
import com.piwew.tourismapp.favorite.FavoriteViewModel
import com.piwew.tourismapp.home.HomeViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(private val tourismUseCase: TourismUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(tourismUseCase) as T
            }

            modelClass.isAssignableFrom(DetailTourismViewModel::class.java) -> {
                DetailTourismViewModel(tourismUseCase) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(tourismUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}