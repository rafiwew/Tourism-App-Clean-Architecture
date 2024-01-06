package com.piwew.tourismapp.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.piwew.tourismapp.core.data.source.TourismRepository
import com.piwew.tourismapp.core.di.Injection
import com.piwew.tourismapp.detail.DetailTourismViewModel
import com.piwew.tourismapp.favorite.FavoriteViewModel
import com.piwew.tourismapp.home.HomeViewModel

class ViewModelFactory private constructor(private val tourismRepository: TourismRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(tourismRepository) as T
            }
            modelClass.isAssignableFrom(DetailTourismViewModel::class.java) -> {
                DetailTourismViewModel(tourismRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(tourismRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }
}